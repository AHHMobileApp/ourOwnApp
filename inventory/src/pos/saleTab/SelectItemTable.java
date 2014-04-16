package pos.saleTab;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import inventory.StockData;
import inventory.stockTab.StockTab;

public class SelectItemTable extends StockTab implements ListSelectionListener {

	public SelectItemTable(final Connection con, StockData stockData, final SaleTableModel saleTableModel) {
		super(con, stockData);
		// TODO Auto-generated constructor stub		
		stockTable.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
		      if (e.getClickCount() == 2){
		    	  String inputSellPrice = "";
		    	  String inputQty;
		    	  int row = stockTable.getSelectedRow();		   
		    	  inputSellPrice =  (Float) stockTable.getValueAt(row, 5) + "";
		    	  inputQty = JOptionPane.showInputDialog(null,
		    			  "QTY",
		    			  "Enter QTY",
		    			  JOptionPane.QUESTION_MESSAGE);
		    	  if(inputQty != null){
			    	  inputSellPrice = JOptionPane.showInputDialog(null,
			    			  "Enter sellPrice",
			    			  inputSellPrice);
		    	  }
		    	  int qty = 0;
		    	  float sellPrice = 0;
		    	  if(inputQty != null && inputSellPrice != null){
			    	  try {
			    		  qty = Integer.parseInt(inputQty);
			    		  sellPrice = Float.parseFloat(inputSellPrice);			    		  
				    	  String productID = (String) stockTable.getValueAt(row, 0);
				    	  CartItem item = new CartItem(productID, con);
				    	  item.setQty(qty);
				    	  item.setSellPrice(sellPrice);
				    	  saleTableModel.addData(item);
				    	  saleTableModel.fireTableChanged(null);
			    	  } catch(Exception ex){
			    		  JOptionPane.showMessageDialog(null,"invaild input");
			    	  }
		    	  }
		    	  
		      }  
		      }
		});
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
