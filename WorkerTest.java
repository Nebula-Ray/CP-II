import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WorkerTest
{
    @Test
    public void testCalculateWeeklyPay()
    {
        Worker worker = new Worker("John", "Doe", "W001", "Mr.", 1985, 20.00);
        assertEquals(800, worker.calculateWeeklyPay(40));
        assertEquals(950, worker.calculateWeeklyPay(45)); // 800 + (5 * 30)
    }
}
