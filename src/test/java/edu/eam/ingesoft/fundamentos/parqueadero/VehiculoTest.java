package edu.eam.ingesoft.fundamentos.parqueadero;

import edu.eam.ingesoft.fundamentos.parqueadero.logica.Propietario;
import edu.eam.ingesoft.fundamentos.parqueadero.logica.Vehiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Vehiculo.
 */
public class VehiculoTest {

    private Propietario propietario;

    @BeforeEach
    public void setUp() {
        propietario = new Propietario("123456789", "Juan Pérez");
    }

    // ==================== PRUEBAS DEL CONSTRUCTOR ====================

    @Test
    public void testConstructorInicializaAtributos() {
        Vehiculo vehiculo = new Vehiculo("ABC123", 2020, "Rojo", propietario, "SEDAN");

        assertEquals("ABC123", vehiculo.getPlaca());
        assertEquals(2020, vehiculo.getModelo());
        assertEquals("Rojo", vehiculo.getColor());
        assertEquals(propietario, vehiculo.getPropietario());
        assertEquals("SEDAN", vehiculo.getTipo());
    }

    // ==================== PRUEBAS DE OBTENER TARIFA HORA ====================

    @Test
    public void testObtenerTarifaHoraSEDAN() {
        Vehiculo vehiculo = new Vehiculo("ABC123", 2020, "Rojo", propietario, "SEDAN");
        assertEquals(1500, vehiculo.obtenerTarifaHora(), 0.01);
    }

    @Test
    public void testObtenerTarifaHoraSUV() {
        Vehiculo vehiculo = new Vehiculo("XYZ789", 2022, "Negro", propietario, "SUV");
        assertEquals(2300, vehiculo.obtenerTarifaHora(), 0.01);
    }

    @Test
    public void testObtenerTarifaHoraCAMION() {
        Vehiculo vehiculo = new Vehiculo("CAM456", 2019, "Blanco", propietario, "CAMION");
        assertEquals(3000, vehiculo.obtenerTarifaHora(), 0.01);
    }

    @Test
    public void testObtenerTarifaHoraTipoInvalido() {
        Vehiculo vehiculo = new Vehiculo("INV000", 2021, "Azul", propietario, "MOTO");
        assertEquals(0, vehiculo.obtenerTarifaHora(), 0.01);
    }

    // ==================== PRUEBAS DE GETTERS ====================

    @Test
    public void testGetPropietarioRetornaObjetoCorrecto() {
        Vehiculo vehiculo = new Vehiculo("ABC123", 2020, "Rojo", propietario, "SEDAN");
        assertEquals("123456789", vehiculo.getPropietario().getCedula());
        assertEquals("Juan Pérez", vehiculo.getPropietario().getNombre());
    }
}
