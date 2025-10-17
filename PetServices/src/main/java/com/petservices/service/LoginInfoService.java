package com.petservices.service;

import com.petservices.mapper.LoginInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ${author}
 * @since 2025-02-23
 */
@Service
public class LoginInfoService {

    @Autowired
    LoginInfoMapper loginInfoMapper;

}
