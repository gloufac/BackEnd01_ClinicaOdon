package ClinicaOdontologica.service;

import ClinicaOdontologica.model.Paciente;
import ClinicaOdontologica.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public Paciente actualizarPaciente(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> buscarPacientePorId(Long id){
        return pacienteRepository.findById(id);
    }

    public Optional<Paciente> buscarPacientePorCedula(String cedula){
        return pacienteRepository.findByCedula(cedula);
    }

    public boolean existePacientePorCedulaId(String cedula, Long id){
        var pacienteCedula = pacienteRepository.findByCedula(cedula);
        if(pacienteCedula.isPresent() && !pacienteCedula.get().getId().equals(id)){
            return true;
        }
        return false;
    }

    public Optional<Paciente> buscarPacientePorEmail(String email){
        return pacienteRepository.findByEmail(email);
    }

    public boolean existePacientePorEmailId(String email, Long id){
        var pacienteEmail = pacienteRepository.findByEmail(email);
        if(pacienteEmail.isPresent() && !pacienteEmail.get().getId().equals(id)){
            return true;
        }
        return false;
    }

    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }

    public List<Paciente> buscarTodos(){
        return pacienteRepository.findAll();
    }
}
