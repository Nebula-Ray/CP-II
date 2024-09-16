import java.util.ArrayList;

public class InheritanceDemo
{
    public static void main(String[] args)
    {
        Worker worker1 = new Worker("John", "Doe", "W001", "Mr.", 1985, 20.00);
        Worker worker2 = new Worker("Jane", "Doe", "W002", "Ms.", 1990, 22.00);
        Worker worker3 = new Worker("Jim", "Beam", "W003", "Mr.", 1980, 18.50);
        SalaryWorker salaryWorker1 = new SalaryWorker("Alice", "Johnson", "S001", "Dr.", 1975, 52000);
        SalaryWorker salaryWorker2 = new SalaryWorker("Bob", "Smith", "S002", "Prof.", 1982, 60000);
        SalaryWorker salaryWorker3 = new SalaryWorker("Charlie", "Brown", "S003", "Mr.", 1992, 48000);
        ArrayList<Worker> workers = new ArrayList<>();
        workers.add(worker1);
        workers.add(worker2);
        workers.add(worker3);
        workers.add(salaryWorker1);
        workers.add(salaryWorker2);
        workers.add(salaryWorker3);
        simulateWeeklyPay(workers, 40);
        simulateWeeklyPay(workers, 50);
        simulateWeeklyPay(workers, 40);
    }
    public static void simulateWeeklyPay(ArrayList<Worker> workers, double hoursWorked)
    {
        System.out.println("\nHours worked: " + hoursWorked);
        for (Worker worker : workers)
        {
            worker.displayWeeklyPay(hoursWorked);
        }
    }
}
