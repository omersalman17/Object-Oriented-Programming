import oop.ex3.spaceship.*;
import org.junit.*;
import java.util.HashMap;
import static org.junit.Assert.*;

public class LongTermTest {
    private static LongTermStorage testLongTermStorage = new LongTermStorage();
//    private static Item [] testItemsArray = ItemFactory.createAllLegalItems();
    private static Item volume2Item = ItemFactory.createSingleItem("baseball bat");
    private static Item volume3Item = ItemFactory.createSingleItem("helmet, size 1");
    private static Item volume5Item = ItemFactory.createSingleItem("helmet, size 3");
    private static Item volume10Item = ItemFactory.createSingleItem("spores engine");
    private static Item volume4Item = ItemFactory.createSingleItem("football");
    private static Item illegalItem = ItemFactory.createSingleItem("sword");
    private static HashMap<String, Integer> testMap;

    //MAGIC NUMBERS
    private final static int LONG_TERM_STORAGE_CAPACITY = 1000;
    private final static int DEFAULT_ERROR_RETURN_INT = -1;
    private final static int ACTION_SUCCESS_RETURN_INT = 0;
    private final static int ACTION_SUCCES_WARNING_RETURN_INT = 1;

    @Before
    public void createTestObjects(){
        testLongTermStorage.resetInventory();
        testMap = new HashMap<String, Integer>();
    }

    @Test
    public void testGetCapacity(){
        assertEquals("locker capacity incorrect",LONG_TERM_STORAGE_CAPACITY,
                testLongTermStorage.getCapacity());
    }

    @Test
    public void getCapacityWhenLockerFull(){
        testLongTermStorage.addItem(volume5Item, 3);
        testLongTermStorage.addItem(volume3Item, 1);
        testLongTermStorage.addItem(volume10Item, 98);
        testLongTermStorage.addItem(volume2Item, 1);
        assertEquals("locker capacity incorrect", LONG_TERM_STORAGE_CAPACITY,
                testLongTermStorage.getCapacity());
    }

    @Test
    public void getCapacityAfterAddingItems(){
        testLongTermStorage.addItem(volume5Item, 1);
        testLongTermStorage.addItem(volume3Item, 1);
        testLongTermStorage.addItem(volume10Item, 20);
        testLongTermStorage.addItem(volume2Item, 1);
        testLongTermStorage.addItem(volume10Item, 0);
        assertEquals("locker capacity incorrect", LONG_TERM_STORAGE_CAPACITY,
                testLongTermStorage.getCapacity());
    }

    @Test(expected = NullPointerException.class)
    public void getCapacityAfterAddingIllegalItem(){
        testLongTermStorage.addItem(volume5Item, 1);
        testLongTermStorage.addItem(volume3Item, 1);
        testLongTermStorage.addItem(volume10Item, 1);
        testLongTermStorage.addItem(illegalItem, 1);
        assertEquals("locker capacity incorrect", LONG_TERM_STORAGE_CAPACITY,
                testLongTermStorage.getCapacity());
    }

    @Test
    public void getCapacityAfterAddingItemFailed(){
        testLongTermStorage.addItem(volume5Item, 1);
        testLongTermStorage.addItem(volume3Item, 1);
        testLongTermStorage.addItem(volume10Item, 1);
        testLongTermStorage.addItem(volume2Item, 1);
        testLongTermStorage.addItem(volume10Item, 100); // adding item fails
        assertEquals("locker capacity incorrect", LONG_TERM_STORAGE_CAPACITY,
                testLongTermStorage.getCapacity());
    }

    @Test
    public void getAvailableCapacityEmptyLocker(){
        assertEquals("locker available capacity incorrect",LONG_TERM_STORAGE_CAPACITY,
                testLongTermStorage.getAvailableCapacity());
    }

    @Test
    public void getAvailableCapacityFullLocker(){
        testLongTermStorage.addItem(volume5Item, 3);
        testLongTermStorage.addItem(volume3Item, 1);
        testLongTermStorage.addItem(volume10Item, 98);
        testLongTermStorage.addItem(volume2Item, 1);
        assertEquals("locker available capacity incorrect", 0,
                testLongTermStorage.getAvailableCapacity());
    }

    @Test
    public void getAvailableCapacity(){
        testLongTermStorage.addItem(volume5Item, 1);
        testLongTermStorage.addItem(volume3Item, 1);
        testLongTermStorage.addItem(volume10Item, 2);
        testLongTermStorage.addItem(volume2Item, 1);
        assertEquals("locker available capacity incorrect", 970,
                testLongTermStorage.getAvailableCapacity());
    }

    @Test
    public void testGetAvailableCapacity(){
        int usedUnitsSum = 0;
        for (String i: testLongTermStorage.getInventory().keySet()){
            usedUnitsSum += testLongTermStorage.getInventory().get(i);
        }
        assertEquals(testLongTermStorage.getAvailableCapacity(),
                testLongTermStorage.getCapacity() - usedUnitsSum);
    }

    @Test
    public void addZeroNumberOfItems(){
        testLongTermStorage.addItem(volume10Item, 0);
        testLongTermStorage.addItem(volume5Item, 0);
        testLongTermStorage.addItem(volume4Item, 0);
        testLongTermStorage.addItem(volume3Item, 0);
        testLongTermStorage.addItem(volume2Item, 0);
        assertEquals("added more than 0 items to locker", LONG_TERM_STORAGE_CAPACITY,
                testLongTermStorage.getAvailableCapacity());
    }

    @Test
    public void addItemToEmptyLongTermStorage(){
        assertEquals("didnt add item to empty locker",0,
                testLongTermStorage.addItem(volume10Item, 1));
    }

    @Test
    public void addItemsToNotEmptyLocker(){
        testLongTermStorage.addItem(volume10Item, 1);
        assertEquals("didnt add items to not empty locker", 0,
                testLongTermStorage.addItem(volume10Item, 1));
        assertEquals("didnt add  different items to not empty locker",0,
                testLongTermStorage.addItem(volume4Item, 3));
    }

    @Test
    public void addItemsToFillLockerToMaxCapacity(){
        assertEquals("didnt add items",0, testLongTermStorage.addItem(volume5Item, 4));
        assertEquals("didnt add items to fill the locker's max capacity", 0,
                testLongTermStorage.addItem(volume10Item, 98));
        assertEquals("not full locker", 0, testLongTermStorage.getAvailableCapacity());
    }

    @Test(expected = NullPointerException.class)
    public void addIllegalItem(){
        assertEquals("added illegal item", -1,
                testLongTermStorage.addItem(illegalItem, 1));
    }

    @Test
    public void addNegativeItemsNumber(){
        assertEquals("added negative number of items", -1,
                testLongTermStorage.addItem(volume10Item, -1));
    }

    @Test
    public void addItemsToFullLocker(){
        testLongTermStorage.addItem(volume10Item, 98);
        testLongTermStorage.addItem(volume4Item, 5);
        assertEquals("added 1 item of the existing type to full locker",-1,
                testLongTermStorage.addItem(volume10Item, 1));
        assertEquals("added 3 items of the existing type to full locker",-1,
                testLongTermStorage.addItem(volume4Item, 3));
        assertEquals("added 1 item of the different type to full locker",-1,
                testLongTermStorage.addItem(volume3Item, 1));
        assertEquals("added 3 items of the different type to full locker",-1,
                testLongTermStorage.addItem(volume5Item, 3));
    }

    @Test
    public void addItemsCapacityBiggerThanLockerAvailableCapacity(){
        testLongTermStorage.addItem(volume10Item, 99);
        testLongTermStorage.addItem(volume4Item, 1);
        assertEquals("added existing item's capacity higher than locker's available capacity ",
                -1, testLongTermStorage.addItem(volume10Item, 1));
        assertEquals("added different item's capacity higher than locker's available capacity ",
                -1, testLongTermStorage.addItem(volume5Item, 2));
    }

    @Test
    public void addItemsTakingTooMuchCapacityToEmptyLocker(){
        assertEquals("added items taking more capacity than empty locker's capacity ",-1,
                testLongTermStorage.addItem(volume10Item, 101));
    }

    @Test
    public void getItemCount(){
        testLongTermStorage.addItem(volume5Item, 3);
        assertEquals(" didn't count item's amount correctly ", 3,
                testLongTermStorage.getItemCount(volume5Item.getType()));
    }


    @Test(expected = NullPointerException.class)
    public void getItemCountIllegalItem(){
        testLongTermStorage.addItem(illegalItem, 1);
        assertEquals(" didn't count item's amount correctly ", 0,
                testLongTermStorage.getItemCount(illegalItem.getType()));
    }

    @Test
    public void getItemCountNotExistingItem(){
        testLongTermStorage.addItem(volume5Item, 3);
        assertEquals("counted more than 0 of not existing item ", 0,
                testLongTermStorage.getItemCount(volume10Item.getType()));
        assertEquals("counted more than 0 of not existing item ", 0,
                testLongTermStorage.getItemCount(volume2Item.getType()));
        assertEquals("counted more than 0 of not existing item ", 0,
                testLongTermStorage.getItemCount(volume3Item.getType()));
        assertEquals("counted more than 0 of not existing item ", 0,
                testLongTermStorage.getItemCount(volume4Item.getType()));

    }

    @Test
    public void getItemCountInEmptyLocker(){
        assertEquals("counted more than 0 items in empty locker ", 0,
                testLongTermStorage.getItemCount(volume10Item.getType()));
        assertEquals("counted more than 0 of not existing item ", 0,
                testLongTermStorage.getItemCount(volume5Item.getType()));
        assertEquals("counted more than 0 of not existing item ", 0,
                testLongTermStorage.getItemCount(volume2Item.getType()));
        assertEquals("counted more than 0 of not existing item ", 0,
                testLongTermStorage.getItemCount(volume3Item.getType()));
        assertEquals("counted more than 0 of not existing item ", 0,
                testLongTermStorage.getItemCount(volume4Item.getType()));
    }

    @Test
    public void getItemCountOfDifferentTypeItems(){
        testLongTermStorage.addItem(volume5Item, 2);
        testLongTermStorage.addItem(volume3Item, 1);
        testLongTermStorage.addItem(volume10Item, 2);
        testLongTermStorage.addItem(volume2Item, 1);
        assertEquals("didn't count items correctly ", 2,
                testLongTermStorage.getItemCount(volume10Item.getType()));
        assertEquals("didn't count items correctly ", 2,
                testLongTermStorage.getItemCount(volume5Item.getType()));
        assertEquals("didn't count items correctly ", 1,
                testLongTermStorage.getItemCount(volume2Item.getType()));
        assertEquals("didn't count items correctly ", 1,
                testLongTermStorage.getItemCount(volume3Item.getType()));
        assertEquals("didn't count items correctly ", 0,
                testLongTermStorage.getItemCount(volume4Item.getType()));
    }

    @Test
    public void getItemCountInFullLocker() {
        testLongTermStorage.addItem(volume5Item, 3);
        testLongTermStorage.addItem(volume3Item, 1);
        testLongTermStorage.addItem(volume10Item, 98);
        testLongTermStorage.addItem(volume2Item, 1);
        assertEquals("didn't count items correctly ", 98,
                testLongTermStorage.getItemCount(volume10Item.getType()));
        assertEquals("didn't count items correctly ", 3,
                testLongTermStorage.getItemCount(volume5Item.getType()));
        assertEquals("didn't count items correctly ", 1,
                testLongTermStorage.getItemCount(volume2Item.getType()));
        assertEquals("didn't count items correctly ", 1,
                testLongTermStorage.getItemCount(volume3Item.getType()));
        assertEquals("didn't count items correctly ", 0,
                testLongTermStorage.getItemCount(volume4Item.getType()));
    }

    @Test
    public void getItemCountAfterAddingSameItem(){
        testLongTermStorage.addItem(volume5Item, 3);
        assertEquals("didn't count items correctly ",
                3,testLongTermStorage.getItemCount(volume5Item.getType()));
        testLongTermStorage.addItem(volume5Item, 1);
        assertEquals("didn't count items correctly ",
                4, testLongTermStorage.getItemCount(volume5Item.getType()));
        testLongTermStorage.addItem(volume5Item, 0);
        assertEquals("didn't count items correctly ",
                4, testLongTermStorage.getItemCount(volume5Item.getType()));
    }

    @Test
    public void getItemCountAfterAddingNegativeNumber(){
        testLongTermStorage.addItem(volume5Item, 3);
        testLongTermStorage.addItem(volume5Item, -1);
        assertEquals("didn't count items correctly ", 3,
                testLongTermStorage.getItemCount(volume5Item.getType()));
    }

    @Test
    public void getInventoryAfterAddingOneItem(){
        testMap.put(volume5Item.getType(), 3);
        testLongTermStorage.addItem(volume5Item, 3);
        assertEquals("incorrect map", testMap, testLongTermStorage.getInventory());
    }

    @Test
    public void getInventoryAfterAddingSameItem(){
        testMap.put(volume5Item.getType(), 3);
        testLongTermStorage.addItem(volume5Item, 1);
        testLongTermStorage.addItem(volume5Item, 1);
        testLongTermStorage.addItem(volume5Item, 1);
        assertEquals("incorrect map", testMap, testLongTermStorage.getInventory());
    }

    @Test
    public void getInventoryOfEmptyLocker(){
        assertEquals("incorrect map", testMap, testLongTermStorage.getInventory());
    }

    @Test
    public void getInventoryWithDifferentItemsTypes(){
        testLongTermStorage.addItem(volume5Item, 2);
        testLongTermStorage.addItem(volume3Item, 1);
        testLongTermStorage.addItem(volume10Item, 2);
        testLongTermStorage.addItem(volume2Item, 1);
        testMap.put(volume5Item.getType(), 2);
        testMap.put(volume3Item.getType(), 1);
        testMap.put(volume10Item.getType(), 2);
        testMap.put(volume2Item.getType(), 1);
        assertEquals("incorrect map", testMap, testLongTermStorage.getInventory());
    }

    @Test
    public void getInventoryWhenLockerFull(){
        testLongTermStorage.addItem(volume5Item, 3);
        testLongTermStorage.addItem(volume3Item, 1);
        testLongTermStorage.addItem(volume10Item, 98);
        testLongTermStorage.addItem(volume2Item, 1);
        testMap.put(volume5Item.getType(), 3);
        testMap.put(volume3Item.getType(), 1);
        testMap.put(volume10Item.getType(), 98);
        testMap.put(volume2Item.getType(), 1);
        assertEquals("incorrect map", testMap, testLongTermStorage.getInventory());
    }

    @Test
    public void getInventoryAfterAddingZeroItem(){
        testLongTermStorage.addItem(volume5Item, 3);
        testLongTermStorage.addItem(volume10Item, 0);
        testMap.put(volume5Item.getType(), 3);
        assertEquals("incorrect map", testMap, testLongTermStorage.getInventory());
    }

    @Test
    public void getInventoryAfterAddingNegativeItemsNumber(){
        testLongTermStorage.addItem(volume5Item, 3);
        testLongTermStorage.addItem(volume5Item, -2);
        testMap.put(volume5Item.getType(), 3);
        assertEquals("incorrect map", testMap, testLongTermStorage.getInventory());
    }

    @Test(expected = NullPointerException.class)
    public void getInventoryAfterAddingNotExistingItem(){
        testLongTermStorage.addItem(volume5Item, 3);
        testLongTermStorage.addItem(illegalItem, 2);
        testMap.put(volume5Item.getType(), 3);
        assertEquals("incorrect map", testMap, testLongTermStorage.getInventory());
    }

    @Test
    public void resetInventoryEmptyStorage(){
        testLongTermStorage.resetInventory();
        assertEquals("inventory not empty",testMap,testLongTermStorage.getInventory());
    }

    @Test
    public void resetInventoryAfterAddingItems(){
        testLongTermStorage.addItem(volume5Item, 3);
        testLongTermStorage.addItem(volume3Item, 1);
        testLongTermStorage.addItem(volume10Item, 2);
        testLongTermStorage.addItem(volume2Item, 1);
        testLongTermStorage.resetInventory();
        assertEquals("inventory not empty",testMap,testLongTermStorage.getInventory());
    }

    @Test
    public void resetInventoryFullStorage(){
        testLongTermStorage.addItem(volume5Item, 3);
        testLongTermStorage.addItem(volume3Item, 1);
        testLongTermStorage.addItem(volume10Item, 98);
        testLongTermStorage.addItem(volume2Item, 1);
        assertEquals("storage not full",0 ,testLongTermStorage.getAvailableCapacity());
        testLongTermStorage.resetInventory();
        assertEquals("inventory not empty",testMap,testLongTermStorage.getInventory());
    }
}
