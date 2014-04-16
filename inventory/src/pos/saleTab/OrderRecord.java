package pos.saleTab;

import inventory.Inventory;

import java.sql.*;
import java.util.Date;
import java.util.Vector;

public class OrderRecord {

	public String orderIDGenerator(){
		Date calendar = new Date();
		String temp = "";
		
		temp += calendar.getYear() - 100 + "";

		
		if (calendar.getMonth() + 1 < 10)
			temp += "0" + (calendar.getMonth() + 1);
		else
			temp += (calendar.getMonth() + 1);

		if (calendar.getDate() < 10)
			temp += "0" + calendar.getDate();
		else
			temp += calendar.getDate()+ "";

		if (calendar.getHours() < 10)
			temp += "0" + (calendar.getHours());
		else
			temp += calendar.getHours();

		if (calendar.getMinutes() < 10)
			temp += "0" + calendar.getMinutes();
		else
			temp += calendar.getMinutes();
		
		if (calendar.getSeconds() < 10)
			temp += "0" + calendar.getSeconds();
		else
			temp += calendar.getSeconds();

		return temp;
	}

	public boolean insertOrderMaster(Connection con, String orderID, float subTotal){	
		try {
			Statement st = con.createStatement();
			String sql = "insert into " + Inventory.dbName + ".order_master values('" + orderID + "',curdate()," + subTotal + ")";			
			return !st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
	}
	public void insertOrderDetial(Connection con, String orderID, CartItem item){
		try {
			Statement st = con.createStatement();
			//String orderID = this.orderIDGenerator();
			//String sql = "insert into " + Inventory.dbName + ".order_master values('" + orderID + "',curdate()," + (item.getQty() * item.getSellPrice()) + ")";
			//boolean executed = st.execute(sql);
			//if (executed = true){
			String sql = "insert into " + Inventory.dbName + ".order_detail values('"+ orderID +"','"+ item.getProductID() 
					+"'," + item.getSellPrice() + ","+item.getQty()+"," + 
					(item.getQty() * item.getSellPrice()) + "," + 
					(item.getQty() * (item.getSellPrice() - item.getPrimeCost())) +",'')";
			st.execute(sql);
			//}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
