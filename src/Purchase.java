

import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Purchase extends JPanel {

	private JTable purchaseTable;
	private DefaultTableModel tableModel;
	private Statement stmt;
	private Font font1=new Font("Times New Roman",Font.BOLD,13);

	public Purchase() throws Exception {

		showTable();

		JPanel tablePanel = new JPanel(new BorderLayout());


		tablePanel.add(new JScrollPane(purchaseTable), BorderLayout.CENTER);
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
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void showTable() throws Exception {

		initializeDB();
		String queryString = "select * from purchase";
		ResultSet rs = stmt.executeQuery(queryString);
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
		purchaseTable = new JTable(tableModel){
			public boolean isCellEditable(int row, int column){
				return false;
			}
			public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
				Component c=super.prepareRenderer(renderer, row, column);

				c.setBackground(row%2==0?new Color(118, 215, 196  ):new Color(208, 211, 212  ));
				if(isRowSelected(row)) {c.setForeground(Color.red);} else c.setForeground(Color.BLACK);
				return c;
		}
		};
		JTableHeader header=purchaseTable.getTableHeader();
		header.setBackground(Color.white);
		purchaseTable.setRowHeight(25);
		purchaseTable.setRowMargin(0);
		purchaseTable.getTableHeader().setFont(font1);
		purchaseTable.getTableHeader().setReorderingAllowed(false);
		purchaseTable.setShowGrid(false);

	}

	public void insertTable(String productId, String companyName,
			String purchaseDate, String purchasePrice, String receiveDate,
			String quantity) throws SQLException, ClassNotFoundException {

		initializeDB();
		System.out.println(receiveDate);
		String queryString = "insert into purchase values" + "('" + productId
				+ "','" + companyName + "','" + purchaseDate + "','"
				+ purchasePrice + "','" + receiveDate + "','" + quantity + "')";
		stmt.executeUpdate(queryString);
		Object[] data = new Object[] { productId, companyName, purchaseDate,
				purchasePrice, receiveDate, quantity };
		tableModel.addRow(data);
		String queryStringForProduct = "select Quantity from product where Product_id='"
				+ productId + "'";
		ResultSet rs = stmt.executeQuery(queryStringForProduct);
		int q = 1;
		if (rs.next()) {
			q = Integer.parseInt(rs.getString(1));
		}
		System.out.println(q);
		System.out.println(quantity);
		int total = q + Integer.parseInt(quantity);
		System.out.println(total);
		String update = "update product set Quantity='" + total + "'"
				+ " where Product_id='" + productId + "'";
		stmt.executeUpdate(update);

	}

	public Purchase getCustomerClass() {
		return this;
	}

	//public JTable getTable() {
	//	return purchaseTable;
	//}
}
