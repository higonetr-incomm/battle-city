import java.awt.*;
import java.util.*;
import java.io.File;

public class TanqueJugador extends Tanque implements Controlable
{
   private Graphics2D g;
   private boolean escudoActivado;
   private Efectos e;
   private int cantEstrellas;
   private int tiempoPower;
   private boolean indestructible, muerto ;

   private String colorTanque;

	

    public TanqueJugador(String colorTanque)
    {
        super(300,500,36,36);
        step = 2;
        this.colorTanque=colorTanque;
        //img = new javax.swing.ImageIcon("Imagenes/Tank arriba.png").getImage();
        img = new javax.swing.ImageIcon("Imagenes/Tanques Jugador/"+colorTanque+"ARRIBA.gif").getImage();

        imagen = new Vector<Image>() ;
        //imagen.add (new javax.swing.ImageIcon("Imagenes/Tank arriba.png").getImage() );
        imagen.add (new javax.swing.ImageIcon("Imagenes/Tanques Jugador/"+colorTanque+"ARRIBA.gif").getImage() );
        //imagen.add (new javax.swing.ImageIcon("Imagenes/Tank abajo.png").getImage() );
        imagen.add (new javax.swing.ImageIcon("Imagenes/Tanques Jugador/"+colorTanque+"ABAJO.gif").getImage() );
        //imagen.add (new javax.swing.ImageIcon("Imagenes/Tank derecha.png").getImage() );
        imagen.add (new javax.swing.ImageIcon("Imagenes/Tanques Jugador/"+colorTanque+"DERECHA.gif").getImage() );
        //imagen.add (new javax.swing.ImageIcon("Imagenes/Tank izquierda.png").getImage() );
        imagen.add (new javax.swing.ImageIcon("Imagenes/Tanques Jugador/"+colorTanque+"IZQUIERDA.gif").getImage() );
        

        muerto = false;
        cantEstrellas = 1 ;
        tiempoPower = -1 ;
        indestructible = false;
    
        direction = DIR_NONE;
        ultimaDireccion = DIR_UP;
        hayProyectil=false;
    }


	/*
    public void mover() {
        	super.mover();
        //System.out.println("x="+x+";y="+y);
        //if(oyente!=null)
        	//	oyente.TanqueMoved(this);
    }

    //invoked when this object collides with the surrounding space
    //--> la nave detiene su marcha cuando choca con las parades del escenario
    public void collides(){
		//System.out.println("colision!");
		direction = DIR_NONE;
	}*/

    public void procesarControl(String accion, Control control){
	//	System.out.println(accion);
	//	System.out.println(control.getControl(Control.DERECHA));
		if(!muerto){
			if (accion.equals(control.getControl(Control.IZQUIERDA))){
				direction = DIR_LEFT;
				ultimaDireccion = DIR_LEFT;
				//img = new javax.swing.ImageIcon("Imagenes/Tank izquierda.png").getImage();
				//g.drawImage(img,x,y,width,height,null);
				//g.drawImage(new javax.swing.ImageIcon("Imagenes/Tank izquierda.png").getImage(),x,y,width,height,null);
				/*esto tira excepcion, ver esto: http://stackoverflow.com/questions/8639567/java-rotating-images*/
			}
			if (accion.equals(control.getControl(Control.DERECHA)) ){
				direction = DIR_RIGHT;
				ultimaDireccion = DIR_RIGHT;
				//img = new javax.swing.ImageIcon("Imagenes/Tank derecha.png").getImage();
				//g.drawImage(img,x,y,width,height,null);
			}
			if (accion.equals(control.getControl(Control.ARRIBA))){
				direction = DIR_UP;
				ultimaDireccion = DIR_UP;
				//img = new javax.swing.ImageIcon("Imagenes/Tank arriba.png").getImage();
				//g.drawImage(img,x,y,width,height,null);
			}
			if (accion.equals(control.getControl(Control.ABAJO))){
				direction = DIR_DOWN;
				ultimaDireccion = DIR_DOWN;
				//img = new javax.swing.ImageIcon("Imagenes/Tank abajo.png").getImage();
				//g.drawImage(img,x,y,width,height,null);
			}
			if (accion.equals(control.getControl(Control.TIRO))){
				dispararProyectil(this);
			}
		}
		if (accion.equals(control.getControl(Control.PAUSA))){
			contenedor.setPause();
			BattleCity.getSonido(10).play();	
		}
    }

    public void controlTerminado(){
		direction = DIR_NONE;
    }

     public void paint(Graphics2D g){
        //g.setColor(java.awt.Color.BLACK);
        //g.fillRoundRect(x+5,y+5,width,height,10,10);
		if(!muerto)
			g.drawImage(img,x,y,width,height,null);
    }
    
   

    	 public void colisiono(){
         	BattleCity.getJugador().quitarVida();
         	muerto = true;
    	 	//contenedor.sacarObjeto(this);


    }
   
	public boolean isMuerto(){
		return muerto;
	}

    public void obtenerBonus(PowerUp p){

		if( p instanceof Bomba){
			((Bomba)p).efectoBonus();
			BattleCity.getSonido(1).play();	
		}
		if( p instanceof TanqueBonus){
			((TanqueBonus)p).efectoBonus();
			BattleCity.getSonido(0).play();	
		}
		if( p instanceof Escudo){
			((Escudo)p).efectoBonus(this);
			escudoActivado=true;
			BattleCity.getSonido(7).play();	
		}
		if( p instanceof Estrella){
			((Estrella)p).efectoBonus(this);
			BattleCity.getSonido(0).play();
			//o directamente sumar aca la estrella, me parece mas facil
			//asi no habria que modificar efectoBonus en estrella y lo
			//que haria efectoBonus() es cambiar la img del tanque
		}
		if( p instanceof Reloj){
			BattleCity.getSonido(0).play();
			((Reloj)p).efectoBonus();
		}
		if( p instanceof Pala){
			BattleCity.getSonido(2).play();
			((Pala)p).efectoBonus();
		}
	}

    public void actualizar(){
    		super.actualizar();
			if( tiempoPower > 0 ){
				tiempoPower--;
				if(tiempoPower == 0 ){
					indestructible = false;
					tiempoPower = -1 ;
					escudoActivado=false;
					//e.setImagen(null);
			}
		}
    }
    

    public void setEstrella(int i){
		cantEstrellas = cantEstrellas + i;
		
	}

	public int getEstrellas(){
		return cantEstrellas;
	}
	
	public void resetear(){
		cantEstrellas = 1;
		muerto = false;
        cantEstrellas = 1 ;
        tiempoPower = -1 ;
        setIndestructible(true);
        //this.setLocation(4*50,12*50);
        this.x = 4 * 50;
        this.y = 12 * 50;   
	}

    public void setIndestructible(boolean b){
		if(b == true){
			indestructible = true;
			tiempoPower = 650 ;
			e = new Efectos(((int)this.getLocation().getX())+5,((int)this.getLocation().getY())+5,45,45,this);
			e.setComportamientoEspecial(true);
			e.setImagen(new File("Imagenes/bonusEscudo.gif"));
			e.configurar(1,52,51,650);
			contenedor.agregarListaEfectos(e);
		}
	}

	public boolean getIndestructible(){
		return indestructible;
	}

}
