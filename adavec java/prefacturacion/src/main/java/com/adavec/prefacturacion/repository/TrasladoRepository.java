package com.adavec.prefacturacion.repository;

import com.adavec.prefacturacion.model.Traslado;
import com.adavec.prefacturacion.model.Unidad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface TrasladoRepository extends JpaRepository<Traslado, Long> {
    List<Traslado> findByUnidad(Unidad unidad);
}
