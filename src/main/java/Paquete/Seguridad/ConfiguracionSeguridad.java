package Paquete.Seguridad;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class ConfiguracionSeguridad {

    private final FiltroAutenticacionJWT filtroAutenticacionJWT;

    @Bean
    public SecurityFilterChain cadenaFiltrosSeguridad(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(gestor -> gestor.sessionCreationPolicy(STATELESS))
                .exceptionHandling(excepcion -> excepcion
                        .authenticationEntryPoint((solicitud, respuesta, authException) -> {
                            respuesta.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                            respuesta.getWriter().write("Y la autenticación?");}
                        )
                        .accessDeniedHandler((solicitud, respuesta, accessDeniedException) -> {
                            respuesta.setStatus(HttpServletResponse.SC_FORBIDDEN);
                            respuesta.getWriter().write("Acceso denegado.");}
                        )
                )
                .addFilterBefore(filtroAutenticacionJWT, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(autorizacion -> autorizacion
                        .requestMatchers("/usuarios/**").permitAll()
                        .anyRequest().authenticated()
                ).build();
    }

    @Bean
    CorsConfigurationSource fuenteConfiguracionCors() {
        var configuracion = new CorsConfiguration();
        configuracion.setAllowedOrigins(List.of("*"));
        configuracion.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
        configuracion.setAllowedHeaders(List.of("*"));
        var fuente = new UrlBasedCorsConfigurationSource();
        fuente.registerCorsConfiguration("/**", configuracion);
        return fuente;
    }

    @Bean
    public AuthenticationManager gestorAutenticacion(AuthenticationConfiguration configuracion) throws Exception {
        return configuracion.getAuthenticationManager();
    }
}
