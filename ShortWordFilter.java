public class ShortWordFilter implements Filter
{
    @Override
    public boolean accept(Object x)
    {
        String s = (String) x;
        if (s.length() < 5)
            return true;
        else
            return false;
    }
}
