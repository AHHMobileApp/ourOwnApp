package gui;

import items.Catagory;
import items.Item;
import items.ItemsTableModel;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;

import javax.swing.WindowConstants;
import javax.swing.table.TableRowSorter;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.jdesktop.application.Application;

import connections.HibernateUtils;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class ItemSearch extends javax.swing.JFrame implements ActionListener {
	private JPanel jPanel1;
	private JScrollPane jScrollPane1;
	private JTable jTable1;
	private JComboBox jComboBox1;
	private List<Catagory> catagorys;
	private List<Item> items;

	/**
	 * Auto-generated main method to display this JFrame
	 */

	public ItemSearch() {
		super();
		Session session = HibernateUtils.getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(Catagory.class);
		catagorys = criteria.list();
		criteria = session.createCriteria(Item.class);
		items = criteria.list();
		session.close();
		initGUI();
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jPanel1 = new JPanel();
				GridBagLayout jPanel1Layout = new GridBagLayout();
				jPanel1Layout.rowWeights = new double[] { 0.0, 0.1 };
				jPanel1Layout.rowHeights = new int[] { 36, 7 };
				jPanel1Layout.columnWeights = new double[] { 0.1 };
				jPanel1Layout.columnWidths = new int[] { 7 };
				jPanel1.setLayout(jPanel1Layout);
				getContentPane().add(jPanel1, BorderLayout.CENTER);
				{
					jScrollPane1 = new JScrollPane();
					jPanel1.add(jScrollPane1, new GridBagConstraints(0, 1, 1,
							1, 0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0,
							0));
					{
						ItemsTableModel itemsTableModel = new ItemsTableModel(
								new String[0][2]);
						jTable1 = new JTable();
						Vector<String[]> data = new Vector<String[]>();
						for (Item item : items) {
							if (item.getStock() != 0){
								String[] temp = { item.getProductName(),
										item.getProductID(), item.getCatagory() };
								data.add(temp);
							}
						}
						itemsTableModel.setData(data);
						jScrollPane1.setViewportView(jTable1);
						jTable1.setModel(itemsTableModel);
						jTable1.addMouseListener(new MouseAdapter() {
							public void mouseClicked(MouseEvent e) {
								int row = jTable1.getSelectedRow();
								int col = jTable1.getSelectedColumn();
								StringSelection stringSelection = new StringSelection( jTable1.getValueAt(row, 1).toString() );
								Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
							    clipboard.setContents( stringSelection, stringSelection);
							}
						});
					}
				}
				{
					jComboBox1 = new JComboBox();
					jPanel1.add(jComboBox1, new GridBagConstraints(0, 0, 1, 1,
							0.0, 0.0, GridBagConstraints.CENTER,
							GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0,
							0));
					for (Catagory _catagory : catagorys) {
						jComboBox1.addItem(_catagory.getCatagoryName());
					}
					jComboBox1.addActionListener(this);
				}
			}
			pack();
			setSize(400, 300);
			Application.getInstance().getContext().getResourceMap(getClass())
					.injectComponents(getContentPane());
		} catch (Exception e) {
			// add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jComboBox1) {
			TableRowSorter<ItemsTableModel> sorter = new TableRowSorter<ItemsTableModel>(
					(ItemsTableModel) jTable1.getModel());
			jTable1.setRowSorter(sorter);
			RowFilter<ItemsTableModel, Object> rf = null;
			try {
				rf = RowFilter.regexFilter((String) jComboBox1
						.getSelectedItem(), 2);
			} catch (java.util.regex.PatternSyntaxException e) {
				return;
			}
			sorter.setRowFilter(rf);
		}
	}

}
