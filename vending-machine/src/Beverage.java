import java.util.ArrayList;

public class Beverage {
    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    private String name;

    Beverage(String name){
        this.name = name;
    }

    ArrayList<Ingredient> getIngredients(){
        return this.ingredients;
    }

    void addIngredient(Ingredient ingredient){
        if (ingredient != null){
            this.ingredients.add(ingredient);
        }
    }

    void removeIngredient(Ingredient ingredient){
        if (ingredient != null){
            this.ingredients.remove(ingredient);
        }
    }

    public String getName(){
        return this.name;
    }
}
