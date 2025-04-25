package com.adavec.prefacturacion.controller;

import com.adavec.prefacturacion.model.Cobros;
import com.adavec.prefacturacion.model.Traslado;
import com.adavec.prefacturacion.repository.TrasladoRepository;
import com.adavec.prefacturacion.service.CobrosService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/cobros")
public class CobrosController {

    private final CobrosService cobrosService;
    private final TrasladoRepository trasladoRepository;

    public CobrosController(CobrosService cobrosService, TrasladoRepository trasladoRepository) {
        this.cobrosService = cobrosService;
        this.trasladoRepository = trasladoRepository;
    }

    @GetMapping
    public List<Cobros> listarTodos() {
        return cobrosService.listarTodos();
    }

    @GetMapping("/{id}")
    public Cobros obtenerPorId(@PathVariable Long id) {
        return cobrosService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Cobro no encontrado con ID: " + id));
    }

    @GetMapping("/traslado/{trasladoId}")
    public List<Cobros> listarPorTraslado(@PathVariable Long trasladoId) {
        Traslado traslado = trasladoRepository.findById(trasladoId)
                .orElseThrow(() -> new RuntimeException("Traslado no encontrado con ID: " + trasladoId));
        return traslado.getCobros(); // Asumiendo que en Traslado tienes getCobros()
    }

    @PostMapping("/traslado/{trasladoId}")
    public Cobros crearCobro(@PathVariable Long trasladoId, @RequestBody Cobros cobro) {
        Traslado traslado = trasladoRepository.findById(trasladoId)
                .orElseThrow(() -> new RuntimeException("Traslado no encontrado con ID: " + trasladoId));
        cobro.setTraslado(traslado);
        return cobrosService.guardar(cobro);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        cobrosService.eliminar(id);
    }
}
