package ClinicaOdontologica;

import ClinicaOdontologica.dao.BaseDatos;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * http://localhost:8080/odontologos
 */
@SpringBootApplication
public class ClinicaOdontologicaApplication {

	private static final Logger logger = Logger.getLogger(ClinicaOdontologicaApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
		logger.info("Aplicacion web iniciada ...");
		BaseDatos.crearTablas();
		logger.info("Base de datos lista para usar");
	}

}
