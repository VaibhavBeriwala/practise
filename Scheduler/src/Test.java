public class Test {
    public static void main(String[] args) throws InterruptedException {
        Scheduler scheduler = new Scheduler(2);
        scheduler.start();

        Long currentTimeMs = System.currentTimeMillis();
        System.out.println("Current time: " + currentTimeMs);
        Long delay1 = 30 * 1000L; // 30 s
        Long delay2 = 5 * 1000L; // 5 s
        Long delay3 = 10 * 1000L; // 10 s
        Long delay4 = 60 * 1000L; // 60 s

        Job adhoc1 = new AdhocJob(currentTimeMs +  delay1);
        Job adhoc2 = new AdhocJob(currentTimeMs +  delay2);

        Job periodic1 = new PeriodicJob(currentTimeMs + delay3, delay3);

        scheduler.submitJob(adhoc1);
        scheduler.submitJob(periodic1);
        scheduler.submitJob(adhoc2);

        Thread.sleep(50*1000);

        scheduler.stop();
    }
}

