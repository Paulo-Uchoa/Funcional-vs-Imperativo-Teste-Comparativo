package benchmark;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BenchmarkRunner {

    private static final Runtime runtime = Runtime.getRuntime();
    private final int executions;

    public BenchmarkRunner(int executions) {
        this.executions = Math.max(executions, 1);
    }

    public List<BenchmarkResult> runComparison(String label, Runnable functionalTask, Runnable imperativeTask) {
        long globalStart = System.currentTimeMillis();
        System.out.println("\n>>> Iniciando benchmark: " + label);

        List<BenchmarkResult> results = new ArrayList<>();
        results.add(execute(label, "Funcional", functionalTask));
        results.add(execute(label, "Imperativo", imperativeTask));

        results.forEach(System.out::println);
        System.out.println("--------------------------------------------------");

        long globalEnd = System.currentTimeMillis();
        double totalSecs = (globalEnd - globalStart) / 1000.0;
        System.out.printf(">>> Benchmark concluído em %.2f segundos.%n", totalSecs);

        return results;
    }

    private BenchmarkResult execute(String label, String paradigm, Runnable task) {

       try{
        long[] times = new long[executions];
        long[] memories = new long[executions];

        for (int i = 0; i < executions; i++) {
            System.gc();
            long startMemory = runtime.totalMemory() - runtime.freeMemory();
            long startTime = System.nanoTime();

            task.run();

            long endTime = System.nanoTime();
            long endMemory = runtime.totalMemory() - runtime.freeMemory();

            times[i] = (endTime - startTime) / 1_000_000;
            memories[i] = (endMemory - startMemory);
        }

        double avgTime = Arrays.stream(times).average().orElse(0);
        double avgMemoryKb = Arrays.stream(memories).average().orElse(0) / 1024.0;
        long bestTime = Arrays.stream(times).min().orElse(0);
        long worstTime = Arrays.stream(times).max().orElse(0);
        double stdDevTime = calculateStdDev(times, avgTime);

        return new BenchmarkResult(label, paradigm, avgTime, avgMemoryKb, bestTime, worstTime, stdDevTime);

       } catch (StackOverflowError e) {
           System.err.println("Erro: StackOverflow durante execução (" + label + " - " + paradigm + ")");
           return new BenchmarkResult(label, paradigm, 0, 0, 0, 0, 0);
       } catch (OutOfMemoryError e) {
           System.err.println("Erro: Memória insuficiente durante execução (" + label + " - " + paradigm + ")");
           return new BenchmarkResult(label, paradigm, 0, 0, 0, 0, 0);
       }
    }

    private double calculateStdDev(long[] values, double mean) {
        double variance = 0;
        for (long v : values) {
            variance += Math.pow(v - mean, 2);
        }
        return Math.sqrt(variance / values.length);
    }

    public void exportToJson(String filePath, List<BenchmarkResult> results) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(results, writer);
            System.out.println("Exportando resultados para pasta raiz " + filePath);
        } catch (IOException e) {
            System.err.println("Erro ao exportar JSON: " + e.getMessage());
        }
    }
}