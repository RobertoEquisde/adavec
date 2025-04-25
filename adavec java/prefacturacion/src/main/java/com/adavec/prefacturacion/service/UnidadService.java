package com.adavec.prefacturacion.service;

import com.adavec.prefacturacion.model.Proveedor;
import com.adavec.prefacturacion.model.Unidad;
import com.adavec.prefacturacion.repository.UnidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadService {

    private final UnidadRepository unidadRepository;

    public UnidadService(UnidadRepository unidadRepository) {
        this.unidadRepository = unidadRepository;
    }

    public List<Unidad> listarTodas() {
        return unidadRepository.findAll();
    }

    public List<Unidad> listarPorProveedor(Proveedor proveedor) {
        return unidadRepository.findByProveedor(proveedor);
    }


    public Unidad guardar(Unidad unidad) {
        return unidadRepository.save(unidad);
    }
}
