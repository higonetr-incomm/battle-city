import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.*;

public class Base extends Objeto{
	
	public Base(){
		super(300,600,50,50);
		
		try {
			imgB = ImageIO.read(new File("Imagenes/aguila (51x52).gif"));
		} catch (Exception e) {}
		
		img = (Image)imgB.getSubimage(0,0,51,52);
		
		//img = new javax.swing.ImageIcon("Imagenes/aguila (51x52).gif").getImage();
	}
	
	public void paint(Graphics2D g){
		//g.setColor(java.awt.Color.BLACK);
        	//g.fillRoundRect(x+5,y+5,width,height,10,10);
        	g.drawImage(img,x,y,width,height,null);
	}
	
	 public void colisiono(){
	 	System.out.println("FIN DEL JUEGO");
	 	BattleCity.getSonido(3).play();	
	 	//contenedor.sacarObjeto(this);
	 	img = (Image)imgB.getSubimage(51,0,51,52);
	 	BattleCity.cargarPuntaje();
	 	VideoJuego.maquinaDeEstado=4;
	 	
    }
	
	public void actualizar(){
		
	}
	
}	
