package ClinicaOdontologica.service;


import ClinicaOdontologica.model.Domicilio;
import ClinicaOdontologica.model.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PacienteServiceTest {

    @Autowired
    private  PacienteService pacienteService;

    //metodos de ese service que necesito testear

    @Test
    @Order(1)
    public void guardarPaciente() {
        Paciente  paciente = new Paciente("Andrea", "Medina", "111111", LocalDate.of(2024, 6, 19), "andrea@gmail.com", new Domicilio("Calle false", 123, "La Rioja", "Costa Rica"));
        Paciente pacienteGuardado = pacienteService.guardarPaciente(paciente);
        assertEquals(4L, pacienteGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarPacientePorId(){
        Long id= 1L;
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorId(id);
        assertNotNull(pacienteBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarPacienteTest(){
//      Paciente  paciente = new Paciente("Andrea", "Medina", "111111", LocalDate.of(2024, 6, 19), "andrea@gmail.com", new Domicilio("Calle false", 123, "La Rioja", "Costa Rica"));
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPacientePorId(1L);
        if(pacienteBuscado.isPresent()){
            pacienteBuscado.get().setApellido("Perez");
        }
    }

    @Test
    @Order(4)
    public void buscarTodos(){
    List<Paciente> pacientes = pacienteService.buscarTodos();
    assertEquals(4, pacientes.size());
    }

    @Test
    @Order(5)
    public  void eliminarPaciente(){
        pacienteService.eliminarPaciente(4L);
        Optional<Paciente> pacienteBuscado= pacienteService.buscarPacientePorId(4L);
        assertFalse(pacienteBuscado.isPresent());

    }
}
