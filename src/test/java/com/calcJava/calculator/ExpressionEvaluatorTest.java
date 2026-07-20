package com.calcJava.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpressionEvaluatorTest {

    // Classe cérebro da Fase 2
    private ExpressionEvaluator evaluator;

    // Limpeza para cada teste que for fazer
    @BeforeEach
    public void setUp() {
        this.evaluator = new ExpressionEvaluator();
    }

    @ParameterizedTest
    @CsvSource({
        "'2 + 3', 5.0",
        "'10 - 4 - 2', 4.0",
        "'2 + 3 * 4', 14.0",
        "'(2 + 3) * 4', 20.0",
        "'10 / (2 + 3)', 2.0",
        "'2.5 * 2 + 4 / 2', 7.0",
        "'2 ^ 3', 8.0",                // 2 ao cubo = 8
        "'2 * pi', 6.283185307179586", // 2 * 3.1415...
        "'e ^ 1', 2.718281828459045",  // Constante e elevada a 1
        "'sin(0)', 0.0",
        "'cos(0)', 1.0",
        "'sqrt(9)', 3.0",
        "'sin(pi / 2)', 1.0", // Seno de 90 graus em radiano
        "'3 * sqrt(4) + 1', 7.0"
    })
    public void deveAvaliarExpressõesMatemáticasComplexas(String expressao, double resultadoEsperado) {
        double resultadoObtido = evaluator.avaliar(expressao);

        assertEquals(resultadoEsperado, resultadoObtido, 0.0001,
                                                        /*
                                                         Esse terceiro parâmetro é o delta (a tolerância de erro). Estamos dizendo: "Se a resposta do Java chegar incrivelmente perto do esperado, com uma diferença menor que 0.0001, considere correto".
                                                        */
            () -> String.format("Falha ao avaliar a expressão: %s", expressao));
    }

    @Test
    public void deveLancaráExcecaoParaExpressaoMalFormatada() {
        assertThrows(IllegalArgumentException.class, () -> {
            evaluator.avaliar("2 + * 3");
        }, "Uma expressão com operadores consecutivos inválidos deve lançar exceção.");
    }
}
