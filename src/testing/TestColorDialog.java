package testing;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TestColorDialog extends JApplet{
	private ColorDialog colorDialog1=new ColorDialog();
	private JButton jbtChangeColor=new JButton("Choose Color");

	public TestColorDialog()
	{
		setLayout(new FlowLayout());
		jbtChangeColor.setText("Change Button Text color");
		jbtChangeColor.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						colorDialog1.setVisible(true);

						if(colorDialog1.getColor()!=null)
							jbtChangeColor.setForeground(colorDialog1.getColor());
					}
				});
		add(jbtChangeColor);
	}
}
