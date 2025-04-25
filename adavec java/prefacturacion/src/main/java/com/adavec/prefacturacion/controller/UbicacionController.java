package com.adavec.prefacturacion.controller;

import com.adavec.prefacturacion.model.Ubicacion;
import com.adavec.prefacturacion.service.UbicacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ubicaciones")
@CrossOrigin(origins = "*")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    @GetMapping
    public List<Ubicacion> listar() {
        return ubicacionService.listarTodas();
    }
}
