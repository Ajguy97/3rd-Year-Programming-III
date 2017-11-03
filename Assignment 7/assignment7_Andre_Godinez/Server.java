package assignment7_Andre_Godinez;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Server extends Thread {
private String name;
private Order order;


	public Server(String n,Order o) {
		name = n;
		order = o;
	}
	
	@Override
	public void run() {
	try {
		order.serveOrder(name);
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
	
	public String getServerName() {
		return name;
	}
	
	
	
}
