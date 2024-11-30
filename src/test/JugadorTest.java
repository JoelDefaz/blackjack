package test;

import main.Jugador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void testJugadorSeCreaConNombre() {
        String nombreEsperado = "Jugador 1";
        Jugador jugador = new Jugador(nombreEsperado);
        assertEquals(nombreEsperado, jugador.toString(), "El nombre del jugador no coincide");
    }

    @Test
    public void testJugadorInicialmenteActivo() {
        Jugador jugador = new Jugador("Jugador 1");
        boolean estaActivo = jugador.estaActivo();
        assertTrue(estaActivo, "El jugador debería estar activo al inicio");
    }

    @Test
    public void testPlantarseDesactivaJugador() {
        Jugador jugador = new Jugador("Jugador 1");
        jugador.plantarse();
        assertFalse(jugador.estaActivo(), "El jugador debería estar inactivo después de plantarse");
    }

    @Test
    public void testToStringDevuelveNombre() {
        String nombreEsperado = "Jugador 2";
        Jugador jugador = new Jugador(nombreEsperado);
        String nombreActual = jugador.toString();
        assertEquals(nombreEsperado, nombreActual, "El método toString no devuelve el nombre correcto del jugador");
    }
}
