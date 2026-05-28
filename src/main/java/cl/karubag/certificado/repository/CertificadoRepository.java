package cl.karubag.certificado.repository;

import cl.karubag.certificado.model.Certificado;
import cl.karubag.certificado.model.TipoCertificado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CertificadoRepository extends JpaRepository<Certificado, Long> {

    List<Certificado> findByClienteId(Long clienteId);

    List<Certificado> findByTipo(TipoCertificado tipo);

    List<Certificado> findByClienteIdAndTipo(Long clienteId, TipoCertificado tipo);

    List<Certificado> findByActivoTrue();
}
