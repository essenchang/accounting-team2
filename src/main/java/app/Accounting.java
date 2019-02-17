package app;

import app.repository.IBudgetRepo;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Accounting {
    private IBudgetRepo budgetRepo;

    public Accounting(IBudgetRepo budgetRepo) {

        this.budgetRepo = budgetRepo;
    }

    public double getAmount(LocalDate start, LocalDate end) {
        if (budgetRepo.getAll().isEmpty()) {
            return 0;
        }

        return DAYS.between(start, end)+1;
    }
}

