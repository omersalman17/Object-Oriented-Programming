import oop.ex3.spaceship.*;
import java.util.HashMap;
import java.util.Map;

public abstract class StorageUnit {

    private final static int  defaultActionSuccessReturnInt = 0;

    private final int capacity;
    protected Map<String, Integer> storageMap;
    private Item [] itemsArray;


    public StorageUnit(int capacity) {
        this.capacity = capacity;
        this.storageMap = new HashMap<String, Integer>();
        this.itemsArray = ItemFactory.createAllLegalItems();
    }

    public abstract int addItem(Item item, int n);

    public int getItemCount(String type) {
        if (this.storageMap.containsKey(type))
            return this.storageMap.get(type);
        else
            return defaultActionSuccessReturnInt;
    }

    public Map<String, Integer> getInventory() {
        return new HashMap<String, Integer>(this.storageMap);
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getAvailableCapacity() {
        int sum = 0;
        for (int i = 0; i< this.itemsArray.length; i++){
            if (this.storageMap.containsKey(this.itemsArray[i].getType()))
                sum += this.storageMap.get(this.itemsArray[i].getType()) * this.itemsArray[i].getVolume();
        }
        return this.getCapacity() - sum;
    }
}
