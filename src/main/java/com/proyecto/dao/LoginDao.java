
package com.proyecto.dao;
import com.proyecto.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginDao extends JpaRepository<Login, Long>{
    
}
