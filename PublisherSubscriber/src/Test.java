public class Test {
    public static void main(String args[]) throws InterruptedException {
        Message dsa1 = new Message("Graphs", "DSA");
        Message dsa2 = new Message("Trees", "DSA");
        Message dsa3 = new Message("Arrays", "DSA");

        Message sd1 = new Message("Uber", "SystemDesign");
        Message sd2 = new Message("Netflix", "SystemDesign");
        Message sd3 = new Message("Instagram", "SystemDesign");

        Message[] messages = {dsa1, dsa2, dsa3, sd1, sd2, sd3};

        PubSubService kafka = new PubSubService(3);
        kafka.start();

        // 3 different Consumers/Subscribers
        Consumer dsa = new Consumer("DSA Subscription", kafka);
        Consumer systemDesign = new Consumer("System Design", kafka);
        Consumer interviewPractise = new Consumer("Interview Practise", kafka);

        // Consumers subscribe to topic(s)
        dsa.subscribe("DSA");
        systemDesign.subscribe("SystemDesign");
        interviewPractise.subscribe("DSA");
        interviewPractise.subscribe("SystemDesign");

        // 1 single Producer/Publisher
        Producer producer = new Producer(kafka);
        for (Message m: messages){
            producer.produce(m);
        }

        Thread.sleep(5000);
        System.out.println("Shutting down Kakfa");
        kafka.stop();
    }
}
