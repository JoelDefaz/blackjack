package main;

import javax.swing.*;
import java.awt.*;

public class PantallaInicio extends JFrame {

    public PantallaInicio() {
        setTitle("Blackjack");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(new Color(50, 100, 150));

        JLabel titulo = new JLabel("BLACKJACK", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botonesPanel.setBackground(panelPrincipal.getBackground());

        JButton botonJugar = new JButton("Jugar");
        botonJugar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        botonJugar.setPreferredSize(new Dimension(120, 40));
        botonJugar.setBackground(new Color(100, 200, 100)); // Verde suave
        botonJugar.setForeground(Color.WHITE);
        botonJugar.setFocusPainted(false);
        botonJugar.addActionListener(e -> {
            dispose();
            new Thread(() -> {
                new Juego().jugar();
            }).start();
        });
        botonesPanel.add(botonJugar);

        JButton botonSalir = new JButton("Salir");
        botonSalir.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        botonSalir.setPreferredSize(new Dimension(120, 40));
        botonSalir.setBackground(new Color(200, 100, 100)); // Rojo suave
        botonSalir.setForeground(Color.WHITE);
        botonSalir.setFocusPainted(false);
        botonSalir.addActionListener(e -> {
            System.exit(0);
        });
        botonesPanel.add(botonSalir);

        panelPrincipal.add(botonesPanel, BorderLayout.CENTER);

        add(panelPrincipal);
        setLocationRelativeTo(null);
    }
}