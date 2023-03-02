import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
public class JTextFieldHint extends JTextField implements FocusListener{
private JTextField jtf;
private Icon icon;
private String hint;
private Insets dummyInsets;

public JTextFieldHint(String icon, String hint,int num){
	super(num);
    setIcon(createImageIcon("icons/"+icon+".png",icon));
    this.hint = hint;

    Border border = UIManager.getBorder("TextField.border");
    JTextField dummy = new JTextField();
    this.dummyInsets = border.getBorderInsets(dummy);

    addFocusListener(this);
}

public void setIcon(Icon newIcon){
    this.icon = newIcon;
}

@Override
protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int textX = 2;

        if(this.icon!=null){
            int iconWidth = icon.getIconWidth();
            int iconHeight = icon.getIconHeight();
            int x = dummyInsets.left + 5;
            textX = x+iconWidth+2;
            int y = (this.getHeight() - iconHeight)/2;
            icon.paintIcon(this, g, x, y);
        }

        setMargin(new Insets(2, textX, 2, 2));

        if ( this.getText().equals("")) {
            int width = this.getWidth();
            int height = this.getHeight();
            Font prev = g.getFont();
            Font italic = prev.deriveFont(Font.BOLD+Font.ITALIC);
            Color prevColor = g.getColor();
            g.setFont(italic);
            //g.setColor(UIManager.getColor("textInactiveText"));
            g.setColor(Color.LIGHT_GRAY);
            int h = g.getFontMetrics().getHeight();
            int textBottom = (height - h) / 2 + h - 4;
            int x = this.getInsets().left;
            Graphics2D g2d = (Graphics2D) g;
            RenderingHints hints = g2d.getRenderingHints();
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.drawString(hint, x, textBottom);
            g2d.setRenderingHints(hints);
            g.setFont(prev);
            g.setColor(prevColor);
        }

}
protected ImageIcon createImageIcon(String path, String description) {
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
      //  return new ImageIcon(imgURL, description);
    	ImageIcon img1=new ImageIcon(imgURL,description);
		Image i1=img1.getImage();
		Image searchImg=i1.getScaledInstance(20,20,Image.SCALE_SMOOTH);
		ImageIcon g=new ImageIcon(searchImg);
		return g;
    } else {
        System.err.println("Couldn't find file: " + path);
        return null;
    }
}

@Override
public void focusGained(FocusEvent arg0) {
    this.repaint();
}

@Override
public void focusLost(FocusEvent arg0) {
    this.repaint();
}


}