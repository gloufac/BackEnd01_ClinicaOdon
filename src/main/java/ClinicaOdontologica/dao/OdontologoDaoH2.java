package ClinicaOdontologica.dao;

import ClinicaOdontologica.model.Domicilio;
import ClinicaOdontologica.model.Odontologo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

// TODO: Jessica actualizar, eliminar, todos los metodos
public class OdontologoDaoH2 implements iDao<Odontologo> {

    private static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);
    private final static String SQL_INSERT = "INSERT INTO odontologos" +
            "(numero_matricula, nombre, apellido)" +
            "VALUES(?,?,?)";
    private static final String SQL_SELECT_ONE = "SELECT * FROM odontologos WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM odontologos";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Iniciar operación guardar Odontologo");
        Connection connection = null;

        try{
            connection = BaseDatos.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            psInsert.setString(1, odontologo.getNumeroMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getNombre());

            psInsert.execute();
            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()) {
                odontologo.setId(rs.getInt(1));
            }
        }catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return odontologo;
    }

    @Override
    public Odontologo buscarPorID(Integer id) {
        logger.info("Iniciando busqueda de un odontologo");
        Connection connection = null;
        Odontologo odontologo = null;

        try {
            connection = BaseDatos.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT_ONE);
            psSelect.setInt(1, id);

            ResultSet rs = psSelect.executeQuery();
            while (rs.next()) {
                odontologo = new Odontologo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
            }

            logger.info("Paso buscarPorID");

        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return odontologo;
    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("Iniciar operación listar Odontologo");
        Connection connection = null;
        List<Odontologo> lstOdontologos = new ArrayList<>();

        try {
            connection = BaseDatos.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT_ALL);

            ResultSet rs = psSelect.executeQuery();
            while (rs.next()) {
                Odontologo odontologo = new Odontologo(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                lstOdontologos.add(odontologo);
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return lstOdontologos;
    }

    @Override
    public Odontologo buscarPorString(String valor) {
        return null;
    }
}
