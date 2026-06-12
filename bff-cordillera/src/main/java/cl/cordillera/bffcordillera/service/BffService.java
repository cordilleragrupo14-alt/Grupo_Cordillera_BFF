package cl.cordillera.bffcordillera.service;

import cl.cordillera.bffcordillera.client.*;
import cl.cordillera.bffcordillera.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BffService {

    private final DatosOrganizacionalesClient datosClient;
    private final KpiClient kpiClient;

    public List<DatoOrganizacionalDTO> obtenerTodosDatos()                        { return datosClient.obtenerTodos(); }
    public DatoOrganizacionalDTO       obtenerDatoPorId(String id)                { return datosClient.obtenerPorId(id); }
    public List<DatoOrganizacionalDTO> obtenerDatosPorArea(String area)           { return datosClient.obtenerPorArea(area); }
    public DatoOrganizacionalDTO       crearDato(DatoOrganizacionalDTO dto)       { return datosClient.crear(dto); }
    public DatoOrganizacionalDTO       actualizarDato(String id, DatoOrganizacionalDTO dto) { return datosClient.actualizar(id, dto); }
    public void                        eliminarDato(String id)                    { datosClient.eliminar(id); }

    public List<KpiDTO> obtenerTodosKpis()                          { return kpiClient.obtenerTodos(); }
    public KpiDTO       obtenerKpiPorId(String id)                  { return kpiClient.obtenerPorId(id); }
    public List<KpiDTO> obtenerKpisPorArea(String area)             { return kpiClient.obtenerPorArea(area); }
    public List<KpiDTO> obtenerKpisPorEstado(String estado)         { return kpiClient.obtenerPorEstado(estado); }
    public KpiDTO       crearKpi(KpiDTO dto)                        { return kpiClient.crear(dto); }
    public KpiDTO       actualizarKpi(String id, KpiDTO dto)        { return kpiClient.actualizar(id, dto); }
    public KpiDTO       actualizarValorKpi(String id, Double valor) { return kpiClient.actualizarValor(id, Map.of("valor", valor)); }
    public void         eliminarKpi(String id)                      { kpiClient.eliminar(id); }

    public DashboardDTO obtenerDashboard() {
        List<KpiDTO> kpis = kpiClient.obtenerTodos();
        List<DatoOrganizacionalDTO> datos = datosClient.obtenerTodos();

        // Agrupa por estado (el campo estado en KpiDTO es String, viene serializado del enum)
        Map<String, Long> kpisPorEstado = kpis.stream()
                .filter(k -> k.getEstado() != null)
                .collect(Collectors.groupingBy(KpiDTO::getEstado, Collectors.counting()));

        Map<String, Double> ventasPorArea = datos.stream()
                .filter(d -> d.getValor() != null && d.getArea() != null)
                .collect(Collectors.groupingBy(
                        DatoOrganizacionalDTO::getArea,
                        Collectors.summingDouble(DatoOrganizacionalDTO::getValor)));

        // CORREGIDO: comparación correcta con el String "EN_META" (era k.getEstado() Object)
        long enMeta = kpis.stream()
                .filter(k -> "EN_META".equals(k.getEstado()))
                .count();

        return DashboardDTO.builder()
                .kpis(kpis)
                .kpisPorEstado(kpisPorEstado)
                .ventasPorArea(ventasPorArea)
                .totalKpis(kpis.size())
                .kpisEnMeta((int) enMeta)
                .generadoEn(LocalDateTime.now())
                .build();
    }
}
