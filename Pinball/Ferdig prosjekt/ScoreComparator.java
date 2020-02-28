import java.util.*;

public class ScoreComparator implements Comparator<Spiller> {
    @Override
    public int compare(Spiller o1, Spiller o2) {
        // return o1.getScore().compareTo(o2.getScore());
        return Integer.compare(o1.getScore(), o2.getScore());
    }
}