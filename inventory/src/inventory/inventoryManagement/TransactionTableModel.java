package inventory.inventoryManagement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.AbstractTableModel;

public class TransactionTableModel extends AbstractTableModel {
	private Connection con;
	private String[] columnNames = { "From", "ProductID", "ProductName", "QTY",
			"TransactionID" };
	private Object[][] data = null;

	public TransactionTableModel(Connection con) {
		this.con = con;
		ResultSet rs;
		try {
			Statement st = con.createStatement();
			rs = st
					.executeQuery("select count(transaction_id) from overview.stock_transaction where is_accept = -1");
			rs.next();
			int totalUnTreadedItems = rs.getInt("count(transaction_id)");
			if (totalUnTreadedItems != 0) {
				data = new Object[totalUnTreadedItems][5];
				int i = 0;
				rs = st
						.executeQuery("select a.to, a.product_id, a.qty, a.is_accept, b.product_name, a.transaction_id from overview.product_super b, overview.stock_transaction a where a.product_id = b.product_id and a.to != 'Tai Po'");
				while (rs.next()) {
					if (rs.getInt("is_accept") == -1) {
						data[i][0] = rs.getString("a.to");
						data[i][1] = rs.getString("a.product_id");
						data[i][2] = rs.getString("b.product_name");
						data[i][3] = rs.getInt("a.qty");
						data[i][4] = rs.getString("a.transaction_id");
						i++;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setData(Object[][] data) {
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
