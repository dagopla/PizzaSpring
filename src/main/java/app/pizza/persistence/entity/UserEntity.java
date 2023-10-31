package app.pizza.persistence.entity;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Entidad de usuario en una base de datos relacional con postgresql
@Entity
@Table(name = "user_pizza")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(nullable = false)
    // private Long id;
    @Id
    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false )
    private Boolean locked;

    @Column(nullable = false )
    private Boolean diseable;
    @Column(nullable = false, length = 50)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;
}
