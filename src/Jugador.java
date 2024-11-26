public class Jugador extends Participante {
    private String nombre;

    public Jugador(String nombre) {
        super();
        this.nombre = nombre;
    }

    public void plantarse() {
        this.activo = false;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
