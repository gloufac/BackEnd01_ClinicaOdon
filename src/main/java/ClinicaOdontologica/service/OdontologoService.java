package ClinicaOdontologica.service;

import ClinicaOdontologica.model.Odontologo;

import java.util.List;

public interface OdontologoService {

    List<Odontologo> listaOdontologos();
    Odontologo buscarPorId(int id);

}
