package gui;

import gui.resources.Labels;

import items.Catagory;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import javax.swing.WindowConstants;

import org.hibernate.Session;
import org.hibernate.Transaction;

import records.LastID;

import connections.HibernateUtils;

import javax.swing.SwingUtilities;


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
public class MainFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 8767997579318207265L;
	private JTabbedPane MainTab;
	private JMenuBar MainMeun;
	private JMenu Menu;
	private JMenuItem jMenuItem1;
	
	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame inst = new MainFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	public MainFrame() {
		super("My POS");
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				MainMeun = new JMenuBar();
				setJMenuBar(MainMeun);
				MainMeun.setSize(497, 20);
				MainMeun.setPreferredSize(new java.awt.Dimension(497, 20));
				MainMeun.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				MainMeun.setName("MainMeun");
				{
					Menu = new JMenu("Meun");
					MainMeun.add(Menu);
					{
						jMenuItem1 = new JMenuItem(Labels.newCatagory);
						Menu.add(jMenuItem1);
						jMenuItem1.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent arg0) {
								String catagoryID = (String)JOptionPane.showInputDialog(Labels.inputCatagoryID);
								String catagoryName = (String)JOptionPane.showInputDialog(Labels.inputCatagoryName);
								Catagory catagory = new Catagory();
								catagory.setCatagoryID(catagoryID);
								catagory.setCatagoryName(catagoryName);
								Session session = HibernateUtils.getSessionFactory().openSession();
								Transaction tx = session.beginTransaction();
								try{
									session.save(catagory);
									tx.commit();
								}catch(Exception e){
									
								}
								session.close();
							}
						});
					}
				}
			}
			{	
				NewItemPanel newItemPanel = new NewItemPanel();
				MainTab = new JTabbedPane();
				MainTab.add(Labels.saleTab, new SalePanel());
				MainTab.add(newItemPanel,Labels.newItem);
				MainTab.add(new NewVIPPanel(),Labels.newVIP);
				MainTab.add(new VoidRecordPanel(),Labels.voidPanel);
				MainTab.add(new SettlementPanel(),Labels.settlement);
				getContentPane().add(MainTab, BorderLayout.CENTER);
				MainTab.setPreferredSize(new java.awt.Dimension(497, 274));
			}
			pack();
			this.setSize(505, 300);
			this.setResizable(false);
//			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(getContentPane());
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
}
