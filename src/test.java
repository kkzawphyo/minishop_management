import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;
public class test extends JPanel{
	private JTextField htet=new JTextField(15);
	private JButton aung=new JButton("Add");
	public test()
	{
		setLayout(new FlowLayout());
		add(htet);
		add(aung);
		aung.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if((isValid(htet.getText()))&&htet.getText().length()<5)
				{
					//updatingData();
					System.out.println("This is valid");
					System.out.println(htet.getText().length());
				}
				else
				{
					System.out.print("This is not valid");
				}
			}
		});
	}
	private boolean isValid(String s)
	{
		for(int i=0;i<s.length();i++)
		{
			if(Character.isLetter(s.charAt(i)))
				return false;
		}
		return true;
	}
	/*public static void main(String[] args) {
		test frame=new test();
		frame.setTitle("Login");
		//frame.setUndecorated(true);
		frame.setSize(700,500);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}*/

}
