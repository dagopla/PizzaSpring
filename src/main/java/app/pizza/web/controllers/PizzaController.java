package app.pizza.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.pizza.persistence.entity.PizzaEntity;
import app.pizza.services.PizzaService;
import app.pizza.services.dto.UpdatePizzaPriceDto;

@RestController
@RequestMapping("api/pizza")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int elements) {
        return ResponseEntity.ok().body(this.pizzaService.getAll(page, elements));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable int id) {
        return ResponseEntity.ok().body(this.pizzaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizzaEntity){
        if(pizzaEntity==null || this.pizzaService.exist(pizzaEntity.getIdPizza()) ) return ResponseEntity.badRequest().build();
        this.pizzaService.save(pizzaEntity);
        return ResponseEntity.ok().body(pizzaEntity);
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() != null && this.pizzaService.exist(pizza.getIdPizza())) {
            return ResponseEntity.ok(this.pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        if (!this.pizzaService.exist(id))
            return ResponseEntity.badRequest().build();
        this.pizzaService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/available")
    public ResponseEntity<Page<PizzaEntity>> getAvailable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int elements,
            @RequestParam(defaultValue = "price") String sort) {
        return ResponseEntity.ok().body(this.pizzaService.getAvailable(page, elements, sort));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
        if (name == null || name.isEmpty())
            return ResponseEntity.badRequest().build();
        try {
            return ResponseEntity.ok().body(this.pizzaService.getByName(name));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/name/containing/{name}")
    public ResponseEntity<List<PizzaEntity>> getByNameContaining(@PathVariable String name) {
        if (name == null || name.isEmpty())
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(this.pizzaService.getByNameContaining(name));
    }

    @GetMapping("/price/between/{min}/{max}")
    public ResponseEntity<List<PizzaEntity>> getByPriceBetween(@PathVariable double min, @PathVariable double max) {
        if (min < 0 || max < 0 || min > max)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(this.pizzaService.getByPriceBetween(min, max));
    }

    @GetMapping("/price/top3/{price}")
    public ResponseEntity<List<PizzaEntity>> getTop3ByPriceLessThanEqualOrderByPriceAsc(@PathVariable double price) {
        if (price < 0)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(this.pizzaService.getTop3ByPriceLessThanEqualOrderByPriceAsc(price));
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto newPizzaPrice) {
        if (!this.pizzaService.exist(newPizzaPrice.getPizzaId()) || newPizzaPrice.getNewPrice() < 0)
            return ResponseEntity.badRequest().build();
        this.pizzaService.updatePrice(newPizzaPrice);
        return ResponseEntity.ok().build();
    }

}
