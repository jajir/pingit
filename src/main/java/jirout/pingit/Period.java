package jirout.pingit;

public class Period {

    private final long start;

    private long stop;

    private Period(final long start) {
        this.start = start;
    }

    public static Period start() {
        return new Period(System.currentTimeMillis());
    }

    public void end() {
        this.stop = System.currentTimeMillis();
    }

    public Duration toDuration() {
        return Duration.of(stop - start);
    }

}
