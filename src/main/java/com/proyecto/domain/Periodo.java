package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Entity
@Data
@Table(name="periodo")
public class Periodo implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private int ano;
    private int cuatrimestre;
    private Date fechaInicio;
    private Date fechaFin;

    public Periodo() {
    }

    public Periodo(int ano, int cuatrimestre, Date fechaInicio, Date fechaFin) {
        this.ano = ano;
        this.cuatrimestre = cuatrimestre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
}