import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Estrella extends PowerUp{
	
	
	public Estrella(int x, int y){
		super(x,y,30,30);
		img = new javax.swing.ImageIcon("Imagenes/bonus/bonusEstrella.gif").getImage() ;
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
	
	public void efectoBonus(TanqueJugador tj){
		tj.setEstrella(1);
	}
}
