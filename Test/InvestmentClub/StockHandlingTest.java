package InvestmentClub;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StockHandlingTest {

    @Test
    public void testTimeStamp() {
        StockHandling ts = new StockHandling();
        assertEquals("27-11-2025", ts.currentDate());
    }
}