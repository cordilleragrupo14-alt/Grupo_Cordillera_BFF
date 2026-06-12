package cl.cordillera.bffcordillera.client;
import cl.cordillera.bffcordillera.client.fallback.KpiClientFallback;
import cl.cordillera.bffcordillera.dto.KpiDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/** PATRÓN: Circuit Breaker - fallback activo si ms-kpi falla */
@FeignClient(name = "ms-gestion-kpi", url = "${ms.kpi.url}", fallback = KpiClientFallback.class)
public interface KpiClient {
    @GetMapping("/api/kpis") List<KpiDTO> obtenerTodos();
    @GetMapping("/api/kpis/{id}") KpiDTO obtenerPorId(@PathVariable String id);
    @GetMapping("/api/kpis/area/{area}") List<KpiDTO> obtenerPorArea(@PathVariable String area);
    @GetMapping("/api/kpis/estado/{estado}") List<KpiDTO> obtenerPorEstado(@PathVariable String estado);
    @PostMapping("/api/kpis") KpiDTO crear(@RequestBody KpiDTO dto);
    @PutMapping("/api/kpis/{id}") KpiDTO actualizar(@PathVariable String id, @RequestBody KpiDTO dto);
    @PatchMapping("/api/kpis/{id}/valor") KpiDTO actualizarValor(@PathVariable String id, @RequestBody Map<String, Double> body);
    @DeleteMapping("/api/kpis/{id}") void eliminar(@PathVariable String id);
}
