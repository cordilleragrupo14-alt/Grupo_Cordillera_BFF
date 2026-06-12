# bff-cordillera
Backend For Frontend — Grupo Cordillera | Puerto: 8080

## Variables de entorno (Render)
| Variable | Valor |
|----------|-------|
| `MS_DATOS_URL` | `https://grupo-cordillera-b-ms-datos.onrender.com` |
| `MS_KPI_URL` | `https://grupo-cordillera-b-msv-gestion.onrender.com` |

## Endpoint principal
`GET /api/bff/dashboard` — respuesta agregada de ambos microservicios para el frontend

## Todos los endpoints
- `/api/bff/dashboard`
- `/api/bff/datos/**`
- `/api/bff/kpis/**`

## Patrones implementados
- **Circuit Breaker**: `DatosClientFallback`, `KpiClientFallback` (Resilience4j)
- **Repository Pattern (vía Feign)**: `DatosOrganizacionalesClient`, `KpiClient`

## Ejecución local
```bash
mvn clean install && mvn spring-boot:run
```
