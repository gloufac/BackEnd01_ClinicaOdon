package ClinicaOdontologica.dao;

import org.apache.log4j.Logger;

import java.sql.*;


public class BaseDatos {
    private static final String DB_JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/db_pacientes";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "se";
    private static final Logger logger = Logger.getLogger(BaseDatos.class);
    private static final String CREATE_DROP_TABLE_PACIENTES = "DROP TABLE IF EXISTS pacientes;" +
            "CREATE TABLE pacientes (" +
            "id int AUTO_INCREMENT primary key, " +
            "nombre varchar(100) not null, " +
            "apellido varchar(100) not null, " +
            "cedula varchar(50) not null, " +
            "fecha_ingreso date not null, " +
            "domicilio_id int not null," +
            "email VARCHAR(100) NOT NULL" +// FK foreign key
            ")";
    private static final String CREATE_DROP_TABLE_DOMICILIO = "DROP TABLE IF EXISTS domicilios;" +
            "CREATE TABLE domicilios(" +
            "id int AUTO_INCREMENT primary key, " +
            "calle varchar(100) not null, " +
            "numero int not null, " +
            "localidad varchar(150) not null, " +
            "provincia varchar(150) not null " +
            ")";
    private static final String CREATE_DROP_TABLE_ODONTOLOGO = "DROP TABLE IF EXISTS odontologos;" +
            "CREATE TABLE odontologos (" +
            "id int AUTO_INCREMENT primary key, " +
            "numero_matricula varchar(50) not null, " +
            "nombre varchar(100) not null, " +
            "apellido varchar(100) not null " +
            ")";

    private static final String SQL_INSERT_PACIENTES = "INSERT INTO pacientes (nombre, apellido, cedula, fecha_ingreso, domicilio_id, email)" +
            "VALUES " +
            "('Gloria', 'Lopez', '123454', '2024-01-01', 1, 'XXX@digitalhouse.com')," +
            "('Samantha', 'Sanchez', '54545', '2024-01-01', 1, 'yyy@digitalhouse.com')" +
            ";";

    private static final String SQL_INSERT_DOMICILIOS = "INSERT INTO domicilios(calle, numero, localidad, provincia)" +
            "VALUES" +
            "('Aragon', 541, 'New Jersey', 'New Jersey')," +
            "('Ohlas', 589, 'Pasadena', 'California')";

    private final static String SQL_INSERT_ODONTOLOGOS = "INSERT INTO odontologos(numero_matricula, nombre, apellido)" +
            "VALUES" +
            "('ODO-1234', 'Pedro', 'Perez')," +
            "('ODO-5842', 'Ana', 'Cantila')," +
            "('ODO-9547', 'Jorge', 'Martinez')" ;

    public static void crearTablas() {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute(CREATE_DROP_TABLE_PACIENTES);
            statement.execute(CREATE_DROP_TABLE_DOMICILIO);
            statement.execute(CREATE_DROP_TABLE_ODONTOLOGO);

            PreparedStatement psInsertDom = connection.prepareStatement(SQL_INSERT_DOMICILIOS);
            psInsertDom.execute();

            PreparedStatement psInsertPac = connection.prepareStatement(SQL_INSERT_PACIENTES);
            psInsertPac.execute();

            PreparedStatement psInsertOdo = connection.prepareStatement(SQL_INSERT_ODONTOLOGOS);
            psInsertOdo.execute();

            logger.info("Tablas creadas con exito, con datos de pruebas insertados");
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }
    }

    public static Connection getConnection() throws Exception {
        Class.forName(DB_JDBC_DRIVER);
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
