# 🧮 java-calc-core

Um motor de cálculo matemático robusto desenvolvido em Java puro (sem frameworks), focado puramente em lógica algorítmica, estruturas de dados e conceitos fundamentais de Ciência da Computação.

O objetivo deste projeto é desacoplar do desenvolvimento web/banco de dados para focar na essência da Programação Orientada a Objetos (POO), arquitetura de código limpa e validação rigorosa de expressões.

---

## 🚀 Roadmap de Desenvolvimento

O projeto será construído em etapas incrementais, evoluindo a complexidade algorítmica a cada fase:

- [ ] **Fase 1: Motor Básico (Console)**
  - Operações aritméticas elementares (`+`, `-`, `*`, `/`).
  - Interface via CLI (Linha de Comando).
  - Validações básicas (Prevenção de divisão por zero, entradas inválidas).
- [ ] **Fase 2: Interpretador de Expressões (Parser)**
  - Suporte a expressões completas com parênteses (ex: `(2 + 3) * 5`).
  - Implementação do algoritmo **Shunting-yard** de Edsger Dijkstra.
  - Uso de **Pilhas (Stacks)** para Notação Polonesa Inversa (RPN).
- [ ] **Fase 3: Expansão Científica**
  - Funções trigonométricas (`sin`, `cos`, `tan`).
  - Logaritmos, potenciação e radiciação.
  - Suporte a constantes matemáticas ($\pi$ e $e$).

---

## 🛠️ Tecnologias e Conceitos Utilizados

- **Linguagem:** Java (Java SE)
- **Testes:** JUnit 5 (Foco total em Test-Driven Development - TDD)
- **Padrões de Projeto:** Strategy Pattern (para isolamento de operações)
- **Gerenciador de Dependências:** Maven (apenas para JUnit)

---

## 🧪 Como Executar os Testes

Como o projeto foca em lógica pura, a cobertura de testes é o coração da validação do motor.

```bash
# Executar testes via Maven
mvn test
```

---

## 📄 Licença
​Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
