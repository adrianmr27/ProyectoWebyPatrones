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
    
//    @OneToOne
//    @JoinColumn(name = "persona_id")
//    private Persona persona;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "persona_id")
    private Persona persona;

    private String estatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante")
    private List<Matricula> matriculas;
    
    public Estudiante() {
    }

    public Estudiante(Persona persona, String estatus) {
        this.persona = persona;
        this.estatus = estatus;
    }

    public Estudiante(Persona persona, String estatus, List<Matricula> matriculas) {
        this.persona = persona;
        this.estatus = estatus;
        this.matriculas = matriculas;
    }
}
