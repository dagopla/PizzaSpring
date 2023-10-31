package app.pizza.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserRoleId implements Serializable {
    private String username;
    private String role;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof UserRoleId that))    
            return false;
        return Objects.equals(username, that.username)&& Objects.equals(role, that.role);
    }
    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }   
}
