package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
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

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cantidad_sesiones")
    private Integer cantidadSesiones;

    @Column(name = "profesor_id")
    private Long profesorId; // Asumiendo que Profesor es otro objeto o simplemente una relación

    @OneToMany(mappedBy = "curso")
    private List<Matricula> matriculas;
    
//    @ManyToOne
//    @JoinColumn(name = "periodo_id")
//    private Periodo periodo; // Relación con Periodo

    public Curso() {
    }

    public Curso(String nombre, int cantidadSesiones, Long profesorId) {
        this.nombre = nombre;
        this.cantidadSesiones = cantidadSesiones;
        this.profesorId = profesorId;
    }


}