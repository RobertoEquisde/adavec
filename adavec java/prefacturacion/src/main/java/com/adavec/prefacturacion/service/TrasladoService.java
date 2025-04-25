package com.adavec.prefacturacion.service;

import com.adavec.prefacturacion.dto.CrearTrasladoDTO;
import com.adavec.prefacturacion.dto.TrasladoDTO;
import com.adavec.prefacturacion.model.*;
import com.adavec.prefacturacion.repository.*;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrasladoService {

    private final UnidadRepository unidadRepository;
    private final TrasladoRepository trasladoRepository;
    private final UbicacionRepository ubicacionRepository;
    private final CobrosRepository CobrosRepository;

    public TrasladoService(
            UnidadRepository unidadRepository,
            TrasladoRepository trasladoRepository,
            UbicacionRepository ubicacionRepository,
            CobrosRepository CobrosRepository
    ) {
        this.unidadRepository = unidadRepository;
        this.trasladoRepository = trasladoRepository;
        this.ubicacionRepository = ubicacionRepository;
        this.CobrosRepository = CobrosRepository;
    }

    public List<TrasladoDTO> listarTodos() {
        return trasladoRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<Traslado> listarPorUnidad(Unidad unidad) {
        return trasladoRepository.findByUnidad(unidad);
    }

    public List<Traslado> listarPorProveedor(Proveedor proveedor) {
        List<Unidad> unidades = unidadRepository.findByProveedor(proveedor);
        List<Traslado> traslados = new ArrayList<>();

        for (Unidad unidad : unidades) {
            traslados.addAll(trasladoRepository.findByUnidad(unidad));
        }

        return traslados;
    }

    public Traslado guardar(Traslado traslado) {
        return trasladoRepository.save(traslado);
    }

    private TrasladoDTO mapToDTO(Traslado traslado) {
        TrasladoDTO dto = new TrasladoDTO();
        dto.setId(traslado.getId());
        dto.setOrigen(traslado.getOrigen().getNombre());
        dto.setOrigenId(traslado.getOrigen().getId());
        dto.setDestino(traslado.getDestino().getNombre());
        dto.setDestinoId(traslado.getDestino().getId());
        dto.setFechaSalida(traslado.getFechaSalida());
        dto.setFechaLlegada(traslado.getFechaLlegada());
        dto.setKilometrosRecorridos(traslado.getKilometrosRecorridos());
        dto.setObservaciones(traslado.getObservaciones());
        dto.setRangoTarifa(traslado.getRangoTarifa());
        dto.setUnidad(traslado.getUnidad());
        return dto;
    }

    private int calcularRangoDesdeKilometros(double km) {
        if (km < 0) {
            throw new IllegalArgumentException("Los kilómetros no pueden ser negativos");
        }

        if (km >= 0 && km <= 460) return 1;
        if (km > 460 && km <= 930) return 2;
        if (km > 930 && km <= 1390) return 3;
        if (km > 1390 && km <=1850) return 4;
        return 5;

    }


    public Traslado crearTrasladoDesdeDTO(CrearTrasladoDTO dto) {
        Ubicacion origen = ubicacionRepository.findById(dto.getOrigenId())
                .orElseThrow(() -> new IllegalArgumentException("Origen no encontrado"));
        Ubicacion destino = ubicacionRepository.findById(dto.getDestinoId())
                .orElseThrow(() -> new IllegalArgumentException("Destino no encontrado"));
        Unidad unidad = unidadRepository.findById(dto.getUnidadId())
                .orElseThrow(() -> new IllegalArgumentException("Unidad no encontrada"));

        int rango = calcularRangoDesdeKilometros(dto.getKilometrosRecorridos());

        Traslado traslado = new Traslado();
        traslado.setOrigen(origen);
        traslado.setDestino(destino);
        traslado.setOrigenNombre(origen.getNombre());
        traslado.setDestinoNombre(destino.getNombre());
        traslado.setFechaSalida(dto.getFechaSalida());
        traslado.setFechaLlegada(dto.getFechaLlegada());
        traslado.setKilometrosRecorridos(dto.getKilometrosRecorridos());
        traslado.setObservaciones(dto.getObservaciones());
        traslado.setUnidad(unidad);
        traslado.setRangoTarifa(rango);

        traslado = trasladoRepository.save(traslado);

        // Crear registro en cobros

        Cobros cobro = new Cobros();
        double tarifaFinal = switch (traslado.getRangoTarifa()) {
            case 1 -> 3800.0;
            case 2 -> 7200.0;
            case 3 -> 11220.0;
            case 4 -> 13600.0;
            case 5 -> 16600.0;
            default -> 0.0;
        };
        cobro.setTarifaUnica(tarifaFinal);
        cobro.setFecha(dto.getFechaSalida());
        cobro.setTraslado(traslado);

        CobrosRepository.save(cobro);

        return traslado;
    }
}
