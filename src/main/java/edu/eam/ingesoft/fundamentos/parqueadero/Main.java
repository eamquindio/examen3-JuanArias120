package edu.eam.ingesoft.fundamentos.parqueadero;

import edu.eam.ingesoft.fundamentos.parqueadero.logica.Parqueadero;
import edu.eam.ingesoft.fundamentos.parqueadero.logica.Propietario;

/**
 * Clase principal de demostración del Sistema de Parqueadero "EstacionaFácil".
 * Muestra ejemplos de uso de todas las funcionalidades del sistema.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("  SISTEMA DE PARQUEADERO 'ESTACIONAFÁCIL'");
        System.out.println("=".repeat(60));
        System.out.println();

        // Crear el parqueadero
        Parqueadero parqueadero = new Parqueadero();

        // ==================== REGISTRAR PROPIETARIOS ====================
        System.out.println("--- REGISTRANDO PROPIETARIOS ---");

        boolean registro1 = parqueadero.registrarPropietario("123456789", "Juan Pérez");
        System.out.println("Registrar Juan Pérez (123456789): " + (registro1 ? "EXITOSO" : "FALLIDO"));

        boolean registro2 = parqueadero.registrarPropietario("987654321", "María García");
        System.out.println("Registrar María García (987654321): " + (registro2 ? "EXITOSO" : "FALLIDO"));

        boolean registro3 = parqueadero.registrarPropietario("555555555", "Carlos López");
        System.out.println("Registrar Carlos López (555555555): " + (registro3 ? "EXITOSO" : "FALLIDO"));

        // Intentar registrar propietario duplicado
        boolean registroDuplicado = parqueadero.registrarPropietario("123456789", "Juan Duplicado");
        System.out.println("Registrar duplicado (123456789): " + (registroDuplicado ? "EXITOSO" : "FALLIDO (esperado)"));

        System.out.println();

        // ==================== REGISTRAR VEHÍCULOS ====================
        System.out.println("--- REGISTRANDO VEHÍCULOS ---");

        boolean vehiculo1 = parqueadero.registrarVehiculo("ABC123", 2020, "Rojo", "123456789", "SEDAN");
        System.out.println("Registrar SEDAN ABC123: " + (vehiculo1 ? "EXITOSO" : "FALLIDO"));

        boolean vehiculo2 = parqueadero.registrarVehiculo("XYZ789", 2022, "Negro", "987654321", "SUV");
        System.out.println("Registrar SUV XYZ789: " + (vehiculo2 ? "EXITOSO" : "FALLIDO"));

        boolean vehiculo3 = parqueadero.registrarVehiculo("CAM456", 2019, "Blanco", "555555555", "CAMION");
        System.out.println("Registrar CAMION CAM456: " + (vehiculo3 ? "EXITOSO" : "FALLIDO"));

        // Intentar registrar vehículo duplicado
        boolean vehiculoDuplicado = parqueadero.registrarVehiculo("ABC123", 2021, "Azul", "987654321", "SUV");
        System.out.println("Registrar duplicado ABC123: " + (vehiculoDuplicado ? "EXITOSO" : "FALLIDO (esperado)"));

        System.out.println();

        // ==================== REGISTRAR SERVICIOS ====================
        System.out.println("--- REGISTRANDO SERVICIOS ---");

        // Servicio 1: SEDAN, 5 horas, cliente ESTANDAR
        double costo1 = parqueadero.registrarServicio("ABC123", 8, 13);
        System.out.println("Servicio SEDAN ABC123 (8:00-13:00): $" + costo1);
        System.out.println("  -> 5 horas × $1,500 = $7,500 (sin descuento)");

        // Servicio 2: SUV, 3 horas, cliente ESTANDAR
        double costo2 = parqueadero.registrarServicio("XYZ789", 10, 13);
        System.out.println("Servicio SUV XYZ789 (10:00-13:00): $" + costo2);
        System.out.println("  -> 3 horas × $2,300 = $6,900 (sin descuento)");

        // Servicio 3: CAMION, 4 horas, cliente ESTANDAR
        double costo3 = parqueadero.registrarServicio("CAM456", 9, 13);
        System.out.println("Servicio CAMION CAM456 (9:00-13:00): $" + costo3);
        System.out.println("  -> 4 horas × $3,000 = $12,000 (sin descuento)");

        // Intentar servicio con hora inválida
        double costoInvalido = parqueadero.registrarServicio("ABC123", 25, 30);
        System.out.println("Servicio con hora inválida: " + (costoInvalido == -1 ? "RECHAZADO (esperado)" : "$" + costoInvalido));

        System.out.println();

        // ==================== SIMULAR MUCHOS SERVICIOS PARA UN CLIENTE ====================
        System.out.println("--- SIMULANDO CLIENTE FRECUENTE (Juan Pérez) ---");

        // Simular muchos servicios para que Juan llegue a VIP (más de 500 horas)
        // Necesitamos acumular más horas directamente para la demostración
        parqueadero.acumularHorasCliente("123456789", 495); // Ya tiene 5, ahora tendrá 500

        Propietario juan = parqueadero.buscarPropietario("123456789");
        System.out.println("Horas acumuladas de Juan: " + juan.getHorasAcumuladas());
        System.out.println("Categoría de Juan: " + juan.obtenerCategoria());
        System.out.println("Descuento de Juan: " + (juan.obtenerDescuento() * 100) + "%");

        // Un servicio más lo convierte en VIP
        double costoVIP = parqueadero.registrarServicio("ABC123", 8, 10); // 2 horas más
        System.out.println("\nNuevo servicio (2 horas):");
        System.out.println("Horas acumuladas de Juan: " + juan.getHorasAcumuladas());
        System.out.println("Categoría de Juan: " + juan.obtenerCategoria());
        System.out.println("Descuento de Juan: " + (juan.obtenerDescuento() * 100) + "%");
        System.out.println("Costo con descuento VIP: $" + costoVIP);
        System.out.println("  -> 2 horas × $1,500 × (1 - 0.15) = $2,550");

        System.out.println();

        // ==================== ESTADÍSTICAS ====================
        System.out.println("--- ESTADÍSTICAS DEL PARQUEADERO ---");

        double totalRecaudado = parqueadero.calcularTotalRecaudado();
        System.out.println("Total recaudado: $" + totalRecaudado);

        int clientesVIP = parqueadero.contarClientesVIP();
        System.out.println("Número de clientes VIP: " + clientesVIP);

        Propietario clienteMasHoras = parqueadero.obtenerClienteMasHoras();
        if (clienteMasHoras != null) {
            System.out.println("Cliente con más horas: " + clienteMasHoras.getNombre() +
                    " (" + clienteMasHoras.getHorasAcumuladas() + " horas)");
        }

        System.out.println();
        System.out.println("=".repeat(60));
        System.out.println("  FIN DE LA DEMOSTRACIÓN");
        System.out.println("=".repeat(60));
    }
}
