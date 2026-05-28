package cl.karubag.certificado.controller;

import cl.karubag.certificado.dto.CertificadoDTO;
import cl.karubag.certificado.model.TipoCertificado;
import cl.karubag.certificado.service.CertificadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/certificados")
public class CertificadoController {

    private final CertificadoService certificadoService;

    public CertificadoController(CertificadoService certificadoService) {
        this.certificadoService = certificadoService;
    }

    @GetMapping
    public ResponseEntity<List<CertificadoDTO>> listarTodos() {
        return ResponseEntity.ok(certificadoService.listarTodos());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CertificadoDTO>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(certificadoService.listarPorCliente(clienteId));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<CertificadoDTO>> listarPorTipo(@PathVariable TipoCertificado tipo) {
        return ResponseEntity.ok(certificadoService.listarPorTipo(tipo));
    }

    @GetMapping("/cliente/{clienteId}/tipo/{tipo}")
    public ResponseEntity<List<CertificadoDTO>> listarPorClienteYTipo(
            @PathVariable Long clienteId,
            @PathVariable TipoCertificado tipo) {
        return ResponseEntity.ok(certificadoService.listarPorClienteYTipo(clienteId, tipo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CertificadoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(certificadoService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<CertificadoDTO> crear(@Valid @RequestBody CertificadoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(certificadoService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CertificadoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CertificadoDTO dto) {
        return ResponseEntity.ok(certificadoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        certificadoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}