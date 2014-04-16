package pos.recordTab;

import inventory.Inventory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Record {
	Connection con;
	Order[] orders;
	private int ordersSize;
	
	public Record(Connection con){
		this.con = con;
		try {
			Statement st = con.createStatement();
			Statement st2 = con.createStatement();
			ResultSet res = st.executeQuery("select count(order_id) from " + Inventory.dbName + ".order_detail");
			res.next();
			int ordersSize = res.getInt("count(order_id)");
			orders = new Order[ordersSize];
			int i = 0;
			res = st.executeQuery("select order_ID, product_ID, qty, sell_Price, sub_total from " + Inventory.dbName + ".order_detail order by substr(order_id,3,2) ,substr(order_id,1,2)");
			ResultSet resProductSuper = null;
			while(res.next()){
				String orderID = res.getString("order_ID");
				String productID = res.getString("product_ID");
				short qty = res.getShort("qty");
				float sellPrice = res.getFloat("sell_Price");
				float subTotal = res.getFloat("sub_total");
				//System.out.println("select prime_cost from " + Inventory.dbName + ".product_super where product_ID = '" + productID + "'");
				resProductSuper = st2.executeQuery("select product_name, prime_cost, catagory_name from " + Inventory.dbName + ".product_super where product_ID = '" + productID + "'");
				resProductSuper.next();
				String productName = resProductSuper.getString("product_name");
				
				String catagory = resProductSuper.getString("catagory_name");
				Order temp = new Order(orderID, productID, productName, catagory, qty, sellPrice, subTotal);
				orders[i] = temp;
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Order[] getOrders(){
		return orders;
	}
	public int getTotalRows(){
		return ordersSize;
	}

	
}
