# Algorithm Benchmark: Functional vs Imperative Paradigms

A Java application that compares algorithms implemented in the **imperative** and **functional**
paradigms. The goal is to **evaluate performance (execution time, memory usage and consistency)**
across different operations, including sorting, simple operations and tree manipulation.

---

## 📂 Project Structure

```
src/main/java/
├── Main.java                  # Program entry point
├── ApplicationRunner.java     # Runs the menu and controls the flow
├── algorithms/
│   ├── bubblesort/            # Bubble Sort (functional and imperative)
│   ├── mergesort/             # Merge Sort (functional and imperative)
│   ├── orderingsystem/        # Ordering system (functional and imperative)
│   ├── simpleoperations/      # Sequential and parallel sum
│   └── tree/                  # Trees (functional and imperative)
├── benchmark/
│   ├── BenchmarkRunner.java   # Orchestrates test execution
│   └── BenchmarkResult.java   # Result structure (time, memory, etc.)
└── util/
    └── Util.java              # Helper functions (array/tree/order generation)
```

- **`results/benchmark_results.json`** → contains the latest exported results in JSON.

---

## 🚀 Features

The program offers an **interactive menu** with the following options:

1. **Bubble Sort** — sort random arrays with Bubble Sort (functional and imperative).
2. **Merge Sort** — sort random arrays with Merge Sort (functional and imperative).
3. **Ordering System** — filter and sort order lists by category and minimum value.
4. **Trees** — manipulate trees, tripling values and collecting pairs.
5. **Sequential and Parallel Sum** — sum elements from 1 to n, comparing sequential vs parallel.
6. **Change Preferences** — set:
   - Array/list printing (`PRINT_ARRAY`).
   - Number of executions per test (`EXECUTIONS`).
7. **Export Results** — save the latest results as **JSON** in the `results/` folder.

---

## 🐳 Running with Docker

### 1️⃣ Build the image

```bash
docker build -t algorithms-app .
```

### 2️⃣ Run interactively

```bash
docker run -it algorithms-app
```

### 3️⃣ Configure environment variables (optional)

```bash
docker run -it -e PRINT_ARRAY=true -e EXECUTIONS=10 algorithms-app
```

---

## 📊 Benchmark Results

For each test, the following are computed:

- **Average time** (ms)
- **Average memory** (KB)
- **Best and worst execution**
- **Standard deviation**

Example:

```
>>> Starting benchmark: Merge Sort
Functional  -> Avg: 12.34 ms, Memory: 1.23 KB, Best: 10 ms, Worst: 18 ms, Std Dev: 2.1
Imperative  -> Avg: 15.67 ms, Memory: 1.45 KB, Best: 14 ms, Worst: 22 ms, Std Dev: 3.0
--------------------------------------------------
```

---

## ⚠️ Handling & Limits

- Invalid or very large inputs are handled with friendly messages.
- Arrays larger than **2 billion elements** are not allowed.
- Out-of-memory situations are caught with `OutOfMemoryError`.
- Bubble Sort is intentionally inefficient for large arrays (used for educational purposes only).

---

## 🔧 Requirements

- **Java 17**
- Docker (optional)
- [Gson](https://github.com/google/gson) (if running outside Docker, add `gson-x.x.x.jar` to `libs/`)

---

## 📫 Contact

Author: **Paulo Uchoa**
E-mail: **paulojosevieira2011@gmail.com**
Project for the **Capstone Project — Imperative vs Functional: Two paths to solve the same problem**
