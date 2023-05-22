package com.conagrovet.gestioncitas.global.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.invoker.HttpClientAdapter;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Util {
    private String apiUrl =  "https://conagrovet.utp-sistemas.com/service-conagrovet/login";
    private String directorioImagen = "";

    public static String guardarArchivo(MultipartFile multiPart, String ruta){
        String nombreOriginal= multiPart.getOriginalFilename();
        try {
            File imageFile = new File(ruta + nombreOriginal);
            multiPart.transferTo(imageFile);
            return nombreOriginal;
        } catch (IOException e) {
            return null;
        }
    }
}
