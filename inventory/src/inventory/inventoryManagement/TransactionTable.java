package inventory.inventoryManagement;

import inventory.Item;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;
import java.sql.Connection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import pos.saleTab.CartItem;

public class TransactionTable extends JPanel {
	private JTable table;
	private TransactionTableModel tableModel;
	public static Connection con;

	public TransactionTable(final Connection con) {
		this.setLayout(new BorderLayout());
		this.con = con;
		tableModel = new TransactionTableModel(con);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane, BorderLayout.CENTER);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String confirm = "";
				if (e.getClickCount() == 2) {
					confirm = JOptionPane.showInputDialog(null,
							"Enter accept or deny", confirm);
					if (confirm.equals("accept")) {
						int row = table.getSelectedRow();
						String productID = (String) tableModel.getValueAt(row,
								1);
						Integer qty = (Integer) tableModel.getValueAt(row, 3);
						String tID = (String) tableModel.getValueAt(row, 4);
						try {
							Statement st = con.createStatement();
							st
									.execute("update overview.stock_transaction set is_accept = 1 where transaction_id = '"
											+ tID + "'");
						} catch (Exception ex) {
							ex.printStackTrace();
						}

						// Item item = new Item(productID,con);
						// item.updteStock((short)(item.getStock() - qty), con);
						for (int i = 0; i < 5; i++)
							tableModel.setValueAt("Done", row, i);
						tableModel.fireTableDataChanged();
					}
				}
			}

		});
	}
}
