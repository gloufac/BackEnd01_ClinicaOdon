package ClinicaOdontologica.controller;


import ClinicaOdontologica.exception.BadRequestException;
import ClinicaOdontologica.exception.ResourceNotFoundException;
import ClinicaOdontologica.model.Odontologo;
import ClinicaOdontologica.model.Paciente;
import ClinicaOdontologica.model.Turno;
import ClinicaOdontologica.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turno") // dejar el nombre en singular
public class TurnoController {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    // TODO preguntar al profe cuando hay errores en la validacion de datos como se controla
    @PostMapping
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) throws BadRequestException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());
        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            var algo = turnoService.guardarTurno(turno);
            algo.setPaciente(pacienteBuscado.get());
            algo.setOdontologo(odontologoBuscado.get());
            return ResponseEntity.ok(algo);
        } else {
            // Bad Request: se requiere el paciente y el odontologo
            //return ResponseEntity.badRequest().build();
            throw new BadRequestException("No existe el odontologo o paciente relacionado");
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodosLosTurnos() {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());
        Optional<Turno> turnoBuscado = turnoService.buscarTurnoPorId(turno.getId());
        if (turnoBuscado.isPresent() && pacienteBuscado.isPresent() && odontologoBuscado.isPresent()
        ) {
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("El turno ha sido actualizado");
        } else {
            throw new ResourceNotFoundException("Id inválido o odontologo y paciente no encontrados");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Turno> turnoBuscado = turnoService.buscarTurnoPorId(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Ok, el turno ha sido eliminado");
        } else {
            throw new ResourceNotFoundException("Id inválido o el turno no existe");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurnoPorId(@PathVariable Long id) {
        Optional<Turno> turnoBuscado = turnoService.buscarTurnoPorId(id);
        return turnoBuscado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/odontologo/{id}")
    public ResponseEntity<List<Turno>> listarTurnosPorOdontologo(@RequestBody Long id) {
        List<Turno> turnos = turnoService.buscarTurnoPorOdontologoId(id);
        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<List<Turno>> listarTurnosPorPaciente(@RequestBody Long id) {
        List<Turno> turnos = turnoService.buscarTurnoPorPacienteId(id);
        return ResponseEntity.ok(turnos);
    }
}
