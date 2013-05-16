import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;
import org.jdesktop.swingx.mapviewer.WaypointRenderer;

/* This class creates a window with swing to show the samples.
 * It uses the same queue as converter to print the samples that have already been in data registers.
 * The window is divided in 6 JPanels not to print all the samples each time, and changes the position
 * of the frames when it prints new samples.
 * The displays shows just right samples.
 */
public class Display {
  
  public static final int SAMPLE_FRAME=221;
  public static final int FRAMES=6;
  public static final int AMPLITUDE=400;
  public static final int WIDTH=FRAMES*SAMPLE_FRAME+5;
  public static int PERIOD=80;
  private static JPanelSample []samples= new JPanelSample[6];
  private static JFrame frame;
  static JXMapKit map;
  static double loc;
  static  GeoPosition location;
  static Waypoint uno;
  static WaypointPainter painter;
  static Set<Waypoint> punto=new HashSet<Waypoint>();
  
  public Display() {
  }
          
  private static void moveTo() {
        loc+=1;
        loc%=50;
        location=new GeoPosition(loc,loc);
            uno=new Waypoint(loc+1,loc+1);
            punto.clear();
            punto.add(uno);
            painter.setWaypoints(punto);
        map.setAddressLocation(location);
        System.out.println(loc);
  }
    private static void initDisplay() {
        //Create and set up the window.
        frame= new JFrame("Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    
        frame.setLayout(new GridLayout());
        //Create panels and put them in the content pane.
        /*
        for(int i=0; i<6;i++){
        samples[i] = new JPanelSample();
        JButton boton=new JButton();
        samples[i].add(boton);
        }
        for(int i=0; i<6;i++){
        frame.getContentPane().add(samples[i]);
        }
        */

        samples[0] = new JPanelSample();
        JButton boton = new JButton("Move");
        boton.addActionListener( new ActionListener()
        {
         public void actionPerformed(ActionEvent e)
            {
                moveTo();
            }
        });
        samples[0].add(boton);
        frame.getContentPane().add(samples[0]);
        frame.setPreferredSize(new Dimension(WIDTH, AMPLITUDE));
        //Display the window.
    }

    public static void main(String args[]) {
        initDisplay();
        int i=0;
        loc=48;
            location=new GeoPosition(loc,loc);
            map=new JXMapKit();
            map.setDefaultProvider(org.jdesktop.swingx.JXMapKit.DefaultProviders.OpenStreetMaps);
            uno=new Waypoint(loc+1,loc+1);
            punto.add(uno);
            painter = new WaypointPainter();
            painter.setWaypoints(punto);
            map.getMainMap().setOverlayPainter(painter);
            /*
            painter.setRenderer(new WaypointRenderer() {
    public boolean paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint wp) {
        g.setColor(Color.RED);
        g.drawLine(-5,-5,+5,+5);
        g.drawLine(-5,+5,+5,-5);
        return true;
    }
});
*/
            map.getMainMap().setOverlayPainter(painter);
            map.setAddressLocation(location);
            frame.getContentPane().add(map);
            /*
        while(true){
        
        frame.remove(samples[i]);
        samples[i]=new JPanelSample();
        for(int j=(i+1); j<=(i+6);j++){ 
        //frame.getContentPane().add(samples[j%6]);
        }
        */
        frame.pack();
        frame.setVisible(true);
    }
}