package com.proyecto.service.impl;

import com.proyecto.dao.PeriodoDao;
import com.proyecto.domain.Periodo;
import com.proyecto.service.PeriodoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PeriodoServiceImpl implements PeriodoService {

    @Autowired
    private PeriodoDao periodoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Periodo> getPeriodos() {
        return periodoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Periodo getPeriodo(Periodo periodo) {
        return periodoDao.findById(periodo.getId())
                .orElse(null);
    }

    @Override
    @Transactional
    public void save(Periodo periodo) {
        periodoDao.save(periodo);
    }

    @Override
    @Transactional
    public void delete(Periodo periodo) {
        periodoDao.delete(periodo);
    }
}