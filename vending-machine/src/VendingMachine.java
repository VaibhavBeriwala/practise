import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class VendingMachine {
    private HashMap<String, Ingredient> availableIngredients;
    private int outlets;
    private String[] slots;
    ExecutorService threadPoolExecutor;

    VendingMachine(int outlets) {
        this.outlets = outlets;
        slots = new String[outlets];
        for(int i = 0; i<outlets; i++){
            slots[i] = "";
        }
        availableIngredients = new HashMap<>();
    }

    public String[] getSlots(){
        return this.slots;
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

    public void decBeverageQuantity(Beverage beverage){
        for(Ingredient i: beverage.getIngredients()){
            decIngredientQuantity(i);
        }
    }

    public boolean canPrepare(Beverage beverage){
        for(Ingredient i: beverage.getIngredients()){
            String name = i.getName();
            if(i.getQuantity() > availableIngredients.get(name).getQuantity()){
                return false;
            }
        }
        return true;
    }

    public void prepare(int outlet, Beverage beverage){
        Runnable task = new Prepare(beverage, outlet, this);
        threadPoolExecutor.execute(task);
    }

    public void start(){
        threadPoolExecutor = Executors.newFixedThreadPool(this.outlets);
    }

    public void stop(){
        // print available ingredients
        for(Ingredient i: availableIngredients.values()){
            System.out.println(i.getName() + " : " + i.getQuantity());
        }
        threadPoolExecutor.shutdown();
    }
}

class Prepare implements Runnable {
    Beverage beverage;
    int outlet;
    VendingMachine machine;

    Prepare(Beverage beverage, int outlet, VendingMachine machine){
        this.beverage = beverage;
        this.outlet = outlet;
        this.machine = machine;
    }

    @Override
    public void run() {
        try {
            String[] slots = machine.getSlots();
            if(slots[outlet].equals("")){
                slots[outlet] = beverage.getName();
                System.out.println("Preparing " + beverage.getName());
                synchronized (machine){
                    if(machine.canPrepare(beverage)){
                        machine.decBeverageQuantity(beverage);
                    } else{
                        // throw error
                        System.out.println("ERROR: Insufficient ingredient available to prepare:" +
                                beverage.getName());
                        return;
                    }
                }
                Thread.sleep(5000);
                System.out.println(beverage.getName() + " has been prepared.");
                slots[outlet] = "";
            } else {
                System.out.println("ERROR: This outlet is already busy!!! Try another outlet!!!");
                //throw exception - outlet already busy
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
