package app;

import app.repository.IBudgetRepo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Accounting {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");
    private IBudgetRepo budget;

    public Accounting(IBudgetRepo budget) {
        this.budget = budget;
    }

    private boolean isMonthMatch(Budget budget, int month) {
        return month == Budget.getYearMonth(dateTimeFormatter, budget.yearMonth).getMonthValue();
    }

    private Budget getBudgetByDate(LocalDate date) {
        List<Budget> budgets = budget.getAll();
        int month = date.getMonthValue();
        for (Budget budget : budgets) {
            if (isMonthMatch(budget, month)) {
                return budget;
            }
        }
        return null;
    }

    private double getUnCrossMonthBudget(Timeline timeline) {
        Budget monthBudget = getBudgetByDate(timeline.getStart());
        if (monthBudget == null) {
            return 0;
        }
        Period p = Period.between(timeline.getStart(), timeline.getEnd());
        return (getSingleDayBudget(timeline.getStart())) * (p.getDays() + 1);
    }

    private double getCrossMonthBudget(Timeline timeline) {
        int intervalAmount = 0;
        if (Period.between(timeline.getStart(), timeline.getEnd()).getMonths() > 1) {
            LocalDate intervalDate = timeline.getStart();
            for (int i = timeline.getStart().getMonthValue(); i < timeline.getEnd().getMonthValue() - 1; i++) {
                intervalAmount += getBudgetByDate(intervalDate.plusMonths(1)).amount;
            }
        }
        return getStartMonthAmount(timeline.getStart()) + getEndMonthAmount(timeline.getEnd()) + intervalAmount;
    }

    private double getEndMonthAmount(LocalDate date) {
        int daysInEnd = date.getDayOfMonth();
        return getSingleDayBudget(date) * daysInEnd;
    }

    private double getStartMonthAmount(LocalDate date) {
        int daysInStart = date.lengthOfMonth() - date.getDayOfMonth() + 1;
        return getSingleDayBudget(date) * daysInStart;

    }

    private double getSingleDayBudget(LocalDate date) {
        int amount = getBudgetByDate(date).amount;
        return amount / date.lengthOfMonth();
    }

    public double totalAmount(Timeline timeline) {
        if (timeline.getStart().isAfter(timeline.getEnd())) {
            // 開始大於結束當錯誤輸入回傳0
            return 0;
        }
        return timeline.isCrossMonth() ?
                getCrossMonthBudget(timeline) : getUnCrossMonthBudget(timeline);
    }
}
