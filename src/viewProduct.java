import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class viewProduct extends JFrame{
	private Object[][] data;
	public viewProduct() throws ClassNotFoundException, SQLException
	{

	}
	public Object[][] tabledata()throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		//System.out.println("Driver loaded");

		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/shop_managment_system","root","root");
		//System.out.println("Database connected");

		Statement statement=connection.createStatement();

		ResultSet resultSet=statement.executeQuery("select * from Product");

		resultSet.last();
		data=new Object[resultSet.getRow()][5];
		resultSet.first();
		resultSet.previous();

		int row=-1;
		while(resultSet.next()){
			++row;
		data[row][0]=(String)resultSet.getString(1);//System.out.print(data[row][0]+"\t");
		data[row][1]=(String)resultSet.getString(2);//System.out.print(data[row][1]+"\t");
		data[row][2]=Integer.parseInt(resultSet.getString(3));//System.out.print(data[row][2]+"\t");
		data[row][3]=Integer.parseInt(resultSet.getString(4));//System.out.print(data[row][3]+"\t");
		data[row][4]=Integer.parseInt(resultSet.getString(5));//System.out.print(data[row][4]+"\t");
		//data[row][5]=Integer.parseInt(resultSet.getString(6));//System.out.println(data[row][5]);
		}
		connection.close();
		return data;
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		JFrame frame=new viewProduct();
		Dimension d=new Dimension(500,200);
		frame.setMinimumSize(d);
		frame.setExtendedState(frame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}
}
