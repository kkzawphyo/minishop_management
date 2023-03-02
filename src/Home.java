import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
public class Home extends JFrame{
	private JMenuBar menu=new JMenuBar();
	private JMenu file=new JMenu("File");
	private JMenu help=new JMenu("Help");
	private JMenu about=new JMenu("About");

	private JPanel titleAndToolPanel=new JPanel();
	private JPanel entryViewInvoicePanel=new JPanel();
	private JPanel tabbedPanePanel=new JPanel();
	private JTabbedPane tabbedPane=new JTabbedPane();
	public InitialPanel initialPanel=new InitialPanel();
	private CodeEntryPanel codeEntryPanel=new CodeEntryPanel();
	private ToolPanel toolPanel=new ToolPanel();
	private ArrayList<String> dataCode=new ArrayList<String>();
	private ArrayList<Integer> quantity=new ArrayList<Integer>();
	private InvoicePanel invoicePanel=new InvoicePanel();
	//private JMenuBar menuBar=new JMenuBar();

	//Database
	private Statement stmt;

	//private JPanel panel1=new JPanel();

	public Home(){
		//Creating Menu
		//menu.setLayout(new GridLayout(0,1));
		//menu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.black));
		menu.add(file);
		menu.add(help);
		menu.add(about);
		setJMenuBar(menu);

		//setting layout to panel
		titleAndToolPanel.setLayout(new BorderLayout());

		//Initialize the applet
		initializeDB();

		//UIManager.put("TabbedPane.borderHightlingtColor", Color.cyan);
		tabbedPane.setUI(new BasicTabbedPaneUI(){
			protected void installDefaults(){
				super.installDefaults();
				highlight=Color.gray;
				lightHighlight=Color.black;
				shadow=Color.gray;
				darkShadow=Color.cyan;
				focus=Color.yellow;
			}
		});
		UIManager.put("TabbedPane.contentBorderInsets", new Insets(0,0,0,0));
		UIManager.put("TabbedPane.tabAreaInsets",new Insets(3,0,0,0));
		UIManager.put("TabbedPane.tabsOverlapBorder", false);

		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		//Creating title panel
		JPanel titlePanel=new JPanel();
		Font font=new Font("SansSerif",Font.BOLD,50);
		JLabel titleLabel=new JLabel("Mini Shop Management System");
		titleLabel.setFont(font);
		titlePanel.add(titleLabel);
		titlePanel.setBackground(new Color(102,153,153));

		//Adding Title panel
		titleAndToolPanel.add(titlePanel,BorderLayout.NORTH);

		//Creating toolPanel


		//Adding toolPanel
		titleAndToolPanel.add(toolPanel,BorderLayout.CENTER);

		//set layout to entryViewInvoicePanel
		entryViewInvoicePanel.setLayout(new BorderLayout());


		//Creating codeEntryPanel

		//codeEntryPanel.setBorder(new LineBorder(Color.white,10));
		//menuBar.setLayout(new GridLayout(0,1,5,5));
		//menuBar.add(file);
		//menuBar.add(help);


		//Creating sale
		Sale sale=new Sale();

		//Adding codeEntryPanel
		//entryViewInvoicePanel.add(codeEntryPanel,BorderLayout.WEST);

		tabbedPane.add("Start    ",initialPanel);
		//Adding tabbedPanePanel

		//Adding sale to tabbedPane
		tabbedPane.add("Sale    ",sale);

		//creating invoicePanel
		//Adding event to confirm button
		initialPanel.confirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				confirmInvoice();
			}
		});

		//tabbedPane.add("Initial",i);
		tabbedPanePanel.setLayout(new BorderLayout());
		tabbedPanePanel.add(tabbedPane,BorderLayout.CENTER);
		//tabbedPanePanel.add(menuBar,BorderLayout.WEST);
		//adding initialPanel
		entryViewInvoicePanel.add(tabbedPanePanel,BorderLayout.CENTER);

		//adding invoicePanel
		entryViewInvoicePanel.add(invoicePanel,BorderLayout.EAST);

		//Adding event
		/*toolPanel.manualInput.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				input();
			}
		});
		*/
		toolPanel.barCode.setFocusable(true);
		toolPanel.barCode.requestFocus();
		toolPanel.barCode.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				barCodeInput();

			}
		});



		//set borderLayout to frame
		setLayout(new BorderLayout());
		add(titleAndToolPanel,BorderLayout.NORTH);
		add(entryViewInvoicePanel,BorderLayout.CENTER);


	}
	public void input(){
		JFrame codeEntryFrame=new JFrame();
		codeEntryFrame.add(codeEntryPanel);
		codeEntryFrame.setSize(500,400);
		//codeEntryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		codeEntryFrame.setLocationRelativeTo(null);
		codeEntryFrame.setVisible(true);
	}
	public void barCodeInput(){
		try{

			String code=toolPanel.barCode.getText();
			String productName=null;
			String price=null;
			String queryString="select Product_name,Normal_price"+" from product"+" where Product_id="+code;
			ResultSet rset=stmt.executeQuery(queryString);

			if(rset.next()){
				productName=rset.getString(1);
				price=rset.getString(2);
			}
			if(productName==null){
				initialPanel.noProduct.setText("This product is not in the stock");
				toolPanel.barCode.setText(null);
			}
			else{
			Object[] data={code,productName,1,price};
			boolean b=true;boolean bl=true;
			int r=initialPanel.tableModel.getRowCount();
			//System.out.println(row);
			if(r==0){
				initialPanel.tableModel.addRow(data);
				//invoicePanel.tableModel.addRow(data);
				quantity.add(1);
				toolPanel.barCode.setText(null);
				bl=false;
			}
			int row=initialPanel.tableModel.getRowCount();
			System.out.print(row);
			if(bl){
			for(int i=0;i<row;i++){
				if(code.equals(initialPanel.tableModel.getValueAt(i,0)))
				{	//System.out.println(code+code.equals(codeEntryPanel.initialPanel.tableModel.getValueAt(i,0)));
					//System.out.println(codeEntryPanel.initialPanel.tableModel.getValueAt(i,0));
					quantity.set(i, quantity.get(i)+1);
					initialPanel.tableModel.setValueAt(quantity.get(i),i , 2);
					b=false;
					toolPanel.barCode.setText(null);
				}

			}
			}
			if(b&&bl){
				initialPanel.tableModel.addRow(data);
				quantity.add(1);
				toolPanel.barCode.setText(null);
			}
			initialPanel.noProduct.setText(" ");
		}


		}
		catch(SQLException ex){
			ex.printStackTrace();
		}



	}

	//ConfirmInvoice
	public void confirmInvoice(){
		Object[][] data=getTableData(initialPanel.tableModel);
		invoicePanel.tableModel.setDataVector(data, invoicePanel.name);
		int total=0;
		for(int i=0;i<data.length;i++){
			int d=Integer.parseInt(data[i][data[0].length-1].toString());
			total+=d;
		}
		invoicePanel.tPrice.setText(" "+total);
	}

	//InitializeDB
	public void initializeDB(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");

			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/shop_managment_system","root","root");

			System.out.println("Database Connected");
			stmt=connection.createStatement();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public Object[][] getTableData(DefaultTableModel table){
		DefaultTableModel dtm=table;
		int row=dtm.getRowCount();
		int column=dtm.getColumnCount()+1;
		Object[][] tableData=new Object [row][column];
		for(int i=0;i<row;i++){
			for(int j=1;j<column-1;j++){
				tableData[i][j]=dtm.getValueAt(i, j);
			}
		}
		for(int i=0;i<row;i++){
			tableData[i][0]=i+1;
			int quantity=Integer.parseInt(tableData[i][2].toString());
			int price=Integer.parseInt(tableData[i][3].toString());
			tableData[i][column-1]=(Object)(quantity*price);
		}


		return tableData;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame=new Home();
		frame.setFocusable(true);

		frame.setTitle("Shop Management System");
				//frame.setSize(width, height);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
