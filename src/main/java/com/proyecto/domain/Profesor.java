package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Table(name = "profesor")
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "personaid", referencedColumnName = "id") // referencia el campo id de persona para la herencia 
    private Persona persona; //tipo persona
    
    private String usuario;
    private String clave;

    public Profesor() {
        // Constructor vacío necesario para JPA
    }

    public Profesor(Persona persona, String usuario, String clave) { //contructor de profesor con persona
        this.persona = persona;
        this.usuario = usuario;
        this.clave = clave;
    }
}