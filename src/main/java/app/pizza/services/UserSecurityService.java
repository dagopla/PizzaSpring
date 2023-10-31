package app.pizza.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.pizza.persistence.entity.UserEntity;
import app.pizza.persistence.entity.UserRoleEntity;
import app.pizza.persistence.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String[] rolesList = user.getRoles().stream().map(UserRoleEntity::getRole).toArray(String[]::new);
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                // .roles(rolesList)
                .authorities(this.getGrantedAuthorities(rolesList))
                .accountLocked(user.getLocked())
                .disabled(user.getDiseable())
                .build(); 
    }
    private String[] getAuthorities(String role){
        if("ADMIN".equals(role)||"CUSTOMER".equals(role)) {
            return new String[]{"ramdon_order"};
        }
        return new String[]{};
    }
    private List<GrantedAuthority> getGrantedAuthorities(String[] roles){
        List<GrantedAuthority> authorities = new ArrayList<>(roles.length);
        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority("Role_"+role));
            for(String authority : this.getAuthorities(role)){
                authorities.add(new SimpleGrantedAuthority(authority));
            }
        }
        return authorities;
    }
}
