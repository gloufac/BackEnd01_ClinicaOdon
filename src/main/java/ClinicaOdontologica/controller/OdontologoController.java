package ClinicaOdontologica.controller;

import ClinicaOdontologica.dao.BaseDatos;
import ClinicaOdontologica.model.Odontologo;
import ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private final OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    /**
     * Crear nuevo odontologo
     * @param odontologo
     * @return
     */
    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) {
        if(!odontologo.getNombre().isEmpty() && !odontologo.getApellido().isEmpty() && !odontologo.getNumeroMatricula().isEmpty()){
            return ResponseEntity.ok(odontologoService.crear(odontologo));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        Odontologo buscado = odontologoService.buscarPorId(odontologo.getId());
        if (buscado != null && buscado.getId() > 0) {
            odontologoService.actualizar(odontologo);
            return ResponseEntity.ok("Odontologo ha sido actualizado");
        } else {
            return ResponseEntity.badRequest().body("Odontologo no encontrado para actualizar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id) {
        if (id > 0) {
            Odontologo buscado = odontologoService.buscarPorId(id);
            if(buscado != null && buscado.getId() > 0){
                return ResponseEntity.ok(buscado);
            }
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id) {
        if (id > 0) {
            Odontologo buscado = odontologoService.buscarPorId(id);
            if (buscado != null && buscado.getId() > 0) {
                odontologoService.eliminar(id);
                return ResponseEntity.ok("Odontologo ha sido eliminado");
            }
        }
        return ResponseEntity.badRequest().body("Odontologo no encontrado para eliminar");
    }

    @GetMapping()
    public ResponseEntity<List<Odontologo>> buscarTodos() {
        List<Odontologo> lista = odontologoService.buscarTodos();
        return ResponseEntity.ok(lista);
    }
}
