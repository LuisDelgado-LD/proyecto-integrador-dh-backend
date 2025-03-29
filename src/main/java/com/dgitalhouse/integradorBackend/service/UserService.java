package com.dgitalhouse.integradorBackend.service;

import com.dgitalhouse.integradorBackend.DTO.userDTOS.UpdateUserRequest;
import com.dgitalhouse.integradorBackend.DTO.userDTOS.UserInfoRequest;
import com.dgitalhouse.integradorBackend.entity.Usuario;

public interface UserService {
    Usuario getUser(Long id);
    UserInfoRequest deleteUser(UserInfoRequest userInfoRequest);
    UpdateUserRequest updateUser(Long Id, UpdateUserRequest updateUserRequest);
}
