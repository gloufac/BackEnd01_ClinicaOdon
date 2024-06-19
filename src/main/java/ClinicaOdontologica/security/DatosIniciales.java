package ClinicaOdontologica.security;

import ClinicaOdontologica.ClinicaOdontologicaApplication;
import ClinicaOdontologica.model.*;
import ClinicaOdontologica.repository.OdontologoRepository;
import ClinicaOdontologica.repository.PacienteRepository;
import ClinicaOdontologica.repository.UsuarioRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private OdontologoRepository odontologoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    private static final Logger logger = Logger.getLogger(ClinicaOdontologicaApplication.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar = "admin";
        String passCifrado = passwordEncoder.encode(passSinCifrar);
        Usuario usuario = new Usuario("glopez", "glopez", passCifrado, "admin@admin.com", UsuarioRole.ROLE_ADMIN);
        Usuario usuario2 = new Usuario("jessica", "jessica", passCifrado, "user@user.com", UsuarioRole.ROLE_USER);
        System.out.println("pass cifrado: " + passCifrado);
        usuarioRepository.save(usuario);
        usuarioRepository.save(usuario2);

        setDefaultValues();
    }

    public void setDefaultValues(){
        // Set default values
        Odontologo odontologo = new Odontologo("ODO-001", "Jazmin", "Posada");
        Odontologo odontologo2 = new Odontologo("ODO-002", "Edward", "Castano");
        Odontologo odontologo3 = new Odontologo("ODO-003", "Alexander", "Bosh");
        odontologoRepository.save(odontologo);
        odontologoRepository.save(odontologo2);
        odontologoRepository.save(odontologo3);

        Domicilio domicilio = new Domicilio("Calle 15", 85, "Tuna", "Cundinamarca");
        Domicilio domicilio2 = new Domicilio("Calle 16", 86, "Robles", "Cundinamarca");
        Domicilio domicilio3 = new Domicilio("Calle 17", 87, "Quinta", "Cundinamarca");
        Paciente paciente = new Paciente("Marcela", "Paez", "1059", LocalDate.parse("2024-04-01"), "marcepaez@coco.com", domicilio);
        Paciente paciente2 = new Paciente("Juana", "Arco", "1060", LocalDate.parse("2024-04-30"), "juanaarco@coco.com", domicilio2);
        Paciente paciente3 = new Paciente("Emma", "Trump", "1061", LocalDate.parse("2024-05-15"), "emmatrump@coco.com", domicilio3);
        pacienteRepository.save(paciente);
        pacienteRepository.save(paciente2);
        pacienteRepository.save(paciente3);

        logger.info("Pacientes y odontologos valores por defecto creados");
    }
}
