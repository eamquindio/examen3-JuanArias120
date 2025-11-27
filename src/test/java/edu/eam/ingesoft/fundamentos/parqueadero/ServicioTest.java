package edu.eam.ingesoft.fundamentos.parqueadero;

import edu.eam.ingesoft.fundamentos.parqueadero.logica.Propietario;
import edu.eam.ingesoft.fundamentos.parqueadero.logica.Servicio;
import edu.eam.ingesoft.fundamentos.parqueadero.logica.Vehiculo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Servicio.
 */
public class ServicioTest {

    private Propietario propietarioEstandar;
    private Propietario propietarioEspecial;
    private Propietario propietarioVIP;

    @BeforeEach
    public void setUp() {
        propietarioEstandar = new Propietario("111111111", "Cliente Estándar");

        propietarioEspecial = new Propietario("222222222", "Cliente Especial");
        propietarioEspecial.acumularHoras(200); // Categoría ESPECIAL

        propietarioVIP = new Propietario("333333333", "Cliente VIP");
        propietarioVIP.acumularHoras(600); // Categoría VIP
    }

    // ==================== PRUEBAS DE CALCULAR HORAS ====================

    @Test
    public void testCalcularHoras() {
        Vehiculo vehiculo = new Vehiculo("ABC123", 2020, "Rojo", propietarioEstandar, "SEDAN");
        Servicio servicio = new Servicio(8, 13, vehiculo);

        assertEquals(5, servicio.calcularHoras());
    }

    @Test
    public void testCalcularHoras1Hora() {
        Vehiculo vehiculo = new Vehiculo("ABC123", 2020, "Rojo", propietarioEstandar, "SEDAN");
        Servicio servicio = new Servicio(10, 11, vehiculo);

        assertEquals(1, servicio.calcularHoras());
    }

    // ==================== PRUEBAS DE COSTO SEDAN ====================

    @Test
    public void testCalcularCostoSedanClienteEstandar() {
        Vehiculo vehiculo = new Vehiculo("ABC123", 2020, "Rojo", propietarioEstandar, "SEDAN");
        Servicio servicio = new Servicio(8, 13, vehiculo); // 5 horas

        // 5 horas × $1,500 × (1 - 0.0) = $7,500
        assertEquals(7500, servicio.getCosto(), 0.01);
    }

    @Test
    public void testCalcularCostoSedanClienteEspecial() {
        Vehiculo vehiculo = new Vehiculo("ABC123", 2020, "Rojo", propietarioEspecial, "SEDAN");
        Servicio servicio = new Servicio(8, 13, vehiculo); // 5 horas

        // 5 horas × $1,500 × (1 - 0.10) = $6,750
        assertEquals(6750, servicio.getCosto(), 0.01);
    }

    @Test
    public void testCalcularCostoSedanClienteVIP() {
        Vehiculo vehiculo = new Vehiculo("ABC123", 2020, "Rojo", propietarioVIP, "SEDAN");
        Servicio servicio = new Servicio(8, 13, vehiculo); // 5 horas

        // 5 horas × $1,500 × (1 - 0.15) = $6,375
        assertEquals(6375, servicio.getCosto(), 0.01);
    }

    // ==================== PRUEBAS DE COSTO SUV ====================

    @Test
    public void testCalcularCostoSUVClienteEstandar() {
        Vehiculo vehiculo = new Vehiculo("XYZ789", 2022, "Negro", propietarioEstandar, "SUV");
        Servicio servicio = new Servicio(10, 14, vehiculo); // 4 horas

        // 4 horas × $2,300 × (1 - 0.0) = $9,200
        assertEquals(9200, servicio.getCosto(), 0.01);
    }

    @Test
    public void testCalcularCostoSUVClienteEspecial() {
        Vehiculo vehiculo = new Vehiculo("XYZ789", 2022, "Negro", propietarioEspecial, "SUV");
        Servicio servicio = new Servicio(10, 14, vehiculo); // 4 horas

        // 4 horas × $2,300 × (1 - 0.10) = $8,280
        assertEquals(8280, servicio.getCosto(), 0.01);
    }

    @Test
    public void testCalcularCostoSUVClienteVIP() {
        Vehiculo vehiculo = new Vehiculo("XYZ789", 2022, "Negro", propietarioVIP, "SUV");
        Servicio servicio = new Servicio(10, 14, vehiculo); // 4 horas

        // 4 horas × $2,300 × (1 - 0.15) = $7,820
        assertEquals(7820, servicio.getCosto(), 0.01);
    }

    // ==================== PRUEBAS DE COSTO CAMION ====================

    @Test
    public void testCalcularCostoCAMIONClienteEstandar() {
        Vehiculo vehiculo = new Vehiculo("CAM456", 2019, "Blanco", propietarioEstandar, "CAMION");
        Servicio servicio = new Servicio(6, 10, vehiculo); // 4 horas

        // 4 horas × $3,000 × (1 - 0.0) = $12,000
        assertEquals(12000, servicio.getCosto(), 0.01);
    }

    @Test
    public void testCalcularCostoCAMIONClienteEspecial() {
        Vehiculo vehiculo = new Vehiculo("CAM456", 2019, "Blanco", propietarioEspecial, "CAMION");
        Servicio servicio = new Servicio(6, 10, vehiculo); // 4 horas

        // 4 horas × $3,000 × (1 - 0.10) = $10,800
        assertEquals(10800, servicio.getCosto(), 0.01);
    }

    @Test
    public void testCalcularCostoCAMIONClienteVIP() {
        Vehiculo vehiculo = new Vehiculo("CAM456", 2019, "Blanco", propietarioVIP, "CAMION");
        Servicio servicio = new Servicio(6, 10, vehiculo); // 4 horas

        // 4 horas × $3,000 × (1 - 0.15) = $10,200
        assertEquals(10200, servicio.getCosto(), 0.01);
    }

    // ==================== PRUEBAS DE GETTERS ====================

    @Test
    public void testGettersRetornanValoresCorrectos() {
        Vehiculo vehiculo = new Vehiculo("ABC123", 2020, "Rojo", propietarioEstandar, "SEDAN");
        Servicio servicio = new Servicio(8, 15, vehiculo);

        assertEquals(8, servicio.getHoraIngreso());
        assertEquals(15, servicio.getHoraSalida());
        assertEquals(vehiculo, servicio.getVehiculo());
    }
}
