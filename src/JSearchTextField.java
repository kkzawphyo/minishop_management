import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


public class JSearchTextField extends JTextField implements FocusListener {

    private String textWhenNotFocused;
    private Icon icon;
    private Insets dummyInsets;
    private Border border1 = this.getBorder();

    public JSearchTextField() {
        super();
        this.textWhenNotFocused = "Enter Text ...";
        this.addFocusListener(this);
    }
    public JSearchTextField(String text,int num) {
        super(num);
        this.textWhenNotFocused =text;
        this.addFocusListener(this);
    }
    public JSearchTextField(String icon,String text,int num) {
        super(num);
        this.textWhenNotFocused =text;
        setIcon(createImageIcon("icons/"+icon+".png",icon));
        Border border = UIManager.getBorder("TextField.border");
        JTextField dummy = new JTextField();
        this.dummyInsets = border.getBorderInsets(dummy);
        this.addFocusListener(this);
    }
    public JSearchTextField(String text) {
        super();
        this.textWhenNotFocused =text;
        this.addFocusListener(this);
    }
    public void setIcon(Icon newIcon){
        this.icon = newIcon;
    }

    public String getTextWhenNotFocused() {
        return this.textWhenNotFocused;
    }

    public void setTextWhenNotFocused(String newText) {
        this.textWhenNotFocused = newText;
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
        if(this.hasFocus()){
        	Border Line=BorderFactory.createLineBorder(Color.green,2);
        	Border empty=new EmptyBorder(2,textX,2,2);
        	CompoundBorder border=new CompoundBorder(Line,empty);
        	this.setBorder(border);
        }
        else
        	this.setBorder(border1);

        setMargin(new Insets(2, textX, 2, 2));

        if (this.getText().equals("")) {
            int width = this.getWidth();
            int height = this.getHeight();
            Font prev = g.getFont();
            Font italic = prev.deriveFont(Font.BOLD+Font.ITALIC);
            Color prevColor = g.getColor();
            g.setFont(italic);
          //  g.setColor(UIManager.getColor("textInactiveText"));
            g.setColor(Color.LIGHT_GRAY);
            int h = g.getFontMetrics().getHeight();
            int textBottom = (height - h) / 2 + h - 4;
            int x = this.getInsets().left;
            Graphics2D g2d = (Graphics2D) g;
            RenderingHints hints = g2d.getRenderingHints();
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g2d.drawString(textWhenNotFocused, x, textBottom);
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

    //FocusListener implementation:
   public void focusGained(FocusEvent e) {
      this.repaint();
        }

    public void focusLost(FocusEvent e) {
        this.repaint();
    }
}