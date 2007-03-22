package motion;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import searchalgorithm.*;
import searchproblem.*;

public class MotionTest extends Component {

    static BufferedImage img;

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }

    public MotionTest() {
    }

    public Dimension getPreferredSize() {
        if (img == null) {
             return new Dimension(100,100);
        } else {
           return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }

	public static void main(String[] args) {		

		System.gc();
		BitmapTerrain t = new BitmapTerrain("D:/Pedro/TP1/motion/th.png");
		
		
		int startx = 141; int starty = 512;
		
		//int gx=180; int gy=540;
		int gx=400; int gy=512; 
		RoverState init = new RoverState(startx,starty,t);
		InformedSearchProblem prob = new RoverProblem(init,gx,gy);
		SearchAlgorithm u = new AStarSearch(prob);
		
		
		t = new BitmapTerrain("D:/Pedro/TP1/motion/th.png");
		Node n = u.searchSolution();
		System.out.println(u.getMetrics());
		if( n != null) {
			System.out.println(n.getPathCost());
			System.out.println(n.getPath());
			img = t.getTerrain();
			RoverState current = (RoverState) init.clone();
			img.setRGB(current.getCoordX(), current.getCoordY(), 0xFF << 16);
			for( Object action: n.getPath()) {
				current.applyOperator(action);
				img.setRGB(current.getCoordX(), current.getCoordY(), 0xFF << 16 );
			}
		}

        JFrame f = new JFrame("Load Image Sample");
        
        f.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e) {
                    System.exit(0);
                }
            });

        f.add(new MotionTest());
        f.pack();
        f.setVisible(true);

	}

}
