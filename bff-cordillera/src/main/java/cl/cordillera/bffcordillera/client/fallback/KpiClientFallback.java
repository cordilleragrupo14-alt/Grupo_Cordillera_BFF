package cl.cordillera.bffcordillera.client.fallback;
import cl.cordillera.bffcordillera.client.KpiClient;
import cl.cordillera.bffcordillera.dto.KpiDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.*;

@Slf4j @Component
public class KpiClientFallback implements KpiClient {
    @Override public List<KpiDTO> obtenerTodos() { log.warn("[CB] ms-kpi no disponible"); return Collections.emptyList(); }
    @Override public KpiDTO obtenerPorId(String id) { log.warn("[CB] ms-kpi no disponible"); return null; }
    @Override public List<KpiDTO> obtenerPorArea(String area) { log.warn("[CB] ms-kpi no disponible"); return Collections.emptyList(); }
    @Override public List<KpiDTO> obtenerPorEstado(String estado) { log.warn("[CB] ms-kpi no disponible"); return Collections.emptyList(); }
    @Override public KpiDTO crear(KpiDTO dto) { log.error("[CB] ms-kpi no disponible"); return null; }
    @Override public KpiDTO actualizar(String id, KpiDTO dto) { log.error("[CB] ms-kpi no disponible"); return null; }
    @Override public KpiDTO actualizarValor(String id, Map<String, Double> body) { log.error("[CB] ms-kpi no disponible"); return null; }
    @Override public void eliminar(String id) { log.error("[CB] ms-kpi no disponible"); }
}
