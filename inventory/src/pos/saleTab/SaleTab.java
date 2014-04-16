package pos.saleTab;

import inventory.InventoryUI;
import inventory.StockData;
import inventory.stockTab.StockTab;

import java.awt.GridLayout;
import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class SaleTab extends JPanel{
	
	Connection con;
	private SaleTableModel saleTableModel;
	public SaleTab(Connection con, StockData data){
		saleTableModel = new SaleTableModel();
		JTabbedPane saleTab = new JTabbedPane();
		SelectItemTable stockTab = new SelectItemTable(con,data,saleTableModel);
		stockTab.setStockTabSize(640, 200);
		InventoryUI.TU.attach(stockTab);
		saleTab.addTab("sortTable", stockTab);
		saleTab.addTab("SearchBYID", new SearchByID(con, saleTableModel));
		
		//OrderRecord orderRecord = new OrderRecord(con);
		this.setLayout(new GridLayout(2,1));
		this.add(saleTab);
		this.add(new CartPanel(saleTableModel,con));
	}
}
