import java.io.File;
import java.io.IOException;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Truck {

	private int x;
	private int y;
	private int xa;
	private int ya;
	private int w;
	private int h;
	private BufferedImage deliveryTruck;
	private DistributionCenter dc;

	public Truck(DistributionCenter dc) {
		
		this.dc = dc;
		try {
			deliveryTruck = ImageIO.read(new File("PostTruck.png"));
		} catch (IOException e) {
		}
		
		x = dc.getFrameWidth();
		xa = 0;
		w = 350;
		h = 125;
		y = dc.getFrameHeight() - h - 40;

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
	

	public BufferedImage getImg() {
		return deliveryTruck;
	}
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}
	
	public int getXI() {
		return dc.getFrameWidth();
	}
	
	public int getY() {
		return y;
	}
	


	public void paint(Graphics2D g) {
		
		g.drawImage(getImg(), getX(), getY(), getW(), getH(), null);
		
		if (getX() < 0) {
			setX(getXI());
			setXA(0);
		}
	}
	
	public void move() {
		setX(getX() + getXA());
	}

	

	
}
