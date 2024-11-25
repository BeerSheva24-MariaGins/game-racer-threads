package telran.multithreading;

public class Result {
    private int place;
    private int racerNumber;
    private long runningTime;

    public Result(int place, int racerNumber, long runningTime) {
        this.place = place;
        this.racerNumber = racerNumber;
        this.runningTime = runningTime;
    }

    public int getPlace() {
        return place;
    }

    public int getRacerNumber() {
        return racerNumber;
    }

    public long getRunningTime() {
        return runningTime;
    }

    @Override
    public String toString() {
        return String.format("%d\t\t%d\t\t%d", place, racerNumber, runningTime);
    }
}
