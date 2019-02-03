import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import colors.NamedColor;

public class NamedColorPanel extends JPanel
{
  private static final long serialVersionUID = 100100100L;
  
  private NamedColor myColor = null;

  public NamedColorPanel(NamedColor color)
  {
    myColor = color;
    setPreferredSize(new Dimension(40, 15));
  }
  
  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);;
    //JLabel lbl = new JLabel(myColor.getName());
    g.setColor(myColor.getColor());
    g.fillRect(0, 0, getWidth(), getHeight());
  }
}
