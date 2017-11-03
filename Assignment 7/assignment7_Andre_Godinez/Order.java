package assignment7_Andre_Godinez;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Order {
	private static final int MAXORDERS = 1;
	BlockingQueue<String> queue = new ArrayBlockingQueue<>(MAXORDERS);
	ArrayList<String> orderList;
	
	//lock and condition variables
	private ReentrantLock lock = new ReentrantLock();
	private final Condition queueNotFull = lock.newCondition();
	private final Condition queueNotEmpty = lock.newCondition();
	
	public Order() throws IOException {
		orderList = readInOrderList();
	}
	
	public void prepareOrder(String name) throws IOException, InterruptedException {
		while(true) {
		lock.lock();
		try {
			while(queue.size() == MAXORDERS) {
				queueNotEmpty.await();
			}
			//order to add
			String toPrepare = orderList.get(0);
			boolean isAdded = queue.offer(toPrepare);
			if(isAdded) {
				System.out.println("Chef " + name + " is preparing " + orderList.get(0));
				orderList.remove(0);
				//updateOrderList();
		//signalling server threads that the queue has an order now
			queueNotFull.signalAll();
		}
	} finally {
		lock.unlock();
	}
		}
	}
	
	public void serveOrder(String name) throws InterruptedException {
		while(true) {
		lock.lock();
		try {
			while(queue.size() == 0) {
				//queue is empty so server has to wait
				queueNotFull.await();
			}
			
			String toServe = queue.poll();
			if(toServe!=null) {
				System.out.println("Server " + name + " is serving "+ toServe);
				
				//Signal chef that queue may be empty now
				queueNotEmpty.signalAll();
			}
		} finally {
			lock.unlock();
		}
		}
	}
	

	public static ArrayList readInOrderList() throws IOException {
		ArrayList<String> orderList = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(new File("orderList.txt")));
		String line = null;
		    while((line = reader.readLine()) != null) {
				orderList.add(line);
			}
		    reader.close();
		    return orderList;
		}
	
	public void updateOrderList() throws IOException {
		PrintWriter pw = new PrintWriter(new FileOutputStream("orderList.txt"));
		
		for(String str: orderList) {
			pw.println(str);
		}
		pw.close();
		
	}
	
}




