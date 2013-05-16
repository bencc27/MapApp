import java.awt.*;
import javax.swing.JPanel;


/*
 * This class draws one frame for the display; it is imposible to draw all the samples,
 * so this class drawa 1 sample out of a number of samples depending of the period to refresh the display.
 * The constant SAMPLES refers to this number, so it's calculated with the period chosen in Display. 
 * As the average of sectors read in a second is 75, this number is used to calculate SAMPLES.
 * 
 */
public class JPanelSample extends JPanel
{
  private static final int SAMPLES=(int)((Display.PERIOD*0.075*588)/(221));
  
  
  public JPanelSample(){
  }
  
  
public void paintComponent( Graphics g )
{
  int amplitude=0;
  int zero=(int)(Display.AMPLITUDE/2);
super.paintComponent( g ); // llama el método paintComponent de la superclase

this.setOpaque(true);
this.setBackground( Color.RED );
this.setPreferredSize(new Dimension(Display.SAMPLE_FRAME/3, Display.AMPLITUDE));

g.setColor( Color.RED );
g.drawLine( 0, 5+ zero, 1, 6+zero);
}

}