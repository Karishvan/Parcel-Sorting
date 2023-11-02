import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Plane {

	private int x;
	private int y;
	private int xa;
	private int ya;
	private int w;
	private int h;
	private BufferedImage airCanada;
	private BufferedImage westJet;
	private BufferedImage img;
	private DistributionCenter dc;

	public Plane(DistributionCenter dc) {
		double rand = Math.random();
		
		this.dc = dc;
		try {
			airCanada = ImageIO.read(new File("AirCanada.png"));
			westJet = ImageIO.read(new File("WestJet.png"));
		} catch (IOException e) {
		}
		
		if (rand < 0.5) {
			img = westJet;
		} else {
			img = airCanada;
		}
		
		x = dc.getFrameWidth()  + 400;
		y = 0;
		xa = 0;
		w = -390;
		h = 134;

	}
	
	private DistributionCenter getDc() {
		return dc;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getXA() {
		return xa;
	}
	
	public void setXA(int xa) {
		this.xa = xa;
	}
	

	public BufferedImage getAirCanada() {
		return airCanada;
	}
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}
	
	public int getXI() {
		return dc.getFrameWidth()  + 400;
	}
	
	public int getY() {
		return y;
	}
	
	private BufferedImage getWestJet() {
		return westJet;
	}

	private BufferedImage getImg() {
		return img;
	}
	
	private void setImg (BufferedImage n) {
		img = n;
	}

	public void paint(Graphics2D g) {
		double rand = Math.random();
		
		int rightX = getX() - getW();
		g.drawImage(getImg(), getX(), getY(), getW(), getH(), null);
		
		if (rightX < 0) {
			//System.out.println(rand);
			if (rand < 0.5) {
				setImg(getWestJet());
			} else {
				setImg(getAirCanada());
			}
			setX(getXI());
			setXA(0);
		}
	}
	
	public void move() {
		setX(getX() + getXA());
	}

	

	
}
