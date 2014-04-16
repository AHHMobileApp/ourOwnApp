package pos.saleTab;

import inventory.Chinese;
import inventory.Item;

import javax.swing.table.AbstractTableModel;

public class SaleTableModel extends AbstractTableModel {
    /**
	 * 
	 */
	private String[] columnNames = {Chinese.productID,
						    		Chinese.productName,
						    		Chinese.catagory,
						    		"SellPrice",
						    		"QTY"};
	private int totalCartItems;
    private Object[][] data = null;
    
    public SaleTableModel(Object[][] data){
    	this.data = data;
    }
    
    public SaleTableModel(){
    	totalCartItems = 0;
    	this.data = new Object[12][5];
   
		this.data[totalCartItems] = new Object[]{"","","","",""};
    	
    }
    
    public void setData(Object[][] data){
    	this.data = data;
    }
    
    public void addData(CartItem item){
    	
    	this.data[totalCartItems] = new Object[]{item.getProductID(),
				item.getProductName(),item.getCatageryName(),
				item.getSellPrice(),item.getQty()};
    	totalCartItems += 1;
    }
    public void resetTable(){
    	this.totalCartItems = 0;
    	this.data = null;
    	this.data = new Object[12][5];
    	this.data[totalCartItems] = new Object[]{"","","","",""};
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