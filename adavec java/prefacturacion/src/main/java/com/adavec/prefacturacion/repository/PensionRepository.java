package com.adavec.prefacturacion.repository;

import com.adavec.prefacturacion.model.Pension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PensionRepository extends JpaRepository<Pension, Long> {
    List<Pension> findByUnidadId(Long unidadId);
}
