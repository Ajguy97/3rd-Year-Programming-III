package assignment7_Andre_Godinez;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class Chef extends Thread{
private String name;
private Order order;

public Chef(String n,Order o) throws IOException, ClassNotFoundException {
	name = n;
	order = o;
}	

@Override
public void run() {
	try {
		order.prepareOrder(name);
	}catch(InterruptedException e){
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}




}