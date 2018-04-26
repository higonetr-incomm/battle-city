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


public class MenuInicio extends Objeto implements KeyListener{ 
	
	private Vector<Image> imagenesMenu;
	private int gestorMovimientoMenu=0;
	
	public MenuInicio(int x, int y, int width, int height)  {       
       	super(x,y,width,height);
       	
       	imagenesMenu = new Vector<Image>();
       	
       	imagenesMenu.add(new javax.swing.ImageIcon("Imagenes/menu/MenuIniciar.png").getImage());
       	imagenesMenu.add(new javax.swing.ImageIcon("Imagenes/menu/MenuSalir.png").getImage());
       	
       	
       	//BattleCity.getSonido(2).play();
       	
		img = new javax.swing.ImageIcon("Imagenes/menu/MenuIniciar.png").getImage();
	}
	
	public void paint(Graphics g){
		//g.setColor(Color.black);
		//g.fillRect(0,0,getWidth(),getHeight());
		//rellena un rectangulo
		//g.setColor(Color.red); PARA QUE LA LINEA ROJA NO APAREZCA
		//g.drawRect(0,0,img.getWidth(this)+1 ,img.getHeight(this)+1);
		//dibuja el rectangulo por afuera de la imagen
		//si las imagenes ocupan todo el espacio las lineas anteriores no son necesarias
		g.drawImage(img,x,y,width,height,null);
	}
	
	public void actualizar(){
		//img = new javax.swing.ImageIcon("Imagenes/fondo escenario.gif").getImage();
		//if(BattleCity.getSonido(2).isRunning()){
			//System.out.println(BattleCity.getSonido(2).isRunning());
			//BattleCity.getSonido(2).play();
		//}
		//System.out.println(gestorMovimientoMenu);
	}
	
	public void colisiono(){
	
	}
	
	public void keyPressed(KeyEvent e){
		if(VideoJuego.maquinaDeEstado==1){ 			
			if((e.getKeyCode() == KeyEvent.VK_ENTER)&&(gestorMovimientoMenu==0)){
					//System.out.println("...");
					VideoJuego.maquinaDeEstado =3;
			}
			if((e.getKeyCode() == KeyEvent.VK_ENTER)&&(gestorMovimientoMenu==1)){
				System.exit(0);
				//BattleCity.f.dispose();
				//BattleCity.f.exit();
			}					
		}
    }
    
    
    public void keyReleased(KeyEvent e){
		
    	if(VideoJuego.maquinaDeEstado==1){ 		
			BattleCity.getSonido(11).play();	
			if((e.getKeyCode() == KeyEvent.VK_DOWN)&&(gestorMovimientoMenu==0)){
				gestorMovimientoMenu=1;
				//System.out.println("entra2");
				img=imagenesMenu.get(gestorMovimientoMenu);
			}
			if((e.getKeyCode() == KeyEvent.VK_UP)&&(gestorMovimientoMenu==1)){
				gestorMovimientoMenu=0;
				//System.out.println("entra1.");
				img=imagenesMenu.get(gestorMovimientoMenu);
			}
		}
    } 
    
    public void keyTyped(KeyEvent e) {}
}


