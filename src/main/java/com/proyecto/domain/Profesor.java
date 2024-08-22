package com.proyecto.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import javax.validation.constraints.NotEmpty;
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
    @JoinColumn(name = "persona_id")
    private Persona persona;
    
    @NotEmpty
    private String usuario;
    @NotEmpty
    private String clave;

    @OneToOne
    @JoinColumn(name = "rol_id")  // Cambiado de "id" a "rol_id"
    private Rol rol;

    public Profesor() {
    }

    public Profesor(Persona persona, String usuario, String clave, Rol rol) {
        this.persona = persona;
        this.usuario = usuario;
        this.clave = clave;
        this.rol = rol;
    }
}
