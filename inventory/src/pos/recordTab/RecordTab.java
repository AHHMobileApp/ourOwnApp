package pos.recordTab;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class RecordTab extends JPanel{
	
	public RecordTab(Connection con){
		this.setLayout(new BorderLayout());
	
		this.add(new RecordPanel(con),BorderLayout.CENTER);
	}
}
