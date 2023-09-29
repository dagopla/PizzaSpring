package app.pizza.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// Entidad de usuario en una base de datos relacional con postgresql
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_pizza")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 20, nullable = false)
    private String id;

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
}
