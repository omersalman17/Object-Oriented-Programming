public class ClosedHashSet extends SimpleHashSet{

    /*----=  Data Members  =-----*/

    private String [] hashTableSringsArray;


    /*----=  Magic Numbers  =-----*/

    private static int DOUBLE = 2;
    private static double HALF = 0.5;
    private static int EXISTING_HASH_TABLE_SIZE_FACTOR = 1;
    private static int MINIMAL_HASH_TABLE_SIZE = 1;


    /*----=  Constructors  =-----*/

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet(){
        super();
        this.hashTableSringsArray = new String[INITIAL_CAPACITY];
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        this.hashTableSringsArray = new String[INITIAL_CAPACITY];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data - Values to add to the set.
     */
    public ClosedHashSet(java.lang.String[] data){
        this.hashTableSringsArray = new String[INITIAL_CAPACITY];
        for (String str: data){
            this.add(str);
        }
    }

    /*----=  Instance Methods  =-----*/

    /**
     * returns The current capacity (number of cells) of the table.
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity(){
        return this.hashTableSringsArray.length;
    }

    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    public boolean contains(String searchVal){
        for (int i = 0; i < this.capacity(); i++) {
            int hashIndex = this.clamp(searchVal, i, this.capacity());
            if (this.hashTableSringsArray[hashIndex] != null) {
                if (this.hashTableSringsArray[hashIndex].equals(searchVal)) {
                    return true;
                }
            }
            else{
                return false;
            }
        }
        return false;
    }

    /**
     * @return The number of elements currently in the set
     */
    public int size(){
        int elementsNumber = 0;
        for (int i = 0; i < this.hashTableSringsArray.length; i++){
            if (this.hashTableSringsArray[i] != null)
                elementsNumber++;
        }
        return elementsNumber;
    }

    /**
     * Add a specified element to the set if it's not already in it.
     * @param newValue - New value to add to the set
     * @return False if newValue already exists in the set
     */
    public boolean add(java.lang.String newValue){
        if (this.contains(newValue)) {
            return false;
        }
        if (this.getLoadFactor(this.size() + 1) > this.getUpperLoadFactor())
            this.resizingTable(ClosedHashSet.DOUBLE);
        this.adding(this.hashTableSringsArray, newValue);
        return true;
    }


    /**
     * Remove the input element from the set.
     * @param toDelete Value to delete
     * @return True if toDelete is found and deleted
     */
    public boolean delete(String toDelete){
        if (!this.contains(toDelete)){
            return false;
        }
        this.deleting(this.hashTableSringsArray, toDelete);
        if (this.getLoadFactor(this.size()) < this.getLowerLoadFactor()){
            if ((int)(this.capacity() * ClosedHashSet.HALF) >= MINIMAL_HASH_TABLE_SIZE)
                this.resizingTable(ClosedHashSet.HALF);
        }
        else {
            // rehashing the hash table without changing its size for avoiding the element delete problem.
            this.resizingTable(EXISTING_HASH_TABLE_SIZE_FACTOR);
        }
        return true;
    }

    /*----=  Private Helper Methods  =-----*/

    /**
     * returns the load factor of this hash set
     * @param size int presents the number of elements currently in the set
     * @return int presents the load factor of this hash set
     */
    private float getLoadFactor(int size){
        return (float)(size) / (float)this.capacity();
    }

    /**
     * function resizing the current Hash Table length according to a specific factor
     * @param factor resizing the hash table according to this double
     */
    private void resizingTable(double factor) {
        String [] stringsTmpArray = new String[(int)(this.capacity() * factor)];
        for (String stringOnExistingHashTable: this.hashTableSringsArray){
            if (stringOnExistingHashTable == null)
                continue;
            this.adding(stringsTmpArray, stringOnExistingHashTable);
        }
        this.hashTableSringsArray = stringsTmpArray;
    }

    /**
     * suited clamp calculating for closed hash set;
     * @param val - String for clamping
     * @param i - int presents index
     * @param newHashSetCapactiy - int present hash set's capacity
     * @return int presents clamped String value for closed hash set
     */
    private int clamp(String val, int i, int newHashSetCapactiy){
        return ((val.hashCode() + (i + i*i) / 2) & (newHashSetCapactiy - 1));
    }

    /**
     * adding a given string to it's suitable hash index on given strings array
     * @param stringsArray array of strings to add a given string to
     * @param value string to add to a given strings array
     */
    private void adding(String [] stringsArray, String value) {
        for (int i = 0; i < this.capacity(); i++) {
            int hashIndex = this.clamp(value, i, stringsArray.length);
            if (stringsArray[hashIndex] == null){
                stringsArray[hashIndex] = value;
                break;
            }
        }
    }

    /**
     * deleting a given string from it's suitable hash index on given strings array
     * @param stringsArray array of strings to delete a given string from
     * @param value string to delete from a given strings array
     */
    private void deleting(String [] stringsArray, String value) {
        for (int i = 0; i < this.capacity(); i++) {
            int hashIndex = this.clamp(value, i, stringsArray.length);
            if (stringsArray[hashIndex] != null){
                if(stringsArray[hashIndex].equals(value)){
                    stringsArray[hashIndex] = null;
                    break;
                }
            }
        }
    }
}
