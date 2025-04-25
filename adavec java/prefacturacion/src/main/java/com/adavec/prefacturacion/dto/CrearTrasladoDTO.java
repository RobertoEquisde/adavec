package com.adavec.prefacturacion.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CrearTrasladoDTO {

    @NotNull(message = "El ID de origen es obligatorio")
    private Long origenId;

    @NotNull(message = "El ID de destino es obligatorio")
    private Long destinoId;

    @NotNull(message = "La fecha de salida es obligatoria")
    private LocalDate fechaSalida;

    @NotNull(message = "La fecha de llegada es obligatoria")
    private LocalDate fechaLlegada;

    @NotNull(message = "Los kilómetros recorridos son obligatorios")
    private Double kilometrosRecorridos;

    private String observaciones;

    @NotNull(message = "El ID de la unidad es obligatorio")
    private Long unidadId;
}
