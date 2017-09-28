package assignment3_Andre_Godinez;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankAccount implements Serializable {
	private String name;
	private static int generateAccountNo = 1000;
	private int accountNo;
	private double balance = 0;
	private List<Transaction> transactions = new ArrayList<Transaction>();
	//non-serializable
	private transient double overdraft;
	
	public BankAccount(String name,String date,double amount){
		this.name = name;
		transactions.add(new Transaction(date,"Open Account",amount));
		balance+=amount;
		accountNo = generateAccountNo;
		generateAccountNo++;
		overdraft = 100; //default value?
	}
	
	public String getTransactionDetail() {
		String output = "Transaction details :\n";
		int i = 1;
		for(Transaction t: transactions) {
			output+= i + "."+t.toString()+"\n";
			i++;
		}
		return output;
	}
	
	public void withdraw(String date, double amount) {
		String type = "Withdraw";
		
		if(balance - amount < 0) {
			System.out.println("Unable to withdraw "+ amount +"\nInsufficient funds.\n");	
		}else {
		balance-=amount;
		transactions.add(new Transaction(date,type,amount));
		}
	}
	
	public void deposit(String date, double amount) {
		String type = "Deposit";
		balance+=amount;
		transactions.add(new Transaction(date,type,amount));
	}
	
	public String toString() {
		
		String output = "Account No.:" + accountNo + "\n" +
               "Name: " + name + "\n" +
			   "Balance: " + balance + "\n";
		
		if(overdraft == 0) {
			output += "Overdraft: Not available";
		}
		else {
			output+="Overdraft: "+overdraft;
		}
		
		return output;
	}
	
}
