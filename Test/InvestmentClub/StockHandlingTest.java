package InvestmentClub;


import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StockHandlingTest {

    // manuel, skriv nuværende dato for at teste.
    @Test
    public void testTimeStamp() {
        StockHandling ts = new StockHandling();

        //indsat dato - "dd.MM.yyyy"
        assertEquals("04.12.2025" , ts.currentDate());
    }
}