package ClinicaOdontologica;

import ClinicaOdontologica.model.Domicilio;
import ClinicaOdontologica.model.Paciente;
import ClinicaOdontologica.service.PacienteService;

import java.time.LocalDate;

public class Cliente {

    public static void main(String[] args) {

        Paciente paciente = new Paciente("Argemiro", "Diaz", "12345"
                , LocalDate.of(2024, 5, 20)
                ,"lalal@lala.com"
                , new Domicilio("Calle X", 234, "Lima", "Peru"));

        PacienteService pacienteService = new PacienteService();
        pacienteService.guardarPaciente(paciente);

    }
}
