package pos.recordTab;

import inventory.Chinese;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class RecordPanel extends JPanel{

	protected JTable recordTable = null;
	private RecordTableModel recordTableModelTable = null;
	private JScrollPane scrollPane = null;
	TableRowSorter<RecordTableModel> sorter;
	Connection con = null;
	
	public RecordPanel(Connection con){
		this.con = con;
		this.setLayout(new BorderLayout());
		Record record = new Record(con);
		recordTableModelTable = new RecordTableModel(record.getOrders());
		recordTable = new JTable(recordTableModelTable);
		recordTable.setAutoCreateRowSorter(true);

		scrollPane = new JScrollPane(recordTable);
    	scrollPane.setPreferredSize(new Dimension(640,480));    	
		this.add(scrollPane,BorderLayout.SOUTH);		
	}
	public void setStockTabSize(int x, int y){
		scrollPane.setPreferredSize(new Dimension(x,y));
	}

 
  
}

