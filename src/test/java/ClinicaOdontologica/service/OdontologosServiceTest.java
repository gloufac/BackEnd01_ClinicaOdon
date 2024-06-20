package ClinicaOdontologica.service;


import ClinicaOdontologica.model.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OdontologosServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    public void guardarOdontologo() {
        Odontologo odontologo = new Odontologo("123963", "Dayana", "Torres");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologo);
        assertEquals(4L, odontologoGuardado.getId());
    }

    @Test
    @Order(2)
    public void buscarOdontologoPorId() {
        Long id = 4L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(id);
        assertNotNull(odontologoBuscado.get());
    }

    @Test
    @Order(3)
    public void actualizarOdontologoTest() {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(4L);
        if(odontologoBuscado.isPresent()){
            odontologoBuscado.get().setApellido("Mendoza");
        }
        odontologoService.actualizarOdontologo(odontologoBuscado.get());
        assertEquals("Mendoza", odontologoBuscado.get().getApellido());
    }

    @Test
    @Order(4)
    public void buscarTodos() {
        List<Odontologo> odontologos = odontologoService.buscarOdontologos();
        assertEquals(4, odontologos.size());
    }

    @Test
    @Order(5)
    public void eliminarOdontologo() {
        odontologoService.eliminarOdontologo(4l);
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologoPorId(4l);
        assertFalse(odontologoBuscado.isPresent());
    }
}
