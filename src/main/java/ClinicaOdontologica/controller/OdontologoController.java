package ClinicaOdontologica.controller;

import ClinicaOdontologica.model.Odontologo;
import ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    @Autowired
    private final OdontologoService odontologoService;

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
            return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(odontologo.getId());
        if (odontologoBuscado.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo ha sido actualizado");
        } else {
            return ResponseEntity.badRequest().body("Odontologo no encontrado para actualizar");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(id);
        if (odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(odontologoBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) {
        if (id > 0) {
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(id);
            if (odontologoBuscado.isPresent()) {
                odontologoService.eliminarOdontologo(id);
                return ResponseEntity.ok("Odontologo ha sido eliminado");
            }
        }
        return ResponseEntity.badRequest().body("Odontologo no encontrado para eliminar");
    }

    @GetMapping()
    public ResponseEntity<List<Odontologo>> buscarTodos() {
        List<Odontologo> lista = odontologoService.buscarOdontologos();
        return ResponseEntity.ok(lista);
    }
}
