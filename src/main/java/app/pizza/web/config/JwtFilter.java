package app.pizza.web.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        // Validar que el request tenga el header authorization
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Validar que el header no sea nulo y que empiece con Bearer
        if (authHeader != null && authHeader.startsWith("Bearer")) {
            // Obtener el token
            String token = authHeader.substring(7);
            // Validar el token

            String username = this.jwtUtils.getUsername(token);
            // Validar que el usuario no sea nulo y que no este autenticado
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Validar que el token sea valido
                if (!this.jwtUtils.validate(token, username)) {
                    filterChain.doFilter(request, response);
                    return;
                }
                User userDetails = (User) this.userService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                // Obtener los roles del usuario
                // Autenticar el usuario
                SecurityContextHolder.getContext().setAuthentication(auth);
                
                filterChain.doFilter(request, response);

            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'doFilterInternal'");
    }

}
