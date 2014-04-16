package gui;

import gui.resources.Labels;

import items.Item;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;

import javax.swing.WindowConstants;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdesktop.application.Application;

import records.Record;
import records.VoidRecord;

import connections.HibernateUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
public class VoidRecordPanel extends javax.swing.JPanel implements ActionListener{
	
	private static final long serialVersionUID = -2581030692021221357L;
	private JButton submit;
	private JTextField recordIDD;
	private JButton jButton1;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JLabel productID;
	private JTextField productIDD;
	private JTextField quantitlyd;
	private JLabel quantity;
	private JTextField voidPriceD;
	private JLabel voidPrice;
	private JLabel recordID;
	
	public VoidRecordPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(500, 220));
			this.setSize(500, 220);
			this.setLayout(null);
			{
				submit = new JButton(Labels.submit);
				this.add(submit);
				submit.setBounds(128, 110, 107, 21);
				submit.setName("submit");
				submit.addActionListener(this);
			}
			{
				recordIDD = new JTextField();
				this.add(recordIDD);
				recordIDD.setBounds(105, 77, 130, 21);
			}
			{
				recordID = new JLabel(Labels.recordID);
				this.add(recordID);
				recordID.setBounds(12, 73, 81, 14);
				recordID.setName("recordID");
			}
			{
				voidPrice = new JLabel(Labels.voidPrice);
				this.add(voidPrice);
				voidPrice.setBounds(12, 47, 81, 14);
				voidPrice.setName("retailPrice");
			}
			{
				voidPriceD = new JTextField();
				this.add(voidPriceD);
				voidPriceD.setBounds(105, 44, 130, 21);
			}
			{
				quantity = new JLabel(Labels.quantity);
				this.add(quantity, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
				quantity.setName("quantity");
				quantity.setBounds(261, 80, 81, 14);
			}
			{
				quantitlyd = new JTextField();
				this.add(quantitlyd, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
				quantitlyd.setBounds(342, 77, 130, 21);
			}
			{
				productIDD = new JTextField();
				this.add(productIDD, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
				productIDD.setBounds(342, 44, 130, 21);
			}
			{
				productID = new JLabel(Labels.productID);
				this.add(productID, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
				productID.setName("productID");
				productID.setBounds(261, 47, 81, 14);
			}
			{
				jButton1 = new JButton(Labels.submit);
				this.add(jButton1, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				jButton1.setName("jButton1");
				jButton1.setBounds(365, 110, 107, 21);
				jButton1.addActionListener(this);
			}
			{
				jLabel1 = new JLabel(Labels.voidRecord);
				this.add(jLabel1);
				jLabel1.setBounds(12, 12, 107, 29);
				jLabel1.setName("jLabel1");
			}
			{
				jLabel2 = new JLabel(Labels.parchase);
				this.add(jLabel2);
				jLabel2.setName("jLabel2");
				jLabel2.setBounds(261, 12, 107, 29);
			}
//			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit){
			if(recordIDD.getText() != null && recordIDD.getText().length() != 0){
				Session session = HibernateUtils.getSessionFactory().openSession();
				String hql = "from Record record where recordID = " + recordIDD.getText() + "";
				Query query = session.createQuery(hql);
				List<Record> records = query.list();
				if (records.size() == 1){
					hql = "from VoidRecord voidRecord where recordID = " + recordIDD.getText() + "";
					query = session.createQuery(hql);
					List<VoidRecord> voidRecordResult = query.list();
					if(voidRecordResult.size() == 0){
						Double voidPrice = records.get(0).getRetailPrice();
						if (voidPriceD.getText() != null && voidPriceD.getText().length() != 0){
							try{
								voidPrice = Double.parseDouble(voidPriceD.getText());
							}catch (Exception ex){
								
							}
						}
						
						VoidRecord voidRecord = new VoidRecord();
						voidRecord.setRecordDate(records.get(0).getDate());
						Date date = new Date();
						java.sql.Date sqlDate = new java.sql.Date(date.getTime());
						voidRecord.setVoidDate(sqlDate);
						voidRecord.setRecordID(records.get(0).getRecordID());
						voidRecord.setVoidPrice(voidPrice * -1);
						voidRecord.setProductID(records.get(0).getProductID());
						hql = "from Item item where productID = '" + records.get(0).getProductID() + "'";
						query = session.createQuery(hql);
						Item item = (Item)query.list().get(0);
						item.setStock(item.getStock() + 1);
						Transaction tx = session.beginTransaction();
						session.update(item);
						session.save(voidRecord);
						tx.commit();
					}else{
						JOptionPane.showMessageDialog(this,"This Record is already void");
					}
				}else{
					JOptionPane.showMessageDialog(this,"Record ID not found");
				}
				session.close();
			}
			recordIDD.setText("");
			voidPriceD.setText("");
		}
		if (e.getSource() == jButton1){
			if(productIDD.getText() != null && productIDD.getText().length() != 0){
				Session session = HibernateUtils.getSessionFactory().openSession();
				String hql = "from Item item where productID = '" + productIDD.getText() + "'";
				Query query = session.createQuery(hql);
				if (query.list().size() == 1){
					Item item = (Item)query.list().get(0);
					try{
						item.setStock(item.getStock() + Integer.parseInt(quantitlyd.getText()));
					} catch (Exception e1){
						
					}
					Transaction tx = session.beginTransaction();
					session.update(item);
					tx.commit();
				}
				quantitlyd.setText("");
				productIDD.setText("");
				session.close();
			}
		}
	}
}
