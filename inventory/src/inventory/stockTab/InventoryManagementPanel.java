package inventory.stockTab;

import inventory.InventoryUI;
import inventory.Item;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class InventoryManagementPanel extends JFrame implements ActionListener {
	private JPanel panel;
	private JButton dropItem;
	private JButton requireItem;
	private JButton updateStock;
	private String productID;
	private int selectedRow;
	private Connection con;

	public InventoryManagementPanel(String productID, int row, Connection con) {
		super("Inventory Management Panel");
		this.con = con;
		panel = new JPanel();
		dropItem = new JButton("Drop Item");
		requireItem = new JButton("Require Item");
		updateStock = new JButton("Update Stock");
		dropItem.addActionListener(this);
		updateStock.addActionListener(this);
		this.productID = productID;
		this.selectedRow = row;
		panel.setLayout(new FlowLayout());
		panel.add(dropItem);
		panel.add(requireItem);
		panel.add(updateStock);
		this.add(panel);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		pack();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == dropItem) {
			Item dropItem = new Item(productID);

			int confirm = JOptionPane.showConfirmDialog(this,
					"Confirm Delete that item?");
			if (confirm == JOptionPane.YES_OPTION) {
				dropItem.Drop(con);
			}
		}
		if (e.getSource() == updateStock) {
			try {
				int qty = Integer.parseInt(JOptionPane
						.showInputDialog("Please enter the new stock"));
				Item item = new Item(productID);
				item.updteStock((short) qty, con);
				InventoryUI.TU.Notify(this.selectedRow,qty);
			} catch (Exception ex) {
				System.out.println("Invaild Input");
				ex.printStackTrace();
			}
		}
	}
}
