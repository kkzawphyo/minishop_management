import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Customer extends JPanel{
	//moved from combine
	private JPanel addCustomerPanel=new JPanel();
	private JPanel deleteAndClearPanel=new JPanel();
	private ImageIcon deleteIcon ,addCustomerIcon;
	private ToolPanel tool;
	private Font labelFont=new Font("Sanserif",Font.BOLD,13);
	private JPanel totalPanel = new JPanel(new BorderLayout());
	private StandardButton addCustomerButton= new StandardButton("Add Customer");
	private StandardButton deleteCustomer = new StandardButton("Delete");
	private StandardButton clearSelection = new StandardButton("clearSelction");
	private JPanel buttonPanel = new JPanel(new BorderLayout());
	//private JButton update = new JButton("Update");
	private ImageIcon edit;
	private MyImageCellRenderer buttonRenderer = new MyImageCellRenderer();
	private JTable customerTable ;
	private MyNewTableModel tableModel = new MyNewTableModel();
	private NonHiglightBorderRenderer borderRender = new NonHiglightBorderRenderer();
	private int row[];
	public static final String DBURL=  "jdbc:mysql://localhost/shop_managment_system?user=root&password=root" ;
	  private static final String DBDRIVER = "com.mysql.jdbc.Driver";
	   static {
	        try {
	            Class.forName(DBDRIVER).newInstance();
	        } catch (Exception e){
	            e.printStackTrace();
	        }
	    }


	//private AddCustomer AddDialog;




	public Customer(ToolPanel toolPanel) throws SQLException , ClassNotFoundException{
	//	this.AddDialog=AddDialog;
		ImageIcon img=new ImageIcon("fav/recycle3.png");
		Image i=img.getImage();
		Image newImg=i.getScaledInstance(15,15,java.awt.Image.SCALE_SMOOTH);
		deleteIcon=new ImageIcon(newImg);

		ImageIcon img1=new ImageIcon("fav/addtion.png");
		Image i1=img1.getImage();
		Image addImg=i1.getScaledInstance(15,15,Image.SCALE_SMOOTH);
		addCustomerIcon=new ImageIcon(addImg);

		ImageIcon img2=new ImageIcon("fav/edit1.png");
		Image i2=img2.getImage();
		Image icon=i2.getScaledInstance(25,25,Image.SCALE_SMOOTH);
		edit=new ImageIcon(icon);

		addCustomerButton.setIcon(addCustomerIcon);
		deleteCustomer.setIcon(deleteIcon);


		tool=toolPanel;
		showTable();
		customerTable.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(((JTable)e.getSource()).getSelectedColumn() == customerTable.getColumnCount()-1){
				//	String id=(String)customerTable.getValueAt(row, 0);
				//	String name=(String)customerTable.getValueAt(row, 1);
				//	String address=(String)customerTable.getValueAt(row, 2);
				//	String pno = (String)customerTable.getValueAt(row, 3);
					try {
						new AddCustomer(getCustomerClass(),AddCustomer.Edit);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else{
					row=customerTable.getSelectedRows();
					for(int i=0 ; i<row.length ; i++){
						System.out.print(row[i]+"\t");
				}

				}

			}
		});

		addCustomerButton.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e)  {
						System.out.print("add customer work");
						try {	//JDialog.setDefaultLookAndFeelDecorated(true);
								new AddCustomer(getCustomerClass(),AddCustomer.Add);

						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

			}
		});

		clearSelection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				customerTable.clearSelection();
			}
		});
	deleteCustomer.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				delete(row);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	});

	customerTable.addKeyListener(new KeyAdapter(){
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode()==KeyEvent.VK_DELETE)
				try {
					delete(row);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	});


	}

	  public Connection getConnection()
	    {
	        Connection connection = null;
	        try {
	            connection = DriverManager.getConnection(DBURL);
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        return connection;
	    }

	  public Customer getCustomerClass(){
		  return this;
	  }
	  public void delete(int[] r) throws SQLException , ClassNotFoundException{

			if(customerTable.getSelectedRows().length==0)
				System.out.print("Please select some row");
			else
			{
				int confirm=(JOptionPane.showConfirmDialog(null, "Do you want to delete selected row", "delete", JOptionPane.YES_NO_OPTION));
				if(confirm==0){
					String id[]=new String[r.length];
					for(int i=0 ; i<r.length ; i++){
						id[i]=(String)customerTable.getValueAt(r[i], 0);
					}
					tableModel.removeRow(r);
				//	System.out.print("row length = "+row.length);
				 	Statement statement =null;
					Connection connection=getConnection();
					statement = connection.createStatement();

					for(int i=0 ; i<id.length ; i++){
						statement.execute("delete from customer where customer_id="+id[i]);
					}
					updateCombo();

					connection.close();
				}


			}



	  }

	public void showTable() throws SQLException , ClassNotFoundException{
		Statement statement =null;
		Connection connection=getConnection();

		String query = "Select * from Customer";

		statement = connection.createStatement();
		ResultSet resultSet=statement.executeQuery(query);
		executeQuery(resultSet);
		connection.close();


	}
	public void updateCombo(){
		tool.customerName.removeAllItems();
		tool.customerName.addItem("Normal Customer");
		int row=getTable().getRowCount();
		for(int i=0 ; i<row ; i++){
			tool.customerName.addItem(getTable().getValueAt(i, 1));
		}
		tool.customerName.setSelectedIndex(0);
	}

	//a b c d are new values;
	public void updateTable(String new_id,String new_name, String new_address, String new_pno,int row)throws SQLException , ClassNotFoundException{

		String old_id=(String)customerTable.getValueAt(row, 0);
		String old_name=(String)customerTable.getValueAt(row, 1);
		String old_address = (String)customerTable.getValueAt(row, 2);
		String old_pno = (String)customerTable.getValueAt(row, 3);

		customerTable.setValueAt(new_id, row, 0);
		customerTable.setValueAt(new_name, row, 1);
		customerTable.setValueAt(new_address, row, 2);
		customerTable.setValueAt(new_pno, row, 3);

		Statement statement =null;
		Connection connection=getConnection();
		statement=connection.createStatement();
		statement.executeUpdate("update customer set customer_id='"+new_id+"' where customer_id='"+old_id+"'");
		statement.executeUpdate("update customer set customer_name='"+new_name+"' where customer_name='"+old_name+"'");
		statement.executeUpdate("update customer set address='"+new_address+"' where address='"+old_address+"'");
		statement.executeUpdate("update customer set phone_no='"+new_pno+"' where phone_no='"+old_pno+"'");
		System.out.print("update worked");
		updateCombo();
		connection.close();

	}

	public void insertTable(String c_id , String c_name , String c_address , String c_pno) throws SQLException , ClassNotFoundException{

		((DefaultTableModel) customerTable.getModel()).addRow(new Object[]{c_id,c_name,c_address,c_pno,edit});

		Statement statement =null;
		Connection connection=getConnection();

		String query = "insert into customer values "+"('"+c_id+"','"+c_name+"','"+c_address+"','"+c_pno+"')";

		statement = connection.createStatement();
		statement.executeUpdate(query);

		connection.close();
		updateCombo();
	}

	public void executeQuery(ResultSet result ) throws SQLException , ClassNotFoundException{
		ResultSet resultSet=result;
		 String [] columnName={"Customer ID","Customer Name","Address","Phone No","edit"};


//			public JButton addCustomerButton ;



		// assign array row and col
		resultSet.last();
		int row = resultSet.getRow();
		int col = 5;
		//System.out.print(row);
		//initialize array;
		Object data[][] = new Object[row][col];


		//set result set to origin state
		resultSet.first();
		resultSet.previous();
		//System.out.println(resultSet.getRow());

		/**	for(int i=0 ; i<row  && resultSet.next(); i++){
			for(int j=0 ; j<col ; j++){
				data[i][j]=resultSet.getString(j+1);
				System.out.println(data[i][j]);
			}
		} **/
		int i=-1;
		while(resultSet.next()){
			++i;
			data[i][0]=resultSet.getString(1);
			data[i][1]=resultSet.getString(2);
			data[i][2]=resultSet.getString(3);
			data[i][3]=resultSet.getString(4);
			data[i][4]=edit;
		}




		 tableModel.setDataVector(data, columnName);
		 customerTable= new JTable(tableModel){
			 public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
					Component c=super.prepareRenderer(renderer, row, column);
					c.setBackground(row%2==0?new Color(252,242,206):new Color(252,242,206));//new Color(250,250,0
				//	if(isRowSelected(row)) c.setForeground(Color.RED); else c.setForeground(Color.BLACK);
					if(isRowSelected(row)) {
						//c.setForeground(Color.yellow);
						//c.setForeground(new Color(100,47,9));
						c.setForeground(Color.RED);

					}

					//c.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
					return c;
				}
		 };

		 Font font=new Font("Times New Roman",Font.BOLD,14);
		 customerTable.setShowGrid(true);
		 customerTable.setRowHeight(40);
		 JTableHeader header=customerTable.getTableHeader();
			header.setFont(font);
			header.setBackground(new Color(100,47,9));
			header.setForeground(Color.yellow);

		//  customerTable.setPreferredScrollableViewportSize(customerTable.getPreferredSize());//thanks mKorbel +1 http://stackoverflow.com/questions/10551995/how-to-set-jscrollpane-layout-to-be-the-same-as-jtable

	      //set the width of last column
	  customerTable.getColumnModel().getColumn(customerTable.getColumnCount()-1).setPreferredWidth(10);//so buttons will fit and not be shown butto..

	        // set table cell render for each columns
	        TableColumn customerId = customerTable.getColumn("Customer ID");
	        customerId.setCellRenderer(borderRender);
	        TableColumn customerName = customerTable.getColumn("Customer Name");
	        customerName.setCellRenderer(borderRender);
	        TableColumn address= customerTable.getColumn("Address");
	        address.setCellRenderer(borderRender);
	        TableColumn phoneNo = customerTable.getColumn("Phone No");
	        phoneNo.setCellRenderer(borderRender);
	        TableColumn edit = customerTable.getColumn("edit");
	        edit.setCellRenderer(buttonRenderer);
	        edit.setHeaderValue(null);
	        customerTable.getTableHeader().setReorderingAllowed(false);
	        //customerTable.getTableHeader().setPreferredSize(preferredSize);

	        //.......

	       customerTable.setFont(labelFont);
	       addCustomerPanel.add(addCustomerButton);
	       deleteAndClearPanel.add(deleteCustomer);
	       deleteAndClearPanel.add(clearSelection);
	        buttonPanel.add(addCustomerPanel,BorderLayout.WEST);
	    	buttonPanel.add(deleteAndClearPanel,BorderLayout.EAST);
	    	buttonPanel.setBackground(new Color( 202, 207, 210 ));
	    	addCustomerPanel.setBackground(new Color( 202, 207, 210 ));
	    	deleteAndClearPanel.setBackground(new Color( 202, 207, 210 ));
	    	setLayout(new BorderLayout());
	    	totalPanel.add(new JScrollPane(customerTable),BorderLayout.CENTER);
	    	totalPanel.add(buttonPanel,BorderLayout.NORTH);
	    	add(totalPanel);

	}
	public JTable getTable(){
		return customerTable;
	}

}
