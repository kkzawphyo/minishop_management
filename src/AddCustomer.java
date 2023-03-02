
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class AddCustomer extends JDialog{
	/**
	 *
	 */
	private int choice;
	static final int Edit=1;
	static final int Add=2;
	private static final long serialVersionUID = 1L;
	public JSearchTextField name,id,address,pno;
	private JLabel cu_name,cu_id,cu_address,cu_pno;
	public StandardButton save,cancel,clear;
	private JPanel jp1 = new JPanel();
	private String c_name,c_id,c_address,c_pno;
	private String errorName,errorId,errorAddress,errorPno=" ";
	private JLabel eName=new JLabel() ;
	private JLabel eId=new JLabel();
	private JLabel eAddress = new JLabel();
	private JLabel ePno = new JLabel();
	private Border border =new JSearchTextField().getBorder();
	private Font labelFont=new Font("Sanserif",Font.BOLD,12);
	private ImageIcon errorIcon = new ImageIcon("error.png");
	private ImageIcon correct = new ImageIcon("correctBig.png");
	private int selectedRow;
	//private Customer cus;


	public AddCustomer() throws ClassNotFoundException, Exception{


	}

	public AddCustomer(Customer cu,int choose) throws Exception{

		this(null,true,cu,choose);
	}

	public AddCustomer(Frame parent , boolean modal , Customer cu,int c) throws Exception , ClassNotFoundException{


		super(parent,modal);
		final Customer cus=cu;
		choice=c;
		jp1.setLayout(null);
		//jp1.setFocusable(true);
		jp1.add(cu_id=new JLabel("Customer ID")); jp1.add(id=new JSearchTextField("eg. 00111",20));
		jp1.add(cu_name=new JLabel("Customer Name")); jp1.add(name=new JSearchTextField("eg. Zaw Zaw..",20));
		jp1.add(cu_address=new JLabel("Address")); jp1.add(address=new JSearchTextField("eg. Pyin Oo Lwin..",20));
		jp1.add(cu_pno=new JLabel("Phone_No")); jp1.add(pno=new JSearchTextField("eg. 09..",20));
		jp1.add(save=new StandardButton("Save")); jp1.add(cancel=new StandardButton("Cancel"));
		jp1.add(clear=new StandardButton("Clear"));

		cu_id.setBounds(30,50,100,30);
		cu_name.setBounds(30,100,100,30);
		cu_address.setBounds(30,150,100,30);
		cu_pno.setBounds(30,200,100,30);

		id.setBounds(150,50,150,30);
		name.setBounds(150,100,150,30);
		address.setBounds(150,150,150,30);
		pno.setBounds(150,200,150,30);

		save.setBounds(40,260,80,30);
		cancel.setBounds(130,260,80,30);
		clear.setBounds(220,260,80,30);

		if(choice==Edit){
		 selectedRow=cu.getTable().getSelectedRow();
			id.setText((String)cu.getTable().getValueAt(selectedRow, 0));
			name.setText((String)cu.getTable().getValueAt(selectedRow, 1));
			address.setText((String)cu.getTable().getValueAt(selectedRow, 2));
			pno.setText((String)cu.getTable().getValueAt(selectedRow, 3));
			System.out.print("check work");
		}
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				saveAction(cus);
			}
		});


		clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				name.setText(null); name.setBorder(border); eName.setVisible(false);
				id.setText(null);	id.setBorder(border);	eId.setVisible(false);
				address.setText(null); address.setBorder(border); eAddress.setVisible(false);
				pno.setText(null); pno.setBorder(border); ePno.setVisible(false);
			}
		});


		cancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);

			}
		});

		name.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					saveAction(cus);
				else if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					setVisible(false);
			}
		});

		id.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					saveAction(cus);
				else if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					setVisible(false);
			}
		});

		address.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					saveAction(cus);
				}

				else if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
					setVisible(false);
				}

			}
		});

		pno.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
					saveAction(cus);
				else if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
					setVisible(false);
			}
		});


		add(jp1);
		setResizable(false);
		this.setLocation(470,170);
		setSize(350,500);
		setVisible(true);
	}



	public void saveAction(Customer cus){


		c_name=name.getText();
		c_id=id.getText().trim();
		c_address=address.getText().trim();
		c_pno=pno.getText().trim();

		eName.setVisible(true); eId.setVisible(true); eAddress.setVisible(true); ePno.setVisible(true);
		try {
			boolean flag1,flag2,flag3,flag4;
			if(isCustomerNameValid(c_name) ){
				flag1=true;
				jp1.add(eName);
				eName.setText(errorName);
				eName.setIcon(null);
				eName.setBounds(40,300,250,30);
			}
			else{
				flag1=false;
				jp1.add(eName);
				eName.setText(errorName);
				eName.setIcon(errorIcon);
				eName.setHorizontalTextPosition(SwingConstants.RIGHT);
				eName.setBounds(40,300,250,30);
			}


			if(isCustomerIdValid(c_id,cus,choice)){
				flag2=true;
				jp1.add(eId);
				eId.setText(errorId);
				eId.setIcon(null);
				eId.setBounds(40,330,250,30);

			}

			else{
				flag2=false;
				jp1.add(eId);
				eId.setText(errorId);
				eId.setIcon(errorIcon);
				eId.setHorizontalTextPosition(SwingConstants.RIGHT);
				eId.setBounds(40,330,250,30);

			}

			if(isAddressValid(c_address)){
				flag3=true;
				jp1.add(eAddress);
				eAddress.setText(errorAddress);
				eAddress.setIcon(null);
				eAddress.setBounds(40,360,250,30);

			}
			else{
				flag3=false;
				jp1.add(eAddress);
				eAddress.setText(errorAddress);
				eAddress.setIcon(errorIcon);
				eAddress.setHorizontalTextPosition(SwingConstants.RIGHT);
				eAddress.setBounds(40,360,250,30);

			}


			if(isPnoValid(c_pno)){
				flag4=true;
				jp1.add(ePno);
				ePno.setText(errorPno);
				ePno.setIcon(null);
				ePno.setBounds(40,390,250,30);

			}
			else{
				flag4=false;
				jp1.add(ePno);
				ePno.setText(errorPno);
				ePno.setIcon(errorIcon);
				ePno.setHorizontalTextPosition(SwingConstants.RIGHT);
				ePno.setBounds(40,390,250,30);

			}



			if(flag1 && flag2 && flag3 && flag4){
				if(choice==Edit){
					cus.updateTable( c_id, c_name, c_address, c_pno, selectedRow);
					JOptionPane.showMessageDialog(null, "Data Changed !","",JOptionPane.INFORMATION_MESSAGE,correct);
					dispose();
				}
				else if(choice==Add){
					cus.insertTable(c_id, c_name, c_address, c_pno);
					JOptionPane.showMessageDialog(null, "Customer Added !","",JOptionPane.INFORMATION_MESSAGE,correct);
					dispose();
				}


				//eName.revalidate(); eId.revalidate(); eAddress.revalidate(); ePno.revalidate();
		}


		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public boolean isCustomerNameValid(String customerName){
			if(customerName.length()==0){
				errorName="<html><font color='red'>Customer Name</font> cannnot be empty !</html>";
				name.setBorder(new LineBorder(Color.red));
				return false;
			}

			if(customerName.length()>30){
				name.setBorder(new LineBorder(Color.red));
				errorName="<html><font color='red'>Customer Name</font> too long</html> ";
				return false;
			}

			errorName=" ";
			name.setBorder(border);
			return true;
			//color the border of customer name text field
			//empty the text field

		}

		public boolean isCustomerIdValid(String  customerId, Customer c, int choices){

			if(customerId.length()==0){
				errorId="<html><font color='red'>Customer Id</font> cannot be empty !</html>";
				id.setBorder(new LineBorder(Color.red));
				return false;
			}

			if(customerId.length()>15){
				errorId="<html><font color='red'>Customer Id</font> too long !</html>";
				id.setBorder(new LineBorder(Color.red));
				return false;
			}

			for(int i=0 ; i<customerId.length() ; i++){
				if(!Character.isDigit(customerId.charAt(i))){
					errorId="<html><font color='red'>Customer Id</font> can only contain digit</html>";
					id.setBorder(new LineBorder(Color.red));
					return false;
				}
			}

			int row=c.getTable().getRowCount();
			int col=0;
			if(choices==Add)
			for(int i=0 ; i<row ; i++){
				if(c.getTable().getValueAt(i, 0).equals(customerId)){
					errorId="<html>This <font color='red'>Customer Id</font> already existed!</html>";
					id.setBorder(new LineBorder(Color.red));
					return false;
				}

			}
			else if(choices==Edit){
				for(int i=0 ; i<row ; i++){
					if(c.getTable().getValueAt(i, 0).equals(customerId) && (c.getTable().getSelectedRow()!=i)){
						errorId="<html>This <font color='red'>Customer Id</font> already existed!</html>";
						id.setBorder(new LineBorder(Color.red));
						return false;
					}
				}
			}
			errorId=" ";
			id.setBorder(border);
			return true;

		}

		public boolean isAddressValid(String customerAddress){

			if(customerAddress.length()>50){
				errorAddress="<html><font color='red'>Customer Address</font> too long !</html>";
				address.setBorder(new LineBorder(Color.red));
				return false;
			}
			errorAddress=" ";
			address.setBorder(border);
			return true;
		}

		public boolean isPnoValid(String customerPno){

			if(customerPno.length()>15){
				errorPno="<html><font color='red'>Customer Phone No</font> too long</html>";
				pno.setBorder(new LineBorder(Color.red));
				return false;
			}

			for(int i=0 ; i<customerPno.length() ; i++ ){
				if(!Character.isDigit(customerPno.charAt(i))){
					errorPno="<html><font color='red'>Customer Phone No</font> can only contain numbers</html>";
					pno.setBorder(new LineBorder(Color.red));
					return false;
				}
			}
			errorPno=" ";
			pno.setBorder(border);
			return true;
		}



}
