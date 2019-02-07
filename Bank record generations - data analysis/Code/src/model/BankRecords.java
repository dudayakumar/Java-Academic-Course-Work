package model;
/*
 * Class name: BankRecords
 * Description: Class contains methods to read, process and print data extracted from the CSV file
 * Programmed by: Dhivya Udaya Kumar
 * CWID: A20432502
 * Date: 24-Oct-2018 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankRecords extends Client {//class-S

	// Static fields for IO processing

	// Array of BankRecords objects
	protected static BankRecords robjs[] = new BankRecords[600];
	// arrayList to hold spreadsheet rows & columns
	static ArrayList<List<String>> array = new ArrayList<>();

	// Instance fields
	private String id;
	private int age;
	private String sex;
	private String region;
	private double income;
	private String married;
	private int children;
	private String car;
	private String save_act;
	private String current_act;
	private String mortgage;
	private String pep;

	// Getters and setters for the instance fields
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public String getMarried() {
		return married;
	}

	public void setMarried(String married) {
		this.married = married;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getSave_act() {
		return save_act;
	}

	public void setSave_act(String save_act) {
		this.save_act = save_act;
	}

	public String getCurrent_act() {
		return current_act;
	}

	public void setCurrent_act(String current_act) {
		this.current_act = current_act;
	}

	public String getMortgage() {
		return mortgage;
	}

	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}

	public String getPep() {
		return pep;
	}

	public void setPep(String pep) {
		this.pep = pep;
	}

	/*
	 * Method name: readData
	 * Description: Method to read data from the CSV file
	 * Arguments: None
	 * Return type: void 
	 */
	public void readData() {//rD-S
		File file = new File("bank-Detail.csv");
		BufferedReader br;
		try {
			FileReader fReader = new FileReader(file);
			br = new BufferedReader(fReader);
			if(file.length() == 0) {
				throw new NullPointerException();
			}
			String line;
			while ((line = br.readLine()) != null) {
				// Parsing each record stored in csv file by a comma(,) into a list stored in the arraylist -> Arrays
				array.add(Arrays.asList(line.split(",")));
			}
			
			//Calling processData method
			processData();

		}  catch(ArrayIndexOutOfBoundsException e) {
			System.out.println(file.toString() + " File is empty");
			System.out.println(e);
		} catch(NullPointerException e) {
			System.out.println(file.toString() + " File is empty");
			System.out.println(e);
		}catch (FileNotFoundException e) {
			System.out.println("Cannot read file: " + file.toString());
			System.out.println(e);
		} catch (IOException e) {
			System.out.println("Unable to read file: " + file.toString());
		}
		
	}//rD-E

	/*
	 * Method name: readData
	 * Description: Method to process data from the CSV file
	 * Arguments: None
	 * Return type: void 
	 */
	public void processData() {//psD-S
		int idx = 0;

		for (List<String> rowData : array) {//FE-S
			robjs[idx] = new BankRecords();
			robjs[idx].setId(rowData.get(0));
			robjs[idx].setAge(Integer.parseInt(rowData.get(1)));
			robjs[idx].setSex(rowData.get(2));
			robjs[idx].setRegion(rowData.get(3));
			robjs[idx].setIncome(Double.parseDouble(rowData.get(4)));
			robjs[idx].setMarried(rowData.get(5));
			robjs[idx].setChildren(Integer.parseInt(rowData.get(6)));
			robjs[idx].setCar(rowData.get(7));
			robjs[idx].setSave_act(rowData.get(8));
			robjs[idx].setCurrent_act(rowData.get(9));
			robjs[idx].setMortgage(rowData.get(10));
			robjs[idx].setPep(rowData.get(11));

			idx++;
		}//FE-E		
	}//psD-E

	/*
	 * Method name: printData
	 * Description: Method to print data from the CSV file onto console
	 * Arguments: None
	 * Return type: void 
	 */
	public void printData() {//pD-S

	}//pD-E
}//class-E