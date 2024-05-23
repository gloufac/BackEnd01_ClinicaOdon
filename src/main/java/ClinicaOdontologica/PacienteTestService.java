package ClinicaOdontologica;

import ClinicaOdontologica.dao.BaseDatos;
import ClinicaOdontologica.model.Paciente;

import ClinicaOdontologica.service.PacienteService;

public class PacienteTestService {

    //@Test
    public void buscarUnPaciente(){
        BaseDatos.crearTablas();
        PacienteService pacienteService = new PacienteService();
        Integer buscar1 = 1;
        Paciente paciente = pacienteService.buscarPorID(buscar1);
        //Assertions.assertTrue(paciente != null);
    }
}
