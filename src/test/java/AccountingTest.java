import app.Accounting;
import app.repository.IBudgetRepo;
import com.sun.media.sound.SF2Modulator;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountingTest {

    @Test
    public void noBudget() {
        Accounting accounting = new Accounting();
        assertEquals(0, accounting.getAmount(LocalDate.of(2019,4,1),
                LocalDate.of(2019,4,1)),
                0.0);
    }



}






