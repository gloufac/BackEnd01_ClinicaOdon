package ClinicaOdontologica;


import ClinicaOdontologica.model.Domicilio;
import ClinicaOdontologica.model.Odontologo;
import ClinicaOdontologica.model.Paciente;
import ClinicaOdontologica.model.Turno;
import ClinicaOdontologica.service.OdontologoService;
import ClinicaOdontologica.service.PacienteService;
import ClinicaOdontologica.service.TurnoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnosTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;


    public void cargarDatos() {
        Paciente pacienteGuardado = pacienteService.guardarPaciente(new Paciente("Andrea", "Medina", "111111", LocalDate.of(2024, 6, 19), "andrea@gmail.com", new Domicilio("Calle false", 123, "La Rioja", "Costa Rica")));
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(new Odontologo("123963", "Dayana", "Torres"));
        Turno turnoGuardado =  turnoService.guardarTurno(new Turno( odontologoGuardado, pacienteGuardado,  LocalDate.of(2024,6,19)));
    }

    @Test
    public void listarTodosLosTurnos() throws Exception {
        cargarDatos();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turno").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }


}
