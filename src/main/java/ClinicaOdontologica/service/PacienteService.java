package ClinicaOdontologica.service;


import ClinicaOdontologica.dao.PacienteDaoH2;
import ClinicaOdontologica.dao.iDao;
import ClinicaOdontologica.model.Paciente;

import java.util.List;

public class PacienteService {
    // relacion de asociacion directa con el DAO
    private iDao<Paciente> pacienteiDao;
    public PacienteService() {
        pacienteiDao= new PacienteDaoH2();
    }

    public Paciente guardarPaciente(Paciente paciente){ return pacienteiDao.guardar(paciente); }
    public Paciente buscarPorID(Integer id){ return pacienteiDao.buscarPorID(id);}
    public void actualizar(Paciente paciente){ pacienteiDao.actualizar(paciente); }
    public void eliminar(Integer id){ pacienteiDao.eliminar(id); }
    public List<Paciente> buscarTodos(){
        return pacienteiDao.buscarTodos();
    }
}
