package pos.saleTab;


import inventory.Chinese;
import inventory.Item;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SearchByID extends JPanel implements ActionListener {
	private JLabel searchByIDLabel;
	private JTextField searchByIDField;
	private JButton search;
	private JLabel result;
	private JTextField price = new JTextField(8);
	private JTextField qty = new JTextField(3);
	private JButton submit;
	private Item selectedItem;
	private SaleTableModel saleTableModel;
	Connection con;
	public SearchByID(Connection con, SaleTableModel saleTableModel){
		this.setLayout(new GridLayout(2,1));
		searchByIDLabel = new JLabel(Chinese.searchByID);
		searchByIDField = new JTextField(6);
		search = new JButton("Search");
		result = new JLabel(Chinese.result);
		submit = new JButton("submit");
		JPanel temp = new JPanel(new FlowLayout());
		JPanel temp2 = new JPanel(new FlowLayout());
		temp.add(searchByIDLabel);
		temp.add(searchByIDField);
		temp.add(search);
		this.add(temp);
		temp2.add(result);
		temp2.add(price);
		temp2.add(new JLabel("qty"));
		temp2.add(qty);
		temp2.add(submit);
		this.add(temp2);
		this.con = con;
		this.saleTableModel = saleTableModel;
		search.addActionListener(this);
		submit.addActionListener(this);
		this.setBorder(BorderFactory.createCompoundBorder(
                		BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("searchByID"),
                        BorderFactory.createEmptyBorder(2,2,2,2)),this.getBorder()));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == search){
			selectedItem = new Item(searchByIDField.getText(),con);
			result.setText(selectedItem.toString());
			price.setText(selectedItem.getSellPrice()+"");
		}
		if (e.getSource() == submit){
			if (selectedItem.getProductValid() == true){
				short sellQty;
				float sellPrice;
				
				try{
					sellQty = (short)Integer.parseInt(qty.getText());
					sellPrice = Float.parseFloat(price.getText());
				}catch(Exception ex){
					System.out.println("inVaild input");
					return;
				}
				try{
					if (selectedItem.sellItem(sellQty) == true){
						/*Statement st = con.createStatement();
						st.executeUpdate("update pos.product_super set stock = '" + selectedItem.getStock() + "' where product_ID = '" + selectedItem.getProductID() + "'");
						orderRecord.insertOrderRecord(con, selectedItem.getProductID(), sellQty, sellPrice, selectedItem.getPrimeCost(), "");
						*/
						//orderRecord.addToCart(selectedItem.getProductID(), sellQty, sellPrice, selectedItem.getRemark());
						//System.out.println(selectedItem.getBrand());
						CartItem item = new CartItem(selectedItem.getProductID(),con);
						item.setSellPrice(sellPrice);
						item.setQty(sellQty);
						saleTableModel.addData(item);
						
						saleTableModel.fireTableDataChanged();
					}
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		}
	}
}
