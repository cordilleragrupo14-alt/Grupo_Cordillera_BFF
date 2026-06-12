package cl.cordillera.bffcordillera.service;
import cl.cordillera.bffcordillera.client.*;
import cl.cordillera.bffcordillera.dto.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BffServiceTest {
    @Mock DatosOrganizacionalesClient datosClient;
    @Mock KpiClient kpiClient;
    @InjectMocks BffService service;

    private KpiDTO kpi;
    private DatoOrganizacionalDTO dato;

    @BeforeEach void setUp() {
        kpi = KpiDTO.builder().id("1").nombre("Ventas").area("ventas").meta(10000000.0).valorActual(8500000.0).estado("EN_PROGRESO").porcentajeCumplimiento(85.0).build();
        dato = DatoOrganizacionalDTO.builder().id("1").area("ventas").tipoDato("ventas_diarias").valor(500000.0).build();
    }

    @Test void obtenerTodosKpis_debeRetornarLista() {
        when(kpiClient.obtenerTodos()).thenReturn(List.of(kpi));
        assertEquals(1, service.obtenerTodosKpis().size());
    }
    @Test void obtenerTodosDatos_debeRetornarLista() {
        when(datosClient.obtenerTodos()).thenReturn(List.of(dato));
        assertEquals(1, service.obtenerTodosDatos().size());
    }
    @Test void obtenerDashboard_debeAgregarDatosDeAmbosMicroservicios() {
        when(kpiClient.obtenerTodos()).thenReturn(List.of(kpi));
        when(datosClient.obtenerTodos()).thenReturn(List.of(dato));
        DashboardDTO dashboard = service.obtenerDashboard();
        assertNotNull(dashboard); assertEquals(1, dashboard.getTotalKpis());
        assertNotNull(dashboard.getGeneradoEn());
    }
    @Test void obtenerDashboard_conKpiEnMeta_debeContarCorrectamente() {
        KpiDTO enMeta = KpiDTO.builder().id("2").area("ventas").estado("EN_META").valorActual(10000000.0).meta(10000000.0).build();
        when(kpiClient.obtenerTodos()).thenReturn(List.of(kpi, enMeta));
        when(datosClient.obtenerTodos()).thenReturn(List.of());
        DashboardDTO d = service.obtenerDashboard();
        assertEquals(2, d.getTotalKpis()); assertEquals(1, d.getKpisEnMeta());
    }
    @Test void crearKpi_debeDelegarAlClienteKpi() {
        when(kpiClient.crear(kpi)).thenReturn(kpi);
        assertNotNull(service.crearKpi(kpi));
    }
    @Test void crearDato_debeDelegarAlClienteDatos() {
        when(datosClient.crear(dato)).thenReturn(dato);
        assertNotNull(service.crearDato(dato));
    }
}
