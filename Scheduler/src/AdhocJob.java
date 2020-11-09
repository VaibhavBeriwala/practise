public class AdhocJob extends Job {

    AdhocJob(long executionTimeMs){
        schedule = new JobSchedule(executionTimeMs);
    }

    @Override
    public void run() {
        System.out.println("AdhocJob ran :" + System.currentTimeMillis());
    }
}
