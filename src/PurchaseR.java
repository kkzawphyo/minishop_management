import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class PurchaseR extends JPanel {

	private JTable purchasereturntable;
	private DefaultTableModel tableModel;
	private Statement stmt;
	private Font font1=new Font("Times New Roman",Font.BOLD,13);

	public PurchaseR() throws Exception {

		showTable();

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());

		tablePanel.add(new JScrollPane(purchasereturntable), BorderLayout.CENTER);
		setLayout(new BorderLayout());
		add(tablePanel, BorderLayout.CENTER);

	}

	private void initializeDB() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");

			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost/shop_managment_system", "root",
					"root");
			System.out.println("Database Connected");
			stmt = connection.createStatement();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void showTable() throws Exception {

		initializeDB();
		String query = "select * from purchase_return";
		ResultSet rs = stmt.executeQuery(query);
		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}
		tableModel = new DefaultTableModel(data, columnNames);
		purchasereturntable = new JTable(tableModel)
		{
			public boolean isCellEditable(int row, int column){
				return false;
			}
			public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
			Component c=super.prepareRenderer(renderer, row, column);
			//Color alternateColor=new Color(252,242,206);
			//Color whiteColor=Color.white;
			if(!c.getBackground().equals(getSelectionBackground())){
				Color bg=(row%2==0?new Color(118, 215, 196  ):new Color(208, 211, 212  ));
				c.setBackground(bg);
				bg=null;
			}


			return c;
	}
	};
	JTableHeader header=purchasereturntable.getTableHeader();
	header.setBackground(Color.white);
	purchasereturntable.setRowHeight(25);
	purchasereturntable.getTableHeader().setFont(font1);
	purchasereturntable.getTableHeader().setReorderingAllowed(false);
	purchasereturntable.setShowGrid(false);
	}

	public void insertTable(String productId, String companyName,String quantity,String purchaseDate) throws Exception {

		initializeDB();
		String queryString = "insert into purchase_return values" + "('" + productId
				+ "','" + companyName + "','" +quantity + "','" + purchaseDate + "')";
		stmt.executeUpdate(queryString);
		Object[] data = new Object[] { productId, companyName, quantity, purchaseDate };
		tableModel.addRow(data);

	}

	public PurchaseR getCustomerClass() {
		return this;
	}

}
