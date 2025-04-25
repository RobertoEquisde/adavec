package com.adavec.prefacturacion.controller;

import com.adavec.prefacturacion.model.Traslado;
import com.adavec.prefacturacion.model.Unidad;
import com.adavec.prefacturacion.repository.UnidadRepository;
import com.adavec.prefacturacion.service.TrasladoService;
import com.adavec.prefacturacion.service.UnidadService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidades")
public class UnidadController {

    private final UnidadService unidadService;
    private final TrasladoService trasladoService;
    private final UnidadRepository unidadRepository;

    public UnidadController(UnidadService unidadService, TrasladoService trasladoService, UnidadRepository unidadRepository) {
        this.unidadService = unidadService;
        this.trasladoService = trasladoService;
        this.unidadRepository = unidadRepository;
    }

    @GetMapping
    public List<Unidad> obtenerUnidades() {
        return unidadService.listarTodas();
    }
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/{id}")
    public Unidad obtenerUnidadPorId(@PathVariable Long id) {
        return unidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidad no encontrada con ID: " + id));
    }

    @PostMapping
    public Unidad crearUnidad(@RequestBody @Valid Unidad unidad) {
        return unidadService.guardar(unidad);
    }
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/{id}/traslados")
    public List<Traslado> obtenerTrasladosPorUnidad(@PathVariable Long id) {
        Unidad unidad = unidadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidad no encontrada con ID: " + id));
        System.out.println("✅ Se está llamando a /api/unidades/" + id + "/traslados");
        return trasladoService.listarPorUnidad(unidad);
    }

}
