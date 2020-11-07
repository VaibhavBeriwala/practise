import java.util.ArrayList;
import java.util.HashMap;

public class Splitwise {

    HashMap<String, User> userMap;

    Splitwise(){
        userMap = new HashMap<>();
    }

    void registerUser(String name, String email){
        if(userMap.get(email) != null){
            System.out.println("ERROR: User mail already exists!!!!");
        } else{
            User newUser = new User(name, email);
            userMap.put(email, newUser);
        }
    }

    void addExpense(String name, ArrayList<Transaction> transactions){
        Expense expense = new Expense(name, transactions);
        expense.updateUsers();
    }

    // create transactions list


    // TODO
    // void editExpense(){}

    // validate if all the users in a transaction exists in userMap

}
