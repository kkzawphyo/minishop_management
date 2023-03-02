
import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import java.awt.*;
import java.awt.event.*;
public class Sale extends JPanel{

	JTabbedPane tabbedPane=new JTabbedPane();
	//private JTree jTree=new JTree();
	public Sale(){
		//UIManager.put("TabbedPane.borderHightlingtColor", Color.cyan);
		InitialPanel i=new InitialPanel();
		CodeEntryPanel c=new CodeEntryPanel();
		//tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		//tabbedPane.setAlignmentY(JTabbedPane.BOTTOM_ALIGNMENT);

		tabbedPane.setUI(new BasicTabbedPaneUI(){
			protected void installDefaults(){
				super.installDefaults();
				highlight=Color.gray;
				lightHighlight=Color.black;
				shadow=Color.gray;
				darkShadow=Color.cyan;
				focus=Color.yellow;
			}
		});

		tabbedPane.addTab("Sale Record",i);
		tabbedPane.addTab("Sale Order",c);
		//tabbedPane.setBorder(null);




		//UIManager.put("TabbedPane.contentBorderInsets", new Insets(0,0,0,0));
		//UIManager.put("TabbedPane.tabAreaInsets",new Insets(3,0,0,0));
		//UIManager.put("TabbedPane.tabsOverlapBorder", false);
		//UIManager.put("TabbedPane.selectHightlight", Color.BLACK);
		//UIManager.put("TabbedPane.focus", Color.cyan);
		setLayout(new BorderLayout());
		add(tabbedPane,BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
			JFrame frame=new JFrame();
			//frame.setLayout(null);
			JPanel p=new Sale();
			System.out.print(p.getHeight());
			frame.setSize(1000,1200);
			frame.add(p);

			frame.setTitle("JTable Testing");
			//frame.pack();
			//frame.setUndecorated(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);

	}

}
