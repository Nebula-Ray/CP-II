public class Worker extends Person
{
    private double hourlyPayRate;

    public Worker(String firstName, String lastName, String ID, String title, int YOB, double hourlyPayRate)
    {
        super(firstName, lastName, ID, title, YOB);
        this.hourlyPayRate = hourlyPayRate;
    }
    public double calculateWeeklyPay(double hoursWorked)
    {
        double regularHours = Math.min(40, hoursWorked);
        double overtimeHours = Math.max(0, hoursWorked - 40);
        return (regularHours * hourlyPayRate) + (overtimeHours * hourlyPayRate * 1.5);
    }
    public void displayWeeklyPay(double hoursWorked)
    {
        double regularHours = Math.min(40, hoursWorked);
        double overtimeHours = Math.max(0, hoursWorked - 40);
        double regularPay = regularHours * hourlyPayRate;
        double overtimePay = overtimeHours * hourlyPayRate * 1.5;
        double totalPay = regularPay + overtimePay;

        System.out.println("Regular hours: " + regularHours + " Pay: " + regularPay);
        System.out.println("Overtime hours: " + overtimeHours + " Pay: " + overtimePay);
        System.out.println("Total pay: " + totalPay);
    }
    @Override
    public String toCSV()
    {
        return super.toCSV() + "," + hourlyPayRate;
    }
    @Override
    public String toXML()
    {
        return super.toXML().replace("</person>", "<hourlyPayRate>" + hourlyPayRate + "</hourlyPayRate></person>");
    }
    @Override
    public String toJSON()
    {
        return super.toJSON().replace("}", ", \"hourlyPayRate\": " + hourlyPayRate + " }");
    }
}
