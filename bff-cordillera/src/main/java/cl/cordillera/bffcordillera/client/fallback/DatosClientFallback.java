package cl.cordillera.bffcordillera.client.fallback;
import cl.cordillera.bffcordillera.client.DatosOrganizacionalesClient;
import cl.cordillera.bffcordillera.dto.DatoOrganizacionalDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.*;

@Slf4j @Component
public class DatosClientFallback implements DatosOrganizacionalesClient {
    @Override public List<DatoOrganizacionalDTO> obtenerTodos() { log.warn("[CB] ms-datos no disponible"); return Collections.emptyList(); }
    @Override public DatoOrganizacionalDTO obtenerPorId(String id) { log.warn("[CB] ms-datos no disponible"); return null; }
    @Override public List<DatoOrganizacionalDTO> obtenerPorArea(String area) { log.warn("[CB] ms-datos no disponible"); return Collections.emptyList(); }
    @Override public DatoOrganizacionalDTO crear(DatoOrganizacionalDTO dto) { log.error("[CB] ms-datos no disponible"); return null; }
    @Override public DatoOrganizacionalDTO actualizar(String id, DatoOrganizacionalDTO dto) { log.error("[CB] ms-datos no disponible"); return null; }
    @Override public void eliminar(String id) { log.error("[CB] ms-datos no disponible"); }
}
