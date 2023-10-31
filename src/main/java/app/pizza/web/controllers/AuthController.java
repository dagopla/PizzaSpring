package app.pizza.web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.pizza.services.dto.LoginDto;
import app.pizza.web.config.JwtUtils;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto){
        System.out.println(loginDto);
        UsernamePasswordAuthenticationToken login= new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        Authentication authentication= this.authenticationManager.authenticate(login);
        System.out.println(authentication.getAuthorities());
        String jwt =this.jwtUtils.create(loginDto.getUsername());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }
}
