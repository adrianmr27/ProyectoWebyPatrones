
package com.proyecto.dao;

import com.proyecto.domain.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SuscripcionDao extends JpaRepository<Suscripcion, Long> {
    
}
