


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class PurchaseReturn extends JPanel
{
	private JPanel buttonPanel = new JPanel();
	private JPanel p=new JPanel(new FlowLayout(FlowLayout.LEFT));
	private StandardButton addPurchaseButton = new StandardButton("Add New PurchaseReturnProduct");
	private PurchaseR pp = new PurchaseR();
	private JLabel sear = new JLabel("Enter Date");
	private JSearchTextField jsear = new JSearchTextField("YYYY-MM-DD",10);

	public PurchaseReturn() throws Exception {

		addPurchaseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.out.print("add purchasereturn work");
				try {
					new AddNewPurchaseReturn(pp.getCustomerClass());

				}
				catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});

		jsear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					new viewDateReturn(jsear.getText());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		setLayout(new BorderLayout());
		buttonPanel.add(addPurchaseButton);
		p.add(sear);
		p.add(jsear);

		add(p,BorderLayout.NORTH);
		add(pp, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	public static void main(String[] args) throws Exception {

		JPanel panel= new PurchaseReturn();
		JFrame frame=new JFrame();
		frame.add(panel);
		frame.setTitle("PurchaseReturn");
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
