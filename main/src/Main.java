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
import java.util.Scanner;

public class Main {

        private static final Scanner scanner = new Scanner(System.in);
        private static final Runtime runtime = Runtime.getRuntime();

        private static boolean printArray = false;
        private static int auxExecutions = 1;

        public static void main(String[] args) {
            configureParams();

            while (true) {
                System.out.println("\n===== MENU DE TESTES =====");
                System.out.println("1 - Testar Bubble Sort");
                System.out.println("2 - Testar Merge Sort");
                System.out.println("3 - Testar Sistema de Pedidos");
                System.out.println("4 - Testar Árvores");
                System.out.println("5 - Testar Soma Sequencial e Paralela");
                System.out.println("6 - Alterar preferências");
                System.out.println("0 - Sair");
                System.out.print("Escolha uma opção: ");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> executeBubbleSort();
                    case 2 -> executeMergeSort();
                    case 3 -> executeOrdersSystem();
                    case 4 -> executeTrees();
                    case 5 -> executeSum();
                    case 6 -> configureParams();
                    case 0 -> {
                        System.out.println("Encerrando...");
                        return;
                    }
                    default -> System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }

    private static void configureParams() {
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
            System.out.print("Quantas execuções deseja por teste? (ex.: 1, 5, 10): ");
            auxExecutions = Math.max(scanner.nextInt(), 1);
        }
    }


    private static void executeBubbleSort() {
            System.out.print("Digite o tamanho do array: ");
            int size = scanner.nextInt();
            int[] baseArray = Util.randomIntArray(size, 1, 10000);

            runAndCompare(
                    "Bubble Sort",
                    () -> {
                        int[] arr = Arrays.copyOf(baseArray, size);
                        if (printArray) System.out.println("Array original: " + Arrays.toString(arr));
                        FunctionalBubbleSort.order(arr);
                    },
                    () -> {
                        int[] arr = Arrays.copyOf(baseArray, size);
                        if (printArray) System.out.println("Array original: " + Arrays.toString(arr));
                        ImperativeBubbleSort.order(arr);
                    }
            );
        }

        private static void executeMergeSort() {
            System.out.print("Digite o tamanho do array: ");
            int size = scanner.nextInt();
            int[] baseArray = Util.randomIntArray(size, 1, 10000);

            runAndCompare(
                    "Merge Sort",
                    () -> {
                        int[] arr = Arrays.copyOf(baseArray, size);
                        if (printArray) System.out.println("Array original: " + Arrays.toString(arr));
                        FunctionalMergeSort.order(arr);
                    },
                    () -> {
                        int[] arr = Arrays.copyOf(baseArray, size);
                        if (printArray) System.out.println("Array original: " + Arrays.toString(arr));
                        ImperativeMergeSort.order(arr);
                    }
            );
        }

        private static void executeOrdersSystem() {
            System.out.print("Digite a quantidade de pedidos: ");
            int qtd = scanner.nextInt();
            List<Order> orders = Util.randomOrders(qtd);

            if (printArray) System.out.println("Pedidos gerados: " + orders);

            runAndCompare(
                    "Sistema de Pedidos",
                    () -> {
                        FunctionalOrdering.filterByCategoryAndMinValue(orders, 50);
                        FunctionalOrdering.orderBy(orders, false);
                    },
                    () -> {
                        ImperativeOrdering.filterByCategoryAndMinValue(orders, 50);
                        ImperativeOrdering.orderBy(orders, false);
                    }
            );
        }

        private static void executeTrees() {
            System.out.print("Digite a altura da árvore: ");
            int altura = scanner.nextInt();
            Node tree = Util.randomTree(altura, 1, 100);

            runAndCompare(
                    "Árvores",
                    () -> {
                        Node t = FunctionalTree.tripleValues(tree);
                        FunctionalTree.collectEven(t);
                    },
                    () -> {
                        ImperativeTree.tripleValues(tree);
                        ImperativeTree.collectEven(tree);
                    }
            );
        }

        private static void executeSum() {
            System.out.print("Digite o número de elementos: ");
            long n = scanner.nextLong();

            runAndCompare(
                    "Soma Sequencial e Paralela",
                    () -> FunctionalSimpleOperation.sumElements(n),
                    () -> ImperativeSimpleOperation.sumElements(n)
            );
        }

        private static void runAndCompare(String label, Runnable functionalTask, Runnable imperativeTask) {
            double[] funcMetrics = executeMultiple(functionalTask, auxExecutions);
            double[] impMetrics = executeMultiple(imperativeTask, auxExecutions);

            System.out.printf("\n===== RESULTADOS: %s =====%n", label);
            System.out.printf("Funcional -> Tempo médio: %.2f ms | Memória média: %.2f KB%n",
                    funcMetrics[0], funcMetrics[1]);
            System.out.printf("Imperativo -> Tempo médio: %.2f ms | Memória média: %.2f KB%n",
                    impMetrics[0], impMetrics[1]);
            System.out.println("--------------------------------------------------");
        }

        private static double[] executeMultiple(Runnable task, int times) {
            long sumTime = 0;
            long sumMemory = 0;

            for (int i = 0; i < times; i++) {
                System.gc();
                long startMemory = runtime.totalMemory() - runtime.freeMemory();
                long startTime = System.currentTimeMillis();

                task.run();

                long endTime = System.currentTimeMillis();
                long endMemory = runtime.totalMemory() - runtime.freeMemory();

                sumTime += (endTime - startTime);
                sumMemory += (endMemory - startMemory);
            }

            double averageTime = (double) sumTime / times;
            double averageMemoryKB = (double) sumMemory / times / 1024.0;
            return new double[]{averageTime, averageMemoryKB};
        }
    }