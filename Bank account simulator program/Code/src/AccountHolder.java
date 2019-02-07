/*
 * Class name: AccountHolder
 * Description: Class contains methods for various bank operations
 * Programmed by: Dhivya Udaya Kumar
 * CWID: A20432502
 * Date: 15-Sep-2018 
 * Lab number: 01
 */

import java.util.Objects;
import java.util.Scanner;

public class AccountHolder {

	//Current account balance
	private double balance=0;
	
	//Default interest rate
	private static double annualInterestRate = 0.04;
	
	/*
	 * Method name: main
	 * Description: Main method of the class; Execution begins here
	 * Arguments: String
	 * Return type: void 
	 */
		
	public static void main(String[] args) {		
		//Object creation
		AccountHolder accBalance = new AccountHolder(1000);
	}

	/*
	 * Constructor: AccountHolder
	 * Description: Creates new account with user specified balance
	 * Arguments: double
	 */ 
	
	public AccountHolder(double balance) {
		if(balance < 0) {
			//Error trapping for negative input by user
			System.out.println("Please enter a positive value");
		}
		else {
			this.balance = balance;
		}
	}
	
	/*
	 * Method name: deposit
	 * Description: Method adds amount deposited by user to existing balance
	 * Arguments: double
	 * Return type: void 
	 */
	
	public void deposit(double depositAmount) {
		this.balance = this.balance + depositAmount;
	}
	
	/*
	 * Method name: withdrawal
	 * Description: Method subtracts amount withdrawn by user from existing balance
	 * Arguments: double
	 * Return type: void 
	 */
	
	public void withdrawal(double withdrawAmount) {		
		if(this.balance - withdrawAmount < 500) {
			Scanner sc = new Scanner(System.in);
			String response;
			System.out.println("Warning: If account balance goes below $500, a penalty of $50 will be deducted from your account!");
			System.out.print("Would you like to continue? (Y/N) : ");
			response = sc.nextLine();
			if(this.balance - withdrawAmount - 50 < 100 && (Objects.equals(response, "y") || Objects.equals(response, "Y"))) {
				System.out.println("Minimum balance of $100 needs to be maintained!");
				System.out.println("Withdrawal cancelled.");
			}
			else if (Objects.equals(response, "y") || Objects.equals(response, "Y")){
				this.balance = this.balance - withdrawAmount - 50;
				System.out.println("Transaction successful!");
			}
			else if (Objects.equals(response, "n") || Objects.equals(response, "N")){
				System.out.println("Withdrawal cancelled.");
			}
		}
		else {
			this.balance = this.balance - withdrawAmount;
			System.out.println("Transaction successful!");
		}
	}
	
	/*
	 * Method name: getAnnualInterestRate
	 * Description: Getter method for fetching current interest rate
	 * Arguments: None
	 * Return type: double 
	 */
	
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	
	/*
	 * Method name: monthlyInterest
	 * Description: Method updates existing balance with the addition of annual interest
	 * Arguments: None
	 * Return type: void 
	 */
	
	public void monthlyInterest() {
		balance += balance * (annualInterestRate / 12.0);
	}
	
	/*
	 * Method name: roi
	 * Description: Method to calculate standard ROI for given number of months
	 * Arguments: double
	 * Return type: double 
	 */
	
	public double roi(double numOfMonths) {
		return balance * numOfMonths * (annualInterestRate / 12.0);
	}
	
	/*
	 * Method name: modifyMonthlyInterest
	 * Description: Method updates annual interest rate with new rate
	 * Arguments: double
	 * Return type: void 
	 */
	
	public static void modifyMonthlyInterest(double rateUpdate) {		
		if((rateUpdate > 0.01) && (rateUpdate < 1.00)) {
			annualInterestRate = rateUpdate;
		}
		else {
			System.out.println("Please enter interest rate in decimal ranging (0.01 - 1.00)");
		}
	}
	
	/*
	 * Method name: toString
	 * Description: Method converts the balance amount to user-friendly format
	 * Arguments: None
	 * Return type: String 
	 */
	
	public String toString() {
		return String.format("$%.2f", balance);
	}
}