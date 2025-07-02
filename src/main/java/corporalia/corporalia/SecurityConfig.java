package corporalia.corporalia;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF (solo para pruebas)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Método actualizado
                .httpBasic(Customizer.withDefaults()); // Configuración básica HTTP
        return http.build(); // Cambia la configuración de filtro
    }
}
