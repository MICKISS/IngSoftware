package com.proyectoFinal.proyectoFinal.Controllers;

import com.proyectoFinal.proyectoFinal.Services.FileServices;
import com.proyectoFinal.proyectoFinal.dao.UsuarioDao;
import com.proyectoFinal.proyectoFinal.model.ExcelCabecera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class UploadFileController {

    @Autowired
    FileServices fileServices;

//    @GetMapping("/")
//    public String index() {
//        return "multipartfile/uploadform.html";
//    }

    @RequestMapping(value = "api/excel/upload", method = RequestMethod.POST)
    public String uploadMultipartFile(@RequestBody MultipartFile file) {
        String mensaje = "";
        try {
            String result = fileServices.store(file);
            if (result == "false") {
                mensaje = "Archivo subido correctamente...";
            }
            if (result=="true"){
                mensaje="El archivo no pudo subirse porque ya se encuentra un archivo con la misma referencia";
            }
        } catch (Exception e) {
            mensaje = "El archivo no pudo subirse valide el formato e intente de nuevo";
        }
        return mensaje;
    }


}