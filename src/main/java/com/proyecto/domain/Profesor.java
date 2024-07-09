package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name="profesor")
public class Profesor extends Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    private String usuario;
    private String clave;

    public Profesor() {
        super();
    }


    public Profesor(String nombre, String apellido1, String apellido2, String correo, Integer telefono, String usuario, String clave) {
        super(nombre, apellido1, apellido2, correo, telefono);
        this.usuario = usuario;
        this.clave = clave;
    }
}
