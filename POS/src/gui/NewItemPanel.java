package gui;

import gui.resources.Labels;
import items.Catagory;
import items.Item;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdesktop.application.Application;

import connections.HibernateUtils;

import javax.swing.JLabel;
import javax.swing.JTextArea;
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
public class NewItemPanel extends javax.swing.JPanel implements AncestorListener,ActionListener{
	private JLabel productName;
	private JLabel catagory;
	private JTextField quantityD;
	private JTextField suggestPriceD;
	private JTextField costD;
	private JTextField productNameD;
	private JButton submit;
	private JComboBox catagoryD;
	private JTextArea descriptionD;
	private JLabel quantity;
	private JLabel suggestPrice;
	private JLabel cost;
	private JLabel description;
	private List<Catagory> catagorys;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	public NewItemPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(500, 220));
			this.setSize(500, 220);
			GridBagLayout thisLayout = new GridBagLayout();
			this.setLayout(thisLayout);
			{
				productName = new JLabel(Labels.productName);
				this.add(productName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				productName.setName("productName");
			}
			{
				catagory = new JLabel(Labels.catagory);
				this.add(catagory, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				catagory.setName("catagory");
			}
			{
				description = new JLabel(Labels.description);
				this.add(description, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				description.setName("description");
			}
			{
				cost = new JLabel(Labels.code);
				this.add(cost, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				cost.setName("cost");
			}
			{
				suggestPrice = new JLabel(Labels.suggestPrice);
				this.add(suggestPrice, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				suggestPrice.setName("suggestPrice");
			}
			{
				quantity = new JLabel(Labels.quantity);
				this.add(quantity, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				quantity.setName("quantity");
			}
			{
				descriptionD = new JTextArea();
				this.add(descriptionD, new GridBagConstraints(3, 2, 1, 2, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
				descriptionD.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
			}
			{
				submit = new JButton(Labels.submit);
				this.add(submit, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				submit.setName("submit");
				submit.addActionListener(this);
			}
			{
				productNameD = new JTextField();
				this.add(productNameD, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				productNameD.setPreferredSize(new java.awt.Dimension(120, 21));
				productNameD.setSize(120, 21);
			}
			{
				costD = new JTextField();
				this.add(costD, new GridBagConstraints(3, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				costD.setPreferredSize(new java.awt.Dimension(120, 21));
				costD.setSize(120, 21);
				costD.setName("costD");
			}
			{
				suggestPriceD = new JTextField();
				this.add(suggestPriceD, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				suggestPriceD.setPreferredSize(new java.awt.Dimension(120, 21));
				suggestPriceD.setSize(120, 21);
			}
			{
				quantityD = new JTextField();
				this.add(quantityD, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				quantityD.setPreferredSize(new java.awt.Dimension(120, 21));
				quantityD.setSize(120, 21);
			}
			{	
				ComboBoxModel jComboBox1Model = new DefaultComboBoxModel();
				catagoryD = new JComboBox();
				this.add(catagoryD, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				catagoryD.setModel(jComboBox1Model);
				Session session = HibernateUtils.getSessionFactory().openSession(); 
				Criteria criteria = session.createCriteria(Catagory.class);
		        this.catagorys = criteria.list();
		        for (Catagory _catagory : catagorys){
		        	catagoryD.addItem(_catagory.getCatagoryName());
		        }
				session.close();
			}
			thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0};
			thisLayout.rowHeights = new int[] {55, 55, 55, 55};
			thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.1};
			thisLayout.columnWidths = new int[] {75, 150, 75, 170};
//			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void reset(){
		quantityD.setText("");
		suggestPriceD.setText("");
		costD.setText("");
		productNameD.setText("");
		catagoryD.setSelectedItem(0);
		descriptionD.setText("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit){
			Item item = new Item();
			item.setCost(Integer.parseInt(costD.getText()));
			item.setDes(descriptionD.getText());
			item.setProductName(productNameD.getText());
			item.setStock(Integer.parseInt(quantityD.getText()));
			item.setSuggestedRetailPrice(Double.parseDouble(suggestPriceD.getText()));
			item.setCatagory(catagoryD.getSelectedItem().toString());
			for (Catagory _catagory : this.catagorys){
				System.out.println(catagoryD.getSelectedItem().toString() + " " + _catagory.getCatagoryName());
				if (_catagory.getCatagoryName().trim().equals(catagoryD.getSelectedItem().toString().trim())){
					item.setProductID(item.productIDGenerater(_catagory.getCatagoryID()));
					break;
				} else {
					item.setProductID(item.productIDGenerater("0"));
				}
			}
			Session session = HibernateUtils.getSessionFactory().openSession(); 
			Transaction tx = session.beginTransaction();
			session.save(item);
			tx.commit();
			session.close();
			reset();
		}
	}

	@Override
	public void ancestorAdded(AncestorEvent arg0) {
		Session session = HibernateUtils.getSessionFactory().openSession(); 
		Criteria criteria = session.createCriteria(Catagory.class);
        this.catagorys = criteria.list();
        for (Catagory _catagory : catagorys){
        	catagoryD.addItem(_catagory.getCatagoryName());
        }
		session.close();
	}

	@Override
	public void ancestorMoved(AncestorEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ancestorRemoved(AncestorEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public JComboBox getCatagoryD() {
		return catagoryD;
	}

	public void setCatagoryD(JComboBox catagoryD) {
		this.catagoryD = catagoryD;
	}

	public List<Catagory> getCatagorys() {
		return catagorys;
	}

	public void setCatagorys(List<Catagory> catagorys) {
		this.catagorys = catagorys;
	}
}
