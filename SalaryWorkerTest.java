import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SalaryWorkerTest
{
    @Test
    public void testCalculateWeeklyPay()
    {
        SalaryWorker salaryWorker = new SalaryWorker("Alice", "Johnson", "S001", "Dr.", 1975, 52000);
        assertEquals(1000, salaryWorker.calculateWeeklyPay(40), 0.001);
        assertEquals(1000, salaryWorker.calculateWeeklyPay(50), 0.001); // The hoursWorked should not affect the pay
    }
    @Test
    public void testCalculateWeeklyPayWithDifferentSalary()
    {
        SalaryWorker salaryWorker = new SalaryWorker("Bob", "Smith", "S002", "Prof.", 1982, 78000);
        assertEquals(1500, salaryWorker.calculateWeeklyPay(40), 0.001);
        assertEquals(1500, salaryWorker.calculateWeeklyPay(30), 0.001);
    }
}
