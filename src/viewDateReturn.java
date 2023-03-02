
import java.awt.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;

@SuppressWarnings("serial")
public class viewDateReturn extends JDialog
{
	public viewDateReturn(String text) throws Exception{
		this(null,true,text);
	}

	public viewDateReturn(Frame parent,boolean model,String text) throws Exception
	{
		super(parent,model);
		if(text.length()>0)
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded viewDate");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/shop_managment_system", "root", "root");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Purchase_return where Return_Date='"+text+"'");

			ResultSetMetaData metaData = rs.getMetaData();

			int colCount = metaData.getColumnCount();

			// names of columns
			 Vector<String> colNames = new Vector<String>();
			    for (int column = 1; column <= colCount; column++) {
			        colNames.add(metaData.getColumnName(column));
			    }

			// data of the table
			Vector<Vector<Object>> data = new Vector<Vector<Object>>();
			while (rs.next())
			{
				Vector<Object> vector = new Vector<Object>();
				for (int columnIndex = 1; columnIndex <= colCount; columnIndex++)
					vector.add(rs.getObject(columnIndex));

				data.add(vector);
			}

				JTable t=new JTable(data,colNames);

                add(new JScrollPane(t));

				setSize(800,300);
				setVisible(true);
		}

	}
}
