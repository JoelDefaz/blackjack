package test;

import main.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RangoTest {

    @Test
    public void testGetValor() {
        assertEquals(1, Rango.AS.getValor());
        assertEquals(2, Rango.DOS.getValor());
        assertEquals(7, Rango.SIETE.getValor());
        assertEquals(10, Rango.JOTA.getValor());
        assertEquals(10, Rango.REINA.getValor());
        assertEquals(10, Rango.REY.getValor());
    }
}
