package app.repository;

public class Budget {
    private final String yearMonth;
    private final double amount;

    public Budget(String yearMonth, double amount) {
        this.yearMonth = yearMonth;
        this.amount = amount;
    }
}
