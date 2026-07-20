package com.calcJava.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {

    private JTextField display;
    private ExpressionEvaluator evaluator;

    public CalculatorGUI() {
        evaluator = new ExpressionEvaluator();

        // Configuração básica da Janela (JFrame)
        setTitle("Calculadora Científica");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela
        setLayout(new BorderLayout(10, 10));

        // 1. Cria o Display (Campo de texto)
        display = new JTextField();
        display.setFont(new Font("Consolas", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(true); // Permite digitar pelo teclado
        display.setBackground(new Color(240, 240, 240));
        add(display, BorderLayout.NORTH);

        // 2. Painel dos Botões (6x4)
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(6, 4, 5, 5));

        // Rótulos dos botões que vão aparecer na tela
        String[] botoes = {
            "C", "(", ")", "/",
            "sqrt(", "cbrt(", "^", "*",
            "sin(", "cos(", "tan(", "-",
            "7", "8", "9", "+",
            "4", "5", "6", "pi",
            "1", "2", "3", "=",
            "0", ".", "e", "DEL"
        };

        // Adiciona os botões ao painel
        for (String texto : botoes) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("SansSerif", Font.BOLD, 16));
            btn.addActionListener(this);
            painelBotoes.add(btn);
        }

        add(painelBotoes, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("C")) {
            display.setText("");
        }

        else if (comando.equals("DEL")) {
            String textoAtual = display.getText();

            if (!textoAtual.isEmpty()) {
                display.setText(textoAtual.substring(0, textoAtual.length() - 1));
            }
        }

        else if (comando.equals("=")) {

            try {
                double resultado = evaluator.avaliar(display.getText());
                display.setText(String.valueOf(resultado));

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro de Cálculo", JOptionPane.ERROR_MESSAGE);
            }
        }

        else {
            // Adiciona o texto do botão clicado ao display
            display.setText(display.getText() + comando);
        }
    }
}
