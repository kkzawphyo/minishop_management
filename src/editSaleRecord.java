import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.text.ParseException;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.java.dev.designgridlayout.DesignGridLayout;
public class editSaleRecord extends JDialog{

	private Font font1=new Font("Zawgyi-One",Font.BOLD,13);

	static ImageIcon s;

	private Statement statement;
	private Connection connection;

	private JLabel jlbNo=new JLabel("No");
	private JLabel jlbid=new JLabel("Product ID");
	private JLabel jlbCustomerID=new JLabel("Customer ID");
	private JLabel jlbQuantity=new JLabel("Quantity");
	private JLabel jlbDate=new JLabel("Date");

	private JSearchTextField No=new JSearchTextField("eg:  \"1\"");
	private JSearchTextField Product_ID=new JSearchTextField("eg:  \"000000001\"");
	private JSearchTextField CustomerID=new JSearchTextField("eg:  \"11111\"");
	private JSearchTextField Quantity=new JSearchTextField("eg:  \"50\"");
	private JSearchTextField Date1=new JSearchTextField("eg:  \"2016-02-11\"");

	private StandardButton jbtUpdate=new StandardButton("Update");

	public editSaleRecord(JFrame Parent1,boolean modal,final int r)
	{
		super(Parent1,modal);
		setTitle("EDIT");
		setSize(300,260);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		setTF(r);
		String Prod_id=Product_ID.getText();
		String Prod_No=No.getText();


		  ImageIcon img3=new ImageIcon("fav/save3.png");
		  Image i3=img3.getImage();
		Image editImg=i3.getScaledInstance(20,20,Image.SCALE_SMOOTH);
		s=new ImageIcon(editImg);
		//JLabel button=new JLabel(s);




		jlbNo.setFont(font1);
		jlbid.setFont(font1);
		jlbCustomerID.setFont(font1);
		jlbQuantity.setFont(font1);
		jlbDate.setFont(font1);
		jbtUpdate.setIcon(s);
		jbtUpdate.setFont(font1);

		//add(jlbNo);
		//add(No);
		add(jlbid);
		add(Product_ID);
		add(jlbCustomerID);
		add(CustomerID);
		add(jlbQuantity);
		add(Quantity);
		add(jlbDate);
		add(Date1);
		add(jbtUpdate);
		//add(button);

		jlbNo.setBounds(10,10,120,30);No.setBounds(140,10,140,25);

		jlbid.setBounds(10,10,100,30);Product_ID.setBounds(140,10,140,25);

		jlbCustomerID.setBounds(10,50,100,30);CustomerID.setBounds(140,50,140,25);

		jlbQuantity.setBounds(10,90,100,30);Quantity.setBounds(140,90,140,25);

		jlbDate.setBounds(10, 130, 100, 30);Date1.setBounds(140, 130, 140, 25);

		jbtUpdate.setBounds(90, 180, 130, 25);//button.setBounds(180,220 ,90 ,25 );

		No.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					try {
						editActionPerformed(Prod_id,Prod_No,r);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		Product_ID.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					try {
						editActionPerformed(Prod_id,Prod_No,r);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		CustomerID.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					try {
						editActionPerformed(Prod_id,Prod_No,r);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		Quantity.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					try {
						editActionPerformed(Prod_id,Prod_No,r);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		Date1.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					try {
						editActionPerformed(Prod_id,Prod_No,r);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

		jbtUpdate.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					try {
						editActionPerformed(Prod_id,Prod_No,r);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		jbtUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					editActionPerformed(Prod_id,Prod_No,r);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void initializeDB(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("Driver loaded");

			connection=DriverManager.getConnection("jdbc:mysql://localhost/shop_managment_system","root","root");
			//System.out.println("Database connected");

			statement=connection.createStatement();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	private void editActionPerformed(String Prod_id,String Prod_no,int  r) throws Exception{
		//JOptionPane.showMessageDialog(null,"Input Data is not valid", "Error", JOptionPane.ERROR_MESSAGE);
		try {
				if(Product_ID.getText().length()>15 || Product_ID.getText().equals("") || !isProductIdExist())
				{
					JOptionPane.showMessageDialog(null,"Your Product ID is not Valid", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(No.getText().length()>11 ||No.getText().equals("") || isDigit(No.getText()) || isNoExist(Prod_no))
				{
					JOptionPane.showMessageDialog(null,"Input Data is not valid", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(CustomerID.getText().length()>15 ||isCustomerIdDigit(CustomerID.getText()) || CustomerID.getText().equals("") || !isCustomerIdExist())
				{
					JOptionPane.showMessageDialog(null,"Your Customer ID is not valid", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(Date1.getText().equals("") || isValidDate(Date1.getText()))
				{
					JOptionPane.showMessageDialog(null,"Your Date is not valid", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(Quantity.getText().length()>5 || isDigit(Quantity.getText())|| Quantity.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Your Quantity is not valid", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					UpdatingData(r,Prod_id);
					setVisible(false);
				}
			}
	 catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*private boolean isDatavalid(String Prod_no) throws SQLException
	{
		if(Product_ID.getText().length()>15 || Product_ID.getText().equals("") || !isProductIdExist())
			return false;
		else if(No.getText().length()>11 ||No.getText().equals("") || isDigit(No.getText()) || isNoExist(Prod_no))
			return false;
		else if(CustomerID.getText().length()>15 ||isCustomerIdDigit(CustomerID.getText()) || CustomerID.getText().equals("") || !isCustomerIdExist())
			return false;
		else if(Date1.getText().equals("") || isValidDate(Date1.getText()))
			return false;
		else if(Quantity.getText().length()>5 || isDigit(Quantity.getText())|| Quantity.getText().equals(""))
			return false;
		return true;
	}*/

	private boolean isValidDate(String input) throws Exception{
		//return input.matches("([0-9]{2})-([0-9]{2})-([0-9]{4})");
		if(input.length()==10 && !isValidDate1(input))
		{
			if(input==null)
				return true;
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);
			try{				
				Date date=sdf.parse(input);
				System.out.println(date);
			}
			catch(ParseException e)
			{
				e.printStackTrace();
				return true;
			}
			return false;
			
		}
		else
			return true;

	}

	private boolean isValidDate1(String text) {
		 char a='-';
		for(int i=0;i<4;i++)
		{
			if(!Character.isDigit(text.charAt(i)))
			{
				System.out.println(i);
				return true;
			}					
		}

		if(!(a==text.charAt(4)))
		{
			System.out.println(text.charAt(4));
			System.out.println(4);
			return true;
		}	

		for(int i=5;i<7;i++)
		{
			if(!Character.isDigit(text.charAt(i)))
			{
				System.out.println((int)'2');
				return true;
			}	
		}

		if(!(a==text.charAt(4)))
		{
			System.out.println(7);
			return true;
		}	

		for(int i=8;i<10;i++)
		{
			if(!Character.isDigit(text.charAt(i)))
			{
				System.out.println(i);
				return true;
			}	
		}
		return false;
	}

	private boolean isNoExist(String Prod_No) throws SQLException
	{
		initializeDB();
		String Num=No.getText();
		String queryString="select No from sale_record where No='"+Num+"'";
		ResultSet rset=statement.executeQuery(queryString);
		//System.out.print(Num);
		//System.out.print(Prod_No);
		if(Num.equals(Prod_No))
		{
			connection.close();
			return false;
		}
		else if(rset.next())
		{
			connection.close();
			return true;
		}
		else
		{
			connection.close();
			return false;
		}
	}

	private boolean isProductIdExist() throws SQLException
	{
		initializeDB();
		String Pro_id=Product_ID.getText();
		String queryString="select Product_id from product where Product_id='"+Pro_id+"'";
		ResultSet rset=statement.executeQuery(queryString);
		if(rset.next())
		{
			connection.close();
			return true;
		}
		connection.close();
		return false;
	}

	private boolean isCustomerIdExist() throws SQLException
	{
		initializeDB();
		String C_id=CustomerID.getText();
		String queryString="select Customer_id from customer where Customer_id='"+C_id+"'";
		ResultSet rset=statement.executeQuery(queryString);
		if(rset.next())
		{
			connection.close();
			return true;
		}
		else if(C_id.equals("null"))
		{
			connection.close();
			return true;
		}
		else
		{
				connection.close();
				return false;
		}
	}

	private boolean isDigit(String text) {
		for(int i=0;i<text.length();i++)
		{
			if(!Character.isDigit(text.charAt(i)))
					return true;
		}
		return false;
	}

	private boolean isCustomerIdDigit(String text) {
		if(text.equals("null"))
		{
			return false;
		}
		for(int i=0;i<text.length();i++)
		{
			if(!Character.isDigit(text.charAt(i)))
					return true;
		}
		return false;
	}

	private void UpdatingData(final int r,String Prod_id) throws ClassNotFoundException, SQLException{
		initializeDB();

		String No1=No.getText();
		String Pro_id=Product_ID.getText();
		String C_id=CustomerID.getText();
		String Qty=Quantity.getText();
		String Da=Date1.getText();

		String query="update sale_record set No='"+No1+"',Product_id='"+Pro_id+"',Customer_id='"+C_id+"',Quantity='"+Qty+"',Date='"+Da+"' where Product_id='"+Prod_id+"' and No='"+No1+"'";

		statement.executeUpdate(query);
		connection.close();

		sale_record.sale_Record.getModel().setValueAt(No1, r, 0);
		sale_record.sale_Record.getModel().setValueAt(Pro_id, r, 1);
		sale_record.sale_Record.getModel().setValueAt(C_id, r, 2);
		sale_record.sale_Record.getModel().setValueAt(Qty, r, 3);
		sale_record.sale_Record.getModel().setValueAt(Da, r, 4);
	}

	public void setTF(final int r){
		String No1=(String) sale_record.sale_Record.getValueAt(r,0);
		String P_code=(String)sale_record.sale_Record.getValueAt(r,1);
		String C_id=(String)sale_record.sale_Record.getValueAt(r,2);
		String Qty=(String)sale_record.sale_Record.getValueAt(r,3);
		String Da=(String)sale_record.sale_Record.getValueAt(r,4);
		No.setText(No1);
		Product_ID.setText(P_code);
		CustomerID.setText(C_id);
		Quantity.setText(Qty);
		Date1.setText(Da);
	}
}
