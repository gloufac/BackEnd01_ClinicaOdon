package ClinicaOdontologica.repository;

import ClinicaOdontologica.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    Optional<Odontologo> findByNumeroMatricula(String matricula);
}
