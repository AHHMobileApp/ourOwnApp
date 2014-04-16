package inventory.stockTab;

import inventory.Inventory;
import inventory.Observer;
import inventory.StockData;
import inventory.Chinese;
import inventory.TableUpdater;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;


public class StockTab extends JPanel implements ActionListener , Observer{

	private JPanel selectPanel = null;
	protected JTable stockTable = null;
	private TableModel stockTableModel = null;
	private JPanel catagoryP = null;
	private JComboBox catagoryCB = null;
	private JLabel catagoryL = new JLabel(Chinese.catagory, JLabel.CENTER);
	private JPanel subCatagoryP = null;
	private JComboBox subCatagoryCB = new JComboBox();
	private JLabel subCatagoryL = new JLabel(Chinese.subCatagory, JLabel.CENTER);
	private JPanel thirdCatagoryP = null;
	private JComboBox thirdCatagoryCB = new JComboBox();
	private JLabel thirdCatagoryL = new JLabel(Chinese.brand, JLabel.CENTER);
	private JScrollPane scrollPane = null;
	TableRowSorter<TableModel> sorter;
	Connection con = null;

	public StockTab(final Connection con, StockData stockData) {
		this.con = con;
		this.setLayout(new BorderLayout());
		stockTableModel = new TableModel(stockData.getData());
		stockTable = new JTable(stockTableModel);
		stockTable.setAutoCreateRowSorter(true);
		sorter = new TableRowSorter<TableModel>(stockTableModel);
		stockTable.setRowSorter(sorter);
		scrollPane = new JScrollPane(stockTable);
		scrollPane.setPreferredSize(new Dimension(640, 480));
		this.add(selectingPanel(), BorderLayout.NORTH);
		this.add(scrollPane, BorderLayout.SOUTH);

	}

	public void setStockTabSize(int x, int y) {
		scrollPane.setPreferredSize(new Dimension(x, y));
	}

	public JPanel selectingPanel() {
		selectPanel = new JPanel(new GridLayout(1, 3));
		catagoryP = new JPanel(new GridLayout(1, 2));
		subCatagoryP = new JPanel(new GridLayout(1, 2));
		thirdCatagoryP = new JPanel(new GridLayout(1, 2));

		String[] catagoryList;
		try {
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery("select count(catagory_id) from "
					+ Inventory.dbName + ".catagory");
			res.next();
			int numberColumns = res.getInt("count(catagory_id)");
			catagoryList = new String[numberColumns + 1];
			catagoryList[0] = Chinese.all;
			res = st.executeQuery("select catagory_name from "
					+ Inventory.dbName + ".catagory");
			int i = 1;
			while (res.next()) {
				catagoryList[i] = res.getString("catagory_name");
				i++;
			}
			catagoryCB = new JComboBox(catagoryList);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		catagoryP.add(catagoryL);
		catagoryP.add(catagoryCB);
		subCatagoryP.add(subCatagoryL);
		subCatagoryP.add(subCatagoryCB);
		thirdCatagoryP.add(thirdCatagoryL);
		thirdCatagoryP.add(thirdCatagoryCB);

		selectPanel.add(catagoryP);
		selectPanel.add(subCatagoryP);
		selectPanel.add(thirdCatagoryP);
		catagoryCB.addActionListener(this);
		subCatagoryCB.addActionListener(this);
		thirdCatagoryCB.addActionListener(this);
		return selectPanel;
	}

	private void subCatagoryUpdate(String cata) {
		try {
			subCatagoryCB.removeAllItems();
			Statement st = con.createStatement();
			ResultSet res = st
					.executeQuery("select distinct(type) from "
							+ Inventory.dbName
							+ ".product_super a where a.catagory_Name = '"
							+ cata + "'");
			subCatagoryCB.addItem(Chinese.all);
			while (res.next()) {
				subCatagoryCB.addItem(res.getString("type"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void thirdCatagoryUpdate(String cata, String subCata) {
		try {
			thirdCatagoryCB.removeAllItems();
			Statement st = con.createStatement();
			ResultSet res = st.executeQuery("select distinct(brand) from "
					+ Inventory.dbName
					+ ".product_super a where a.catagory_Name = '" + cata
					+ "' and a.type = '" + subCata + "'");
			thirdCatagoryCB.addItem(Chinese.all);
			while (res.next()) {
				if (res.getString("brand") == null)
					break;
				thirdCatagoryCB.addItem(res.getString("brand"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == catagoryCB) {
			thirdCatagoryCB.removeAllItems();
			RowFilter<TableModel, Object> rf = null;
			if (catagoryCB.getSelectedIndex() == 0) {
				subCatagoryCB.removeAllItems();
				rf = RowFilter.regexFilter("", 2);
			} else {
				try {
					subCatagoryUpdate((String) catagoryCB.getSelectedItem());
					rf = RowFilter.regexFilter((String) catagoryCB
							.getSelectedItem(), 2);
				} catch (java.util.regex.PatternSyntaxException ex) {
					return;
				}
			}
			sorter.setRowFilter(rf);
		}

		if (e.getSource() == subCatagoryCB) {
			if (subCatagoryCB.getItemCount() == 0) {
				return;
			}
			RowFilter<TableModel, Object> rf = null;
			if (subCatagoryCB.getSelectedIndex() == 0) {
				rf = RowFilter.regexFilter((String) catagoryCB
						.getSelectedItem(), 2);
			} else {
				try {
					thirdCatagoryUpdate((String) catagoryCB.getSelectedItem(),
							(String) subCatagoryCB.getSelectedItem());
					rf = RowFilter.regexFilter((String) subCatagoryCB
							.getSelectedItem(), 3);
				} catch (java.util.regex.PatternSyntaxException ex) {
					return;
				}
			}
			sorter.setRowFilter(rf);
		}

		if (e.getSource() == thirdCatagoryCB) {
			if (thirdCatagoryCB.getItemCount() == 0) {
				return;
			}
			RowFilter<TableModel, Object> rf = null;
			if (subCatagoryCB.getSelectedIndex() == 0
					&& thirdCatagoryCB.getSelectedIndex() == 0) {
				rf = RowFilter.regexFilter((String) catagoryCB
						.getSelectedItem(), 2);
			} else if (subCatagoryCB.getSelectedIndex() != 0
					&& thirdCatagoryCB.getSelectedIndex() == 0) {
				rf = RowFilter.regexFilter((String) subCatagoryCB
						.getSelectedItem(), 3);
			} else {
				try {
					rf = RowFilter.regexFilter((String) thirdCatagoryCB
							.getSelectedItem(), 4);
				} catch (java.util.regex.PatternSyntaxException ex) {
					return;
				}
			}
			sorter.setRowFilter(rf);
		}
	}

	@Override
	public void update(TableUpdater TU,int row, int qty) {
		stockTableModel.setValueAt(qty, row, 6);
		stockTableModel.fireTableDataChanged();
	}
}
