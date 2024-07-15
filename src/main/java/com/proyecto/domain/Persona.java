package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED) //Para herencias
@Table(name = "persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id") // id es manual 
    private Long idPersona;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String correo;
    private Integer telefono;


    public Persona() {
        // Constructor vacío necesario para JPA
    }

    public Persona(Long idPersona, String nombre, String apellido1, String apellido2, String correo, Integer telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correo = correo;
        this.telefono = telefono;
    }

}
