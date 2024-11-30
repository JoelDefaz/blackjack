package test;

import main.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BarajaTest {

    private Baraja baraja;

    @BeforeEach
    public void setUp() {
        baraja = Baraja.obtenerInstancia();
        baraja.reiniciar();
    }

    @Test
    void testSingleton() {
        Baraja otraBaraja = Baraja.obtenerInstancia();
        assertSame(baraja, otraBaraja, "Baraja debe ser un Singleton.");
    }

    @Test
    public void testBarajaNoEstaVaciaDespuesDeInicializar() {
        assertNotNull(baraja);
        assertTrue(baraja.robarCarta() != null, "La baraja no debería estar vacía al inicio.");
    }

    @Test
    public void testReiniciarBaraja() {
        // Roba una carta antes de reiniciar
        Carta carta1 = baraja.robarCarta();
        assertNotNull(carta1, "Antes de reiniciar, debe ser posible robar una carta.");

        // Reinicia la baraja
        baraja.reiniciar();
        Carta carta2 = baraja.robarCarta();
        assertNotNull(carta2, "Después de reiniciar, las cartas deben estar disponibles.");
    }

    @Test
    public void testRobarCarta() {
        int tamañoInicial = 52; // Número de cartas en la baraja antes de robar
        Carta cartaRobada = baraja.robarCarta();
        assertNotNull(cartaRobada, "La carta robada no debería ser nula.");
        assertEquals(tamañoInicial - 1, baraja.obtenerCantidadDeCartas(), "El tamaño de la baraja debería disminuir después de robar una carta.");
    }

    @Test
    public void testMezclarBaraja() {
        Baraja barajaOriginal = Baraja.obtenerInstancia();
        barajaOriginal.reiniciar();
        Carta primeraCartaOriginal = barajaOriginal.robarCarta();
        barajaOriginal.reiniciar(); // Mezcla y reinicia
        Carta primeraCartaMezclada = barajaOriginal.robarCarta();
        assertNotEquals(primeraCartaOriginal, primeraCartaMezclada, "Las cartas en la parte superior de la baraja deberían ser diferentes después de mezclar.");
    }
}