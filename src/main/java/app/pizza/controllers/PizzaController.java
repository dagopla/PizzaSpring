package app.pizza.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.pizza.persistence.entity.PizzaEntity;
import app.pizza.services.PizzaService;

@RestController
@RequestMapping( "api/pizza")
public class PizzaController {
    @Autowired
    private PizzaService pizzaService;

    
    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll(){
        return ResponseEntity.ok().body(this.pizzaService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable int id){
        return ResponseEntity.ok().body(this.pizzaService.getById(id));
    }
    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizzaEntity){
        if(pizzaEntity==null || !this.pizzaService.exist(pizzaEntity.getIdPizza()) ) return ResponseEntity.badRequest().build();
        this.pizzaService.save(pizzaEntity);
        return ResponseEntity.ok().body(pizzaEntity);
    }
    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizzaEntity){
        if(pizzaEntity !=null && this.pizzaService.exist(pizzaEntity.getIdPizza()) ) return ResponseEntity.badRequest().build();
        this.pizzaService.save(pizzaEntity);
        return ResponseEntity.ok().body(pizzaEntity);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id){
        if(!this.pizzaService.exist(id)) return ResponseEntity.badRequest().build();
        this.pizzaService.delete(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/available")
    public ResponseEntity<List<PizzaEntity>> getAvailable(){
        return ResponseEntity.ok().body(this.pizzaService.getAvailable());
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name){
        if(name==null || name.isEmpty()) return ResponseEntity.badRequest().build();
        if(this.pizzaService.getByName(name)==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(this.pizzaService.getByName(name));
    }
    @GetMapping("/name/containing/{name}")
    public ResponseEntity<List<PizzaEntity>> getByNameContaining(@PathVariable String name){
        if(name==null || name.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(this.pizzaService.getByNameContaining(name));
    }
    @GetMapping("/price/between/{min}/{max}")
    public ResponseEntity<List<PizzaEntity>> getByPriceBetween(@PathVariable double min,@PathVariable double max){
        if(min<0 || max<0 || min>max) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(this.pizzaService.getByPriceBetween(min, max));
    }
    @GetMapping("/description/notcontaining/{description}")
    public ResponseEntity<List<PizzaEntity>> getByDescriptionNotContaining(@PathVariable String description){
        if(description==null || description.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(this.pizzaService.getByDescriptionNotContaining(description));
    }
    

}
