package model;

/*
 * Class name: Records
 * Description: Class contains methods to perform operations on the csv file to calculate averages, totals, minimum and maximum values
 * Programmed by: Dhivya Udaya Kumar
 * CWID: A20432502
 * Date: 24-Oct-2018 
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Records extends BankRecords {//class-S

	// create formatted object to write output directly to the console and to a file
	static FileWriter fw = null;

	/*
	 * Method name: Records
	 * Description: Constructor of the class
	 */
	public Records() {//Records-S
		try {
			fw = new FileWriter("bankrecords.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Cannot read file: " + fw.toString());
			System.out.println(e);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//Records-E
	
	/*
	 * Method name: main
	 * Description: Main method of the class; Execution begins here
	 * Arguments: String
	 * Return type: void 
	 */
	public static void main(String[] args) {//main-S

		LocalDateTime locDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = locDateTime.format(formatter);
		
		System.out.println("Welcome to Bank of IIT Chicago");
		System.out.println("Current date: " + formatDateTime);
		System.out.println("Programmed by Dhivya Udaya Kumar.\n");
		
		Records br = new Records();
		br.readData();
		
		try {
			LocationComp(); // analyze average income per location
			MaxMinComp(); // compare maximum and minimum incomes per location
			femsComp(); // analyze females with mortgage/savings accounts per location
			malesComp(); // analyze male count with car and 1 child per location

			fw.close(); // Closing file object

		} catch (FileNotFoundException e) {
			System.out.println("Cannot read file: " + fw.toString());
			System.out.println(e);
		} catch (NullPointerException e) {
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//main-E

	/*
	 * Method name: LocationComp
	 * Description: Method to calculate average incomes per location
	 * Arguments: none
	 * Return type: void 
	 */
	private static void LocationComp() {//LocationComp-S
		Arrays.sort(robjs, new LocationComparator());

		// Setting up variables required for region counts & incomes per location
		double townCt = 0, innerCt = 0, suburbanCt = 0, ruralCt = 0, townIncSum = 0, innerIncSum = 0,
				suburbanIncSum = 0, ruralIncSum = 0;

		for (int i = 0; i < robjs.length; i++) {
			if (robjs[i].getRegion().equals("RURAL")) {
				ruralIncSum += robjs[i].getIncome();
				++ruralCt;
			} else if (robjs[i].getRegion().equals("INNER_CITY")) {
				innerIncSum += robjs[i].getIncome();
				++innerCt;
			} else if (robjs[i].getRegion().equals("TOWN")) {
				townIncSum += robjs[i].getIncome();
				++townCt;
			} else if (robjs[i].getRegion().equals("SUBURBAN")) {
				suburbanIncSum += robjs[i].getIncome();
				++suburbanCt;
			}
		}

		// setup resulting averages to print to console and to file
		double ruralAvg = ruralIncSum / ruralCt;
		double innerAvg = innerIncSum / innerCt;
		double townAvg = townIncSum / townCt;
		double suburbanAvg = suburbanIncSum / suburbanCt;

		System.out.printf("Updating average income per region... \n");
		System.out.format("Average income for inner city region: %6.2f", innerAvg);
		System.out.println();
		System.out.format("Average income for rural region: %6.2f", ruralAvg);
		System.out.println();
		System.out.format("Average income for suburban region: %6.2f", suburbanAvg);
		System.out.println();
		System.out.format("Average income for town region: %6.2f", townAvg);
		System.out.println();
		System.out.println();

		try {
			fw.write(String.format("Average income for inner city region: %6.2f", innerAvg) + System.lineSeparator());
			fw.write(String.format("Average income for rural region: %6.2f", ruralAvg) + System.lineSeparator());
			fw.write(String.format("Average income for suburban region: %6.2f", suburbanAvg) + System.lineSeparator());
			fw.write(String.format("Average income for town region: %6.2f", townAvg) + System.lineSeparator()
					+ System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//LocationComp-E

	/*
	 * Method name: femsComp
	 * Description: Method to calculate count of females with mortgage and savings account per location
	 * Arguments: none
	 * Return type: void 
	 */
	private static void femsComp() {//femsComp-S
		Arrays.sort(robjs, new LocationComparator());

		// Setting up variables required for mortgage and savings account counts per
		// location
		int townCt = 0, innerCt = 0, suburbanCt = 0, ruralCt = 0;

		for (int i = 0; i < robjs.length; i++) {
			if (robjs[i].getSex().equals("FEMALE") && robjs[i].getMortgage().equals("YES")
					&& robjs[i].getSave_act().equals("YES")) {
				if (robjs[i].getRegion().equals("RURAL")) {
					++ruralCt;
				} else if (robjs[i].getRegion().equals("INNER_CITY")) {
					++innerCt;
				} else if (robjs[i].getRegion().equals("TOWN")) {
					++townCt;
				} else if (robjs[i].getRegion().equals("SUBURBAN")) {
					++suburbanCt;
				}
			}
		}

		System.out.printf("Updating count of females with mortgage and savings account per location... \n");
		System.out.println("Inner city region females with mortgage and savings account: " + innerCt);
		System.out.println("Rural region females with mortgage and savings account: " + ruralCt);
		System.out.println("Suburban region females with mortgage and savings account: " + suburbanCt);
		System.out.println("Town region females with mortgage and savings account: " + townCt + "\n");

		try {
			fw.write(
					"Inner city region females with mortgage and savings account: " + innerCt + System.lineSeparator());
			fw.write("Rural region females with mortgage and savings account: " + ruralCt + System.lineSeparator());
			fw.write("Suburban region females with mortgage and savings account: " + suburbanCt
					+ System.lineSeparator());
			fw.write("Town region females with mortgage and savings account: " + townCt + System.lineSeparator()
					+ System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//femsComp-E

	/*
	 * Method name: femsComp
	 * Description: Method to calculate count of males with car and 1 child per location
	 * Arguments: none
	 * Return type: void 
	 */
	private static void malesComp() {//malesComp-S
		Arrays.sort(robjs, new LocationComparator());
		// Setting up variables required for mortgage and savings account counts per
		// location
		int townCt = 0, innerCt = 0, suburbanCt = 0, ruralCt = 0;

		for (int i = 0; i < robjs.length; i++) {
			if (robjs[i].getSex().equals("MALE") && robjs[i].getCar().equals("YES") && robjs[i].getChildren() == 1) {
				if (robjs[i].getRegion().equals("RURAL")) {
					++ruralCt;
				} else if (robjs[i].getRegion().equals("INNER_CITY")) {
					++innerCt;
				} else if (robjs[i].getRegion().equals("TOWN")) {
					++townCt;
				} else if (robjs[i].getRegion().equals("SUBURBAN")) {
					++suburbanCt;
				}
			}
		}

		System.out.printf("Updating count of males with car and 1 child per location... \n");
		System.out.println("Inner city region males with car and 1 child per location: " + innerCt);
		System.out.println("Rural region males with car and 1 child per location: " + ruralCt);
		System.out.println("Suburban region males with car and 1 child per location: " + suburbanCt);
		System.out.println("Town region males with car and 1 child per location: " + townCt + "\n");

		try {
			fw.write("Inner city region males with car and 1 child per location: " + innerCt + System.lineSeparator());
			fw.write("Rural region males with car and 1 child per location: " + ruralCt + System.lineSeparator());
			fw.write("Suburban region males with car and 1 child per location: " + suburbanCt + System.lineSeparator());
			fw.write("Town region males with car and 1 child per location: " + townCt);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//malesComp-E

	/*
	 * Method name: femsComp
	 * Description: Method to calculate maximum and minimum income per region
	 * Arguments: none
	 * Return type: void 
	 */
	private static void MaxMinComp() {//MaxMinComp-S
		Arrays.sort(robjs, new LocationComparator());

		// Setting up variables required for regions' maximum and minimum income per
		// location
		double townIncMax = -1, innerIncMax = -1, suburbanIncMax = -1, ruralIncMax = -1, townIncMin = -1,
				innerIncMin = -1, suburbanIncMin = -1, ruralIncMin = -1;

		for (int i = 0; i < robjs.length; i++) {
			if (robjs[i].getRegion().equals("RURAL")) {
				if (ruralIncMin == -1 && ruralIncMax == -1) {
					ruralIncMin = ruralIncMax = robjs[i].getIncome();
				}
			} else if (robjs[i].getRegion().equals("INNER_CITY")) {
				if (innerIncMin == -1 && innerIncMax == -1) {
					innerIncMin = innerIncMax = robjs[i].getIncome();
				}
			} else if (robjs[i].getRegion().equals("TOWN")) {
				if (townIncMin == -1 && townIncMax == -1) {
					townIncMin = townIncMax = robjs[i].getIncome();
				}
			} else if (robjs[i].getRegion().equals("SUBURBAN")) {
				if (suburbanIncMin == -1 && suburbanIncMax == -1) {
					suburbanIncMin = suburbanIncMax = robjs[i].getIncome();
				}
			}
		}

		for (int i = 0; i < robjs.length; i++) {
			if (robjs[i].getRegion().equals("RURAL")) {
				if (robjs[i].getIncome() < ruralIncMin) {
					ruralIncMin = robjs[i].getIncome();
				}
				if (robjs[i].getIncome() > ruralIncMax) {
					ruralIncMax = robjs[i].getIncome();
				}
			} else if (robjs[i].getRegion().equals("INNER_CITY")) {
				if (robjs[i].getIncome() < innerIncMin) {
					innerIncMin = robjs[i].getIncome();
				}
				if (robjs[i].getIncome() > innerIncMax) {
					innerIncMax = robjs[i].getIncome();
				}
			} else if (robjs[i].getRegion().equals("TOWN")) {
				if (robjs[i].getIncome() < townIncMin) {
					townIncMin = robjs[i].getIncome();
				}
				if (robjs[i].getIncome() > townIncMax) {
					townIncMax = robjs[i].getIncome();
				}
			} else if (robjs[i].getRegion().equals("SUBURBAN")) {
				if (robjs[i].getIncome() < suburbanIncMin) {
					suburbanIncMin = robjs[i].getIncome();
				}
				if (robjs[i].getIncome() > suburbanIncMax) {
					suburbanIncMax = robjs[i].getIncome();
				}
			}
		}

		System.out.printf("Updating maximum income per region... \n");
		System.out.println("Maximum income for inner city region: " + innerIncMax);
		System.out.println("Maximum income for rural region: " + ruralIncMax);
		System.out.println("Maximum income for suburban region: " + suburbanIncMax);
		System.out.println("Maximum income for town region: " + townIncMax + "\n");

		System.out.printf("Updating minimum income per region... \n");
		System.out.println("Minimum income for inner city region: " + innerIncMin);
		System.out.println("Minimum income for rural region: " + ruralIncMin);
		System.out.println("Minimum income for suburban region: " + suburbanIncMin);
		System.out.println("Minimum income for town region: " + townIncMin + "\n");

		try {
			fw.write("Maximum income for inner city region: " + innerIncMax + System.lineSeparator());
			fw.write("Maximum income for rural region: " + ruralIncMax + System.lineSeparator());
			fw.write("Maximum income for suburban region: " + suburbanIncMax + System.lineSeparator());
			fw.write("Maximum income for town region: " + townIncMax + System.lineSeparator() + System.lineSeparator());

			fw.write("Minimum income for inner city region: " + innerIncMin + System.lineSeparator());
			fw.write("Minimum income for rural region: " + ruralIncMin + System.lineSeparator());
			fw.write("Minimum income for suburban region: " + suburbanIncMin + System.lineSeparator());
			fw.write("Minimum income for town region: " + townIncMin + System.lineSeparator() + System.lineSeparator());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}//MaxMinComp-E
}//class-E
