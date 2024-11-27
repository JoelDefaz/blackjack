import javax.swing.*;
import java.awt.*;

class CartaPanel extends JPanel {
    private Participante participante;
    private String titulo;

    public CartaPanel(Participante participante, String titulo) {
        this.participante = participante;
        this.titulo = titulo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Habilitar renderizado antialias para mayor nitidez
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Font titleFont = new Font("Segoe UI", Font.PLAIN, 20);
        g2d.setFont(titleFont);
        g2d.drawString(titulo, 10, 30);

        Font cardFont = new Font("Segoe UI Symbol", Font.PLAIN, 80);
        g2d.setFont(cardFont);

        // Dibujar las cartas
        int x = 20;
        int y = 120;
        for (Carta carta : participante.getCartas()) {
            g2d.drawString(carta.obtenerCarta(), x, y);
            x += 100;
        }
    }
}
