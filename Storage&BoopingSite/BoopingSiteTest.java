import oop.ex3.searchengine.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoopingSiteTest {
    private static final String hotelsDataSet = "hotels_dataset.txt";
    private static final String llegalCity = "manali";
    private static final String illegalCity = "jerusalem";
    private static Hotel [] testHotelsArray;
    private static final double legalLatitude = 20.0;
    private static final double illegalLatitude = 100;
    private static final double legalLongitude = 30.0;
    private static final double illegalLongitude = 200;
    private static final BoopingSite testBoopingSite = new BoopingSite(hotelsDataSet);

    @Test
    public void cityHotelsCorrectness(){
        testHotelsArray =  testBoopingSite.getHotelsInCityByRating(llegalCity);
        boolean result = true;
        for (Hotel hotel: testHotelsArray){
            if (!(hotel.getCity().equals(llegalCity))){
                result = false;
                break;
            }
        }
        assertTrue("city hotels array contains hotel not in city", result);
    }

    @Test
    public void cityHotelsForIllegalCity(){
        testHotelsArray =  testBoopingSite.getHotelsInCityByRating(illegalCity);
        assertEquals("hotels array contain hotels for illegal city", 0,
                testHotelsArray.length);
    }

    @Test
    public void cityHotelsStarRatingCorrectness(){
        testHotelsArray =  testBoopingSite.getHotelsInCityByRating(llegalCity);
        boolean result = true;
        for (int i = 0; i < testHotelsArray.length - 1; i++) {
            if (testHotelsArray[i].getStarRating() < testHotelsArray[i+1].getStarRating()) {
                result = false;
                break;
            }
        }
        assertTrue("incorrect star rating hotels sort", result);
    }

    @Test
    public void cityHotelsNameSortingCorrectness(){
        testHotelsArray =  testBoopingSite.getHotelsInCityByRating(llegalCity);
        boolean result = true;
        for (int i = 0; i < testHotelsArray.length - 1; i++) {
            if (testHotelsArray[i].getStarRating() == testHotelsArray[i+1].getStarRating()) {
                String firstHotelName = testHotelsArray[i].getPropertyName();
                String secondHotelName = testHotelsArray[i + 1].getPropertyName();
                if (firstHotelName.compareTo(secondHotelName) > 0)
                    result = false;
                    break;
            }
        }
        assertTrue("incorrect name sort order for hotels with equal star rating", result);
    }

    @Test
    public void proximityHotelsSortingCorrectness(){
        testHotelsArray = testBoopingSite.getHotelsByProximity(legalLatitude, legalLongitude);
        assertTrue("hotels sorting by proximity incorrect", distanceSortedHotelsArray());
    }

    @Test
    public void proximityHotelsInCitySortingCorrectness(){
        testHotelsArray = testBoopingSite.getHotelsInCityByProximity(llegalCity, legalLatitude,
                legalLongitude);
        assertTrue("hotels sorting by proximity incorrect", distanceSortedHotelsArray());
    }

    @Test
    public void pointOfInterestHotelsSortingCorrectness(){
        testHotelsArray = testBoopingSite.getHotelsByProximity(legalLatitude, legalLongitude);
        assertTrue("hotels sorting by point of interest incorrect",
                pointOfInterestSortedHotelsArray());
    }

    @Test
    public void pointOfInterestHotelsInCitySortingCorrectness(){
        testHotelsArray = testBoopingSite.getHotelsInCityByProximity(llegalCity, legalLatitude,
                legalLongitude);
        assertTrue("hotels sorting by point of interest incorrect",
                pointOfInterestSortedHotelsArray());
    }

    private double getHotelDistanceFromPoint(Hotel hotel){
        double latitudeFactor = Math.pow((hotel.getLatitude() - legalLatitude) , 2);
        double longitudeFactor = Math.pow((hotel.getLongitude() - legalLongitude), 2);
        return Math.sqrt(latitudeFactor + longitudeFactor);
    }

    private boolean distanceSortedHotelsArray(){
        for (int i = 0; i < testHotelsArray.length - 1; i++) {
            double firstHotelDistance = getHotelDistanceFromPoint(testHotelsArray[i]);
            double secondHotelDistance = getHotelDistanceFromPoint(testHotelsArray[i+1]);
            if (firstHotelDistance > secondHotelDistance)
                return false;
        }
        return true;
    }

    private boolean pointOfInterestSortedHotelsArray(){
        for (int i = 0; i < testHotelsArray.length - 1; i++){
            double firstHotelDistance = getHotelDistanceFromPoint(testHotelsArray[i]);
            double secondHotelDistance = getHotelDistanceFromPoint(testHotelsArray[i+1]);
            if (firstHotelDistance == secondHotelDistance){
                if (testHotelsArray[i].getNumPOI() < testHotelsArray[i+1].getNumPOI())
                    return false;
            }
        }
        return true;
    }

    @Test
    public void proximityHotelsArrayIllegalLatitude(){
        testHotelsArray = testBoopingSite.getHotelsByProximity(illegalLatitude, legalLongitude);
        assertEquals("sorted array contains hotels according to illegal latitude",
                0, testHotelsArray.length);
    }

    @Test
    public void proximityHotelsByCityIllegalLatitude(){
        testHotelsArray = testBoopingSite.getHotelsInCityByProximity(llegalCity, illegalLatitude,
                legalLongitude);
        assertEquals("sorted array contains hotels in city according to illegal latitude",
                0, testHotelsArray.length);
    }

    @Test
    public void proximityHotelsArrayIllegalLongitude(){
        testHotelsArray = testBoopingSite.getHotelsByProximity(legalLatitude, illegalLongitude);
        assertEquals("sorted array contains hotels according to illegal longitude",
                0, testHotelsArray.length);
    }

    @Test
    public void proximityHotelsByCityIllegalLongitude() {
        testHotelsArray = testBoopingSite.getHotelsInCityByProximity(llegalCity, legalLatitude,
                illegalLongitude);
        assertEquals("sorted array contains hotels in city according to illegal longitude",
                0, testHotelsArray.length);
    }

    @Test
    public void proximityHotelsArrayIllegalLatitudeAndLongitude(){
        testHotelsArray = testBoopingSite.getHotelsByProximity(illegalLatitude, illegalLongitude);
        assertEquals("sorted array contains hotels according to illegal longitude",
                0, testHotelsArray.length);
    }

    @Test
    public void proximityHotelsInCityIllegalLatitudeAndLongitude(){
        testHotelsArray = testBoopingSite.getHotelsInCityByProximity(llegalCity, illegalLatitude,
                illegalLongitude);
        assertEquals("sorted array contains hotels according to illegal longitude",
                0, testHotelsArray.length);
    }

    @Test
    public void proximityHotelsInCitylegalCity(){
        testHotelsArray = testBoopingSite.getHotelsInCityByProximity(llegalCity, legalLatitude,
                legalLongitude);
        boolean result = true;
        for(Hotel hotel: testHotelsArray){
            if(!hotel.getCity().equals(llegalCity)){
                result = false;
                break;
            }
        }
        assertTrue("array contains hotel not in the same city",result);
    }

    @Test
    public void proximityHotelsInCityIllegalCity() {
        testHotelsArray = testBoopingSite.getHotelsInCityByProximity(illegalCity, legalLatitude,
                legalLongitude);
        assertEquals("array contains hotel in illegal city",0, testHotelsArray.length);
    }

    @Test
    public void proximityHotelsInCityAllIllegal() {
        testHotelsArray = testBoopingSite.getHotelsInCityByProximity(illegalCity, illegalLatitude,
                illegalLongitude);
        boolean result = true;
        if (testHotelsArray.length != 0)
            result = false;
        assertEquals("array contains hotel when given all illegal parameters",0,
                testHotelsArray.length);
    }
}

