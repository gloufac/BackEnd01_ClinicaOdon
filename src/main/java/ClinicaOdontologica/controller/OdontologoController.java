package ClinicaOdontologica.controller;

import ClinicaOdontologica.ClinicaOdontologicaApplication;
import ClinicaOdontologica.OdontologoTestService;
import ClinicaOdontologica.model.Odontologo;
import ClinicaOdontologica.service.OdontologoService;
import ClinicaOdontologica.service.OdontologoServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@Controller //simular ser un controller, sin tecnologia de vista
@RequestMapping("odontologos")
public class OdontologoController {

    private static final Logger logger = Logger.getLogger(ClinicaOdontologicaApplication.class);

    private final OdontologoServiceImpl odontologoService = new OdontologoServiceImpl();
//    @Autowired
//    public OdontologoController(OdontologoService odontologoService) {
//        this.odontologoService = odontologoService;
//    }

    @GetMapping("/sas")
    public Odontologo crearUsuario(){
        return new Odontologo("JAJA", "lol", "Mena");
    }

    @RequestMapping("/lista")
    public List<Odontologo> getOdontologos() {
        return odontologoService.listaOdontologos();
    }

    @RequestMapping("/matricula")
    public String buscarOdontologoPorId(Model model, @RequestParam("id") int id){
        logger.info("está recibiendo el id: "+ id);
        Odontologo odontologo = odontologoService.buscarPorId(id);
        model.addAttribute("matricula", odontologo.getNumeroMatricula());
        logger.info("matricula "+ odontologo.getNumeroMatricula());
        return "index";
    }

}
