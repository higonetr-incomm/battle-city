import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.*;

public class Efectos extends Objeto {
		
	protected Vector<Image> imagen ;
	//Image aux;
	private int tiempo, cortes, ancho, alto;
	
	private boolean comportamientoEspecial=false;
	private TanqueJugador tanque;
	private int aux;
	private int i;
	private int aux2;
	private int aux3;

		
	public Efectos(int x, int y,int anchoV,int altoV, TanqueJugador tj){
		super(x,y,anchoV,altoV);
		tanque = tj ;
		tiempo = -1 ;
		//img=null;
		//imagen= new Vector<Image>();
		
		/*
		try {
			imgB = ImageIO.read(new File("Imagenes/explosion chica (51x51).gif"));
		} catch (Exception e) {}*/
		
		//img=(Image)imgB;
		//tiempo=20;
		
		//imagen.add((Image)imgB.getSubimage(0,0,51,51));
		//imagen.add((Image)imgB.getSubimage(51,0,51,51));
		//imagen.add((Image)imgB.getSubimage(102,0,51,51));		
	}
	
	/*public void configurar(File dir, int tiempo, int cantCortes, int ancho, int alto){
		
		try {
			imgB = ImageIO.read(dir);
		} catch (Exception e) {}
		
		//for(int i=0;i<cantCortes;i++){
		aux=(Image)imgB.getSubimage(0,0,51,51);
    			//imagen.add((Image)imgB.getSubimage(0,0,ancho,alto));
		//}
			
	}*/
	
	public Efectos(int x, int y,int anchoV,int altoV){
		super(x,y,anchoV,altoV);
		tiempo = -1 ;
	}
	
	public void setComportamientoEspecial(boolean bol){
		comportamientoEspecial=true;
	}
	
	public void actualizar(){
		
		if(!comportamientoEspecial){
			if((tiempo==aux)&&(aux3<cortes)){
				img = (Image)imgB.getSubimage(aux3*ancho,0,ancho,alto);
				aux=aux-aux2;
				aux3=aux3+1;
			}			
		} else {
			this.x = (int)tanque.getLocation().getX() ;
			this.y= (int)tanque.getLocation().getY() ;
		}
		if( tiempo > 0 )
			tiempo --; 
			if(tiempo == 0 )
				contenedor.sacarEfecto(this);	
	}
	
	public void modificarXY(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void setImagen(File x){
		if(!comportamientoEspecial){
			try {
				imgB = ImageIO.read(x);
			} catch (Exception e) {}
			img=(Image)imgB;
		} else {
			img = new javax.swing.ImageIcon("Imagenes/bonusEscudo.gif").getImage();
		}
		/*
		int j=0;
		for(int i=0;(i<cortarVeces)&&(j<imgB.getWidth());i++){
    			imagen.add(i,(Image)imgB.getSubimage(j,0,ancho,alto));
    			j=j+cambiaImagen;
		}
		
		cambiaImagenCada=tiempo/x;
		cambiaImagen=imgB.getWidth()/cortarVeces;*/
	}
		
	public void configurar(int cant,int ancho, int alto, int tiempo){
		this.cortes=cant;
		this.ancho=ancho;
		this.alto=alto;
		this.tiempo=tiempo;
		this.aux=tiempo;
		this.i=0;
		this.aux2=tiempo/cortes;
		this.aux3=0;
	}
		
	public void paint(Graphics2D g){
        	g.drawImage(img,x,y,width,height,null);
	}
	
	public void colisiono(){
		contenedor.sacarEfecto(this);
	}
}
