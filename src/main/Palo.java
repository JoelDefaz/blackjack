package main;

public enum Palo {
    PICAS(0),
    CORAZONES(1),
    DIAMANTES(2),
    TREBOLES(3);

    private final int valor;

    Palo(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
