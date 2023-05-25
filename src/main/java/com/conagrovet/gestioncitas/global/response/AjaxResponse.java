package com.conagrovet.gestioncitas.global.response;

import com.conagrovet.gestioncitas.global.Cuerpo.Cuerpo;
import lombok.Data;

@Data
public class AjaxResponse {
    private String estado;
    private String mensaje;
    private Cuerpo data;

}
