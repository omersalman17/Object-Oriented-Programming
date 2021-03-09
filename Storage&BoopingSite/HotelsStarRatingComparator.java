import oop.ex3.searchengine.*;
import java.util.Comparator;

public class HotelsStarRatingComparator implements Comparator<Hotel> {
    public int compare(Hotel hotel1, Hotel hotel2) {
        int starRatingHotel1 = hotel1.getStarRating();
        int starRatingHotel2 = hotel2.getStarRating();
        int result = -(starRatingHotel1 - starRatingHotel2);
        if (result == 0)
            return hotel1.getPropertyName().compareTo(hotel2.getPropertyName());
        else
            return result;
    }
}

