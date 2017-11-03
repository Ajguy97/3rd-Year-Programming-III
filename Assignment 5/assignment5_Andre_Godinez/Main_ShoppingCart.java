package assignment5_Andre_Godinez;
//Andre Godinez
//15460718
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main_ShoppingCart {
	

	public static void main(String args[]) throws FileNotFoundException, IOException, ClassNotFoundException {
	//deserializing inventoryList and printing it
	FileInputStream fis = new FileInputStream("InventoryList.txt");
	ObjectInputStream ois = new ObjectInputStream(fis);	
	ArrayList<Inventory> inventoryList = (ArrayList<Inventory>) ois.readObject();
	Main_Inventory.printInventory(inventoryList);
	
	//creating shopping cart and adding items to it then printing it
	ShoppingCart cart1 = new ShoppingCart("Mark 1","13/10/2017");
	cart1.addItem("Apple", 2);
	cart1.addItem("Orange", 5);
	cart1.addItem("Milk", 2);
	cart1.addItem("Blue Cheese", 4);
	cart1.addItem("Candy", 25);
	cart1.removeItem("Candy", 5);
	
	ShoppingCart cart2 = new ShoppingCart("Mark","13/10/2017");
	cart2.addItem("Apple", 2);
	cart2.addItem("Orange", 5);
	cart2.addItem("Milk", 2);
	cart2.addItem("Blue Cheese", 4);
	cart2.addItem("Cheddar", 3);
	cart2.addItem("Beef", 6);
	cart2.addItem("Candy", 20);
	cart2.addItem("Chocolate", 10);
	cart2.addItem("Chicken", 2);
	cart2.removeItem("Blue Cheese", 1);
	cart2.removeItem("Chocolate", 5);
	
	System.out.println(cart1.viewCart()+"\n");
	System.out.println(cart2.viewCart()+"\n");
	
	
	//deserializing the new inventoryList that was serialized by addItem and removeItem method
	//then printing it
	FileInputStream fis1 = new FileInputStream("InventoryList.txt");
	ObjectInputStream ois1 = new ObjectInputStream(fis1);	
	inventoryList = (ArrayList<Inventory>) ois1.readObject();
	System.out.println("INVENTORY LIST AFTER ADDING AND REMOVING ITEMS\n");
	Main_Inventory.printInventory(inventoryList);
	
	    
	System.out.println("SHUFFLING CART 2 AND SORTING CART 2  IN ORDER OF SUBTOTALS\n\n");
	Collections.shuffle(cart2.getItems());
	System.out.println(cart2.viewCart()+"\n");
   //sorting shoppingcart itemList in ascending order - totalPrice
    Collections.sort(cart2.getItems(), Comparator.comparing(Item::getItemTotal));
    System.out.println(cart2.viewCart()+"\n");
   
   
    ois.close();
    ois1.close();
    fis.close();
    fis1.close();
	}//end main
}//end main_shoppingcart class

	

	