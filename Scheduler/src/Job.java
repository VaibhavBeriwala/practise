public abstract class Job{
    JobSchedule schedule = null;
    JobState state;

    public abstract void run();

    public void setState(JobState s){
        this.state = s;
    }

    public void cancelJob(){
        this.state = JobState.CANCELLED;
    }

    public JobState getState(){
        return this.state;
    }

    public JobSchedule getSchedule(){
        return this.schedule;
    }
}
