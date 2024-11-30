package test;

import main.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CartaTest {

    @Test
    public void testObtenerPalo() {
        Carta carta = new Carta(Palo.CORAZONES, Rango.DOS);
        assertEquals(Palo.CORAZONES, carta.obtenerPalo());
    }

    @Test
    public void testObtenerRango() {
        Carta carta = new Carta(Palo.CORAZONES, Rango.DOS);
        assertEquals(Rango.DOS, carta.obtenerRango());
    }

    @Test
    public void testObtenerCarta() {
        Carta carta = Baraja.obtenerInstancia().robarCarta();
        assertNotNull(carta);
    }

    @Test
    public void testToString() {
        Carta carta = new Carta(Palo.CORAZONES, Rango.DOS);
        assertEquals("DOS de CORAZONES", carta.toString());
    }
}
