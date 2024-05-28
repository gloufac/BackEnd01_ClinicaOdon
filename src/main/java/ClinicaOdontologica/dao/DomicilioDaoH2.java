package ClinicaOdontologica.dao;

import ClinicaOdontologica.model.Domicilio;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class DomicilioDaoH2 implements iDao<Domicilio> {
    private static final Logger logger = Logger.getLogger(DomicilioDaoH2.class);
    private static final String SQL_INSERT = "INSERT INTO domicilios" +
            "(calle,numero,localidad,provincia)" +
            "VALUES (?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE domicilios " +
            "SET calle = ?,numero = ?,localidad = ?,provincia = ?" +
            "WHERE id = ?";
    private static final String SQL_SELECT_ONE = "SELECT * FROM domicilios WHERE id = ?";

    @Override
    public Domicilio guardar(Domicilio domicilio) {
        logger.info("Iniciar operacion guardado Domicilio");
        Connection connection = null;
        try {
            connection = BaseDatos.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, domicilio.getCalle());
            psInsert.setInt(2, domicilio.getNumero());
            psInsert.setString(3, domicilio.getLocalidad());
            psInsert.setString(4, domicilio.getProvincia());
            psInsert.execute();
            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()) {
                domicilio.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return domicilio;
    }

    @Override
    public Domicilio buscarPorID(Integer id) {
        logger.info("Iniciando busqueda de un domicilio");
        Connection connection = null;
        Domicilio domicilio = null;
        try {
            connection = BaseDatos.getConnection();
            Statement statement = connection.createStatement();

            PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT_ONE);
            psSelect.setInt(1, id);

            ResultSet rs = psSelect.executeQuery();
            while (rs.next()) {
                domicilio = new Domicilio(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5)
                );
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return domicilio;
    }

    @Override
    public void actualizar(Domicilio domicilio) {
        logger.info("Iniciar operacion actualizar Domicilio");
        Connection connection = null;
        try {
            connection = BaseDatos.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);
            psUpdate.setString(1, domicilio.getCalle());
            psUpdate.setInt(2, domicilio.getNumero());
            psUpdate.setString(3, domicilio.getLocalidad());
            psUpdate.setString(4, domicilio.getProvincia());
            psUpdate.setInt(5, domicilio.getId());
            psUpdate.execute();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Domicilio> buscarTodos() {
        return List.of();
    }

    @Override
    public Domicilio buscarPorString(String valor) {
        return null;
    }
}
