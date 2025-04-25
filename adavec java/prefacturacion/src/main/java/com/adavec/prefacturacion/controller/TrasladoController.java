package com.adavec.prefacturacion.controller;

import com.adavec.prefacturacion.dto.CrearTrasladoDTO;
import com.adavec.prefacturacion.dto.TrasladoDTO;
import com.adavec.prefacturacion.model.Traslado;
import com.adavec.prefacturacion.service.TrasladoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/api/traslados")
public class TrasladoController {

    private final TrasladoService trasladoService;

    public TrasladoController(TrasladoService trasladoService) {
        this.trasladoService = trasladoService;
    }

    @GetMapping
    public List<TrasladoDTO> listarTraslados() {
        return trasladoService.listarTodos();
    }



    @PostMapping
    public Traslado crearTraslado(@RequestBody @Valid Traslado traslado) {
        return trasladoService.guardar(traslado);
    }
    @PostMapping("/crear-dto")
    public ResponseEntity<TrasladoDTO> crearTrasladoDesdeDTO(@RequestBody @Valid CrearTrasladoDTO dto) {
        Traslado trasladoGuardado = trasladoService.crearTrasladoDesdeDTO(dto);
        TrasladoDTO trasladoDTO = trasladoService.listarTodos().stream()
                .filter(t -> t.getId().equals(trasladoGuardado.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Traslado creado pero no encontrado"));

        return ResponseEntity.status(HttpStatus.CREATED).body(trasladoDTO);
    }

}
