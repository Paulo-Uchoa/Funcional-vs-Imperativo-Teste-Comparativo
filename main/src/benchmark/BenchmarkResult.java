package benchmark;

public class BenchmarkResult {

        private final String label;
        private final String paradigm;
        private final double avgTimeMs;
        private final double avgMemoryKb;
        private final long bestTimeMs;
        private final long worstTimeMs;
        private final double stdDevTime;

        public BenchmarkResult(String label, String paradigm, double avgTimeMs, double avgMemoryKb,
                               long bestTimeMs, long worstTimeMs, double stdDevTime) {
            this.label = label;
            this.paradigm = paradigm;
            this.avgTimeMs = avgTimeMs;
            this.avgMemoryKb = avgMemoryKb;
            this.bestTimeMs = bestTimeMs;
            this.worstTimeMs = worstTimeMs;
            this.stdDevTime = stdDevTime;
        }

        public String getLabel() { return label; }
        public String getParadigm() { return paradigm; }
        public double getAvgTimeMs() { return avgTimeMs; }
        public double getAvgMemoryKb() { return avgMemoryKb; }
        public long getBestTimeMs() { return bestTimeMs; }
        public long getWorstTimeMs() { return worstTimeMs; }
        public double getStdDevTime() { return stdDevTime; }

        @Override
        public String toString() {
            return String.format("%s (%s) -> Média: %.2f ms, Memória: %.2f KB, Melhor: %d ms, Pior: %d ms, Desvio Padrão: %.2f",
                    label, paradigm, avgTimeMs, avgMemoryKb, bestTimeMs, worstTimeMs, stdDevTime);
        }
}
