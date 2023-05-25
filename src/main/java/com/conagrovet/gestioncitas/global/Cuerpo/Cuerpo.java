package com.conagrovet.gestioncitas.global.Cuerpo;

import com.conagrovet.gestioncitas.usuarios.dto.UsuarioDto;
import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import lombok.Data;

@Data
public class Cuerpo {
    private Usuario usuario;
    private Boolean m_boolean;
    private UsuarioDto usuarioDto;
}
