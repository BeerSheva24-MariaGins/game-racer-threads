package telran.multithreading;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.StandardInputOutput;

public class Main {
    private static Race race;
    private static Racer[] racers;

    public static void main(String[] args) {
        InputOutput io = new StandardInputOutput();
        Menu menu = new Menu("RACING GAME", getMenuItems(io));
        menu.perform(io);
    }

    private static Item[] getMenuItems(InputOutput io) {
        return new Item[] {
                Item.of("Set race parameters", i -> setRaceParameters(io)),
                Item.of("Start race!!!", i -> startRace(io)),
                Item.ofExit()
        };
    }

    private static void setRaceParameters(InputOutput io) {
        int nRacers = io.readNumberRange("Enter number of racers (1 - 10000):", "Invalid number of racers", 1, 10000)
                .intValue();
        int distance = io.readNumberRange("Enter race distance (number of iterations (1 - 10000)):", "Invalid distance",
                1, 10000).intValue();
        int minSleepTime = io.readNumberRange("Enter min sleep time, ms (1 - 1000):", "Invalid sleep time", 1, 1000)
                .intValue();
        int maxSleepTime = io.readNumberRange("Enter max sleep time, ms (min sleep time - 5000):", "Invalid sleep time",
                minSleepTime, 5000).intValue();

        race = new Race(distance, minSleepTime, maxSleepTime);
        racers = new Racer[nRacers];
        Racer.setRacers(racers);
        io.writeLine("Race parameters were installed.");
    }

    private static void startRace(InputOutput io) {
        if (race == null || racers == null) {
            io.writeLine("Please set race parameters first.");
            return;
        }

        for (int i = 0; i < racers.length; i++) {
            racers[i] = new Racer(race, i + 1);
            racers[i].start();
        }

        while (Racer.getWinner() == 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
        io.writeLine("All racers have finished. Thanks for watching the race!");
    }
}
