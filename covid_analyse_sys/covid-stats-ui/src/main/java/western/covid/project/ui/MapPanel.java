package western.covid.project.ui;

import com.covid.country.operation.ICountryOperation;
import com.covid.country.operation.impl.CountryOperationImpl;
import western.covid.project.dispatch.observe.Observer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * A customized JPanel help to render background image and red filled circle.
 * @author Tianci Du
 * @version 1.0
 */
public class MapPanel extends JPanel implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage myPicture;
	int mapWidth;
	int mapHeight;

	/**
	 * Initialize background image.
	 */
	public MapPanel() {
		try {
			String path = System.getProperty("user.dir");
			String imagePath=path+File.separator+"images"+File.separator+"map.jpeg";
			System.out.println("path:"+imagePath);
			myPicture = ImageIO.read(new File(imagePath));
			mapWidth = myPicture.getWidth();
			mapHeight = myPicture.getHeight();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Override
//	public void paint(Graphics g) {
//		super.paint(g);
//		System.out.println("pic width:"+myPicture.getWidth()+","+myPicture.getHeight());
//		g.drawImage(myPicture, 0, 0, super.getWidth(), super.getHeight(), this);
//		g.setColor(Color.red);
////		g.drawOval(422, 100, 30, 30);
////		this.drawCircle(myPicture, 104.195397, 35.86166, mapWidth, mapHeight, 30);
//
//	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(myPicture, 0, 0, super.getWidth(), super.getHeight(), this);
	}

	/**
	 * To render the red circle with new coming data.
	 * @param countryList
	 * @param map
	 */
	public void refresh(List<String> countryList,Map<String,Double> map){
		String path = System.getProperty("user.dir");
		String imagePath=path+File.separator+"images"+File.separator+"map.jpeg";
		try {
			myPicture = ImageIO.read(new File(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ICountryOperation countryOperation=new CountryOperationImpl();
		for (String country : countryList){
			Double[] coordinate=countryOperation.getCountryCoordinate(country);
			int oval=this.calcOvalDimension(map,countryList,country);
			this.drawCircle(myPicture,coordinate[0],coordinate[1],mapWidth,mapHeight,oval,map.get(country));
		}
	}

//	public void refresh(double longitude, double latitude, Map<String,Double> casesMap, List<String> countryList, String country) {
//
//		int oval=this.calcOvalDimension(casesMap,countryList,country);
//		this.drawCircle(myPicture, longitude, latitude, mapWidth, mapHeight, oval);
//	}

	/**
	 * render the red circle on the background image.
	 * @param myPicture
	 * @param longitude
	 * @param latitude
	 * @param mapWidth
	 * @param mapHeight
	 * @param ovalDimension
	 */
	private void drawCircle(BufferedImage myPicture,double longitude,double latitude,int mapWidth,int mapHeight,int ovalDimension,Double cases) {
		// longitude and latitude values are of type double as given
		Point2D coords = new Point2D.Double(longitude, latitude);
		
		System.out.println("Coordinates: " + coords.getX() + ", " + coords.getY());
		// Add the circle to the image
		Point testPoint = getXY(coords.getY(), coords.getX(), mapWidth, mapHeight);
		Graphics2D editableImage = (Graphics2D) myPicture.getGraphics();
		if (cases < 1){			//per capita case
			if (cases >= 0.01){
				editableImage.setColor(Color.red);
			}else {
				editableImage.setColor(Color.BLUE);
			}
		}
		else {
			if (cases >= 50000.0){
				editableImage.setColor(Color.RED);
			}else {
				editableImage.setColor(Color.BLUE);
			}
		}
		editableImage.setStroke(new BasicStroke(3));
		editableImage.fillOval(testPoint.x - (ovalDimension / 2),testPoint.y - (ovalDimension / 2), ovalDimension,ovalDimension);
		editableImage.dispose();
	}

	/**
	 * Retrieve the position of circle on the background image.
	 * @param lat
	 * @param lng
	 * @param mapWidth
	 * @param mapHeight
	 * @return
	 */
	private Point getXY(double lat, double lng, int mapWidth, int mapHeight) {
		int screenX = (int) Math.round((((lng + 180) / 360) * mapWidth));
		int screenY = (int) Math.round(((((lat * -1) + 90) / 180) * mapHeight)); 
		System.out.println("screenx:"+screenX+",screenY:"+screenY);
		return new Point(screenX, screenY);
	}


	/**
	 * Calculating the size of Oval dimension.
	 * @param cases
	 * @param countryList
	 * @param countryName
	 * @return
	 */
	private Integer calcOvalDimension(Map<String,Double> cases, List<String> countryList,String countryName) {

		Double maxValue = 0.0;
		Double temp;
		for (String country:countryList){
			temp = cases.get(country);
			if (temp > maxValue){
				maxValue = temp;
			}
		}
		int maxOvalDimension;
		if (maxValue < 10000.0){
			maxOvalDimension = 20;
		}
		else if (maxValue < 50000.0){
			maxOvalDimension = 30;
		}
		else if (maxValue < 100000.0){
			maxOvalDimension = 50;
		}
		else {
			maxOvalDimension = 70;
		}
		int minOvalDimension=15;
		int oval;
		double percentage;
		percentage = cases.get(countryName)/maxValue;
		oval = (int)Math.round(((maxOvalDimension - minOvalDimension) * percentage) + minOvalDimension);
		return oval;


	}
	private void refreshAll(){
		this.repaint();
	}

	@Override
	public void update(Object obj) {
		String msg=String.valueOf(obj);
		if (msg!=null&&msg.equals("refreshAll")){
			this.refreshAll();
		}
		this.repaint();

	}
}
