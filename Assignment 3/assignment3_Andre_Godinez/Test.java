package assignment3_Andre_Godinez;


import java.io.*;



public class Test {

	public static void main(String args[]) throws IOException, ClassNotFoundException {
		String directory = System.getProperty("user.dir"); 
		
		System.out.println("Question 1 :\n\n");
		Transaction[] transactions = new Transaction[3];
		
		transactions[0] = new Transaction("16/08/2017","Open Account",100);
		transactions[1] = new Transaction("22/08/2017","Withdraw",100);
		transactions[2] = new Transaction("23/09/2017","Deposit",100);
		
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("transactions.txt"));
		
		System.out.println("Serializing array of objects to " +directory);
		oos.writeObject(transactions);
		
	
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("transactions.txt"));
		System.out.println("Reading array of objects from " + directory);
		Transaction[] transactionsCopy = (Transaction[]) ois.readObject();
		
		for(int i=0;i<transactionsCopy.length;i++) {
			System.out.println(transactionsCopy[i].toString());
		}
		
		System.out.println("\n\nQuestion 2 :\n\n ");
		
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
		
		
		ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream("accountDetails.txt"));
		
		System.out.println("Serializing Bank Account object to " + directory + "\n");
		oos.writeObject(ba);
		
		
		
		
		System.out.println("Retrieving serialized Bank Account object from " + directory + "\n");
		
		ObjectInputStream ois1 = new ObjectInputStream(new FileInputStream("accountDetails.txt"));
		
		BankAccount baCopy = (BankAccount) ois.readObject();
		
		System.out.println(baCopy.toString()+"\n");
		System.out.println(baCopy.getTransactionDetail());
		
		System.out.println("\n\nQuestion 3 :\n\n");
		 // create a new RandomAccessFile with filename test
        RandomAccessFile file = new RandomAccessFile(directory +"/question3.txt", "rw");
       
        file.seek(file.length());
        file.writeChars("Yes");
        
        
        file.seek(0);
        String line;
        if((line = file.readLine()) != null) {
          System.out.println(line);
        }
        
        oos.close();
		oos1.close();
		ois.close();
		ois1.close();
        file.close();
       
	}
	
}
