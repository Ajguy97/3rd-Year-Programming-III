package assignment3_Andre_Godinez;


import  java.io.Serializable;


public class Transaction implements Serializable {
	
private static int generateTransactionNo = 1000;
private int transactionNo;
private String date;
private String type;
private double amount;

public Transaction(String date,String type, double amount) {
	this.date = date;
	this.type = type;
	this.amount = amount;
	
	transactionNo = generateTransactionNo;
	generateTransactionNo++;
}



public String toString() {
	return date+" "+type+" "+amount;
}

}
