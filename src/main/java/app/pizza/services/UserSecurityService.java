package app.pizza.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.pizza.persistence.entity.UserEntity;
import app.pizza.persistence.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserEntity user = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("ADMIN")
                .accountLocked(user.getLocked())
                .disabled(user.getDiseable())
                .build(); 
    }
}
