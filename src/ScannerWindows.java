import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
public class ScannerWindows extends Scanner{
	
	public ScannerWindows (DistributionCenter dc) {
		super(dc);
	}
	
	@Override
	public void paint (Graphics2D g) {
		//int middleX1 = getX() + getW() / 3;
		//int middleX2 = getX() + getW() * 2 / 3;
		//int rightX = getX() + getW();
		int botY = getY() + getH();
		//int middleY = getY() - 20;
		
		int xRSide[] = {getRightX(), getRightX(), getRightX() + super.getSlant(), getRightX() + super.getSlant() };
		int yRSide[] = {getY(), botY, botY - super.getSlant(), getY() - super.getSlant() };
		
		
		
		int xWindow[] = {getRightX() + super.getSlant() / 5, getRightX() + super.getSlant() * 4 / 5 + 2, getRightX() + super.getSlant() * 4 / 5 + 2, getRightX() + super.getSlant() / 5 };
		int yWindow1[] = super.getWindow(1);
		int yWindow2[] = super.getWindow(5);
		int yWindow3[] = super.getWindow(9);
		
		g.setColor(getShade().darker());
		g.fillPolygon(xRSide, yRSide, 4);
		
		g.setColor(Color.black);
		
		g.fillPolygon(xWindow, yWindow1, 4);
		g.fillPolygon(xWindow, yWindow2, 4);
		g.fillPolygon(xWindow, yWindow3, 4);
		g.drawPolygon(xRSide, yRSide, 4);
		g.setColor(Color.red);
		//g.fillPolygon(xLBorder, yLBorder, 4);
	}

}
