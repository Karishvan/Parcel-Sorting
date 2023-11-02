import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
public class Parcel {
	private int h;
	private int l;
	private int w;
	private int x;
	private int y;
	private int xa;
	private Color type;
	private Color niceBlue = new Color(0, 153, 255);
	private DistributionCenter dc;
	
	private static int intlParcels = 0;
	private static int domParcels = 0;
	private static int unknownParcels = 0;
	
	
	public Parcel(DistributionCenter dc, int xVal) {
		double rand = Math.random();
		
		h = ((int) ((Math.random() * (50 - 20)) + 20)) * -1;
		l = (int) ((Math.random() * (50 - 20)) + 20);
		w = (int) ((Math.random() * (50 - 10)) + 10);
		
		//h = -50;
		//l = 50;
		//w = 50;
		
		//Blue is International
		//Green is Domestic
		//Yellow is Unknown
		if (rand <= 0.33) {
			type = niceBlue;
		} else if (rand > 0.33 && rand <= 0.66) {
			type = Color.GREEN;
		} else if (rand > 0.66) {
			type = Color.YELLOW;
		}
		
		//initial positions
		x = xVal;
		y = 350;
		
		//initial speed
		xa = 1;
		
		this.dc = dc;
		
	}
	
	public static int getIntlParcels() {
		return intlParcels;
	}
	
	public static int getDomParcels() {
		return domParcels;
	}
	
	public static int getUnknownParcels() {
		return unknownParcels;
	}
	
	public static void addIntlParcels() {
		intlParcels++;
	}
	
	public static void addDomParcels() {
		domParcels++;
	}
	
	public static void addUnknownParcels() {
		unknownParcels++;
	}
	
	private DistributionCenter getCenter() {
		return dc;
	}
	
	public Color getBlue() {
		return niceBlue;
	}
	
	public int getX() {
		return x;
	}
	
	public int getRightX() {
		return getX() + getW();
	}
	
	public int getY() {
		return y;
	}
	public int getL() {
		return l;
	}
	public int getW() {
		return w;
	}
	public int getH() {
		return h;
	}
	public Color getParcelColor() {
		return type;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getXA() {
		return xa;
	}
	public int getLengthSlant() {
		double temp = getL() /  Math.sqrt(2);
		return (int) temp;
	}
	
	
	public void paint (Graphics2D g) {
		//int slant = getL() / (int) Math.sqrt(2);
		//int rightX = getX() + getW();
		int topY = getY() + getH();
		
		//System.out.println(getLengthSlant());
		
		//Starts from bot left and goes Counter clockwise
		//int [] xCoordsLSide = {getX() - getLengthSlant(), getX(), getX(), getX() - getLengthSlant()};
		//System.out.println(getL() + " " + getW());
		//int [] yCoordsLSide = {getY() - getLengthSlant(),getY(), topY, topY - getLengthSlant()};
		
		//int [] xCoordsTopL = {getX(), getRightX(), getRightX() - getLengthSlant(), getX() - getLengthSlant()};
		//int [] yCoordsTopL = {topY, topY, topY - getLengthSlant(), topY - getLengthSlant()};
		
		//To flip everything to the right
		int [] xCoordsRSide = {getRightX(), getRightX() + getLengthSlant(), getRightX() + getLengthSlant(), getRightX()};
		int [] yCoordsRSide = {getY(), getY() - getLengthSlant(), topY - getLengthSlant(), topY};
		
		int [] xCoordsTopR = {getX(), getRightX(), getRightX() + getLengthSlant(), getX() + getLengthSlant()};
		int [] yCoordsTopR = {topY, topY, topY - getLengthSlant(), topY - getLengthSlant()};
		
		
	
		
		g.setColor(getParcelColor());
		//System.out.println(getX() + " " + getY() + " " + getW() + " " + getH());
		g.fillRect(getX(), topY, getW(), getH() * -1);
		g.setColor(getParcelColor().darker());
		
		//LEFT SIDE STUFF
		//g.fillPolygon(xCoordsLSide, yCoordsLSide, 4);
		//g.fillPolygon(xCoordsTopL, yCoordsTopL, 4);
		
		g.fillPolygon(xCoordsRSide, yCoordsRSide, 4);
		g.fillPolygon(xCoordsTopR, yCoordsTopR, 4);
		
		
		g.setColor(Color.black);
		g.drawRect(getX(), topY, getW(), getH() * -1);
		
		//LEFT SIDE STUFF
		//g.drawPolygon(xCoordsLSide,yCoordsLSide,4);
		//g.drawPolygon(xCoordsTopL, yCoordsTopL, 4);
		
		g.drawPolygon(xCoordsRSide, yCoordsRSide, 4);
		g.drawPolygon(xCoordsTopR, yCoordsTopR, 4);
		
	
		
		
	}
	
	public void move(Conveyer b, Scanner sc) {
		if ((b.getSpace() == true && b.getX() < sc.getX()) || b.getX() > sc.getX()) {
			setX(getX() + getXA());
		} /*else if (b.getX() > sc.getX()) {
			setX(getX() + getXA());
		}*/
		
		 
	}
	
	public void sort(Scanner sc, Conveyer intl, Conveyer dom, Conveyer unknown, Plane p1, Truck t1){
		//if (getX() + getXA() > sc.getX() + (sc.getW() /2) && getX() + getXA() < sc.getX() + 35 + (sc.getW() /2)) {
		if (getX() == sc.getX()) {
			if (getParcelColor() == getBlue()) {
				setY(sc.getWHeight(1) + 45);
				sc.setImage("International");
				
				Parcel.addIntlParcels();
				
				
			} else if (getParcelColor() == Color.GREEN) {
				setY(sc.getWHeight(5) - 25);
				sc.setImage("Domestic");
				Parcel.addDomParcels();
			
				
			} else {
				setY(sc.getWHeight(9) - 105);
				sc.setImage("Unknown");
				Parcel.addUnknownParcels();
				
			}
			sc.setStatus(true);
		} else if(getX() == getCenter().getWidth()) {
			
			
			if (getParcelColor() == getBlue()) {
				intl.setXA(0);
				p1.setXA(-10);
			} else if (getParcelColor() == Color.GREEN) {
				dom.setXA(0);
				t1.setXA(-10);
				
			} else {
				unknown.setXA(0);
			}
		} else if(getX() > sc.getRightX() - 40 && getX() < getCenter().getWidth()) {
			if (getParcelColor() == getBlue()) {
				intl.setXA(1);
				
			} else if (getParcelColor() == Color.GREEN) {
				dom.setXA(1);
				
			} else {
				unknown.setXA(1);
				
			}
			
		} else if (getX() + getXA() > sc.getX() + getL() + sc.getSlant()) {
			sc.setStatus(false);
		
		} 
	}
	
}
