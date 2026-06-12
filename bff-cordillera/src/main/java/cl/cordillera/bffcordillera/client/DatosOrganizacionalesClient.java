package cl.cordillera.bffcordillera.client;
import cl.cordillera.bffcordillera.client.fallback.DatosClientFallback;
import cl.cordillera.bffcordillera.dto.DatoOrganizacionalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/** PATRÓN: Circuit Breaker - fallback activo si ms-datos falla */
@FeignClient(name = "ms-datos-organizacionales", url = "${ms.datos.url}", fallback = DatosClientFallback.class)
public interface DatosOrganizacionalesClient {
    @GetMapping("/api/datos") List<DatoOrganizacionalDTO> obtenerTodos();
    @GetMapping("/api/datos/{id}") DatoOrganizacionalDTO obtenerPorId(@PathVariable String id);
    @GetMapping("/api/datos/area/{area}") List<DatoOrganizacionalDTO> obtenerPorArea(@PathVariable String area);
    @PostMapping("/api/datos") DatoOrganizacionalDTO crear(@RequestBody DatoOrganizacionalDTO dto);
    @PutMapping("/api/datos/{id}") DatoOrganizacionalDTO actualizar(@PathVariable String id, @RequestBody DatoOrganizacionalDTO dto);
    @DeleteMapping("/api/datos/{id}") void eliminar(@PathVariable String id);
}
