package test;

import main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParticipanteTest {

    private Participante participante;

    @BeforeEach
    public void setUp() {
        participante = new Jugador("Prueba");
    }

    @Test
    public void testObtenerCarta() {
        assertEquals(0, participante.obtenerPuntuacion());
        assertEquals(0, participante.getCartas().length);

        participante.obtenerCarta();
        assertEquals(1, participante.getCartas().length);
        assertTrue(participante.obtenerPuntuacion() > 0);
    }

    @Test
    public void testObtenerPuntuacion() {
        assertEquals(0, participante.obtenerPuntuacion());

        participante.obtenerCarta();
        assertTrue(participante.obtenerPuntuacion() > 0);
    }

    @Test
    public void testEliminarCartas() {
        participante.obtenerCarta();
        assertEquals(1, participante.getCartas().length);
        assertTrue(participante.obtenerPuntuacion() > 0);

        participante.eliminarCartas();
        assertEquals(0, participante.getCartas().length);
        assertEquals(0, participante.obtenerPuntuacion());
    }

    @Test
    public void testEstaActivo() {
        assertTrue(participante.estaActivo());

        participante.obtenerCarta();
        assertTrue(participante.estaActivo());

        while (participante.obtenerPuntuacion() < 21) {
            participante.obtenerCarta();
        }
        assertFalse(participante.estaActivo());
    }
}