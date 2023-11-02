import java.awt.*;
import java.awt.event.*;
public class Conveyer {
	private int x;
	private int y;
	private int w;
	private int h;
	private int l;
	private DistributionCenter dc;
	
	private int increment;
	private int wheelIncrement;
	private boolean space;
	private boolean controllable;
	private int xa;
	
	public Conveyer(DistributionCenter dc, int x, int y, int w, int h, int l){
		this.dc = dc;
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.l = l;
		
		increment = 0;
		wheelIncrement = 0;
		space = false;
		xa = 0;
	}
	
	public int getIncrement() {
		return increment;
	}
	
	public int getWheelI() {
		return wheelIncrement;
	}
	public void setIncrement(int n) {
		increment = n;
	}
	
	public void setWheelI(int n) {
		wheelIncrement = n;
	}
	public int getXA() {
		return xa;
	}
	
	public void setXA(int n) {
		xa = n;
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
	
	public static void setMain(Conveyer b) {
		b.controllable = true;
		b.setSpace(true);
		b.setXA(1);
	}
	
	public boolean getSpace() {
		return space;
	}
	
	public void setSpace(boolean n) {
		space = n;
	}
	
	public boolean getControllable() {
		return controllable;
	}
	
	public void paint(Graphics2D g) {
		int slant = (int)(getL() / Math.sqrt(2));
		int rightX = getX() + getW();
		int botY = getY() + getH();
		
		int xCoordsRight[] = {rightX, rightX + slant,rightX + slant, rightX};
		int yCoordsRight[] = {getY(), getY() - slant, botY - slant, botY};
		
		int xCoordsTop[] = {getX(), rightX, rightX + slant, getX() + slant};
		int yCoordsTop[] = {getY(), getY(), getY() - slant, getY() - slant};
		
		
		
	
		
		
		g.setColor(Color.gray);
		
		g.fillRect(getX(),getY(),getW(),getH());
		g.setColor(Color.gray.darker());
		g.fillPolygon(xCoordsRight,yCoordsRight,4);
		g.fillPolygon(xCoordsTop,yCoordsTop,4);
		
		g.setColor(Color.black);
		g.drawRect(getX(),getY(),getW(),getH());
		g.drawPolygon(xCoordsRight,yCoordsRight,4);
		g.drawPolygon(xCoordsTop,yCoordsTop,4);
		
		
		
		/*for (int i =getX(); i < getW() / 50-2; i ++) {
			g.setColor(Color.black);
			g.drawLine(50*i, getY(), 50*i + slant, getY() - slant);
		}*/
		
		
		//Conveyer Lines
		for (int i = 0; i < getW() / 50; i ++) {
			g.setColor(Color.black);
     			
			if (getIncrement() + getX() + 50 *i > getX() + getW()) {
				setIncrement(0);
			}
			g.drawLine(getIncrement() + getX() + 50*i, getY(), getIncrement() + getX() + 50*i + slant, getY() - slant);
		}
		
		
		//Conveyer Switch (For the main one)
		if (getControllable() == true) {
			g.setColor(Color.black);
			g.fillRect(getX()  + getW()/2, getY() + getH(), 20, 20);
			if(getSpace()) {
				g.setColor(Color.green);
			} else {
				g.setColor(Color.red);
			}
	
			
			g.fillArc(getX()  + getW()/2, getY() + getH(), 20, 20, 0, 360);
		}
		
		
		//WHEELS
		for (int i = 0; i < getW() / 50; i ++) {
			g.setColor(Color.gray.darker().darker());
			g.fillOval(getX() + i*50 + 5, getY() + 8, 15,15);
			g.setColor(Color.white.darker());
			
			g.fillArc(getX() + i*50 + 5, getY() + 8, 15,15, 0 - getWheelI(), 40);
			g.fillArc(getX() + i*50 + 5, getY() + 8, 15,15, 180 - getWheelI(), 40);
			
		
		}
	
		
		//End round edge
		
		for (int i = 0; i < getH() - 1; i ++) {
			g.setColor(Color.gray);
			g.fillArc(rightX - 10 + i, getY() - i, 20, getH(), 90, -180);
			g.setColor(Color.black);
			g.drawArc(rightX - 10 + i, getY() - i, 20, getH(), 90, -180);
			
		}
		g.drawArc(rightX - 10, getY(), 20, getH(), 90, -180);
		
		
		
	}
	
	
	public void move() {
		
		//if (getControllable()) {
			//if (getSpace()) {
				setIncrement(getIncrement()+getXA());
				setWheelI(getWheelI() + getXA() * 2);
			//}
		//} else {
			//setIncrement(getIncrement()+getXA());
		//}
		
		
	}
	
	public void keyPressed (KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			
			if (getSpace() == false) {
				setSpace (true);
				setXA(1);
			} else if (getSpace() == true){
				setSpace (false);
				setXA(0);
			}
			
		}
	}
	
}
