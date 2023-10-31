package app.pizza.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Autowired
    private JwtFilter jwtFilter;

    // security filter chain
    // http basic
    // csrf disable
    // cors
    // user details service
    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
            //Se agrega cors para permitir el acceso desde el front end
                .cors(Customizer.withDefaults())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(customRequest -> customRequest
                
        //Este metodo permite configurar las rutas que se van a proteger y los roles que pueden acceder a ellas
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/api/customer/**").hasAnyRole("ADMIN","CUSTOMER")
                .requestMatchers(HttpMethod.GET,"/api/pizza**").permitAll()
                .requestMatchers(HttpMethod.PUT).denyAll()
                //Se configura la ruta /api/orders/** para que solo pueda ser accedida por un usuario con rol de administrador
                .requestMatchers("/api/order/ramdon").hasAuthority("ramdon_order")
                .requestMatchers("/api/order/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

            
        return http.build();
    }

    // user details service
    // in memory
    // admin admin
    // admin role
    // user role
    // Se crea un usuario con rol de administrador cargado en memoria
    // @Bean
    // public UserDetailsService userDetailsService(){
    // //return new InMemoryUserDetailsManager() para crear un usuario con rol de
    // administrador cargado en memoria
    // return new InMemoryUserDetailsManager(
    // User.builder()
    // .username("admin")
    // .password(passwordEncoder().encode("admin"))
    // .roles("ADMIN")
    // .build(),
    // User.builder()
    // .username("customer")
    // .password(passwordEncoder().encode("customer"))
    // .roles("CUSTOMER")
    // .build()
    // );
    // }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() {

        };
    }
}
