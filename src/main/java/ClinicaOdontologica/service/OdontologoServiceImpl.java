package ClinicaOdontologica.service;

import ClinicaOdontologica.dao.OdontologoDaoH2;
import ClinicaOdontologica.dao.iDao;
import ClinicaOdontologica.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class OdontologoServiceImpl {

    private iDao<Odontologo> odontologoiDao;
    public OdontologoServiceImpl() {
        odontologoiDao= new OdontologoDaoH2();
    }
    public Odontologo guardarOdontologo(Odontologo Odontologo){ return odontologoiDao.guardar(Odontologo); }

    public Odontologo buscarPorId(Integer id){ return odontologoiDao.buscarPorID(id);}

    public List<Odontologo> buscarTodos(){
        return odontologoiDao.buscarTodos();
    }


    public List<Odontologo> listaOdontologos() {
//        return Arrays.asList(new Odontologo("ODO-PRUEBA", "Javier", "Jaramillo")
//                ,new Odontologo("ODO-PRUEBA2", "Ana", "Minca")
//        );
        return odontologoiDao.buscarTodos();
    }

}
