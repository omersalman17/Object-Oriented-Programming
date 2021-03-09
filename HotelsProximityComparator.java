import oop.ex3.searchengine.*;
import java.util.Comparator;

public class HotelsProximityComparator implements Comparator<Hotel>{
    private double pointLatitude;
    private double pointLongitude;
    private final int POW2 = 2; //MAGIC NUMBER

    public HotelsProximityComparator(double pointLatitude, double pointLongitude){
        this.pointLatitude = pointLatitude;
        this.pointLongitude = pointLongitude;
    }

    public int compare(Hotel hotel1, Hotel hotel2){
        double latitudeH1, latitudeH2, longitudeH1, longitudeH2;
        latitudeH1 = hotel1.getLatitude();
        latitudeH2 = hotel2.getLatitude();
        longitudeH1 = hotel1.getLongitude();
        longitudeH2 = hotel2.getLongitude();
        double latitudeFactorH1 = Math.pow((latitudeH1 - this.pointLatitude) , this.POW2);
        double longitudeFactorH1 = Math.pow((longitudeH1 - this.pointLongitude), this.POW2);
        double distanceH1 = Math.sqrt(latitudeFactorH1 + longitudeFactorH1);
        double latitudeFactorH2 = Math.pow((latitudeH2 - this.pointLatitude) , this.POW2);
        double longitudeFactorH2 = Math.pow((longitudeH2 - this.pointLongitude), this.POW2);
        double distanceH2 = Math.sqrt(latitudeFactorH2 + longitudeFactorH2);
        if (distanceH1 > distanceH2)
            return 1;
        else if (distanceH1 < distanceH2)
            return -1;
        else{
            int hotel1NumPOI = hotel1.getNumPOI();
            int hotel2NumPOI = hotel2.getNumPOI();
            return -(hotel1NumPOI - hotel2NumPOI);
        }
    }
}
