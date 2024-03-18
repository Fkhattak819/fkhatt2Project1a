/**
* <Car Class, represents a car with attributes of make, year, and price. Implements comparable class that sorts cars 
*  based off make and then year if makes are the same.>
*
* CSC 1351 Programming Project No 1
*
* Section 2
*
* @author <Fahd Khattak>
* @since <March 17th, 2024>
*
*/
public class Car implements Comparable<Car> {
	
    private String make; // Manufacturer of the car
    private int year; // Year the car was made
    private int price; // Price of the car
    
    /**
    * <Car Constructor, makes instances of the car parts. Takes in make, year, and price as params>
    *
    * CSC 1351 Programming Project No 1
    *
    * Section 2
    *
    * @author <Fahd Khattak>
    * @since <March 17th, 2024>
    *
    */
    public Car(String make, int year, int price) {
    	
        this.make = make;
        this.year = year;
        this.price = price;
    }

    /**
    * <getMake, returns make>
    *
    * CSC 1351 Programming Project No 1
    *
    * Section 2
    *
    * @author <Fahd Khattak>
    * @since <March 17th, 2024>
    *
    */
    public String getMake() {
        return make;
    }
    
    /**
     * <getYear, returns year>
     *
     * CSC 1351 Programming Project No 1
     *
     * Section 2
     *
     * @author <Fahd Khattak>
     * @since <March 17th, 2024>
     *
     */
    public int getYear() {
        return year;
    }
    
    /**
     * <getPrice, returns price>
     *
     * CSC 1351 Programming Project No 1
     7
     * Section 2
     *
     * @author <Fahd Khattak>
     * @since <March 17th, 2024>
     *
     */
    public int getPrice() {
        return price;
    }
    
    /**
     * <compareTo, compares cars primarily on make, then year if makes are identical. Returns sorted comparison of the two cars. >
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
    public int compareTo(Car other) {
    	
        // Compare the make of this car to the make of another car
        int makeComparison = this.make.compareTo(other.make);
        
        // The Integer.compare method returns a negative value if this.make is less than other.make alphabetically,
        // zero if they are equal, and a positive value if this.make is greater than other.make
        if (makeComparison != 0) {
   
            return makeComparison;
        } 
        else {
        	
        	//same logic only if makes are the same.
            return Integer.compare(this.year, other.year);
        }
    }
    
    /**
     * <toString, returns string representation of the car by make, year, and price.>
     *
     * CSC 1351 Programming Project No 1
     *
     * Section 3
     *
     * @author <Fahd Khattak>
     * @since <March 17th, 2024>
     *
     */

    @Override
    public String toString() {
        return "Make: " + make + ", Year: " + year + ", Price: " + price;
    }
    
    
}
