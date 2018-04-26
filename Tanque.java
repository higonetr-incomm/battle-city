import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.util.*;
import java.awt.*;

public abstract class Tanque extends ObjetoMovible{
	protected boolean hayProyectil; //disparo
	protected double ultimaDireccion; //o frente
	
	protected Vector<Image> imagen ;
	
	public Tanque(){
		super(300,500,40,40);
		hayProyectil=false;
	}
	public Tanque(int x, int y, int width, int height){
		super(x,y,width,height);
	}
	public void mover(){
		if (nextX()>0 & 
			nextX()+width < contenedor.getWidth() && 
			nextY()>0 && 
			nextY()+height < contenedor.getHeight()) { 
	
			recColision = new Rectangle(nextX(),nextY(),this.width,this.height);
			//for tanto if recColision intersecta algo
			for(Objeto obj : this.contenedor.objetos){
				if((obj!=this)&&!(obj instanceof Proyectil)&&!(obj instanceof Arbusto)&&!(obj instanceof Efectos)){
					//System.out.println(obj instanceof ParedDeLadrillo);
					colisiona=recColision.intersects(obj);
					if(colisiona) 
						break;
					//recColision.getBounds().intersects(vectorRectangle.get("Obstaculo").get(i).getBounds())
					//System.out.println("ESTOY VIENDO SI CHOCA CON LOS OBJETOS"+colisiona);
				}
			}		
			
			if ((direction != DIR_NONE)&&(colisiona==false)){
				x += Math.round(Math.cos(Math.toRadians(direction)) * step);
				y += Math.round(Math.sin(Math.toRadians(direction)) * step);
				//System.out.println("moviendo...");
			}
			
			/*if (x<=0 || 
				x+width >= contenedor.getWidth() ||
				y<=0 ||
				y+height >= contenedor.getHeight()){				
				collides();
				System.out.println("no se q hace...");}*/	
		}
		else collides();
	}
	
	public double frente(){
		return ultimaDireccion;
	}
	
	public void actualizar(){
		mover();
		if(direction==DIR_UP)
			img=imagen.get(0);
		if(direction==DIR_DOWN)
			img=imagen.get(1);
		if(direction==DIR_RIGHT)
			img=imagen.get(2);
		if(direction==DIR_LEFT)
			img=imagen.get(3);
	}
	
	public void collides(){
		direction = DIR_NONE;
	}
	
	public void dispararProyectil(Tanque x){
		if(hayProyectil==false){
			contenedor.agregarProyectil(new Proyectil(this));
			//this.contenedor.agregarObjeto(p); /*ESTO ME TIRA ERROR!!! FAK*/
			hayProyectil = true;
		}
	}
}
