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


public class PantallaPasarNivel extends Objeto{ 
	
	private Vector<Image> imagenesMenu;
	private int gestorMovimientoMenu=0;
	
	private int tiempoCargas;
	private boolean yaCargoLaBarra;
	
	public PantallaPasarNivel(int x, int y, int width, int height)  {       
       	super(x,y,width,height);
       	
       	imagenesMenu = new Vector<Image>();
       	
       	tiempoCargas=0;
       	yaCargoLaBarra=false;
       	
       	imagenesMenu.add(new javax.swing.ImageIcon("Imagenes/menu/carga0.png").getImage());
       	imagenesMenu.add(new javax.swing.ImageIcon("Imagenes/menu/carga1.png").getImage());
       	imagenesMenu.add(new javax.swing.ImageIcon("Imagenes/menu/carga2.png").getImage());
       	imagenesMenu.add(new javax.swing.ImageIcon("Imagenes/menu/carga3.png").getImage());
       	imagenesMenu.add(new javax.swing.ImageIcon("Imagenes/menu/carga4.png").getImage());
       	imagenesMenu.add(new javax.swing.ImageIcon("Imagenes/menu/carga5.png").getImage());
       	imagenesMenu.add(new javax.swing.ImageIcon("Imagenes/menu/carga6.png").getImage());
       	imagenesMenu.add(new javax.swing.ImageIcon("Imagenes/menu/carga7.png").getImage());
       	

       	
       	//BattleCity.getSonido(2).play();
       	
		img = new javax.swing.ImageIcon("Imagenes/menu/carga0.png").getImage();
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
		if((tiempoCargas==0)&&(VideoJuego.maquinaDeEstado==2)){
			tiempoCargas=250;
		}
		
		if(yaCargoLaBarra==false){
			if(tiempoCargas==250)
				img = imagenesMenu.get(1);
			if(tiempoCargas==210)
				img = imagenesMenu.get(2);
			if(tiempoCargas==180)
				img = imagenesMenu.get(3);
			if(tiempoCargas==150)
				img = imagenesMenu.get(4);
			if(tiempoCargas==120)
				img = imagenesMenu.get(5);
			if(tiempoCargas==70)
				img = imagenesMenu.get(6);
			if(tiempoCargas==30){
				img = imagenesMenu.get(7);
				yaCargoLaBarra=true;
			}
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
	
	public void colisiono(){}
	
}
