package ClinicaOdontologica.controller;

import ClinicaOdontologica.model.Paciente;
import ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    /**
     * Crear un nuevo paciente
     * @param paciente
     * @return paciente
     */
    @PostMapping
    public ResponseEntity<Paciente> crearPaciente(@RequestBody Paciente paciente) {
        if(paciente != null && !paciente.getNombre().isEmpty() && !paciente.getApellido().isEmpty()
                && !paciente.getCedula().isEmpty() && !paciente.getEmail().isEmpty()){
            return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Actualizar datos de un paciente
     * @param paciente
     * @return
     */
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) {

        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(paciente.getId()); //necesitamos primeramente validar si existe o  no
        if (pacienteBuscado.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("El paciente se ha actualizado");
        } else {
            return ResponseEntity.badRequest().body("No se encontro paciente");
        }
    }

    /**
     * Buscar paciente por el ID
     * @param id
     * @return paciente
     */
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(id);
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<Paciente> buscarPacientePorEmail(@PathVariable String email) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorEmail(email);
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Eliminar paciente por el ID
     * @param id
     * @return String
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {
        if (id > 0) {
            Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(id);
            if(pacienteBuscado.isPresent()){
                pacienteService.eliminarPaciente(id);
                return ResponseEntity.ok("El paciente se ha eliminado");
            }
        }
        return ResponseEntity.badRequest().body("Id no válido o Paciente no encontrado");
    }

    /**
     * Listar todos los pacientes
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<Paciente>> buscarTodos() {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        return ResponseEntity.ok(pacientes);
    }
}

