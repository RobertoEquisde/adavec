package com.adavec.prefacturacion.dto;

import com.adavec.prefacturacion.model.Unidad;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TrasladoDTO {

    private Long id;
    private Long origenId;
    private Long destinoId;
    private String origen;
    private String destino;
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
    private Double kilometrosRecorridos;
    private String observaciones;
    private Integer rangoTarifa;
    private Double tarifaCalculada;

    private Unidad unidad;

    public Double getTarifaCalculada() {
        return calcularTarifaDesdeRango(this.rangoTarifa);
    }

    private Double calcularTarifaDesdeRango(Integer rango) {
        return switch (rango) {
            case 1 -> 3800.0;
            case 2 -> 7200.0;
            case 3 -> 11220.0;
            case 4 -> 13600.0;
            case 5 -> 16600.0;
            default -> 0.0;
        };
    }
}
