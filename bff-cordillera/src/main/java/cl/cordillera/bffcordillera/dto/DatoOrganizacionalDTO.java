package cl.cordillera.bffcordillera.dto;
import lombok.*;
import java.time.LocalDateTime;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class DatoOrganizacionalDTO {
    private String id, area, tipoDato, unidad, sucursal, fuente;
    private Double valor;
    private LocalDateTime fechaDato;
}
