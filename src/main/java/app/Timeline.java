package app;

import java.time.LocalDate;

public class Timeline {
    private final LocalDate start;
    private final LocalDate end;

    public Timeline(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    boolean isCrossMonth() {
        return getStart().getMonthValue() != getEnd().getMonthValue();
    }
}
