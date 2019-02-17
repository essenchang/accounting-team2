import app.Accounting;
import app.repository.Budget;
import app.repository.IBudgetRepo;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountingTest {

    private FakeBudgetRepo fakeBudgetRepo = new FakeBudgetRepo();
    private final Accounting accounting = new Accounting(fakeBudgetRepo);

    @Test
    public void noBudget() {
        assertEquals(0, accounting.getAmount(LocalDate.of(2019,4,1),
                LocalDate.of(2019,4,1)),
                0.0);
    }


    @Test
    public void periodInsideBudgetMonth() {


        fakeBudgetRepo.setBudgets(new Budget("201904", 30));
        assertEquals(1, accounting.getAmount(LocalDate.of(2019,4,1),
                LocalDate.of(2019,4,1)),
                0.0);
    }

    class FakeBudgetRepo implements IBudgetRepo {

        private List<Budget> budgets = new ArrayList<>();

        @Override
        public List<Budget> getAll() {
            return budgets;
        }

        public void setBudgets(Budget... budgets) {

            this.budgets.clear();
            this.budgets.addAll(Arrays.asList(budgets));

        }
    }
}






