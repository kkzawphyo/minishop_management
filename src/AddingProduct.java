import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javafx.scene.layout.Border;
import net.java.dev.designgridlayout.DesignGridLayout;

class AddingProduct extends JDialog {

	private ImageIcon a;

	private Statement statement;
	private Connection connection;

	private Font font1=new Font("Times New Roman",Font.BOLD,13);
	private LineBorder tfBorder=new LineBorder(Color.green,2);

	private JLabel jlbname=new JLabel("Product Name");
	private JLabel jlbid=new JLabel("Product ID");
	private JLabel jlbN_price=new JLabel("Normal Price");
	private JLabel jlbVIP_price=new JLabel("VIP Price");
	private JLabel jlbQuantity=new JLabel("Quantity");
//	private JLabel jlbA_Quantity=new JLabel("á€±á€›á€¬á€€á€¹á€›á€½á€­á€¡á€±á€›á€¡á€�á€¼á€€á€¹=");

	private JSearchTextField Product_Name=new JSearchTextField("eg:  \"Pencile\"");
	private JSearchTextField Product_ID=new JSearchTextField("eg:  \"1234567890001\"");
	private JSearchTextField Normal_Price=new JSearchTextField("eg:  \"1000\"");
	private JSearchTextField VIP_Price=new JSearchTextField("eg:  \"800\"");
	private JSearchTextField Quantity=new JSearchTextField("eg:  \"100\"");
//	private JTextField Arrival_Quantity=new JTextField();

	private StandardButton Add=new StandardButton("ADD");
	private StandardButton Clear=new StandardButton("Clear");
	//private JButton Update=new JButton("Update");


	public AddingProduct(JFrame parent,boolean modal)
	{
		super(parent,modal);
		setTitle("Adding Product");
		setSize(300,300);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		jlbname.setFont(font1);
		jlbid.setFont(font1);
		jlbN_price.setFont(font1);
		jlbVIP_price.setFont(font1);
		jlbQuantity.setFont(font1);

		ImageIcon img3=new ImageIcon("fav/edit2.png");
		Image i3=img3.getImage();
		Image editImg=i3.getScaledInstance(15,15,Image.SCALE_SMOOTH);
		a=new ImageIcon(editImg);

		initializeDB();
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
		//add(jlbA_Quantity);
		//add(Arrival_Quantity);

		add(Add);
		add(Clear);

		jlbname.setBounds(10,10,100,30);Product_Name.setBounds(130,10,140,25);

		jlbid.setBounds(10,50,100,30);Product_ID.setBounds(130,50,140,25);

		jlbN_price.setBounds(10,90,100,30);Normal_Price.setBounds(130,90,140,25);

		jlbVIP_price.setBounds(10,130,100,30);VIP_Price.setBounds(130,130,140,25);

		jlbQuantity.setBounds(10, 170, 100, 30);Quantity.setBounds(130, 170, 140, 25);

		//jlbA_Quantity.setBounds(780, 10, 150, 30);Arrival_Quantity.setBounds(790, 40, 100, 25);

		Add.setBounds(35, 220, 100, 25);
		Clear.setBounds(155, 220, 100, 25);

		Product_Name.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					TextFieldListener(a);
				}
			}
		});
		Product_ID.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					TextFieldListener(a);
				}
			}
		});

		Normal_Price.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					TextFieldListener(a);
				}
			}
		});

		VIP_Price.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					TextFieldListener(a);
				}
			}
		});

		Quantity.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					TextFieldListener(a);
				}
			}
		});

		/*Arrival_Quantity.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					TextFieldListener();
				}
			}
		});*/

		Add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				TextFieldListener(a);
			}
		});

		Add.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					TextFieldListener(a);
				}
			}
		});

		Clear.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					Product_Name.setText(null);
					Product_ID.setText(null);
					Normal_Price.setText(null);
					VIP_Price.setText(null);
					Quantity.setText(null);
					//Arrival_Quantity.setText(null);
				}
			});
		Clear.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					Product_Name.setText(null);
					Product_ID.setText(null);
					Normal_Price.setText(null);
					VIP_Price.setText(null);
					Quantity.setText(null);
				//	Arrival_Quantity.setText(null);
				}
			}
		});
	}
	private void TextFieldListener(ImageIcon g){
		try {
			if((isDatavalid()))
			{
				try
				{
					ImageIcon img=new ImageIcon("fav/succ.png");
					Image i=img.getImage();
					Image Img=i.getScaledInstance(35,35,Image.SCALE_SMOOTH);
					ImageIcon gg=new ImageIcon(Img);
					JOptionPane.showMessageDialog(null, "success","Done",JOptionPane.INFORMATION_MESSAGE,gg);
					AddingData(a);
					//setVisible(false);
					Product_Name.setText(null);
					Product_ID.setText(null);
					Normal_Price.setText(null);
					VIP_Price.setText(null);
					Quantity.setText(null);
					Product_Name.requestFocus();
				//	Arrival_Quantity.setText(null);
				}
				catch (ClassNotFoundException | SQLException e1)
				{
					e1.printStackTrace();
				}

			}
			else
			{
				try {
					if(isProductIdExist())
					{
						JOptionPane.showMessageDialog(null,"Your Product_ID has been existed", "Error", JOptionPane.ERROR_MESSAGE);
						//connection.close();
					}
					else if(isProductNameExist())
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
		catch (SQLException e)
		{
			e.printStackTrace();
		}
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
		//else if(Product_Price.getText().length()>15 ||isDigit(Product_Price.getText()) || Product_Price.getText().equals(""))
			//return false;
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
	private void AddingData(ImageIcon g) throws ClassNotFoundException, SQLException
	{
		initializeDB();
		String Pro_id=Product_ID.getText();
		String pro_name=Product_Name.getText();
		Integer NormalPrice=Integer.parseInt(Normal_Price.getText());
		Integer VIPPrice=Integer.parseInt(VIP_Price.getText());
		Integer Qty=Integer.parseInt(Quantity.getText());
		//String ProductPrice=Product_Price.getText();


		String query="insert into Product values('"+pro_name+"','"+Pro_id+"','"+NormalPrice+"','"+VIPPrice+"','"+Qty+"')";

		statement.executeUpdate(query);
		connection.close();

		Object[] row = {pro_name,Pro_id,NormalPrice,VIPPrice,Qty,g};
		DefaultTableModel model = (DefaultTableModel)Product.product1.getModel();
		model.addRow(row);
	}

	/*private boolean isletter(String s)
	{
		for(int i=0;i<s.length();i++)
		{
			if(Character.isLetter(s.charAt(i)))
					return true;
		}
		return false;
	}*/
	/*public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AddingProduct frame=new AddingProduct();
		frame.setSize(280,330);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}*/

}