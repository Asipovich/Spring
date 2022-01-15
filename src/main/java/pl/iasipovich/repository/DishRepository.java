package pl.iasipovich.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.iasipovich.model.Dish;

import javax.persistence.Entity;
import javax.persistence.Table;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long>{
    void deleteById(Long id);

}
