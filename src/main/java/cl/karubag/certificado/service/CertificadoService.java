package cl.karubag.certificado.service;

import cl.karubag.certificado.dto.CertificadoDTO;
import cl.karubag.certificado.model.Certificado;
import cl.karubag.certificado.model.TipoCertificado;
import cl.karubag.certificado.repository.CertificadoRepository;
import cl.karubag.certificado.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificadoService {

    private final CertificadoRepository certificadoRepository;

    public CertificadoService(CertificadoRepository certificadoRepository) {
        this.certificadoRepository = certificadoRepository;
    }

    public List<CertificadoDTO> listarTodos() {
        return certificadoRepository.findAll()
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<CertificadoDTO> listarPorCliente(Long clienteId) {
        return certificadoRepository.findByClienteId(clienteId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<CertificadoDTO> listarPorTipo(TipoCertificado tipo) {
        return certificadoRepository.findByTipo(tipo)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<CertificadoDTO> listarPorClienteYTipo(Long clienteId, TipoCertificado tipo) {
        return certificadoRepository.findByClienteIdAndTipo(clienteId, tipo)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CertificadoDTO obtenerPorId(Long id) {
        Certificado certificado = certificadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certificado no encontrado con id: " + id));
        return toDTO(certificado);
    }

    public CertificadoDTO crear(CertificadoDTO dto) {
        Certificado certificado = toEntity(dto);
        if (certificado.getFechaEmision() == null) {
            certificado.setFechaEmision(LocalDate.now());
        }
        return toDTO(certificadoRepository.save(certificado));
    }

    public CertificadoDTO actualizar(Long id, CertificadoDTO dto) {
        Certificado certificado = certificadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Certificado no encontrado con id: " + id));
        certificado.setClienteId(dto.getClienteId());
        certificado.setTipo(dto.getTipo());
        certificado.setFechaEmision(dto.getFechaEmision());
        certificado.setPeriodoInicio(dto.getPeriodoInicio());
        certificado.setPeriodoFin(dto.getPeriodoFin());
        certificado.setTotalKilos(dto.getTotalKilos());
        certificado.setActivo(dto.getActivo());
        return toDTO(certificadoRepository.save(certificado));
    }

    public void eliminar(Long id) {
        certificadoRepository.deleteById(id);
    }

    private CertificadoDTO toDTO(Certificado c) {
        CertificadoDTO dto = new CertificadoDTO();
        dto.setId(c.getId());
        dto.setClienteId(c.getClienteId());
        dto.setTipo(c.getTipo());
        dto.setFechaEmision(c.getFechaEmision());
        dto.setPeriodoInicio(c.getPeriodoInicio());
        dto.setPeriodoFin(c.getPeriodoFin());
        dto.setTotalKilos(c.getTotalKilos());
        dto.setActivo(c.getActivo());
        return dto;
    }

    private Certificado toEntity(CertificadoDTO dto) {
        Certificado c = new Certificado();
        c.setClienteId(dto.getClienteId());
        c.setTipo(dto.getTipo());
        c.setFechaEmision(dto.getFechaEmision());
        c.setPeriodoInicio(dto.getPeriodoInicio());
        c.setPeriodoFin(dto.getPeriodoFin());
        c.setTotalKilos(dto.getTotalKilos());
        c.setActivo(dto.getActivo() != null ? dto.getActivo() : true);
        return c;
    }
}