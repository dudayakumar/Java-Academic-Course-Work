package controller;

//import static model.BankRecords.robjs;

import java.sql.ResultSet;

import model.BankRecords;
import model.DAOModel;
import view.LoanView;

public class LoanProcessing extends BankRecords{
	public static void main(String args[]) {
		BankRecords br = new BankRecords();
		br.readData();
		DAOModel dao = new DAOModel();
		dao.createTable();
		dao.insertRecords(robjs); // perform inserts
		ResultSet rs;
		rs = dao.retrieveRecords();
		new LoanView().runView(rs);

	}
	
}
