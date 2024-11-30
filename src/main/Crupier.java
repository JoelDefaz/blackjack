package main;

import java.util.List;

public class Crupier extends Participante {

    public Crupier() {
        super();
    }

    // Sobrescribe el método getCartas para mostrar solo la primera carta si el crupier está activo
    @Override
    public Carta[] getCartas() {
        if (activo) {
            List<Carta> cartasARevelar = cartas.subList(0, 1);
            return cartasARevelar.toArray(new Carta[0]);
        }
        return cartas.toArray(new Carta[0]);
    }

    // Método que permite al crupier pedir cartas hasta tener más de 16 puntos
    public void completarMano() {
        while (obtenerPuntuacion() <= 16) {
            obtenerCarta();
        }
        this.activo = false;
    }

    // Sobrescribe el método toString para retornar el nombre "main.Crupier"
    @Override
    public String toString() {
        return "Crupier";
    }
}
