public class PeriodicJob extends Job {

    PeriodicJob(long executionTimeMs, long delayMs){
        this.schedule = new JobSchedule(executionTimeMs, delayMs);
    }

    @Override
    public void run() {
        System.out.println("PeriodicJob ran: " + System.currentTimeMillis());
    }
}
