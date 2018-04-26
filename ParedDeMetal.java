import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Dimension;

public class ParedDeMetal extends Obstaculo {

		private int duracion;

	public ParedDeMetal(int x,int y){
		super(x,y,50,50);
		img = new javax.swing.ImageIcon("Imagenes/pared de metal.png").getImage();
		duracion = -1 ;
	}

	public ParedDeMetal(int x, int y, int d){
		super(x,y,50,50);
		img = new javax.swing.ImageIcon("Imagenes/pared de metal.png").getImage();
		duracion = d;
	}

	public void paint(Graphics2D g){
		g.setColor(java.awt.Color.BLACK);
        	//g.fillRoundRect(x+5,y+5,width,height,10,10);
        	g.drawImage(img,x,y,width,height,null);
	}

	public void colisiono(){
		contenedor.sacarObjeto(this);
    }

	public void actualizar(){
		if ( duracion > 0 ){
			duracion--;
			if(duracion == 0 ){
				//int auxX = this.x; 
				//int auxY = this.y;
				contenedor.incorporarObstaculo( new ParedDeLadrillo(this.x,this.y) );
				contenedor.sacarObjeto(this);
				//contenedor.incorporarObstaculo( new ParedDeLadrillo(auxX,auxY) );
				System.out.println("x " + x + " y " + y );
				
				//contenedor.incorporarObstaculo( new ParedDeLadrillo(5*50,11*50) );
				//contenedor.incorporarObstaculo( new ParedDeLadrillo(6*50,11*50) );
				//contenedor.incorporarObstaculo( new ParedDeLadrillo(7*50,11*50) );
				//contenedor.incorporarObstaculo( new ParedDeLadrillo(7*50,12*50) );
			}
		}
	}
}
