

import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import net.java.dev.designgridlayout.DesignGridLayout;

import com.alee.laf.WebLookAndFeel;

import javax.swing.plaf.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.*;
import javax.swing.*;
import javax.swing.border.*;

import com.alee.laf.*;

public class Sale_rcd_ord_rtn  extends JPanel{


	private  JTable myTable;
	private ArrayList<String> cols=new ArrayList<String>();
	private ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
	private TableRowSorter<TableModel> sorter;
	private  DefaultTableModel tm;
	//TableColumn tCol=new TableColumn(tModel.getColumnCount());
	private JButton btFilter = new JButton(" Search for... ");
	private int flag4del;
	private JTextField jtfFilter =new JTextField(20);
	private boolean isSearched=false;
	private JLabel jlbImg=new JLabel("Search for");
	private JButton backButton=new JButton("Back");
	Image SearchImage;
	private JSpinner rowSpin,pageSpin;
	JPanel TablePane=new JPanel(new BorderLayout());;
	private  JPanel btmPane;
	static JPanel topPane;
	private  StandardButton addRecord;
	private  JButton printPage=new JButton(" Print   ");
	Font font=new Font("SansSerif",Font.BOLD,15);
	//private JLabel spinLabel,goPageLabel;
	 ImageIcon t;ImageIcon e;
	Connection connection;

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
	if(flag!=1){ColVec.add("  ");
				ColVec.add("  ");}

	//Creating myTable

	createTable(dataVec,ColVec,flag);
	//design();

	}



public  void createTable(Vector data,final Vector col,final int flag){
	myTable=new JTable(){
		public Class<?> getColumnClass(int column){ return getValueAt(0,column).getClass();}
		public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
			Component c=super.prepareRenderer(renderer, row, column);
			if(flag==0)c.setBackground(row%2==0?new Color(178,186,187):new Color(133,146,158));
			else if(flag==1) c.setBackground(row%2==0?new Color(178,186,187):new Color(133,146,158));
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
			sorter=new TableRowSorter<TableModel>(tm);
			myTable.setRowSorter(sorter);
			//myTable.setShowHorizontalLines(false);
			//myTable.setShowVerticalLines(false);
			myTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			//if(flag==0)myTable.getTableHeader().setBackground(new Color(186,104,200));
			//else if(flag==1) myTable.getTableHeader().setBackground(new Color(149,117,205));
			//else myTable.getTableHeader().setBackground(new Color(255,216,1));

			JTableHeader header=myTable.getTableHeader();



			//new Color(206,147,216)
			if(flag==0) header.setForeground(Color.black);
			else if(flag==1)header.setForeground(Color.black);
				else header.setForeground(Color.black);
			 //new column is created

			//Aligning Center
			DefaultTableCellRenderer centerAlign=new DefaultTableCellRenderer()/*{

				public Component getTableCellRendererComponent(JTable table,Object v,boolean isSelected,boolean hasFocus,int row,int column){
					Component c=super.getTableCellRendererComponent(table, v, hasFocus, hasFocus, row, column);
					//((JComponent) c).setBorder(BorderFactory.createEmptyBorder());
					c.setForeground();
					 c.setForeground();
					 c.setForeground();
					return c;
				}
			}*/;
			centerAlign.setHorizontalAlignment(JLabel.CENTER);
			for(int a=0;a<myTable.getColumnCount()-2;a++)
				 myTable.getColumnModel().getColumn(a).setCellRenderer(centerAlign);
			//myTable.setEnabled(false);
			//myTable.setAutoCreateRowSorter(true);
			myTable.setAutoCreateColumnsFromModel(false);

			myTable.addMouseListener(new MouseAdapter(){

				public void mouseClicked(MouseEvent e){

					JDialog.setDefaultLookAndFeelDecorated(true);
				int col=((JTable)e.getSource()).getSelectedColumn();
				int row=((JTable)e.getSource()).getSelectedRow();

					if(col==myTable.getColumnCount()-2 && flag!=1){
					JDialog.setDefaultLookAndFeelDecorated(true);
					new add_editDialog(myTable,"Edit",cols, row);
					}
					else if(col==myTable.getColumnCount()-1&& flag!=1) {
					int yn=JOptionPane.showOptionDialog(null, "Are you sure to delete this record?", null, JOptionPane.ERROR_MESSAGE, JOptionPane.WARNING_MESSAGE, null, null, null);
					if(yn==JOptionPane.YES_OPTION && row!=-1){
					String pkValue0=(String)tm.getValueAt(row, 0);String pkValue1=(String)tm.getValueAt(row, 1);


						try {
								deleting(pkValue0,pkValue1);
								tm.removeRow(row);
							} catch (SQLException e1) {

								e1.printStackTrace();
				}}

				}
				}});
			addRecord.addActionListener( new ActionListener(){public void actionPerformed(ActionEvent e){
					JDialog.setDefaultLookAndFeelDecorated(true);
					new add_editDialog(myTable,"Add Record",cols,0);
					}
				});

			ImageIcon img2=new ImageIcon("fav/del.png");
			Image i2=img2.getImage();
			Image trashImg=i2.getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);

			ImageIcon img3=new ImageIcon("fav/EditRow.jpg");
			Image i3=img3.getImage();
			Image editImg=i3.getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
			e=new ImageIcon(editImg); t=new ImageIcon(trashImg);



			ImageIcon img1=new ImageIcon("fav/images(2).jpg");
			Image i1=img1.getImage();
			Image newImg1=i1.getScaledInstance(10,10,java.awt.Image.SCALE_SMOOTH);


			if(flag!=1) setImg2Table(myTable.getRowCount());

    printPage.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

    printPage.setFont(new Font("San Serif",Font.BOLD,12));
    //printPage.setIcon(new ImageIcon(newImg1));
    printPage.setFocusable(false);

    final ImageIcon searchImg=new ImageIcon("edit-find.png");
	Image Img=searchImg.getImage();
	SearchImage=Img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);

	//jlbImg.setIcon(new ImageIcon(SearchImage));



	final String str="Search"+"...";

	  jtfFilter.setText(str);
	  jtfFilter.addMouseListener(new MouseAdapter(){
		  public void mouseClicked(MouseEvent e){
			  jtfFilter.setText(null);
		  }
	  });
	  jtfFilter.addKeyListener(new KeyAdapter() {
	  public void keyPressed(KeyEvent e) {
		 if(e.getKeyCode()==KeyEvent.VK_ENTER){
			 String text = jtfFilter.getText();
			 if (text.trim().length() == 0)
			  sorter.setRowFilter(null);
			  else
			 	 sorter.setRowFilter(RowFilter.regexFilter(text));
			isSearched=true; jtfFilter.setText(str);
			if(isSearched) {backButton.setVisible(isSearched);

			}
	  }
	  }});
	  backButton.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==backButton){
			jtfFilter.setText(null);
			 String text = jtfFilter.getText();
			 if (text.trim().length() == 0)
			  sorter.setRowFilter(null);
			  else
			 	 sorter.setRowFilter(RowFilter.regexFilter(text));
			isSearched=false; jtfFilter.setText(str);
		}
		backButton.setVisible(isSearched);
	}
});
}

public Sale_rcd_ord_rtn(int flag) throws ClassNotFoundException, SQLException  {


		btmPane=new JPanel(new FlowLayout(FlowLayout.CENTER));
	topPane=new JPanel(new BorderLayout());
	backButton.setVisible(isSearched);
	btmPane.add(backButton);

	btmPane.add(jtfFilter);




	addRecord=new StandardButton("Add new record");
	addRecord.setForeground(Color.green);
	 addRecord.setFont(new Font("San Serif",Font.PLAIN,12));
	 ImageIcon img=new ImageIcon("fav/addtion.png");
		Image i=img.getImage();
		Image newImg=i.getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
	    addRecord.setIcon(new ImageIcon(newImg));
	    addRecord.setFocusable(true);

if(flag!=1)topPane.add(addRecord,BorderLayout.AFTER_LINE_ENDS);
	JLabel l1=new JLabel("Sale Order");l1.setOpaque(false);l1.setForeground(Color.black);l1.setFont(new Font("Rockwell",Font.BOLD+Font.ITALIC,25));
	JLabel l2=new JLabel("Sale Record");l2.setOpaque(false);l2.setForeground(Color.black);l2.setFont(new Font("Rockwell",Font.BOLD+Font.ITALIC,25));
	JLabel l3=new JLabel("Sale Return");l3.setOpaque(false);l3.setForeground(Color.black);l3.setFont(new Font("Rockwell",Font.BOLD+Font.ITALIC,25));
	if(flag==0) {topPane.add(l1,BorderLayout.BEFORE_LINE_BEGINS);topPane.setBackground(new Color( 202, 207, 210 )); }
	else if(flag==1) {topPane.add(l2,BorderLayout.BEFORE_LINE_BEGINS);topPane.setBackground(new Color( 202, 207, 210 ));}
	else {topPane.add(l3,BorderLayout.BEFORE_LINE_BEGINS);topPane.setBackground(new Color( 202, 207, 210 ));}


	//topPane.setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));

	switch(flag){
	case 0:connectDB("select * from sale_order",flag);flag4del=0;break;
	case 1:connectDB("select * from sale_record",flag);break;
	case 2:connectDB("select * from sale_return",flag);flag4del=2;

	}

	TablePane.add(new JScrollPane(myTable),BorderLayout.CENTER);//TablePane.add(new test());

	myTable.setShowGrid(false);
	JTableHeader header=myTable.getTableHeader();
	header.setFont(font);

	//topPane.setBackground(new Color(98,90,87));
	//new Color(255,216,1)
	btmPane.setBackground(Color.LIGHT_GRAY);
	myTable.setFont(new Font("San Serif",Font.PLAIN,12));
	myTable.setForeground( Color.BLACK);
	myTable.setRowMargin(0);
	myTable.setRowHeight(3*myTable.getRowHeight()/2);
	JPanel AllPane=new JPanel(new BorderLayout());





	AllPane.add(topPane,BorderLayout.NORTH);AllPane.add(TablePane,BorderLayout.CENTER);AllPane.add(btmPane,BorderLayout.SOUTH);

	//add(TablePane,BorderLayout.CENTER);
	//add(btmPane,BorderLayout.SOUTH);
	setLayout(new BorderLayout());
	add(AllPane,BorderLayout.CENTER);
	}

public void addTo(JTextField[] inText,boolean flag2,int row){
	Object[] data=new Object[inText.length];
	for(int i=0;i<inText.length;i++) data[i]=inText[i].getText();
	String pk0=(String)tm.getValueAt(row, 0);
	String pk=(String)tm.getValueAt(row, 1);
	try{
	if(flag2) for(int i=0;i<inText.length;i++) { setImg2Table(tm.getRowCount());deleting(pk0,pk);System.out.print("I got passed #1");
	tm.setValueAt(data[i], row,i);inserting(data);System.out.print("I got passed #2");}
	else{
	tm.addRow(data);myTable.repaint();
	setImg2Table(tm.getRowCount());
			inserting(data);
	}
	}		catch (Exception e) {

				e.printStackTrace();
			}

}

public  void inserting(Object[] data) throws SQLException{
	int y=0;
	String url ="";
	if(data.length==3 ||flag4del==2) {url+="insert into sale_return values( ?, ?, ? )";}
	else if(data.length==5||flag4del==0) {url+="insert into sale_order values( ?, ?, ?, ? )";}
	PreparedStatement ps1=connection.prepareStatement(url);
		for(int x=1;x<=data.length;x++) ps1.setString(x, (String)data[y++]);
		ps1.executeUpdate();

}
public void deleting(String value,String value2) throws SQLException{


	String url ="";
	if(flag4del==2) {url+="delete from sale_return where Product_id='"+value+"'  and Customer_id='"+value2+" ' ";}
	else if(flag4del==0) {url+="delete from sale_order where C_id='"+value+"'  and P_id='"+value2+"'";}
	PreparedStatement ps1=connection.prepareStatement(url);

	ps1.executeUpdate();

}

public void setImg2Table(int line){
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
		myTable.setValueAt(t,line,x[1]); myTable.setValueAt(e,line,x[0]);
	}
	c.getColumn(x[0]).setPreferredWidth(15);
	c.getColumn(x[1]).setPreferredWidth(15);
}
private class add_editDialog  extends JDialog{
	JTextField[] inText;JButton button;
	JPanel midPane;private JTable table4copy=new JTable();
	private int currentIndex=0;
	 ArrayList<String> col4Data=new ArrayList<String>();
	private int row2Edit,row2add;
	private final long serialVersionUID = 1L;

	public void setTF(JTable table,final ArrayList<String> column,final String title,int r){

		inText=new JTextField[column.size()];
		row2Edit=r;


	//table.setModel(model=new DefaultTableModel());
	midPane=new JPanel();
	DesignGridLayout layout=new DesignGridLayout(midPane);

	//TableModel tm=(DefaultTableModel)table.getModel();

	for(int i=0;i<column.size();i++){
		if(title=="Edit") layout.row().grid(new JLabel(col4Data.get(i))).add(inText[i]=new JTextField((String)table4copy.getValueAt(row2Edit, i)));
		else layout.row().grid(new JLabel(column.get(i))).add(inText[i]=new JTextField(""));

		layout.row().center().fill().add(new JSeparator());

	}
	for(int i=0;i<column.size();i++){
		inText[i].addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				int key=e.getKeyCode();

					switch(key){
					case KeyEvent.VK_UP:focusFor(currentIndex-1);break;
					case KeyEvent.VK_DOWN:focusFor(currentIndex+1);break;
					}

			}
		});
	}

	}

	public add_editDialog(JTable table,final String title, ArrayList<String> column,int r){

		super();col4Data=column;table4copy=table;
		setTF(table4copy, column,title, r);
		row2add=r;
		JPanel buttonPane=new JPanel();
		button=new JButton("Save");
		buttonPane.add(button);
		button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						addTo(inText,title=="Edit",row2add);
						if(title!="Edit"){

							if(e.getSource()==button) {for(int i=0;i<inText.length;i++){ inText[i].setText(null);};setTF(table4copy, col4Data,title, row2add);}
							else {setVisible(false);dispose();}
						}

						else {
						setVisible(false);dispose();}

					}

		});
		getContentPane().add(buttonPane, BorderLayout.PAGE_END);
		getContentPane().add(midPane, BorderLayout.PAGE_START);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(title);
		setBackground(new Color(244,226,135));
		//pack();
		this.setLocationRelativeTo(null);
		this.setSize(300, 500);
		setVisible(true);

	}
	private void focusFor(int index){
		if(index<0 || index>(inText.length-1)) return;
		inText[index].requestFocus();
		currentIndex=index;

	}

	public JRootPane createRootPane() {
		JRootPane rootPane = new JRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
		Action action = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				dispose();
			}
		};
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(stroke, "ESCAPE");
		rootPane.getActionMap().put("ESCAPE", action);
		return rootPane;
	}


}


}
