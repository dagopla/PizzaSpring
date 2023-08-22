package app.pizza.services;

import java.util.List;

import org.springframework.stereotype.Service;

import app.pizza.persistence.entity.PizzaEntity;
import app.pizza.persistence.repository.PizzaRepository;

@Service
public class PizzaService {
    
    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository=pizzaRepository;
    }
    public List<PizzaEntity> getAll(){
        return this.pizzaRepository.findAll();
    }
    public PizzaEntity getById(int id) {
        return this.pizzaRepository.findById(id).orElse(null);
    }
    public void save(PizzaEntity pizzaEntity){
        this.pizzaRepository.save(pizzaEntity);
    }
    public boolean exist(int id){
        return this.pizzaRepository.existsById(id);
    }
    public void delete(int id){
        this.pizzaRepository.deleteById(id);
    }
    public List<PizzaEntity> getAvailable(){
        return this.pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }
    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name);
    }
    public List<PizzaEntity> getByNameContaining(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameContainingIgnoreCase(name);
    }
    public List<PizzaEntity> getByPriceBetween(double min,double max){
        return this.pizzaRepository.findAllByAvailableTrueAndPriceBetween(min, max);
    }
    public List<PizzaEntity> getByDescriptionNotContaining(String description){
        return this.pizzaRepository.findAllByAvailableTrueAndDescriptionNoContainingIgnoreCase(description);
    }
    

}
