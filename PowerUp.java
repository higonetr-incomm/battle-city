import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class PowerUp extends Objeto{
	
	protected int duracion;
	protected int puntos;
	protected boolean obtenido;
	
	
	public PowerUp(int x, int y, int width, int heigth){
		super(x,y,width,heigth);
		obtenido = false;
		puntos = 500 ;
		duracion = 600;
	}
	
	public int getDuracion(){
		return duracion;
	}
	
	public void setDuracion(int t){
		duracion = t;
	}
	
	
    public void setObtenido(){
      obtenido=true;
    }
    public boolean getObtenido(){
     return obtenido;
    }
    
    public int getPuntos(){
		return(puntos);
	}
    
    public void aplicarEfecto(){}
    
    public void pintar(Graphics g){
			g.drawImage(img,x,y,width,height,null);
	}
}
