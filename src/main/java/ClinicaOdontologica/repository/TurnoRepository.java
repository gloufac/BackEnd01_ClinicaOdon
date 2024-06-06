package ClinicaOdontologica.repository;

import ClinicaOdontologica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

    List<Turno> findByodontologo_id(Long odontologoId);
    List<Turno> findBypaciente_id(Long pacienteId);
}
