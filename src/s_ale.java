

import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
//import com.alee.laf.WebLookAndFeel;
import javax.swing.plaf.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.*;

import javax.swing.*;
import javax.swing.border.*;
//import com.alee.laf.*;

public class s_ale extends JPanel{

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
	Font font=new Font("SansSerif",Font.BOLD,15);
	//private static JButton addRecord=new JButton(" Add new record   ");
	//private static JButton printPage=new JButton(" Print   ");
	//private JLabel spinLabel,goPageLabel;
	//static ImageIcon t;static ImageIcon e;
	static Connection connection;

public void connectDB(String url,int flag) throws ClassNotFoundException, SQLException  {

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
	//ColVec.add("  ");
	//ColVec.add("  ");

	//Creating myTable

	createTable(dataVec,ColVec,flag);
	//design();
	connection.close();
	}/*
/*
public static void deleting(String roll_no) throws SQLException{
/*	String sql="";
	switch(tableName){
	case "Student":sql+="delete from student where roll_no='"+roll_no+"'";break;
	}*/
	//Connection connt = null;
	//try {
		//Class.forName("com.mysql.jdbc.Driver");
		//connt=DriverManager.getConnection("jdbc:mysql://localhost/shop_managment_system","root","root");

		//PreparedStatement ps=connt.prepareStatement("delete from student where roll_no='"+roll_no+"'");
		//ps.executeUpdate();
	//} catch (Exception e) {

	//	e.printStackTrace();
	//}
	//connt.close();
//}*/

public void createTable(Vector data,final Vector col,final int flag){
	myTable=new JTable(){
		public Class<?> getColumnClass(int column){ return getValueAt(0,column).getClass();}
		public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
			Component c=super.prepareRenderer(renderer, row, column);
			if(flag==0)c.setBackground(row%2==0?new Color(225,190,231):new Color(206,147,216));
			else if(flag==1) c.setBackground(row%2==0?new Color(209,196,233):new Color(179,157,219));
			else c.setBackground(row%2==0?new Color(255,245,195):new Color(244,226,135));
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
			//if(flag==0)myTable.getTableHeader().setBackground(new Color(186,104,200));
			//else if(flag==1) myTable.getTableHeader().setBackground(new Color(149,117,205));
			//else myTable.getTableHeader().setBackground(new Color(255,216,1));

//}

			 //new column is created
			//setImg2Table(myTable.getRowCount());
			//Aligning Center
			DefaultTableCellRenderer centerAlign=new DefaultTableCellRenderer()/*{
				public Component getTableCellRendererComponent(JTable table,Object v,boolean isSelected,boolean hasFocus,int row,int column){
					Component c=super.getTableCellRendererComponent(table, v, hasFocus, hasFocus, row, column);
					((JComponent) c).setBorder(BorderFactory.createEmptyBorder());
					return c;
				}
			}*/;
			centerAlign.setHorizontalAlignment(JLabel.CENTER);
			for(int a=0;a<myTable.getColumnCount()-2;a++)
				 myTable.getColumnModel().getColumn(a).setCellRenderer(centerAlign);
			//myTable.setEnabled(false);
			myTable.setAutoCreateRowSorter(true);
			myTable.setAutoCreateColumnsFromModel(false);
			}



/*

   addRecord.addActionListener( new ActionListener(){


		public void actionPerformed(ActionEvent e){
			JDialog.setDefaultLookAndFeelDecorated(true);
		new Add_Edit(myF,myTable,"Add Record",cols,0);
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
*/
public s_ale(int flag) throws ClassNotFoundException, SQLException  {
	topPane=new JPanel(new FlowLayout(FlowLayout.RIGHT));
	TablePane=new JPanel(new BorderLayout());
	btmPane=new JPanel(new FlowLayout(FlowLayout.CENTER));


	topPane.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));


	switch(flag){
	case 0:connectDB("select * from sale_order",flag);break;
	case 1:connectDB("select * from sale_record",flag);break;
	case 2:connectDB("select * from sale_return",flag);

	}

	TablePane.add(new JScrollPane(myTable),BorderLayout.CENTER);//TablePane.add(new test());

	myTable.setShowGrid(false);
	JTableHeader header=myTable.getTableHeader();
	header.setFont(font);


	//topPane.setBackground(new Color(98,90,87));
	//btmPane.setBackground(new Color(255,216,1));
	myTable.setFont(new Font("San Serif",Font.PLAIN,12));
	myTable.setForeground( Color.BLACK);
	myTable.setRowMargin(0);
	myTable.setRowHeight(3*myTable.getRowHeight()/2);
	JPanel AllPane=new JPanel(new BorderLayout());
	AllPane.add(topPane,BorderLayout.NORTH);AllPane.add(TablePane,BorderLayout.CENTER);AllPane.add(btmPane,BorderLayout.SOUTH);
	//add(topPane,BorderLayout.NORTH);
	//add(TablePane,BorderLayout.CENTER);
	//add(btmPane,BorderLayout.SOUTH);
	setLayout(new BorderLayout());
	add(AllPane,BorderLayout.CENTER);
	}

}

