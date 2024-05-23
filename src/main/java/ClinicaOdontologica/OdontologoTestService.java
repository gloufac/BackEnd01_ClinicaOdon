package ClinicaOdontologica;

import ClinicaOdontologica.dao.BaseDatos;
import ClinicaOdontologica.dao.OdontologoDaoList;
import ClinicaOdontologica.model.Odontologo;
//import org.junit.jupiter.api.Assertions;
import ClinicaOdontologica.service.OdontologoServiceImpl;
import java.util.List;

public class OdontologoTestService {
    /**
     * Lista odontologos desde el hashmap
     */
    //@Test
    public void  agregarOdontologoMap(){
        OdontologoDaoList odontologoDaoList = new OdontologoDaoList();
        Odontologo odontologo = new Odontologo("OD-1589", "Ramiro", "Gonzalez");
        Odontologo od2 = odontologoDaoList.guardar(odontologo);
        //Assertions.assertTrue(od2 != null);
    }
    //@Test
    public void  buscarTodosOdontologosMap(){
        OdontologoDaoList odontologoDaoList = new OdontologoDaoList();
        Odontologo odontologo = new Odontologo(1,"OD-1589", "Ramiro", "Gonzalez");
        Odontologo odontologo2 = new Odontologo(2,"OD-9845", "Susana", "Duarte");

        odontologoDaoList.guardar(odontologo);
        odontologoDaoList.guardar(odontologo2);
        List<Odontologo> lstOdon = odontologoDaoList.buscarTodos();

        //Assertions.assertTrue(lstOdon.stream().count() == 2);
    }

    /**
     * Testear DAO Odontologo H2
     */
    //@Test
    public void buscarOdontologoPorId(){
        BaseDatos.crearTablas(); // se tuvo que haber creado un registro
        OdontologoServiceImpl odontologoService = new OdontologoServiceImpl();
        Integer buscar1 = 1;
        Odontologo odontologo = odontologoService.buscarPorId(buscar1);
        //Assertions.assertEquals("ODO-1234", odontologo.getNumeroMatricula());
    }

    //@Test
    public void listarOdontologos(){
        BaseDatos.crearTablas();
        OdontologoServiceImpl odontologoService = new OdontologoServiceImpl();
        List<Odontologo> lstOdontologos = odontologoService.buscarTodos();
        //Assertions.assertEquals(3, lstOdontologos.stream().count());
    }
}
