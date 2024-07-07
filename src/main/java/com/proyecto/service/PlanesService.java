
package com.proyecto.service;

import com.proyecto.domain.Planes;
import java.util.List;

public interface PlanesService {
    public List<Planes> getPlanes();
    
    public Planes getPlanes(Planes plan);
    
    public void save(Planes plan);
   
    public void delete(Planes plan);
    
}
