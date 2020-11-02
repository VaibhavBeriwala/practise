public class Test {
    public static void main(String args[]){
        VendingMachine machine = new VendingMachine(5);

        // All Beverages
        Beverage gingerTea = new Beverage("ginger_tea");
        gingerTea.addIngredient(new Ingredient("milk", 100));
        gingerTea.addIngredient(new Ingredient("ginger", 10));
        gingerTea.addIngredient(new Ingredient("water", 50));

        Beverage coffee = new Beverage("coffee");
        coffee.addIngredient(new Ingredient("milk", 50));
        coffee.addIngredient(new Ingredient("water", 100));
        coffee.addIngredient(new Ingredient("coffee", 10));

        Beverage hotMilk = new Beverage("hot_milk");
        hotMilk.addIngredient(new Ingredient("milk", 100));

        Beverage elaichiTea = new Beverage("elaichi_tea");
        elaichiTea.addIngredient(new Ingredient("milk", 100));
        elaichiTea.addIngredient(new Ingredient("water", 50));
        elaichiTea.addIngredient(new Ingredient("elaichi", 10));

        Beverage hotWater = new Beverage("hot_water");
        hotWater.addIngredient(new Ingredient("water", 100));


        // Set available ingredients in machine
        machine.addIngredient(new Ingredient("water", 500));
        machine.addIngredient(new Ingredient("milk", 500));
        machine.addIngredient(new Ingredient("elaichi", 50));
        machine.addIngredient(new Ingredient("ginger", 50));
        machine.addIngredient(new Ingredient("coffee", 50));

        machine.start();

        // Start preparing beverages
        machine.prepare(1, elaichiTea);
        machine.prepare(2, gingerTea);
        machine.prepare(3, hotMilk);
        machine.prepare(4, hotWater);

        // error should be thrown for below since outlet 1 is already occupied
        machine.prepare(1, coffee);

        machine.stop();


    }
}
