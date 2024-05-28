package ClinicaOdontologica.controller;

import ClinicaOdontologica.dao.BaseDatos;
import ClinicaOdontologica.model.Odontologo;
import ClinicaOdontologica.model.Paciente;
import ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController //simular ser un controller, sin tecnologia de vista
@RequestMapping("/odontologos")
public class OdontologoController {

    private final OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
        BaseDatos.crearTablas();
    }


    @PostMapping
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo) {
        return odontologoService.crear(odontologo);
    }

    @PutMapping
    public String actualizarOdontologo(@RequestBody Odontologo odontologo) {
        Odontologo buscado = odontologoService.buscarPorId(odontologo.getId());
        if (buscado != null) {
            odontologoService.actualizar(odontologo);
            return "Odontologo actualizado";
        } else {
            return "Odontologo no encontrado";
        }
    }

    // clase 27 mayo
    @GetMapping("/{id}")
    public Odontologo buscarPorId(@PathVariable Integer id) {
        if (id > 0) {
            Odontologo buscado = odontologoService.buscarPorId(id);
            return buscado;
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String eliminarOdontologo(@PathVariable Integer id) {
        if (id > 0) {
            odontologoService.eliminar(id);
            return "Odontologo eliminado";
        } else {
            return "Id no valido para odontologo";
        }
    }

    @GetMapping()
    public List<Odontologo> buscarTodos() {
        List<Odontologo> lista = odontologoService.buscarTodos();
        return lista;
    }

}
