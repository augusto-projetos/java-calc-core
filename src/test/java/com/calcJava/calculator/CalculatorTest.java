package com.calcJava.calculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @ParameterizedTest
    @CsvSource({
        "5,   5,  '+',  10.0",
        "10,  4,  '-',  6.0",
        "3,   4,  '*',  12.0",
        "10,  2,  '/',  5.0",
        "2.5, 2,  '+',  4.5"
    })
    public void deveCalcularOperacoesBasicasCorretamente(double num1, double num2, String operacao, double resultadoEsperado) {
        Calculator calc = new Calculator();

        double resultadoObtido = calc.calcular(num1, num2, operacao);

        assertEquals(resultadoEsperado, resultadoObtido,
            () -> String.format("Erro ao calcular: %s %s %s", num1, operacao, num2));
    }
}
