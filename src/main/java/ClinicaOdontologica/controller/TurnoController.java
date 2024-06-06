package ClinicaOdontologica.controller;


import ClinicaOdontologica.model.Paciente;
import ClinicaOdontologica.model.Turno;
import ClinicaOdontologica.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    @PostMapping
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {

        if (pacienteService.buscarPacientePorId(turno.getPaciente().getId()).isPresent()
                && odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId()).isPresent()
            ) {
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            // Bad Request: se requiere el paciente y el odontologo
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodosLosTurnos() {
        return ResponseEntity.ok(turnoService.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) {
        Optional<Turno> turnoBuscado = turnoService.buscarTurnoPorId(turno.getId());
        if (turnoBuscado.isPresent()
                && pacienteService.buscarPacientePorId(turno.getPaciente().getId()).isPresent()
                && odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId()).isPresent()
        ) {
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("El turno ha sido actualizado");
        } else {
            //bad request or not found
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarTurno(@RequestBody Long id) {
        Optional<Turno> turnoBuscado = turnoService.buscarTurnoPorId(id);
        if(turnoBuscado.isPresent()){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Ok, el turno ha sido eliminado");
        } else {
            return ResponseEntity.badRequest().body("Id inválido o el turno no se encontró");
        }
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
