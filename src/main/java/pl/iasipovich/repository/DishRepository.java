package pl.iasipovich.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pl.iasipovich.model.Dish;

import java.util.Optional;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long>{
    void deleteById(Long id);
    Optional<Dish> findById(Long id);
}
