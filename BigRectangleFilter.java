import java.awt.*;

public class BigRectangleFilter implements Filter
{
    @Override
    public boolean accept(Object x)
    {
        Rectangle r =(Rectangle) x;
        double perimeter = 2 * r.height + 2 * r.width;
        if(perimeter > 10)
            return true;
        else
            return false;
    }
}
