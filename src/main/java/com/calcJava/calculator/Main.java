package com.calcJava.calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Calculator calc = new Calculator();

        System.out.println("=== Motor Matemático Java (Fase 1) ===");
        System.out.println("Digite 'sair' a qualquer momento para fechar.\n");

        while (true) {
            try {
                System.out.print("Digite o primeiro número: ");
                String entrada1 = scanner.next();
                
                if (entrada1.equalsIgnoreCase("sair")) break;

                double num1 = Double.parseDouble(entrada1);

                System.out.print("Digite a operação (+, -, *, /): ");
                String operacao = scanner.next();

                if (operacao.equalsIgnoreCase("sair")) break;

                System.out.print("Digite o segundo número: ");
                String entrada2 = scanner.next();

                if (entrada2.equalsIgnoreCase("sair")) break;

                double num2 = Double.parseDouble(entrada2);

                // Executa a lógica usando o motor
                double resultado = calc.calcular(num1, num2, operacao);
                System.out.printf("👉 Resultado: %.2f\n\n", resultado);

            } catch (NumberFormatException e) {
                System.out.println("❌ Erro: Digite um número válido!\n");
            } catch (Exception e) {
                System.out.println("❌ Erro: " + e.getMessage() + "\n");
            }
        }

        System.out.println("Programa encerrado. Até mais!");
        scanner.close();
    }
}
