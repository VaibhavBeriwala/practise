import java.util.Comparator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

public class Scheduler {
    private PriorityBlockingQueue<Job> queue;
    private SchedulerThread schedulerThread;

    Scheduler(int poolSize){
        queue = new PriorityBlockingQueue<Job>(8, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return (int)(o1.getSchedule().getNextExecutionTimeMs() - o2.getSchedule().getNextExecutionTimeMs());
            }
        });
        schedulerThread = new SchedulerThread(poolSize, queue);
    }

    public void stop(){
        schedulerThread.stopRunning();
    }

    public void start(){
        schedulerThread.start();
    }

    public void submitJob(Job j){
        j.setState(JobState.RUNNING);
        queue.add(j);
    }

    public void cancelJob(Job j){
        queue.remove(j);
    }
}

class SchedulerThread extends Thread{
    private PriorityBlockingQueue<Job> queue;
    private ExecutorService threadPoolExecutor;
    private boolean stopped = false;

    SchedulerThread(int poolSize, PriorityBlockingQueue<Job> queue){
        this.queue = queue;
        threadPoolExecutor = Executors.newFixedThreadPool(poolSize);
    }

    public void stopRunning(){
        stopped = true;
        threadPoolExecutor.shutdown();
    }
    public void run() {
        while (!stopped){
            try {
                Job j = queue.take();
                if(j.getState() == JobState.CANCELLED){
                    continue;
                }

                Long nextJobExecutionTime = j.getSchedule().getNextExecutionTimeMs();
                boolean jobExecuted = false;
                while(!jobExecuted && !stopped){
                    Long currentTimeMs = System.currentTimeMillis();
                    if(nextJobExecutionTime <= currentTimeMs){
                        // schedule next and run this Job
                        if(j.getSchedule().shouldScheduleNext()){
                            long delay = j.getSchedule().getDelayMs();
                            j.getSchedule().setNextExecutionTimeMs(currentTimeMs + delay);
                            queue.add(j);
                        }
                        threadPoolExecutor.submit(() -> j.run());
                        jobExecuted = true;
                    } else {
                        System.out.println("Sleeping for : " + (nextJobExecutionTime - currentTimeMs));
                        Thread.sleep(nextJobExecutionTime - currentTimeMs);
                    }
                }
            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println("Shutting down all scheduler threads!!");
            }
        }
    }
}
