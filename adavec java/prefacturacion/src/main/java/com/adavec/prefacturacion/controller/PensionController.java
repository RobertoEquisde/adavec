package com.adavec.prefacturacion.controller;

import com.adavec.prefacturacion.model.Pension;
import com.adavec.prefacturacion.service.PensionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pensiones")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PensionController {

    private final PensionService pensionService;

    public PensionController(PensionService pensionService) {
        this.pensionService = pensionService;
    }

    @GetMapping
    public List<Pension> listarTodas() {
        return pensionService.listarTodas();
    }

    @GetMapping("/unidad/{unidadId}")
    public List<Pension> listarPorUnidad(@PathVariable Long unidadId) {
        return pensionService.listarPorUnidad(unidadId);
    }

    @PostMapping
    public Pension crearPension(@RequestBody @Valid Pension pension) {
        return pensionService.guardar(pension);
    }

    @DeleteMapping("/{id}")
    public void eliminarPension(@PathVariable Long id) {
        pensionService.eliminar(id);
    }
}
