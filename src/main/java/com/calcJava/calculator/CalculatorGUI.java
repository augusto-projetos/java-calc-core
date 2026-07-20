package com.calcJava.calculator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {

    private JTextField display;
    private ExpressionEvaluator evaluator;

    // Paleta de Cores (Dark Mode Moderno)
    private final Color COR_FUNDO = new Color(32, 32, 32);
    private final Color COR_DISPLAY = new Color(24, 24, 24);
    private final Color COR_TEXTO_DISPLAY = new Color(255, 255, 255);
    private final Color COR_BTN_NUMERO = new Color(50, 50, 50);
    private final Color COR_BTN_OPERADOR = new Color(60, 60, 60);
    private final Color COR_BTN_CIENTIFICO = new Color(40, 40, 40);
    private final Color COR_BTN_IGUAL = new Color(0, 120, 215); // Azul moderno
    private final Color COR_BTN_LIMP = new Color(180, 40, 40);   // Vermelho discreto

    public CalculatorGUI() {
        evaluator = new ExpressionEvaluator();

        // Configuração básica da Janela (JFrame)
        setTitle("Calculadora Científica");
        setSize(420, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel Principal com padding
        JPanel painelPrincipal = new JPanel(new BorderLayout(15, 15));
        painelPrincipal.setBackground(COR_FUNDO);
        painelPrincipal.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(painelPrincipal);

        // 1. Display Estilizado
        display = new JTextField();
        display.setFont(new Font("Consolas", Font.BOLD, 28));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(true); // Permite digitar pelo teclado
        display.setBackground(COR_DISPLAY);
        display.setForeground(COR_TEXTO_DISPLAY);
        display.setCaretColor(Color.WHITE);
        display.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(50, 50, 50), 1),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        add(display, BorderLayout.NORTH);

        // 2. Grid de Botões (7x4)
        JPanel painelBotoes = new JPanel(new GridLayout(7, 4, 8, 8));
        painelBotoes.setBackground(COR_FUNDO);

        // Mapeamento visual para o usuário
        String[] botoes = {
            "C", "(", ")", "DEL",
            "√(", "3√(", "^", "/",
            "sin(", "cos(", "tan(", "*",
            "7", "8", "9", "-",
            "4", "5", "6", "+",
            "1", "2", "3", "π",
            "0", ".", "e", "="
        };

        // Adiciona os botões ao painel
        for (String texto : botoes) {
            JButton btn = new JButton(texto);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);

            // Estilização por tipo de botão
            if (texto.equals("=")) {

                btn.setBackground(COR_BTN_IGUAL);
                btn.setForeground(Color.WHITE);
            }

            else if (texto.equals("C")) {

                btn.setBackground(COR_BTN_LIMP);
                btn.setForeground(Color.WHITE);
            }

            else if ("0123456789.".contains(texto)) {

                btn.setBackground(COR_BTN_NUMERO);
                btn.setForeground(Color.WHITE);
            }

            else if ("+-*/^".contains(texto)) {

                btn.setBackground(COR_BTN_OPERADOR);
                btn.setForeground(new Color(255, 180, 0)); // Destaque em amarelo/laranja
            }

            else {

                btn.setBackground(COR_BTN_CIENTIFICO);
                btn.setForeground(new Color(130, 200, 255)); // Azul claro pras funções
            }

            btn.addActionListener(this);
            painelBotoes.add(btn);
        }

        add(painelBotoes, BorderLayout.CENTER);

        // Carrega a imagem e define como ícone da janela
        try {
            java.net.URL url = getClass().getResource("/ico/calculator.png");
            if (url != null) {
                // Lê o PNG e converte em objeto de imagem nativo do Java
                java.awt.Image icone = javax.imageio.ImageIO.read(url);
                this.setIconImage(icone);
            } else {
                System.out.println("Erro: O arquivo /ico/calculator.png não foi encontrado na pasta resources.");
            }
        } catch (Exception ex) {
            System.out.println("Não foi possível carregar o ícone da janela: " + ex.getMessage());
        }
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
                // Traduz os símbolos da tela para o que o ExpressionEvaluator entende
                String expressaoTratada = display.getText()
                        .replace("√(", "sqrt(")
                        .replace("3√(", "cbrt(")
                        .replace("π", "pi");

                double resultado = evaluator.avaliar(expressaoTratada);

                // Formata pra não mostrar .0 em números inteiros
                if (resultado == (long) resultado) {

                    display.setText(String.format("%d", (long) resultado));
                }

                else {

                    display.setText(String.valueOf(resultado));
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this, "Expressão inválida!", "Erro de Cálculo", JOptionPane.ERROR_MESSAGE);
            }
        }

        else {
            // Insere o caractere do botão no display
            display.setText(display.getText() + comando);
        }
    }
}
