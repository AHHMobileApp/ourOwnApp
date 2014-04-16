package inventory.inventoryManagement;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import inventory.StockData;
import inventory.Connector;

public class TestTransactionTM {
	public static void main(String args[]){
		Connector con = new Connector();
		DateFormat dateFormat = 
            new SimpleDateFormat("dd/MM/yyyy");
		//TransactionTableModel TTM = new TransactionTableModel(con.con());
		Date date = new Date();
		StockData data = new StockData(con.con());
		
		for (int i = 0; i < 50; i++){
			String orderID;
			orderID = "";
			orderID += "11";
			date.setYear(2011 - 1900);
			short temp = (short)(Math.random()*12 + 1);
			if (temp < 10)
				orderID += "0" + temp;				
			else orderID += temp;
			date.setMonth(temp);
			
			temp = (short)(Math.random()*31 + 1);
			if (temp < 10)
				orderID += "0" + temp;
			else orderID += temp;
			date.setDate(temp);
			
			
			
			temp = (short)(Math.random()*24 + 1);
			if (temp < 10)
				orderID += "0" + temp;
			else orderID += temp;
			
			temp = (short)(Math.random()*60 + 1);
			if (temp < 10)
				orderID += "0" + temp;
			else orderID += temp;
			
			String from,to;
			if ((short)(Math.random()*25  + 1) % 2  == 0){
				from = "Tai Po";
				to = "Ma On Shan";
			} else{
				from = "Ma On Shan";
				to = "Tai Po";
			}
			
			short no = (short)(Math.random() * data.getData().length);
			String productID = data.getProductID(no);
			
			short qty = (short)(Math.random()*5 + 1);
			short nod = (short)(Math.random()*5 + 1);
			try {
				Statement st = con.con().createStatement();
				System.out.println("insert into overview.stock_transaction values('"+  orderID +  "','" + productID + "',"
						+ qty + ",'" + from + "','" + to + "', null ," + nod + ")");
				st.executeUpdate("insert into overview.stock_transaction values('"+  orderID +  "','" + productID + "',"
								+ qty + ",'" + from + "','" + to + "', null ," + nod + ")");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
