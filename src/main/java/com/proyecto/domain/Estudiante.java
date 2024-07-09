package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name="estudiante")
public class Estudiante implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @OneToOne
    @JoinColumn(name = "personaid")
    private Persona persona;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante")
    private List<Matricula> matriculas;
    
    public Estudiante() {
    }

    public Estudiante(Persona persona) {
        this.persona = persona;
    }

    public Estudiante(Persona persona, List<Matricula> matriculas) {
        this.persona = persona;
        this.matriculas = matriculas;
    }

    
}
