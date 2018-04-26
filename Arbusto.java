import java.awt.*;

public class Arbusto extends Obstaculo{

    public Arbusto(int x, int y) {
    	super(x,y,50,50);
    	img = new javax.swing.ImageIcon("Imagenes/arbusto.gif").getImage();
    }
    
    public void paint(Graphics2D g){
    	g.drawImage(img,x,y,width,height,null);
    }
    
    public void actualizar(){
    	
    }
    
    public void colisiono(){
    	
    }
    
}