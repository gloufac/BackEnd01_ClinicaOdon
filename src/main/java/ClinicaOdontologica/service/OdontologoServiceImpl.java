package ClinicaOdontologica.service;

import ClinicaOdontologica.dao.OdontologoDaoH2;
import ClinicaOdontologica.dao.iDao;
import ClinicaOdontologica.model.Odontologo;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

@Service
public class OdontologoServiceImpl implements OdontologoService {

    private iDao<Odontologo> odontologoiDao;
    public OdontologoServiceImpl() { odontologoiDao= new OdontologoDaoH2();}

    @Override
    public List<Odontologo> buscarTodos(){ return odontologoiDao.buscarTodos();}

    @Override
    public Odontologo buscarPorId(Integer id){ return odontologoiDao.buscarPorID(id);}

    public Odontologo crear(Odontologo Odontologo){ return odontologoiDao.guardar(Odontologo); }

    public void actualizar(Odontologo Odontologo){ odontologoiDao.actualizar(Odontologo); }

    @Override
    public List<Odontologo> listaOdontologos() {
        return Arrays.asList(new Odontologo("ODO-PRUEBA", "Javier", "Jaramillo")
                ,new Odontologo("ODO-PRUEBA2", "Ana", "Minca")
        );
    }
}
