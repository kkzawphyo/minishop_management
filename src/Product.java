import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import com.alee.laf.*;
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
public class Product extends JPanel implements	ListSelectionListener{

	static JFrame frame;

	static ImageIcon e;
	static ImageIcon f;
	static ImageIcon g;
	static int [] row1;
	private Font font1=new Font("Times New Roman",Font.BOLD,13);
	private Font font2=new Font("Times New Roman",Font.ROMAN_BASELINE,13);

	private JPanel panel = new JPanel();
	//private JPanel panel1=new JPanel();

	private StandardButton recycle=new StandardButton("delete");

	private StandardButton jbtAddingProduct=new StandardButton("Adding New Product");
	//private JButton jbtAddExitPro=new JButton("á€€á€¯á€”á€¹á€•á€…á�¥áŠá€¹á€¸á€¡á€±á€Ÿá€¬á€„á€¹á€¸á€‘á€Šá€¹á€·á€žá€¼á€„á€¹á€¸á�¿á€�á€„á€¹á€¸");
	//private JButton jbtUpdateinfo=new  JButton("Updating information");
	private static StandardButton Search=new StandardButton("Search");
	private static JSearchTextField jtfSearch;

	static String[] columnNames={"Product Name","Product ID","Normal Price","VIP Price","Quantity","  "};
	static Object[][] data;
	static NewTableModel tableModel ;
	static JTable product1;
	//private TableRowSorter<TableModel> sorter;

	private AddingProduct addFrame;
	private EditProduct editFrame;
	private static SureDelete  SureDelFrame;

	public Product() throws ClassNotFoundException, SQLException
	{
		setLayout(new BorderLayout());

		ImageIcon img3=new ImageIcon("fav/edit2.png");
		Image i3=img3.getImage();
		Image editImg=i3.getScaledInstance(15,15,Image.SCALE_SMOOTH);
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
		jbtAddingProduct.setIcon(g);

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
		panel.add(jbtAddingProduct);
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


		product1=new JTable(){
			public Class<?> getColumnClass(int column){ return getValueAt(0,column).	getClass();}
			public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
				Component c=super.prepareRenderer(renderer, row, column);
				c.setBackground(row%2==0?new Color(178,186,187):new Color(133,146,158));
				if(isRowSelected(row)) {c.setForeground(new Color(248, 249, 249));c.setBackground(new Color(175, 122, 197));} else c.setForeground(Color.BLACK);
				return c;
			}
		};

		ListSelectionModel selectionModel = product1.getSelectionModel();
		selectionModel.addListSelectionListener( this );
		product1.setModel(tableModel);
		product1.setRowMargin(0);
		product1.setRowHeight(3*product1.getRowHeight()/2);
		product1.getTableHeader().setFont(font1);
		product1.getTableHeader().setReorderingAllowed(false);
		product1.setShowGrid(false);
		//sorter=new TableRowSorter<TableModel>(product1.getModel());
		//product1.setRowSorter(sorter);
		product1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		setImgToTable(product1.getRowCount());
		product1.getTableHeader().setForeground(new Color( 46, 64, 83 ));
		//product1.setEnabled(false);
		add(new JScrollPane(product1),BorderLayout.CENTER);

		product1.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent e){
			//JDialog.setDefaultLookAndFeelDecorated(true);



			if(((JTable)e.getSource()).getSelectedColumn()==product1.getColumnCount()-1){
				int row=((JTable)e.getSource()).getSelectedRow();
				editFrame=new EditProduct(frame,true,row);
				editFrame.setVisible(true);
			}
			}
		});

		jbtAddingProduct.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//JDialog.setDefaultLookAndFeelDecorated(true);
				addFrame=new AddingProduct(frame,true);
				addFrame.setVisible(true);
			}
		});

		jbtAddingProduct.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					addFrame=new AddingProduct(frame,true);
					addFrame.setVisible(true);
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
				product1.clearSelection();
				searchingID();
			}
		});

		Search.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					product1.clearSelection();
					searchingID();
				}
			}
		});

		jtfSearch.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					product1.clearSelection();
					searchingID();
				}
			}
		});

	}
	  public static void delete(int[] r) throws SQLException , ClassNotFoundException{

			if(product1.getSelectedRows().length==0)
				JOptionPane.showMessageDialog(null, "First,select product you want to delete"," ",JOptionPane.WARNING_MESSAGE);
			else
			{
				SureDelFrame=new SureDelete(frame,true);
				SureDelFrame.setVisible(true);
				if(SureDelete.getFlag()==1){
					String id[]=new String[r.length];
					for(int i=0 ; i<r.length ; i++){
						id[i]=(String)product1.getValueAt(r[i], 1);
					}
					tableModel.removeRow(r);
				//	System.out.print("row length = "+row.length);
					//System.out.println("Driver loaded");

					Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/shop_managment_system","root","root");
					//System.out.println("Database connected");

					Statement statement=connection.createStatement();
					for(int i=0 ; i<id.length ; i++){
						statement.execute("delete from product where Product_id='"+id[i]+"'");
					}

					connection.close();
				}


			}



	  }
	public static void searchingID(){
		boolean flag=true;
		String id=toLowerCases(jtfSearch.getText());
		//System.out.print(toLowerCases(id));
		int row=product1.getRowCount();
		for(int i=0 ; i<row ; i++){
			if(id.equals(toLowerCases(product1.getValueAt(i, 1))) || id.equals(toLowerCases(product1.getValueAt(i, 0)))){
				product1.setRowSelectionInterval(i, i);
				product1.scrollRectToVisible(new Rectangle(product1.getCellRect(i, 0, true)));
				jtfSearch.setText(null);
				flag=false;
			}
		}
		if(flag==true){
			JOptionPane.showMessageDialog(null, "Product does not exist!", "Product Status", JOptionPane.ERROR_MESSAGE);
		}
	}

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

		ResultSet resultSet=statement.executeQuery("select * from Product");

		resultSet.last();
		data=new Object[resultSet.getRow()][5];
		resultSet.first();
		resultSet.previous();

		int row=-1;
		while(resultSet.next()){
			++row;
		data[row][0]=(String)resultSet.getString(1);//System.out.print(data[row][0]+"\t");
		data[row][1]=(String)resultSet.getString(2);//System.out.print(data[row][1]+"\t");
		data[row][2]=Integer.parseInt(resultSet.getString(3));//System.out.print(data[row][2]+"\t");
		data[row][3]=Integer.parseInt(resultSet.getString(4));//System.out.print(data[row][3]+"\t");
		data[row][4]=Integer.parseInt(resultSet.getString(5));//System.out.print(data[row][4]+"\t");
		//data[row][5]=Integer.parseInt(resultSet.getString(6));//System.out.println(data[row][5]);
		}


		connection.close();
		return data;
	}

	public static void setImgToTable(int line){

		int[] x=new int[2];int y=0;
		TableColumnModel c=product1.getColumnModel();
		for(int i=0;i<product1.getColumnCount();++i) {
				if (product1.getColumnName(i).equals("  "))
						x[y++]=i;
		}
		if(line==product1.getModel().getRowCount()){
		for(int i=0;i<1;++i) {
				for(int j=0;j<line;++j){
			 product1.setValueAt(e, j,x[i] );}
		}
		}
		else {
			product1.setValueAt(e,line,x[1]);
		}
		c.getColumn(x[0]).setPreferredWidth(30);
		c.getColumn(x[1]).setPreferredWidth(30);
	}

/*	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//JFrame.setDefaultLookAndFeelDecorated(true);
		frame=new Product();
		Dimension d=new Dimension(500,200);
		frame.setMinimumSize(d);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}*/
	@Override
	public void valueChanged(ListSelectionEvent event)
	{
		if( event.getSource() ==product1.getSelectionModel() && event.getFirstIndex() >= 0 )
			{
					TableModel model = (TableModel)product1.getModel();
					row1=product1.getSelectedRows();
			}
	}
}