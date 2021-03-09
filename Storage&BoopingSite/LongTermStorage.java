import oop.ex3.spaceship.*;


public class LongTermStorage extends StorageUnit {
    private static int ONE_THOUSAND_STORAGE_UNITS = 1000;
    private final static int  defaultActionSuccessReturnInt = 0;
    private final static int  defaultErrorReturnInt = -1;

    public LongTermStorage(){
        super(ONE_THOUSAND_STORAGE_UNITS);
    }

    @Override
    public int addItem(Item item, int n) {
        if (n == 0)
            return defaultActionSuccessReturnInt;
        else if (n < 0)
            return defaultErrorReturnInt;
        int itemQuantity = 0;
        if (this.storageMap.containsKey(item.getType()))
            itemQuantity = this.getItemCount(item.getType());
        if ((item.getVolume() * n) > this.getAvailableCapacity()) {
            System.out.println("Error: Your request cannot be completed at this time. " +
                    "Problem: no room for " + n + " Items of type " + item.getType());
            return defaultErrorReturnInt;
        }
        else {
            this.storageMap.put(item.getType(), n + itemQuantity);
            return defaultActionSuccessReturnInt;
        }
    }

    public void resetInventory(){
        this.storageMap.clear();
    }

}
