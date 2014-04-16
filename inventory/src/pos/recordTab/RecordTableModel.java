package pos.recordTab;


import javax.swing.table.AbstractTableModel;

import pos.saleTab.CartItem;

public class RecordTableModel extends AbstractTableModel{
	private String[] columnNames = {"Date",
						    		"OrderID",
						    		"ProductCatagory",
						    		"ProductName",						    		
						    		"SellPrice",
						    		"QTY",
						    		"Total"};
	private Object[][] data = null;
	
	public RecordTableModel(Object[][] data){
		this.data = data;
	}
	
    public RecordTableModel(Order[] order){
    	this.data = new Object[order.length][7];
    	for(int i = 0; i < order.length; i++){
    		this.data[i] = new Object[]{order[i].getDate(),
    				order[i].getOrderID(),order[i].getCatagory(),
    				order[i].getProductName(),order[i].getSellPrice(),
    				order[i].getQty(),order[i].getTotal()};
    		//System.out.println(item[i].getProductName());
    	}
    }
	
	public void setData(Order[] order){
		this.data = null;
		this.data = new Object[order.length][7];
    	for(int i = 0; i < order.length; i++){
    		this.data[i] = new Object[]{order[i].getDate(),
    				order[i].getOrderID(),order[i].getCatagory(),
    				order[i].getProductName(),order[i].getSellPrice(),
    				order[i].getQty(),order[i].getTotal()};
    		//System.out.println(item[i].getProductName());
    	}
	}
	
	
	
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public int getRowCount() {
		try{
			return data.length;
		}catch(Exception e){
			return 0;
		}
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}
	
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();	
	}
}
