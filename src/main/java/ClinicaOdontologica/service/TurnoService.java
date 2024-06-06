package ClinicaOdontologica.service;

import ClinicaOdontologica.model.Turno;
import ClinicaOdontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public Turno guardarTurno(Turno turno){
        return turnoRepository.save(turno);
    }

    public Turno actualizarTurno(Turno turno){
        return turnoRepository.save(turno);
    }

    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }

    public Optional<Turno> buscarTurnoPorId(Long id){
        return turnoRepository.findById(id);
    }

    public List<Turno> buscarTurnoPorOdontologoId(Long odontologoId){
        return turnoRepository.findByodontologo_id(odontologoId);
    }

    public List<Turno> buscarTurnoPorPacienteId(Long pacienteId){
        return turnoRepository.findBypaciente_id(pacienteId);
    }

    public List<Turno> buscarTodos(){
        return turnoRepository.findAll();
    }
}
