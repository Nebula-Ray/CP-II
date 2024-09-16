public class SalaryWorker extends Worker
{
    private double annualSalary;

    public SalaryWorker(String firstName, String lastName, String ID, String title, int YOB, double annualSalary)
    {
        super(firstName, lastName, ID, title, YOB, 0);
        this.annualSalary = annualSalary;
    }
    @Override
    public double calculateWeeklyPay(double hoursWorked)
    {
        return annualSalary / 52;
    }
    @Override
    public void displayWeeklyPay(double hoursWorked)
    {
        System.out.println("Weekly pay: " + calculateWeeklyPay(hoursWorked) + " (Fraction of annual salary)");
    }
    @Override
    public String toCSV()
    {
        return super.toCSV() + "," + annualSalary;
    }
    @Override
    public String toXML()
    {
        return super.toXML().replace("</person>", "<annualSalary>" + annualSalary + "</annualSalary></person>");
    }
    @Override
    public String toJSON()
    {
        return super.toJSON().replace("}", ", \"annualSalary\": " + annualSalary + " }");
    }
}
