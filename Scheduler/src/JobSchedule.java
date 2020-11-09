public class JobSchedule {
    private long nextExecutionTimeMs;
    boolean waitForPrevious;
    private long delayMs;

    JobSchedule(long nextExecutionTimeMs, boolean waitForPrevious, long delayMs){
        this.nextExecutionTimeMs = nextExecutionTimeMs;
        this.waitForPrevious = waitForPrevious;
        this.delayMs = delayMs;
    }

    JobSchedule(long nextExecutionTimeMs, long delayMs){
        this.nextExecutionTimeMs = nextExecutionTimeMs;
        this.delayMs = delayMs;
        this.waitForPrevious = false;
    }

    JobSchedule(long nextExecutionTimeMs){
        this.nextExecutionTimeMs = nextExecutionTimeMs;
        this.waitForPrevious = false;
        this.delayMs = -1;
    }

    public long getNextExecutionTimeMs(){
        return this.nextExecutionTimeMs;
    }

    public void setNextExecutionTimeMs(long timeMs){
        this.nextExecutionTimeMs = timeMs;
    }

    public boolean shouldWaitForPrevious(){
        return this.waitForPrevious;
    }

    public long getDelayMs(){
        return this.delayMs;
    }

    public boolean shouldScheduleNext(){
        return this.delayMs > 0;
    }
}
