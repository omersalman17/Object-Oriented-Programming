import oop.ex3.searchengine.*;
import java.util.*;

public class BoopingSite {
    private Hotel [] hotelsArray ;

    //MAGIC NUMBERS
    private final static int POSITIVE_LEGAL_LATITUDE_INPUT = 90;
    private final static int NEGATIVE_LEGAL_LATITUDE_INPUT = -90;
    private final static int POSITIVE_LEGAL_LONGITUDE_INPUT = 180;
    private final static int NEGATIVE_LEGAL_LONGITUDE_INPUT = -180;

   public BoopingSite(String name){
       this.hotelsArray = HotelDataset.getHotels(name);
   }

    public Hotel[] getHotelsInCityByRating(String city){
        Hotel [] suitedHotelsArray = this.getCityHotelsArray(city);
        Arrays.sort(suitedHotelsArray, new HotelsStarRatingComparator());
        return suitedHotelsArray;
    }

    public Hotel[] getHotelsByProximity(double latitude, double longitude){
        if (IllegalInput(latitude,longitude))
           return new Hotel[0];
        Hotel [] hotelsArray = this.hotelsArray.clone(); // TODO checking clone
        Arrays.sort(hotelsArray, new HotelsProximityComparator(latitude, longitude));
        return hotelsArray;
    }

    public Hotel[] getHotelsInCityByProximity(String city, double latitude, double longitude){
        if (IllegalInput(latitude,longitude))
            return new Hotel[0];
       Hotel [] suitedHotelsArray = this.getCityHotelsArray(city);
       Arrays.sort(suitedHotelsArray, new HotelsProximityComparator(latitude, longitude));
       return suitedHotelsArray;

    }

    private ArrayList<Hotel> getCityHotelsArrayList(String city){
        ArrayList<Hotel> cityHotelsArrayList = new ArrayList<Hotel>(0);
        for (Hotel hotel: this.hotelsArray){
            if (hotel.getCity().equals(city))
                cityHotelsArrayList.add(hotel);
        }
        return cityHotelsArrayList;
    }

    private Hotel[] getCityHotelsArray(String city) {
        ArrayList<Hotel> cityHotelsArrayList = getCityHotelsArrayList(city);
        return cityHotelsArrayList.toArray(new Hotel[0]);
    }

    private boolean IllegalInput(double latitude, double longitude){
        if (latitude > POSITIVE_LEGAL_LATITUDE_INPUT || latitude < NEGATIVE_LEGAL_LATITUDE_INPUT
                || longitude > POSITIVE_LEGAL_LONGITUDE_INPUT || longitude < NEGATIVE_LEGAL_LONGITUDE_INPUT)
            return true;
        else
            return false;
    }
}
