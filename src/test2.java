import java.awt.*;
import javax.swing.*;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.*;
public class test2 extends JFrame{
	
	private static Object[] data;
	private static  JList jmb;
	private static JTextField jtf=new JTextField(10);
	private ListModel source = new DefaultListModel(); // use a model of your choice here;
    private FilteredListModel filteredListModel = new FilteredListModel(source);
    
	public test2() throws ClassNotFoundException, SQLException
	{
		setLayout(new BorderLayout());
		data=tableData();
		Arrays.sort(data);
		jmb=new JList(data);
		add(jtf,BorderLayout.NORTH);
		add(new JScrollPane(jmb),BorderLayout.CENTER);
		jtf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String stf=jtf.getText();
				for(int i=0;i<data.length;i++)
				{
					if(stf.equals(data[i]))
					{
						jmb.setSelectedIndex(i);
						break;
					}
				}
			}
		});
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		
		test2 frame=new test2();
		frame.pack();
		frame.setVisible(true);
	}
	public static Object[] tableData()throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		//System.out.println("Driver loaded");

		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/shop_managment_system","root","root");
		//System.out.println("Database connected");

		Statement statement=connection.createStatement();

		ResultSet resultSet=statement.executeQuery("select * from Product");


		resultSet.last();
		data=new Object[resultSet.getRow()];
		resultSet.first();
		resultSet.previous();

		int row=-1;
		while(resultSet.next()){
			++row;
		data[row]=(String)resultSet.getString(1);//System.out.print(data[row][0]+"\t");
		}


		connection.close();
		return data;
	}
}
