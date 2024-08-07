package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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


    @Column(name = "ano")
    private int ano;

    @Column(name = "cuatrimestre")
    private int cuatrimestre;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

//    @OneToMany(mappedBy = "periodo")
//    private List<Matricula> matriculas;

    public Periodo() {
    }

    public Periodo(int ano, int cuatrimestre, Date fechaInicio, Date fechaFin) {
        this.ano = ano;
        this.cuatrimestre = cuatrimestre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
}