package com.proyecto.service;

import com.proyecto.domain.Periodo;
import java.util.List;

public interface PeriodoService {
    
    public List<Periodo> getPeriodos();
    
    public Periodo getPeriodo(Periodo periodo);
    
    public void save(Periodo periodo);
   
    public void delete(Periodo periodo);
}
