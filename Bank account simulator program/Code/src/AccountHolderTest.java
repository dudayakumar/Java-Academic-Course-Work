/*
 * Class name: AccountHolderTest 
 * Description: Test class to test every scenario of bank operations
 * Programmed by: Dhivya Udaya Kumar
 * CWID: A20432502
 * Date: 15-Sep-2018 
 * Lab number: 01
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AccountHolderTest {//class-S

	public static void main(String[] args) {//main-S
		
		/*
		 * Declaring initial variables
		 */
		
		Scanner sc = new Scanner(System.in);
		Double userResponse = 0.0;
		Integer numOfMonths = 0;
		Integer userSelection = 0;
		Integer userSetPin = 0000;
		
		/*
		 * Initializing account-holder object
		 */
		
		AccountHolder ahObj = null;
		
		/*
		 * Setting up welcome message, date and time	
		 */
		
		LocalDateTime locDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = locDateTime.format(formatter);
		System.out.println("Current date: "+ formatDateTime);
		System.out.println("Programmed by Dhivya Udaya Kumar \n");
		System.out.println("Welcome to Bank of Illinois Institute of Technology, Chicago \n");
		
		/*
		 * Logic to accept initial deposit to start user's account
		 */
	
		do {//DW1-S
			System.out.println("Please choose the following options to proceed");
			
			System.out.print("Enter 1 for initial deposit: ");
			if(sc.hasNextInt()) {
				userSelection = sc.nextInt();
			}
			else {
				sc.next();
			}
			if(userSelection != 1) {
				System.out.println("Please choose a valid number! \n");
			}			
		}while(userSelection != 1);//DW1-E
		
		do {//DW2-S
			System.out.println();
			System.out.print("Enter the amount you wish to deposit : ");
			if(sc.hasNextDouble()) {
				userResponse = sc.nextDouble();
			}
			else {
				sc.next();
			}
			
			if(userResponse > 0) {
				ahObj = new AccountHolder(userResponse);
				System.out.println("Transaction successful!");
				System.out.println("Transaction date & time: "+formatDateTime);
				System.out.println("Your account balance is: "+ahObj.toString() +"\n\n");
				System.out.println("---------End of Transaction---------");
			}
			else {//handling case where initial deposit is <= $0 or not a valid number
				System.out.println("Invalid amount entered! Please enter a valid amount!");
			}
		}while(userResponse <= 0);//DW2-E
		
		/*
		 *Menu Entry for user 
		 */
		do {//DW3-S
			System.out.println("What would you like to do now?: ");
			System.out.println("1. Deposit money into my account");
			System.out.println("2. Withdraw money from my account");
			System.out.println("3. Check available balance in my account");
			System.out.println("4. Calculate standard ROI");
			System.out.println("5. Update interest rate");
			System.out.println("6. Run report");
			System.out.println("7. Sign out");
			
			do {//DW4-S
				System.out.print("Choose an option : ");
				if(sc.hasNextInt()) {
					userSelection = sc.nextInt();
				}
				else {
					sc.next();
				}
				
				if(userSelection < 1 || userSelection > 7) {//if-S
					System.out.println("Invalid! Please enter a valid number! \n");
				}//if-E
			}while(userSelection <=1 && userSelection >= 7);//DW4-E
			
		    switch(userSelection) {//switch-S
		    case 1:
		    	{//c1-S
		    		/*
		    		 * Testing deposit addition scenario 
		    		 */
		    		System.out.print("Enter deposit amount : ");
		    		userResponse = sc.nextDouble();
		    		
		    		//Add deposit to existing balance in user's account
		    		ahObj.deposit(userResponse);
		    		System.out.println("Transaction successful!");
					System.out.println("Transaction date & time: "+formatDateTime);
					System.out.println("Your account balance is: "+ahObj.toString() +"\n");
					System.out.println("---------End of Transaction---------");
					System.out.println();
					System.out.println("Current date: "+ formatDateTime);
					System.out.println("Programmed by Dhivya Udaya Kumar \n");
		    		
		    		break;
		    	}//c1-E
		    case 2:
		    	{//c2-S
		    		/*
		    		 * Testing withdrawal deduction scenario 
		    		 */
		    		System.out.println("Current balance: "+ahObj.toString());
		    		System.out.print("Enter withdrawal amount : ");
		    		userResponse = sc.nextDouble();
		    		//Deduct amount from existing balance in user's account
		    		ahObj.withdrawal(userResponse);
					System.out.println("Transaction date & time: "+formatDateTime);
					System.out.println("Your account balance is: "+ahObj.toString() +"\n");
					System.out.println("---------End of Transaction--------- \n");
					System.out.println();
					System.out.println("Current date: "+ formatDateTime);
					System.out.println("Programmed by Dhivya Udaya Kumar \n");
					
		    		break;
		    	}//c2-E
		    case 3:
		    	{//c3-S
		    		/*
		    		 * Testing balance check scenario 
		    		 */
		    		System.out.println("Your account balance is: "+ahObj.toString() +"\n");
		    		System.out.println("---------End of Transaction--------- \n");
		    		System.out.println();
					System.out.println("Current date: "+ formatDateTime);
					System.out.println("Programmed by Dhivya Udaya Kumar \n");
		    		
		    		break;
		    	}//c3-E
		    case 4:
		    	{//c4-S
		    		/*
		    		 * Calculate standard ROI
		    		 */
		    		do {
		    			System.out.println("Welcome to standard ROI calculation");
		    			System.out.println("Calculating ROI for your account balance of "+ahObj.toString());
		    			System.out.println("Rate of Interest = "+ ahObj.getAnnualInterestRate()+"%");
		    			System.out.print("Enter number of months for interest calculation: ");
		    			userResponse = sc.nextDouble();
		    			
		    			if(userResponse <= 0) {
		    				System.out.println("Invalid entry. Please enter valid value.");
		    				System.out.println();
							System.out.println("Current date: "+ formatDateTime);
							System.out.println("Programmed by Dhivya Udaya Kumar \n");
		    			}
		    			else {	    				
		    				System.out.println("Interest for " + userResponse + " months = $" + String.format("%.2f", ahObj.roi(userResponse)) +"\n");
		    				System.out.println("---------End of Transaction--------- \n");
		    				System.out.println();
							System.out.println("Current date: "+ formatDateTime);
							System.out.println("Programmed by Dhivya Udaya Kumar \n");
		    			}
		    		}while(userResponse <= 0);
		    		
		    		break;
		    	}//c4-E
		    case 5:
		    	{//c5-S
		    		/*
		    		 * Set new Interest rate
		    		 */
		    		System.out.println("Set new interest rate : ");
		    		userResponse = sc.nextDouble();
		    		System.out.println("Previous Rate of Interest = "+ahObj.getAnnualInterestRate());
		    		AccountHolder.modifyMonthlyInterest(userResponse);
		    		System.out.println("Current Rate of Interest = "+ahObj.getAnnualInterestRate());
		    		System.out.println("Calculating ROI for your account balance of "+ahObj.toString());
		    		ahObj.monthlyInterest();
		    		System.out.println("Your projected balance for next month will be "+ahObj.toString() +"\n");
		    		System.out.println("---------End of Transaction--------- \n");
		    		System.out.println();
					System.out.println("Current date: "+ formatDateTime);
					System.out.println("Programmed by Dhivya Udaya Kumar \n");
		    		
		    		break;		    		
		    	}//c5-E
		    case 6:
		    	{//c6-S
		    		/*
		    		 * Run Report 
		    		 */
		    		do {
			    		System.out.println("Calculating ROI for your account balance of "+ahObj.toString());
		    			System.out.println("Rate of Interest = "+ ahObj.getAnnualInterestRate());
		    			System.out.print("Enter number of months for interest calculation: ");
		    			numOfMonths = sc.nextInt();
		    			
		    			if(userResponse <= 0) {
		    				System.out.println("Invalid entry. Please enter valid value.");
		    			}
		    			else {	    				
		    				System.out.println("Calculating your balance with ROI of " + ahObj.getAnnualInterestRate() + " for " + numOfMonths + " months:");
		    				System.out.println("\n");
		    				for(int i=1; i<= numOfMonths ; i++) {
		    					ahObj.monthlyInterest();
		    					System.out.println("For month " + i + ", your balance is "+ ahObj.toString());
		    				}
		    				System.out.println();
		    				System.out.println("For a period of " + numOfMonths + " months, your projected balance will be "+ ahObj.toString()+ "\n");
		    				System.out.println("---------End of Transaction--------- \n");
		    				System.out.println();
							System.out.println("Current date: "+ formatDateTime);
							System.out.println("Programmed by Dhivya Udaya Kumar \n");
		    			}
		    		}while(userResponse <= 0);
		    		
		    		break;
		    	}//c6-E
		    case 7:
		    	{//c7-S
		    		/*
		    		 * Sign out of the user's account
		    		 */
		    		System.out.println("Thank you! Have a great day!");
		    		System.out.println();
					System.out.println("Current date: "+ formatDateTime);
					System.out.println("Programmed by Dhivya Udaya Kumar \n");
		    		
		    		break;
		    	}//c7-E
		    }//switch-E
		
		}while(userSelection != 7);//DW1-E
		
	}//main-E

}//class-E