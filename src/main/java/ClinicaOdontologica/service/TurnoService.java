package ClinicaOdontologica.service;

import ClinicaOdontologica.model.Turno;
import ClinicaOdontologica.repository.TurnoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;


    public Turno guardarTurno(Turno turno){
        Turno turno1 = turnoRepository.save(turno);
        turno1.setNombreturno("CLO-" + StringUtils.leftPad(turno.getId().toString(), 3, "0"));
        return turnoRepository.save(turno1);
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

    public List<Turno> buscarTurnoPorFechaOdontologoPaciente(LocalDate fecha, Long odontologoId, Long pacienteId){
        var turnos = turnoRepository.findTurnoByFecha(fecha);

        var turnosFilter = turnos.stream().filter(x -> x.getOdontologo().getId().equals(odontologoId) && x.getPaciente().getId().equals(pacienteId)).toList();

        return turnosFilter;
    }

    public List<Turno> buscarTodos(){
        return turnoRepository.findAll();
    }
}
