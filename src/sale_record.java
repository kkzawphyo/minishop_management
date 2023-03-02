import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import com.alee.laf.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import com.alee.laf.WebLookAndFeel;
import javax.swing.table.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;
public class sale_record extends JFrame implements	ListSelectionListener{

	static JFrame frame;

	static ImageIcon e;
	static ImageIcon f;
	static ImageIcon g;
	static int [] row1;
	private Font font1=new Font("Zawgyi-One",Font.BOLD,13);
	private Font font2=new Font("Zawgyi-One",Font.ROMAN_BASELINE,13);

	private JPanel panel = new JPanel();
	//private JPanel panel1=new JPanel();

	private StandardButton recycle=new StandardButton("delete");

	//private StandardButton jbtAddingProduct=new StandardButton("Adding New Product");
	//private JButton jbtAddExitPro=new JButton("ကုန္ပစၥၥည္းအေဟာင္းထည့္သြင္းၿခင္း");
	//private JButton jbtUpdateinfo=new  JButton("Updating information");
	private static StandardButton Search=new StandardButton("Search");
	private static JSearchTextField jtfSearch;

	static String[] columnNames={"No","Product ID","Customer ID","Quantity","Date","  "};
	static Object[][] data;
	static NewTableModel tableModel ;
	static JTable sale_Record;
	//private TableRowSorter<TableModel> sorter;
	private editSaleRecord   EditRecord;
	private static SureDeleteRecord  SureDel;
	private viewRecordByDate viewRecord;

	public sale_record() throws ClassNotFoundException, SQLException
	{
		setLayout(new BorderLayout());

		ImageIcon img3=new ImageIcon("fav/editing.png");
		Image i3=img3.getImage();
		Image editImg=i3.getScaledInstance(20,20,Image.SCALE_SMOOTH);
		e=new ImageIcon(editImg);

		ImageIcon img=new ImageIcon("fav/recycle3.png");
		Image i=img.getImage();
		Image newImg=i.getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
		f=new ImageIcon(newImg);

		ImageIcon img1=new ImageIcon("fav/addtion.png");
		Image i1=img1.getImage();
		Image addImg=i1.getScaledInstance(15,15,Image.SCALE_SMOOTH);
		g=new ImageIcon(addImg);

		//jbtAddingProduct.setFont(font1);
		//jbtAddingProduct.setForeground(Color.white);
		//jbtAddingProduct.setBackground(new Color( 46, 64, 83));

		//Search.setFont(font1);
		//Search.setForeground(Color.white);
		//Search.setBackground(new Color( 46, 64, 83));

		//jbtAddingProduct.setIcon(t);
		//jbtAddExitPro.setFont(font2);

		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));

		jtfSearch=new JSearchTextField("search","Enter Product name or ID","Enter Procuct name or ID".length()-8);
		jtfSearch.setPreferredSize(new Dimension(0,27));

		recycle.setIcon(f);
		panel.add(jtfSearch);
		panel.add(Search);
		panel.add(recycle);

		panel.setBackground(new Color( 202, 207, 210 ));
		//panel.add(jbtAddExitPro);
		//panel.add(jbtUpdateinfo);
		//panel1.add(test);
		//panel1.setBackground(new Color( 202, 207, 210 ));
		//add(panel1,BorderLayout.NORTH);
		add(panel,BorderLayout.NORTH);

		data=tabledata();
		tableModel=new NewTableModel(data,columnNames);


		sale_Record=new JTable(){
			public Class<?> getColumnClass(int column){ return getValueAt(0,column).	getClass();}
			public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
				Component c=super.prepareRenderer(renderer, row, column);
				c.setBackground(row%2==0?new Color(215, 189, 226):new Color(230, 176, 170));
				if(isRowSelected(row)) {c.setForeground(new Color(248, 249, 249));c.setBackground(new Color(33, 47, 61));} else c.setForeground(Color.BLACK);
				return c;
			}
		};

		ListSelectionModel selectionModel = sale_Record.getSelectionModel();
		selectionModel.addListSelectionListener( this );
		sale_Record.setModel(tableModel);
		sale_Record.setRowMargin(0);
		sale_Record.setRowHeight(3*sale_Record.getRowHeight()/2);
		sale_Record.getTableHeader().setFont(font1);
		sale_Record.getTableHeader().setReorderingAllowed(false);
		sale_Record.setShowGrid(false);
		//sorter=new TableRowSorter<TableModel>(product1.getModel());
		//product1.setRowSorter(sorter);
		sale_Record.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		setImgToTable(sale_Record.getRowCount());
		sale_Record.getTableHeader().setForeground(new Color( 46, 64, 83 ));
		//product1.setEnabled(false);
		add(new JScrollPane(sale_Record),BorderLayout.CENTER);

		sale_Record.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e){
			//JDialog.setDefaultLookAndFeelDecorated(true);



			if(((JTable)e.getSource()).getSelectedColumn()==sale_Record.getColumnCount()-1){
				int row=((JTable)e.getSource()).getSelectedRow();
				EditRecord=new editSaleRecord(frame,true,row);
				EditRecord.setVisible(true);
			}
			}
		});

		recycle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				/*SureDelFrame=new SureDelete(frame,true);
				SureDelFrame.setVisible(true);
					if(SureDelete.getFlag()==1)
					{
						deleteFrame=new DeletProduct(frame,true);
						deleteFrame.setVisible(true);
					}	*/
				try {
					delete(row1);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		recycle.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					try {
						delete(row1);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		Search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				sale_Record.clearSelection();
				//searchingID();
				if(isValidDate(jtfSearch.getText()))
				{

				}
				else
					JOptionPane.showMessageDialog(null,"Your input is not valid", "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		Search.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					sale_Record.clearSelection();
					//searchingID();
				}
			}
		});

		jtfSearch.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					sale_Record.clearSelection();
					//searchingID();
				}
			}
		});

	}

	private boolean isValidDate(String input){
		//return input.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})");
		Date date=null;
		try{
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
			date=sdf.parse(input);
			if(!input.equals(sdf.format(date)))
			{
				date=null;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		if(date==null)
			return true;
		else
			return false;
	}

	  public static void delete(int[] r) throws SQLException , ClassNotFoundException{

			if(sale_Record.getSelectedRows().length==0)
				JOptionPane.showMessageDialog(null, "First,select product you want to delete"," ",JOptionPane.WARNING_MESSAGE);
			else
			{
				SureDel=new SureDeleteRecord(frame,true);
				SureDel.setVisible(true);
				if(SureDel.getFlag()==1){
					String id[]=new String[r.length];
					String no[]=new String[r.length];
					for(int i=0 ; i<r.length ; i++){
						id[i]=(String)sale_Record.getValueAt(r[i], 1);
						no[i]=(String)sale_Record.getValueAt(r[i],0);
					}
					tableModel.removeRow(r);
				//	System.out.print("row length = "+row.length);
					//System.out.println("Driver loaded");

					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/shop_managment_system","root","root");
					//System.out.println("Database connected");

					Statement statement=connection.createStatement();
					for(int i=0 ; i<id.length ; i++){
						statement.execute("delete from sale_record where No='"+no[i]+"' and Product_id='"+id[i]+"'");
					}

					connection.close();
				}


			}



	  }
	/*public static void searchingID(){
		boolean flag=false;
		String id=toLowerCases(jtfSearch.getText());
		//System.out.print(toLowerCases(id));
		int row=sale_Record.getRowCount();
		for(int i=0 ; i<row ; i++){
			if(id.equals(toLowerCases(sale_Record.getValueAt(i, 1))) || id.equals(toLowerCases(sale_Record.getValueAt(i, 0)))){
				sale_Record.setRowSelectionInterval(i, i);
				sale_Record.scrollRectToVisible(new Rectangle(sale_Record.getCellRect(i, 0, true)));
				jtfSearch.setText(null);
				flag=true; break;
			}
		}
		if(flag==false){
			JOptionPane.showMessageDialog(null, "Product does not exist!", "Product Status", JOptionPane.ERROR_MESSAGE);
		}
	}*/

	public static String toLowerCases(Object s){
		String s1=(String)s;
		String s2="";
		for(int i=0 ; i<s1.length() ; i++){
			s2+=Character.toLowerCase(s1.charAt(i));
		}
		return s2;
	}

	public static Object[][] tabledata()throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		//System.out.println("Driver loaded");

		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/shop_managment_system","root","root");
		//System.out.println("Database connected");

		Statement statement=connection.createStatement();

		ResultSet resultSet=statement.executeQuery("select * from sale_record");

		resultSet.last();
		data=new Object[resultSet.getRow()][5];
		resultSet.first();
		resultSet.previous();

		int row=-1;
		while(resultSet.next()){
			++row;
		data[row][0]=resultSet.getString(1);//System.out.print(data[row][0]+"\t");
		data[row][1]=resultSet.getString(2);//System.out.print(data[row][1]+"\t");
		data[row][2]=resultSet.getString(3);//System.out.print(data[row][2]+"\t");
		data[row][3]=resultSet.getString(4);//System.out.print(data[row][3]+"\t");
		data[row][4]=resultSet.getString(5);//System.out.print(data[row][4]+"\t");
		//data[row][5]=Integer.parseInt(resultSet.getString(6));//System.out.println(data[row][5]);
		}


		connection.close();
		return data;
	}

	public static void setImgToTable(int line){

		int[] x=new int[2];int y=0;
		TableColumnModel c=sale_Record.getColumnModel();
		for(int i=0;i<sale_Record.getColumnCount();++i) {
				if (sale_Record.getColumnName(i).equals("  "))
						x[y++]=i;
		}
		if(line==sale_Record.getModel().getRowCount()){
		for(int i=0;i<1;++i) {
				for(int j=0;j<line;++j){
					sale_Record.setValueAt(e, j,x[i] );}
		}
		}
		else {
			sale_Record.setValueAt(e,line,x[1]);
		}
		c.getColumn(x[0]).setPreferredWidth(30);
		c.getColumn(x[1]).setPreferredWidth(30);
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//JFrame.setDefaultLookAndFeelDecorated(true);
		frame=new sale_record();
		Dimension d=new Dimension(500,200);
		frame.setMinimumSize(d);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
	@Override
	public void valueChanged(ListSelectionEvent event)
	{
		if( event.getSource() ==sale_Record.getSelectionModel() && event.getFirstIndex() >= 0 )
			{
					TableModel model = (TableModel)sale_Record.getModel();
					row1=sale_Record.getSelectedRows();
			}
	}
}
