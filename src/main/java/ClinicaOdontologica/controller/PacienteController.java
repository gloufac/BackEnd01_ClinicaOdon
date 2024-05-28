package ClinicaOdontologica.controller;

import ClinicaOdontologica.model.Paciente;
import ClinicaOdontologica.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //cambiamos pq no necesitamos tecnologia de vista.
@RequestMapping("/paciente")
public class PacienteController {
    private PacienteService pacienteService;

    public PacienteController() {
        pacienteService = new PacienteService();
    }

    /*@GetMapping
    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String email){
        //vamos a pasar la solicitud atraves del http, osea va a ir en la url
        Paciente paciente= pacienteService.buscarPorCorreo(email);
        model.addAttribute("nombre",paciente.getNombre());
        model.addAttribute("apellido",paciente.getApellido());
        return "index";
        //return pacienteService.buscarPorCorreo(email);
    } */
    @PostMapping //nos permite crear o registrar un paciente
    public Paciente registrarUnPaciente(@RequestBody Paciente paciente) {
        return pacienteService.guardarPaciente(paciente);
    }

    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente) {
        //necesitamos primeramente validar si existe o  no
        Paciente pacienteBuscado = pacienteService.buscarPorID(paciente.getId());
        if (pacienteBuscado != null) {
            pacienteService.actualizar(paciente);
            return "paciente actualizado";
        } else {
            return "paciente no encontrado";
        }

    }

    @GetMapping("/{id}")
    public Paciente buscarPacientePorId(@PathVariable Integer id) {
        if (id > 0) {
            Paciente pacienteBuscado = pacienteService.buscarPorID(id);
            return pacienteBuscado;
        } else {
            return null;
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public Integer eliminarPaciente(@PathVariable Integer id) {
        if (id > 0) {
            pacienteService.eliminar(id);
            return 1;
        } else {
            return 0;
        }
    }

    @GetMapping()
    public List<Paciente> buscarTodos() {
        List<Paciente> pacientes = pacienteService.buscarTodos();
        return pacientes;
    }
}

