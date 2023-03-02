import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.JComboBox;
import javax.swing.JList;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class jcombo extends JFrame{
	private JComboBox jmb;
	private static Object[] data;
	public jcombo() throws ClassNotFoundException, SQLException{
		data=Data();
		Arrays.sort(data);
		jmb = new JComboBox(data);
		jmb.setEditable(true);
		AutoCompleteDecorator.decorate(jmb);

		add(new JScrollPane(jmb));
		//add(jmb);
	   jmb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String str=((JTextField)jmb.getEditor().getEditorComponent()).getText();
				System.out.println(str);
			}
		});
	}
	public static Object[] Data()throws SQLException,ClassNotFoundException
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
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
		jcombo frame=new jcombo();
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}