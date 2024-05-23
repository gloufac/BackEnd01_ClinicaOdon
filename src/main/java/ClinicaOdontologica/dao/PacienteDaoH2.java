package ClinicaOdontologica.dao;

import ClinicaOdontologica.model.Domicilio;
import ClinicaOdontologica.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDaoH2 implements iDao<Paciente> {
    private static final Logger logger = Logger.getLogger(PacienteDaoH2.class);
    private final static String SQL_INSERT = "INSERT INTO pacientes" +
            "(nombre,apellido,cedula,fecha_ingreso,domicilio_id)" +
            "VALUES (?,?,?,?,?)";
    private final static String SQL_UPDATE = "UPDATE pacientes " +
            "SET nombre = ?,apellido = ?,cedula = ?,fecha_ingreso = ?,domicilio_id = ?)" + //??
            "WHERE id = ?";
    private static final String SQL_SELECT_ONE = "SELECT * FROM pacientes WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM pacientes";

    @Override
    public Paciente guardar(Paciente paciente) {
        logger.info("Iniciar operacion guardado Pacientes");
        Connection connection = null;
        try {
            DomicilioDaoH2 daoAux = new DomicilioDaoH2();
            Domicilio domicilio = daoAux.guardar(paciente.getDomicilio());

            connection = BaseDatos.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getCedula());
            psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psInsert.setInt(5, domicilio.getId());

            psInsert.execute();
            ResultSet rs = psInsert.getGeneratedKeys(); // me retorna el id del paciente
            while (rs.next()) {
                paciente.setId(rs.getInt(1));
            }
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return paciente;
    }

    @Override
    public Paciente buscarPorID(Integer id) {
        logger.info("Iniciando busqueda de un paciente");
        Connection connection = null;
        Paciente paciente = null;
        Domicilio domicilio = null;
        try {
            connection = BaseDatos.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT_ONE);
            psSelect.setInt(1, id);

            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();

            ResultSet rs = psSelect.executeQuery();
            while (rs.next()) {
                Domicilio domicilio1 = domicilioDaoH2.buscarPorID(rs.getInt(6));
                paciente = new Paciente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5).toLocalDate(),
                        domicilio1
                );
            }

        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
        return paciente;
    }

    @Override
    public void actualizar(Paciente paciente) {
        logger.info("Iniciar operacion actualizar Paciente");
        Connection connection = null;
        try {
            DomicilioDaoH2 daoAux = new DomicilioDaoH2();
            daoAux.actualizar(paciente.getDomicilio());

            connection = BaseDatos.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE, Statement.RETURN_GENERATED_KEYS);

            psUpdate.setString(1, paciente.getNombre());
            psUpdate.setString(2, paciente.getApellido());
            psUpdate.setString(3, paciente.getCedula());
            psUpdate.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psUpdate.setInt(5, paciente.getDomicilio().getId());
            psUpdate.execute();
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public List<Paciente> buscarTodos() {
        logger.info("Traer todos los pacientes");
        Connection connection = null;
        Paciente paciente = null;
        Domicilio domicilio = null;
        List<Paciente> pacientes = new ArrayList<>();
        try {
            connection  = BaseDatos.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL);
            DomicilioDaoH2 domAux = new DomicilioDaoH2();

            while (rs.next()){
                domicilio= domAux.buscarPorID(rs.getInt(6));
                paciente = new Paciente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getDate(5).toLocalDate(),
                        domicilio
                );
                pacientes.add(paciente);
            }
        }
        catch (Exception e){
            logger.error(e.getMessage());
        }
        return  pacientes;
    }
}
