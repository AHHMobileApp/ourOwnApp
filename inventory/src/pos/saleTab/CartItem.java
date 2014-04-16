package pos.saleTab;
import inventory.Item;
import java.sql.Connection;

public class CartItem extends Item{
	private int qty;
	
	public CartItem(String productID,Connection con) {
		super(productID,con);
	}
	public CartItem(String productID) {
		super(productID);
		this.qty = 0;
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getQty() {
		return qty;
	}
}
