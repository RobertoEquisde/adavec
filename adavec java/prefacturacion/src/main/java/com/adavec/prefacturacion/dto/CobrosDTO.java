package com.adavec.prefacturacion.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CobrosDTO {
    private Long id;
    private String tipoCobro; // Ej. "Gasolina", "Reparación", "Tarifa Única"
    private Double monto;
    private LocalDate fecha;
    private String observaciones;
}
