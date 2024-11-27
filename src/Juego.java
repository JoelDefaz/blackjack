import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.*;

public class Juego extends JFrame {
    private List<Jugador> jugadores;
    private Crupier crupier;
    private Map<Jugador, List<JButton>> botonesJugadores;  // Mapa para almacenar los botones de los jugadores

    public Juego() {
        setTitle("Juego de Cartas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        jugadores = new ArrayList<>();
        crupier = new Crupier();
        botonesJugadores = new HashMap<>(); // Inicializar el mapa de botones

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
        JPanel crupierPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centrado horizontalmente
        crupierPanel.add(panelCrupier);
        add(crupierPanel, BorderLayout.NORTH);
    }

    private void configurarElementosJugadores() {
        JPanel panelJugadores = new JPanel();
        panelJugadores.setLayout(new BoxLayout(panelJugadores, BoxLayout.Y_AXIS)); // Disposición vertical

        for (Jugador jugador : jugadores) {
            JPanel panelJugadorConBotones = new JPanel(new BorderLayout());

            // Crear panel para las cartas del jugador
            CartaPanel panelJugador = new CartaPanel(jugador, jugador.toString());
            panelJugador.setPreferredSize(new Dimension(400, 200));
            panelJugadorConBotones.add(panelJugador, BorderLayout.CENTER);

            // Crear panel para los botones estilizados (usar FlowLayout)
            JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centrado de los botones
            botonesPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Asegurar que los botones se centren

            // Botón de "Pedir carta"
            JButton pedirCartaButton = new JButton("Pedir carta");
            pedirCartaButton.setFont(new Font("Segoe UI", Font.PLAIN, 10)); // Fuente más pequeña
            pedirCartaButton.setPreferredSize(new Dimension(90, 25)); // Tamaño más pequeño
            pedirCartaButton.setBackground(new Color(200, 200, 200));  // Color de fondo suave (gris claro)
            pedirCartaButton.setForeground(Color.DARK_GRAY);  // Color de texto gris oscuro
            pedirCartaButton.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Borde gris suave
            pedirCartaButton.addActionListener(e -> {
                // Llamada al método pedirCarta
                jugador.obtenerCarta();
                repaint();
                if (!jugador.estaActivo()) {
                    JOptionPane.showMessageDialog(this, jugador + " ha perdido");
                    synchronized (jugador) {
                        jugador.notify();
                    }
                }
            });
            pedirCartaButton.setEnabled(false);

            // Botón de "Plantarse"
            JButton plantarseButton = new JButton("Plantarse");
            plantarseButton.setFont(new Font("Segoe UI", Font.PLAIN, 10)); // Fuente más pequeña
            plantarseButton.setPreferredSize(new Dimension(90, 25)); // Tamaño más pequeño
            plantarseButton.setBackground(new Color(230, 230, 230));  // Color de fondo suave (gris claro)
            plantarseButton.setForeground(Color.DARK_GRAY);  // Color de texto gris oscuro
            plantarseButton.setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Borde gris suave
            plantarseButton.addActionListener(e -> {
                // Llamada al método plantarse
                jugador.plantarse();
                JOptionPane.showMessageDialog(this, jugador + " se ha plantado.");
                synchronized (jugador) {
                    jugador.notify();
                }
            });
            plantarseButton.setEnabled(false);

            // Añadir los botones al panel de botones
            botonesPanel.add(pedirCartaButton);
            botonesPanel.add(plantarseButton);

            // Añadir los botones al mapa de botones del jugador
            botonesJugadores.put(jugador, Arrays.asList(pedirCartaButton, plantarseButton));

            panelJugadorConBotones.add(botonesPanel, BorderLayout.EAST);
            panelJugadores.add(panelJugadorConBotones);
        }

        // Añadir panel de jugadores al centro
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
