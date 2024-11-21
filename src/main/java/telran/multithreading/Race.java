package telran.multithreading;

import java.util.concurrent.ThreadLocalRandom;

public class Race {
    private int distance;
    private int minSleepTime;
    private int maxSleepTime;

    public Race(int distance, int minSleepTime, int maxSleepTime) {
        this.distance = distance;
        this.minSleepTime = minSleepTime;
        this.maxSleepTime = maxSleepTime;
    }

    public int getDistance() {
        return distance;
    }

    public int getRandomSleepTime() {
        return ThreadLocalRandom.current().nextInt(minSleepTime, maxSleepTime + 1);
    }
}
