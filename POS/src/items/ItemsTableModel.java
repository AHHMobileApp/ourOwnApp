package items;

import gui.resources.Labels;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class ItemsTableModel extends AbstractTableModel {

	private String[] columnNames = {Labels.productName,Labels.productID,Labels.catagory};
	private Object[][] data = null;

	public ItemsTableModel(Object[][] data) {
		this.data = data;
	}

	public void setData(Vector<String[]> cartItems) {
		String[][] data = new String[cartItems.size()][3];
		cartItems.toArray(data);
		this.data = data;
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		try {
			return data.length;
		} catch (Exception e) {
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
	public void setValueAt(Object value, int row, int column) {
		data[row][column] = value;
	}
}
