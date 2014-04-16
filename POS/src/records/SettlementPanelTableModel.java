package records;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class SettlementPanelTableModel extends AbstractTableModel {
	
	private String[] columnNames = {"RecordID","ProductID","Retail Price"};
	private Object[][] data = null;

	public SettlementPanelTableModel(Object[][] data) {
		this.data = data;
	}

	public void setData(Vector<String[]> cartItems) {
		String[][] data = new String[cartItems.size()][2];
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
