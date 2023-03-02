import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.java.dev.designgridlayout.DesignGridLayout;
public class EditProduct extends JDialog{

	private Font font1=new Font("Times New Roman",Font.BOLD,13);

	static ImageIcon s;

	private Statement statement;
	private Connection connection;

	private JLabel jlbname=new JLabel("Product Name");
	private JLabel jlbid=new JLabel("Product ID");
	private JLabel jlbN_price=new JLabel("Normal Price");
	private JLabel jlbVIP_price=new JLabel("VIP Price");
	private JLabel jlbQuantity=new JLabel("Quantity");

	private JSearchTextField Product_Name=new JSearchTextField("eg:  \"Mouse\"");
	private JSearchTextField Product_ID=new JSearchTextField("eg:  \"1234567890010\"");
	private JSearchTextField Normal_Price=new JSearchTextField("eg:  \"6000\"");
	private JSearchTextField VIP_Price=new JSearchTextField("eg:  \"5500\"");
	private JSearchTextField Quantity=new JSearchTextField("eg:  \"100\"");

	private StandardButton jbtUpdate=new StandardButton("Update");

	public EditProduct(JFrame Parent1,boolean modal,final int r)
	{
		super(Parent1,modal);
		setTitle("EDIT");
		setSize(300,300);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		setTF(r);
		String Prod_id=Product_ID.getText();
		String Prod_name=Product_Name.getText();


		  ImageIcon img3=new ImageIcon("fav/save3.png");
		  Image i3=img3.getImage();
		Image editImg=i3.getScaledInstance(20,20,Image.SCALE_SMOOTH);
		s=new ImageIcon(editImg);
		//JLabel button=new JLabel(s);




		jlbname.setFont(font1);
		jlbid.setFont(font1);
		jlbN_price.setFont(font1);
		jlbVIP_price.setFont(font1);
		jlbQuantity.setFont(font1);
		jbtUpdate.setIcon(s);
		jbtUpdate.setFont(font1);

		add(jlbname);
		add(Product_Name);
		add(jlbid);
		add(Product_ID);
		add(jlbN_price);
		add(Normal_Price);
		add(jlbVIP_price);
		add(VIP_Price);
		add(jlbQuantity);
		add(Quantity);
		add(jbtUpdate);
		//add(button);

		jlbname.setBounds(10,10,120,30);Product_Name.setBounds(140,10,140,25);

		jlbid.setBounds(10,50,100,30);Product_ID.setBounds(140,50,140,25);

		jlbN_price.setBounds(10,90,100,30);Normal_Price.setBounds(140,90,140,25);

		jlbVIP_price.setBounds(10,130,100,30);VIP_Price.setBounds(140,130,140,25);

		jlbQuantity.setBounds(10, 170, 100, 30);Quantity.setBounds(140, 170, 140, 25);

		jbtUpdate.setBounds(90, 220, 130, 25);//button.setBounds(180,220 ,90 ,25 );

		Product_Name.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					editActionPerformed(Prod_id,Prod_name,r);
				}
			}
		});

		Product_ID.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					editActionPerformed(Prod_id,Prod_name,r);
				}
			}
		});

		Normal_Price.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					editActionPerformed(Prod_id,Prod_name,r);
				}
			}
		});

		VIP_Price.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					editActionPerformed(Prod_id,Prod_name,r);
				}
			}
		});

		Quantity.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					editActionPerformed(Prod_id,Prod_name,r);
				}
			}
		});

		jbtUpdate.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					editActionPerformed(Prod_id,Prod_name,r);
				}
			}
		});
		jbtUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				editActionPerformed(Prod_id,Prod_name,r);
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

	private boolean isProductNameExist() throws SQLException
	{
		initializeDB();
		String Pro_name=Product_Name.getText();
		String queryString1="select Product_name from product where Product_name='"+Pro_name+"'";
		ResultSet rset=statement.executeQuery(queryString1);
		if(rset.next())
		{
			connection.close();
			return true;
		}
		connection.close();
		return false;
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

	private boolean isDatavalid() throws SQLException
	{
		if(isProductIdExist())
		{
			return false;
		}
		else if(isProductNameExist())
		{
			return false;
		}
		else if(Product_ID.getText().length()>15 || Product_ID.getText().equals(""))
			return false;
		else if(Product_Name.getText().length()>30 || Product_Name.getText().equals(""))
			return false;
		else if(Normal_Price.getText().length()>15|| isDigit(Normal_Price.getText()) || Normal_Price.getText().equals(""))
			return false;
		else if(VIP_Price.getText().length()>15 || isDigit(VIP_Price.getText()) || VIP_Price.getText().equals(""))
			return false;
		else if(Quantity.getText().length()>5 ||isDigit(Quantity.getText())|| Quantity.getText().equals(""))
			return false;
		return true;
	}

	private boolean isDigit(String text) {
		for(int i=0;i<text.length();i++)
		{
			if(!Character.isDigit(text.charAt(i)))
					return true;
		}
		return false;
	}

	private boolean isDatavalid1() throws SQLException
	{
		if(Product_ID.getText().length()>15 || Product_ID.getText().equals(""))
			return false;
		else if(Product_Name.getText().length()>30 || Product_Name.getText().equals(""))
			return false;
		else if(Normal_Price.getText().length()>15|| isDigit(Normal_Price.getText()) || Normal_Price.getText().equals(""))
			return false;
		else if(VIP_Price.getText().length()>15 || isDigit(VIP_Price.getText()) || VIP_Price.getText().equals(""))
			return false;
		else if(Quantity.getText().length()>5 ||isDigit(Quantity.getText())|| Quantity.getText().equals(""))
			return false;
		return true;
	}

	private void UpdatingData(final int r,String Prod_id) throws ClassNotFoundException, SQLException{
		initializeDB();

		String Pro_name=Product_Name.getText();
		String Pro_id=Product_ID.getText();
		Integer NormalPrice=Integer.parseInt(Normal_Price.getText());
		Integer VIPPrice=Integer.parseInt(VIP_Price.getText());
		Integer Qty=Integer.parseInt(Quantity.getText());

		String query="update product set Product_name='"+Pro_name+"',Product_id='"+Pro_id+"',Normal_price='"+NormalPrice+"',VIP_price='"+VIPPrice+"',Quantity='"+Qty+"' where Product_id='"+Prod_id+"'";

		statement.executeUpdate(query);
		connection.close();

		Product.product1.getModel().setValueAt(Pro_name, r, 0);
		Product.product1.getModel().setValueAt(Pro_id, r, 1);
		Product.product1.getModel().setValueAt(NormalPrice, r, 2);
		Product.product1.getModel().setValueAt(VIPPrice, r, 3);
		Product.product1.getModel().setValueAt(Qty, r, 4);
	}
	private void editActionPerformed(String Prod_id,String Prod_name,int  r){
		try {
			if(Prod_id.equals(Product_ID.getText()) && Prod_name.equals(Product_Name.getText()) && isDatavalid1())
			{
				UpdatingData(r,Prod_id);
				setVisible(false);
			}
			else if(Prod_id.equals(Product_ID.getText()) && !isProductNameExist() && isDatavalid1())
			{
				UpdatingData(r,Prod_id);
				setVisible(false);
			}
			else if(Prod_name.equals(Product_Name.getText()) && !isProductIdExist() && isDatavalid1())
			{
				UpdatingData(r,Prod_id);
				setVisible(false);
			}
			else if(isDatavalid()){
				UpdatingData(r,Prod_id);
				setVisible(false);
			}
			else
			{
				try {
					if(!Prod_id.equals(Product_ID.getText()) && isProductIdExist())
					{
						JOptionPane.showMessageDialog(null,"Your Product_ID has been existed", "Error", JOptionPane.ERROR_MESSAGE);
						//connection.close();
					}
					else if(!Prod_name.equals(Product_Name.getText()) && isProductNameExist())
					{
						JOptionPane.showMessageDialog(null,"Your Product_Name has been existed", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Input Data is not valid", "Error", JOptionPane.ERROR_MESSAGE);
						//connection.close();
					}

				}
				catch (HeadlessException | SQLException e1)
				{
					e1.printStackTrace();
				}
			}
		}
	 catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setTF(final int r){
		String P_name=(String)Product.product1.getValueAt(r,0);
		String P_code=(String)Product.product1.getValueAt(r,1);
		int N_price=(int)Product.product1.getValueAt(r,2);
		int V_price=(int)Product.product1.getValueAt(r,3);
		int Qty=(int)Product.product1.getValueAt(r,4);
		Product_Name.setText(P_name);
		Product_ID.setText(P_code);
		Normal_Price.setText(Integer.toString(N_price));
		VIP_Price.setText(Integer.toString(V_price));
		Quantity.setText(Integer.toString(Qty));
	}
}
