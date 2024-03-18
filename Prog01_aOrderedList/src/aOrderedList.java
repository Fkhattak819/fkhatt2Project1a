import java.util.Arrays;
import java.util.NoSuchElementException;

/*
* <aOrderedList Class, this class allows the manipulation of a sorted list, includes adding, get, size, reset, next, hasNext, remove, 
* 	getObjectIndex, and to String>
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author <Fahd Khattak>
* @since <March 17th, 2024>
*
*/
public class aOrderedList {
	
	private final int SIZEINCREMENTS = 20; // Increment size to expand array
    private Comparable[] oList; // Array to store elements of any ordered list
    private int listSize; // Maximum size of the array 
    private int numObjects; // Number of objects that are stored in the array
    private int curr; // Use as an iterator, or pointer to go through the array 

    /*
    * <aOrderedList Constructor, initializes the variables, sets numobjects, listSize to SIZEINCREMENTS, oList, at the curr(current). >
    *
    * CSC 1351 Programming Project No 1
    *
    * Section 2
    *
    * @author <Fahd Khattak>
    * @since <March 17th, 2024>
    *
    */
	public aOrderedList() {
		
		this.numObjects = 0;
		this.listSize = SIZEINCREMENTS;
		this.oList = new Car[listSize];
		this.curr = 0;
		
	}
	
	/**
    * <add method, this method takes in the parameter newObject and adds it to the array.>
    *  
    *
    * CSC 1351 Programming Project No 1
    *
    * Section 2
    *
    * @author <Fahd Khattak>
    * @since <March 17th, 2024>
    *
    */
	
	//generic type 
	public void add(Comparable<?> newObject) {
		
		//If the array is full, double the size.	
		if (this.numObjects == this.oList.length) {
			
		       this.oList = Arrays.copyOf(this.oList, 2 * oList.length);
		    }
		   
			// Find the insertion point for the new object using binary search
			// Will return -(insertionPoint) - 1 if object is not found
		    // Parameters are (List to be searched, first index, last, and Object we are looking for).
		    int insertionPoint = Arrays.binarySearch(this.oList, 0, this.numObjects, newObject); 
		    
			// If the binarySearch returns a negative value if the key is not found, convert the insertion point to an index to the array
		    if (insertionPoint < 0) {
		        insertionPoint = -(insertionPoint + 1); // Convert to destination insertion index
		    }
		    
		    // Makes a copy and shifts all elements to the right of the newObject index
		    // Inserts newObject into insertion point
		    //Parameters are (Source array, starting point where elements will be copied, destination array, where copied elements will go, count of objects to be copied)
		    System.arraycopy(this.oList, insertionPoint, this.oList, insertionPoint + 1, this.numObjects - insertionPoint);
		    
		    this.oList[insertionPoint] = newObject;
		    
		    //Count of object is increased because of the newObject.
		    numObjects++;
		}
	
	/**
    * <comparable <?> get() method, takes in index as the parameter and returns the object in the list at that index. >
    *
    * CSC 1351 Programming Project No 1
    *
    * Section 2
    *
    * @author <Fahd Khattak>
    * @since <March 17th, 2024>
    *
    */
	public Comparable<?> get(int index) {
		return this.oList[index];
		
		}
	/**
    * <size() method, returns the size of the array aka numObjects. >
    *
    * CSC 1351 Programming Project No 1
    *
    * Section 2
    *
    * @author <Fahd Khattak>
    * @since <March 17th, 2024>
    *
	*/
	public int size() {
		return this.numObjects;
		
		}
	
	/**
    * <isEmpty() method, return true if the array equals 0 and false if it is not equal to 0 >
    *
    * CSC 1351 Programming Project No 1
    *
    * Section 2
    *
    * @author <Fahd Khattak>
    * @since <March 17th, 2024>
    *
    */
	public boolean isEmpty() {
		return this.numObjects == 0;
	
	}
	
	/**
    * <reset() method, resets the current iterator to 0>
    *
    * CSC 1351 Programming Project No 1
    *
    * Section 2
    *
    * @author <Fahd Khattak>
    * @since <March 17th, 2024>
    *
    */
	 public void reset() {
	    	curr = 0;
	    }
	 /**
    * <next method, returns the next object in the oList array by adding one to the current iterator.>
    *
    * CSC 1351 Programming Project No 1
    *
    * Section 2
    *
    * @author <Fahd Khattak>
    * @since <March 17th, 2024>
    *
    */
	 public Comparable<?> next() throws NoSuchElementException {
		 if(hasNext()) {
	        return oList[curr++];
		 }
		 else {
	        throw new NoSuchElementException("No more elements in the list.");
	    }
	}
	    
	    
	 /**
    * <hasNext method, return true if the object has an object the the right by validating current less than number of objects and current 
    *  being greater than 0. Returns false if otherwise.  >
    *
    * CSC 1351 Programming Project No 1
    *
    * Section 2
    *
    * @author <Fahd Khattak>
    * @since <March 17th, 2024>
    *
    */
    public boolean hasNext() {
        return this.curr < this.numObjects && curr >= 0;
    	
    }
    
    /**
     * <remove method, removes car at a specific index.>
     *
     * CSC 1351 Programming Project No 1
     *
     * Section 2
     *
     * @author <Fahd Khattak>
     * @since <March 17th, 2024>
     *
     */
    public void remove(int index) {
    	
    	//Check if its within bounds
        if (index < 0 || index >= numObjects) {
        	
            throw new IndexOutOfBoundsException("Index: " + index);//Indicates the invalid index
        }
        
        //Remove by making a copy 
        //Shifts elements to the left to fill the gap left by the removed element.
        //Copies elements starting from index + 1 to the position starting at index (where the removed element was located).
        //Number elements to be copied is numObjects - index - 1, meaning the elements after the removed item are shifted left.
        System.arraycopy(oList, index + 1, oList, index, numObjects - index - 1);
        
        //Reduces the list size by one.
        oList[numObjects--] = null;
        curr--;
    }
    
    /**
     * <getObjectIndex method, check if the make and year of the deleted cars equals any makes or years in the ordered list. Returns the indexes
     *  that are matching>
     *
     * CSC 1351 Programming Project No 1
     *
     * Section 2
     *
     * @author <Fahd Khattak>
     * @since <March 17th, 2024>
     *
     */
    
    public int getObjectIndex(String make, int year) {
    	//Iterate through list of cars
    	for(int i = 0; i < size(); i++) {
    		Car car = (Car) oList[i];
    		if(car.getMake().equals(make) && car.getYear() == year){
    			
                // If a match is found, return the current index
    			return i;
    		}
    	}
    	//If no match is found return -1
		return -1;
    }
    
    /**
     * <toString(), Generates a string representation of the ordered list. This method overrides
	 * the default toString method and provides a custom string format for the list.
	 * Each element in the list is converted to its string representation (via its own
	 * toString method), and then enclosed in square brackets.>
     *
     * CSC 1351 Programming Project No 1
     *
     * Section 2
     *
     * @author <Fahd Khattak>
     * @since <March 17th, 2024>
     *
     */
    @Override
	public String toString() {
		
    	 	String stringResult = "";
		    
		    for (int i = 0; i < numObjects; i++) {
		        if (i > 0) {
		        	stringResult += ", ";
		        }
		        //Adds a Bracket in front of the string.
		        stringResult += "[";
		        
		        stringResult += this.oList[i].toString();
		        
		        //Adds a Bracket at the back of the string.
		        stringResult += "]";
		    }
	
		    //Return the properly formatted string.
		    return stringResult;
		}
		
		
}
		
		

	
	


