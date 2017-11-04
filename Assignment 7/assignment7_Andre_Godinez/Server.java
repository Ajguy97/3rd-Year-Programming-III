package assignment7_Andre_Godinez;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Server extends Thread {
private String name;
private final ArrayBlockingQueue<String> sharedQueue;
private ArrayList<String> servedOrders;


	public Server(String n,ArrayBlockingQueue q) {
		name = n;
		sharedQueue = q;
		servedOrders = new ArrayList<String>();
	}
	
	@Override
	public void run() {
		try {
		
		while(true) {
			//using poll with timeout to ensure the lock is released when FINISHED 
			//is found OR when the timeout expires.
			//returns null after timeout
			String serve = sharedQueue.poll(6, TimeUnit.SECONDS);
			
			if("FINISHED".equalsIgnoreCase(serve) || serve == null) {
				
				System.out.println(printServedOrders());
				
				break;
			}else {
			
			System.out.println("Server " + name + " is serving "+ serve);
			servedOrders.add(serve);
			}
		}
		
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		try {
    		Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	 public String printServedOrders() {
		 String output = "";
		 int burger = 0;
		 int pizza = 0;
		 int fish = 0;
		 
		 for(String s : servedOrders) {
			 
			 if(s.contains("Cheese Burger"))
			 burger++;
			 if(s.contains("Neapolitan Pizza"))
			 pizza++;
			 if(s.contains("Fish n Chips"))
			 fish++;
		 }
		 
		 output+="Server " + name + " finished serving " + servedOrders.size() + " including " +
				 burger+ " burgers, " + pizza + " pizzas and "+ fish + " fish n chips";
		 
		 return output;
	 }
	 
	 
	 
	
}
