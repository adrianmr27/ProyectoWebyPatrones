package com.proyecto.domain;
import jakarta.persistence.*;
import java.io.Serializable;
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
    private int anno;
    private int cuatrimestre;

    public Periodo(int anno, int cuatrimestre) {
        this.anno = anno;
        this.cuatrimestre = cuatrimestre;
    }

    public Periodo() {
    }
    
    
}
