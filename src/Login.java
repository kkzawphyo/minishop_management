
import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Login extends JFrame{
	/**
	 *
	 */
	private Statement statement;
	private Connection connection;
	private static final long serialVersionUID = 1L;
	//panel
	private JTextField userName=new JTextField(10);
	private JPasswordField password=new JPasswordField(10);
	//konoha
	private JTextField Newname=new JTextField(10);
	private JPasswordField NewPassword=new JPasswordField(10);
	private JPasswordField Comfirm=new JPasswordField(10);
	//uchiha
	private JTextField Currentname=new JTextField(10);
	private JPasswordField Currentpass=new JPasswordField(10);
	//private JPasswordField confirmcreate=new JPasswordField(10);

	private ImageIcon icon=new ImageIcon("D:/JavaTesting2016/Shop Management System/image/Market.png");

	private JButton login=new JButton("Login");
	private JButton cancel=new JButton("Cancel");
	private JButton reset=new JButton("Reset Password");
	private JButton info=new JButton("Team's Info");
	//panel
	private JButton Done=new JButton("Done");
	//private JButton back=new JButton("<Back");
	private JButton konohacancel=new JButton("Cancel");
	//konoha
	private JButton backacc=new JButton("<Back");
	private JButton cancelacc=new JButton("Cancel");
	private JButton Next=new JButton("Next>");
	//uchiha
	private JButton infoback=new JButton("<Back");
	//senju
	Font largeFont=new Font("TimesRoman",Font.BOLD,30);

	JLabel title=new JLabel("Mini_Market Login");
	JLabel incorrect=new JLabel("User name or password is incorrected!");
	JLabel user=new JLabel("Account Name");
	JLabel pass=new JLabel("Password");
	JLabel cant=new JLabel("Can't Sign in?");
	JLabel teaminfo=new JLabel("More about us?");
	JLabel logo=new JLabel(icon);
	JLabel mart=new JLabel("@MiNiMart");

	JLabel currentname=new JLabel("Current Name");
	JLabel newPw=new JLabel("New Password");
	JLabel comfirmPw=new JLabel("Confirm Password");
	JLabel usernoticed1=new JLabel("Please provide an account name and password.");
	JLabel usernoticed2=new JLabel("Be sure to make your password somthing hard ");
	JLabel usernoticed3=new JLabel("to guess, and use at least 8 characters.");
	JLabel usernoticed4=new JLabel("Be a good journey");
	JLabel konohatitle=new JLabel("Mini_Market");
	JLabel mart2=new JLabel("@MiNiMart");

	JLabel uchihatitle=new JLabel("Reset");
	JLabel usernote1=new JLabel(">>Enter current name  first");
	JLabel usernote2=new JLabel(">>Type current password");
	JLabel usernote3=new JLabel(">>If you don't remember current password?? ");
	JLabel usernote4=new JLabel("Contact us : 09792772885");
	JLabel newacc=new JLabel("Current Name");
	JLabel newpassword=new JLabel("Current Password");
	//JLabel confirm password=new JLabel("Confirm Password");
	JLabel mart3=new JLabel("@MiNiMart");
	JLabel kuso=new JLabel("User name or password is incorrected!");

	JLabel inforead1=new JLabel("This project is created by team @MiNiMart: ");
	JLabel inforead2=new JLabel("Members: ");
	JLabel inforead3=new JLabel("Htet Aung Khant");
	JLabel inforead4=new JLabel("Zwe Htet Paing");
	JLabel inforead5=new JLabel("Kyi Thar Hein");
	JLabel inforead6=new JLabel("Phyo Wai Aung");
	JLabel inforead7=new JLabel("Hein Htet Thar");
	JLabel inforead8=new JLabel("Thar Htoo Kyaw");
	JLabel inforead9=new JLabel("Gam Hpam");
	JLabel inforead10=new JLabel("Aung Ko Ko Lin");
	JLabel inforead11=new JLabel("Than Zaw Myint");
	JLabel inforead12=new JLabel("Htun Htun Lin");
	JLabel senjutitle=new JLabel("Team_Info");
	JLabel mart4=new JLabel("@MiNiMart");
	JLabel logo1=new JLabel(icon);

	public Login(){
		final JPanel senju=new JPanel();

		senju.setLayout(null);
		senju.add(mart4);
		senju.add(senjutitle);
		senju.add(logo1);
		senju.add(infoback);
		senju.add(inforead1);
		senju.add(inforead2);
		senju.add(inforead3);
		senju.add(inforead4);
		senju.add(inforead5);
		senju.add(inforead6);
		senju.add(inforead7);
		senju.add(inforead8);
		senju.add(inforead9);
		senju.add(inforead10);
		senju.add(inforead11);
		senju.add(inforead12);

		senjutitle.setForeground(Color.WHITE);
		senjutitle.setBounds(10,0,180,30);

		inforead1.setBounds(100,50,280,13);
		inforead1.setForeground(Color.WHITE);

		inforead2.setBounds(100,75,150,13);
		inforead2.setForeground(Color.WHITE);

		inforead3.setBounds(100,90,150,13);
		inforead3.setForeground(Color.WHITE);

		inforead4.setBounds(100,105,150,13);
		inforead4.setForeground(Color.WHITE);

		inforead5.setBounds(100,120,150,13);
		inforead5.setForeground(Color.WHITE);

		inforead6.setBounds(100,135,150,13);
		inforead6.setForeground(Color.WHITE);

		inforead7.setBounds(100,150,150,13);
		inforead7.setForeground(Color.WHITE);

		inforead8.setBounds(100,165,150,13);
		inforead8.setForeground(Color.WHITE);

		inforead9.setBounds(100,180,150,13);
		inforead9.setForeground(Color.WHITE);

		inforead10.setBounds(100,195,150,13);
		inforead10.setForeground(Color.WHITE);

		inforead11.setBounds(100,210,150,13);
		inforead11.setForeground(Color.WHITE);

		inforead12.setBounds(100,225,150,13);
		inforead12.setForeground(Color.WHITE);

		infoback.setBounds(240,260,100,20);
		infoback.setBackground(new Color(39,39,39));
		infoback.setForeground(Color.WHITE);

		mart4.setBounds(50,270,200,80);
		mart4.setFont(largeFont);
		mart4.setForeground(Color.WHITE);

		logo1.setBounds(350,90,80,80);

		senju.setBackground(new Color(35,35,35));

		add(senju);

		final JPanel uchiha=new JPanel();

		uchiha.setLayout(null);
		uchiha.add(uchihatitle);
		uchiha.add(usernote1);
		uchiha.add(usernote2);
		uchiha.add(usernote3);
		uchiha.add(usernote4);
		uchiha.add(newacc);
		uchiha.add(Currentname);
		uchiha.add(newpassword);
		uchiha.add(Currentpass);
		//uchiha.add(confirm password);
		//uchiha.add(confirmcreate);
		uchiha.add(backacc);
		uchiha.add(cancelacc);
		uchiha.add(Next);
		uchiha.add(mart3);
		uchiha.add(kuso);

		uchiha.setBackground(new Color(35,35,35));

		kuso.setForeground(Color.RED);

		uchihatitle.setForeground(Color.WHITE);
		uchihatitle.setBounds(10,0,180,30);

		usernote1.setBounds(100,50,280,15);
		usernote1.setForeground(Color.WHITE);

		usernote2.setBounds(100,70,280,15);
		usernote2.setForeground(Color.WHITE);

		usernote3.setBounds(100,90,280,15);
		usernote3.setForeground(Color.WHITE);

		usernote4.setBounds(100,110,220,15);
		usernote4.setForeground(Color.WHITE);

		newacc.setBounds(100,150,130,20);
		newacc.setForeground(Color.WHITE);
		Currentname.setBounds(240,150,200,20);
		Currentname.setBackground(new Color(39,39,39));
		Currentname.setForeground(Color.WHITE);
		Currentname.setCaretColor(Color.WHITE);

		newpassword.setBounds(100,180,130,20);
		newpassword.setForeground(Color.WHITE);
		Currentpass.setBounds(240,180,200,20);
		Currentpass.setBackground(new Color(39,39,39));
		Currentpass.setForeground(Color.WHITE);
		Currentpass.setCaretColor(Color.WHITE);

		/*confirmpassword.setBounds(100,200,130,20);
		confirmpassword.setForeground(Color.WHITE);
		confirmcreate.setBounds(240,200,200,20);
		confirmcreate.setBackground(new Color(39,39,39));
		confirmcreate.setForeground(Color.WHITE);
		confirmcreate.setCaretColor(Color.WHITE);*/

		backacc.setBounds(150,220,90,20);
		backacc.setBackground(new Color(39,39,39));
		backacc.setForeground(Color.WHITE);

		cancelacc.setBounds(250,220,90,20);
		cancelacc.setBackground(new Color(39,39,39));
		cancelacc.setForeground(Color.WHITE);

		Next.setBounds(350, 220,90, 20);
		Next.setBackground(new Color(39,39,39));
		Next.setForeground(Color.WHITE);

		mart3.setBounds(20,240,240,40);
		mart3.setFont(largeFont);
		mart3.setForeground(Color.WHITE);

		add(uchiha);

		final JPanel konoha=new JPanel();

		konoha.setLayout(null);
		konoha.add(currentname);
		konoha.add(Newname);
		konoha.add(newPw);
		konoha.add(NewPassword);
		konoha.add(comfirmPw);
		konoha.add(Comfirm);
		//konoha.add(back);
		konoha.add(Done);
		konoha.add(konohacancel);
		konoha.add(usernoticed1);
		konoha.add(usernoticed2);
		konoha.add(usernoticed3);
		konoha.add(usernoticed4);
		konoha.add(mart2);
		konoha.add(konohatitle);

		konoha.setBackground(new Color(35,35,35));
		currentname.setBounds(100,140,130,20);
		currentname.setForeground(Color.WHITE);
		Newname.setBounds(240,140,200,20);
		Newname.setBackground(new Color(39,39,39));
		Newname.setForeground(Color.WHITE);
		Newname.setCaretColor(Color.WHITE);

		newPw.setBounds(100,170,130,20);
		newPw.setForeground(Color.WHITE);
		NewPassword.setBounds(240,170,200,20);
		NewPassword.setBackground(new Color(39,39,39));
		NewPassword.setForeground(Color.WHITE);
		NewPassword.setCaretColor(Color.WHITE);

		comfirmPw.setBounds(100,200,130,20);
		comfirmPw.setForeground(Color.WHITE);
		Comfirm.setBounds(240,200,200,20);
		Comfirm.setBackground(new Color(39,39,39));
		Comfirm.setForeground(Color.WHITE);
		Comfirm.setCaretColor(Color.WHITE);

		Done.setBounds(340,240,100,20);
		Done.setBackground(new Color(39,39,39));
		Done.setForeground(Color.WHITE);

		konohacancel.setBounds(240, 240, 95, 20);
		konohacancel.setBackground(new Color(39,39,39));
		konohacancel.setForeground(Color.WHITE);

		//back.setBounds(150, 240, 90, 20);
		//back.setBackground(new Color(39,39,39));
		//back.setForeground(Color.WHITE);

		usernoticed1.setBounds(100,40,280,15);
		usernoticed1.setForeground(Color.WHITE);

		usernoticed2.setBounds(100,60,280,15);
		usernoticed2.setForeground(Color.WHITE);

		usernoticed3.setBounds(100,80,280,15);
		usernoticed3.setForeground(Color.WHITE);

		usernoticed4.setBounds(130,100,220,15);
		usernoticed4.setForeground(Color.WHITE);

		mart2.setBounds(20,260,240,40);
		mart2.setFont(largeFont);
		mart2.setForeground(Color.WHITE);

		konohatitle.setForeground(Color.WHITE);
		konohatitle.setBounds(10,0,180,30);

		add(konoha);

		final JPanel panel=new JPanel();

		panel.setLayout(null);
		panel.add(user);
		panel.add(userName);
		panel.add(pass);
		panel.add(password);
		panel.add(login);
		panel.add(cancel);
		panel.add(incorrect);
		panel.add(info);
		panel.add(teaminfo);
		panel.add(logo);
		panel.add(title);
		panel.add(cant);
		panel.add(reset);
		panel.add(mart);

		panel.setBackground(new Color(35, 35, 35));

		incorrect.setForeground(Color.RED);

		user.setBounds(140,120,130,20);
		user.setForeground(Color.WHITE);
		userName.setBounds(240,120,200,20);
		userName.setBackground(new Color(35, 35, 35));
		userName.setForeground(Color.WHITE);
		userName.setCaretColor(Color.WHITE);

		pass.setBounds(140,150,130,20);
		pass.setForeground(Color.WHITE);
		password.setBounds(240,150,200,20);
		password.setBackground(new Color(35, 35, 35));
		password.setForeground(Color.WHITE);
		password.setCaretColor(Color.WHITE);

		login.setBounds(240,200,95,20);
		login.setBackground(new Color(39,39,39));
		login.setForeground(Color.WHITE);

		cancel.setBounds(340, 200, 100, 20);
		cancel.setBackground(new Color(39,39,39));
		cancel.setForeground(Color.WHITE);

		cant.setBounds(140,250,180,20);
		cant.setForeground(Color.WHITE);
		reset.setBounds(240,250,200,20);
		reset.setBackground(new Color(39,39,39));
		reset.setForeground(Color.WHITE);

		teaminfo.setBounds(140,280,180,20);
		teaminfo.setForeground(Color.WHITE);

		info.setBounds(240,280,200,20);
		info.setBackground(new Color(39,39,39));
		info.setForeground(Color.WHITE);

		logo.setBounds(30,30,80,80);

		mart.setBounds(120,30,200,80);
		mart.setFont(largeFont);
		mart.setForeground(Color.WHITE);

		title.setForeground(Color.WHITE);
		title.setBounds(10,0,180,30);

		add(panel);


		password.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER){
				try {
					if(confirmPassword())
						try {
							loginActionPerformed();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						} catch (SQLException e) {
							e.printStackTrace();
						} catch (InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (UnsupportedLookAndFeelException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else
						{
							incorrect.setBounds(140,220,300,20);
							password.setText(null);
						}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});

		userName.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER){
				try {
					if(confirmPassword())
						try {
							try {
								loginActionPerformed();
							} catch (InstantiationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (UnsupportedLookAndFeelException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (ClassNotFoundException | SQLException e) {
							e.printStackTrace();
						}
					else
						{
							incorrect.setBounds(140,220,300,20);
							password.setText(null);

						}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
		login.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER){
					try {
						if(confirmPassword())
							try {
								try {
									loginActionPerformed();
								} catch (InstantiationException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (IllegalAccessException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (UnsupportedLookAndFeelException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} catch (ClassNotFoundException | SQLException e1) {
								e1.printStackTrace();
							}
						else
						{
							incorrect.setBounds(140,225,300,20);
							password.setText(null);
						}
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});

	   login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					if(confirmPassword())
						try {
							try {
								loginActionPerformed();
							} catch (InstantiationException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IllegalAccessException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (UnsupportedLookAndFeelException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
					else
					{
						incorrect.setBounds(140,225,300,20);
						password.setText(null);
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		cancel.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER){
					System.exit(0);
				}
			}
		});
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(panel.isVisible()){
					panel.setVisible(false);
					uchiha.setVisible(true);
					add(uchiha);
				}
				else{
					uchiha.setVisible(false);
					panel.setVisible(true);
					add(panel);
				}
			}

		});

		info.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(panel.isVisible()){
					panel.setVisible(false);
					senju.setVisible(true);
					add(senju);
				}
				else{
					senju.setVisible(false);
					panel.setVisible(true);
					add(panel);
				}
			}

		});

		/*back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(konoha.isVisible()){
					konoha.setVisible(false);
					panel.setVisible(true);
					add(panel);
				}
				else{
					panel.setVisible(false);
					konoha.setVisible(true);
					add(konoha);
				}
			}

		});*/

		backacc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(uchiha.isVisible()){
					uchiha.setVisible(false);
					panel.setVisible(true);
					add(panel);
				}
				else{
					panel.setVisible(false);
					uchiha.setVisible(true);
					add(uchiha);
				}
			}

		});

		cancelacc.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});


		konohacancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});

		Done.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(konoha.isVisible()){
					konoha.setVisible(false);
					panel.setVisible(true);
					add(panel);
				}
				else{
					panel.setVisible(false);
					konoha.setVisible(true);
					add(konoha);
				}
				try {
					confirmNewAccount();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		infoback.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(senju.isVisible()){
					senju.setVisible(false);
					panel.setVisible(true);
					add(panel);
				}
				else{
					panel.setVisible(false);
					senju.setVisible(true);
					add(senju);
				}
			}

		});

		Currentname.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER){
					try {
						if(confirmPassword1()) {
							uchiha.setVisible(false);
							konoha.setVisible(true);
							add(konoha);
						} else
						{
							incorrect.setBounds(200,255,300,20);
							password.setText(null);
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		Currentpass.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER){
					try {
						if(confirmPassword1()) {
							uchiha.setVisible(false);
							konoha.setVisible(true);
							add(konoha);
						} else
						{
							incorrect.setBounds(200,255,300,20);
							password.setText(null);
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});

		Next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					if(confirmPassword1()) {
						uchiha.setVisible(false);
						konoha.setVisible(true);
						add(konoha);
					} else
					{
						kuso.setBounds(200,255,300,20);
						password.setText(null);
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		uchiha.setFocusable(true);

		panel.setFocusable(true);

		konoha.setFocusable(true);


	}
	@SuppressWarnings("deprecation")
	private boolean confirmPassword() throws SQLException , ClassNotFoundException{
		initializeDB();
		String query = "Select * from Accounts";
		ResultSet rs=statement.executeQuery(query);
		while(rs.next()){
			if(userName.getText().trim().equals(rs.getString(1)) && password.getText().equals(rs.getString(2)))
				return true;
		}

		return false;
	}
	private boolean confirmPassword1() throws SQLException , ClassNotFoundException{
		initializeDB();
		String query = "Select * from Accounts";
		ResultSet rs=statement.executeQuery(query);
		while(rs.next()){
			if(Currentname.getText().trim().equals(rs.getString(1)) && Currentpass.getText().equals(rs.getString(2)))
				return true;
		}

		return false;
	}
	public void resetAccount() throws SQLException, ClassNotFoundException{
		initializeDB();

	}
	private void initializeDB(){

		if(NewPassword.getText().trim().equals(Comfirm.getText().trim())){
			try{

				Class.forName("com.mysql.jdbc.Driver");
				//System.out.println("Driver loaded");

				connection=DriverManager.getConnection("jdbc:mysql://localhost/shop_managment_system","root","root");
				//System.out.println("Database connected");

				statement=connection.createStatement();
			}
			catch(Exception ex){
			ex.printStackTrace();
			}
		}
	}
	public void confirmNewAccount() throws SQLException{
		String name=Newname.getText();
		String password=Comfirm.getText();
		initializeDB();
		String queryForDelete="delete from accounts";
		statement.executeUpdate(queryForDelete);

		String queryString="insert into accounts values ('"+name+"','"+password+"')";
		statement.executeUpdate(queryString);

	}
	/*private void ResetActionPerformed() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		uchiha.setVisible(false);
		konoha.setVisible(true);
		add(konoha);
	}*/

	private void loginActionPerformed() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		ShopManagementSystem secondFrame=new ShopManagementSystem();

		secondFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		secondFrame.setUndecorated(false);
		secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		secondFrame.setLocationRelativeTo(null);
		secondFrame.setVisible(true);
		this.setVisible(false);
	}
	public static void main(String[] args) {
		JFrame frame=new Login();
		frame.setTitle("Login");
		frame.setUndecorated(true);
		frame.setSize(500,330);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
