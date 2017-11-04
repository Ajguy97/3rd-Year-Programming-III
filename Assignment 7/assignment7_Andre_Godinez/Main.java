package assignment7_Andre_Godinez;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class Main {

	public static void main(String args[]) throws ClassNotFoundException, IOException, InterruptedException {
		
		ArrayBlockingQueue<String> q = new ArrayBlockingQueue<String>(20);
		List<String> orderList = Collections.synchronizedList(new ArrayList<String>());
		BufferedReader reader = new BufferedReader(new FileReader(new File("orderList.txt")));
		String line = null;
	    while((line = reader.readLine()) != null) {
			orderList.add(line);
		}
	    reader.close();
		
		
	    
		Thread chef1 = new Thread(new Chef("Mark",q,orderList));
		Thread chef2 = new Thread(new Chef("John",q,orderList));
		Thread server1 = new Thread(new Server("Katie",q));
		Thread server2 = new Thread(new Server("Andrew",q));
		Thread server3 = new Thread(new Server("Emily",q));
		
		chef1.start();
		//this makes sure the 2 chefs dont make the first order at the same time
		//i dont know why it does this SOMETIMES
		chef1.join(50);
		chef2.start();
		server1.start();
		server2.start();
		server3.start();
		
		
	}
	 

}
