import java.awt.Rectangle;
import java.util.ArrayList;

public class RectangleMain{
    public static void main(String[] args) {
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle(1, 2));
        rectangles.add(new Rectangle(3, 4));
        rectangles.add(new Rectangle(5, 6));
        rectangles.add(new Rectangle(2, 2));
        rectangles.add(new Rectangle(4, 5));

        BigRectangleFilter filter = new BigRectangleFilter();
        for (Rectangle rect : rectangles) {
            if (filter.accept(rect)) {
                System.out.println("Big Rectangle: " + rect);
            }
        }
    }
}
