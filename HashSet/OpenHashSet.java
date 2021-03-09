import java.util.*;

public class OpenHashSet extends SimpleHashSet {

    /*----=  Data Members  =-----*/

    /**
     * an array of OpenHashSetLink type objects who contains linked list as a data member for
     * the implementation of open hash set
     */
    private linkedListHashSet [] linkedListsArray;


    /*----=  Magic Numbers  =-----*/

    private static int DOUBLE = 2;
    private static double HALF = 0.5;



    /*----=  Constructors  =-----*/

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor,lowerLoadFactor);
        this.defaultConstructorInitiating();
    }

    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet(){
        super();
        this.defaultConstructorInitiating();
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored. The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     * @param data - Values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data){
        super();
        this.defaultConstructorInitiating();
        for (String dataStr: data){
            this.add(dataStr);
        }
    }

    /*----=  Inner Class  =-----*/

    /**
     * class of object contains linked list of string types for the open hash set implementation.
     */
    private class linkedListHashSet{

        /** linked list data member */
        private LinkedList<String> linkedList;

        /** constructs a new object contains contructed linked list as a data member */
        private linkedListHashSet(){
            this.linkedList = new LinkedList<String>();
        }
    }


    /*----=  Instance Methods  =-----*/

    /**
     * returns The current capacity (number of cells) of the table.
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity(){
        return this.linkedListsArray.length;
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
        else{
            if (this.getLoadFactor(this.size() + 1) > this.getUpperLoadFactor())
                this.resizingTable(OpenHashSet.DOUBLE);
            int hashIndex = this.clamp(newValue.hashCode());
            // adding element to the suited linked list on the open hash set linked lists array.
            this.linkedListsArray[hashIndex].linkedList.add(newValue);
        }
        return true;
    }


    /**
     * Look for a specified value in the set.
     * @param searchVal Value to search for
     * @return True if searchVal is found in the set
     */
    public boolean contains(String searchVal){
        int hashIndex = this.clamp(searchVal.hashCode());
        LinkedList<String> currentLinkedList = this.linkedListsArray[hashIndex].linkedList;
        for (String str: currentLinkedList){
            if (str.equals(searchVal)) {
                return true;
            }
        }
        return false;
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
        else{
            int hashIndex = this.clamp(toDelete.hashCode());
            // removing element from the suited linked list on the open hash set array.
            this.linkedListsArray[hashIndex].linkedList.remove(toDelete);
            if (this.getLoadFactor(this.size()) < this.getLowerLoadFactor()){
                if ((int)(this.capacity() * OpenHashSet.HALF) >= 1)
                    this.resizingTable(OpenHashSet.HALF);
            }
        }
        return true;
    }


    /**
     * @return The number of elements currently in the set
     */
    public int size(){
        int elementsNumber = 0;
        for (linkedListHashSet currentLink: this.linkedListsArray) {
            elementsNumber += currentLink.linkedList.size();
        }
        return elementsNumber;
    }


    /*----=  Private Helper Methods  =-----*/

    /**
     * returns the load factor of this hash set
     * @param size int presents the number of elements currently in the set
     * @return int presents the load factor of this hash set
     */
    private float getLoadFactor(int size){
        return (float)(size) / this.capacity();
    }

    /**
     * function resizing the current Hash Table length according to a specific factor
     * @param factor resizing the hash table according to this double
     */
    private void resizingTable(double factor) {
        linkedListHashSet[] linkedListsTmpArray = new linkedListHashSet[(int)(this.capacity() * factor)];
        this.initiatingLinkedLists(linkedListsTmpArray);
        for (linkedListHashSet openHashSetLink: this.linkedListsArray){
            for (String str: openHashSetLink.linkedList){
                int hashIndex = this.resizingClamp(str.hashCode(), linkedListsTmpArray.length);
                linkedListsTmpArray[hashIndex].linkedList.add(str);
            }
        }
        this.linkedListsArray = linkedListsTmpArray;
    }

    /**
     * initiating linked lists objects for the hash set's array
     * @param currentLinkedListsArray - hash set's linked list array
     */
    private void initiatingLinkedLists(linkedListHashSet[] currentLinkedListsArray ){
        for( int i = 0; i < currentLinkedListsArray.length; i++){
            currentLinkedListsArray[i] = new linkedListHashSet();
        }
    }

    /**
     * function returns suited clamped linked lists array index according to the new resized hash set
     * @param index - index returned by hash function worked on a specific string
     * @param newHashSetCapacity - length of the new linked lists array suited for resized hash set
     * @return clamped linked lists array index according to the new resized h set
     */
    private int resizingClamp(int index, int newHashSetCapacity){
        return (index & (newHashSetCapacity - 1));
    }

    /**
     * function used for constructor's initiating new open hash set with default capacity and lower/upper
     * load factor bounds.
     */
    private void defaultConstructorInitiating(){
        this.linkedListsArray = new linkedListHashSet[SimpleHashSet.INITIAL_CAPACITY];
        this.initiatingLinkedLists(this.linkedListsArray);
    }
}
