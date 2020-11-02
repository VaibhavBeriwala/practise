import java.util.HashMap;
import java.util.concurrent.*;

public class VendingMachine {
    private HashMap<String, Ingredient> availableIngredients;
    private int outlets;
    private String[] machine;
    ExecutorService threadPoolExecutor;

    VendingMachine(int outlets) {
        this.outlets = outlets;
        machine = new String[outlets];
        for(int i = 0; i<outlets; i++){
            machine[i] = "";
        }
        availableIngredients = new HashMap<>();
    }

    public void addIngredient(Ingredient ingredient){
        if (availableIngredients.get(ingredient.getName()) == null){
            availableIngredients.put(ingredient.getName(), ingredient);
        } else {
            // throw error
        }
    }

    public void incIngredientQuantity(Ingredient increment){
        if (availableIngredients.get(increment.getName()) != null){
            Ingredient ingredient = availableIngredients.get(increment.getName());
            ingredient.setQuantity(ingredient.getQuantity() + increment.getQuantity());
        } else{
            // throw exception
        }
    }

    public void decIngredientQuantity(Ingredient decrement){
        if (availableIngredients.get(decrement.getName()) != null){
            Ingredient ingredient = availableIngredients.get(decrement.getName());
            ingredient.setQuantity(ingredient.getQuantity() - decrement.getQuantity());
        } else{
            // throw exception
        }
    }

    public int getAvailableQuantity(Ingredient ingredient){
        if(availableIngredients.get(ingredient.getName()) != null){
            return availableIngredients.get(ingredient.getName()).getQuantity();
        } else{
            //throw exception
            return -1;
        }
    }

    public void prepare(int outlet, Beverage beverage){
        Runnable task = new Prepare(beverage, outlet, machine);
        threadPoolExecutor.execute(task);
    }

    public void start(){
        threadPoolExecutor = Executors.newFixedThreadPool(this.outlets);
    }

    public void stop(){
        threadPoolExecutor.shutdown();
    }
}

class Prepare implements Runnable {
    Beverage beverage;
    int outlet;
    String[] machine;

    Prepare(Beverage beverage, int outlet, String[] machine){
        this.beverage = beverage;
        this.outlet = outlet;
        this.machine = machine;
    }

    @Override
    public void run() {
        try {
            if(machine[outlet].equals("")){
                machine[outlet] = beverage.getName();
                System.out.println("Preparing " + beverage.getName());
                Thread.sleep(5000);
                System.out.println(beverage.getName() + " has been prepared.");
                machine[outlet] = "";
            } else {
                System.out.println("ERROR: This outlet is already busy!!! Try another outlet!!!");
                //throw exception - outlet already busy
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
