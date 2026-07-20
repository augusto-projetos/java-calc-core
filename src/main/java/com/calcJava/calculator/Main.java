package com.calcJava.calculator;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculatorGUI gui = new CalculatorGUI();
            gui.setVisible(true);
        });
    }
}

/*
Comando para gerar executável -> .\mvnw clean package

Execute o comando -> & "C:\Program Files\Java\jdk-17\bin\jpackage.exe" --type app-image --name Calculadora --input target --main-jar java-calc-core-1.0-SNAPSHOT.jar --main-class com.calcJava.calculator.Main --dest dist --icon src\main\resources\ico\calculator.ico

Empacote no zip a pasta dist/Calculadora para compartilhar o projeto
*/
