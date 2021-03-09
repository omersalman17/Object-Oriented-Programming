import oop.ex3.spaceship.*;
import java.util.ArrayList;

public class Locker extends StorageUnit {
    protected static LongTermStorage longTermStorage = new LongTermStorage();

    private  final static String errorMessage = "Error: Your request cannot be completed at this time. ";
    private final static String actionSuccessWarningMessage = "Warning: Action successful, but has caused" +
            " items to be moved to storage";

    //MAGIC NUMBERS
    private final static int DEFAULT_ERROR_RETURN_INT = -1;
    private final static int DEFAULT_ACTION_SUCCESS_RETURN_INT = 0;
    private final static int ACTION_SUCCES_WARNING_RETURN_INT = 1;
    private final int FIFTY_PRECENT_CAPACTIY = (int)(0.5 * this.getCapacity());
    private final int TWENTY_PRECENT_CAPACTIY = (int)(0.2 * this.getCapacity());



    public Locker(int capacity){
        super(capacity);
    }

    public int removeItem(Item item, int n){
        if (n == 0)
            if (!this.storageMap.containsKey(item.getType()))
                return this.defaultErrorMessage();
            else
                return DEFAULT_ACTION_SUCCESS_RETURN_INT;
        else if (n < 0) {
            return this.removeNegativeItemsNumberAction(item);
        }
        if (this.storageMap.containsKey(item.getType())) {
            if (n > this.storageMap.get(item.getType()))
                return removeNotExistingItemAmount(item, n);
            else {
                int newItemQuantity = getItemCount(item.getType()) - n;
                if (newItemQuantity == 0)
                    this.storageMap.remove(item.getType());
                else
                    this.storageMap.put(item.getType(), newItemQuantity);
                return DEFAULT_ACTION_SUCCESS_RETURN_INT;
            }
        }
        else
            return DEFAULT_ERROR_RETURN_INT;
    }

    @Override
    public int addItem(Item item, int n) {
        if (this.baseballBatAndFootballTogether(item, n))
            return -2;
        if (n < 0)
            return DEFAULT_ERROR_RETURN_INT;
        int lockerItemCapacity;
        int lockerItemQuantity;
        if (this.storageMap.containsKey(item.getType())){
            lockerItemQuantity = this.getItemCount(item.getType());
            lockerItemCapacity = lockerItemQuantity * item.getVolume();
        }
        else{
            lockerItemQuantity = 0;
            lockerItemCapacity = 0;
        }
        int itemRequiredCapacity = item.getVolume() * n;
        if (this.getAvailableCapacity() >= itemRequiredCapacity){
            if (itemRequiredCapacity + lockerItemCapacity > FIFTY_PRECENT_CAPACTIY){
                int itemQuantityToAddLongStorage = n;
                if (lockerItemCapacity + item.getVolume() > TWENTY_PRECENT_CAPACTIY){
                    itemQuantityToAddLongStorage += lockerItemQuantity;
                    lockerItemQuantity = 0;
                    lockerItemCapacity = 0;
                }
                while(lockerItemCapacity + item.getVolume() <= TWENTY_PRECENT_CAPACTIY){
                    lockerItemQuantity ++;
                    itemQuantityToAddLongStorage --;
                    lockerItemCapacity = lockerItemQuantity * item.getVolume();
                }
                if (Locker.longTermStorage.addItem(item, itemQuantityToAddLongStorage) == 0){
                    if (lockerItemQuantity == 0)
                        this.storageMap.remove(item.getType());
                    else
                        this.storageMap.put(item.getType(), lockerItemQuantity);
                    return actionSuccessWarning();
                }
                else
                    return noRoomInLongTermStorage(itemQuantityToAddLongStorage, item);
            }
            else {
                if (n + lockerItemQuantity == 0)
                    this.storageMap.remove(item.getType());
                else
                    this.storageMap.put(item.getType(), n + lockerItemQuantity);
                return DEFAULT_ACTION_SUCCESS_RETURN_INT;
            }
        }
        else
            return noRoomInLocker(n, item);
    }

    protected static LongTermStorage getLongTermStorage(){
        return Locker.longTermStorage;
    }


    // tried making it more modular than concrete ifs
    private Boolean baseballBatAndFootballTogether(Item item, int n) {
        ArrayList<String> ItemsTypesArrayList = new ArrayList<String>();
        ItemsTypesArrayList.addAll(this.storageMap.keySet());
        ItemsTypesArrayList.add(item.getType());
        boolean containsFootball = ItemsTypesArrayList.contains("football");
        boolean containsBaseballBat = ItemsTypesArrayList.contains("baseball bat");
        boolean containsBoth = containsFootball && containsBaseballBat;
        if (containsBoth)
            System.out.println(errorMessage +
                    "Problem: the locker cannot contain items of type " + item.getType() +
                    ", as it contains a contradicting item");
        ItemsTypesArrayList.clear();
        return containsBoth;
        }

    private int defaultErrorMessage(){
        System.out.println(errorMessage);
        return DEFAULT_ERROR_RETURN_INT;
    }

    private int removeNegativeItemsNumberAction(Item item){
        System.out.println(errorMessage +
                "Problem: cannot remove a negative number of items of type " + item.getType());
        return DEFAULT_ERROR_RETURN_INT;
    }

    private int removeNotExistingItemAmount(Item item, int n){
        System.out.println(errorMessage + "Problem: the locker does not contain " + n +
                "items of type " + item.getType());
        return DEFAULT_ERROR_RETURN_INT;
    }

    private int actionSuccessWarning(){
        System.out.println(actionSuccessWarningMessage);
        return ACTION_SUCCES_WARNING_RETURN_INT;
    }

    private int noRoomInLongTermStorage(int itemQuantityToAddLongStorage, Item item){
        System.out.println(errorMessage + "Problem: no room for " + itemQuantityToAddLongStorage +
                " items of" + " type " + item.getType());
        return DEFAULT_ERROR_RETURN_INT;
    }

    private int noRoomInLocker(int n, Item item){
        System.out.println(errorMessage + "Problem: no room for " + n +
                " items of type " + item.getType());
        return DEFAULT_ERROR_RETURN_INT;
    }
}