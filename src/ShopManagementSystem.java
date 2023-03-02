import java.sql.*;
import java.util.*;
import java.util.Date;
import java.awt.print.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.event.MenuEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
public class ShopManagementSystem extends JFrame implements Printable{

	private JMenuBar menuBar=new JMenuBar();
	private JMenu start=new JMenu("Start");
	private JMenu sale=new JMenu("Sale");
	private JMenu purchase=new JMenu("Purchase");
	private JMenu stock=new JMenu("Stock");
	private JMenu customer=new JMenu("Customer");

	private JMenuItem purchaseRecord=new JMenuItem("PurchaseRecord");
	private JMenuItem purchaseReturn=new JMenuItem("PurchaseReturn");

	private JMenuItem saleRecord=new JMenuItem("SaleRecord");
	private JMenuItem saleOrder=new JMenuItem("SaleOrder");
	private JMenuItem saleReturn=new JMenuItem("SaleReturn");

	private int total=0;

	private JPanel titleAndToolPanel=new JPanel();
	private JPanel entryViewInvoicePanel=new JPanel();
	private Product stockPanel;

	private Sale_rcd_ord_rtn saleRecordPanel,saleReturnPanel,saleOrderPanel;
	//private JPanel tabbedPanePanel=new JPanel();
	//private JTabbedPane tabbedPane=new JTabbedPane();
	public InitialPanel initialPanel=new InitialPanel();

	private CodeEntryPanel codeEntryPanel=new CodeEntryPanel();
	private ToolPanel toolPanel=new ToolPanel();
	public Customer customerPanel;
	private LinkedList<String> dataCode=new LinkedList<String>();
	private ArrayList<Integer> quantity=new ArrayList<Integer>();

	private JInternalFrame invoiceFrame=new JInternalFrame("Invoice",true,true,true,true);
	//private JDesktopPane desktopPane=new JDesktopPane();
	private InvoicePanel invoicePanel=new InvoicePanel();
	private JInternalFrame internalFrame=new JInternalFrame();


	private PurchaseReturn purchaseReturnPanel;
	private Combine combinePanel;
	//private JComboBox testBox=new JComboBox(new Object[]{"Start","Sale",});
	//Database
	private Statement stmt;
	Font fontForMenu=new Font("SansSerif",Font.BOLD,17);
	//private JPanel panel1=new JPanel();


	public ShopManagementSystem() throws ClassNotFoundException, SQLException {


		//Initialize the applet
		initializeDB();

		try {
			stockPanel=new Product();
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		saleRecordPanel=new Sale_rcd_ord_rtn(1);saleReturnPanel=new Sale_rcd_ord_rtn(2);try {
			saleOrderPanel=new Sale_rcd_ord_rtn(0);
		} catch (ClassNotFoundException | SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			combinePanel=new Combine();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}try {
			purchaseReturnPanel=new PurchaseReturn();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}


		customerPanel=new Customer(toolPanel);
		//Creating title panel
		JPanel titlePanel=new JPanel();
		Font font=new Font("SansSerif",Font.BOLD,50);
		JLabel titleLabel=new JLabel("Mini Shop Management System");
		titleLabel.setFont(font);
		titleLabel.setForeground(Color.white);
		titlePanel.add(titleLabel);
		titlePanel.setBackground(new Color(0,102,102));
		//Color(102,153,153)
		//setting layout to titleAndToolpanel
		titleAndToolPanel.setLayout(new BorderLayout());

		//Adding Title panel
		titleAndToolPanel.add(titlePanel,BorderLayout.NORTH);

		//Adding toolPanel
		titleAndToolPanel.add(toolPanel,BorderLayout.CENTER);


		menuBar.setLayout(new FlowLayout(FlowLayout.LEFT,0,5));
		start.setFont(fontForMenu);
		menuBar.add(start);
		sale.add(saleRecord);
		sale.add(saleOrder);
		sale.add(saleReturn);
		sale.setFont(fontForMenu);

		purchase.add(purchaseRecord);
		purchase.add(purchaseReturn);
		purchase.setFont(fontForMenu);
		menuBar.add(sale);

		purchase.setFont(fontForMenu);
		menuBar.add(purchase);

		stock.setFont(fontForMenu);
		menuBar.add(stock);

		customer.setFont(fontForMenu);
		menuBar.add(customer);
		internalFrame.setMenuBar(menuBar);

		saleRecord.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

			}
		});

		//Creating sale
		Sale salePanel=new Sale();

		//Adding event to confirm button



		//set layout to entryViewInvoicePanel
		entryViewInvoicePanel.setLayout(new BorderLayout());


		//adding invoicePanel
		entryViewInvoicePanel.add(initialPanel,BorderLayout.CENTER);
		entryViewInvoicePanel.add(invoicePanel,BorderLayout.EAST);

		start.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){

				entryViewInvoicePanel.add(initialPanel,BorderLayout.CENTER);
				initialPanel.setVisible(true);
				codeEntryPanel.setVisible(false);
				stockPanel.setVisible(false);
				saleRecordPanel.setVisible(false);
				saleReturnPanel.setVisible(false);
				saleOrderPanel.setVisible(false);
				customerPanel.setVisible(false);
				combinePanel.setVisible(false);
				purchaseReturnPanel.setVisible(false);
			}
		});

		initialPanel.confirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				confirmInvoice();
				quantity.clear();
			}
		});
		initialPanel.delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(initialPanel.table.getSelectedRow()>=0){
					int i=initialPanel.table.getSelectedRow();

					if(initialPanel.table.getRowCount()>1){
						int q=quantity.get(i+1);
						quantity.remove(i+1);
						quantity.set(i,q);

					}
					else
						{quantity.clear();}
					initialPanel.tableModel.removeRow(initialPanel.table.getSelectedRow());
					System.out.println();
					System.out.println(initialPanel.tableModel.getRowCount());
				}
				toolPanel.barCode.requestFocus(true);
			}
		});

		invoicePanel.print.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				printingAction();
			}
		});

		//adding event to stock
		stock.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){

				if(!stockPanel.isVisible()){
					try {
						stockPanel=new Product();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				entryViewInvoicePanel.add(stockPanel,BorderLayout.CENTER);
				initialPanel.setVisible(false);
				stockPanel.setVisible(true);
				saleRecordPanel.setVisible(false);
				saleReturnPanel.setVisible(false);
				saleOrderPanel.setVisible(false);
				customerPanel.setVisible(false);
				combinePanel.setVisible(false);
				purchaseReturnPanel.setVisible(false);
			}
		});
		//adding event to customer
		customer.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(!customerPanel.isVisible()){
					try {
						customerPanel=new Customer(toolPanel);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				entryViewInvoicePanel.add(customerPanel,BorderLayout.CENTER);
				customerPanel.setVisible(true);
				initialPanel.setVisible(false);
				stockPanel.setVisible(false);
				saleRecordPanel.setVisible(false);
				saleReturnPanel.setVisible(false);
				saleOrderPanel.setVisible(false);
				combinePanel.setVisible(false);
				purchaseReturnPanel.setVisible(false);
			}
		});

		//Adding event to salerecord
		saleRecord.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!saleRecordPanel.isVisible()){
				try {
					saleRecordPanel=new Sale_rcd_ord_rtn(1);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				entryViewInvoicePanel.add(saleRecordPanel,BorderLayout.CENTER);
				initialPanel.setVisible(false);
				stockPanel.setVisible(false);
				saleRecordPanel.setVisible(true);
				saleReturnPanel.setVisible(false);
				saleOrderPanel.setVisible(false);
				customerPanel.setVisible(false);
				combinePanel.setVisible(false);
				purchaseReturnPanel.setVisible(false);
			}
		});
		//Adding event to salereturn
				saleReturn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(!saleReturnPanel.isVisible()){
						try {
							saleReturnPanel=new Sale_rcd_ord_rtn(2);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
						entryViewInvoicePanel.add(saleReturnPanel,BorderLayout.CENTER);
						initialPanel.setVisible(false);
						stockPanel.setVisible(false);
						saleReturnPanel.setVisible(true);
						saleRecordPanel.setVisible(false);saleOrderPanel.setVisible(false);
						customerPanel.setVisible(false);
						combinePanel.setVisible(false);
						purchaseReturnPanel.setVisible(false);
					}
				});


				//Adding event to saleOrder
				saleOrder.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						if(!saleOrderPanel.isVisible()){
						try {
							saleOrderPanel=new Sale_rcd_ord_rtn(0);
						} catch (ClassNotFoundException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						}
						entryViewInvoicePanel.add(saleOrderPanel,BorderLayout.CENTER);
						initialPanel.setVisible(false);
						stockPanel.setVisible(false);
						saleOrderPanel.setVisible(true);
						saleReturnPanel.setVisible(false);
						saleRecordPanel.setVisible(false);
						customerPanel.setVisible(false);
						combinePanel.setVisible(false);
						purchaseReturnPanel.setVisible(false);
					}
				});

		purchaseRecord.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!combinePanel.isVisible()){
					try {
						combinePanel=new Combine();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					entryViewInvoicePanel.add(combinePanel,BorderLayout.CENTER);
					initialPanel.setVisible(false);
					stockPanel.setVisible(false);
					saleOrderPanel.setVisible(false);
					saleReturnPanel.setVisible(false);
					saleRecordPanel.setVisible(false);
					customerPanel.setVisible(false);
					combinePanel.setVisible(true);
					purchaseReturnPanel.setVisible(false);
			}
		});

		purchaseReturn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(!purchaseReturnPanel.isVisible()){
					try {
						purchaseReturnPanel=new PurchaseReturn();
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					entryViewInvoicePanel.add(purchaseReturnPanel,BorderLayout.CENTER);
					initialPanel.setVisible(false);
					stockPanel.setVisible(false);
					saleOrderPanel.setVisible(false);
					saleReturnPanel.setVisible(false);
					saleRecordPanel.setVisible(false);
					customerPanel.setVisible(false);
					combinePanel.setVisible(false);
					purchaseReturnPanel.setVisible(true);
			}
		});

		toolPanel.barCode.setFocusable(true);
		toolPanel.barCode.requestFocus();
		toolPanel.barCode.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				barCodeInput();

			}
		});



		//Disable title bar
		BasicInternalFrameUI b=(BasicInternalFrameUI)invoiceFrame.getUI();
		b.setNorthPane(null);
		invoiceFrame.add(invoicePanel);
		invoiceFrame.setVisible(true);
		invoiceFrame.setBorder(null);
		//invoiceFrame.putClientProperty("JInternalFrame.frameType", "normal");
		//invoiceFrame.setOpaque(true);
		//invoiceFrame.setBorder(new LineBorder(new Color(30, 6, 6), 0, true));
		//invoiceFrame.set
		//UIManager.setLookAndFeel(WebLookAndFeel.class.getCanonicalName());
		//SwingUtilities.updateComponentTreeUI(frame);

		//desktopPane.add(invoiceFrame,new Integer(1));
		//desktopPane.setSize(getPreferredSize(450,350));
		//invoiceFrame.setBounds(0,0,450,450);

		//disable title border
		BasicInternalFrameUI bi=(BasicInternalFrameUI)internalFrame.getUI();
		bi.setNorthPane(null);

		internalFrame.add(entryViewInvoicePanel);
		//internalFrame.setTitle("");
		internalFrame.setClosable(true);
		internalFrame.setFrameIcon(null);

		//internalFrame.setOpaque(true);
		internalFrame.setBorder(new LineBorder(new Color(30, 6, 6), 0, true));
		internalFrame.setVisible(true);
		//internalFrame.putClientProperty("JInternalFrame.frameType", "normal");

		JSplitPane jSplitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,internalFrame,invoiceFrame);
		//set borderLayout to frame
		//setBackground(new Color(30,6,6,5));
		jSplitPane.setDividerLocation(970);
		//jSplitPane.setForeground(Color.red);
		setLayout(new BorderLayout());
		add(titleAndToolPanel,BorderLayout.NORTH);
		add(jSplitPane,BorderLayout.CENTER);
		//add(internalFrame,BorderLayout.CENTER);
		//invoiceFrame.setSize(300,300);
		//add(invoiceFrame,BorderLayout.EAST);

	}
	public int print(Graphics g,PageFormat pf,int page)throws PrinterException{
		if(page>0){
			return NO_SUCH_PAGE;
		}
		Graphics2D g2d=(Graphics2D)g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		int x=50;int y=50;
		int row=invoicePanel.tableModel.getRowCount();
		int column=invoicePanel.tableModel.getColumnCount();
		Object[][] data=getTableDataForInvoice(invoicePanel.tableModel);
		//String columnName=invoicePanel.tableModel.getColumnName(1);

		for(int i=0;i<column;i++){
			g.drawString(invoicePanel.tableModel.getColumnName(i), x, y);
			x+=80;
		}
		x=50;y=70;
		System.out.println(row+" "+column);
		//System.out.println(data[0][4].toString());

		if(row>0&&column>0)
		{for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				g.drawString(data[i][j].toString(), x, y);
				x+=80;

			}
			x=50;
				//g.drawString(data[0][4].toString(),x,y);
			y+=20;
		}
			g.drawString("Total :"+total, 370, y);
		}

		//((Window) invoicePanel.tableModel).paint(g2d);
		//System.out.print(columnName);
		//g.drawString("HelloWorld",500,100);
		return PAGE_EXISTS;
	}

	public void barCodeInput(){
		try{

			String code=toolPanel.barCode.getText().toString();
			System.out.println("Code:"+code);
			String productName=null;
			String price=null;
			String conditionalPrice=null;
			System.out.println(toolPanel.CName.toString());
			if(toolPanel.CName.toString().equals("Normal Customer")){
				conditionalPrice="Normal_price";
				System.out.print(conditionalPrice);
				initialPanel.customerName.setText(null);
			}
			else{
				conditionalPrice="VIP_price";
				initialPanel.customerName.setText("By "+toolPanel.CName.toString());
			}
			String queryString="select Product_name,"+conditionalPrice+",Quantity from product"+" where Product_id='"+code+"'";
			ResultSet rset=stmt.executeQuery(queryString);
			boolean scanningForTrue=true;
			int q=0;
			if(rset.next()){
				productName=rset.getString(1);
				price=rset.getString(2);
				if(rset.getString(3)==null){
					q=-1;
				};
				q=Integer.parseInt(rset.getString(3));
			}
			boolean b=true;boolean bl=true;
			Object[] data={code,productName,1,price};
			if(q>0){




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
				if(code.equals(initialPanel.tableModel.getValueAt(i,0))&&scanningForTrue)
				{	//System.out.println(code+code.equals(codeEntryPanel.initialPanel.tableModel.getValueAt(i,0)));
					//System.out.println(codeEntryPanel.initialPanel.tableModel.getValueAt(i,0));

					q=q-quantity.get(i);
					if(q<=0){
						scanningForTrue=false;
					}
					else{
						quantity.set(i, quantity.get(i)+1);
						initialPanel.tableModel.setValueAt(quantity.get(i),i , 2);

						toolPanel.barCode.setText(null);
					}
					b=false;

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
		if(q<=0){
			if(q!=-1)
			if(productName==null){
				initialPanel.noProduct.setText("This product is not in the stock");
				toolPanel.barCode.setText(null);
			}
			else
				JOptionPane.showMessageDialog(null, "This product is no longer exist and fill again");
			//if(!scanningForTrue)
				//JOptionPane.showMessageDialog(null, "This product is no longer exist and fill again");
			toolPanel.barCode.setText(null);
		}


		}
		catch(SQLException ex){
			ex.printStackTrace();
		}



	}

	public void printingAction(){
		PrinterJob job=PrinterJob.getPrinterJob();
		job.setPrintable(this);
		boolean ok=job.printDialog();
		if(ok){
			try{
				job.print();
			}
			catch(PrinterException ex){
				ex.printStackTrace();
			}
		}

	}

	//ConfirmInvoice
	public void confirmInvoice(){
		Object[][] data=getTableDataForInvoice(initialPanel.tableModel);
		invoicePanel.tableModel.setDataVector(data, invoicePanel.name);
		total=0;
		for(int i=0;i<data.length;i++){
			int d=Integer.parseInt(data[i][data[0].length-1].toString());
			total+=d;
		}

		invoicePanel.tPrice.setText(" "+total);

		Object[][] dataToDatabase=getTableDataForDatabase(initialPanel.tableModel);
		String productId=null;
		String quantity=null;
		String customerName=null;
		Date date=new Date();
			for(int j=0;j<dataToDatabase.length;j++){
				productId=dataToDatabase[j][0].toString();
				quantity=dataToDatabase[j][2].toString();
				String nameId=null;
				if(toolPanel.CName.equals("Normal_Customer"))
					customerName=null;
				else{
					customerName=toolPanel.CName.toString();
					try{
						String selectingCustomerName="select Customer_id "+" from customer "+" where Customer_name="+"'"+customerName+"'";
						ResultSet set=stmt.executeQuery(selectingCustomerName);

						while(set.next()){
							nameId=set.getString(1);
							System.out.println(nameId);
						}
						}
						catch(SQLException ex){
							ex.printStackTrace();
						}
				}
				//if(!customerName.equals("Normal_Customer"))]
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(date);
				System.out.println(calendar.getTime());
				String d=calendar.get(Calendar.YEAR)+"-"+date.getMonth()+"-"+date.getDate();
				System.out.println(" "+productId+" "+quantity+" "+d);
				boolean ifTrue=false;
				String quary="insert into sale_record(Product_id,Customer_id,Quantity,Date)"+"values ( '"+
						productId+"','"+nameId+"',"+quantity+",'"+d+"')";
				try{
					stmt.executeUpdate(quary);
					ifTrue=true;
				}
				catch(SQLException ex){
					ex.printStackTrace();
				}
				String updateQuery="update product set Quantity=Quantity-'"+quantity+"'where Product_id='"+productId+"'";
				try {
					stmt.executeUpdate(updateQuery);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			int row=initialPanel.tableModel.getRowCount();
			for(int i=0;i<row;i++){
				System.out.println(initialPanel.tableModel.getRowCount());
				initialPanel.tableModel.removeRow(0);
			}
			initialPanel.customerName.setText(null);
			toolPanel.barCode.requestFocus(true);
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
	public Object[][] getTableDataForInvoice(DefaultTableModel table){
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
	public Object[][] getTableDataForDatabase(DefaultTableModel table){
		DefaultTableModel dtm=table;
		int row=dtm.getRowCount();
		int column=dtm.getColumnCount()+1;
		Object[][] tableData=new Object [row][column];
		for(int i=0;i<row;i++){
			for(int j=0;j<column-1;j++){
				tableData[i][j]=dtm.getValueAt(i, j);
			}
		}
		return tableData;
	}
	public Object[][] getTableData(DefaultTableModel table){

		DefaultTableModel dtm=table;
		int row=dtm.getRowCount();
		int column=dtm.getColumnCount();
		Object[][] tableData=new Object [row][column];
		for(int i=0;i<row;i++){
			for(int j=0;j<column-1;j++){
				tableData[i][j]=dtm.getValueAt(i, j);
			}
		}
		return tableData;
	}
	public static void main(String[] args) throws Exception{

		//UIManager.setLookAndFeel(WebLookAndFeel.class.getCanonicalName());
		//JFrame.setDefaultLookAndFeelDecorated(true);

		JFrame frame=new ShopManagementSystem();
		//UIManager.setLookAndFeel(WebLookAndFeel.class.getCanonicalName());
		//SwingUtilities.updateComponentTreeUI(frame);
		frame.setFocusable(true);

		frame.setTitle("Shop Management System");
				//frame.setSize(width, height);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		Toolkit tk=Toolkit.getDefaultToolkit();
		int x=(int)tk.getScreenSize().getWidth();
		int y=(int)tk.getScreenSize().getHeight();
		//SwingUtilities.updateComponentTreeUI(frame);
		frame.pack();
		//frame.setSize(x,y);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		//frame.setUndecorated(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}


