package ClinicaOdontologica.service;

import ClinicaOdontologica.model.Odontologo;
import java.util.List;

public interface OdontologoService {

    /**
     * Buscar todos los odontologos H2
     * @return
     */
    List<Odontologo> buscarTodos();

    /**
     * Buscar odontologo por id
     * @param id
     * @return
     */
    Odontologo buscarPorId(Integer id);

    /**
     * Crear nuevo odontologo
     * @param Odontologo
     * @return
     */
    Odontologo crear(Odontologo Odontologo);

    /**
     * Actualizar datos odontologo
     * @param Odontologo
     */
    void actualizar(Odontologo Odontologo);


    /**
     * Listar odontologos, valores quemados en el codigo. Proposito de pruebas
     * @return
     */
    List<Odontologo> listaOdontologos();

    void eliminar(Integer id);
}
