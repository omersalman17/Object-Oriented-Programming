import oop.ex3.spaceship.*;
import org.junit.*;
import java.util.HashMap;
import static org.junit.Assert.*;

public class LockerTest {
    private static Locker testLocker;
    private static Item volume2Item = ItemFactory.createSingleItem("baseball bat");
    private static Item volume3Item = ItemFactory.createSingleItem("helmet, size 1");
    private static Item volume5Item = ItemFactory.createSingleItem("helmet, size 3");
    private static Item volume10Item = ItemFactory.createSingleItem("spores engine");
    private static Item volume4Item = ItemFactory.createSingleItem("football");
    private static Item illegalItem = ItemFactory.createSingleItem("sword");
    private static HashMap<String, Integer> testMap;

    //MAGIC NUMBERS
    private final static int TEST_LOCKER_CAPACITY = 40;
    private final static int LONG_TERM_STORAGE = 1000;
    private final static int DEFAULT_ERROR_RETURN_INT = -1;
    private final static int ACTION_SUCCESS_RETURN_INT = 0;
    private final static int ACTION_SUCCES_WARNING_RETURN_INT = 1;

    @Before
    public void createTestObjects(){
        testLocker = new Locker(TEST_LOCKER_CAPACITY);
        testMap = new HashMap<String, Integer>();
    }

    @Test
    public void testGetCapacity(){
        assertEquals("locker capacity incorrect",TEST_LOCKER_CAPACITY, testLocker.getCapacity());
    }

    @Test
    public void getCapacityWhenLockerFull(){
        testLocker.addItem(volume5Item, 3);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 2);
        testLocker.addItem(volume2Item, 1);
        assertEquals("locker capacity incorrect", TEST_LOCKER_CAPACITY, testLocker.getCapacity());
    }

    @Test
    public void getCapacityAfterAddingItems(){
        testLocker.addItem(volume5Item, 1);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 1);
        testLocker.addItem(volume2Item, 1);
        testLocker.addItem(volume10Item, 0);
        assertEquals("locker capacity incorrect", TEST_LOCKER_CAPACITY, testLocker.getCapacity());
    }

    @Test(expected = NullPointerException.class)
    public void getCapacityAfterAddingIllegalItem(){
        testLocker.addItem(volume5Item, 1);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 1);
        testLocker.addItem(illegalItem, 1);
        assertEquals("locker capacity incorrect", TEST_LOCKER_CAPACITY, testLocker.getCapacity());
    }

    @Test
    public void getCapacityAfterAddingItemFailed(){
        testLocker.addItem(volume5Item, 1);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 1);
        testLocker.addItem(volume2Item, 1);
        testLocker.addItem(volume10Item, 3); // adding item fails
        assertEquals("locker capacity incorrect", TEST_LOCKER_CAPACITY, testLocker.getCapacity());
    }

    @Test
    public void getAvailableCapacityEmptyLocker(){
        assertEquals("locker available capacity incorrect", TEST_LOCKER_CAPACITY,
                testLocker.getAvailableCapacity());
    }

    @Test
    public void getAvailableCapacityFullLocker(){
        testLocker.addItem(volume5Item, 3);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 2);
        testLocker.addItem(volume2Item, 1);
        assertEquals("locker available capacity incorrect", 0,
                testLocker.getAvailableCapacity());
    }

    @Test
    public void getAvailableCapacity(){
        testLocker.addItem(volume5Item, 1);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 1);
        testLocker.addItem(volume2Item, 1);
        assertEquals("locker available capacity incorrect", 20,
                testLocker.getAvailableCapacity());
    }

    @Test
    public void addZeroNumberOfItems(){
        testLocker.addItem(volume10Item, 0);
        testLocker.addItem(volume5Item, 0);
        testLocker.addItem(volume4Item, 0);
        testLocker.addItem(volume3Item, 0);
        testLocker.addItem(volume2Item, 0);
        assertEquals("added more than 0 items to locker", 40,
                testLocker.getAvailableCapacity());
    }

    @Test
    public void addItemToEmptyLocker(){
        assertEquals("didnt add item to empty locker",0,
                testLocker.addItem(volume10Item, 1));
    }

    @Test
    public void addItemsToLongStorageNotNeeded(){
        assertEquals("moved item to long term storage not needed",0,
                testLocker.addItem(volume10Item, 2));
    }

    @Test
    public void addCurrentAmountItemsToLongStorage(){
        assertEquals("didnt move items to long term storage",1,
                testLocker.addItem(volume10Item, 3));
        assertEquals("didnt move current amount of items to long term storage",0,
                testLocker.getItemCount(volume10Item.getType()));
    }

    @Test
    public void addCurrentAmountItemsToLongStorage2(){
        assertEquals("didnt move items to long term storage",1,
                testLocker.addItem(volume4Item, 8));
        assertEquals("didnt move current amount of items to long term storage",2,
                testLocker.getItemCount(volume4Item.getType()));
    }

    @Test
    public void addCurrentAmountItemsToLongStorageWhenFillingLockerToMaxCapacity(){
        assertEquals("moved item to long term storage not needed",0,
                testLocker.addItem(volume4Item, 5));
        assertEquals("didnt move to long term storage",1,
                testLocker.addItem(volume4Item, 5));
        assertEquals("didnt move current amount of items to long term storage",2,
                testLocker.getItemCount(volume4Item.getType()));
    }

    @Test
    public void addItemsToNotEmptyLocker(){
        testLocker.addItem(volume10Item, 1);
        assertEquals("didnt add items to not empty locker", 0,
                testLocker.addItem(volume10Item, 1));
        assertEquals("didnt add  different items to not empty locker",0,
                testLocker.addItem(volume4Item, 3));
    }

    @Test
    public void addItemsToFillLockerToMaxCapacity(){
        assertEquals("didnt add items",0, testLocker.addItem(volume10Item, 2));
        assertEquals("moved item to long term storage not needed", 2,
                testLocker.getItemCount(volume10Item.getType()));
        assertEquals("didnt add items to fill the locker's max capacity", 0,
                testLocker.addItem(volume4Item, 5));
        assertEquals("moved item to long term storage not needed", 5,
                testLocker.getItemCount(volume4Item.getType()));
        assertEquals("not full locker", 0, testLocker.getAvailableCapacity());
    }

    @Test(expected = NullPointerException.class)
    public void addIllegalItem(){
        assertEquals("added illegal item", -1, testLocker.addItem(illegalItem, 1));
    }

    @Test
    public void addNegativeItemsNumber(){
        assertEquals("added negative number of items", -1,
                testLocker.addItem(volume10Item, -1));
    }

    @Test
    public void addItemsToFullLocker(){
        testLocker.addItem(volume10Item, 2);
        testLocker.addItem(volume4Item, 5);
        assertEquals("added 1 item of the existing type to full locker",-1,
                testLocker.addItem(volume10Item, 1));
        assertEquals("added 3 items of the existing type to full locker",-1,
                testLocker.addItem(volume4Item, 3));
        assertEquals("added 1 item of the different type to full locker",-1,
                testLocker.addItem(volume3Item, 1));
        assertEquals("added 3 items of the different type to full locker",-1,
                testLocker.addItem(volume5Item, 3));
        assertEquals("added items of the different type to full locker and locker contains" +
                " contradicting items",-2, testLocker.addItem(volume2Item, 1));
    }

    @Test
    public void addItemsCapacityBiggerThanLockerAvailableCapacity(){
        testLocker.addItem(volume10Item, 2);
        testLocker.addItem(volume4Item, 4);
        assertEquals("added existing item's capacity higher than locker's available capacity ",
                -1, testLocker.addItem(volume10Item, 1));
        assertEquals("added different item's capacity higher than locker's available capacity ",
                -1, testLocker.addItem(volume5Item, 2));
    }

    @Test
    public void addItemsTakingTooMuchCapacityToEmptyLocker(){
        assertEquals("added items taking more capacity than empty locker's capacity ",
                -1, testLocker.addItem(volume10Item, 5));
    }

    @Test
    public void addItemsToLongTermThroughLocker(){
        testLocker.addItem(volume5Item, 5);
        assertEquals("didnt move current amount of items to long storage ",1,
                testLocker.getItemCount(volume5Item.getType()));
    }

    @AfterClass
    public static void addItemsToFillLongTermStorageMaxCapacity(){
        int result;
        for (int i = 0; i < 20; i++){
            result = testLocker.addItem(volume10Item, 4);
            if (result != 1){
            assertEquals("added items through locker to full long term storage",
                    -1, result);
            break;
            }
        }
        assertEquals("didnt add item to locker when long storage is full", 0,
                testLocker.addItem(volume4Item, 2));
    }

    @Test
    public void addContradictingItem1(){
        testLocker.addItem(volume4Item, 1);
        assertEquals("contradicting items in locker", -2,
                testLocker.addItem(volume2Item, 1));
    }

    @Test
    public void addContradictingItem2(){
        testLocker.addItem(volume2Item, 1);
        assertEquals("contradicting items in locker", -2,
                testLocker.addItem(volume4Item, 1));
    }

    @Test
    public void addBaseballBatToLockerNotContainsFootBall(){
        testLocker.addItem(volume5Item, 1);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 1);
        assertEquals("didnt add baseball bat to locker not contains football",
                0, testLocker.addItem(volume2Item, 1));
    }

    @Test
    public void addFootballToLockerNotContainsBaseballbat(){
        testLocker.addItem(volume5Item, 1);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 1);
        assertEquals("didnt add baseball bat to locker not contains football",
                0, testLocker.addItem(volume4Item, 1));
    }

    @Test
    public void removeOneItem(){
        testLocker.addItem(volume5Item, 4);
        assertEquals("didnt remove one item", 0, testLocker.removeItem(volume5Item, 1));
        assertEquals("locker not contains current amount of items", 3,
                testLocker.getItemCount(volume5Item.getType()));
    }

    @Test
    public void removeAllItems(){
        testLocker.addItem(volume5Item, 4);
        assertEquals("didnt remove all items", 0, testLocker.removeItem(volume5Item, 4));
        assertEquals("locker not contains current amount of items", 0,
                testLocker.getItemCount(volume5Item.getType()));
    }

    @Test
    public void removeOneItemFromEmptyLocker(){
        assertEquals("remove 1 item from empty locker", -1,
                testLocker.removeItem(volume5Item, 1));
    }

    @Test
    public void removeFiveItemsFromEmptyLocker(){
        assertEquals("remove 5 items from empty locker", -1,
                testLocker.removeItem(volume5Item, 5));
    }

    @Test
    public void removeHigherAmountOfItemsFromLockerExistingItemsAmount(){
        testLocker.addItem(volume5Item, 4);
        assertEquals("remove higher amount of items for locker's existing items amount ",
                -1, testLocker.removeItem(volume5Item, 5));
    }

    @Test
    public void removeNotExistingItemInLocker(){
        testLocker.addItem(volume5Item, 1);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 1);
        testLocker.addItem(volume2Item, 1);
        assertEquals("remove not existing items from locker ", -1,
                testLocker.removeItem(volume4Item, 1));
    }

    @Test
    public void removeDifferentTypeItems(){
        testLocker.addItem(volume5Item, 1);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 1);
        testLocker.addItem(volume2Item, 1);
        assertEquals("didn't remove different item types ", 0,
                testLocker.removeItem(volume2Item, 1));
        assertEquals("didn't remove different item types ", 0,
                testLocker.removeItem(volume10Item, 1));
    }

    @Test
    public void removePartialOfDifferentTypeItems(){
        testLocker.addItem(volume5Item, 2);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 2);
        testLocker.addItem(volume2Item, 1);
        assertEquals("didn't remove different item types ", 0,
                testLocker.removeItem(volume5Item, 1));
        assertEquals("didn't remove different item types ", 0,
                testLocker.removeItem(volume10Item, 1));
        assertEquals("didn't remove part of item's amount", 1,
                testLocker.getItemCount(volume5Item.getType()));
        assertEquals("didn't remove part of item's amount", 1,
                testLocker.getItemCount(volume3Item.getType()));
        assertEquals("didn't remove part of item's amount", 1,
                testLocker.getItemCount(volume2Item.getType()));
        assertEquals("didn't remove part of item's amount", 1,
                testLocker.getItemCount(volume10Item.getType()));
    }

    @Test
    public void removeZeroAmountOfExistingItems(){
        testLocker.addItem(volume10Item, 1);
        assertEquals("didn't remove zero amount of existing item ", 0,
                testLocker.removeItem(volume10Item, 0));
    }

    @Test
    public void removeZeroAmountOfNotExistingItems(){
        testLocker.addItem(volume10Item, 1);
        assertEquals("remove zero amount of not existing item ", -1,
                testLocker.removeItem(volume5Item, 0));
    }

    @Test
    public void removeItemFromFullLocker(){
        testLocker.addItem(volume5Item, 3);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 2);
        testLocker.addItem(volume2Item, 1);
        assertEquals(" didn't remove item from full locker ", 0,
                testLocker.removeItem(volume5Item, 2));
    }

    @Test
    public void removeNegativeAmountOfItem(){
        testLocker.addItem(volume5Item, 3);
        assertEquals(" removed negative amount of an item ", -1,
                testLocker.removeItem(volume5Item, -1));
    }

    @Test
    public void getItemCount(){
        testLocker.addItem(volume5Item, 3);
        assertEquals(" didn't count item's amount correctly ", 3,
                testLocker.getItemCount(volume5Item.getType()));
    }

    @Test
    public void getItemCountAfterMovingToLongTermStorage(){
        testLocker.addItem(volume4Item, 8);
        assertEquals(" didn't count item's amount correctly ", 2,
                testLocker.getItemCount(volume4Item.getType()));
    }

    @Test(expected = NullPointerException.class)
    public void getItemCountIllegalItem(){
        testLocker.addItem(illegalItem, 1);
        assertEquals(" didn't count item's amount correctly ", -1,
                testLocker.getItemCount(illegalItem.getType()));
    }

    @Test
    public void getItemCountNotExistingItem(){
        testLocker.addItem(volume5Item, 3);
        assertEquals("counted more than 0 of not existing item ", 0,
                testLocker.getItemCount(volume10Item.getType()));
        assertEquals("counted more than 0 of not existing item ", 0,
                testLocker.getItemCount(volume2Item.getType()));
        assertEquals("counted more than 0 of not existing item ", 0,
                testLocker.getItemCount(volume3Item.getType()));
        assertEquals("counted more than 0 of not existing item ", 0,
                testLocker.getItemCount(volume4Item.getType()));

    }

    @Test
    public void getItemCountInEmptyLocker(){
        assertEquals("counted more than 0 items in empty locker ", 0,
                testLocker.getItemCount(volume10Item.getType()));
        assertEquals("counted more than 0 of not existing item ", 0,
                testLocker.getItemCount(volume5Item.getType()));
        assertEquals("counted more than 0 of not existing item ", 0,
                testLocker.getItemCount(volume2Item.getType()));
        assertEquals("counted more than 0 of not existing item ", 0,
                testLocker.getItemCount(volume3Item.getType()));
        assertEquals("counted more than 0 of not existing item ", 0,
                testLocker.getItemCount(volume4Item.getType()));
    }

    @Test
    public void getItemCountOfDifferentTypeItems(){
        testLocker.addItem(volume5Item, 2);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 2);
        testLocker.addItem(volume2Item, 1);
        assertEquals("didn't count items correctly ", 2,
                testLocker.getItemCount(volume10Item.getType()));
        assertEquals("didn't count items correctly ", 2,
                testLocker.getItemCount(volume5Item.getType()));
        assertEquals("didn't count items correctly ", 1,
                testLocker.getItemCount(volume2Item.getType()));
        assertEquals("didn't count items correctly ", 1,
                testLocker.getItemCount(volume3Item.getType()));
        assertEquals("didn't count items correctly ", 0,
                testLocker.getItemCount(volume4Item.getType()));
    }

    @Test
    public void getItemCountInFullLocker() {
        testLocker.addItem(volume5Item, 3);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 2);
        testLocker.addItem(volume2Item, 1);
        assertEquals("didn't count items correctly ", 2,
                testLocker.getItemCount(volume10Item.getType()));
        assertEquals("didn't count items correctly ", 3,
                testLocker.getItemCount(volume5Item.getType()));
        assertEquals("didn't count items correctly ", 1,
                testLocker.getItemCount(volume2Item.getType()));
        assertEquals("didn't count items correctly ", 1,
                testLocker.getItemCount(volume3Item.getType()));
        assertEquals("didn't count items correctly ", 0,
                testLocker.getItemCount(volume4Item.getType()));
    }

    @Test
    public void getItemCountAfterAddingSameItem(){
        testLocker.addItem(volume5Item, 3);
        assertEquals("didn't count items correctly ", 3,
                testLocker.getItemCount(volume5Item.getType()));
        testLocker.addItem(volume5Item, 1);
        assertEquals("didn't count items correctly ", 4,
                testLocker.getItemCount(volume5Item.getType()));
        testLocker.addItem(volume5Item, 0);
        assertEquals("didn't count items correctly ", 4,
                testLocker.getItemCount(volume5Item.getType()));
    }

    @Test
    public void getItemCountAfterAddingNegativeNumber(){
        testLocker.addItem(volume5Item, 3);
        testLocker.addItem(volume5Item, -1);
        assertEquals("didn't count items correctly ", 3,
                testLocker.getItemCount(volume5Item.getType()));
    }

    @Test
    public void getInventoryAfterAddingOneItem(){
        testMap.put(volume5Item.getType(), 3);
        testLocker.addItem(volume5Item, 3);
        assertEquals("incorrect map", testMap, testLocker.getInventory());
    }

    @Test
    public void getInventoryAfterAddingSameItem(){
        testMap.put(volume5Item.getType(), 3);
        testLocker.addItem(volume5Item, 1);
        testLocker.addItem(volume5Item, 1);
        testLocker.addItem(volume5Item, 1);
        assertEquals("incorrect map", testMap, testLocker.getInventory());
    }

    @Test
    public void getInventoryOfEmptyLocker(){
        assertEquals("incorrect map", testMap, testLocker.getInventory());
    }

    @Test
    public void getInventoryWithDifferentItemsTypes(){
        testLocker.addItem(volume5Item, 2);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 2);
        testLocker.addItem(volume2Item, 1);
        testMap.put(volume5Item.getType(), 2);
        testMap.put(volume3Item.getType(), 1);
        testMap.put(volume10Item.getType(), 2);
        testMap.put(volume2Item.getType(), 1);
        assertEquals("incorrect map", testMap, testLocker.getInventory());
    }

    @Test
    public void getInventoryWhenLockerFull(){
        testLocker.addItem(volume5Item, 3);
        testLocker.addItem(volume3Item, 1);
        testLocker.addItem(volume10Item, 2);
        testLocker.addItem(volume2Item, 1);
        testMap.put(volume5Item.getType(), 3);
        testMap.put(volume3Item.getType(), 1);
        testMap.put(volume10Item.getType(), 2);
        testMap.put(volume2Item.getType(), 1);
        assertEquals("incorrect map", testMap, testLocker.getInventory());
    }

    @Test
    public void getInventoryAfterAddingZeroItem(){
        testLocker.addItem(volume5Item, 3);
        testLocker.addItem(volume10Item, 0);
        testMap.put(volume5Item.getType(), 3);
        assertEquals("incorrect map", testMap, testLocker.getInventory());
    }

    @Test
    public void getInventoryAfterAddingNegativeItemsNumber(){
        testLocker.addItem(volume5Item, 3);
        testLocker.addItem(volume5Item, -2);
        testMap.put(volume5Item.getType(), 3);
        assertEquals("incorrect map", testMap, testLocker.getInventory());
    }

    @Test(expected = NullPointerException.class)
    public void getInventoryAfterAddingNotExistingItem(){
        testLocker.addItem(volume5Item, 3);
        testLocker.addItem(illegalItem, 2);
        testMap.put(volume5Item.getType(), 3);
        assertEquals("incorrect map", testMap, testLocker.getInventory());
    }

    @Test
    public void testGetAvailableCapacity(){
        int usedUnitsSum = 0;
        for (String i: testLocker.getInventory().keySet()){
            usedUnitsSum += testLocker.getInventory().get(i);
        }
        assertEquals(testLocker.getAvailableCapacity(),testLocker.getCapacity() - usedUnitsSum);
    }
}