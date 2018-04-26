import java.awt.*;
import javax.swing.*;

public class Proyectil extends ObjetoMovible{
	protected Tanque tank;
	
	protected boolean disparadoPorJugador;
	static final int TANQUEJUGADOR=0;
	static final int TANQUENEMIGO=1;
	
	 public void colisiono(){
	 	contenedor.sacarProyectil(this);
    }
	
	public Proyectil (Tanque x){	
		super((int)x.getLocation().getX()+12,(int)x.getLocation().getY()+12,10,10);
		img = new javax.swing.ImageIcon("Imagenes/Laser.gif").getImage();
		//img = 
		step = 4 ;
		if( x instanceof TanqueJugador && ((TanqueJugador)x).getEstrellas() > 1 ){
			step = 6;
		}
		direction = x.ultimaDireccion;
		tank=x;
		//contenedor.agregarProyectil(this);
		if(x instanceof TanqueJugador){
			disparadoPorJugador=true;
			BattleCity.getSonido(5).play();	
		}else
			disparadoPorJugador=false;
		//this.contenedor=x.getEscenario();
		//this.contenedor.agregarObjeto(this);
		//disparadoPorJugador();
	}
	
	/*public Proyectil(int x, int y, double direccion){
		super(x,y,10,10);
		img = new javax.swing.ImageIcon("Imagenes/Laser.gif").getImage();
		step = 10;
		direction=direccion;		
		disparadoPorJugador=false;
		tank=null;
	}*/
	
	/*public void setDisparadoPorJugador(){
		disparadoPorJugador=true;
	}*/
		
	public boolean disparadoPorJugador(){
		if(tank instanceof TanqueJugador)
			return true;
		else
			return false;
	}
	
	public void mover(){
		super.mover();
		direction=tank.frente();
	}
	
	public void collides(){
		//this.contenedor.sacarProyectil(this);
		//tank.hayProyectil=false;
		direction = DIR_NONE;
	}
	
	public void actualizar(){
		super.mover();
		if (direction==DIR_NONE)
			contenedor.sacarProyectil(this);
	}
	
	public void paint(Graphics2D g){
		g.setColor(java.awt.Color.WHITE);
        	g.fillRoundRect(x,y,width,height,10,10);
        	//g.drawImage(null,x,y,width,height,null);
		g.drawImage(img,x,y,width,height,null);	
	}
	
	public Tanque tanqueAsociado(){
		return tank;
	}
}
