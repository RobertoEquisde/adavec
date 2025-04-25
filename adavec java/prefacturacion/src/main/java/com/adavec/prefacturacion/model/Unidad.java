package com.adavec.prefacturacion.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "unidades")
@Data
public class Unidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Las placas no pueden estar vacías")
    private String placas;

    @NotBlank(message = "El número económico es obligatorio")
    private String numeroEconomico;

    @NotBlank(message = "El tipo de unidad es obligatorio")
    private String tipoUnidad;
    @NotBlank(message = "Debes indicar a quién fue reportada la unidad")
    private String reportadoA;

    @Column(columnDefinition = "TEXT")
    private String comentarios;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @NotNull(message = "El año es obligatorio")
    @Min(value = 1900, message = "El año debe ser válido")
    private Integer anio;
    @ManyToOne
    @JoinColumn(name = "proveedor_id", nullable = false)
    @NotNull(message = "El proveedor es obligatorio")
    private Proveedor proveedor;
    // Relación con pensiones (historial o registro actual)
    @OneToMany(mappedBy = "unidad", cascade = CascadeType.ALL)
    private List<Pension> pensiones;
}
