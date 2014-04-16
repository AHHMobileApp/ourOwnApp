package gui;

import gui.resources.Labels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.WindowConstants;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jdesktop.application.Application;

import connections.HibernateUtils;

import vips.Vip;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
public class NewVIPPanel extends javax.swing.JPanel implements ActionListener{

	private static final long serialVersionUID = 3628026542214370889L;
	private JLabel firstName;
	private JLabel lastName;
	private JTextField discountD;
	private JButton submit;
	private JTextField teleNoD;
	private JTextField lastNameD;
	private JTextField firstNameD;
	private JLabel teleNo;
	private JLabel discount;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	public NewVIPPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(500, 220));
			GridBagLayout thisLayout = new GridBagLayout();
			this.setSize(500, 220);
			thisLayout.rowWeights = new double[] {0.0, 0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {55, 55, 55, 55};
			thisLayout.columnWeights = new double[] {0.0, 0.1, 0.1, 0.1};
			thisLayout.columnWidths = new int[] {75, 150, 75, 175};
			this.setLayout(thisLayout);
			{
				firstName = new JLabel(Labels.firstName);
				this.add(firstName, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				firstName.setName("firstName");
			}
			{
				lastName = new JLabel(Labels.lastName);
				this.add(lastName, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				lastName.setName("lastName");
			}
			{
				discount = new JLabel(Labels.discount);
				this.add(discount, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				discount.setName("discount");
			}
			{
				teleNo = new JLabel(Labels.teleNo);
				this.add(teleNo, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				teleNo.setName("teleNo");
			}
			{
				firstNameD = new JTextField();
				this.add(firstNameD, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				firstNameD.setPreferredSize(new java.awt.Dimension(160, 21));
			}
			{
				lastNameD = new JTextField();
				this.add(lastNameD, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				lastNameD.setPreferredSize(new java.awt.Dimension(160, 21));
			}
			{
				discountD = new JTextField();
				this.add(discountD, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				discountD.setPreferredSize(new java.awt.Dimension(160,21));
			}
			{
				teleNoD = new JTextField();
				this.add(teleNoD, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 5), 0, 0));
				teleNoD.setPreferredSize(new java.awt.Dimension(160,21));
				teleNoD.setSize(160, 21);
			}
			{
				submit = new JButton(Labels.submit);
				this.add(submit, new GridBagConstraints(2, 3, 2, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				submit.setName("submit");
				submit.setPreferredSize(new java.awt.Dimension(50, 21));
				submit.addActionListener(this);
			}
//			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit){
			Vip vip = new Vip();
			try{
				vip.setDiscount(Integer.parseInt(discountD.getText()));
			} catch (Exception e1){
				return;
			}
			vip.setFirstName(firstNameD.getText());
			vip.setLastName(lastNameD.getText());
			vip.setTelePhoneNo(teleNoD.getText());
			Session session = HibernateUtils.getSessionFactory().openSession(); 
			Transaction tx = session.beginTransaction();
			session.save(vip);
			tx.commit();
			session.close();
		}
	}
}
