package edu.eam.ingesoft.fundamentos.parqueadero;

import edu.eam.ingesoft.fundamentos.parqueadero.logica.Propietario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Propietario.
 */
public class PropietarioTest {

    private Propietario propietario;

    @BeforeEach
    public void setUp() {
        propietario = new Propietario("123456789", "Juan Pérez");
    }

    // ==================== PRUEBAS DEL CONSTRUCTOR ====================

    @Test
    public void testConstructorInicializaAtributos() {
        assertEquals("123456789", propietario.getCedula());
        assertEquals("Juan Pérez", propietario.getNombre());
        assertEquals(0, propietario.getHorasAcumuladas());
    }

    // ==================== PRUEBAS DE ACUMULAR HORAS ====================

    @Test
    public void testAcumularHoras() {
        propietario.acumularHoras(10);
        assertEquals(10, propietario.getHorasAcumuladas());
    }

    @Test
    public void testAcumularHorasMultiplesVeces() {
        propietario.acumularHoras(50);
        propietario.acumularHoras(30);
        propietario.acumularHoras(20);
        assertEquals(100, propietario.getHorasAcumuladas());
    }

    // ==================== PRUEBAS DE OBTENER CATEGORÍA ====================

    @Test
    public void testObtenerCategoriaEstandarCon0Horas() {
        assertEquals("ESTANDAR", propietario.obtenerCategoria());
    }

    @Test
    public void testObtenerCategoriaEstandarCon100Horas() {
        propietario.acumularHoras(100);
        assertEquals("ESTANDAR", propietario.obtenerCategoria());
    }

    @Test
    public void testObtenerCategoriaEspecialCon101Horas() {
        propietario.acumularHoras(101);
        assertEquals("ESPECIAL", propietario.obtenerCategoria());
    }

    @Test
    public void testObtenerCategoriaEspecialCon500Horas() {
        propietario.acumularHoras(500);
        assertEquals("ESPECIAL", propietario.obtenerCategoria());
    }

    @Test
    public void testObtenerCategoriaVIPCon501Horas() {
        propietario.acumularHoras(501);
        assertEquals("VIP", propietario.obtenerCategoria());
    }

    @Test
    public void testObtenerCategoriaVIPCon1000Horas() {
        propietario.acumularHoras(1000);
        assertEquals("VIP", propietario.obtenerCategoria());
    }

    // ==================== PRUEBAS DE OBTENER DESCUENTO ====================

    @Test
    public void testObtenerDescuentoEstandar() {
        assertEquals(0.0, propietario.obtenerDescuento(), 0.01);
    }

    @Test
    public void testObtenerDescuentoEspecial() {
        propietario.acumularHoras(150);
        assertEquals(0.10, propietario.obtenerDescuento(), 0.01);
    }

    @Test
    public void testObtenerDescuentoVIP() {
        propietario.acumularHoras(600);
        assertEquals(0.15, propietario.obtenerDescuento(), 0.01);
    }

    // ==================== PRUEBAS DE ES VIP ====================

    @Test
    public void testEsVIPConMenosDe500Horas() {
        propietario.acumularHoras(500);
        assertFalse(propietario.esVIP());
    }

    @Test
    public void testEsVIPConMasDe500Horas() {
        propietario.acumularHoras(501);
        assertTrue(propietario.esVIP());
    }

    @Test
    public void testEsVIPCon0Horas() {
        assertFalse(propietario.esVIP());
    }
}
