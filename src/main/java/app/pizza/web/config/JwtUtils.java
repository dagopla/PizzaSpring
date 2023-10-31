package app.pizza.web.config;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtUtils {
    private static String SECRET_KEY="platzi-pizza";
    private static Algorithm ALGORITHM= Algorithm.HMAC256(SECRET_KEY);
    public String create(String username){
        return JWT.create()
        .withSubject(username)
        .withIssuer(username)
        .withIssuedAt(new Date())
        .withExpiresAt(new Date(System.currentTimeMillis()+TimeUnit.DAYS.toMillis(15)))
        .sign(ALGORITHM);
    }
    public boolean validate(String token, String username) {
        try {
            JWT.require(ALGORITHM)
                .withIssuer(username)
                .build()
                .verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public String getUsername(String token) {
        return JWT.require(ALGORITHM)
                .build()
                .verify(token)
                .getSubject();
    }
}
