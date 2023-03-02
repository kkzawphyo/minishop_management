
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;
public class TestingJSplitPane extends JFrame{
	public TestingJSplitPane(){
		setTitle("Example of split pane");
		setSize(150,150);
		JPanel p1=new JPanel();
		JPanel p2=new JPanel();
		JLabel l1=new JLabel("Area1");
		JLabel l2=new JLabel("Area2");

		p1.add(l1);
		p2.add(l2);

		JSplitPane splitPane=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,p1,p2);
		splitPane.setOneTouchExpandable(true);
		splitPane.setDividerSize(20);

		JButton button=new JButton(">>");
		splitPane.setUI(new ButtonDividerUI(button));

		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

			}
		});
		add(splitPane);

	}
	public static void main(String[] args){
		TestingJSplitPane sp=new TestingJSplitPane();
		sp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sp.setVisible(true);
	}
	class ButtonDividerUI extends BasicSplitPaneUI{
		protected JButton button;
		public ButtonDividerUI(JButton button){
			this.button=button;
		}
		public BasicSplitPaneDivider createDefaultDivider(){
			BasicSplitPaneDivider divider=new BasicSplitPaneDivider(this){
				public int getDividerSize(){
					if(getOrientation()==JSplitPane.HORIZONTAL_SPLIT){
						return button.getPreferredSize().width;
					}
					return button.getPreferredSize().height;
				}
	};
	divider.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
	divider.add(button);
	return divider;
	}
	}
}
