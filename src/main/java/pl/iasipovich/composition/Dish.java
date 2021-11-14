package pl.iasipovich.composition;

public class Dish {
    String dishName;
    String dishDescription;
    String recipe;

    public Dish(String dishName, String dishDescription, String recipe) {
        this.dishName = dishName;
        this.dishDescription = dishDescription;
        this.recipe = recipe;
    }


    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public void setDishDescription(String dishDescription) {
        this.dishDescription = dishDescription;
    }

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

}
