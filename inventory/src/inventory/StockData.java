package inventory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StockData {
	Item[] data = null;
	
	public Item[] getData(){
		return data;
	}
	public String getProductID(short no){
		return data[no].getProductID();
	}
	public StockData(Connection con){	 		
		try{
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery("select count(product_id) from " + Inventory.dbName + ".product_super");				
			res.next();
			int totalColumns = res.getInt("count(product_id)");
			data = new Item[totalColumns];				
			res = st.executeQuery("SELECT * FROM  " + Inventory.dbName + ".product_super ");	
			int i = 0;
			while (res.next()) {		        	
	            String productID = res.getString("product_id");
	            String productName = res.getString("product_name");
	            float primeCost = res.getFloat("prime_cost");
	            float sellPrice = res.getFloat("sell_price");
	            short stock = res.getShort("stock");
	            String brand = res.getString("brand");
	            String type = res.getString("type");
	            String catagoryName = res.getString("catagory_name");
	            if (brand == null){
	            	brand = "na";
	            }
	            data[i] = new Item(productID, productName, primeCost, sellPrice, stock, brand, type, catagoryName);
	            i++;		            
	          }	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
