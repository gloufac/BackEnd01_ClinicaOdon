package ClinicaOdontologica.security;

import ClinicaOdontologica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

/**
 * Clase donde spring entra en juego
 * f
 * arrobas: inyeccion de independencias
 */
@Configuration
@EnableWebSecurity
public class ConfigWebSecurity {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Proveedor autenticaciones de tipo DAO
     * Metodo provee la autenticacion de la aplicacion
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecure) throws Exception{
        httpSecure
                .csrf(
                        AbstractHttpConfigurer::disable // desactivar referencias cruzadas
                )// PROD: investigar
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(antMatcher(HttpMethod.POST, "/paciente")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.GET, "/paciente")).hasAnyRole("ADMIN", "USER")
                        .requestMatchers(antMatcher(HttpMethod.PUT, "/paciente")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.DELETE, "/paciente")).hasRole("ADMIN")
                        .requestMatchers(antMatcher("/pacientes/get_pacientes.html")).hasRole("ADMIN")
                        .requestMatchers(antMatcher("/odontologos/get_odontologos.html")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.POST, "/odontologo")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.GET, "/odontologo")).hasAnyRole("ADMIN", "USER")
                        .requestMatchers(antMatcher(HttpMethod.PUT, "/odontologo")).hasRole("ADMIN")
                        .requestMatchers(antMatcher(HttpMethod.DELETE, "/odontologo")).hasRole("ADMIN")
                        .requestMatchers("./turnos/**", "./js/turnos/**").hasRole("USER")
                .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());
        return httpSecure.build();
    }
}
