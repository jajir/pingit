package jirout.pingit;

public class Duration {

    private final long miliseconds;

    public static Duration of(final long miliseconds) {
        return new Duration(miliseconds);
    }

    private Duration(final long miliseconds) {
        this.miliseconds = miliseconds;
    }

    @Override
    public String toString() {
        return Long.toString(miliseconds);
    }

    long getMiliseconds() {
        return miliseconds;
    }

}
