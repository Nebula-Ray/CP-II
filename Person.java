public class Person
{
    private String firstName;
    private String lastName;
    private String ID;
    private String title;
    private int YOB;

    public Person(String firstName, String lastName, String ID, String title, int YOB)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.title = title;
        this.YOB = YOB;
    }
    public String toCSV()
    {
        return firstName + "," + lastName + "," + ID + "," + title + "," + YOB;
    }
    public String toXML()
    {
        return "<person><firstName>" + firstName + "</firstName><lastName>" + lastName +
                "</lastName><ID>" + ID + "</ID><title>" + title + "</title><YOB>" + YOB + "</YOB></person>";
    }
    public String toJSON()
    {
        return "{ \"firstName\": \"" + firstName + "\", \"lastName\": \"" + lastName + "\", " +
                "\"ID\": \"" + ID + "\", \"title\": \"" + title + "\", \"YOB\": " + YOB + " }";
    }
}
