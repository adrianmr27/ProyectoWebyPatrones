package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Table(name = "curso")
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nombre;
    private int cantidadSesiones;

    @ManyToOne
    @JoinColumn(name = "profesor_id")
    private Profesor profesor;
    
    @Transient
    private Periodo periodo; // Campo transitorio para almacenar el período

    public Curso() {
    }

    public Curso(String nombre, int cantidadSesiones, Profesor profesor) {
        this.nombre = nombre;
        this.cantidadSesiones = cantidadSesiones;
        this.profesor = profesor;
    }


}