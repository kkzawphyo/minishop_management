import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import com.alee.laf.WebLookAndFeel;
import javax.swing.plaf.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.*;
import javax.swing.*;
import javax.swing.border.*;
import com.alee.laf.*;

public class LoadDB2Table extends JFrame {

	static JFrame myF ;
	public static JTable myTable;
	ArrayList<String> cols=new ArrayList<String>();
	private ArrayList<ArrayList<String>> data=new ArrayList<>();
	private TableRowSorter<TableModel> sorter=new TableRowSorter<TableModel>();
	private static DefaultTableModel tm;
	//TableColumn tCol=new TableColumn(tModel.getColumnCount());
	private JButton btFilter = new JButton(" Search for... ");
	private JTextField jtfFilter = new JTextField(25);
	private JSpinner rowSpin,pageSpin;
	JPanel TablePane;
	static JPanel btmPane;
	static JPanel topPane;
	private static JButton addRecord=new JButton(" Add new record   ");
	private static JButton printPage=new JButton(" Print   ");
	private JLabel spinLabel,goPageLabel;
	static ImageIcon t;static ImageIcon e;
	static Connection connection;

public void connectDB(String url) throws ClassNotFoundException, SQLException  {

	Class.forName("com.mysql.jdbc.Driver");
	connection=DriverManager.getConnection("jdbc:mysql://localhost/shop_managment_system","root","root");
	Statement s=connection.createStatement();
	ResultSet rs=s.executeQuery(url);
	ResultSetMetaData rsMeta=rs.getMetaData();
	for (int i = 1; i <= rsMeta.getColumnCount(); i++)
			cols.add(rs.getMetaData().getColumnName(i));

	while(rs.next()){
		ArrayList<String> row=new ArrayList<String>();
			for(int i=1;i <= rsMeta.getColumnCount(); i++)
			row.add(rs.getString(i));
	       	data.add(row);
	}
	Vector ColVec=new Vector();
	Vector dataVec=new Vector();

	for(int i=0;i<data.size();i++){
				ArrayList subArray=(ArrayList)data.get(i);
				Vector subVector=new Vector();
				for(int j=0;j<subArray.size();j++)
				subVector.add(subArray.get(j));
				dataVec.add(subVector);
	}
	for(int i=0;i<cols.size();i++)  ColVec.add(cols.get(i));
	ColVec.add("  ");
	ColVec.add("  ");

	//Creating myTable

	createTable(dataVec,ColVec);
	design();
	connection.close();
	}
public static void deleting(String roll_no) throws SQLException{
/*	String sql="";
	switch(tableName){
	case "Student":sql+="delete from student where roll_no='"+roll_no+"'";break;
	}*/
	Connection connt = null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connt=DriverManager.getConnection("jdbc:mysql://localhost/shop_managment_system","root","root");

		PreparedStatement ps=connt.prepareStatement("delete from product where Product_id='"+roll_no+"'");
		ps.executeUpdate();
	} catch (Exception e) {

		e.printStackTrace();
	}
	connt.close();
}

public void createTable(Vector data,final Vector col){
	myTable=new JTable(){
		public Class<?> getColumnClass(int column){ return getValueAt(0,column).getClass();}
		public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
			Component c=super.prepareRenderer(renderer, row, column);
			c.setBackground(row%2==0?new Color(255,245,195):new Color(244,226,135));
			if(isRowSelected(row)) c.setForeground(Color.RED); else c.setForeground(Color.BLACK);
			//c.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			return c;
		}

	};

	tm=new DefaultTableModel(data,col){
		//public class
		public boolean isCellEditable(int row,int column){return false;}
	};
			myTable.setModel(tm);
			myTable.getTableHeader().setReorderingAllowed(false);

			//myTable.setShowHorizontalLines(false);
			//myTable.setShowVerticalLines(false);
			myTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			myTable.getTableHeader().setBackground(new Color(255,216,1));

			ImageIcon img2=new ImageIcon("fav/del.png");
			Image i2=img2.getImage();
			Image trashImg=i2.getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
			t=new ImageIcon(trashImg);

			ImageIcon img3=new ImageIcon("fav/EditRow.jpg");
			Image i3=img3.getImage();
			Image editImg=i3.getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
			e=new ImageIcon(editImg);


			myTable.addMouseListener(new MouseAdapter(){

				public void mouseClicked(MouseEvent e){JDialog.setDefaultLookAndFeelDecorated(true);
				int col=((JTable)e.getSource()).getSelectedColumn();
				int row=((JTable)e.getSource()).getSelectedRow();

				if(col==myTable.getColumnCount()-1){
					String roll_no=(String)tm.getValueAt(row, 1);
					tm.removeRow(row);

				try {
					deleting(roll_no);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}

				//tm.fireTableRowsDeleted(row, row);
				//int yn=JOptionPane.showOptionDialog(rootPane, "Are you sure to delete this record?", null, JOptionPane.ERROR_MESSAGE, JOptionPane.WARNING_MESSAGE, null, null, null);
				//if(yn==1 && row!=-1) {tm.removeRow(row);tm.fireTableRowsDeleted(row, row);}
				}
				else if(col==myTable.getColumnCount()-2){
					JDialog.setDefaultLookAndFeelDecorated(true);
					new myDialog(myF,myTable,"Edit",cols, row);
				}
				}
			});

			 //new column is created
			setImg2Table(myTable.getRowCount());
			//Aligning Center
			DefaultTableCellRenderer centerAlign=new DefaultTableCellRenderer();/*{
				public Component getTableCellRendererComponent(JTable table,Object v,boolean isSelected,boolean hasFocus,int row,int column){
					Component c=super.getTableCellRendererComponent(table, v, hasFocus, hasFocus, row, column);
					((JComponent) c).setBorder(BorderFactory.createEmptyBorder());
					return c;
				}
			}*/
			centerAlign.setHorizontalAlignment(JLabel.CENTER);
			for(int a=0;a<myTable.getColumnCount()-2;a++)
				 myTable.getColumnModel().getColumn(a).setCellRenderer(centerAlign);
			//myTable.setEnabled(false);
			myTable.setAutoCreateRowSorter(true);
			myTable.setAutoCreateColumnsFromModel(false);
			}



public void design(){

	pageSpin=new JSpinner(new SpinnerNumberModel(1,1,100,1));
	goPageLabel=new JLabel("Go to page:");
	rowSpin=new JSpinner(new SpinnerNumberModel(10,1,30,2));
	spinLabel=new JLabel("Row count:");

	ImageIcon img=new ImageIcon("fav/addRecord.jpg");
	Image i=img.getImage();
	Image newImg=i.getScaledInstance(10,10,java.awt.Image.SCALE_SMOOTH);

	ImageIcon img1=new ImageIcon("fav/images(2).jpg");
	Image i1=img1.getImage();
	Image newImg1=i1.getScaledInstance(10,10,java.awt.Image.SCALE_SMOOTH);

    addRecord.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

    addRecord.setFont(new Font("San Serif",Font.BOLD,12));
    addRecord.setIcon(new ImageIcon(newImg));
    addRecord.setFocusable(true);

   addRecord.addActionListener( new ActionListener(){


		public void actionPerformed(ActionEvent e){
			JDialog.setDefaultLookAndFeelDecorated(true);
		new myDialog(myF,myTable,"Add Record",cols,0);
			}
		});

    printPage.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

    printPage.setFont(new Font("San Serif",Font.BOLD,12));
    printPage.setIcon(new ImageIcon(newImg1));
    printPage.setFocusable(false);

	btFilter.addActionListener(new java.awt.event.ActionListener() {
		  public void actionPerformed(java.awt.event.ActionEvent e) {
			  String text = jtfFilter.getText();
				 if (text.trim().length() == 0)
				  sorter.setRowFilter(null);
				  else
				 	 sorter.setRowFilter(RowFilter.regexFilter(text));
		  }
	});
}

public LoadDB2Table() throws ClassNotFoundException, SQLException  {
	topPane=new JPanel(new FlowLayout(FlowLayout.RIGHT));
	TablePane=new JPanel(new BorderLayout());
	btmPane=new JPanel(new FlowLayout(FlowLayout.CENTER));


	topPane.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));


	topPane.add(addRecord);
	topPane.add(printPage);

	connectDB("select * from product");
	TablePane.add(new JScrollPane(myTable),BorderLayout.CENTER);//TablePane.add(new test());

	btmPane.add(goPageLabel);
	btmPane.add(pageSpin);
	btmPane.add(spinLabel);
	btmPane.add(rowSpin);
	btmPane.add(jtfFilter);
	btmPane.add(btFilter);
	add(topPane,BorderLayout.NORTH);
	add(TablePane,BorderLayout.CENTER);
	add(btmPane,BorderLayout.SOUTH);
	}

public static void main(String[] args)throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException  {
	JFrame.setDefaultLookAndFeelDecorated(true);
	myF = new LoadDB2Table();
	UIManager.setLookAndFeel(WebLookAndFeel.class.getCanonicalName());
	SwingUtilities.updateComponentTreeUI(myF);
	myTable.setShowGrid(false);
	addRecord.setBackground(new Color(255,216,1));
	printPage.setBackground(new Color(255,216,1));
	topPane.setBackground(new Color(98,90,87));
	btmPane.setBackground(new Color(255,216,1));
	myTable.setFont(new Font("San Serif",Font.PLAIN,12));
	myTable.setForeground( Color.BLACK);
	myTable.setRowMargin(0);
	myTable.setRowHeight(3*myTable.getRowHeight()/2);
	myF.setSize(1000,500);
	//myF.setUndecorated(true);
	//myF.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
	myF.setVisible(true);

}

public static  void addTo(JTextField[] inText,boolean flag,int row){
	Object[] data=new Object[inText.length];
	for(int i=0;i<inText.length;i++) data[i]=inText[i].getText();
	String roll_no=(String)tm.getValueAt(row, 2);
	try{
	if(flag) for(int i=0;i<inText.length;i++) { tm.setValueAt(data[i], row,i);setImg2Table(tm.getRowCount());

		deleting(roll_no);

	}
	else {tm.addRow(data);myTable.repaint();
	setImg2Table(tm.getRowCount());}
			inserting(data);
	} catch (SQLException e) {
		e.printStackTrace();
	}

}

public static void inserting(Object[] data) throws SQLException{

	Connection connt = null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connt=DriverManager.getConnection("jdbc:mysql://localhost/shop_managment_system","root","root");

		PreparedStatement ps=connt.prepareStatement("insert into shop_managment_system values( ?, ?, ?, ?, ?, ? )");
		int y=0;
		for(int x=1;x<=data.length;x++) ps.setString(x, (String)data[y++]);
		ps.executeUpdate();
	} catch (Exception e) {

		e.printStackTrace();
	}
	connt.close();
}
public static void setImg2Table(int line){
	int[] x=new int[2];int y=0;
	TableColumnModel c=myTable.getColumnModel();
	for(int i=0;i<myTable.getColumnCount();++i) {
			if (myTable.getColumnName(i).equals("  "))
					x[y++]=i;
	}
	if(line==myTable.getModel().getRowCount()){
	for(int i=0;i<2;++i) {
			for(int j=0;j<line;++j){
			if(i>0)myTable.setValueAt(t, j,x[i] ); else myTable.setValueAt(e, j,x[i] );}
	}
	}
	else {
		myTable.setValueAt(e,line,x[1]); myTable.setValueAt(t,line,x[0]);
	}
	c.getColumn(x[0]).setPreferredWidth(30);
	c.getColumn(x[1]).setPreferredWidth(30);
}




}

