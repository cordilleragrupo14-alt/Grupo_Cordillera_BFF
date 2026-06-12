package cl.cordillera.bffcordillera.dto;
import lombok.*;
import java.time.LocalDateTime;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KpiDTO {
    private String id, nombre, descripcion, area, unidad, estado, periodicidad, sucursal;
    private Double meta, valorActual, porcentajeCumplimiento;
    private LocalDateTime fechaVencimiento, fechaActualizacion;
}
