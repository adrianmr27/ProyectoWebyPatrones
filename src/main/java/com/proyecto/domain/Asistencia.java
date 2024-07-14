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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "matriculaid")
    private Matricula matriculaid;

    private Date fecha;

    public Asistencia() {}

    public Asistencia(Matricula matriculaid, Date fecha) {
        this.matriculaid = matriculaid;
        this.fecha = fecha;
    }
}
