package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;


@Entity
@Data
@Table(name = "asistencia")
public class Asistencia implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "matricula_id")
    private Matricula matricula;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "asistio")
    private Boolean asistio;


    public Asistencia() {}

    public Asistencia(Matricula matricula, Date fecha, Boolean asistio) {
        this.matricula = matricula;
        this.fecha = fecha;
        this.asistio = asistio;
    }
    
    
}

