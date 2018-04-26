import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Dimension;


public class EstanqueDeAgua extends Obstaculo {
	
	public EstanqueDeAgua(int x, int y){
		super(x,y,50,50);
		img = new javax.swing.ImageIcon("Imagenes/estanque de agua.PNG").getImage();
	}
	
	public void paint(Graphics2D g){
		//g.setColor(java.awt.Color.BLACK);
        	//g.fillRoundRect(x+5,y+5,width,height,10,10);
        	g.drawImage(img,x,y,width,height,null);
	}
	
	 public void colisiono(){
    }
	
	public void actualizar(){
		
	}
}
