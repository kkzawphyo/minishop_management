import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import  java.awt.*;
import  java.util.*;

import javax.swing.plaf.*;
import com.alee.laf.WebLookAndFeel;

import net.java.dev.designgridlayout.DesignGridLayout;
import java.awt.*;
import java.awt.event.*;
public class myDialog  extends JDialog{
	JTextField[] inText;
	JPanel midPane;
	private static final long serialVersionUID = 1L;
	/*public static void main(String[] a){
		testfordialog dialog = new testfordialog(new JFrame(), "hello JCGs", "This is a JDialog example");
		// set the size of the window
		dialog.setSize(300, 150);
	}*/
	public void setTF(JTable table,final ArrayList<String> column,final String title,final int r){
		inText=new JTextField[column.size()];

	//table.setModel(model=new DefaultTableModel());
	midPane=new JPanel();
	DesignGridLayout layout=new DesignGridLayout(midPane);

	TableModel tm=(DefaultTableModel)table.getModel();

	for(int i=0;i<column.size();i++){
		if(title=="Edit") layout.row().grid(new JLabel(column.get(i))).add(inText[i]=new JTextField((String)tm.getValueAt(r, i)));
		else layout.row().grid(new JLabel(column.get(i))).add(inText[i]=new JTextField(""));

		layout.row().center().fill().add(new JSeparator());

	}}
	public myDialog(JFrame parent,final JTable table,final String title, final ArrayList<String> column,final int r){

		super(parent, title);
		setTF(table, column,title, r);


		JPanel buttonPane=new JPanel();
		final JButton button=new JButton("Save");
		buttonPane.add(button);
		button.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e) {
						LoadDB2Table.addTo(inText,title=="Edit",r);


						if(title!="Edit"){

							if(e.getSource()==button) setTF(table, column,title, r);
							else {setVisible(false);dispose();}
						}

						else {
						setVisible(false);dispose();}

					}

		});
		getContentPane().add(buttonPane, BorderLayout.PAGE_END);
		getContentPane().add(midPane, BorderLayout.PAGE_START);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle(title);
		setBackground(new Color(244,226,135));
		//pack();
		setLocationRelativeTo(null);
		setSize(300, 500);
		setVisible(true);

	}
	public JRootPane createRootPane() {
		JRootPane rootPane = new JRootPane();
		KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
		Action action = new AbstractAction() {

			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				dispose();
			}
		};
		InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(stroke, "ESCAPE");
		rootPane.getActionMap().put("ESCAPE", action);
		return rootPane;
	}


}
