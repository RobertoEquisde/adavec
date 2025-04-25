package com.adavec.prefacturacion.service;

import com.adavec.prefacturacion.model.Pension;
import com.adavec.prefacturacion.repository.PensionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PensionService {

    private final PensionRepository pensionRepository;

    public PensionService(PensionRepository pensionRepository) {
        this.pensionRepository = pensionRepository;
    }

    public List<Pension> listarTodas() {
        return pensionRepository.findAll();
    }

    public List<Pension> listarPorUnidad(Long unidadId) {
        return pensionRepository.findByUnidadId(unidadId);
    }

    public Pension guardar(Pension pension) {
        return pensionRepository.save(pension);
    }

    public void eliminar(Long id) {
        pensionRepository.deleteById(id);
    }
}
