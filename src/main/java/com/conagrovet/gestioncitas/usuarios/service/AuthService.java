package com.conagrovet.gestioncitas.usuarios.service;

import com.conagrovet.gestioncitas.usuarios.dto.ResponseLoginDto;

public interface AuthService {
    ResponseLoginDto login(String email, String password);
}
