package inventory;

import inventory.stockTab.InventoryManagementTab;
import pos.recordTab.RecordTab;
import inventory.inventoryManagement.TransactionTable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import pos.saleTab.SaleTab;

public class InventoryUI extends JPanel {
	/**
	 * @param args
	 * @throws SQLException
	 */
	private SaleTab saleTab;
	private InventoryManagementTab inventoryManagementTab;
	public static TableUpdater TU;

	public InventoryUI(Connection con, StockData stockData) {
		super(new BorderLayout());
		TU = new TableUpdater();
		saleTab = new SaleTab(con, stockData);
		inventoryManagementTab = new InventoryManagementTab(con, stockData);
		JTabbedPane tabbedPane = new JTabbedPane();
		this.add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.addTab(Chinese.tabName1, saleTab);
		tabbedPane.addTab(Chinese.tabName2, inventoryManagementTab);
		tabbedPane.addTab("record", new RecordTab(con));
		tabbedPane.addTab("transaction", new TransactionTable(con));
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		JFrame frame = new JFrame("POS_SYSTEM");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this, BorderLayout.CENTER);
		frame.setJMenuBar(InventoryUI.createMenuBar());
		frame.pack();
		frame.setVisible(true);

		TU.attach(inventoryManagementTab);
	}

	public static JMenuBar createMenuBar() {
		JMenuBar menuBar;
		JMenu menu;
		final JMenuItem menuItem;
		menuBar = new JMenuBar();
		menu = new JMenu(Chinese.file);
		menu.setMnemonic(KeyEvent.VK_F);
		menuItem = new JMenuItem(Chinese.exit, KeyEvent.VK_Q);
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(menuItem,
						Chinese.confirmExit, Chinese.exit,
						JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.CANCEL_OPTION) {
					return;
				}

				System.exit(0);
			}
		});
		menu.add(menuItem);
		menuBar.add(menu);
		return menuBar;
	}

}
