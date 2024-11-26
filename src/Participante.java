import java.util.ArrayList;
import java.util.List;

public abstract class Participante {
    protected List<Carta> cartas;
    private Baraja baraja;
    private int puntuacion;
    protected boolean activo;

    public Participante() {
        cartas = new ArrayList<>();
        baraja = Baraja.obtenerInstancia();
        puntuacion = 0;
        activo = true;
    }

    public void obtenerCarta() {
        cartas.add(baraja.robarCarta());
        calcularPuntuacion();
        if (puntuacion >= 21) {
            activo = false;
        }
    }

    public void eliminarCartas() {
        cartas.clear();
        puntuacion = 0;
        activo = true;
    }

    public Carta[] getCartas() {
        return cartas.toArray(new Carta[0]);
    }

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

    public int obtenerPuntuacion() {
        return puntuacion;
    }

    public boolean estaActivo() {
        return activo;
    }
}
