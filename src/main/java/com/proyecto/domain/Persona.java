package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Entity
@Data
@Table(name = "persona")
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private int identificacion;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String correo;
    private String telefono;

    public Persona() {
    }

    public Persona(int identificacion, String nombre, String apellido1, String apellido2, String correo, String telefono) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correo = correo;
        this.telefono = telefono;
    }
}
