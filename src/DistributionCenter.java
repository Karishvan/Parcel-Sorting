//Karishvan Ragunathan
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DistributionCenter extends JPanel{

	private static int frameWidth = 1020;
	private static int frameHeight = 640;
	
	
	Parcel[] packages = new Parcel[20];
	Conveyer arrivals = new Conveyer(this,-90,360,450,30,70);
	
	
	
	Plane p1 = new Plane(this);
	Truck t1 = new Truck(this);
	
	Scanner sc = new Scanner(this);
	ScannerWindows sw = new ScannerWindows(this);
	
	Conveyer international = new Conveyer (this,sc.getRightX() - 20,sc.getWHeight(1) + 50 ,450,30,50);
	Conveyer domestic = new Conveyer (this,sc.getRightX() - 20,sc.getWHeight(5) - 20 ,450,30,50);
	Conveyer unknown = new Conveyer (this,sc.getRightX() - 20,sc.getWHeight(9) - 100 ,450,30,50);
	

	
	
	public DistributionCenter() {
		for (int i = 0; i < packages.length; i++) {
			packages[i] = new Parcel(this, i * -200 - 100);
		}
		
		Conveyer.setMain(arrivals);
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			
			}

			@Override
			public void keyPressed(KeyEvent e) {
				arrivals.keyPressed(e);
				
			}
		});
	
		setFocusable(true); 
	
	}
	
	public static int getFrameHeight() {
		return frameHeight;
	}
	
	public static int getFrameWidth() {
		return frameWidth;
	}
	
	public void paint (Graphics g) {
		super.paint(g);
		//LOCATION OF THE Y STRING
		int strYLoc = 50;
		int strXLoc = 0;
		
		
		
		Graphics2D g2d = (Graphics2D) g;
		
		
		arrivals.paint(g2d);
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		sw.paint(g2d);
		international.paint(g2d);
		domestic.paint(g2d);
		unknown.paint(g2d);
		
		
		
		
		for (Parcel b: packages) {
			b.paint(g2d);
			
		}
		
		
		sc.paint(g2d);
		p1.paint(g2d);
		t1.paint(g2d);
		
		
		
		//Sorted Parcels Board
		g.setColor(Color.darkGray.darker());
		g.fillRect(strXLoc, strYLoc * 0, 150,strYLoc * 4);
		g.setColor(Color.white);
		g.drawString("Sorted Parcels", 0, 20);
		g.drawString("International Parcels: " + Parcel.getIntlParcels(), strXLoc, strYLoc);
		g.drawString("Domestic Parcels: " + Parcel.getDomParcels(), strXLoc , strYLoc * 2);
		g.drawString("Unknown Parcels: " + Parcel.getUnknownParcels(), strXLoc, strYLoc * 3);
		
		
	}
	
	
	private void move() {
		for (Parcel b: packages) {
			
			if (b.getX() > sc.getX()) {
				if (b.getParcelColor() == b.getBlue()) {
					b.move(international, sc);
				} else if (b.getParcelColor() == Color.GREEN) {
					b.move(domestic, sc);
				} else {
					b.move(unknown, sc);
				}
			} else {
				b.move(arrivals, sc);
			}
			
			b.sort(sc, international, domestic, unknown, p1, t1);
		}
		
		arrivals.move();
		international.move();
		domestic.move();
		unknown.move();
		p1.move();
		t1.move();
		
		
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		int count = 1;
		JFrame frame = new JFrame("Distribution center");

		DistributionCenter p = new DistributionCenter();
		frame.add(p);
		
		frame.setSize(getFrameWidth(), getFrameHeight());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		while (true) {
			p.move();
			count++;
			Scanner.setCount(count);
			p.repaint();
			Thread.sleep(10);
		}


	}

}
