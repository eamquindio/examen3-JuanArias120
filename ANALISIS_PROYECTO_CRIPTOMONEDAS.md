# Análisis del Proyecto: Sistema de Gestión de Criptomonedas

## 1. Descripción General

El proyecto **ejerciciocriptomonedas** es un sistema de gestión de portafolio de criptomonedas desarrollado en Java. Permite registrar operaciones de compra y venta, calcular comisiones dinámicas basadas en día y hora, y generar análisis de rentabilidad del portafolio.

---

## 2. Estructura del Proyecto

```
ejerciciocriptomonedas/
├── src/
│   ├── main/java/edu/eam/ingesoft/fundamentos/criptomonedas/
│   │   ├── logica/               # Capa de lógica de negocio
│   │   │   ├── Operacion.java    # Modelo de transacción
│   │   │   ├── Criptomoneda.java # Portafolio por cripto
│   │   │   └── Wallet.java       # Gestor principal del portafolio
│   │   ├── gui/                  # Interfaces de usuario
│   │   │   ├── CLI.java          # Interfaz de línea de comandos
│   │   │   └── GUI.java          # Interfaz gráfica (Swing)
│   │   └── Main.java             # Programa de demostración
│   └── test/java/                # Pruebas unitarias
│       └── edu/eam/ingesoft/fundamentos/criptomonedas/
│           ├── OperacionTest.java
│           ├── CriptomonedaTest.java
│           └── WalletTest.java
├── pom.xml                       # Configuración Maven
├── README.md                     # Documentación principal
├── enunciado.md                  # Especificación del ejercicio
├── INTERFACES.md                 # Documentación de interfaces
└── GUIA_COMPLETA.md              # Guía de implementación
```

---

## 3. Tecnologías Utilizadas

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| Java | 17 | Lenguaje principal |
| Maven | 3.9 | Gestión de dependencias y build |
| JUnit 5 | 5.10.0 | Framework de pruebas unitarias |
| Java Swing | - | Interfaz gráfica |

---

## 4. Arquitectura y Diseño

### 4.1 Modelo de Clases

El sistema utiliza un patrón de **composición jerárquica**:

```
Wallet (1)
  └── contiene → Criptomoneda (*)
                    └── contiene → Operacion (*)
```

### 4.2 Clases Principales

#### **Operacion.java** (~254 líneas)
Representa una transacción individual (compra o venta).

**Atributos:**
- `tipo`: "compra" o "venta"
- `cantidad`: Cantidad de criptomoneda
- `precioUnitario`: Precio por unidad en USD
- `dia`: Día de la semana
- `hora`: Hora del día (0-23)

**Métodos principales:**
| Método | Descripción |
|--------|-------------|
| `getPeriodo()` | Clasifica la hora en Mañana/Tarde/Noche |
| `getMontoOperacion()` | Calcula cantidad × precio |
| `getComisionPorcentaje()` | Calcula % de comisión según día y hora |
| `getComisionDolares()` | Convierte comisión a dólares |
| `getCostoTotal()` | Monto + comisión (para compras) |
| `getIngresoNeto()` | Monto - comisión (para ventas) |

#### **Criptomoneda.java** (~466 líneas)
Gestiona el portafolio de una criptomoneda específica.

**Atributos:**
- `nombre`: Nombre completo (ej: "Bitcoin")
- `simbolo`: Símbolo (ej: "BTC")
- `operaciones`: ArrayList de operaciones

**Métodos principales:**
| Categoría | Métodos |
|-----------|---------|
| Cantidades | `getCantidadComprada()`, `getCantidadVendida()`, `getCantidadActual()` |
| Dinero | `getDineroInvertido()`, `getDineroRecuperado()`, `getComisionesTotales()` |
| Promedios | `getPrecioPromedioCompra()`, `getPrecioPromedioVenta()` |
| Análisis | `getGananciaRealizada()`, `getRentabilidadPorcentaje()`, `getEstado()` |
| Filtros | `obtenerOperacionesPorTipo()`, `obtenerOperacionesPorDia()`, `obtenerOperacionesPorPeriodo()` |
| Búsqueda | `obtenerComisionMasAlta()`, `obtenerComisionMasBaja()` |
| Avanzado | `getGananciaNoRealizada()`, `getPrecioBreakEven()` |

#### **Wallet.java** (~397 líneas)
Gestor principal que coordina múltiples criptomonedas.

**Atributos:**
- `nombreDueno`: Nombre del propietario
- `criptomonedas`: ArrayList de criptomonedas

**Métodos principales:**
| Categoría | Métodos |
|-----------|---------|
| Gestión | `agregarCriptomoneda()`, `buscarCriptomoneda()`, `existeCriptomoneda()` |
| Transacciones | `registrarCompra()`, `registrarVenta()` |
| Portafolio | `calcularValorTotalInvertido()`, `calcularGananciaTotalPortafolio()`, `calcularComisionesTotalesPortafolio()` |
| Rendimiento | `obtenerCriptoConMejorRentabilidad()`, `obtenerCriptoConPeorRentabilidad()` |
| Clasificación | `obtenerCriptosConGanancias()`, `obtenerCriptosConPerdidas()`, `obtenerCriptosSinVentas()` |
| Operaciones | `contarTotalOperaciones()`, `obtenerOperacionesPorDia()`, `obtenerOperacionesPorPeriodo()` |
| Patrones | `obtenerCriptoConMasOperaciones()`, `obtenerDiaMasOperado()`, `obtenerPeriodoMasOperado()` |
| Reportes | `generarReporteCompleto()` |

---

## 5. Reglas de Negocio

### 5.1 Sistema de Comisiones

Las comisiones se calculan dinámicamente según día y hora:

**Comisión Base por Día:**
| Día | Porcentaje Base |
|-----|-----------------|
| Lunes - Viernes | 0.5% |
| Sábado | 0.8% |
| Domingo | 1.0% |

**Ajuste por Período:**
| Período | Horas | Ajuste |
|---------|-------|--------|
| Mañana | 6:00 - 11:59 | +0.0% |
| Tarde | 12:00 - 17:59 | +0.2% |
| Noche | 18:00 - 5:59 | +0.5% |

**Fórmula:** `Comisión Total = Base + Ajuste`

### 5.2 Cálculos Financieros

- **Costo Total (Compra):** `Monto + Comisión`
- **Ingreso Neto (Venta):** `Monto - Comisión`
- **Ganancia Realizada:** `Dinero Recuperado - Costo de Unidades Vendidas`
- **Rentabilidad %:** `((Precio Promedio Venta - Precio Promedio Compra) / Precio Promedio Compra) × 100`
- **Precio Break-Even:** `Dinero Invertido / Cantidad Actual`

---

## 6. Patrones de Diseño Identificados

### 6.1 Patrón de Composición
```java
Wallet → ArrayList<Criptomoneda> → ArrayList<Operacion>
```

### 6.2 Valores Calculados (No Almacenados)
Principio fundamental: los valores derivados se calculan bajo demanda, nunca se almacenan.

```java
// Correcto: Cálculo bajo demanda
public double getCantidadActual() {
    return getCantidadComprada() - getCantidadVendida();
}

// Incorrecto: Almacenar valores calculados
private double cantidadActual; // ❌ Requiere actualización manual
```

### 6.3 Patrón Acumulador
```java
double total = 0;
for (Operacion op : operaciones) {
    if (op.getTipo().equals("compra")) {
        total += op.getCantidad();
    }
}
return total;
```

### 6.4 Patrón Filtro
```java
ArrayList<Operacion> filtradas = new ArrayList<>();
for (Operacion op : operaciones) {
    if (op.getDia().equals(dia)) {
        filtradas.add(op);
    }
}
return filtradas;
```

### 6.5 Patrón Búsqueda de Máximo/Mínimo
```java
Criptomoneda mejor = null;
double maxRentabilidad = Double.NEGATIVE_INFINITY;
for (Criptomoneda cripto : criptomonedas) {
    if (cripto.tieneVentas() && cripto.getRentabilidadPorcentaje() > maxRentabilidad) {
        maxRentabilidad = cripto.getRentabilidadPorcentaje();
        mejor = cripto;
    }
}
return mejor;
```

---

## 7. Interfaces de Usuario

### 7.1 CLI (Command Line Interface)
- Navegación por menús interactivos
- Salida formateada en tablas de texto
- Validación de entrada de usuario
- ~680 líneas de código

### 7.2 GUI (Graphical User Interface)
- Basada en Java Swing
- Interfaz con pestañas: Inicio, Gestión, Operaciones, Análisis, Reportes
- Formularios con ComboBox y campos de texto
- Tablas con actualización en tiempo real
- ~840 líneas de código

---

## 8. Suite de Pruebas

### 8.1 Cobertura de Pruebas

| Clase de Prueba | Casos de Prueba | Estado |
|-----------------|-----------------|--------|
| OperacionTest | 18 | ✅ PASS |
| CriptomonedaTest | 21 | ✅ PASS |
| WalletTest | 39 | ✅ PASS |
| **Total** | **78** | **100% PASS** |

### 8.2 Ejemplo de Prueba
```java
@Test
public void testGetComisionPorcentajeSabadoNoche() {
    operacion.setDia("Sábado");
    operacion.setHora(22);

    double resultado = operacion.getComisionPorcentaje();

    // Base Sábado: 0.8% + Ajuste Noche: 0.5% = 1.3%
    assertEquals(1.3, resultado, 0.01);
}
```

---

## 9. Métricas del Código

| Componente | Líneas Aproximadas |
|------------|-------------------|
| Operacion.java | 254 |
| Criptomoneda.java | 466 |
| Wallet.java | 397 |
| CLI.java | 680+ |
| GUI.java | 840+ |
| Main.java | 146 |
| **Total Lógica** | ~1,117 |
| **Total UI** | ~1,520 |
| **Total Pruebas** | ~690 |

---

## 10. Requisitos de Ejecución

### Prerrequisitos
- JDK 17 o superior
- Maven 3.9 o superior

### Comandos de Ejecución
```bash
# Compilar
mvn clean compile

# Ejecutar pruebas
mvn test

# Ejecutar CLI
mvn exec:java -Dexec.mainClass="edu.eam.ingesoft.fundamentos.criptomonedas.gui.CLI"

# Ejecutar GUI
mvn exec:java -Dexec.mainClass="edu.eam.ingesoft.fundamentos.criptomonedas.gui.GUI"
```

---

## 11. Objetivos Educativos

Este proyecto enseña:

1. **Programación Orientada a Objetos**: Clases, objetos, encapsulamiento, composición
2. **Colecciones**: Manipulación de ArrayList, iteración, filtrado, búsqueda
3. **Estructuras de Control**: Lógica condicional, ciclos, iteraciones anidadas
4. **Diseño de Métodos**: Parámetros, tipos de retorno, delegación
5. **Cálculos**: Fórmulas financieras, agregaciones, estadísticas
6. **Diseño de Software**: Separación de responsabilidades, valores calculados vs almacenados
7. **Testing**: Pruebas unitarias con JUnit, mentalidad TDD
8. **Modelado del Mundo Real**: Diseño de sistemas para representar dominios reales

---

## 12. Conclusión

El proyecto **ejerciciocriptomonedas** es un sistema educativo completo y bien estructurado que demuestra prácticas profesionales de ingeniería de software:

- Separación clara de responsabilidades (lógica, UI, pruebas)
- Cobertura de pruebas exhaustiva (78 pruebas, 100% exitosas)
- Múltiples interfaces de usuario (CLI y GUI)
- Modelado de dominio real
- Documentación extensiva
- Estructura de datos jerárquica con composición de ArrayList
- Patrones avanzados de procesamiento de datos
- Lógica de cálculo financiero con comisiones dependientes del tiempo