package app.pizza.web.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    //Se configura cors para permitir el acceso desde el front end al back end en el puerto 4200
    @Bean
    //La clase CorsConfiguration permite configurar los origenes, metodos y headers que se van a permitir
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        //Permite todos los metodos http mensionados en el array
        corsConfiguration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
        //Permite todos los headers
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        //La clase UrlBasedCorsConfigurationSource permite configurar las rutas que se van a proteger y los roles que pueden acceder a ellas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
