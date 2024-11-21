package telran.multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class Racer extends Thread {
    private Race race;
    private int number;
    private static AtomicInteger winner = new AtomicInteger(0);
    private static Racer[] racers;

    public Racer(Race race, int number) {
        this.race = race;
        this.number = number;
        this.setDaemon(true);
    }

    public static void setRacers(Racer[] racers) {
        Racer.racers = racers;
    }

    public static int getWinner() {
        return winner.get();
    }

    @Override
    public void run() {
        for (int i = 0; i < race.getDistance(); i++) {
            if (winner.get() > 0) {
                return;
            }
            try {
                Thread.sleep(race.getRandomSleepTime());
            } catch (InterruptedException e) {
                return;
            }
            System.out.printf("Racer %d is at iteration %d%n", number, i + 1);
        }

        if (winner.compareAndSet(0, number)) {
            System.out.printf("Congratulations to Racer %d - winner!%n", number);

            stopAllRacers();
        }
    }

    private void stopAllRacers() {
        for (Racer racer : racers) {
            if (racer != null && racer != this) {
                racer.interrupt();
            }
        }
    }
}
