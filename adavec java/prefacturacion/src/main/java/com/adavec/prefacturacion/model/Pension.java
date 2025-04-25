package com.adavec.prefacturacion.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Table(name = "pensiones")
@Data
public class Pension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La fecha de reporte es obligatoria")
    private String fechaReporte;

    @NotBlank(message = "La fecha de autorización es obligatoria")
    private String fechaAutorizacion;

    @NotNull(message = "Los días son obligatorios")
    @Min(value = 0, message = "Días no puede ser negativo")
    private Integer dias;

    @NotNull(message = "El costo es obligatorio")
    @Min(value = 0, message = "El costo debe ser mayor o igual a cero")
    private Double costo;

    private Boolean patio;
    private Boolean ordenSalida;
    private Boolean tanque1;
    private Boolean tanque2;

    @ManyToOne
    @JoinColumn(name = "unidad_id", nullable = false)
    @NotNull(message = "La unidad es obligatoria")
    private Unidad unidad;
}
