package com.calcJava.calculator;

public class Calculator {

    public double calcular(double num1, double num2, String operacao) {

        switch (operacao) {
            case "+":
                return num1 + num2;

            case "-":
                return num1 - num2;

            case "*":
                return num1 * num2;

            case "/":
                if (num2 == 0) {
                    throw new ArithmeticException("Não é possível dividir por zero.");
                }
                return num1 / num2;

            default:
                throw new IllegalArgumentException("Operação inválida: " + operacao);
        }
    }
}
