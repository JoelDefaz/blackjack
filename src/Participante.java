import java.util.ArrayList;
import java.util.List;

public abstract class Participante {
    protected List<Carta> cartas;
    private Baraja baraja;
    private int puntuacion;
    protected boolean activo;

    // Constructor para inicializar el participante con una baraja, puntuación y estado activo
    public Participante() {
        cartas = new ArrayList<>();
        baraja = Baraja.obtenerInstancia();
        puntuacion = 0;
        activo = true;
    }

    // Método para obtener una carta de la baraja y actualizar la puntuación
    public void obtenerCarta() {
        cartas.add(baraja.robarCarta());
        calcularPuntuacion();
        if (puntuacion >= 21) {
            activo = false;
        }
    }

    // Método para eliminar todas las cartas del participante y reiniciar el estado
    public void eliminarCartas() {
        cartas.clear();
        puntuacion = 0;
        activo = true;
    }

    // Método para obtener todas las cartas del participante
    public Carta[] getCartas() {
        return cartas.toArray(new Carta[0]);
    }

    // Método privado para calcular la puntuación del participante según sus cartas
    private void calcularPuntuacion() {
        int numeroAses = 0;
        int puntuacion = 0;
        for (Carta carta : cartas) {
            puntuacion += carta.obtenerRango().getValor();
            if (carta.obtenerRango().equals(Rango.AS)) {
                numeroAses++;
            }
        }
        for (int i = 0; i < numeroAses; i++) {
            puntuacion += 10;
            if (puntuacion > 21) {
                puntuacion -= 10;
                break;
            }
        }
        this.puntuacion = puntuacion;
    }

    // Método para obtener la puntuación actual del participante
    public int obtenerPuntuacion() {
        return puntuacion;
    }

    // Método para verificar si el participante está activo (es decir, no se ha plantado ni ha superado 21)
    public boolean estaActivo() {
        return activo;
    }
}
