package assignment5_Andre_Godinez;
//Andre Godinez
//15460718
import java.io.Serializable;

public class Inventory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//stock keeping unit
	private String sku;
	private double cost;
	private Item item;
	
	public Inventory(String stockKeepingUnit ,String itemName,int quantity,double price,double c) {
		sku = stockKeepingUnit;
		cost = c;
		item = new Item(itemName,quantity,price);
		
	}
	
	public String toString() {
		return sku+"\t"+item.toString();
	}
	
	public Item getItem() {
		return item;
	}
	
	public String getItemName() {
		return item.getItemName();
	}
	
}
