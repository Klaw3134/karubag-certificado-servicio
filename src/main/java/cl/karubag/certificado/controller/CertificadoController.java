package cl.karubag.certificado.controller;

import cl.karubag.certificado.dto.CertificadoDTO;
import cl.karubag.certificado.model.TipoCertificado;
import cl.karubag.certificado.service.CertificadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Certificados", description = "Gestion de certificados de reciclaje Karübag")
@RestController
@RequestMapping("/api/certificados")
public class CertificadoController {

    private final CertificadoService certificadoService;

    public CertificadoController(CertificadoService certificadoService) {
        this.certificadoService = certificadoService;
    }

    @Operation(summary = "Listar todos los certificados", description = "Retorna la lista completa de certificados")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<CertificadoDTO>> listarTodos() {
        return ResponseEntity.ok(certificadoService.listarTodos());
    }

    @Operation(summary = "Listar por cliente", description = "Retorna certificados de un cliente especifico")
    @ApiResponse(responseCode = "200", description = "Lista de certificados del cliente")
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CertificadoDTO>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(certificadoService.listarPorCliente(clienteId));
    }

    @Operation(summary = "Listar por tipo", description = "Retorna certificados filtrados por tipo")
    @ApiResponse(responseCode = "200", description = "Lista de certificados por tipo")
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CertificadoDTO>> listarPorTipo(@PathVariable TipoCertificado tipo) {
        return ResponseEntity.ok(certificadoService.listarPorTipo(tipo));
    }

    @Operation(summary = "Listar por cliente y tipo", description = "Retorna certificados de un cliente filtrados por tipo")
    @ApiResponse(responseCode = "200", description = "Lista de certificados por cliente y tipo")
    @GetMapping("/cliente/{clienteId}/tipo/{tipo}")
    public ResponseEntity<List<CertificadoDTO>> listarPorClienteYTipo(
            @PathVariable Long clienteId, @PathVariable TipoCertificado tipo) {
        return ResponseEntity.ok(certificadoService.listarPorClienteYTipo(clienteId, tipo));
    }

    @Operation(summary = "Obtener certificado por ID", description = "Busca un certificado por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Certificado encontrado"),
        @ApiResponse(responseCode = "404", description = "Certificado no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CertificadoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(certificadoService.obtenerPorId(id));
    }

    @Operation(summary = "Crear certificado", description = "Genera un certificado de reciclaje para un cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Certificado creado exitosamente",
            content = @Content(schema = @Schema(implementation = CertificadoDTO.class),
            examples = @ExampleObject(value = "{\"clienteId\": 1, \"tipo\": \"IMPACTO\", \"periodoInicio\": \"2026-06-01\", \"periodoFin\": \"2026-06-30\", \"totalKilos\": 3.5, \"activo\": true}")))
    })
    @PostMapping
    public ResponseEntity<CertificadoDTO> crear(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Datos del certificado. Tipos: IMPACTO, RECICLAJE_ESG, TRIMESTRAL",
            required = true,
            content = @Content(examples = @ExampleObject(value = "{\"clienteId\": 1, \"tipo\": \"IMPACTO\", \"periodoInicio\": \"2026-06-01\", \"periodoFin\": \"2026-06-30\", \"totalKilos\": 3.5, \"activo\": true}")))
        @Valid @RequestBody CertificadoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(certificadoService.crear(dto));
    }

    @Operation(summary = "Actualizar certificado", description = "Actualiza los datos de un certificado")
    @ApiResponse(responseCode = "200", description = "Certificado actualizado exitosamente")
    @PutMapping("/{id}")
    public ResponseEntity<CertificadoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CertificadoDTO dto) {
        return ResponseEntity.ok(certificadoService.actualizar(id, dto));
    }

    @Operation(summary = "Eliminar certificado", description = "Elimina un certificado por su ID")
    @ApiResponse(responseCode = "204", description = "Certificado eliminado exitosamente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        certificadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
