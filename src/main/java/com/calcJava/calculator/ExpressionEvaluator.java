package com.calcJava.calculator;

import java.util.Stack;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

public class ExpressionEvaluator {

    /*
     * Método principal que recebe a string da expressão (ex: "2 + 3 * 4")
     * e retorna o resultado numérico final avaliado.
     */
    public double avaliar(String expressao) {
        if (expressao == null || expressao.trim().isEmpty()) {
            throw new IllegalArgumentException("A expressão não pode ser vazia.");
        }

        try {
            List<String> tokensValidos = tokenizar(expressao);
            List<String> ordemPosFixa = shuntingYard(tokensValidos);
            return calcularPosFixa(ordemPosFixa);

        } catch (EmptyStackException e) {
            throw new IllegalArgumentException("Expressão mal formatada: parênteses ou operadores inválidos.");
        }
    }

    // Ler a string caractere por caractere e agrupá-los em unidades com significado lógico
    private List<String> tokenizar(String expressao) {
        List<String> tokens = new ArrayList<>();
        int i = 0;

        while (i < expressao.length()) {
            char c = expressao.charAt(i);

            // 1. Ignorar espaços em branco
            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            // 2. Se for um operador ou parênteses, adiciona direto
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^' || c == '(' || c == ')') {
                tokens.add(String.valueOf(c));
                i++;
            }

            // 3. Se for um dígito ou ponto decimal, vai "acumular" o número completo
            else if (Character.isDigit(c) || c == '.') {
                StringBuilder numero = new StringBuilder();

                // Continua lendo enquanto for dígito ou ponto
                while (i < expressao.length() && (Character.isDigit(expressao.charAt(i)) || expressao.charAt(i) == '.')) {
                    numero.append(expressao.charAt(i));
                    i++;
                }

                tokens.add(numero.toString());
            }

            // 4. Se for uma letra, acumula a palavra
            else if (Character.isLetter(c)) {
                StringBuilder palavra = new StringBuilder();

                while (i < expressao.length() && Character.isLetter(expressao.charAt(i))) {
                    palavra.append(expressao.charAt(i));
                    i++;
                }

                // Adiciona a palavra em letras minúsculas para padronizar
                tokens.add(palavra.toString().toLowerCase());
            }

            // 5. Se encontrar algo que não faz sentido (como uma letra ou caractere especial inválido)
            else {
                throw new IllegalArgumentException("Caractere inválido na expressão: " + c);
            }
        }

        return tokens;
    }

    // Auxiliar para dizermos quais são as ordens dos operadores
    private int obterPrecedencia(String operador) {
        switch (operador) {
            case "+":
            case "-":
                return 1; // Mais fracos
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            case "sin":
            case "cos":
            case "tan":
            case "sqrt":
            case "log":
                return 4; // Mais precedência
            default:
                return -1; // Para parênteses ou tokens inválidos
        }
    }

    // Aplicação da tecnica Shunting-yard
    private List<String> shuntingYard(List<String> tokens) {
        List<String> saida = new ArrayList<>();
        Stack<String> pilhaOperadores = new Stack<>();

        for (String token : tokens) {

            // Se for número ou uma constante conhecida
            if (Character.isDigit(token.charAt(0)) || token.equals("pi") || token.equals("e")) {
                saida.add(token);
            }

            // Se for parênteses aberto
            else if (token.equals("(")) {
                pilhaOperadores.push(token);
            }

            // Se for parênteses fechado
            else if (token.equals(")")) {

                while (!pilhaOperadores.isEmpty() && !pilhaOperadores.peek().equals("(")) {
                    saida.add(pilhaOperadores.pop());
                }

                if (pilhaOperadores.isEmpty()) {
                    throw new IllegalArgumentException("Expressão mal formatada: parênteses desalinhados.");
                }

                pilhaOperadores.pop(); // Remove o "(" da pilha
            }

            // Se for um operador (+, -, *, /)
            else {

                while (!pilhaOperadores.isEmpty() &&
                    obterPrecedencia(pilhaOperadores.peek()) >= obterPrecedencia(token)) {
                    saida.add(pilhaOperadores.pop());
                }

                pilhaOperadores.push(token);
            }
        }

        // Esvazia os operadores restantes da pilha para a saída
        while (!pilhaOperadores.isEmpty()) {

            String topo = pilhaOperadores.pop();

            if (topo.equals("(") || topo.equals(")")) {
                throw new IllegalArgumentException("Expressão mal formatada: parênteses desalinhados.");
            }

            saida.add(topo);
        }

        return saida;
    }

    // Calcula a expressão e vai redefinindo as ordens
    private double calcularPosFixa(List<String> tokensPosFixos) {
        Stack<Double> pilhaNumeros = new Stack<>();
        Calculator calculadoraBasica = new Calculator();

        for (String token : tokensPosFixos) {

            // Se for a constante PI
            if (token.equals("pi")) {
                pilhaNumeros.push(Math.PI);
            }

            // Se for a constante E
            else if (token.equals("e")) {
                pilhaNumeros.push(Math.E);
            }

            // Se for número, empilha
            else if (Character.isDigit(token.charAt(0)) || (token.length() > 1 && token.charAt(1) == '.')) {
                pilhaNumeros.push(Double.parseDouble(token));
            }

            // Se for o operador de potência
            else if (token.equals("^")) {

                if (pilhaNumeros.size() < 2) {
                    throw new IllegalArgumentException("Expressão mal formatada: operadores em excesso.");
                }

                double expoente = pilhaNumeros.pop();
                double base = pilhaNumeros.pop();
                pilhaNumeros.push(Math.pow(base, expoente));
            }

            // Se for função científica
            else if (token.equals("sin") || token.equals("cos") || token.equals("tan") || token.equals("sqrt") || token.equals("log")) {

                if (pilhaNumeros.isEmpty()) throw new IllegalArgumentException("Expressão mal formatada: falta argumento para a função " + token);

                double valor = pilhaNumeros.pop(); // Desempilha apenas um número
                double resultado = 0;

                switch (token) {
                    case "sin":
                        resultado = Math.sin(valor); break;

                    case "cos":
                        resultado = Math.cos(valor); break;

                    case "tan":
                        resultado = Math.tan(valor); break;

                    case "sqrt":
                        if (valor < 0) throw new ArithmeticException("Raiz quadrada de número negativo não permitida.");
                        resultado = Math.sqrt(valor); break;

                    case "log":
                        if (valor <= 0) throw new ArithmeticException("Logaritmo de número menor ou igual a zero não permitido.");
                        resultado = Math.log(valor); break;
                }
                pilhaNumeros.push(resultado); // Devolve o resultado científico
            }

            // Se for operador, desempilha dois, calcula e empilha o resultado
            else {

                if (pilhaNumeros.size() < 2) {
                    throw new IllegalArgumentException("Expressão mal formatada: operadores em excesso.");
                }

                double num2 = pilhaNumeros.pop(); // O topo da pilha é o segundo operando
                double num1 = pilhaNumeros.pop(); // O de baixo é o primeiro operando

                double resultado = calculadoraBasica.calcular(num1, num2, token);
                pilhaNumeros.push(resultado);
            }
        }

        if (pilhaNumeros.size() != 1) {
            throw new IllegalArgumentException("Expressão mal formatada: números em excesso.");
        }

        return pilhaNumeros.pop();
    }

}
