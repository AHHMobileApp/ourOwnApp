package inventory.stockTab;


import inventory.Chinese;
import inventory.Item;

import javax.swing.table.AbstractTableModel;



public class TableModel extends AbstractTableModel {
    /**
	 * 
	 */
	private String[] columnNames = {Chinese.productID,
						    		Chinese.productName,						    	
						    		Chinese.catagory,
						    		Chinese.type,
						    		Chinese.brand,
						    		Chinese.salePrice,
						    		Chinese.stock};

    private Object[][] data = null;
    
    public TableModel(Object[][] data){
    	this.data = data;
    }
    
    public TableModel(Item[] item){
    	this.data = new Object[item.length][7];
    	for(int i = 0; i < item.length; i++){
    		this.data[i] = new Object[]{item[i].getProductID(),
    						item[i].getProductName(),item[i].getCatageryName(),
    						item[i].getType(),item[i].getBrand(),item[i].getSellPrice(),
    						item[i].getStock()};
    		//System.out.println(item[i].getProductName());
    	}
    }
    
    public void setData(Object[][] data){
    	this.data = data;
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
	@Override
	public void setValueAt(Object value, int row, int column){
		data[row][column] = value;
	}
}