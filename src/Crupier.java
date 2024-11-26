import java.util.List;

public class Crupier extends Participante {
    public Crupier() {
        super();
    }

    @Override
    public Carta[] getCartas() {
        if (activo) {
            List<Carta> cartasARevelar = cartas.subList(0, 1);
            return cartasARevelar.toArray(new Carta[0]);
        }
        return cartas.toArray(new Carta[0]);
    }

    public void completarMano() {
        while (obtenerPuntuacion() <= 16) {
            obtenerCarta();
        }
        this.activo = false;
    }

    @Override
    public String toString() {
        return "Crupier";
    }
}
