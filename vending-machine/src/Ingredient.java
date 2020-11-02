public class Ingredient {
    private String name;
    private int quantity;

    Ingredient(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }

    void setQuantity(int quantity){
        this.quantity = quantity;
    }

    void setName(String name){
        this.name = name;
    }

    String getName(){
        return this.name;
    }

    int getQuantity(){
        return this.quantity;
    }
}