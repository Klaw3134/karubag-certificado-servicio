# certificado-servicio

Microservicio de gestión de certificados para la plataforma Karübag.

## Descripción
Genera y gestiona los certificados de reciclaje para clientes residenciales y corporativos. Se comunica con pesaje-servicio para obtener los kilos reciclados.

## Tecnologías
- Java 21
- Spring Boot 3.5.14
- Spring Data JPA
- PostgreSQL (Neon)
- WebClient (Spring WebFlux)

## Puerto
`8089`

## Base de datos
`karubag_certificado`

## Comunicación con otros servicios
- `pesaje-servicio` (:8088) — obtiene total de kilos reciclados

## Endpoints principales

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | /api/certificados | Listar todos los certificados |
| GET | /api/certificados/cliente/{clienteId} | Listar por cliente |
| GET | /api/certificados/tipo/{tipo} | Listar por tipo |
| GET | /api/certificados/cliente/{clienteId}/tipo/{tipo} | Listar por cliente y tipo |
| GET | /api/certificados/{id} | Obtener certificado por ID |
| POST | /api/certificados | Crear certificado |
| PUT | /api/certificados/{id} | Actualizar certificado |
| DELETE | /api/certificados/{id} | Eliminar certificado |

## Tipos de certificado
`IMPACTO`, `RECICLAJE_ESG`, `TRIMESTRAL`

## Cómo ejecutar
```bash
./mvnw spring-boot:run
```

## Variables de entorno
```
spring.datasource.url=jdbc:postgresql://<host>/karubag_certificado
spring.datasource.username=<usuario>
spring.datasource.password=<contraseña>
```