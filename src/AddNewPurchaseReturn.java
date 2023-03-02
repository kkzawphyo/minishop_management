

import javax.swing.*;
import javax.swing.border.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class AddNewPurchaseReturn extends JDialog
{
	public JSearchTextField id,name,r_date,q;

	private JLabel p_id, p_name, p_date, p_q;
	public StandardButton save, cancel, clear;
	private JPanel jp1 = new JPanel();
	private String pr_id, pr_name, pr_date, pr_q;
	private String errorName, errorId, errorpurdate,errorquantity;
	private JLabel eId = new JLabel();
	private JLabel eName = new JLabel();
	private JLabel erdate = new JLabel();
	private JLabel eq = new JLabel();
	private Border border = new JTextField().getBorder();
	private ImageIcon errorIcon = new ImageIcon("error.png");
	private ImageIcon correct = new ImageIcon("correctBig.png");

	public AddNewPurchaseReturn() throws Exception {
	}

	public AddNewPurchaseReturn(PurchaseR cu) throws Exception {

		this(null, true, cu);
		}

	public AddNewPurchaseReturn(Frame parent, boolean modal,PurchaseR cu)
			throws Exception, ClassNotFoundException {

		super(parent, modal);
		final PurchaseR c = cu;
		jp1.setLayout(null);
		jp1.add(p_id = new JLabel("Product id"));
		jp1.add(id = new JSearchTextField("eg. 1034...",15));
		jp1.add(p_name = new JLabel("Company name"));
		jp1.add(name = new JSearchTextField("eg. Mg Mg",30));
		jp1.add(p_date = new JLabel("Purchase_date"));
		jp1.add(r_date = new JSearchTextField("eg. YYYY-MM-DD",10));
		jp1.add(p_q = new JLabel("Quantity"));
		jp1.add(q = new JSearchTextField("eg. 2..",6));
		jp1.add(save = new StandardButton("Save"));
		jp1.add(cancel = new StandardButton("Cancel"));
		jp1.add(clear = new StandardButton("Clear"));

		p_name.setBounds(30, 50, 100, 30);
		p_id.setBounds(30, 100, 100, 30);
		p_date.setBounds(30, 150, 100, 30);
		p_q.setBounds(30, 200, 200, 30);

		name.setBounds(150, 50, 150, 30);
		id.setBounds(150, 100, 150, 30);
		r_date.setBounds(150, 150, 150, 30);
		q.setBounds(150, 200, 150, 30);

		save.setBounds(40, 250, 80, 30);
		cancel.setBounds(130, 250, 80, 30);
		clear.setBounds(220, 250, 80, 30);

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				pr_id = id.getText();
				pr_name = name.getText();
				pr_date = r_date.getText();
				pr_q = q.getText();
				eName.setVisible(true);
				eId.setVisible(true);
				erdate.setVisible(true);
				eq.setVisible(true);

				try {
					boolean flag1, flag2, flag3, flag4;
					if (isCompanyNameValid(pr_name)) {
						flag1 = true;
						jp1.add(eName);
						eName.setText(errorName);
						eName.setIcon(null);
						eName.setBounds(40, 280, 250, 30);

						System.out.print("company name valid");
					} else {
						flag1 = false;
						jp1.add(eName);
						eName.setText(errorName);
						eName.setIcon(errorIcon);
						eName.setHorizontalTextPosition(SwingConstants.RIGHT);
						eName.setBounds(40, 280, 250, 30);

						System.out.print("Company name not valid");
					}

					if (isProuductIdValid(pr_id)) {
						flag2 = true;
						jp1.add(eId);
						eId.setText(errorId);
						eId.setIcon(null);
						eId.setBounds(40, 310, 250, 30);

						System.out.print("Product id  valid");
					}

					else {
						flag2 = false;
						jp1.add(eId);
						eId.setText(errorId);
						eId.setIcon(errorIcon);
						eId.setHorizontalTextPosition(SwingConstants.RIGHT);
						eId.setBounds(40, 310, 250, 30);

						System.out.print("Product id not valid");
					}

					if (isPdateValid(pr_date)) {
						flag3 = true;
						jp1.add(erdate);
						erdate.setText(errorpurdate);
						erdate.setIcon(null);
						erdate.setBounds(40, 330, 250, 30);

					} else {
						flag3 = false;
						jp1.add(erdate);
						erdate.setText(errorpurdate);
						erdate.setIcon(errorIcon);
						erdate.setHorizontalTextPosition(SwingConstants.RIGHT);
						erdate.setBounds(40, 330, 250, 30);

					}


					if (isQuantityValid(pr_q)) {
						flag4 = true;
						jp1.add(eq);
						eq.setText(errorquantity);
						eq.setIcon(null);
						eq.setBounds(40,360, 250, 30);

					} else {
						flag4 = false;
						jp1.add(eq);
						eq.setText(errorquantity);
						eq.setIcon(errorIcon);
						eq.setHorizontalTextPosition(SwingConstants.RIGHT);
						eq.setBounds(40, 360, 250, 30);

					}

					if (flag1 && flag2 && flag3 && flag4) {

						c.insertTable(pr_id, pr_name,pr_q, pr_date);
						c.revalidate();
						JOptionPane.showMessageDialog(null, "Purchse return Added !",
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
				erdate.setVisible(false);
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

		this.setLocation(470, 170);
		setSize(400, 450);
		setResizable(false);
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
			name.setBorder(new LineBorder(Color.red));
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
			errorId = "<html><font color='red'>Product Id</font> too long !</html>";
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
			errorpurdate = "<html><font color='red'>Purchase return date</font> is wrong format !</html>";
			r_date.setBorder(new LineBorder(Color.red));
			return false;
		}
		if (pdate.length()==0) {
			errorpurdate = "<html><font color='red'>Purchase return date</font> is not inserted !</html>";
			r_date.setBorder(new LineBorder(Color.red));
			return false;
		}
		String datePattern = "\\d{4}-\\d{1,2}-\\d{1,2}";
		if (!pdate.matches(datePattern)) {
			errorpurdate = "<html><font color='red'>Purchase date</font> is wrong format !</html>";
			r_date.setBorder(new LineBorder(Color.red));
			return false;
		}
		errorpurdate = " ";
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
			errorquantity = "<html><font color='red'>Quantity amount is</font> not inserted</html>";
			q.setBorder(new LineBorder(Color.red));
			return false;
		}
		for (int i = 0; i < pri.length(); i++) {
			if (!Character.isDigit(pri.charAt(i))) {
				errorquantity = "<html><font color='red'>Quantity</font> can only contain numbers</html>";
				q.setBorder(new LineBorder(Color.red));
				return false;
			}
		}
		errorquantity = " ";
		q.setBorder(border);
		return true;
	}

}
