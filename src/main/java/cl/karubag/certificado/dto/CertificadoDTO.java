package cl.karubag.certificado.dto;

import cl.karubag.certificado.model.TipoCertificado;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class CertificadoDTO {

    private Long id;

    @NotNull(message = "El clienteId es obligatorio")
    private Long clienteId;

    @NotNull(message = "El tipo es obligatorio")
    private TipoCertificado tipo;

    private LocalDate fechaEmision;

    private LocalDate periodoInicio;

    private LocalDate periodoFin;

    private Double totalKilos;

    private Boolean activo;

    public CertificadoDTO() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public TipoCertificado getTipo() { return tipo; }
    public void setTipo(TipoCertificado tipo) { this.tipo = tipo; }
    public LocalDate getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }
    public LocalDate getPeriodoInicio() { return periodoInicio; }
    public void setPeriodoInicio(LocalDate periodoInicio) { this.periodoInicio = periodoInicio; }
    public LocalDate getPeriodoFin() { return periodoFin; }
    public void setPeriodoFin(LocalDate periodoFin) { this.periodoFin = periodoFin; }
    public Double getTotalKilos() { return totalKilos; }
    public void setTotalKilos(Double totalKilos) { this.totalKilos = totalKilos; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}