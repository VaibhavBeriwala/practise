public class Test {
    public static void main(String args[]){
        User user1 = new User("user1", "user1@test.com");
        User user2 = new User("user2", "user2@test.com");
        User user3 = new User("user3", "user3@test.com");
        User user4 = new User("user4", "user4@test.com");

        Splitwise s = new Splitwise();
        s.registerUser(user1.name, user1.email);
        s.registerUser(user2.name, user2.email);
        s.registerUser(user3.name, user3.email);
        s.registerUser(user4.name, user4.email);


    }
}
