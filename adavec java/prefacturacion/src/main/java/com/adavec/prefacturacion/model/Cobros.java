package com.adavec.prefacturacion.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "cobros")
@Data
public class Cobros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double tarifaUnica;

    private Double combustible;

    private Double reparaciones;

    private Double seguro;

    private Double pension;

    private Double loderas;

    private Double otrosGastos;

    private LocalDate fecha;

    private String descripcion;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "traslado_id", nullable = false)
    private Traslado traslado;

    public Double getTotal() {
        return (tarifaUnica != null ? tarifaUnica : 0.0) +
                (combustible != null ? combustible : 0.0) +
                (reparaciones != null ? reparaciones : 0.0) +
                (seguro != null ? seguro : 0.0) +
                (pension != null ? pension : 0.0) +
                (loderas != null ? loderas : 0.0) +
                (otrosGastos != null ? otrosGastos : 0.0);
    }
}
