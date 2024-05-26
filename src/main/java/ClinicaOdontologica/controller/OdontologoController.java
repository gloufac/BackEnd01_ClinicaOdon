package ClinicaOdontologica.controller;

import ClinicaOdontologica.dao.BaseDatos;
import ClinicaOdontologica.model.Odontologo;
import ClinicaOdontologica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController //simular ser un controller, sin tecnologia de vista
@RequestMapping("odontologos")
public class OdontologoController {

    private final OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
        BaseDatos.crearTablas();
    }

    @GetMapping("/sas")
    public Odontologo crearUsuario(){
        return new Odontologo("JAJA", "lol", "Mena");
    }

    @GetMapping
    public List<Odontologo> getOdontologos() {
        return odontologoService.buscarTodos();
    }

}
