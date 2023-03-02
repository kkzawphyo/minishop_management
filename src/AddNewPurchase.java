


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class AddNewPurchase extends JDialog {

	public JSearchTextField id = new JSearchTextField("eg. 1028...",15);
	public JSearchTextField name = new JSearchTextField("eg. Aung Aung..",30);
	public JSearchTextField p_date = new JSearchTextField("eg. YYYY-MM-DD" ,10 );
	public JSearchTextField price = new JSearchTextField("eg. 5000",8);
	public JSearchTextField r_date = new JSearchTextField("eg. YYYY-MM-DD" ,10);
	public JSearchTextField q = new JSearchTextField("eg. 5",6);
	private JLabel cu_id, cu_name, cu_date, cu_price, re_date, cu_q;
	public StandardButton save, cancel, clear;
	private JPanel jp1 = new JPanel();
	private String c_id, c_name, c_date, c_price, _date, c_q = null;
	private String errorName, errorId, errorPurdate, errorPrice, errorRedate,
			errorquantity = " ";
	private JLabel eId = new JLabel();
	private JLabel eName = new JLabel();
	private JLabel epdate = new JLabel();
	private JLabel eprice = new JLabel();
	private JLabel erdate = new JLabel();
	private JLabel eq = new JLabel();
	private Border border = new JTextField().getBorder();
	private ImageIcon errorIcon = new ImageIcon("error.png");
	private ImageIcon correct = new ImageIcon("correctBig.png");
	public AddNewPurchase() throws Exception {

	}

	public AddNewPurchase(Purchase cu) throws Exception {

		this(null, true, cu);
	}

	public AddNewPurchase(Frame parent, boolean modal,Purchase cu)
			throws Exception, ClassNotFoundException {

		super(parent, modal);
		final Purchase c = cu;

		jp1.setLayout(null);
		jp1.add(cu_id = new JLabel("Product id"));
		jp1.add(id );
		jp1.add(cu_name = new JLabel("Company name"));
		jp1.add(name);
		jp1.add(cu_date = new JLabel("Purchase_date"));
		jp1.add(p_date);
		jp1.add(cu_price = new JLabel("Purchase_price"));
		jp1.add(price);
		jp1.add(re_date = new JLabel("Receice_date"));
		jp1.add(r_date);
		jp1.add(cu_q = new JLabel("Quantity"));
		jp1.add(q);
		jp1.add(save = new StandardButton("Save"));
		jp1.add(cancel = new StandardButton("Cancel"));
		jp1.add(clear = new StandardButton("Clear"));

		cu_name.setBounds(30, 50, 100, 30);
		cu_id.setBounds(30, 100, 100, 30);
		cu_date.setBounds(30, 150, 100, 30);
		cu_price.setBounds(30, 200, 100, 30);
		re_date.setBounds(30, 250, 200, 30);
		cu_q.setBounds(30, 300, 200, 30);

		name.setBounds(150, 50, 150, 30);
		id.setBounds(150, 100, 150, 30);
		p_date.setBounds(150, 150, 150, 30);
		price.setBounds(150, 200, 150, 30);
		r_date.setBounds(150, 250, 150, 30);
		q.setBounds(150, 300, 150, 30);

		save.setBounds(40, 350, 80, 30);
		cancel.setBounds(130, 350, 80, 30);
		clear.setBounds(220, 350, 80, 30);

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				c_id = id.getText();
				c_name = name.getText();
				c_date = p_date.getText();
				c_price = price.getText();
				_date = r_date.getText();
				c_q = q.getText();
				eName.setVisible(true);
				eId.setVisible(true);
				epdate.setVisible(true);
				eprice.setVisible(true);
				erdate.setVisible(true);
				eq.setVisible(true);

				try {
					boolean flag1, flag2, flag3, flag4, flag5, flag6;
					if (isCompanyNameValid(c_name)) {
						flag1 = true;
						jp1.add(eName);
						eName.setText(errorName);
						eName.setIcon(null);
						eName.setBounds(40, 380, 250, 30);

						System.out.print("company name valid");
					} else {
						flag1 = false;
						jp1.add(eName);
						eName.setText(errorName);
						eName.setIcon(errorIcon);
						eName.setHorizontalTextPosition(SwingConstants.RIGHT);
						eName.setBounds(40, 380, 250, 30);

						System.out.print("Company name not valid");
					}

					if (isProuductIdValid(c_id)) {
						flag2 = true;
						jp1.add(eId);
						eId.setText(errorId);
						eId.setIcon(null);
						eId.setBounds(40, 410, 250, 30);

						System.out.print("Product id  valid");
					}

					else {
						flag2 = false;
						jp1.add(eId);
						eId.setText(errorId);
						eId.setIcon(errorIcon);
						eId.setHorizontalTextPosition(SwingConstants.RIGHT);
						eId.setBounds(40, 410, 250, 30);

						System.out.print("Product id not valid");
					}

					if (isPdateValid(c_date)) {
						flag3 = true;
						jp1.add(epdate);
						epdate.setText(errorPurdate);
						epdate.setIcon(null);
						epdate.setBounds(40, 440, 250, 30);

					} else {
						flag3 = false;
						jp1.add(epdate);
						epdate.setText(errorPurdate);
						epdate.setIcon(errorIcon);
						epdate.setHorizontalTextPosition(SwingConstants.RIGHT);
						epdate.setBounds(40, 440, 250, 30);

					}

					if (isPriceValid(c_price)) {
						flag4 = true;
						jp1.add(eprice);
						eprice.setText(errorPrice);
						eprice.setIcon(null);
						eprice.setBounds(40, 470, 250, 30);

					} else {
						flag4 = false;
						jp1.add(eprice);
						eprice.setText(errorPrice);
						eprice.setIcon(errorIcon);
						eprice.setHorizontalTextPosition(SwingConstants.RIGHT);
						eprice.setBounds(40, 470, 250, 30);

					}
					if (isRdateValid(_date)) {
						flag5 = true;
						jp1.add(erdate);
						erdate.setText(errorRedate);
						erdate.setIcon(null);
						erdate.setBounds(40, 500, 250, 30);

					} else {
						flag5 = false;
						jp1.add(erdate);
						erdate.setText(errorRedate);
						erdate.setIcon(errorIcon);
						erdate.setHorizontalTextPosition(SwingConstants.RIGHT);
						erdate.setBounds(40, 500, 250, 30);

					}

					if (isQuantityValid(c_q)) {
						flag6 = true;
						jp1.add(eq);
						eq.setText(errorquantity);
						eq.setIcon(null);
						eq.setBounds(40, 530, 250, 30);

					} else {
						flag6 = false;
						jp1.add(eq);
						eq.setText(errorquantity);
						eq.setIcon(errorIcon);
						eq.setHorizontalTextPosition(SwingConstants.RIGHT);
						eq.setBounds(40, 530, 250, 30);

					}

					if (flag1 && flag2 && flag3 && flag4 && flag5 && flag6) {

						c.insertTable(c_id, c_name, c_date, c_price, _date, c_q);
						c.revalidate();
						JOptionPane.showMessageDialog(null, "New Purchase Added !",
								"", JOptionPane.INFORMATION_MESSAGE, correct);
						dispose();

					}

				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name.setText(null);
				name.setBorder(border);
				eName.setVisible(false);
				id.setText(null);
				id.setBorder(border);
				eId.setVisible(false);
				p_date.setText(null);
				p_date.setBorder(border);
				epdate.setVisible(false);
				price.setText(null);
				price.setBorder(border);
				eprice.setVisible(false);
				r_date.setText(null);
				r_date.setBorder(border);
				erdate.setVisible(false);
				q.setText(null);
				q.setBorder(border);
				eq.setVisible(false);
			}
		});

		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});

		add(jp1);
		setResizable(false);
		//this.setLocation(470, 170);
		setSize(400, 600);
		setVisible(true);
	}

	public boolean isCompanyNameValid(String companyName) {
		if (companyName.length() == 0) {
			errorName = "<html><font color='red'>Company Name</font> cannnot be empty !</html>";
			name.setBorder(new LineBorder(Color.red));
			return false;
		}

		if (companyName.length() > 30) {
			name.setBorder(new LineBorder(Color.red));
			errorName = "<html><font color='red'>Company Name</font> too long</html> ";
			return false;
		}

		errorName = " ";
		name.setBorder(border);
		return true;
		// color the border of customer name text field
		// empty the text field

	}

	public boolean isProuductIdValid(String productId)
			throws ClassNotFoundException, SQLException {

		boolean c = false;
		if (productId.length() == 0) {
			errorId = "<html><font color='red'>product Id</font> cannot be empty !</html>";
			id.setBorder(new LineBorder(Color.red));
			// a=false;
			return false;
		}

		if (productId.length() > 15) {
			errorId = "<html><font color='red'>Customer Id</font> too long !</html>";
			id.setBorder(new LineBorder(Color.red));
			// b=false;
			return false;
		}

		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Driver Loaded");
		Connection connection = DriverManager.getConnection(
				"jdbc:mysql://localhost/shop_managment_system", "root", "root");
		System.out.println("Database Connected");
		Statement statement = connection.createStatement();
		ResultSet resultset = statement
				.executeQuery("select Product_id from product");

		while (resultset.next()) {
			if (productId.equals(resultset.getString(1))) {
				c = true;
			}
			;
		}

		for (int i = 0; i < productId.length(); i++) {
			if (!Character.isDigit(productId.charAt(i))) {
				errorId = "<html><font color='red'>Customer Id</font> can only contain digit</html>";
				id.setBorder(new LineBorder(Color.red));
				// d=false;
				return false;
			}
		}
		if (c) {
			errorId = " ";
			id.setBorder(border);
			return true;
		} else {
			errorId = "<html><font color='red'>Product Id</font> is not in your Product list !</html>";
			id.setBorder(new LineBorder(Color.RED));
			return false;
		}
	}

	public boolean isPdateValid(String pdate) {

		if (pdate.length() > 10) {
			errorPurdate = "<html><font color='red'>Purchase date</font> is wrong format !</html>";
			p_date.setBorder(new LineBorder(Color.red));
			return false;
		}
		String datePattern = "\\d{4}-\\d{1,2}-\\d{1,2}";
		if (!pdate.matches(datePattern)) {
			errorPurdate = "<html><font color='red'>Purchase date</font> is wrong format !</html>";
			p_date.setBorder(new LineBorder(Color.red));
			return false;
		}
		errorPurdate = " ";
		p_date.setBorder(border);
		return true;
	}

	public boolean isPriceValid(String pri) {

		if (pri.length() > 15) {
			errorPrice = "<html><font color='red'>Purchase price</font> is too much</html>";
			price.setBorder(new LineBorder(Color.red));
			return false;
		}
		if (pri.length()==0) {
			errorPrice = "<html><font color='red'>Price amount </font> is not inserted </html>";
			price.setBorder(new LineBorder(Color.red));
			return false;
		}
		for (int i = 0; i < pri.length(); i++) {
			if (!Character.isDigit(pri.charAt(i))) {
				errorPrice = "<html><font color='red'>purchase price</font> can only contain numbers</html>";
				price.setBorder(new LineBorder(Color.red));
				return false;
			}
		}
		errorPrice = " ";
		price.setBorder(border);
		return true;
	}

	public boolean isRdateValid(String pri) {

		if (pri.length() > 10) {
			errorRedate = "<html><font color='red'>Receive date</font> is wrong format</html>";
			r_date.setBorder(new LineBorder(Color.red));
			return false;
		}
		if (pri.length() == 0) {
			errorRedate = "<html><font color='red'>Receive date</font> is not inserted</html>";
			r_date.setBorder(new LineBorder(Color.red));
			return false;
		}
		String datePattern = "\\d{4}-\\d{1,2}-\\d{1,2}";
		if (!pri.matches(datePattern)) {
			errorPurdate = "<html><font color='red'>Purchase date</font> is wrong format !</html>";
			p_date.setBorder(new LineBorder(Color.red));
			return false;
		}
		errorRedate = " ";
		r_date.setBorder(border);
		return true;
	}

	public boolean isQuantityValid(String pri) {

		if (pri.length() > 6) {
			errorquantity = "<html><font color='red'>Quantity amount is</font> too many</html>";
			q.setBorder(new LineBorder(Color.red));
			return false;
		}
		if (pri.length()==0) {
			errorquantity = "<html><font color='red'>Quantity amount </font> is not inserted </html>";
			q.setBorder(new LineBorder(Color.red));
			return false;
		}

		for (int i = 0; i < pri.length(); i++) {
			if (!Character.isDigit(pri.charAt(i))) {
				errorquantity = "<html><font color='red'>Quantity amount</font> can only contain numbers</html>";
				q.setBorder(new LineBorder(Color.red));
				return false;
			}
		}
		errorquantity = " ";
		q.setBorder(border);
		return true;
	}

}
