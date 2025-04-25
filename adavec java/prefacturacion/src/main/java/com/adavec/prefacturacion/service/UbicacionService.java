package com.adavec.prefacturacion.service;

import com.adavec.prefacturacion.model.Ubicacion;
import com.adavec.prefacturacion.repository.UbicacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    public UbicacionService(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    public List<Ubicacion> listarTodas() {
        return ubicacionRepository.findAll();
    }
}
