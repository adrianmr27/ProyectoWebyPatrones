
package com.proyecto.service.impl;

import com.proyecto.dao.LoginDao;
import com.proyecto.service.LoginService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class LoginServiceImpl implements LoginService{
    @Autowired
    private LoginDao loginDao;
}
