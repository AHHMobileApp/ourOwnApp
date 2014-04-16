package gui;


import gui.resources.Labels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;


import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.jdesktop.application.Application;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
public class PurchasePanel extends javax.swing.JPanel {

	private static final long serialVersionUID = 6690875041328014732L;
	private JLabel productID;
	private JTextField productIDD;
	private JLabel quantity;
	private JScrollPane jScrollPane1;
	private JTable stockTable;
	private JButton submit;
	private JTextField quantitlyd;
	private JTextField costD;
	private JLabel cost;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
		
	public PurchasePanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			this.setPreferredSize(new java.awt.Dimension(500, 220));
			this.setSize(500, 220);
			GridBagLayout thisLayout = new GridBagLayout();
			this.setLayout(thisLayout);
			thisLayout.rowWeights = new double[] {0.1, 0.1, 0.1, 0.1};
			thisLayout.rowHeights = new int[] {55, 55, 55, 55};
			thisLayout.columnWeights = new double[] {0.0, 0.0, 0.1};
			thisLayout.columnWidths = new int[] {75, 150, 75, 175};
			{
				productID = new JLabel(Labels.productID);
				this.add(productID, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
				productID.setBounds(12, 12, 51, 14);
				productID.setName("productID");
			}
			{
				productIDD = new JTextField();
				this.add(productIDD, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
				productIDD.setBounds(75, 9, 10, 21);
				productIDD.setPreferredSize(new java.awt.Dimension(130, 21));
				productIDD.setSize(130, 21);
			}
			{
				cost = new JLabel(Labels.code);
				this.add(cost, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 0), 0, 0));
				cost.setBounds(12, 38, 22, 14);
				cost.setName("cost");
			}
			{
				costD = new JTextField();
				this.add(costD, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
				costD.setBounds(75, 35, 130, 21);
			}
			{
				quantity = new JLabel(Labels.quantity);
				this.add(quantity, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(5, 5, 0, 5), 0, 0));
				quantity.setBounds(12, 65, 44, 14);
				quantity.setName("quantitly");
			}
			{
				quantitlyd = new JTextField();
				this.add(quantitlyd, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(5, 5, 0, 5), 0, 0));
				quantitlyd.setBounds(75, 62, 130, 21);
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1, new GridBagConstraints(2, 0, 2, 4, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
				{
					TableModel stockTableModel = 
						new DefaultTableModel(
								new String[][] { { "One", "Two" }, { "Three", "Four" } },
								new String[] { "Column 1", "Column 2" });
					stockTable = new JTable();
					jScrollPane1.setViewportView(stockTable);
					stockTable.setModel(stockTableModel);
				}
			}
			{
				submit = new JButton("Submit");
				this.add(submit, new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE, new Insets(5, 5, 5, 5), 0, 0));
				submit.setName("submit");
			}
			Application.getInstance().getContext().getResourceMap(getClass()).injectComponents(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
