package com.adavec.prefacturacion.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "traslados")
@Data

public class Traslado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origen_id")
    @NotNull
    private Ubicacion origen;
    @Column(name = "origen")
    private String origenNombre;

    @ManyToOne
    @JoinColumn(name = "destino_id")
    @NotNull
    private Ubicacion destino;
    @Column(name = "destino")
    private String destinoNombre;

    @NotNull(message = "La fecha de salida es obligatoria")
    private LocalDate fechaSalida;

    @NotNull(message = "La fecha de llegada es obligatoria")
    private LocalDate fechaLlegada;

    private Double kilometrosRecorridos;

    private String observaciones;

    @NotNull(message = "El rango de tarifa es obligatorio")
    private Integer rangoTarifa;
    @ManyToOne
    @JoinColumn(name = "unidad_id", nullable = false)
    @NotNull(message = "La unidad es obligatoria")
    private Unidad unidad;
    @OneToMany(mappedBy = "traslado", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Cobros> cobros;

}
