package test;

import main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CrupierTest {
    private Crupier crupier;

    @BeforeEach
    void setUp() {
        crupier = new Crupier();
    }

    @Test
    void testCompletarMano() {
        crupier.obtenerCarta();

        assertTrue(crupier.obtenerPuntuacion() <= 16, "La puntuación inicial debería ser menor o igual a 16");

        crupier.completarMano();

        assertTrue(crupier.obtenerPuntuacion() > 16, "La puntuación debería ser mayor a 16 después de completar la mano");
        assertFalse(crupier.estaActivo(), "El crupier debería estar inactivo después de completar la mano");
    }

    @Test
    void testToString() {
        assertEquals("Crupier", crupier.toString(), "El método toString debería retornar 'Crupier'");
    }

    @Test
    void testObtenerCartas() {
        crupier.obtenerCarta(); // El crupier toma una carta
        assertEquals(1, crupier.getCartas().length, "El crupier debe tener solo una carta visible inicialmente.");
    }
}
