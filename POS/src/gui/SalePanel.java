package gui;
import gui.resources.Labels;
import items.Item;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.JobAttributes;
import java.awt.PageAttributes;

import java.awt.Insets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdesktop.application.Application;

import printingInvoice.Printing;

import records.Bill;
import records.LastID;
import records.Record;
import records.SaleTabTableModel;
import vips.Vip;

import connections.HibernateUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;


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
public class SalePanel extends javax.swing.JPanel implements AncestorListener,ActionListener,KeyListener {

	private static final long serialVersionUID = 1L;
	private JScrollPane jScrollPane;
	private JLabel retailPrice;
	private JLabel stockL;
	private JLabel productNameL;
	private JPanel productDetail;
	private JLabel vipID;
	private JLabel suggestedPrice;
	
	private JTextField productIDD;
	private JTextField retailPriceD;
	private JLabel suggestPriceD;
	private JTextField vipIDD;
	private JLabel stockD;
	private JLabel productNameD;

	private JButton submitButton;
	private JTable cart;
	private SaleTabTableModel cartModel;
	private Vector<String[]> cartItems;
	private Boolean isItem;
	private int selectedPos;
	private JButton searchPanel;
	private JButton checkOut;
	private List<Item> items;
	private List<Item> soldItems;
	private List<Vip> vips;
	private LastID lastID;

	@SuppressWarnings("unchecked")
	public SalePanel() {
		super();
		initGUI();
		isItem = false;
		Session session = HibernateUtils.getSessionFactory().openSession();
		String hql = "from Item item";
		Query query = session.createQuery(hql);
		items = query.list();
		hql = "from Vip vip";
		query = session.createQuery(hql);
		vips = query.list();
		soldItems = new ArrayList<Item>();
		session.close();
		updateLastID();
	}
	private void updateLastID(){
		Session session = HibernateUtils.getSessionFactory().openSession();
		String hql = "from LastID lastID";
		Query query = session.createQuery(hql);
		lastID = (LastID)query.list().get(0);
		System.out.println(lastID.getId());
		session.close();
		Date today = new Date();
		if (today.getYear() == lastID.getDate().getYear()
			&& today.getMonth() == lastID.getDate().getMonth()
			&& today.getDate() == lastID.getDate().getDate())
		{
		}else {
			Date date = new Date();
			lastID.setDate(new java.sql.Date(date.getTime()));
			lastID.setId(1);
			saveLastID();
		}
	}
	
	private void saveLastID(){
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(lastID);
		tx.commit();
		session.close();
	}
	
	private void printInvoice(Bill bill, List<Record> records, Vip vip){
		PrinterJob job = PrinterJob.getPrinterJob();
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		aset.add(new Copies(1));
		PageFormat pf = job.getPageFormat(aset);
		Paper paper = new Paper(); //pf.getPaper();
		paper.setSize(132,20*72);
		paper.setImageableArea(0, 0, 2.3*72, 20*72);

		JobAttributes ja = new JobAttributes();
		ja.setDestination(JobAttributes.DestinationType.PRINTER);
		ja.setPrinter("Label");
		ja.setDialog(JobAttributes.DialogType.COMMON);
		PageAttributes pa = new PageAttributes();
		pa.setOrigin(PageAttributes.OriginType.PRINTABLE);

		pf.setPaper(paper);
		job.defaultPage(pf);
		job.setPrintable(new Printing(bill, records, vip), pf);
		
		try {
			job.print(aset);
		} catch (PrinterException ex) {
			/* The job did not successfully complete */
		}
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(500, 220));
			this.setLayout(null);
			this.addAncestorListener(this);
			{
				jScrollPane = new JScrollPane();
				this.add(jScrollPane);
				jScrollPane.setBounds(216, 12, 271, 170);
				{	
					cartItems = new Vector<String[]>();
					cartModel = new SaleTabTableModel(new String[0][2]);
					cart = new JTable();
					jScrollPane.setViewportView(cart);
					cart.setModel(cartModel);
				}
			}
			{
				productIDD = new JTextField();
				this.add(productIDD);
				productIDD.setBounds(12, 161, 192, 21);
				productIDD.addKeyListener(this);
			}
			{
				submitButton = new JButton(Labels.submit);
				this.add(submitButton);
				submitButton.setBounds(92, 188, 112, 21);
				submitButton.setName("submitButton");
				submitButton.addActionListener(this);
			}
			{
				productDetail = new JPanel();
				GridBagLayout productDetailLayout = new GridBagLayout();
				productDetailLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1, 0.1};
				productDetailLayout.rowHeights = new int[] {7, 7, 7, 7, 7};
				productDetailLayout.columnWeights = new double[] {0.0, 0.1};
				productDetailLayout.columnWidths = new int[] {100, 7};
				this.add(productDetail);
				productDetail.setBounds(12, 12, 192, 143);
				productDetail.setLayout(productDetailLayout);
				{
					productNameL = new JLabel(Labels.productName);
					productDetail.add(productNameL, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					productNameL.setName("productNameL");
				}
				{
					productNameD = new JLabel();
					productDetail.add(productNameD, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					productNameD.setName("productNameD");
				}
				{
					stockL = new JLabel(Labels.stock);
					productDetail.add(stockL, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					stockL.setName("stockL");
				}
				{
					stockD = new JLabel();
					productDetail.add(stockD, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					stockD.setName("stockD");
				}
				{
					suggestedPrice = new JLabel(Labels.suggestPrice);
					productDetail.add(suggestedPrice, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					suggestedPrice.setName("suggestedPrice");
				}
				{
					suggestPriceD = new JLabel();
					productDetail.add(suggestPriceD, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					suggestPriceD.setName("suggestPriceD");
				}
				{
					retailPrice = new JLabel(Labels.retailPrice);
					productDetail.add(retailPrice, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					retailPrice.setName("retailPrice");
				}
				{
					retailPriceD = new JTextField();
					productDetail.add(retailPriceD, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					retailPriceD.setPreferredSize(new java.awt.Dimension(5, 20));
					retailPriceD.setSize(90, 20);
				}
				{
					vipID = new JLabel(Labels.VIPID);
					productDetail.add(vipID, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					vipID.setName("vipIDL");
				}
				{
					vipIDD = new JTextField();
					productDetail.add(vipIDD, new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					vipIDD.setPreferredSize(new java.awt.Dimension(5, 20));
					vipIDD.setSize(90, 20);
				}
			}
			{
				checkOut = new JButton(Labels.checkOut);
				this.add(checkOut);
				checkOut.setBounds(385, 188, 104, 21);
				checkOut.setName("checkOut");
				checkOut.addActionListener(this);
			}
			{
				searchPanel = new JButton("Search");
				this.add(searchPanel);
				searchPanel.setBounds(228, 188, 91, 21);
				searchPanel.setName("searchPanel");
				searchPanel.addActionListener(this);
			}
//			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reset(){
		productIDD.setText("");
		retailPriceD.setText("");
		suggestPriceD.setText("$0.0");
		vipIDD.setText("");
		stockD.setText("0");
		productNameD.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitButton){
			if (isItem){
				Double retailPrice = Double.parseDouble(suggestPriceD.getText());
				if(retailPriceD.getText() != null && !retailPriceD.getText().equals("")){
					try{
						retailPrice = Double.parseDouble(retailPriceD.getText());
					}catch(Exception ex){
						//TODO
					}
				}
				String[] thisItem = {productNameD.getText(),retailPrice.toString(),""};
				cartItems.add(thisItem);
				cartModel.setData(cartItems);
				cartModel.fireTableDataChanged();
				items.get(selectedPos).setStock(items.get(selectedPos).getStock() - 1);
				Item selectedItem = items.get(selectedPos);
				selectedItem.setRetailPrice(retailPrice);
				soldItems.add(items.get(selectedPos));
				isItem = false;
				this.reset();
			}
		}
		if (e.getSource() == checkOut){
			if (soldItems != null && soldItems.size() != 0){
				Vip vip = null;
				List<Record> records = new ArrayList<Record>();
				if(vipIDD.getText() != null && vipIDD.getText() != ""){
					for (Vip _vip : vips){
						try {
							if (_vip.getVipID() == Integer.parseInt(vipIDD.getText())){
								vip = _vip;
								break;
							}
						}catch (Exception e1){
							
						}
					}
				}
				Session session = HibernateUtils.getSessionFactory().openSession();
				Transaction tx = session.beginTransaction();
				
				Bill bill = new Bill();
				Date date = new Date();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				bill.setDate(sqlDate);
				bill.setBillID(bill.genBillID(lastID.getId()));
				lastID.setId(lastID.getId() + 1);
				session.update(lastID);
				double total = 0D;
				
				for (Item item : soldItems){
					session.update(item);
					Record record = new Record();
					record.setDate(sqlDate);
					record.setProductID(item.getProductID());
					Double retailPrice = item.getRetailPrice();
					if (vip != null){
						retailPrice = (retailPrice * (100 - vip.getDiscount()))/100;
					}
					total += retailPrice;
					record.setRetailPrice(retailPrice);			
					record.setBillID(bill.getBillID());
					records.add(record);
					session.save(record);
				}
				bill.setTotal(total);
				if (vip != null){
					bill.setVipNo(vip.getVipID() + "");
				}else{
					bill.setVipNo("----");
				}
				session.save(bill);
				try{
					tx.commit();
				}catch (Exception ex){
					System.out.println("ERROR");
				}
				session.close();
				printInvoice(bill, records, vip);
				soldItems = new ArrayList<Item>();
				cartItems = new Vector<String[]>();
				cartModel.setData(cartItems);
				cartModel.fireTableDataChanged();
				reset();
			}
		}
		if (e.getSource() == searchPanel){
			ItemSearch itemSearch = new ItemSearch();
			itemSearch.setVisible(true);
		}
		productIDD.requestFocus();
	}

	@Override
	public void ancestorAdded(AncestorEvent event) {
		
	}

	@Override
	public void ancestorMoved(AncestorEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void ancestorRemoved(AncestorEvent event) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
			int temp = 0;
			for (Item item : items){
			    if (productIDD.getText().trim().equals(item.getProductID())){
			    	suggestPriceD.setText(item.getSuggestedRetailPrice() + "");
			    	productNameD.setText(item.getProductName());
			    	stockD.setText(item.getStock() + "");
			    	selectedPos = temp;
			    	isItem = true;
			    	break;
			    }
			    temp++;
			}
		}
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE){
			soldItems = new ArrayList<Item>();
			cartItems = new Vector<String[]>();
			cartModel.setData(cartItems);
			cartModel.fireTableDataChanged();
			isItem = false;
			Session session = HibernateUtils.getSessionFactory().openSession();
			String hql = "from Item item";
			Query query = session.createQuery(hql);
			items = query.list();
			hql = "from Vip vip";
			query = session.createQuery(hql);
			vips = query.list();
			session.close();
			reset();
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}
