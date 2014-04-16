package inventory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Item {

	private String productID;
	private String productName;
	private short stock;
	private float primeCost;
	private float sellPrice;
	private String remark;
	private String type;
	private String brand;
	private String catagoryName;
	private boolean productValid;
	Connection con;

	public Item(String productID, Connection con) {
		this.productID = productID;
		try {
			Statement st = con.createStatement();
			ResultSet res = st
					.executeQuery("SELECT brand,type,product_name,catagory_name,prime_cost,sell_price,stock  FROM  "
							+ Inventory.dbName
							+ ".product_super where product_ID = "
							+ this.productID);
			res.next();
			if (res.getString("product_name") == null)
				this.setProductValid(false);
			else {
				this.setProductValid(true);
				this.setProductName(res.getString("product_name"));
				this.setBrand(res.getString("brand"));
				this.setCatageryName(res.getString("catagory_name"));
				this.setSellPrice(res.getFloat("sell_price"));
				this.setStock(res.getShort("stock"));
				this.setType(res.getString("type"));
				this.setPrimeCost(res.getShort("prime_cost"));
				if (this.getBrand() == null) {
					this.setBrand("");
				}
			}
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
	}

	public Item(String productID, String productName, float primeCost,
			float sellPrice, short stock, String brand, String type,
			String catagoryName) {
		this.productID = productID;
		this.productName = productName;
		this.primeCost = primeCost;
		this.sellPrice = sellPrice;
		this.stock = stock;
		this.brand = brand;
		this.type = type;
		this.catagoryName = catagoryName;
	}

	public Item(String productID) { // constructor for cartItem initialization
		this.productID = productID;
		this.productName = "";
		this.sellPrice = 0;
		this.catagoryName = "";
	}

	public boolean sellItem(short no) {
		if (stock == -1) {
			return true;
		} else if (stock < no) {
			System.out.println("out of stock");
			return false;
		} else {
			stock = (short) (stock - no);
			return true;
		}
	}

	public void updteStock(short newStock, Connection con) {
		try {
			Statement st = con.createStatement();
			st.execute("update " + Inventory.dbName
					+ ".product_super set stock = " + newStock + " where product_id = '" + 
					this.productID + "'");
			System.out.println("Done");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void Add(Connection con) {
		if (this.getProductValid() == false) {
			try {
				Statement st = con.createStatement();
				st.execute("insert into " + Inventory.dbName
						+ ".product_super values('" + this.getProductID()
						+ "','" + this.getProductName() + "','',"
						+ this.getPrimeCost() + "," + this.getSellPrice() + ","
						+ this.getStock() + ",'" + this.getBrand() + "','"
						+ this.getType() + "','" + this.getCatageryName()
						+ "')");
				System.out.println("Done");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void Drop(Connection con) {
		if (this.getProductValid() == false) {
			try {
				Statement st = con.createStatement();
				st.execute("delete from " + Inventory.dbName
						+ ".product_super where product_ID = '"
						+ this.productID + "'");
				System.out.println("Done");
				System.out.println("delete from " + Inventory.dbName
						+ ".product_super where product_ID = '"
						+ this.productID + "'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void checkVaild(Connection con) {
		try {
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery("select product_ID from "
					+ Inventory.dbName + ".product_super where product_ID = '"
					+ this.getProductID() + "'");
			if (res.next() == false)
				this.setProductValid(false);
			else
				this.setProductValid(true);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String toString() {
		if (productValid == true)
			return "product Name: " + productName + " Type: " + type
					+ " brand :" + brand + " stock:" + stock;
		else
			return "product NOT FOUND";
	}

	public void setProductValid(boolean isValid) {
		this.productValid = isValid;
	}

	public boolean getProductValid() {
		return productValid;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public short getStock() {
		return stock;
	}

	public void setStock(short stock) {
		this.stock = stock;
	}

	public float getPrimeCost() {
		return primeCost;
	}

	public void setPrimeCost(float primeCost) {
		this.primeCost = primeCost;
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setCatageryName(String catageryName) {
		this.catagoryName = catageryName;
	}

	public String getCatageryName() {
		return catagoryName;
	}
}
