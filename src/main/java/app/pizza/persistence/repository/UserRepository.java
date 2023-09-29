package app.pizza.persistence.repository;

import app.pizza.persistence.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity,String> {

}
