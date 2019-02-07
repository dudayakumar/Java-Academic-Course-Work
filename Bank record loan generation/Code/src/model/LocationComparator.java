package model;
/*
 * Class name: LocationComparator
 * Description: Class contains method to compare location regions
 * Programmed by: Dhivya Udaya Kumar
 * CWID: A20432502
 * Date: 24-Oct-2018 
 */
import java.util.Comparator;

public class LocationComparator implements Comparator<BankRecords> {//class-S

	@Override
	public int compare(BankRecords br1, BankRecords br2) {//compare-S
		int result = br1.getRegion().compareTo(br2.getRegion());
		return result;
	}//compare-E
}//class-E
