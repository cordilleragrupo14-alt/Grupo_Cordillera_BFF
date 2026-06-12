package cl.cordillera.bffcordillera.controller;

import cl.cordillera.bffcordillera.dto.*;
import cl.cordillera.bffcordillera.service.BffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

// NOTA: @CrossOrigin eliminado — el CORS lo maneja CorsConfig de forma global
@RestController
@RequestMapping("/api/bff")
@RequiredArgsConstructor
public class BffController {

    private final BffService service;

    @GetMapping("/dashboard")
    public ResponseEntity<DashboardDTO> obtenerDashboard() {
        return ResponseEntity.ok(service.obtenerDashboard());
    }

    // --- Datos organizacionales ---
    @GetMapping("/datos")
    public ResponseEntity<List<DatoOrganizacionalDTO>> obtenerTodosDatos() {
        return ResponseEntity.ok(service.obtenerTodosDatos());
    }
    @GetMapping("/datos/{id}")
    public ResponseEntity<DatoOrganizacionalDTO> obtenerDatoPorId(@PathVariable String id) {
        DatoOrganizacionalDTO d = service.obtenerDatoPorId(id);
        return d != null ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }
    @GetMapping("/datos/area/{area}")
    public ResponseEntity<List<DatoOrganizacionalDTO>> obtenerDatosPorArea(@PathVariable String area) {
        return ResponseEntity.ok(service.obtenerDatosPorArea(area));
    }
    @PostMapping("/datos")
    public ResponseEntity<DatoOrganizacionalDTO> crearDato(@RequestBody DatoOrganizacionalDTO dto) {
        DatoOrganizacionalDTO d = service.crearDato(dto);
        return d != null ? ResponseEntity.status(HttpStatus.CREATED).body(d)
                         : ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
    @PutMapping("/datos/{id}")
    public ResponseEntity<DatoOrganizacionalDTO> actualizarDato(@PathVariable String id, @RequestBody DatoOrganizacionalDTO dto) {
        DatoOrganizacionalDTO d = service.actualizarDato(id, dto);
        return d != null ? ResponseEntity.ok(d) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/datos/{id}")
    public ResponseEntity<Void> eliminarDato(@PathVariable String id) {
        service.eliminarDato(id);
        return ResponseEntity.noContent().build();
    }

    // --- KPIs ---
    @GetMapping("/kpis")
    public ResponseEntity<List<KpiDTO>> obtenerTodosKpis() {
        return ResponseEntity.ok(service.obtenerTodosKpis());
    }
    @GetMapping("/kpis/{id}")
    public ResponseEntity<KpiDTO> obtenerKpiPorId(@PathVariable String id) {
        KpiDTO k = service.obtenerKpiPorId(id);
        return k != null ? ResponseEntity.ok(k) : ResponseEntity.notFound().build();
    }
    @GetMapping("/kpis/area/{area}")
    public ResponseEntity<List<KpiDTO>> obtenerKpisPorArea(@PathVariable String area) {
        return ResponseEntity.ok(service.obtenerKpisPorArea(area));
    }
    @GetMapping("/kpis/estado/{estado}")
    public ResponseEntity<List<KpiDTO>> obtenerKpisPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(service.obtenerKpisPorEstado(estado));
    }
    @PostMapping("/kpis")
    public ResponseEntity<KpiDTO> crearKpi(@RequestBody KpiDTO dto) {
        KpiDTO k = service.crearKpi(dto);
        return k != null ? ResponseEntity.status(HttpStatus.CREATED).body(k)
                         : ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build();
    }
    @PutMapping("/kpis/{id}")
    public ResponseEntity<KpiDTO> actualizarKpi(@PathVariable String id, @RequestBody KpiDTO dto) {
        KpiDTO k = service.actualizarKpi(id, dto);
        return k != null ? ResponseEntity.ok(k) : ResponseEntity.notFound().build();
    }
    @PatchMapping("/kpis/{id}/valor")
    public ResponseEntity<KpiDTO> actualizarValorKpi(@PathVariable String id, @RequestBody Map<String, Double> body) {
        Double valor = body.get("valor");
        if (valor == null) return ResponseEntity.badRequest().build();
        KpiDTO k = service.actualizarValorKpi(id, valor);
        return k != null ? ResponseEntity.ok(k) : ResponseEntity.notFound().build();
    }
    @DeleteMapping("/kpis/{id}")
    public ResponseEntity<Void> eliminarKpi(@PathVariable String id) {
        service.eliminarKpi(id);
        return ResponseEntity.noContent().build();
    }
}
