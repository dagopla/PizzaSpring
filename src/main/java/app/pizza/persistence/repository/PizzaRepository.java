package app.pizza.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import app.pizza.persistence.entity.PizzaEntity;
import app.pizza.services.dto.UpdatePizzaPriceDto;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity,Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();
    Optional<PizzaEntity> findAllByAvailableTrueAndNameIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndNameContainingIgnoreCase(String name);
    List<PizzaEntity> findAllByAvailableTrueAndPriceBetween(double min,double max);
    List<PizzaEntity> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(double price);
    
    @Query(value = "UPDATE pizza "+
    "SET price= :#{#newPizzaPrice.newPrice} "+
    "WHERE id_pizza = :#{#newPizzaPrice.pizzaId} ",nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);
}
