package com.monopatin.authservice.config;

import com.monopatin.authservice.security.jwt.JwtConfigurer;
import com.monopatin.authservice.security.jwt.TokenProvider;
import com.monopatin.authservice.service.AuthorityConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {

    private final TokenProvider tokenProvider;


    /**
     * Este método configura y devuelve un objeto PasswordEncoder que se utilizará para cifrar y verificar 
     * contraseñas. En este caso, se utiliza BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**Este método configura la cadena de filtros de seguridad para la aplicación. 
     * Aquí se define cómo se gestionan las solicitudes HTTP y las reglas de autorización.
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain filterChain( HttpSecurity http ) throws Exception {
        // AGREGAMOS NUESTRA CONFIG DE JWT.
        http
                .apply( securityConfigurerAdapter() );
        http
            .csrf( AbstractHttpConfigurer::disable )
            // MANEJAMOS LOS PERMISOS A LOS ENDPOINTS.
            .authorizeHttpRequests( auth -> auth
                    .requestMatchers("/api/v1/**").permitAll()
            )
            .anonymous( AbstractHttpConfigurer::disable )
            .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http
            .httpBasic( Customizer.withDefaults() );
        return http.build();
    }

    /**
     * Nuestra configuracion de JWT.
     */
    private JwtConfigurer securityConfigurerAdapter() {
        return new JwtConfigurer(tokenProvider);
    }


    ////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    /**
     * Este método configura un ResourceDatabasePopulator que carga datos de un script SQL ubicado 
     * en el recurso de clase "db_auth.sql". 
     * Esto puede ser utilizado para cargar datos en la base de datos al iniciar la aplicación.
     */

    @Bean
    public ResourceDatabasePopulator databasePopulator() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("db_auth.sql"));
        return populator;
    }

}
