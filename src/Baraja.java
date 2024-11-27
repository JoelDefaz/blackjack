import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {
    private List<Carta> cartas;
    private static Baraja instancia;

    // Constructor privado para inicializar la baraja
    private Baraja() {
        cartas = new ArrayList<>();
        reiniciar();
        mezclar();
    }

    // Método para obtener la instancia única de la baraja (patrón Singleton)
    public static Baraja obtenerInstancia() {
        if (instancia == null) {
            instancia = new Baraja();
        }
        return instancia;
    }

    // Método para mezclar las cartas en la baraja
    private void mezclar() {
        Collections.shuffle(cartas);
    }

    // Método para reiniciar la baraja con todas las cartas
    public void reiniciar() {
        cartas.clear();
        for (Palo palo : Palo.values()) {
            for (Rango rango : Rango.values()) {
                cartas.add(new Carta(palo, rango));
            }
        }
        mezclar();
    }

    // Método para robar una carta de la baraja
    public Carta robarCarta() {
        if (cartas.isEmpty()) {
            return null;
        }
        return cartas.remove(cartas.size() - 1);
    }
}
