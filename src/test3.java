import javax.swing.*;
import java.awt.*;
public class test3 extends JFrame{

	public static void main(String[] args)
	{
		JFrame a=new JFrame();
		JTextField testing=new JTextField();
		JTextFieldHint test1=new JTextFieldHint("search","Searching",15);
		test1.setPreferredSize(new Dimension(0,27));
		a.add(test1);
		a.setLayout(new FlowLayout(FlowLayout.LEFT));
		a.setExtendedState(a.MAXIMIZED_BOTH);
		a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		a.setLocationRelativeTo(null);
		a.setVisible(true);
	}
}
