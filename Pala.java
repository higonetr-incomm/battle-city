import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Pala extends PowerUp{
	
	
	public Pala(int x, int y){
		super(x,y,30,30);
		img = new javax.swing.ImageIcon("Imagenes/bonus/bonusPala.gif").getImage() ;
		colisiono = false;
	}
	
	public void actualizar(){
		duracion = duracion -1 ;
		if(duracion == 0 && !colisiono)
			contenedor.sacarBonus(this);
			
	}
	
	public void paint(Graphics2D g){
		g.drawImage(img,x,y,width,height,null);
	}
	
	public void colisiono(){
		contenedor.sacarBonus(this);
		colisiono = true;
	} 
	
	public void efectoBonus(){
		
		contenedor.reforzarBase();
		//contenedor.reforzarBase( new ParedDeMetal(0*50,5*50,500) );
		//contenedor.reforzarBase( new ParedDeMetal(1*50,5*50,500) );
		//contenedor.reforzarBase( new ParedDeMetal(1*50,6*50,500) );
		//contenedor.reforzarBase( new ParedDeMetal(1*50,7*50,500) );
		//contenedor.reforzarBase( new ParedDeMetal(0*50,7*50,500) );
		
		//////////////////////////////////////
		
		//contenedor.reforzarBase( new ParedDeMetal(450,2,500) );
		//contenedor.reforzarBase( new ParedDeMetal(400,2,500) );
		//contenedor.reforzarBase( new ParedDeMetal(350,2,500) );
		//contenedor.reforzarBase( new ParedDeMetal(300,2,500) );
		
	}
}
