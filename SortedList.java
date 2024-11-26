import java.util.ArrayList;

public class SortedList {
    private ArrayList<String> list;

    public SortedList() {
        list = new ArrayList<>();
    }


    public void add(String element) {
        int index = binarySearch(element);
        list.add(index, element);
    }


    public int binarySearch(String element) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int comparison = list.get(mid).compareTo(element);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }


    public ArrayList<String> getList() {
        return list;
    }
}
