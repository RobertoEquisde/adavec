package com.adavec.prefacturacion.repository;

import com.adavec.prefacturacion.model.Unidad;
import com.adavec.prefacturacion.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnidadRepository extends JpaRepository<Unidad, Long> {
    List<Unidad> findByProveedor(Proveedor proveedor);
}
