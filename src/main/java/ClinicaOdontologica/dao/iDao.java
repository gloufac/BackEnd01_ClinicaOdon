package ClinicaOdontologica.dao;

import java.util.List;

public interface iDao<T> {
    T guardar(T t);
    T buscarPorID(Integer id);
    void actualizar(T t);
    void eliminar(Integer id);
    List<T> buscarTodos();
}
