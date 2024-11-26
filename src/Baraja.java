import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baraja {
    private List<Carta> cartas;
    private static Baraja instancia;

    private Baraja() {
        cartas = new ArrayList<>();
        reiniciar();
        mezclar();
    }

    public static Baraja obtenerInstancia() {
        if (instancia == null) {
            instancia = new Baraja();
        }
        return instancia;
    }

    private void mezclar() {
        Collections.shuffle(cartas);
    }

    public void reiniciar() {
        cartas.clear();
        for (Palo palo : Palo.values()) {
            for (Rango rango : Rango.values()) {
                cartas.add(new Carta(palo, rango));
            }
        }
        mezclar();
    }

    public Carta robarCarta() {
        if (cartas.isEmpty()) {
            return null;
        }
        return cartas.remove(cartas.size() - 1);
    }
}
