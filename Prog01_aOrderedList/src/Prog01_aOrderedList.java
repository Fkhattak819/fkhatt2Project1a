import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
* <GProg01_aOrderedList Class, Main class that reads an input file from the user, creates car objects from the file that are sorted,
*  passes the file into the ordered list to be manipulated. Then the user outputs the data to a custom file.>
*
* CSC 1351 Programming Project No 1
* Section 2
*
* @author <Fahd Khattak>
* @since <March 17th, 2024>
*
*/
public class Prog01_aOrderedList {
	

	/*
	* <Main Method, This method prompts the user to input a filename to read car data from. It then processes each line of the input file,
	*  interpreting each line as data for a car object. Adding new car objects to an ordered list based on
	*  the input data ('A' to add a car, 'D' to delete a car based on make and year). After processing the input file,
	*  it prompts the user for an output filename, where it writes each car in the ordered list, including make,
	*  year, and price.>
	*
	* CSC 1351 Programming Project No 1
	* 
	* Section 2
	*
	* @author <Fahd Khattak>
	* @since <March 17th, 2024>
	*
	*/
	public static void main(String[] args) throws FileNotFoundException {
		
		// Creates a new aOrderedList object
        aOrderedList orderedList = new aOrderedList(); 
		Scanner scanner = new Scanner(System.in);

		//Ask user for the file name.
		System.out.println("Enter input filename: ");
		String inputFileName = scanner.nextLine();
		Scanner inputFile = getInputFile(inputFileName); // File ready to be read.
		
		
		
		while (inputFile.hasNextLine()) { // Stops if there is no line left in the file.
			
			// Converts every line in the file into an array.
            String line = inputFile.nextLine(); 
            String[] carData = line.split(",");
            
            		
            if (carData[0].toUpperCase().equals("A")) { //'A' means to add the car
            	
            	// Takes the elements 1,2,3 and uses them to create a car object. Needs to be parsed because values in the file are all strings.
                Car newCar = new Car(carData[1], Integer.parseInt(carData[2]), Integer.parseInt(carData[3])); 
                orderedList.add(newCar); // Adds the car to the orderedList
            }
            
            else if (carData.length == 2 && carData[0].toUpperCase().equals("D")) { // Deleting car with out specified make and year.
            	
            	int index = Integer.parseInt(carData[1]);
            	try {
            		
	            	if (index >= 0 && index < orderedList.size()) {
	            		
	                    orderedList.remove(index);
	                } else {
	                	//Index not found
	                    System.out.println("Index out of bounds for orderedList: " + index);
	                }
	            	
	            //Catching a NumberFormatException where e holds the exception.
            	}catch(NumberFormatException e) {
            		
            		System.out.println("Invalid number format for index: \" + carData[1])");
            	}
            	
            }
            else  if (carData.length == 3 && carData[0].toUpperCase().equals("D")) { //'D' means to delete car
        	   
        	   String make = carData[1]; // Makes a "make" variable from the second element in the line.
        	   int year = Integer.parseInt(carData[2]); // Makes a int year from the third element in the line.
        	   int index = orderedList.getObjectIndex(make, year); // Passes the variables into the getObject method, an returns the index the line is in the orderedList.
        	   
        	   if (index >= 0) { // if the index is found, continue.
        		   orderedList.remove(index); // Removes the index from the ordered list.
        	   }
                
            }
            
        }
		
		//Requesting user for the output file name.
		System.out.println("Enter output filename: ");
		String userOutputName = scanner.nextLine();
        
        PrintWriter outputWriter = GetOutputFile(userOutputName); // Creates a file called outputWriter to input the final data.
        
        
        try(outputWriter){
        	
	        outputWriter.println("Number of cars: " + orderedList.size()); // Print out number of objects in the orderedList
	        outputWriter.println(); 
	        
        // Output each Car object in the specified format by going through the entire ordered list.
	        for (int i = 0; i < orderedList.size(); i++) {
        	
	            Comparable<?> Comparable = orderedList.get(i);
	            
	            Car car = (Car) Comparable; // Casting car
	            
	            outputWriter.println("Make: " + car.getMake());
	            
	            outputWriter.println("Year: " + car.getYear());
	
	            outputWriter.println("Price: $" + car.getPrice());
	            outputWriter.println(); // Blank line after each car's details
	            

	        }
	       
        }catch (Exception notValid){ // Holds the exception when caught
       
        	notValid.printStackTrace(); // Printing the stack trace of the caught exception to the standard error stream.

      }
        
        scanner.close();
        outputWriter.close();
    }
		

	
	/*
	* <getInputFile Method, takes in userPrompt(name of inputFile) validates, opens it for reading, then returns
	*  a Scanner object of the file. If file doesn't exist user is ask to quit using 'N'.>
	*
	* CSC 1351 Programming Project No 1
	* Section 2
	*
	* @author <Fahd Khattak>
	* @since <March 17th, 2024>
	*
	*/
	public static Scanner getInputFile(String userPrompt) throws FileNotFoundException {
		
		Scanner scanner = new Scanner(System.in);
		
		// Takes in user name and converts into a string for a file to be created.
        String fileName = userPrompt; 
        
        // Creating the file.
        File myfile = new File(fileName); 
        
        while (!myfile.exists()) { //Keeps running the loop until the user types 'N', thus throws FileNotFoundExecption
        	
            System.out.printf("File specified <%s> does not exist. Would you like to continue? <Y/N>", fileName);
            String answer = scanner.nextLine();
            
            if ("N".equals(answer.toUpperCase())) {
            	
                throw new FileNotFoundException();
            }
            
            fileName = scanner.nextLine();
            myfile = new File(fileName);
        }
	
        
     //Return Scanner of the file ready to be read.
     return new Scanner(myfile);

}
       
	/*
	* <GetOutputFile Method, takes in userPrompt and validates the file path, returns the writer(file) to be written on.>
	*
	* CSC 1351 Programming Project No 1
	* Section 2
	*
	* @author <Fahd Khattak>
	* @since <March 17th, 2024>
	*
	*/
	public static PrintWriter GetOutputFile(String userPrompt) throws FileNotFoundException{
		
	        Scanner scanner = new Scanner(System.in);
	        PrintWriter writer = null; // Initializes the writer file
	        boolean fileValid = false; // Logic variable that becomes true when file is valid.

	        while (!fileValid) {
	            try {
	                
	                String outputFileName = userPrompt;
	                
	                writer = new PrintWriter(outputFileName);
	                
	                fileValid = true; // If no exception is thrown, file path is valid
	                
	            } catch (FileNotFoundException notVaild) {
	            	
	                System.out.println("Invalid file path. Do you want to try again? (Y/N)");
	                String answer = scanner.nextLine();
	                
	                if (answer.toUpperCase().equals("N")){
	                	
	                	// Rethrow exception to exit the method if user does not want to try again
	                    throw notVaild; 
	                }
	               
	            }
	            scanner.close();
	       }
	        
	     // return a PrintWriter ready to be written on.
	     return writer;
	  }

	
}
	

