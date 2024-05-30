package ClinicaOdontologica.controller;


import ClinicaOdontologica.model.Turno;
import ClinicaOdontologica.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private OdontologoService odontologoService;
    private PacienteService pacienteService;


    public TurnoController() {
        turnoService = new TurnoService();
        odontologoService = new OdontologoServiceImpl();
        pacienteService = new PacienteService();
    }

    @PostMapping
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
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
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) {
        if (pacienteService.buscarPorID(turno.getPaciente().getId()) != null && odontologoService.buscarPorId(turno.getOdontologo().getId()) != null) {
            turnoService.actualizar(turno);
            return ResponseEntity.ok("El turno ha sido actualizado");
        } else {
            //bad request or not found
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarTurno(@RequestBody Integer id) {
        Turno turno = turnoService.buscarPorID(id);
        if(turno != null && turno.getId() > 0){
            turnoService.eliminar(id);
            return ResponseEntity.ok("Ok, el turno ha sido eliminado");
        } else {
            return ResponseEntity.badRequest().body("Id inválido o el turno no se encontró");
        }
    }
}
