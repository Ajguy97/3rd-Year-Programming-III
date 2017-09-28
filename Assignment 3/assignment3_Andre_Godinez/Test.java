package assignment3_Andre_Godinez;


import java.io.*;



public class Test {

	public static void main(String args[]) throws IOException, ClassNotFoundException {
		String directory = System.getProperty("user.dir"); 
		
//		Transaction[] transactions = new Transaction[3];
//		
//		transactions[0] = new Transaction("16/08/2017","Open Account",100);
//		transactions[1] = new Transaction("22/08/2017","Withdraw",100);
//		transactions[2] = new Transaction("23/09/2017","Deposit",100);
//		
//		FileOutputStream fos = new FileOutputStream("transactions.txt");
//		ObjectOutputStream oos = new ObjectOutputStream(fos);
//		
//			System.out.println("Serializing array of objects to " +directory);
//			oos.writeObject(transactions);
//		
//		
//		oos.close();
//		
//		FileInputStream fis = new FileInputStream("transactions.txt");
//		ObjectInputStream ois = new ObjectInputStream(fis);
//		
//		Transaction[] transactionsCopy = (Transaction[]) ois.readObject();
//		
//		for(int i=0;i<transactionsCopy.length;i++) {
//			System.out.println(transactionsCopy[i].toString());
//		}
		System.out.println("Opening Bank account...\n");
		BankAccount ba = new BankAccount("Jenny Lee","16/08/2017",100);
		System.out.println(ba.toString()+"\n");
		
		System.out.println("Withdrawing 200...\n");
		ba.withdraw("22/08/2017", 200);
		System.out.println("Depositing 100...\n");
		ba.deposit("23/08/2017", 100);
		System.out.println("Withdrawing 50...\n");
		ba.withdraw("01/09/2017", 50);
		
		
		System.out.println(ba.getTransactionDetail());
		
		FileOutputStream fos = new FileOutputStream("accountDetails.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		System.out.println("Serializing Bank Account object to " + directory + "\n");
		oos.writeObject(ba);
		
		oos.close();
		
		System.out.println("Retrieving serialized Bank Account object from " + directory + "\n");
		FileInputStream fis = new FileInputStream("accountDetails.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		BankAccount baCopy = (BankAccount) ois.readObject();
		
		System.out.println(baCopy.toString()+"\n");
		System.out.println(baCopy.getTransactionDetail());
	}
	
}
