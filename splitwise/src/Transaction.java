public class Transaction {
    User paidBy;
    User owedBy;
    Long amount;

    Transaction(User paidBy, User owedBy, Long amount){
        this.paidBy = paidBy;
        this.owedBy = owedBy;
        this.amount = amount;
    }
}
