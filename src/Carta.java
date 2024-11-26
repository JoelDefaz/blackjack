public class Carta {
    private Palo palo;
    private Rango rango;

    public Carta(Palo palo, Rango rango) {
        this.palo = palo;
        this.rango = rango;
    }

    public Palo obtenerPalo() {
        return palo;
    }

    public Rango obtenerRango() {
        return rango;
    }

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

    @Override
    public String toString() {
        return String.format("%s de %s", rango, palo);
    }
}
