package ClinicaOdontologica.repository;

import ClinicaOdontologica.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

    List<Turno> findByodontologo_id(Long odontologoId);
    List<Turno> findBypaciente_id(Long pacienteId);

    @Query("select u from Turno u where u.fecha = ?1") // and u.odontologo_id = ?2 and u.paciente_id = ?3
    List<Turno> findTurnoByFecha(LocalDate fecha); //Long odontologoId, Long pacienteId
}
