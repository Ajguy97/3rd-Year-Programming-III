package assignment7_Andre_Godinez;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Chef extends Thread{
	private String name;
	private final ArrayBlockingQueue<String> sharedQueue;
	private List<String> orderList;
	private ArrayList<String> preparedOrders;
	
	
	public Chef(String n,ArrayBlockingQueue q, List<String> oL) throws IOException{
		name = n;
		sharedQueue = q;
		orderList = oL;
		preparedOrders = new ArrayList<String>();
	}	
	
	 @Override
	    public void run() {
			 try {
				 
			 while(true) {
		               	//if orderlist size is 0 then no more orders to to prepare
				 		//break out of while loop and print out prepared orders
		            	if(orderList.size() == 0) {
		            		System.out.println(printPreparedOrders());
			            	break;
			            	
		            	}
		            	else {
		            	//else we just keep getting the first order from the orderList
		            	String toPrepare = orderList.get(0);
		            	
		            	sharedQueue.put(toPrepare);
		            	preparedOrders.add(toPrepare);
		            	
		            	System.out.println("Chef " + name + " is preparing " + orderList.get(0));
		            	orderList.remove(0);
		            	}
		            	
		            	try {
		            		Thread.sleep(1000);
			    		} catch (InterruptedException e) {
			    			// TODO Auto-generated catch block
			    			e.printStackTrace();
			    		}
		            	
		            }
			 
			 } catch (Exception err) {
		                err.printStackTrace();
	            }
	            
			 
			 //when we reach this code the while loop is finished
			 try {
				
				sharedQueue.put("FINISHED");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           
		 
	 }
	 
	 
	 
	 public String printPreparedOrders() {
		 String output = "";
		 int burger = 0;
		 int pizza = 0;
		 int fish = 0;
		 
		 for(String s : preparedOrders) {
			 
			 if(s.contains("Cheese Burger"))
			 burger++;
			 if(s.contains("Neapolitan Pizza"))
			 pizza++;
			 if(s.contains("Fish n Chips"))
			 fish++;
		 }
		 
		 output+="Chef " + name + " finished prepared " + preparedOrders.size() + " including " +
				 burger+ " burgers, " + pizza + " pizzas and "+ fish + " fish n chips";
		 
		 return output;
	 }
	 
	 
	 
	 
    }

	
