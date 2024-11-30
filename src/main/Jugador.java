package main;

public class Jugador extends Participante {
    private String nombre;

    // Constructor que inicializa el nombre del jugador y llama al constructor de la clase base (main.Participante)
    public Jugador(String nombre) {
        super();
        this.nombre = nombre;
    }

    // Método para que el jugador se plante, lo que lo hace inactivo
    public void plantarse() {
        this.activo = false;
    }

    // Sobrescribe el método toString para devolver el nombre del jugador
    @Override
    public String toString() {
        return this.nombre;
    }
}
