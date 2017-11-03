package assignment5_Andre_Godinez;
//Andre Godinez
//15460718
import java.io.Serializable;

public class Item  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String itemName;
	private double price;
	private int quantity;
	
	
	public Item(String name, int q, double p) {
		itemName = name;
		quantity= q;
		price = p;
		
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public int getItemQuantity() {
		return quantity;
	}
	
	public void setItemQuantity(int q) {
		quantity = q;
	}
	
	public double getItemPrice() {
		return price;
	}
	
	public double getItemTotal() {
		return price*quantity;
	}
	
	public String toString() {
		 return String.format( "%-16s %-8d €%.2f", itemName, quantity,price );
	}
}
