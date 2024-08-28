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

    @Column(name = "aprobado")
    private Boolean aprobado;

    @Column(name = "evaluacion1")
    private Float evaluacion1;

    @Column(name = "evaluacion2")
    private Float evaluacion2;

    @Column(name = "evaluacion3")
    private Float evaluacion3;

    @Column(name = "nota_final")
    private Float notaFinal;

    @ManyToOne
    @JoinColumn(name = "matricula_id")
    private Matricula matriculaId;

    public Calificacion() {
        // Constructor sin argumentos
    }

    public Calificacion(Boolean aprobado, Float evaluacion1, Float evaluacion2, Float evaluacion3, Float notaFinal, Matricula matriculaId) {
        this.aprobado = aprobado;
        this.evaluacion1 = evaluacion1;
        this.evaluacion2 = evaluacion2;
        this.evaluacion3 = evaluacion3;
        this.notaFinal = notaFinal;
        this.matriculaId = matriculaId;
    }
}
