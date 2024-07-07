package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Table(name="matricula")

public class Matricula implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Estudiante estudianteid;

    @ManyToOne
    private Curso cursoid;

    public Matricula(Estudiante estudianteid, Curso cursoid) {
        this.estudianteid = estudianteid;
        this.cursoid = cursoid;
    }
    
    
}
