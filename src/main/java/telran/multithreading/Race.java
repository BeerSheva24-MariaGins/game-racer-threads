package telran.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private int distance;
    private int minSleep;
    private int maxSleep;
    private long startTime;
    AtomicInteger winner = new AtomicInteger(-1);
    private List<Result> results = new ArrayList<>();

    public Race(int distance, int minSleep, int maxSleep) {
        this.distance = distance;
        this.minSleep = minSleep;
        this.maxSleep = maxSleep;
        this.startTime = System.currentTimeMillis(); 
    }

    public int getWinner() {
        return winner.get();
    }

    public int getDistance() {
        return distance;
    }

    public int getMinSleep() {
        return minSleep;
    }

    public int getMaxSleep() {
        return maxSleep;
    }

    public long getStartTime() {
        return startTime;
    }

    public synchronized void addResult(Result result) {
        results.add(result);
    }

    public List<Result> getResults() {
        return results;
    }
}
