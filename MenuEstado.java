import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Color;
import java.util.Vector;
import java.util.Iterator;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.*;
import java.io.RandomAccessFile;
import java.io.File;

public class MenuEstado extends Objeto{
	
	protected Image imagen;
	 protected Image imagenVidas;
    public MenuEstado(int x, int y, int width, int height)  {       
       	super(x,y,width,height);
       	img = new javax.swing.ImageIcon("Imagenes/FondoBarraEstado.gif").getImage();
       	imagen = new javax.swing.ImageIcon("Imagenes/icono tanque.gif").getImage();
       	imagenVidas = new javax.swing.ImageIcon("Imagenes/Full_Heart.png").getImage();
       	//System.out.println("hola");      	
		
    }
    
	public void paint(Graphics2D g){
		g.drawImage(img,x,y,width,height,null);
		dibujarTanques(BattleCity.getEscenario().getCantidadEnemigos(),g);
		dibujarPuntos(Integer.toString(BattleCity.getJugador().getPuntos()),g);
		dibujarVidas(BattleCity.getJugador().getVidas(),g);
		dibujarNivel(Integer.toString(Escenario.nivelActual+1),g);
		
	}
	
	public void dibujarTanques(int cant, Graphics2D gr){
		int aux = x + 25;
		int auy = y + 15;
		for(int i= 0; i< cant ; i++){
			gr.drawImage(imagen,aux,auy,20,20,null);
			aux = aux +	25;
			if (aux > 700){
				aux = x + 25;
				auy = auy + 25;
			}
		}
	}
	
	public void dibujarPuntos(String str, Graphics2D g){
		g.drawString(str, x+40,y+460);
		//System.out.println("Puntos = " + BattleCity.getJugador().getPuntos() );
	}
	
	public void dibujarVidas(int cant, Graphics2D g){
		int aux = x + 10;
		int auy = y + 510;
		for(int i= 0; i< cant ; i++){
			g.drawImage(imagenVidas,aux,auy,20,20,null);
			aux = aux +	25;
			if (aux >= 735){
				aux = x + 10;
				auy = auy + 25;
			}
		}
		
	}
	
	public void dibujarNivel(String str, Graphics2D g){
		g.drawString(str, x+50,y+410);
	}
	
	public void actualizar(){};
	public void colisiono(){};
	       
}
