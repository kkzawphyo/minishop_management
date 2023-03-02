
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.event.*;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.plaf.basic.BasicToolBarUI;

import javafx.scene.control.ComboBox;

import javax.swing.*;
public class ToolPanel extends JPanel{
	//public JButton jbtNew=new JButton(new ImageIcon("image/new file.png"));
	//public JButton jbtSave=new JButton(new ImageIcon("image/save.png"));
	//public JButton jbtDelete=new JButton(new ImageIcon("image/delete.jpg"));
	//public JButton jbtForward=new JButton(new ImageIcon("image/forward.png"));
	//public JButton jbtBackward=new JButton(new ImageIcon("image/backward.png"));
	public JTextField barCode=new JTextField(30);
	//public JButton jbtPrint=new JButton(new ImageIcon("image/print.png"));
	//public JButton manualInput=new JButton("ManualInput");
	public JComboBox customerName;
	//public JComboBox language;
	public Object CName=null;
	private Statement stmt;
	public ToolPanel(){
		JToolBar toolBar=new JToolBar();
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
		//toolBar.add(new JButton("New"));
		//toolBar.add(jbtNew);

		toolBar.setFloatable(false);
		//toolBar.setBackground(Color.black);

		//jbtNew.setFocusPainted(false);
		//jbtNew.setContentAreaFilled(false);
		//jbtNew.setBackground(null);
		//jbtPrint.setBackground(null);
		//jbtSave.setBackground(null);
	//	jbtForward.setBackground(null);
		//jbtBackward.setBackground(null);
		//toolBar.add(jbtSave);
	//	toolBar.add(jbtDelete);
		//toolBar.add(jbtBackward);
		//toolBar.add(jbtForward);
		//toolBar.add(jbtPrint);
		toolBar.add(new JLabel("BarCode:"));
		toolBar.add(barCode);
		toolBar.setBackground(Color.LIGHT_GRAY);
		toolBar.setOpaque(true);


		//jbtBackward.setBorderPainted(false);
		//jbtForward.setBorderPainted(false);
		//toolBar.add(manualInput);
		//toolBar.setFocusable(true);
		barCode.setFocusable(true);
		barCode.requestFocus();

		//jbtNew.setBorderPainted(false);
		//jbtSave.setBorderPainted(false);
		//jbtDelete.setBorderPainted(false);
		//jbtForward.setBorderPainted(false);
		//jbtBackward.setBorderPainted(false);
		//jbtPrint.setBorderPainted(false);



		initializeDB();

		String queryString="select Customer_name"+" from customer";
		try{
		ResultSet rs=stmt.executeQuery(queryString);
		//ResultSetMetaData rsMetaData=rs.getMetaData();
		//int count=0;
		ArrayList<Object> name=new ArrayList<Object>();
		name.add(0, "Normal Customer");
		while(rs.next()){
			name.add(rs.getObject(1));
		}

		customerName=new JComboBox(name.toArray());
		//customerName.add("Normal_Customer", 0);
		customerName.setSelectedIndex(0);
		stmt.close();
		CName=customerName.getSelectedItem();
		//connecting database
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}

		//Adding event to customerName
		customerName.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				CName=customerName.getSelectedItem();
			}
		});


		toolBar.add(customerName);

		//toolBar.add(language);
		//Adding event to manualInput



		setLayout(new BorderLayout());


		add(toolBar,BorderLayout.NORTH);
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
		JFrame frame=new JFrame();
		ToolPanel panel=new ToolPanel();
		frame.add(panel);
		frame.setTitle("JTable Testing");
		frame.pack();
		//frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
