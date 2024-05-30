package ClinicaOdontologica.service;



import ClinicaOdontologica.dao.TurnosDAOLISTA;
import ClinicaOdontologica.dao.iDao;
import ClinicaOdontologica.model.Paciente;
import ClinicaOdontologica.model.Turno;

import java.util.List;

public class TurnoService {
    private iDao<Turno> turnoiDao;

    public TurnoService() {
        turnoiDao= new TurnosDAOLISTA();
    }
    public Turno guardarTurno(Turno turno){
        return turnoiDao.guardar(turno);
    }
    public Turno buscarPorID(Integer id){
        return turnoiDao.buscarPorID(id);
    }
    public List<Turno> listarTurnos(){
        return turnoiDao.buscarTodos();
    }
    public void actualizar(Turno turno){ turnoiDao.actualizar(turno); }
    public void eliminar(Integer id){ turnoiDao.eliminar(id); }
}
