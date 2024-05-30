package ClinicaOdontologica.service;

import ClinicaOdontologica.dao.OdontologoDaoH2;
import ClinicaOdontologica.dao.iDao;
import ClinicaOdontologica.model.Odontologo;

public class OdontologoServiceII {
    private iDao<Odontologo> odontologoiDao;

    public OdontologoServiceII() {
        odontologoiDao= new OdontologoDaoH2();
    }
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoiDao.guardar(odontologo);
    }
    public Odontologo buscarPorId(Integer id){
        return odontologoiDao.buscarPorID(id);
    }
}
