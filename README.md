# Benchmark de Algoritmos: Paradigmas Funcional vs Imperativo

Este projeto é uma aplicação Java que compara algoritmos implementados nos paradigmas **imperativo** e **funcional**.  
O objetivo é **avaliar desempenho (tempo de execução, uso de memória e consistência)** de diferentes operações, incluindo ordenação, operações simples e manipulação de árvores.

---

## 📂 Estrutura do Projeto

```
src/main/java/
├── Main.java                  # Ponto de entrada do programa
├── ApplicationRunner.java     # Responsável pela execução do menu e controle do fluxo
├── algorithms/
│   ├── bubblesort/            # Bubble Sort (funcional e imperativo)
│   ├── mergesort/             # Merge Sort (funcional e imperativo)
│   ├── orderingsystem/        # Sistema de pedidos (funcional e imperativo)
│   ├── simpleoperations/      # Soma sequencial e paralela
│   └── tree/                  # Árvores (funcional e imperativo)
├── benchmark/                 
│   ├── BenchmarkRunner.java   # Orquestra execução dos testes
│   └── BenchmarkResult.java   # Estrutura de resultado (tempo, memória, etc.)
└── util/
    └── Util.java              # Funções auxiliares (geração de arrays, árvores, pedidos)
```

- **`results/benchmark_results.json`** → contém os últimos resultados exportados em JSON.

---

## 🚀 Funcionalidades

O programa apresenta um **menu interativo** com as seguintes opções:

1. **Bubble Sort**  
   - Ordenação de arrays aleatórios com Bubble Sort (funcional e imperativo).

2. **Merge Sort**  
   - Ordenação de arrays aleatórios com Merge Sort (funcional e imperativo).

3. **Sistema de Pedidos**  
   - Filtragem e ordenação de listas de pedidos com base em categoria e valor mínimo.

4. **Árvores**  
   - Manipulação de árvores, triplicando valores e coletando pares.

5. **Soma Sequencial e Paralela**  
   - Soma de elementos de 1 a n, comparando sequencial vs paralelo.

6. **Alterar Preferências**  
   - Definir:
     - Impressão de arrays/listas (`PRINT_ARRAY`).
     - Número de execuções por teste (`EXECUTIONS`).

7. **Exportar Resultados**  
   - Salva os últimos resultados em **JSON** na pasta `results/`.

---

## 🐳 Executando via Docker

### 1️⃣ Construir a imagem

```bash
docker build -t algorithms-app .
```

### 2️⃣ Rodar de forma interativa

```bash
docker run -it algorithms-app
```

### 3️⃣ Configurar variáveis de ambiente (opcional)

```bash
docker run -it -e PRINT_ARRAY=true -e EXECUTIONS=10 algorithms-app
```

---

## 📊 Resultados do Benchmark

Para cada teste, são calculados:

- **Tempo médio** (ms)  
- **Memória média** (KB)  
- **Melhor e pior execução**  
- **Desvio padrão**

Exemplo:

```
>>> Iniciando benchmark: Merge Sort
Funcional   -> Média: 12,34 ms, Memória: 1,23 KB, Melhor: 10 ms, Pior: 18 ms, Desvio Padrão: 2,1
Imperativo  -> Média: 15,67 ms, Memória: 1,45 KB, Melhor: 14 ms, Pior: 22 ms, Desvio Padrão: 3,0
--------------------------------------------------
```

---

## ⚠️ Tratamentos e Limites

- Entradas inválidas ou muito grandes são tratadas com mensagens amigáveis.  
- Arrays acima de **2 bilhões de elementos** não são permitidos.  
- Excessos de memória são capturados com `OutOfMemoryError`.  
- O Bubble Sort é intencionalmente ineficiente para grandes arrays (usado apenas para fins didáticos).  

---

## 🔧 Requisitos

- **Java 17**  
- Docker (opcional)  
- [Gson](https://github.com/google/gson) (caso rode fora do Docker, adicione `gson-x.x.x.jar` em `libs/`)

---

## 📫 Contato

Autor: **Paulo Uchoa**  
E-mail: **paulojosevieira2011@gmail.com**  
Projeto para **Trabalho de Conclusão de Curso - Imperativo vs Funcional: Dois caminhos para resolver o mesmo problema**
