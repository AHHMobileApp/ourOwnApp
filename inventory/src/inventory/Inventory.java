package inventory;

import java.sql.Connection;
import java.sql.SQLException;

public class Inventory {
	
	public static Connection con = null;
	public static final String location = "Tai Po";
	public static String shopID = "1";
	public static String dbName = "pos";
	
	public static void main(String arg[]) throws SQLException{
		Connector conn = new Connector();
		//item item = new item("444801", "prName", (float)3.2, (float) 2.1, (short)4, "brand", "type", "ame");
		try{			
			con = conn.con();
			StockData stockData = new StockData(con);
			InventoryUI inventory = new InventoryUI(con,stockData);
			}catch(Exception e){
			e.printStackTrace();
		}finally{
			//conn.conClose();
		}
	}
}
