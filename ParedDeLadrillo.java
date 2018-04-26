import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Dimension;


public class ParedDeLadrillo extends Obstaculo {
	
	public ParedDeLadrillo(int x, int y){
		super(x,y,50,50);
		img = new javax.swing.ImageIcon("Imagenes/pared de ladrillos.png").getImage();
		//img = new javax.swing.ImageIcon("Imagenes/29983257-Old-school-8-bit-brick-arcade-game-style-background-seamless-vector--Stock-Vector.jpg").getImage();
		//img = new javax.swing.ImageIcon("Imagenes/ladrillos.gif").getImage();
	}
	
	public void paint(Graphics2D g){
		//g.setColor(java.awt.Color.BLACK);
        	//g.fillRoundRect(x+5,y+5,width,height,10,10);
        	g.drawImage(img,x,y,width,height,null);
	}
	
	public void colisiono(){
		//contenedor.agregarEfecto(new Efectos((int)this.getLocation().getX(),(int)this.getLocation().getY()));
		//e.explosionChica();
		//contenedor.agregarObjeto(new ParedDeLadrillo(0,0));
	 	contenedor.sacarObjeto(this);
    	}
	
	public void actualizar(){
		
	}
}
