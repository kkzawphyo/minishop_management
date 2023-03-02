
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class InitialPanel extends JPanel{

	private JPanel panel=new JPanel();
	private String[] columnName={"Product_Id","Product_Name","Quantity","Price"};
	private Object[][] data={};
	public DefaultTableModel tableModel=new DefaultTableModel(data,columnName);
	public JTable table;
	public JLabel noProduct=new JLabel();
	public StandardButton confirm=new StandardButton("Confirm");
	public StandardButton delete=new StandardButton("Delete");
	public JLabel customerName=new JLabel(" ");
	Font font=new Font("SansSerif",Font.BOLD,15);
	public InitialPanel(){
		//panel for name and button
		JPanel nbPanel=new JPanel();
		nbPanel.setLayout(new BorderLayout());
		nbPanel.add(noProduct,BorderLayout.CENTER);
		noProduct.setFont(font);
		noProduct.setForeground(Color.red);
		JPanel confirmDeletePanel=new JPanel();
		confirmDeletePanel.setLayout(new FlowLayout(FlowLayout.RIGHT,10,10));

		//confirmDeletePanel.add(delete);
		confirmDeletePanel.add(confirm);
		delete.setRolloverEnabled(false);
		delete.setRequestFocusEnabled(false);
		delete.setFocusable(false);
		confirm.setRolloverEnabled(false);
		confirm.setRequestFocusEnabled(false);
		confirm.setFocusable(false);
		nbPanel.add(confirmDeletePanel,BorderLayout.EAST);

		Border border=new EmptyBorder(0,0,0,-1);
		Border border2=new EmptyBorder(10,10,10,10);
		nbPanel.setBorder(border2);
		//panel.add(table);
		table=new JTable(tableModel){
			public Component prepareRenderer(TableCellRenderer renderer,int row,int column){
				Component c=super.prepareRenderer(renderer, row, column);
				//Color alternateColor=new Color(252,242,206);
				//Color whiteColor=Color.white;
				if(!c.getBackground().equals(getSelectionBackground())){
					Color bg=(row%2==0?new Color(252,242,206):new Color(250,250,0));
					c.setBackground(bg);
					bg=null;
				}


				return c;
		}
		};


		//adding color to table header
		JTableHeader header=table.getTableHeader();
		header.setFont(font);
		header.setBackground(new Color(100,47,9));
		header.setForeground(Color.yellow);


		table.setShowGrid(true);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		table.getTableHeader().setReorderingAllowed(false);
		table.setRowMargin(0);
		//table.setRowHeight(3*table.getRowHeight()/2);
		// table.setShowGrid(true);
		table.setRowHeight(20);
		//table.setEnabled(false);
		//table.getColumnModel().getColumn(3).setPreferredWidth(15);
		//table.setForeground(Color.black);


		//table.setGridColor(new java.awt.Color(102, 255, 255));
		//TableColumn tm=table.getColumnModel().getColumn(0);
		//tm.setCellRenderer(new ColorColumnRenderer(Color.LIGHT_GRAY,Color.blue));
		//nbPanel.setBackground(new Color(102, 255, 255));

		panel.setLayout(new BorderLayout());
		panel.add(new JScrollPane(table),BorderLayout.CENTER);
		panel.add(nbPanel,BorderLayout.SOUTH);

		//tableModel.addRow(new Object[]{"34433",5});
		//panel.add(new JLabel("Product_Id"));
		//panel.add(new JLabel("Quantity"));

		//nbPanel.setLayout(new FlowLayout(FlowLayout.LEFT,50,5));

		//Adding name and button
		//nbPanel.add(customerName);
		//nbPanel.add(confirm);
		//setOpaque(true);
		//customerName.setForeground(Color.black);
		/*delete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(table.getSelectedRow()>=0){
					tableModel.removeRow(table.getSelectedRow());
				}

				}
		});*/
		table.setBorder(border);
		setLayout(new BorderLayout());
		add(panel,BorderLayout.CENTER);
		add(customerName,BorderLayout.NORTH);
		panel.setBorder(border);
		//setBorder(border2);
		//setFocusable(true);



	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame=new JFrame();
		//frame.setLayout(null);
		JPanel p=new InitialPanel();
		//System.out.print(p.getHeight());
		frame.setSize(1000,1200);
		frame.add(p);
		///frame.setOpacity(true);
		frame.setTitle("JTable Testing");
		//frame.pack();
		//frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	class ColorColumnRenderer extends DefaultTableCellRenderer{
		Color bkgndColor,fgndColor;
		public ColorColumnRenderer(Color bkgnd,Color fgnd){
			super();
			bkgndColor=bkgnd;
			fgndColor=fgnd;
		}
		public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
			Component cell=super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			cell.setBackground(bkgndColor);;
			cell.setForeground(fgndColor);
			return cell;
		}
	}

}
