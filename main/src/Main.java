import algorithms.bubblesort.FunctionalBubbleSort;
import algorithms.bubblesort.ImperativeBubbleSort;
import algorithms.mergesort.FunctionalMergeSort;
import algorithms.mergesort.ImperativeMergeSort;
import algorithms.orderingsystem.FunctionalOrdering;
import algorithms.orderingsystem.ImperativeOrdering;
import algorithms.orderingsystem.Order;
import algorithms.simpleoperations.FunctionalSimpleOperation;
import algorithms.simpleoperations.ImperativeSimpleOperation;
import algorithms.tree.FunctionalTree;
import algorithms.tree.ImperativeTree;
import algorithms.tree.Node;
import algorithms.util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {


    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();

        // ============================
        // Teste Bubble Sort
        // ============================
        System.out.println("===== Teste Bubble Sort =====");
        int[] arr1 = Util.randomIntArray(20, 1, 1000); // 20 elementos, valores aleatórios 1-10000
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);

        measureExecution("Funcional Bubble Sort", runtime, () -> {
            int[] sorted = FunctionalBubbleSort.order(arr1);
            System.out.println("Array: " + Arrays.toString(arr1));
            System.out.println("Resultado: " + Arrays.toString(sorted));
        });

        measureExecution("Imperativo Bubble Sort", runtime, () -> {
            int[] sorted = ImperativeBubbleSort.order(arr2);
            System.out.println("Array: " + Arrays.toString(arr2));
            System.out.println("Resultado: " + Arrays.toString(sorted));
        });

        // ============================
        // Teste Merge Sort
        // ============================
        System.out.println("\n===== Teste Merge Sort =====");
        int[] arr3 = Util.randomIntArray(20 , 1, 10000); // 20 elementos, valores aleatórios 1-10000
        int[] arr4 = Arrays.copyOf(arr3, arr3.length);

        measureExecution("Funcional Merge Sort", runtime, () -> {
            int[] sorted = FunctionalMergeSort.order(arr3);
           System.out.println("Array: " + Arrays.toString(arr3));
           System.out.println("Resultado: " + Arrays.toString(sorted));
        });

        measureExecution("Imperativo Merge Sort", runtime, () -> {
            int[] sorted = ImperativeMergeSort.order(arr4);
            System.out.println("Array: " + Arrays.toString(arr4));
            System.out.println("Resultado: " + Arrays.toString(sorted));
        });

        // ============================
        // Teste Sistema de Pedidos
        // ============================
        System.out.println("\n===== Teste Sistema de Pedidos =====");
        List<Order> orders = Util.randomOrders(6); // 4 pedidos aleatórios
        System.out.println("Pedidos gerados: " + orders);

        measureExecution("Funcional Filtro Pedidos > 50", runtime, () -> {
            Map<String, Double> filtered = FunctionalOrdering.filterByCategoryAndMinValue(orders, 50);
            System.out.println("Resultado: " + filtered);
        });

        measureExecution("Funcional Pedidos Ordenados Desc", runtime, () -> {
            Map<String, Double> sorted = FunctionalOrdering.orderBy(orders, false);
            System.out.println("Resultado: " + sorted);
        });

        measureExecution("Imperativo Filtro Pedidos > 50", runtime, () -> {
            Map<String, Double> filtered = ImperativeOrdering.filterByCategoryAndMinValue(orders, 50);
            System.out.println("Resultado: " + filtered);
        });

        measureExecution("Imperativo Pedidos Ordenados Desc", runtime, () -> {
            Map<String, Double> sorted = ImperativeOrdering.orderBy(orders, false);
            System.out.println("Resultado: " + sorted);
        });

        // ============================
        // Teste Árvores
        // ============================
        System.out.println("\n===== Teste Árvores =====");
        Node tree = Util.randomTree(7, 1, 100); // altura 7, valores 1-100

        measureExecution("Funcional Árvores (triplo e pares)", runtime, () -> {
            Node tripledTree = FunctionalTree.tripleValues(tree);
            List<Integer> result = FunctionalTree.collectEven(tripledTree);
            System.out.println("Resultado: " + result);
        });

        measureExecution("Imperativo Árvores (triplo e pares)", runtime, () -> {
            ImperativeTree.tripleValues(tree);
            List<Integer> result = ImperativeTree.collectEven(tree);
            System.out.println("Resultado: " + result);
        });

        System.out.println("\n===== Soma de Elementos de N elementos - Sequencial e Paralela =====");

        measureExecution("Funcional", runtime, () -> {
            long result = FunctionalSimpleOperation.sumElements(1000000000L);
            System.out.println("Resultado: " + result);
        });

        measureExecution("Imperativo", runtime, () -> {
            long result = ImperativeSimpleOperation.sumElements(1000000000L);
            System.out.println("Resultado: " + result);
        });

    }

        // ============================
        // Método auxiliar de medição
        // ============================
        private static void measureExecution(String label, Runtime runtime, Runnable task) {
            // Força coleta de lixo antes para medir memória usada apenas pelo bloco
            System.gc();

            long startMemory = runtime.totalMemory() - runtime.freeMemory();
            long startTime = System.currentTimeMillis();

            task.run();

            long endTime = System.currentTimeMillis();
            long endMemory = runtime.totalMemory() - runtime.freeMemory();

            System.out.printf("%s -> Tempo: %d ms | Memória estimada: %.2f KB%n",
                    label, (endTime - startTime), (endMemory - startMemory) / 1024.0);
            System.out.println("--------------------------------------------------");
        }
}