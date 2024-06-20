package ClinicaOdontologica.controller;

import ClinicaOdontologica.exception.BadRequestException;
import ClinicaOdontologica.exception.ResourceNotFoundException;
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
    public ResponseEntity<Paciente> crearPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        if(paciente != null && !paciente.getNombre().isEmpty() && !paciente.getApellido().isEmpty()
                && !paciente.getCedula().isEmpty() && !paciente.getEmail().isEmpty()){
            Optional<Paciente> pacienteBuscadoEmail = pacienteService.buscarPacientePorEmail(paciente.getEmail());
            Optional<Paciente> pacienteBuscadoCedula = pacienteService.buscarPacientePorCedula(paciente.getCedula());
            if(pacienteBuscadoEmail.isPresent()){
                throw new BadRequestException("Paciente con email: " + paciente.getEmail() + " ya existe");
            }
            if(pacienteBuscadoCedula.isPresent()){
                throw new BadRequestException("Paciente con cedula: " + paciente.getCedula() + " ya existe");
            }
            return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
        } else {
            throw new BadRequestException("Campos requeridos para crear Paciente: Nombre, Apellido, Cedula, Email");
        }
    }

    /**
     * Actualizar datos de un paciente
     * @param paciente
     * @return
     */
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException, BadRequestException {

        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(paciente.getId()); //necesitamos primeramente validar si existe o  no
        if (pacienteBuscado.isPresent()) {
            if(!paciente.getNombre().isEmpty() && !paciente.getApellido().isEmpty() && !paciente.getCedula().isEmpty() && !paciente.getEmail().isEmpty()){
                if(pacienteService.existePacientePorEmailId(paciente.getEmail(), paciente.getId())){
                    throw new BadRequestException("Paciente con email: " + paciente.getEmail() + " ya existe en otro paciente");
                }
                if(pacienteService.existePacientePorCedulaId(paciente.getCedula(), paciente.getId())){
                    throw new BadRequestException("Paciente con cedula: " + paciente.getCedula() + " ya existe en otro paciente");
                }
                pacienteService.actualizarPaciente(paciente);
                return ResponseEntity.ok("El paciente se ha actualizado");
            }
            throw new BadRequestException("Campos requeridos para actualizar el Paciente: Nombre, Apellido, Cedula, Email");
        } else {
            throw new ResourceNotFoundException("No se encontró paciente con Id: " + paciente.getId().toString() + " para actualizar");
        }
    }

    /**
     * Buscar paciente por el ID
     * @param id
     * @return paciente
     */
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(id);
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            throw new ResourceNotFoundException("Paciente con Id: " + id.toString() + " no encontrado");
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Paciente> buscarPacientePorEmail(@PathVariable String email) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorEmail(email);
        if (pacienteBuscado.isPresent()) {
            return ResponseEntity.ok(pacienteBuscado.get());
        } else {
            throw new ResourceNotFoundException("Paciente con Email: " + email + " no encontrado");
        }
    }

    /**
     * Eliminar paciente por el ID
     * @param id
     * @return String
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        if (id > 0) {
            Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(id);
            if(pacienteBuscado.isPresent()){
                pacienteService.eliminarPaciente(id);
                return ResponseEntity.ok("El paciente se ha eliminado");
            }
        }
        throw new ResourceNotFoundException("Paciente con Id: " + id + " no encontrado para eliminar");
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

