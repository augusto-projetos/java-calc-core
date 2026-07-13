package com.calcJava.calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpressionEvaluator evaluator = new ExpressionEvaluator();

        System.out.println("=== Interpretador de Expressões ===");
        System.out.println("Digite uma expressão matemática completa (ex: (2 + 3) * 4)");
        System.out.println("Digite 'sair' a qualquer momento para fechar.\n");

        while (true) {
            try {
                System.out.print("Digite a expressão: ");
                // nextLine() para capturar a linha inteira com espaços
                String entrada = scanner.nextLine();

                if (entrada.equalsIgnoreCase("sair")) {
                    break;
                }

                // Executa o motor lógico
                double resultado = evaluator.avaliar(entrada);
                System.out.printf("👉 Resultado: %.4f\n\n", resultado);

            } catch (IllegalArgumentException e) {
                System.out.println("❌ Erro de Validação: " + e.getMessage() + "\n");
            } catch (Exception e) {
                System.out.println("❌ Erro Inesperado: " + e.getMessage() + "\n");
            }
        }

        System.out.println("Programa encerrado. Até mais!");
        scanner.close();
    }
}
