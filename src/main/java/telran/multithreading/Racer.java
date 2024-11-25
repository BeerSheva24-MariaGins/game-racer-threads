package telran.multithreading;

import java.util.Random;

public class Racer extends Thread {
    private Race race;
    private int number;
    private static int position = 1;

    public Racer(Race race, int number) {
        this.race = race;
        this.number = number;
    }

    @Override
    public void run() {
        int minSleep = race.getMinSleep();
        int maxSleep = race.getMaxSleep();
        int distance = race.getDistance();
        Random random = new Random();

        for (int i = 0; i < distance; i++) {
            try {
                sleep(random.nextInt(minSleep, maxSleep + 1));
                System.out.printf("%d - step %d\n", number, i);
            } catch (InterruptedException e) {
                
            }
        }

        long endTime = System.currentTimeMillis(); 
        long runningTime = endTime - race.getStartTime(); 

        synchronized (Race.class) {
            race.addResult(new Result(position++, number, runningTime));
        }

        race.winner.compareAndSet(-1, number);
    }
}
