package ClinicaOdontologica.security;

import ClinicaOdontologica.model.Usuario;
import ClinicaOdontologica.model.UsuarioRole;
import ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar = "admin";
        String passCifrado = passwordEncoder.encode(passSinCifrar);
        Usuario usuario = new Usuario("glopez", "glopez", passCifrado, "admin@admin.com", UsuarioRole.ROLE_ADMIN);
        Usuario usuario2 = new Usuario("jessica", "jessica", passCifrado, "user@user.com", UsuarioRole.ROLE_USER);
        System.out.println("pass cifrado: " + passCifrado);
        usuarioRepository.save(usuario);
        usuarioRepository.save(usuario2);
    }
}
