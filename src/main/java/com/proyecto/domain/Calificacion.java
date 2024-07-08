package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Table(name="calificacion")
public class Calificacion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Matricula matriculaid;

    private Float laboratorio1;
    private Float laboratorio2;
    private Float quiz1;
    private Float quiz2;
    private Float examen1;
    private Float examen2;
    private Float proyecto;

    public Calificacion() {
        // Constructor sin argumentos
    }

    public Calificacion(Matricula matriculaid, Float laboratorio1, Float laboratorio2, Float quiz1, Float quiz2, Float examen1, Float examen2, Float proyecto) {
        this.matriculaid = matriculaid;
        this.laboratorio1 = laboratorio1;
        this.laboratorio2 = laboratorio2;
        this.quiz1 = quiz1;
        this.quiz2 = quiz2;
        this.examen1 = examen1;
        this.examen2 = examen2;
        this.proyecto = proyecto;
    }
}
