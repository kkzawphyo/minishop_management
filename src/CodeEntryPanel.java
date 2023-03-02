
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
public class CodeEntryPanel extends JPanel{
	private JButton add=new JButton("Add");
	private JTextField productCode=new JTextField();
	private JTextField quantity=new JTextField();
	private JComboBox customerName;
	InitialPanel initialPanel=new InitialPanel();
	private Statement stmt;
	private Object CName;
	public CodeEntryPanel(){
		  //setLayout(null);
		  //add(jbtTotal);
		  //setSize(400,400);
		  //setPreferredSize(500,400);
		  //jbtTotal.setBounds(getWidth()/2-40,getHeight()/2-40,20,20);

			//InitializeDB
			initializeDB();

			String queryString="select Customer_name"+" from customer";
			try{
			ResultSet rs=stmt.executeQuery(queryString);
			//ResultSetMetaData rsMetaData=rs.getMetaData();
			//int count=0;
			ArrayList<Object> name=new ArrayList<Object>();
			while(rs.next()){
				name.add(rs.getObject(1));
			}

			customerName=new JComboBox(name.toArray());


			//connecting database
			}
			catch(SQLException ex){
				ex.printStackTrace();
			}

			//Adding event to customerName
			customerName.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					CName=customerName.getSelectedItem();

				}
			});

			setLayout(new GridBagLayout());
			GridBagConstraints gbc=new GridBagConstraints();
			gbc.gridy=0;
			JPanel panel=new JPanel();
			panel.setLayout(new GridLayout(4,2,10,10));
			panel.add(new JLabel("Product Code"));
			panel.add(productCode);
			panel.add(new JLabel("Quantity"));
			panel.add(quantity);
			panel.add(new JLabel("Customer Name"));
			panel.add(customerName);


			//adding event
			add.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					adding();

				}
			});


			//panel.add(new JLabel(""));
			panel.add(add);
			add(panel,gbc);






	}
	public void adding(){
		try{
			String code=productCode.getText();
			String price=null;
			String productName=null;

			String conditionPrice=null;
			if(CName==null){
				conditionPrice="Normal_price";
			}
			else
				conditionPrice="VIP_price";
			//String price=null;
		String queryString="select Product_name,"+conditionPrice+" from product"+" where Product_id="+code;
		ResultSet rset=stmt.executeQuery(queryString);


		if(rset.next()){
			productName=rset.getString(1);
			price=rset.getString(2);
		}

		Object[] data={code,productName,quantity.getText(),price};
		if(productName!=null)
		{	initialPanel.tableModel.addRow(data);
		initialPanel.noProduct.setText(null);
		}
		else
			initialPanel.noProduct.setText("This product is not in the array");
		if(CName==null)
			initialPanel.customerName.setText(" ");
		else
			initialPanel.customerName.setText("By Customer "+CName);
		productCode.setText(null);
		quantity.setText(null);
		productCode.setFocusable(true);

		//placing cursor on textField
		productCode.requestFocus();
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
	}
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
			JFrame frame=new JFrame();
			//frame.setLayout(null);
			JPanel p=new CodeEntryPanel();
			System.out.print(p.getHeight());
			frame.setSize(1000,1200);
			frame.add(p);

			frame.setTitle("JTable Testing");
			//frame.pack();
			//frame.setUndecorated(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

	}

}
