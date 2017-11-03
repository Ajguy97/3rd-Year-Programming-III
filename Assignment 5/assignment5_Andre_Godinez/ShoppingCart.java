package assignment5_Andre_Godinez;
//Andre Godinez
//15460718
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShoppingCart {
	private String customerName;
	private String date;
	
	private List<Item> shoppingCart = new ArrayList<Item>();
	private List<Inventory> inventoryList;
	
	public ShoppingCart(String cn, String d) throws FileNotFoundException, IOException, ClassNotFoundException {
		customerName = cn;
		date = d;
		//deserializing inventoryList from directory
		//storing it into inventoryList variable
		FileInputStream fis = new FileInputStream("InventoryList.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		inventoryList = (ArrayList<Inventory>) ois.readObject();
		ois.close();
		fis.close();
	}//end constructor
	
		//need this getter method to get list of items in shoppingcart
		//and sort them with custom comparator class
		public List<Item> getItems() {
			return shoppingCart;
		}//end get Item method
		
	public void addItem(String name, int quantity) throws FileNotFoundException, IOException, ClassNotFoundException{
		int itemQuantity; //init variable to hold quantity - we don't know quantity yet since
						  //we have to figure out if we have available itemquantity in inventory
		Item itemQuery = binarySearchInventory(name);	
		
			if(itemQuery == null) {
				System.out.println("Item not found.");
			}
			else {
				//item is found
				//quantity is 0 item not available
				if(itemQuery.getItemQuantity() == 0) {
					System.out.println(itemQuery.getItemName()+ " not available.");
				}
				//not enough stock
				else if(itemQuery.getItemQuantity()-quantity < 0) {
					System.out.println(
							"Not enough item stock for "+ itemQuery.getItemName()+
						    "\nStock available: "+itemQuery.getItemQuantity()+"\n");
					itemQuantity = itemQuery.getItemQuantity();//set quantity to available quantity
					shoppingCart.add(new Item(name,itemQuantity,itemQuery.getItemPrice()));
					itemQuery.setItemQuantity(0); //itemQuantity in inventoryList set to 0
				}
				//stock is enough
				else {
					itemQuantity = quantity;//set quantity to quantity passed in
					//subtract itemQuantity in inventoryList with quantity passed in
					itemQuery.setItemQuantity(itemQuery.getItemQuantity()-quantity);
					 //add new Item in shoppingCart with the quantity that was calculated
					shoppingCart.add(new Item(name,itemQuantity,itemQuery.getItemPrice()));
				}
			 //now we serialize the modified inventoryList with item quantity changed.
			 FileOutputStream fos = new FileOutputStream("InventoryList.txt");
			 ObjectOutputStream oos = new ObjectOutputStream(fos);
			 oos.writeObject(inventoryList);
			 oos.close();
			 fos.close();
		 }
	}//end add item method
	
	public void removeItem(String name, int quantity) throws FileNotFoundException, IOException, ClassNotFoundException {
		//enhanced for loop to loop through shoppingCart arrayList
		for(Item i : shoppingCart) {
			if(i.getItemName() == name){ //if name is found
				
				if((i.getItemQuantity() - quantity) == 0) { 	
					shoppingCart.remove(i);
				}
				else { //else just remove quantity from item eg. 5 Oranges €5.00 -> 2 Oranges €2.00
					i.setItemQuantity(i.getItemQuantity() - quantity);
				}
				//update item in inventory list
				//since we're removing from shopping cart
				//we add to the inventoryList
				Item itemQuery = binarySearchInventory(name);	
				itemQuery.setItemQuantity(itemQuery.getItemQuantity()+quantity);
				 FileOutputStream fos = new FileOutputStream("InventoryList.txt");
				 ObjectOutputStream oos = new ObjectOutputStream(fos);
				 oos.writeObject(inventoryList);
				 oos.close();
				 fos.close();
				 
				 //breaking here so dont get concurrentmodification error when removing item from cart
				 break;
			}
		
		}
		
	}//end remove item
	
	
	//Searches inventory given a String name of Item
	public Item binarySearchInventory(String n) {
		
		int index = Collections.binarySearch(inventoryList,
                new Inventory(null,n,0,0,0), Comparator.comparing(Inventory::getItemName));
		
		return inventoryList.get(index).getItem();
	}//end search method
			
	public String viewCart() {
		double total = 0; //variable to show total at the end of cart
		String output=date+ "      Name: "+customerName+"\nQuantity   Item Name        Subtotal\n";
		
		for(Item i : shoppingCart) {
			output+=String.format( "%-10d %-16s €%.2f\n\n", i.getItemQuantity(), i.getItemName() , i.getItemTotal() );
			total+=i.getItemPrice()*i.getItemQuantity();
		}
		
		output+=String.format("                   Total: €%.2f", total);
		
		return output;
	}//end viewCart method
	
}//end shopping cart class



