package app.pizza.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import app.pizza.persistence.entity.PizzaEntity;

public interface PizzaPagSortRepository extends ListPagingAndSortingRepository<PizzaEntity,Integer>{
        Page<PizzaEntity> findByAvailableTrue(Pageable pageable);

}
