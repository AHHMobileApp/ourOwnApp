package pos.recordTab;

import java.util.Date;

public class Order {
	private Date date;
	private String orderID;
	private String productID;
	private String catagory;
	private String productName;
	private short qty;
	private float sellPrice;
	private float total;

	
	public Order(String orderID, String productID, String productName, String catagory, short qty, float sellPrice, float total){
		this.orderID = orderID;
		this.productID = productID;
		this.setProductName(productName);
		this.qty = qty;
		this.sellPrice = sellPrice;
		this.setTotal(total);
		this.catagory = catagory;
		//System.out.println(Integer.parseInt(orderID.substring(2,4)));
		date = new Date();
		date.setYear(Integer.parseInt(orderID.substring(0,2)) + 100);
		date.setMonth(Integer.parseInt(orderID.substring(2,4)) - 1);
		date.setDate(Integer.parseInt(orderID.substring(4,6)) - 1);
		date.setHours(Integer.parseInt(orderID.substring(6,8)));
		date.setMinutes(Integer.parseInt(orderID.substring(8,10)));
	}
	
	public String toString(){
		return orderID + "  " + productID + " ";
	}

	public String getOrderID() {
		return orderID;
	}
	
	public String getProductID() {
		return productID;
	}

	public short getQty() {
		return qty;
	}

	public float getSellPrice() {
		return sellPrice;
	}


	public Date getDate() {
		return date;
	}
	public String getCatagory() {
		return catagory;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductName() {
		return productName;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getTotal() {
		return total;
	}
}
