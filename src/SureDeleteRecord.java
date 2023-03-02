import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class SureDeleteRecord extends JDialog
{
	static ImageIcon g;

	static int flag;

	private Font font1=new Font("System",Font.BOLD,20);
	private Font font2=new Font("System",Font.ROMAN_BASELINE,13);

	private JLabel inf=new JLabel("Sure!");
	private JLabel inf1=new JLabel("If you delete records,those records will be permently  deleted");
	//private JLabel inf2=new JLabel("product(eg: sale record,purchase record,....) will be delete.");

	private StandardButton Yes=new StandardButton("Yes");
	private StandardButton No=new StandardButton("No");

	public SureDeleteRecord(JFrame parent3,boolean modal)
	{
		super(parent3,modal);
		setTitle("Deleting Sale Records");
		setSize(450,150);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);

		ImageIcon img=new ImageIcon("fav/important.png");
		Image a=img.getImage();
		Image editImg=a.getScaledInstance(40,40,Image.SCALE_SMOOTH);
		g=new ImageIcon(editImg);
		JLabel confirm=new JLabel(g);

		inf.setFont(font1);
		inf1.setFont(font2);
	//	inf2.setFont(font2);

		add(confirm);
		add(inf);
		add(inf1);
		//add(inf2);
		add(Yes);
		add(No);
		confirm.setBounds(20, 20, 40, 40);
		inf.setBounds(80, 30,300, 20);
		inf1.setBounds(80, 60, 400, 20);
		//inf2.setBounds(80, 80,400 ,20 );
		Yes.setBounds(150, 95, 60, 25);
		No.setBounds(240,95,60,25);

		Yes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				flag=1;
				setVisible(false);
			}
		});

		Yes.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					flag=1;
					setVisible(false);
				}
			}
		});

		No.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				flag=0;
				setVisible(false);
			}
		});

		No.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode()==KeyEvent.VK_ENTER)
				{
					flag=0;
					setVisible(false);
				}
			}
		});
	}

	public static int getFlag(){
		return flag;
	}
}
