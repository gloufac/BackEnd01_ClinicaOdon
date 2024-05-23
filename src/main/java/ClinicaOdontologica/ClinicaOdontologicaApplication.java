package ClinicaOdontologica;

import ClinicaOdontologica.dao.BaseDatos;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8080/odontologos
 * http://localhost:8080/odontologos/matricula?id=1
 */
@SpringBootApplication
public class ClinicaOdontologicaApplication {

	private static final Logger logger = Logger.getLogger(ClinicaOdontologicaApplication.class);
	public static void main(String[] args) {
		BaseDatos.crearTablas();
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
		logger.info("Inicializando aplicacion");
	}

}
