import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Scanner {
	private static DistributionCenter dc;
	private int x;
	private int y;
	private int w;
	private int h;
	private int l;
	private Color shade;
	private BufferedImage img;
	private BufferedImage international;
	private BufferedImage domestic;
	private BufferedImage unknown;
	private BufferedImage canadaPost;
	private boolean isScanning;
	private static int count;

	public Scanner(DistributionCenter dc) {
		this.dc = dc;
		x = dc.getFrameWidth() / 2 - 140;
		y = dc.getFrameHeight() / 2 - 200;
		w = 200;
		h = 450;
		l = 75;
		shade = new Color(102, 153, 153);
		img = null;
		isScanning = false;
		
		try {
			international = ImageIO.read(new File("International.png"));
			domestic = ImageIO.read(new File("Domestic.png"));
			unknown = ImageIO.read(new File("Unknown.png"));
			canadaPost = ImageIO.read(new File("CanadaPost.png"));
		} catch (IOException e) {
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}

	public int getL() {
		return l;
	}

	public Color getShade() {
		return shade;
	}

	public int[] getWindow(int n) {
		int hi = 20;
		int yi = getY() + getH() * n / 12;
		int yf = (getY() + getH() * (n + 2) / 12);
		
		int[] window = {yi - hi, yi - getSlant() + (hi/5), yf - getSlant() + (hi /2 * 3), yf + hi/4 };
		return window;
	}
	
	/*protected int[] newWindow(int n, int slant) {
		
		int [] window = {getY() + getH() * n/12, (getY() + getH() * n/12 - slant)/4, (getY() + getH() * n/12 + slant)/4, getY() + getH() * n/12 + 50};
		return window;
	}*/

	public int getWHeight(int n) {
		return getY() + getH() * n / 8;
	}
	
	public boolean getStatus() {
		return isScanning;
	}
	
	public static void setCount(int n) {
		count = n;
	}
	
	public void setStatus(boolean result) {
		if (result) {
			isScanning = true;
		} else if (!result && count % 80 == 0){
			isScanning = false;
		}
	}
	
	public BufferedImage getIntl() {
		return international;
	}
	
	public BufferedImage getDom() {
		return domestic;
	}
	
	public BufferedImage getUnknown() {
		return unknown;
	}
	
	public BufferedImage getCPost() {
		return canadaPost;
	}
	


	public void setImage(String str) {
		//BufferedImage international = null;
		//BufferedImage domestic = null;
		//BufferedImage unknown = null;
		
		if (str.equals("International")) {
			img = getIntl();
		} else if (str.equals("Domestic")) {
			img = getDom();
		} else {
			img = getUnknown();
		}
	}
	
	public BufferedImage getImage() {
		return img;
	}
	
	public int getRightX() {
		return getX() + getW();
	}
	
	public int getSlant() {
		double temp = getL() /  Math.sqrt(2);
		return (int) temp;
	}

	public void paint(Graphics2D g) {
	
		
		//int slant = getL() / (int) Math.sqrt(2);
		int middleX1 = getX() + getW() / 3;
		int middleX2 = getX() + getW() * 2 / 3;
		//int rightX = getX() + getW();
		int botY = getY() + getH();
		int middleY = getY() - 20;
		
		//int windowSlant = 50 / (int) Math.sqrt(2);

	

		int xFrontSide[] = { getX(), middleX1, middleX2, getRightX(), getRightX(), getX() };
		int yFrontSide[] = { getY(), middleY, middleY, getY(), botY, botY };

		int xTopSide[] = { middleX1, middleX2, middleX2 + getSlant(), middleX1 + getSlant(), middleX1 };
		int yTopSide[] = { middleY, middleY, middleY - getSlant(), middleY - getSlant(), middleY };

		int xTopRSide[] = { middleX2, getRightX(), getRightX() + getSlant(), middleX2 + getSlant(), middleX2 };
		int yTopRSide[] = { middleY, getY(), getY() - getSlant(), middleY - getSlant(), middleY };

		//int xRSide[] = { getRightX(), getRightX(), getRightX() + slant, getRightX() + slant };
		//int yRSide[] = { getY(), botY, botY - slant, getY() - slant };

		int xTopLSide[] = { getX(), middleX1, middleX1 + getSlant(), getX() + getSlant(), getX() };
		int yTopLSide[] = { getY(), middleY, middleY - getSlant(), getY() - getSlant(), getY() };
		
		
		int xLBorder[] = {getRightX(), getRightX() + getSlant()/4, getRightX() + getSlant() * 1/4, getRightX()};
		int yLBorder[] = {getY(), getY() - getSlant() * 1/4, botY - getSlant() * 1/4, botY};

		//int xWindow[] = {getRightX() + slant / 4, getRightX() + slant * 3 / 4, getRightX() + slant * 3 / 4, getRightX() + slant / 4};

		//int yWindow1[] = getWindow(1, slant);
		//int yWindow2[] = getWindow(3, slant);
		//int yWindow3[] = getWindow(5, slant);
		// int yWindow1[] = {getY() + getH() * 1/8 - 20, (getY() + getH() * 1/8) - slant
		// + 10, (getY() + getH() *2/8) - slant + 10, getY() + getH()*2/8 - 20};
		// int yWindow2[] = {getY() + getH() * 3/8 - 20, (getY() + getH() * 3/8) - slant
		// + 10, (getY() + getH() *4/8) - slant + 10, getY() + getH()*4/8 - 20};
		// int yWindow3[] = {getY() + getH() * 5/8 - 20, (getY() + getH() * 5/8) - slant
		// + 10, (getY() + getH() *6/8) - slant + 10, getY() + getH()*6/8 - 20};

		g.setColor(getShade());
		g.fillPolygon(xFrontSide, yFrontSide, 6);
		g.setColor(getShade().darker());
		g.fillPolygon(xTopSide, yTopSide, 4);
		g.fillPolygon(xTopRSide, yTopRSide, 4);
		//g.fillPolygon(xRSide, yRSide, 4);
		g.fillPolygon(xTopLSide, yTopLSide, 4);
		g.fillPolygon(xLBorder, yLBorder, 4);

		g.setColor(Color.black);
		g.drawPolygon(xFrontSide, yFrontSide, 6);
		g.drawPolygon(xTopSide, yTopSide, 4);
		g.drawPolygon(xTopRSide, yTopRSide, 4);
		//g.drawPolygon(xRSide, yRSide, 4);
		g.drawPolygon(xTopLSide, yTopLSide, 4);
		
		
		
		//g.fillPolygon(xWindow, yWindow1, 4);
		//g.fillPolygon(xWindow, yWindow2, 4);
		//g.fillPolygon(xWindow, yWindow3, 4);
		
		if (getStatus() == false) {
			g.setColor(Color.green);
		} else {
			g.setColor(Color.red);
		}
		
		g.fillRect(getX() + 20, getY() + 20, 25,25);
		g.drawImage(getCPost(), getRightX() - 50, getY() + 15, 50,50, null);
		
		
		g.setColor(Color.black);
		g.drawRect(getX() + 20, getY() + 20, 25,25);

		g.drawImage(getImage(), 0, 501, 100, 100, null);
		
	}

	private static DistributionCenter getDc() {
		return dc;
	}
}
