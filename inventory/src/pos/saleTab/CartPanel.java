package pos.saleTab;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class CartPanel extends JPanel implements ActionListener{
	private JTable table;
	private JButton checkOut;
	Connection con;
	SaleTableModel saleTableModel;

	public CartPanel(SaleTableModel saleTableModel, Connection con){
		this.con = con;
		this.saleTableModel = saleTableModel;
		checkOut = new JButton("check out");
		table = new JTable(this.saleTableModel);
		JScrollPane pane = new JScrollPane(table);
		//table.setPreferredSize(new Dimension(640,100));
		pane.setPreferredSize(new Dimension(640,160));
		this.setLayout(new BorderLayout());
		this.add(pane,BorderLayout.CENTER);
		this.add(checkOut, BorderLayout.SOUTH);
		checkOut.addActionListener(this);
		this.setBorder(BorderFactory.createCompoundBorder(
                		BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Cart"),
                        BorderFactory.createEmptyBorder(2,2,2,2)),this.getBorder()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == checkOut){
			OrderRecord orderRecord = new OrderRecord();
			float subTotal = 0;
			String orderID = orderRecord.orderIDGenerator();
			for(int i = 0; i < saleTableModel.getRowCount(); i++){
				if(saleTableModel.getValueAt(i, 0) != null){
					float temp = (Float) saleTableModel.getValueAt(i, 3);
					int qty = (Integer) saleTableModel.getValueAt(i, 4);
					subTotal += temp * qty;
				}
			}
			if(orderRecord.insertOrderMaster(con, orderID, subTotal)){
				for(int i = 0; i < saleTableModel.getRowCount(); i++){
					if(saleTableModel.getValueAt(i, 0) != null){
						CartItem item = new CartItem((String)saleTableModel.getValueAt(i, 0),con);
						item.setSellPrice((Float) saleTableModel.getValueAt(i, 3));
						item.setQty((Integer)saleTableModel.getValueAt(i, 4));
						orderRecord.insertOrderDetial(con, orderID, item);
					}
				}
			}
			saleTableModel.resetTable();
			saleTableModel.fireTableDataChanged();
		}
		
	}
}
