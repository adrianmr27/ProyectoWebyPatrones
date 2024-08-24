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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "periodo_id")
    private Periodo periodo;
    
    public Matricula() {
    }

    public Matricula(Long id) {
        this.id = id;
    }
    

    public Matricula(Estudiante estudiante, Curso curso, Periodo periodo) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.periodo = periodo;
    }
    
    // Métodos adicionales para obtener el nombre completo del estudiante y otros datos
    public String getNombreCompletoEstudiante() {
        Persona persona = estudiante.getPersona();
        return persona.getNombre() + " " + persona.getApellido1() + " " + persona.getApellido2();
    }
    
    public String getNombreCurso() {
        return curso.getNombre();
    }
    
    public String getAnoPeriodo() {
        return String.valueOf(periodo.getAno());
    }

    public String getCuatrimestrePeriodo() {
        return String.valueOf(periodo.getCuatrimestre());
    }
}