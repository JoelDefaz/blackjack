package test;


import main.Palo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaloTest {

    @Test
    public void testGetValor() {
        assertEquals(0, Palo.PICAS.getValor());
        assertEquals(1, Palo.CORAZONES.getValor());
        assertEquals(2, Palo.DIAMANTES.getValor());
        assertEquals(3, Palo.TREBOLES.getValor());
    }

    @Test
    public void testEnumSize() {
        assertEquals(4, Palo.values().length);
    }

    @Test
    public void testEnumName() {
        assertEquals("PICAS", Palo.PICAS.name());
        assertEquals("CORAZONES", Palo.CORAZONES.name());
        assertEquals("DIAMANTES", Palo.DIAMANTES.name());
        assertEquals("TREBOLES", Palo.TREBOLES.name());
    }

    @Test
    public void testEnumByValor() {
        assertEquals(Palo.valueOf("PICAS"), Palo.PICAS);
        assertEquals(Palo.valueOf("CORAZONES"), Palo.CORAZONES);
        assertEquals(Palo.valueOf("DIAMANTES"), Palo.DIAMANTES);
        assertEquals(Palo.valueOf("TREBOLES"), Palo.TREBOLES);
    }
}
