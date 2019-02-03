
//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc..
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import colors.ColorMap;
import colors.NamedColor;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ColorLibGui
{
  ColorMap colorMap = null;

  //Note: Typically the main method will be in a
  //separate class. As this is a simple one class
  //example it's all in the one class.
  public static void main(String[] args)
  {
    new ColorLibGui();
  }

  public ColorLibGui()
  {
    colorMap = new ColorMap();
    if(!colorMap.initialize("rgb.txt"))
    {
      System.out.println("ColorMap failed to init!");
      System.exit(1);
    }
    JFrame guiFrame = new JFrame();

    //make sure the program exits when the frame closes
    guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    guiFrame.setTitle("ColorLib GUI");
    guiFrame.setSize(300, 250);
    guiFrame.setLayout(new BoxLayout(guiFrame.getContentPane(), BoxLayout.Y_AXIS));

    //This will center the JFrame in the middle of the screen
    guiFrame.setLocationRelativeTo(null);

    //The first JPanel contains a JLabel and JCombobox
    final JPanel rgbPanel = new JPanel();
    JLabel rLabel = new JLabel("R");
    JLabel gLabel = new JLabel("G");
    JLabel bLabel = new JLabel("B");
    JTextField rField = new JTextField(3);
    JTextField gField = new JTextField(3);
    JTextField bField = new JTextField(3);
    rgbPanel.add(rLabel);
    rgbPanel.add(rField);
    rgbPanel.add(gLabel);
    rgbPanel.add(gField);
    rgbPanel.add(bLabel);
    rgbPanel.add(bField);
    
    guiFrame.add(rgbPanel);
    
    //Create the second JPanel. Add a JLabel and JList and
    //make use the JPanel is not visible.
    final JPanel numPanel = new JPanel();
    JLabel numLabel = new JLabel("# Nearest:");
    JTextField numField = new JTextField(3);
    numPanel.add(numLabel);
    numPanel.add(numField);
    
    guiFrame.add(numPanel);
    
    JPanel colorResults = new JPanel();
    colorResults.setLayout(new BoxLayout(colorResults, BoxLayout.Y_AXIS));
    
    JButton goButton = new JButton("Show Colors");

    goButton.addActionListener(new ActionListener(){
      @Override
      public void actionPerformed(ActionEvent event)
      {
        // Get the RGB values and query the color library for the
        // n nearest colors, and display them below.
        int r = Integer.parseInt(rField.getText());
        int g = Integer.parseInt(gField.getText());
        int b = Integer.parseInt(bField.getText());
        Color c = new Color(r, g, b);
        NamedColor colors[] = new NamedColor[colorMap.size()];
        colors = colorMap.getSortedList(c);
        
        int numColors = Integer.parseInt(numField.getText());
        
        // Clear any old results and add the user color in the first slot.
        colorResults.removeAll();
        JPanel userColorPanel = new JPanel();
        userColorPanel.add(new JLabel("Your color"));
        userColorPanel.add(new NamedColorPanel(new NamedColor("user color", c)));
        colorResults.add(userColorPanel);
        
        // Add each of the resulting colors to the results panel.
        for( int i = 0; i < numColors; ++i )
        {
          NamedColor nc = colors[i];
          JLabel lbl = new JLabel(nc.getName());
//          JLabel swatch = new JLabel("text");
//          swatch.setPreferredSize(lbl.getSize());
//          swatch.setBackground(nc.getColor()); THIS NOT WORK WHY
          NamedColorPanel swatch = new NamedColorPanel(nc);
          JPanel colorPanel = new JPanel();
          colorPanel.add(lbl);
          colorPanel.add(swatch);
          colorResults.add(colorPanel);
          guiFrame.pack();
        }
      }
    });

    guiFrame.add(goButton);
    guiFrame.add(colorResults);

    //make sure the JFrame is visible
    guiFrame.setVisible(true);
  }

}