# Benchmark de Algoritmos: Paradigmas Funcional vs Imperativo

Este projeto é uma aplicação Java que realiza comparações entre algoritmos implementados nos paradigmas **imperativo** e **funcional**. O objetivo é avaliar desempenho (tempo de execução e uso de memória) de diferentes operações, incluindo ordenação, operações simples e manipulação de árvores.

---

## Estrutura do Projeto

```
main/src/
├── Main.java                 # Classe principal com menu interativo
├── algorithms/
│   ├── bubblesort/           # Implementações Bubble Sort (funcional e imperativa)
│   ├── mergesort/            # Implementações Merge Sort (funcional e imperativa)
│   ├── orderingsystem/       # Sistema de pedidos (imperativo e funcional)
│   ├── simpleoperations/     # Soma sequencial e paralela
│   └── tree/                 # Árvores (imperativo e funcional)
└── util/
    └── Util.java             # Funções auxiliares
Dockerfile                     # Dockerfile para containerizar a aplicação
README.md                       # Este arquivo
```

---

## Funcionalidades do Programa

O programa apresenta um **menu interativo** com as seguintes opções:

1. **Testar Bubble Sort**  
   - Ordenação de arrays aleatórios usando Bubble Sort nos paradigmas funcional e imperativo.

2. **Testar Merge Sort**  
   - Ordenação de arrays aleatórios usando Merge Sort nos paradigmas funcional e imperativo.

3. **Testar Sistema de Pedidos**  
   - Filtragem e ordenação de listas de pedidos com base em categoria e valor mínimo.

4. **Testar Árvores**  
   - Manipulação de árvores, triplicando valores e coletando números pares, comparando abordagens funcional e imperativa.

5. **Testar Soma Sequencial e Paralela**  
   - Soma de elementos de 1 a n, mostrando diferença de desempenho entre processamento sequencial e paralelo.

6. **Alterar preferências**  
   - Configurações de **impressão de arrays** (`printArray`) e **número de execuções por teste** (`auxExecutions`).

---

## Executando via Docker

### 1️⃣ Construir a imagem

Dentro da pasta raiz do projeto ex: (`C:\benchmark\`);
Execute o comando para construir a imagem.

```bash
docker build -t algorithms-app .
```

- Constrói a imagem Docker a partir do Dockerfile que está na pasta atual `.` e dê a ela o nome (`-t`) algorithms-app.

### 2️⃣ Rodar de forma interativa

```bash
docker run -it algorithms-app
```

- `-it` mantém o terminal interativo para o menu funcionar.
- O programa pedirá entrada de dados para executar os testes.

### 3️⃣ Configurar variáveis de ambiente (opcional)

Você pode definir preferências antes de rodar o container:

```bash
docker run -it -e PRINT_ARRAY=true -e EXECUTIONS=5 algorithms-app
```

- `PRINT_ARRAY=true` → imprime arrays e listas geradas durante os testes.  
- `EXECUTIONS=5` → número de execuções para cada teste, usado para cálculo de médias.

---

## Resultados do Benchmark

Para cada teste, a aplicação calcula:

- **Tempo médio** de execução (ms)  
- **Memória média** utilizada (KB)

Exemplo de saída:

```
===== RESULTADOS: Bubble Sort =====
Funcional   -> Tempo médio: 12.34 ms | Memória média: 1.23 KB
Imperativo  -> Tempo médio: 15.67 ms | Memória média: 1.45 KB
--------------------------------------------------
```

> Observação: você pode ajustar o número de execuções (`EXECUTIONS`) para obter médias mais confiáveis.

---

## Observações

- O projeto está estruturado para **facilitar a comparação de paradigmas de programação**.  
- Todos os algoritmos possuem versões funcionais e imperativas para fins de análise de desempenho.  
- O código é compatível com **Java 17** e pode ser executado tanto localmente quanto via Docker.  
- Para rodar localmente sem Docker, basta compilar todos os arquivos `.java` e executar `java Main`.

---

## Contato

Autor: **Paulo Uchoa**  
E-mail: **paulojosevieira2011@gmail.com**  
Projeto para **Trabalho de Conclusão de Curso - Imperativo versus Funcional: Dois caminhos para resolver o mesmo problema**

