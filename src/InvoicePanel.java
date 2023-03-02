
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.util.*;
import java.util.Vector;
public class InvoicePanel extends JPanel{
	String[] name={"No","Items","Quantity","Price","Amount"};
	//Vector<Object> name=new Vector<Object>();

	//public Object[] colNames=(Object[])name;
	public Object[][] data={};
	private JLabel total=new JLabel("Total Price:");
	public JLabel tPrice=new JLabel(" ");

	public StandardButton print=new StandardButton("Print");
	//Home codeEntryPanel=new Home();

	public DefaultTableModel tableModel=new DefaultTableModel(data,name);
	public JTable myTable=new JTable(tableModel);
	public InvoicePanel(){
		//colNames.add((Object)name);
		//Collections.addAll(colNames, "No.","Items","Price","Amount");
		Border border=new EmptyBorder(-1,-1,-1,-1);
		TitledBorder title=new TitledBorder(border,"Invoice");
		setBorder(title);
		//setting font
		Font font=new Font("SansSerif",Font.BOLD,35);
		//tPrice.setFont(font);
		title.setTitleFont(font);

		//myTable=new JTable (data,colNames);
		//myTable.setModel(tableModel);
		//tableModel=new DefaultTableModel(data,colNames);
		myTable.setModel(tableModel);
		myTable.setShowGrid(false);
		myTable.setRowMargin(0);
		myTable.getColumnModel().getColumn(0).setPreferredWidth(20);
		myTable.getColumnModel().getColumn(1).setPreferredWidth(20);
		myTable.getColumnModel().getColumn(2).setPreferredWidth(20);
		myTable.getColumnModel().getColumn(3).setPreferredWidth(20);
		myTable.getColumnModel().getColumn(4).setPreferredWidth(10);
		myTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		myTable.getTableHeader().setReorderingAllowed(false);
		myTable.setEnabled(false);
		myTable.setBorder(border);

		JTableHeader header=myTable.getTableHeader();

		header.setBackground(Color.black);
		header.setForeground(Color.white);
		//panel for label
		JPanel labelPanel=new JPanel();
		labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
		labelPanel.add(total);
		labelPanel.add(tPrice);
		//Print Panel
		JPanel printPanel=new JPanel();
		printPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,5,5));
		printPanel.add(print);

		print.setRolloverEnabled(false);
		//panel for label and button
		JPanel printLabelPanel=new JPanel();
		printLabelPanel.setLayout(new BorderLayout());
		printLabelPanel.add(labelPanel,BorderLayout.CENTER);
		printLabelPanel.add(printPanel,BorderLayout.SOUTH);

		setLayout(new BorderLayout());

		add(new JScrollPane(myTable),BorderLayout.CENTER);
		add(printLabelPanel,BorderLayout.SOUTH);
		//setBorder(border);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame=new JFrame();
		//frame.setLayout(null);
		JPanel p=new InvoicePanel();
		//System.out.print(p.getHeight());
		frame.setSize(1000,1200);
		frame.add(p);

		frame.setTitle("JTable Testing");
		//frame.pack();
		//frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
