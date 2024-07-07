package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Table(name="profesor")

public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Persona personaid;
    private String usuario;
    private String clave;

    public Profesor(Persona personaid, String usuario, String clave) {
        this.personaid = personaid;
        this.usuario = usuario;
        this.clave = clave;
    }

}
