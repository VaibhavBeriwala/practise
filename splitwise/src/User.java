import java.util.ArrayList;
import java.util.HashMap;

public class User {
    String name;
    String email;

    Long balance;
    HashMap<User, Long> userWiseBalance;
    ArrayList<Expense> expenses;

    User(String name, String email){
        this.name = name;
        this.email = email;

        balance = 0L;
        userWiseBalance = new HashMap<>();
        expenses = new ArrayList<Expense>();
    }

    public void incBalance(Long change){
        this.balance += change;
    }

    public void decBalance(Long change){
        this.balance -= change;
    }

    public void incUserWiseBalance(User user, Long amount){
        Long currAmount = userWiseBalance.get(user);
        if(currAmount != null){
            userWiseBalance.put(user, currAmount + amount);
        } else {
            userWiseBalance.put(user, amount);
        }
    }

    public void decUserWiseBalance(User user, Long amount){
        Long currAmount = userWiseBalance.get(user);
        if(currAmount != null){
            userWiseBalance.put(user, currAmount - amount);
        } else {
            userWiseBalance.put(user, -amount);
        }
    }
}