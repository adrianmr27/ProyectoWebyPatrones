package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Table(name = "planes")
public class Planes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String nombre;
    private double precio;
    private int limiteEstudiantes;
    private String descripcion;
    private Boolean soporteCorreo;
    private Boolean soportePersonalizado;
    private Boolean almacenamientoNube;
    private Boolean reportesAvanzados;
    private Boolean accesoCentroAyuda;

    public Planes() {
    }

    public Planes(String nombre, double precio, int limiteEstudiantes, String descripcion, Boolean soporteCorreo, Boolean soportePersonalizado, Boolean almacenamientoNube, Boolean reportesAvanzados, Boolean accesoCentroAyuda) {
        this.nombre = nombre;
        this.precio = precio;
        this.limiteEstudiantes = limiteEstudiantes;
        this.descripcion = descripcion;
        this.soporteCorreo = soporteCorreo;
        this.soportePersonalizado = soportePersonalizado;
        this.almacenamientoNube = almacenamientoNube;
        this.reportesAvanzados = reportesAvanzados;
        this.accesoCentroAyuda = accesoCentroAyuda;
    }
}
