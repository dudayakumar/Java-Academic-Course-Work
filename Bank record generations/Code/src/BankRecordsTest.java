/*
 * Class name: BankRecordsTest 
 * Description: Test class to test every BankRecords class methods
 * Programmed by: Dhivya Udaya Kumar
 * CWID: A20432502
 * Date: 3-Oct-2018 
 */

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankRecordsTest {//class-S

	/*
	 * Method name: main
	 * Description: Main method of the class; Execution begins here
	 * Arguments: String
	 * Return type: void 
	 */
	public static void main(String[] args) throws FileNotFoundException {//main-S
		
		//Setting up welcome message	
				LocalDateTime locDateTime1 = LocalDateTime.now();
				DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String formatDateTime1 = locDateTime1.format(formatter1);
				System.out.println("Current date: "+ formatDateTime1);
				System.out.println("Programmed by Dhivya Udaya Kumar");
				System.out.println("Welcome to Bank of IIT Chicago Customer record generation section\n");
		//Instantiating BankRecords object
		BankRecords bRec = new BankRecords();
		
		//Calling readData method from BankRecords class
		bRec.readData();
		
	}//main-E

}//class-E
