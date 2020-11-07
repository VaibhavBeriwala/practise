import java.util.ArrayList;

public class Expense {
    String name;
    ArrayList<Transaction> transactions;

    Expense(String name, ArrayList<Transaction> transactions){
        this.name = name;
        this.transactions = transactions;
    }

//    public boolean validateExpense(){
//        Long totalPaid = 0L;
//        Long totalOwed = 0L;
//
//        for(Transaction t: paidBy){
//            totalPaid += t.amount;
//        }
//        for(Transaction t: owedBy){
//            totalOwed += t.amount;
//        }
//
//        if (totalOwed != totalPaid) {return false;}
//        return true;
//    }

    public void updateUsers(){
        for(Transaction t: this.transactions){
            t.paidBy.incBalance(t.amount);
            t.paidBy.incUserWiseBalance(t.owedBy, t.amount);
            t.owedBy.decBalance(t.amount);
            t.owedBy.decUserWiseBalance(t.paidBy, t.amount);
        }
    }
}
