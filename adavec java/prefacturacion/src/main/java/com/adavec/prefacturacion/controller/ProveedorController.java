package com.adavec.prefacturacion.controller;

import com.adavec.prefacturacion.model.Proveedor;
import com.adavec.prefacturacion.model.Traslado;
import com.adavec.prefacturacion.model.Unidad;
import com.adavec.prefacturacion.repository.ProveedorRepository;
import com.adavec.prefacturacion.service.ProveedorService;
import com.adavec.prefacturacion.service.TrasladoService;
import com.adavec.prefacturacion.service.UnidadService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedorController {
    private final ProveedorService proveedorService;
    private final UnidadService unidadService;
    private final ProveedorRepository proveedorRepository;
    private final TrasladoService trasladoService;


    public ProveedorController(
            UnidadService unidadService,
            ProveedorRepository proveedorRepository,
            ProveedorService proveedorService,
            TrasladoService trasladoService) {
        this.unidadService = unidadService;
        this.proveedorRepository = proveedorRepository;
        this.proveedorService = proveedorService;
        this.trasladoService = trasladoService;
    }


    @GetMapping("/{id}/unidades")
    public List<Unidad> obtenerUnidadesPorProveedor(@PathVariable Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));

        return unidadService.listarPorProveedor(proveedor);
    }
    @GetMapping("/{id}/traslados")
    public List<Traslado> obtenerTrasladosPorProveedor(@PathVariable Long id) {
        Proveedor proveedor = proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con ID: " + id));

        return trasladoService.listarPorProveedor(proveedor);
    }
    @GetMapping
    public List<Proveedor> listarProveedores() {
        return proveedorService.listarTodos();
    }

    @PostMapping
    public Proveedor crearProveedor(@RequestBody @Valid Proveedor proveedor) {
        return proveedorService.guardar(proveedor);
    }
}
