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
import java.io.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;


public class PantallaGanoElJuego extends Objeto{ 
	
	private Vector<Image> imagenesMenu;
	private int gestorMovimientoMenu=0;
	
	private int tiempoCargas;
	private boolean yaCargoLaBarra;
	
	public PantallaGanoElJuego(int x, int y, int width, int height)  {       
       	super(x,y,width,height);
       	
       	imagenesMenu = new Vector<Image>();
       	
       	tiempoCargas=0;
       	yaCargoLaBarra=false;
       	
       	imagenesMenu.add(new javax.swing.ImageIcon("Imagenes/pantallaGanoElJuego/1.png").getImage());
       	//imagenesMenu.add(new javax.swing.ImageIcon("Imagenes/pantallaGanoElJuego/2.png").getImage());
       
		img = new javax.swing.ImageIcon("Imagenes/pantallaGanoElJuego/1.png").getImage();
	}
	
	public void paint(Graphics g){
		//g.setColor(Color.RED);
		//g.fillRect(0,0,getWidth(),getHeight());
		//rellena un rectangulo
		//g.setColor(Color.red); PARA QUE LA LINEA ROJA NO APAREZCA
		//g.drawRect(0,0,img.getWidth(this)+1 ,img.getHeight(this)+1);
		//dibuja el rectangulo por afuera de la imagen
		//si las imagenes ocupan todo el espacio las lineas anteriores no son necesarias
		g.drawImage(img,x,y,width,height,null);
		//g.drawString("NIVEL ",350,350);
	}
	
	public void actualizar(){
		if((tiempoCargas==0)&&(VideoJuego.maquinaDeEstado==5)){
			tiempoCargas=200;
		}
		
		if(yaCargoLaBarra==false){
			//if(tiempoCargas==100){
				//img = imagenesMenu.get(1);
			//}
			if(tiempoCargas==1)
				yaCargoLaBarra=true;
		}
		
		if(VideoJuego.maquinaDeEstado==3)
			yaCargoLaBarra=false;
		
		if(tiempoCargas>0)
			tiempoCargas--;
		
		//img = new javax.swing.ImageIcon("Imagenes/fondo escenario.gif").getImage();
		//if(BattleCity.getSonido(2).isRunning()){
			//System.out.println(BattleCity.getSonido(2).isRunning());
			//BattleCity.getSonido(2).play();
		//}
		
	}
	
	public boolean Cargo(){
		return yaCargoLaBarra;
	}
	
	public void colisiono(){}
	
}
