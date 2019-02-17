package app;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Budget {
    private final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");

    public String yearMonth;

    public int getAmount() {
        return amount;
    }

    private int amount;

    public Budget(String yearMonth, int amount) {
        this.yearMonth = yearMonth;
        this.setAmount(amount);
    }

    static YearMonth getYearMonth(DateTimeFormatter dateTimeFormatter, String yearMonth) {
        return YearMonth.parse(yearMonth, dateTimeFormatter);
    }

    boolean isMonthMatch(int month) {
        return month == getYearMonth(dateTimeFormatter, yearMonth).getMonthValue();
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
