package main;

public class Carta {
    private Palo palo;
    private Rango rango;

    // Constructor para inicializar la carta con su palo y rango
    public Carta(Palo palo, Rango rango) {
        this.palo = palo;
        this.rango = rango;
    }

    // Método para obtener el palo de la carta
    public Palo obtenerPalo() {
        return palo;
    }

    // Método para obtener el rango de la carta
    public Rango obtenerRango() {
        return rango;
    }

    // Método para obtener la carta como un carácter Unicode
    public String obtenerCarta() {
        int unicodeBase = 0x1F0A0;
        int unicodeOffsetPalo = palo.getValor() * 16;
        int unicodeOffsetRango = -1;

        for (int i = 0; i < Rango.values().length; i++) {
            if (this.rango.equals(Rango.values()[i])) {
                unicodeOffsetRango = i + 1;
                break;
            }
        }

        if (unicodeOffsetRango > 11) {
            unicodeOffsetRango += 1;
        }

        int unicodeCodePoint = unicodeBase + unicodeOffsetPalo + unicodeOffsetRango;
        String cartaUnicode = new String(Character.toChars(unicodeCodePoint));
        return cartaUnicode;
    }

    // Método para obtener una representación en cadena de la carta
    @Override
    public String toString() {
        return String.format("%s de %s", rango, palo);
    }
}
