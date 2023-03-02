import java.sql.*;
public class exportFromDBMS {
	private static Object[][] data;
	private String[] columnNames={"Product_name","Product_id","Normal price","VIP price","Quantity","Product price"};
	public static void main(String[] args)throws SQLException,ClassNotFoundException
	{
		Object[][] test=tabledata();
	}
	public static Object[][] tabledata()throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		//System.out.println("Driver loaded");

		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/shop_managment_system","root","root");
		//System.out.println("Database connected");

		Statement statement=connection.createStatement();

		ResultSet resultSet=statement.executeQuery("select * from Product");

		
		resultSet.last();
		data=new Object[resultSet.getRow()][7];
		//System.out.print(resultSet.getRow());
		resultSet.first();
		//System.out.print(resultSet.getRow());
		resultSet.previous();
		//System.out.print(resultSet.getRow());
		int row=-1;
		while(resultSet.next()){
			++row;
		data[row][0]=(String)resultSet.getString(1);System.out.print(data[row][0]+"\t");
		data[row][1]=(String)resultSet.getString(2);System.out.print(data[row][1]+"\t");
		data[row][2]=(String)resultSet.getString(3);System.out.print(data[row][2]+"\t");
		data[row][3]=(String)resultSet.getString(4);System.out.print(data[row][3]+"\t");
		data[row][4]=(String)resultSet.getString(5);System.out.print(data[row][4]+"\t");
		data[row][5]=(String)resultSet.getString(6);System.out.println(data[row][5]);
		}
			//System.out.println(resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getString(3)+"\t"+resultSet.getString(4)+"\t"+resultSet.getString(5)+"\t"+resultSet.getString(6));

		connection.close();
		return data;
	}
}
