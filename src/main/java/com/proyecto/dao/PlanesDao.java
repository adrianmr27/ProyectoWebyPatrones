
package com.proyecto.dao;

import com.proyecto.domain.Planes;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author andrj
 */
public interface PlanesDao extends JpaRepository<Planes, Long> {
    
}
