package inventory;

import inventory.stockTab.TableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Data {
	Item []data;
	
	public Data(){
		Connector conn = new Connector();
		Connection con = conn.con();
		this.getStockData(con);
		System.out.println(this.getStockData(con).length);
		TableModel tableModel = new TableModel(this.getStockData(con));
		JTable table = new JTable(tableModel);
		JFrame frame = new JFrame();
		JScrollPane pane = new JScrollPane(table);
		frame.setSize(640, 480);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(pane);
	}
	
	public Item[] getStockData(Connection con){	 		
		try{
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery("select count(product_id) from pos.product_super");				
			res.next();
			int totalColumns = res.getInt("count(product_id)");
			data = new Item[totalColumns];				
			res = st.executeQuery("SELECT * FROM  pos.product_super ");	
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
		return data;
	}
	public String getProductID(short no){
		return data[no].getProductID();
	}
	public static void main (String args[]){
		Data d = new Data();
	}
}
