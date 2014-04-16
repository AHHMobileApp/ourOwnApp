package gui;

import gui.resources.Labels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdesktop.application.Application;

import connections.HibernateUtils;

import records.Record;
import records.SettlementPanelTableModel;
import records.VoidRecord;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class SettlementPanel extends javax.swing.JPanel implements ActionListener,AncestorListener {
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JLabel dayTotal;
	private JLabel monthTotal;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JLabel dayL;
	private JLabel monthL;
	private JComboBox day;
	private JComboBox month;
	private JTable monthlySettlement;
	private JTable dailySettlement;
	private Date minDate;
	private List<Record> records;
	private List<VoidRecord> voidRecords;
	private SettlementPanelTableModel daily;
	private SettlementPanelTableModel monthly;
	private Date today;
	
	public SettlementPanel() {
		super();
		this.addAncestorListener(this);
		today = new Date();
		minDate = new Date();
		minDate.setYear(today.getYear() - 1);
		minDate.setMonth(today.getMonth());
		minDate.setDate(1);
		retrieveRecordFromDB();
		daily = new SettlementPanelTableModel(new String[0][3]);
		monthly = new SettlementPanelTableModel(new String[0][3]);
		initGUI();
		updateSettlement();
	}
	
	public void retrieveRecordFromDB(){
		Session session = HibernateUtils.getSessionFactory().openSession();
		String hql = "from Record record where recordDate > " + new java.sql.Date(minDate.getTime());
		Query query = session.createQuery(hql);
		records = query.list();
		hql = "from VoidRecord voidRecord where voidDate > " + new java.sql.Date(minDate.getTime());
		query = session.createQuery(hql);
		voidRecords = query.list();
	}
	private void updateSettlement(){
		Vector<String[]> dailyData = new Vector<String[]>();
		Vector<String[]> monthlyData = new Vector<String[]>();
		Double dayTotal = 0D;
		Double monthTotal = 0D;
		for (Record record : records){
			if (record.getDate().getMonth() + 1 == Integer.parseInt(month.getSelectedItem().toString())){
				String[] tempRecord = {record.getRecordID().toString(), record.getProductID(),record.getRetailPrice().toString()};
				try{
					if (record.getDate().getDate() == Integer.parseInt(day.getSelectedItem().toString())){
						dailyData.add(tempRecord);
						dayTotal += record.getRetailPrice();
					}
				}catch (Exception e){
					
				}
				monthlyData.add(tempRecord);
				monthTotal += record.getRetailPrice();
			}
		}
		for (VoidRecord record : voidRecords){
			if (record.getVoidDate().getMonth() + 1 == Integer.parseInt(month.getSelectedItem().toString())){
				String[] tempRecord = {record.getRecordID().toString(), record.getProductID(),record.getVoidPrice().toString()};
				try{
					if (record.getVoidDate().getDate() == Integer.parseInt(day.getSelectedItem().toString())){
						dailyData.add(tempRecord);
						dayTotal += record.getVoidPrice();
					}
				}catch (Exception e){
					
				}
				monthlyData.add(tempRecord);
				monthTotal += record.getVoidPrice();
			}
		}
		
		this.dayTotal.setText("Total : $" + dayTotal);
		this.monthTotal.setText("Total : $" + monthTotal);
		daily.setData(dailyData);
		monthly.setData(monthlyData);
		daily.fireTableDataChanged();
		monthly.fireTableDataChanged();
	}
	private int maxNoOfDays(){
		int thisYear = today.getYear();
		if (today.getMonth() < Integer.parseInt(month.getSelectedItem().toString())){
			thisYear = thisYear - 1;
		}
		Calendar cal = new GregorianCalendar(thisYear, Integer.parseInt(month.getSelectedItem().toString()) - 1, 1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(500, 220));
			GridBagLayout thisLayout = new GridBagLayout();
			this.setSize(500, 220);
			thisLayout.rowWeights = new double[] {0.0, 0.0, 0.1};
			thisLayout.rowHeights = new int[] {27, 152, 7};
			thisLayout.columnWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.columnWidths = new int[] {7, 7, 7, 7};
			this.setLayout(thisLayout);
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1, new GridBagConstraints(2, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
				{
					dailySettlement = new JTable();
					jScrollPane1.setViewportView(dailySettlement);
					dailySettlement.setModel(daily);
				}
			}
			{
				jScrollPane2 = new JScrollPane();
				this.add(jScrollPane2, new GridBagConstraints(0, 1, 2, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
				{
					monthlySettlement = new JTable();
					jScrollPane2.setViewportView(monthlySettlement);
					monthlySettlement.setModel(monthly);
				}
			}
			{
				month = new JComboBox();
				this.add(month, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				for (int i = 1; i <= 12; i++){
					month.addItem(i);
				}
				month.setSelectedIndex(today.getMonth());
				month.addActionListener(this);
			}
			{
				day = new JComboBox();
				int maxNoOfDays = maxNoOfDays();
				for (int i = 1; i <= maxNoOfDays; i++){
					day.addItem(i);
				}
				day.setSelectedIndex(today.getDate() - 1);
				day.addActionListener(this);
				this.add(day, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
			}
			{
				monthL = new JLabel(Labels.month);
				this.add(monthL, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				monthL.setName("monthL");
			}
			{
				dayL = new JLabel(Labels.day);
				this.add(dayL, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				dayL.setName("dayL");
			}
			{
				jLabel1 = new JLabel(Labels.monthEnd);
				this.add(jLabel1, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabel1.setName("jLabel1");
			}
			{
				jLabel2 = new JLabel(Labels.dayEnd);
				this.add(jLabel2, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				jLabel2.setName("jLabel2");
			}
			{
				monthTotal = new JLabel();
				this.add(monthTotal, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				monthTotal.setName("monthTotal");
			}
			{
				dayTotal = new JLabel();
				this.add(dayTotal, new GridBagConstraints(3, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				dayTotal.setName("dayTotal");
			}
//			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == month){
			int maxNoOfDays = maxNoOfDays();
			day.removeAllItems();
			for (int i = 1; i <= maxNoOfDays; i++){
				day.addItem(i);
			}
			this.updateSettlement();
		}
		if (arg0.getSource() == day){
			this.updateSettlement();
		}
	}

	@Override
	public void ancestorAdded(AncestorEvent arg0) {
		this.retrieveRecordFromDB();
		this.updateSettlement();
	}

	@Override
	public void ancestorMoved(AncestorEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ancestorRemoved(AncestorEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
