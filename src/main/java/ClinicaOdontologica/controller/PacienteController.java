package ClinicaOdontologica.controller;

import ClinicaOdontologica.model.Paciente;
import ClinicaOdontologica.service.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController() {
        pacienteService = new PacienteService();
    }

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
        Paciente pacienteBuscado = pacienteService.buscarPorID(paciente.getId()); //necesitamos primeramente validar si existe o  no
        if (pacienteBuscado != null && pacienteBuscado.getId() > 0) {
            pacienteService.actualizar(paciente);
            return ResponseEntity.ok("El paciente se ha actualizado");
        } else {
            return ResponseEntity.badRequest().body("paciente no encontrado");
        }

    }

    /**
     * Buscar paciente por el ID
     * @param id
     * @return paciente
     */
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Integer id) {
        if (id > 0) {
            Paciente pacienteBuscado = pacienteService.buscarPorID(id);
            return ResponseEntity.ok(pacienteBuscado);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Eliminar paciente por el ID
     * @param id
     * @return String
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Integer id) {
        if (id > 0) {
            Paciente pacienteBuscado = pacienteService.buscarPorID(id);
            if(pacienteBuscado != null && pacienteBuscado.getId() > 0){
                pacienteService.eliminar(id);
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

