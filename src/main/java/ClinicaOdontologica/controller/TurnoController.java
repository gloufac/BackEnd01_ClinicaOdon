package ClinicaOdontologica.controller;


import ClinicaOdontologica.model.Turno;
import ClinicaOdontologica.service.OdontologoServiceII;
import ClinicaOdontologica.service.PacienteService;
import ClinicaOdontologica.service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;

    public TurnoController() {
        turnoService = new TurnoService();
    }

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno) {

        PacienteService pacienteService = new PacienteService();
        OdontologoServiceII odontologoService = new OdontologoServiceII();
        if (pacienteService.buscarPorID(turno.getPaciente().getId()) != null && odontologoService.buscarPorId(turno.getOdontologo().getId()) != null) {
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            //bad request or not found
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTodosLosTurnos() {
        return ResponseEntity.ok(turnoService.listarTurnos());
    }

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno) {
        PacienteService pacienteService = new PacienteService();
        OdontologoServiceII odontologoService = new OdontologoServiceII();
        if (pacienteService.buscarPorID(turno.getPaciente().getId()) != null && odontologoService.buscarPorId(turno.getOdontologo().getId()) != null) {
            return ResponseEntity.ok(turnoService.actualizar(turno));
        } else {
            //bad request or not found
            return ResponseEntity.badRequest().build();
        }

    }
}
