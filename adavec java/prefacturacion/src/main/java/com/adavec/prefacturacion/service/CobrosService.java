package com.adavec.prefacturacion.service;

import com.adavec.prefacturacion.model.Cobros;
import com.adavec.prefacturacion.repository.CobrosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CobrosService {

    private final CobrosRepository cobrosRepository;

    public CobrosService(CobrosRepository cobrosRepository) {
        this.cobrosRepository = cobrosRepository;
    }

    public List<Cobros> listarTodos() {
        return cobrosRepository.findAll();
    }

    public Optional<Cobros> obtenerPorId(Long id) {
        return cobrosRepository.findById(id);
    }

    public Cobros guardar(Cobros cobro) {
        return cobrosRepository.save(cobro);
    }

    public void eliminar(Long id) {
        cobrosRepository.deleteById(id);
    }
}
