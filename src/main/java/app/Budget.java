package app;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Budget {
    private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");

    public String yearMonth;
    public int amount;

    public Budget(String yearMonth, int amount) {
        this.yearMonth = yearMonth;
        this.amount = amount;
    }

    static YearMonth getYearMonth(DateTimeFormatter dateTimeFormatter, String yearMonth) {
        return YearMonth.parse(yearMonth, dateTimeFormatter);
    }

    boolean isMonthMatch(int month) {
        return month == getYearMonth(dateTimeFormatter, yearMonth).getMonthValue();
    }
}
