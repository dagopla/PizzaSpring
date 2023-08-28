package app.pizza.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.pizza.persistence.entity.PizzaEntity;
import app.pizza.persistence.repository.PizzaPagSortRepository;
import app.pizza.persistence.repository.PizzaRepository;
import app.pizza.services.dto.UpdatePizzaPriceDto;

@Service
public class PizzaService {
    
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private PizzaPagSortRepository pizzaPagSortRepository;
 
    public Page<PizzaEntity> getAll(int page,int elements){
        Pageable pageable = PageRequest.of(page,elements);
        return this.pizzaPagSortRepository.findAll(pageable);
    }
    public PizzaEntity getById(int id) {
        return this.pizzaRepository.findById(id).orElse(null);
    }
    @Transactional
    public PizzaEntity save(PizzaEntity pizzaEntity){
        this.pizzaRepository.save(pizzaEntity);
        return pizzaEntity; 
    }
    public boolean exist(int id){
        return this.pizzaRepository.existsById(id);
    }
    public void delete(int id){
        this.pizzaRepository.deleteById(id);
    }
    public Page<PizzaEntity> getAvailable(int page, int elements, String sort){
        Pageable pageable = PageRequest.of(0, 10, Sort.by(sort).ascending());
        return this.pizzaPagSortRepository.findByAvailableTrue(pageable);
    }
    public PizzaEntity getByName(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameIgnoreCase(name).orElseThrow(()-> new RuntimeException("Pizza not found"));
    }
    public List<PizzaEntity> getByNameContaining(String name){
        return this.pizzaRepository.findAllByAvailableTrueAndNameContainingIgnoreCase(name);
    }
    public List<PizzaEntity> getByPriceBetween(double min,double max){
        return this.pizzaRepository.findAllByAvailableTrueAndPriceBetween(min, max);
    }
    public List<PizzaEntity> getTop3ByPriceLessThanEqualOrderByPriceAsc(double price){
        return this.pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }
    @Transactional
    public void updatePrice(UpdatePizzaPriceDto newPizzaPrice){
        this.pizzaRepository.updatePrice( newPizzaPrice);
    }
    

}
