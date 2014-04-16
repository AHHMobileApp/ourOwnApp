package inventory.stockTab;

import inventory.StockData;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class InventoryManagementTab extends StockTab{
	public InventoryManagementTab(final Connection con, StockData stockData){
		super(con,stockData);
		stockTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int row = stockTable.getSelectedRow();
					String productID =  (String) stockTable.getValueAt(row, 0);
					System.out.println(productID);
					InventoryManagementPanel panel = new InventoryManagementPanel(productID,row,con);
				}
			}
		});
	}
}
