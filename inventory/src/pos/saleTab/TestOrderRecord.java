package pos.saleTab;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import inventory.Connector;
import inventory.StockData;

public class TestOrderRecord {
	/*static Connection con = null;
	public static void main(String args[]){
		connector conn = new connector();
		ResultSet res = null;
		try{
			Connection con = conn.con();
			OrderRecord record = new OrderRecord();
			StockData data = new StockData(con);
			for (int i = 0; i < 250; i++){
				String orderID;
				orderID = "";
				orderID += "11";
		
				short temp = (short)(Math.random()*12 + 1);
				if (temp < 10)
					orderID += "0" + temp;
				else orderID += temp;
				
				temp = (short)(Math.random()*31 + 1);
				if (temp < 10)
					orderID += "0" + temp;
				else orderID += temp;
				
				temp = (short)(Math.random()*24 + 1);
				if (temp < 10)
					orderID += "0" + temp;
				else orderID += temp;
				
				temp = (short)(Math.random()*60 + 1);
				if (temp < 10)
					orderID += "0" + temp;
				else orderID += temp;
				temp = (short)(Math.random()*60 + 1);
				if (temp < 10)
					orderID += "0" + temp;
				else orderID += temp;
				short no = (short)(Math.random() * data.getData().length);
				//System.out.println("select sell_price from pos.product_super where product_id = '" + data.getProductID(no)+ "'");
				Statement st = con.createStatement();
				//res = st.executeQuery("select sell_price,prime_cost from pos.product_super where product_id = '" + data.getProductID(no)+ "'");
				//res.next();
				String pID = data.getProductID(no);
				cartItem item = new cartItem(pID,con);
				//System.out.println(sellPrice);
				short qty = (short)(Math.random() * 5 + 1);
				item.setQty(qty);
				
				record.insertOrderMaster(con, orderID, item.getSellPrice());
				record.insertOrderDetial(con, orderID, item);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
}
