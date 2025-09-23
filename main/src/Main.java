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
import benchmark.BenchmarkResult;
import benchmark.BenchmarkRunner;

import java.io.File;
import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static boolean printArray = false;
    private static int auxExecutions = 100; // default
    private static BenchmarkRunner runner;
    private static final List<BenchmarkResult> lastResults = new ArrayList<>();

    private static final long MAX_ELEMENTS = 1_000_000_000L;

    public static void main(String[] args) {
        configureParams();
        runner = new BenchmarkRunner(auxExecutions);

        Map<Integer, Runnable> options = new HashMap<>();
        options.put(1, Main::executeBubbleSort);
        options.put(2, Main::executeMergeSort);
        options.put(3, Main::executeOrdersSystem);
        options.put(4, Main::executeTrees);
        options.put(5, Main::executeSum);
        options.put(6, Main::configureParams);
        options.put(7, Main::exportResults);

        while (true) {
            try {
                System.out.println("\n===== MENU DE TESTES =====");
                System.out.println("1 - Bubble Sort");
                System.out.println("2 - Merge Sort");
                System.out.println("3 - Sistema de Pedidos");
                System.out.println("4 - Árvores");
                System.out.println("5 - Soma Sequencial e Paralela");
                System.out.println("6 - Alterar preferências");
                System.out.println("7 - Exportar últimos resultados em JSON");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                int choice = scanner.nextInt();
                if (choice == 0) {
                    System.out.println("Encerrando...");
                    return;
                }
                options.getOrDefault(choice, () -> System.out.println("Opção inválida!")).run();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                scanner.nextLine();
            }
        }
    }

    private static void configureParams() {
        try {
            String envPrint = System.getenv("PRINT_ARRAY");
            String envExec = System.getenv("EXECUTIONS");

            if (envPrint != null) {
                printArray = envPrint.equalsIgnoreCase("true");
            } else {
                System.out.print("\nDeseja imprimir os arrays/listas? (S/N): ");
                printArray = scanner.next().trim().equalsIgnoreCase("S");
            }

            if (envExec != null) {
                auxExecutions = Math.max(Integer.parseInt(envExec), 1);
            } else {
                System.out.print("Quantas execuções deseja por teste? (100, 500, 1000, 10000 ou qualquer número > 0): ");
                int value = scanner.nextInt();
                if (value > 0) {
                    if (value >= 100000) {
                        System.out.println("Atenção: valores >= 100000 podem demorar bastante para concluir.");
                    }
                    auxExecutions = value;
                } else {
                    System.out.println("Opção inválida, será usado 100 por padrão.");
                    auxExecutions = 100;
                }
            }

            runner = new BenchmarkRunner(auxExecutions);
            System.out.println("Configuração atualizada! Execuções = " + auxExecutions);

        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Usando padrão: 100 execuções.");
            auxExecutions = 100;
            scanner.nextLine();
        }
    }

    private static void executeBubbleSort() {
        try {
            System.out.print("Digite o tamanho do array: ");
            long size = scanner.nextLong();

            if (size > MAX_ELEMENTS) {
                System.out.println("Valor muito grande! Limite máximo permitido: " + MAX_ELEMENTS);
                return;
            }

            int[] baseArray = Util.randomIntArray((int) size, 1, 10000);

            lastResults.addAll(runner.runComparison(
                    "Bubble Sort",
                    () -> {
                        int[] arr = Arrays.copyOf(baseArray, (int) size);
                        if (printArray) System.out.println("Array original: " + Arrays.toString(arr));
                        FunctionalBubbleSort.order(arr);
                    },
                    () -> {
                        int[] arr = Arrays.copyOf(baseArray, (int) size);
                        if (printArray) System.out.println("Array original: " + Arrays.toString(arr));
                        ImperativeBubbleSort.order(arr);
                    }
            ));

        } catch (OutOfMemoryError e) {
            System.out.println("Memória insuficiente para este tamanho! Escolha um valor menor.");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Digite apenas números.");
            scanner.nextLine();
        }
    }

    private static void executeMergeSort() {
        try {
            System.out.print("Digite o tamanho do array: ");
            long size = scanner.nextLong();

            if (size > MAX_ELEMENTS) {
                System.out.println("Valor muito grande! Limite máximo permitido: " + MAX_ELEMENTS);
                return;
            }

            int[] baseArray = Util.randomIntArray((int) size, 1, 10000);

            lastResults.addAll(runner.runComparison(
                    "Merge Sort",
                    () -> {
                        int[] arr = Arrays.copyOf(baseArray, (int) size);
                        if (printArray) System.out.println("Array original: " + Arrays.toString(arr));
                        FunctionalMergeSort.order(arr);
                    },
                    () -> {
                        int[] arr = Arrays.copyOf(baseArray, (int) size);
                        if (printArray) System.out.println("Array original: " + Arrays.toString(arr));
                        ImperativeMergeSort.order(arr);
                    }
            ));

        } catch (OutOfMemoryError e) {
            System.out.println("Memória insuficiente para este tamanho! Escolha um valor menor.");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Digite apenas números.");
            scanner.nextLine();
        }
    }

    private static void executeOrdersSystem() {
        try {
            System.out.print("Digite a quantidade de pedidos: ");
            long qtd = scanner.nextLong();

            if (qtd > MAX_ELEMENTS) {
                System.out.println("Valor muito grande! Limite máximo permitido: " + MAX_ELEMENTS);
                return;
            }

            List<Order> orders = Util.randomOrders((int) qtd);

            if (printArray) System.out.println("Pedidos gerados: " + orders);

            lastResults.addAll(runner.runComparison(
                    "Sistema de Pedidos",
                    () -> {
                        FunctionalOrdering.filterByCategoryAndMinValue(orders, 50);
                        FunctionalOrdering.orderBy(orders, false);
                    },
                    () -> {
                        ImperativeOrdering.filterByCategoryAndMinValue(orders, 50);
                        ImperativeOrdering.orderBy(orders, false);
                    }
            ));

        } catch (OutOfMemoryError e) {
            System.out.println("Memória insuficiente para essa quantidade de pedidos!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Digite apenas números.");
            scanner.nextLine();
        }
    }

    private static void executeTrees() {
        try {
            System.out.print("Digite a altura da árvore: ");
            long altura = scanner.nextLong();

            if (altura > 30) {
                System.out.println("Altura muito grande! Escolha até 30 para evitar estouro de memória.");
                return;
            }

            Node tree = Util.randomTree((int) altura, 1, 100);

            lastResults.addAll(runner.runComparison(
                    "Árvores",
                    () -> {
                        Node t = FunctionalTree.tripleValues(tree);
                        FunctionalTree.collectEven(t);
                    },
                    () -> {
                        ImperativeTree.tripleValues(tree);
                        ImperativeTree.collectEven(tree);
                    }
            ));

        } catch (OutOfMemoryError e) {
            System.out.println("Memória insuficiente para criar a árvore!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Digite apenas números.");
            scanner.nextLine();
        }
    }

    private static void executeSum() {
        try {
            System.out.print("Digite o número de elementos: ");
            long n = scanner.nextLong();

            if (n > MAX_ELEMENTS) {
                System.out.println("⚠Valor muito grande! Limite máximo permitido: " + MAX_ELEMENTS);
                return;
            }

            lastResults.addAll(runner.runComparison(
                    "Soma Sequencial e Paralela",
                    () -> FunctionalSimpleOperation.sumElements(n),
                    () -> ImperativeSimpleOperation.sumElements(n)
            ));

        } catch (OutOfMemoryError e) {
            System.out.println("Memória insuficiente para esse número de elementos!");
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida! Digite apenas números.");
            scanner.nextLine();
        }
    }

    private static void exportResults() {
        if (lastResults.isEmpty()) {
            System.out.println("Nenhum resultado para exportar. Execute algum teste primeiro.");
            return;
        }

        try {
            String dirPath = "results";
            File dir = new File(dirPath);
            if (!dir.exists()) dir.mkdirs();

            String filePath = dirPath + "/benchmark_results.json";
            runner.exportToJson(filePath, lastResults);

            System.out.println("Resultados exportados com sucesso em: " + filePath);

        } catch (Exception e) {
            System.out.println("Erro ao exportar resultados: " + e.getMessage());
        }
    }
}