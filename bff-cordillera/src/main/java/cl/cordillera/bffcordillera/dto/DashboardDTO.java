package cl.cordillera.bffcordillera.dto;
import lombok.*;
import java.time.LocalDateTime;
import java.util.*;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class DashboardDTO {
    private List<KpiDTO> kpis;
    private Map<String, Long> kpisPorEstado;
    private Map<String, Double> ventasPorArea;
    private int totalKpis, kpisEnMeta;
    private LocalDateTime generadoEn;
}
