package pl.iasipovich.model;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;

@Entity
@Table(name =  "DISH", uniqueConstraints = @UniqueConstraint(columnNames = "dish_name"))
public class Dish {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "dish_name")
    private String dish_name;

    @Column(name = "dish_desc")
    private String dish_desc;

    @Column(name = "dish_recipe")
    private String dish_recipe;

    public Dish() {

    }

    public Dish( String user_name, String dish_name, String dish_desc,String dish_recipe) {
        this.user_name = user_name;
        this.dish_name = dish_name;
        this.dish_desc = dish_desc;
        this.dish_recipe = dish_recipe;
    }

    public Dish(Long id, String user_name, String dish_name, String dish_desc, String dish_recipe) {
        this.id = id;
        this.user_name = user_name;
        this.dish_name = dish_name;
        this.dish_desc = dish_desc;
        this.dish_recipe = dish_recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDish_name() {
        return dish_name;
    }

    public void setDish_name(String dish_name) {
        this.dish_name = dish_name;
    }

    public String getDish_desc() {
        return dish_desc;
    }

    public void setDish_desc(String dish_desc) {
        this.dish_desc = dish_desc;
    }

    public String getDish_recipe() {
        return dish_recipe;
    }

    public void setDish_recipe(String dish_recipe) {
        this.dish_recipe = dish_recipe;
    }
}