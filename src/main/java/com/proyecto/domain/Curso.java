package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Table(name="curso")

public class Curso implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String nombre;
    private int cantidadsesiones;

    @ManyToOne
    @JoinColumn(name = "periodoid")
    private Periodo periodo;

    public Curso(String nombre, int cantidadsesiones, Periodo periodo) {
        this.nombre = nombre;
        this.cantidadsesiones = cantidadsesiones;
        this.periodo = periodo;
    }

    public Curso() {
    }
    
    
}
