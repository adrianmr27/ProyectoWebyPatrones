package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Table(name = "suscripcion_plan")
public class Planes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan")
    private Long idPlan;
    private String nombre;
    private double precio;
    private int limiteEstudiantes;
    private String descripcion;
    private boolean soporteCorreo;
    private boolean soportePersonalizado;
    private boolean almacenamientoNube;
    private boolean reportesAvanzados;
    private boolean accesoCentroAyuda;

    public Planes() {
    }

    public Planes(String nombre, double precio, int limiteEstudiantes, String descripcion, boolean soporteCorreo, boolean soportePersonalizado, boolean almacenamientoNube, boolean reportesAvanzados, boolean accesoCentroAyuda) {
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
