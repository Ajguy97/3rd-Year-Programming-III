package assignment5_Andre_Godinez;
//Andre Godinez
//15460718
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main_Inventory {
	
	
	public static void main(String[] args) throws IOException {
		String directory = System.getProperty("user.dir"); 
		
		ArrayList<Inventory> InventoryList = new ArrayList<Inventory>();
		
		InventoryList.add(new Inventory("1000", "Apple", 30, 2.50, 1.25));
		InventoryList.add(new Inventory("1001", "Orange", 40, 2, 1.00));
		InventoryList.add(new Inventory("2001", "Milk", 10, 2.39, 1.50));
		InventoryList.add(new Inventory("2002", "Orange Juice", 20, 1.99, 1.25));
		InventoryList.add(new Inventory("3001", "Blue Cheese", 10, 2.25, 1.50));
		InventoryList.add(new Inventory("3002", "Cheddar", 20, 2.79, 1.60));
		InventoryList.add(new Inventory("4001", "Chocolate", 40, 2.99, 1.70));
	    InventoryList.add(new Inventory("4002", "Candy", 30, 0.99, 0.50));
	    InventoryList.add(new Inventory("5001", "Beef", 10, 5.00, 3.00));
	    InventoryList.add(new Inventory("5002", "Chicken", 10, 4.00, 2.00));
	    
	    Collections.sort(InventoryList, Comparator.comparing(Inventory::getItemName));
	 
	    printInventory(InventoryList);
	    
	    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("InventoryList.txt"));
		System.out.println("Serializing InventoryList " + directory);
		oos.writeObject(InventoryList);
		oos.close();
		
	    
	}

	public static void printInventory(List<Inventory> inventory) {
		System.out.println("SKU     ItemName         Quantity Price\n"
				 + "---------------------------------------");
		for(Inventory i: inventory) {
		System.out.println(i.toString());
		}
		System.out.println("---------------------------------------\n");
		
	}//end print inventory
}

