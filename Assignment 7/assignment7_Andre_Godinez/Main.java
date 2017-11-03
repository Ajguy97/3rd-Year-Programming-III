package assignment7_Andre_Godinez;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

	public static void main(String args[]) throws ClassNotFoundException, IOException {
		
		final Order order = new Order();
		
		
		Thread mark1 = new Thread(new Chef("Mark",order));
		Thread mark2 = new Thread(new Chef("John",order));
		Thread emily1 = new Thread(new Server("Katie",order));
		Thread emily2 = new Thread(new Server("Andrew",order));
		Thread emily3 = new Thread(new Server("Emily",order));
		
		mark1.start();
		mark2.start();
		emily1.start();
		emily2.start();
		emily3.start();
		
		
	}

	
	
}
