package ClinicaOdontologica.controller;

import ClinicaOdontologica.exception.BadRequestException;
import ClinicaOdontologica.exception.ResourceNotFoundException;
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
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        if(!odontologo.getNombre().isEmpty() && !odontologo.getApellido().isEmpty() && !odontologo.getNumeroMatricula().isEmpty()){
            Optional<Odontologo> odontologoPorMatricula = odontologoService.buscarOdontologPorMatricula(odontologo.getNumeroMatricula());
            if(odontologoPorMatricula.isPresent()){
                throw new BadRequestException("Odontologo con matricula: " + odontologo.getNumeroMatricula() + " ya existe");
            }
            return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
        } else {
            throw new BadRequestException("Campos requeridos para crear Odontologo: Nombre, Apellido, Numero Matricula");
        }
    }

    /**
     * Actualizar datos del odontologo
     * @param odontologo
     * @return
     */
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException, BadRequestException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(odontologo.getId());
        if (odontologoBuscado.isPresent()) {
            if(!odontologo.getNombre().isEmpty() && !odontologo.getApellido().isEmpty() && !odontologo.getNumeroMatricula().isEmpty()){
                if(odontologoService.existeOdontologoPorMatriculaId(odontologo.getNumeroMatricula(), odontologo.getId())){
                    throw new BadRequestException("Odontologo con matricula: " + odontologo.getNumeroMatricula() + " ya existe en otro odontologo");
                }
                odontologoService.actualizarOdontologo(odontologo);
                return ResponseEntity.ok("Odontologo ha sido actualizado");
            }
            throw new BadRequestException("Campos requeridos para actualizar Odontologo: Nombre, Apellido, Numero Matricula");
        } else {
            throw new ResourceNotFoundException("No se encontró Odontologo con Id: " + odontologo.getId().toString() + " para actualizar");
        }
    }

    /**
     * Buscar el odontologo por ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(id);
        if (odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(odontologoBuscado.get());
        } else {
            throw new ResourceNotFoundException("Odontologo con Id: " + id.toString() + " no encontrado");
        }
    }

    /**
     * Eliminar odontologo por el ID
     * @param id
     * @return String
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        if (id > 0) {
            Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(id);
            if (odontologoBuscado.isPresent()) {
                odontologoService.eliminarOdontologo(id);
                return ResponseEntity.ok("Odontologo ha sido eliminado");
            }
        }
        throw new ResourceNotFoundException("Odontologo con Id: " + id + " no encontrado para eliminar");
    }

    @GetMapping()
    public ResponseEntity<List<Odontologo>> buscarTodos() {
        List<Odontologo> lista = odontologoService.buscarOdontologos();
        return ResponseEntity.ok(lista);
    }
}
