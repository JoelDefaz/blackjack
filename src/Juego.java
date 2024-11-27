import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class Juego extends JFrame {
    private List<Jugador> jugadores;
    private Crupier crupier;
    private Map<Jugador, List<JButton>> botonesJugadores;

    public Juego() {
        setTitle("Blackjack");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        jugadores = new ArrayList<>();
        crupier = new Crupier();
        botonesJugadores = new HashMap<>();

        jugadores.add(new Jugador("Jugador 1"));
        jugadores.add(new Jugador("Jugador 2"));

        repartirCartas();
    }

    public void jugar() {
        configurarElementosCrupier();
        configurarElementosJugadores();
        while (true) {
            for (Jugador jugador : jugadores) {
                reactivarBotones(jugador);
                while (jugador.estaActivo()) {
                    try {
                        synchronized (jugador) {
                            jugador.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                desactivarBotones(jugador);
            }
            crupier.completarMano();
            repaint();
            determinarGanador();
            reiniciarJuego();
        }
    }

    private void reiniciarJuego() {
        for (Jugador jugador : jugadores) {
            jugador.eliminarCartas();
        }
        crupier.eliminarCartas();
        Baraja.obtenerInstancia().reiniciar();
        repartirCartas();
        repaint();
    }

    private void determinarGanador() {
        Participante ganador = null;
        int puntajeMaximo = 0;
        boolean empate = false;

        List<Participante> participantes = new ArrayList<>(jugadores);
        participantes.add(crupier);

        for (Participante participante : participantes) {
            int puntuacion = participante.obtenerPuntuacion();
            if (puntuacion > 21) {
                continue;
            }
            if (puntuacion > puntajeMaximo) {
                ganador = participante;
                puntajeMaximo = puntuacion;
            } else if (puntuacion == puntajeMaximo) {
                empate = true;
            }
        }

        if (ganador == null) {
            JOptionPane.showMessageDialog(this, "Nadie ganó");
        } else if (empate) {
            JOptionPane.showMessageDialog(this, "Empate entre los participantes con " + puntajeMaximo + " puntos.");
        } else {
            JOptionPane.showMessageDialog(this, ganador + " es el ganador con " + puntajeMaximo + " puntos.");
        }
    }

    private void configurarElementosCrupier() {
        JPanel panelCrupier = new CartaPanel(crupier, "Cartas del Crupier");
        panelCrupier.setPreferredSize(new Dimension(800, 150));
        JPanel crupierPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        crupierPanel.add(panelCrupier);
        add(crupierPanel, BorderLayout.NORTH);
    }

    private void configurarElementosJugadores() {
        JPanel panelJugadores = new JPanel();
        panelJugadores.setLayout(new BoxLayout(panelJugadores, BoxLayout.Y_AXIS));

        for (Jugador jugador : jugadores) {
            JPanel panelJugadorConBotones = new JPanel(new BorderLayout());

            CartaPanel panelJugador = new CartaPanel(jugador, jugador.toString());
            panelJugador.setPreferredSize(new Dimension(400, 200));
            panelJugadorConBotones.add(panelJugador, BorderLayout.CENTER);

            JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            botonesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Botón de "Pedir carta"
            JButton pedirCartaButton = new JButton("Pedir carta");
            pedirCartaButton.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            pedirCartaButton.setPreferredSize(new Dimension(90, 25));
            pedirCartaButton.setBackground(new Color(200, 200, 200));
            pedirCartaButton.setForeground(Color.DARK_GRAY);
            pedirCartaButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            pedirCartaButton.addActionListener(e -> {
                jugador.obtenerCarta();
                repaint();
                if (jugador.estaActivo()) {
                    return;
                } else if (jugador.obtenerPuntuacion() == 21) {
                    JOptionPane.showMessageDialog(this, "Blackjack");
                } else {
                    JOptionPane.showMessageDialog(this, jugador + " ha perdido");
                }
                synchronized (jugador) {
                    jugador.notify();
                }
            });
            pedirCartaButton.setEnabled(false);

            // Botón de "Plantarse"
            JButton plantarseButton = new JButton("Plantarse");
            plantarseButton.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            plantarseButton.setPreferredSize(new Dimension(90, 25));
            plantarseButton.setBackground(new Color(230, 230, 230));
            plantarseButton.setForeground(Color.DARK_GRAY);
            plantarseButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            plantarseButton.addActionListener(e -> {
                jugador.plantarse();
                JOptionPane.showMessageDialog(this, jugador + " se ha plantado.");
                synchronized (jugador) {
                    jugador.notify();
                }
            });
            plantarseButton.setEnabled(false);

            botonesPanel.add(pedirCartaButton);
            botonesPanel.add(plantarseButton);

            botonesJugadores.put(jugador, Arrays.asList(pedirCartaButton, plantarseButton));

            panelJugadorConBotones.add(botonesPanel, BorderLayout.EAST);
            panelJugadores.add(panelJugadorConBotones);
        }

        JScrollPane scrollPanel = new JScrollPane(panelJugadores);
        add(scrollPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void repartirCartas() {
        for (int i = 0; i < 2; i++) {
            crupier.obtenerCarta();
            for (Jugador jugador : jugadores) {
                jugador.obtenerCarta();
            }
        }
    }

    // Método para desactivar ambos botones (Pedir carta y Plantarse) para un jugador
    private void desactivarBotones(Jugador jugador) {
        List<JButton> botones = botonesJugadores.get(jugador);
        for (JButton boton : botones) {
            boton.setEnabled(false);
        }
    }

    // Método para reactivar todos los botones
    public void reactivarBotones(Jugador jugador) {
        List<JButton> botones = botonesJugadores.get(jugador);
        for (JButton boton : botones) {
            boton.setEnabled(true);
        }
    }
}
