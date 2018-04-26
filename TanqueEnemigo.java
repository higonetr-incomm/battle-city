import java.awt.*;
import javax.swing.*;
import java.util.*;


public class TanqueEnemigo extends Tanque {
	private static final int MUEVECADA=20;
	private int movimientoAleatorio=MUEVECADA;
	private int random;
	private boolean tieneBonus;
	private int energia ;
	private boolean congelado, muerto;
	private int puntajeQueOtorga;

	private int efectoCOMIENZO;

	public TanqueEnemigo(int x, int y, int tipo){
		super(x,y,36,36);
		switch(tipo){
			case 1:
				step = 2;
				energia = 1;
				puntajeQueOtorga = 100;
			break;
			case 2:
				step = 3;
				energia = 1;
				puntajeQueOtorga = 200;
			break;
			case 3:
				step = 1;
				energia = 3;
				puntajeQueOtorga = 400;
			break;
			case 4:
				step = 1;
				energia = 2;
				puntajeQueOtorga = 300;
			break;
		}

		sortearBonus();
		//tieneBonus = true;

		imagen = new Vector<Image>() ;
        	imagen.add (new javax.swing.ImageIcon("Imagenes/tanque enemigo/"+tipo+"/Tank arriba.gif").getImage() );
        	imagen.add (new javax.swing.ImageIcon("Imagenes/tanque enemigo/"+tipo+"/Tank abajo.gif").getImage() );
        	imagen.add (new javax.swing.ImageIcon("Imagenes/tanque enemigo/"+tipo+"/Tank derecha.gif").getImage() );
        	imagen.add (new javax.swing.ImageIcon("Imagenes/tanque enemigo/"+tipo+"/Tank izquierda.gif").getImage() );

		/*
		try{
			img = ImageIO.read(new File("Imagenes/Tank abajo.png"));
		} catch ()
		*/
		
			efectoCOMIENZO=30;

        	img = new javax.swing.ImageIcon("Imagenes/tanque enemigo/"+tipo+"/Tank abajo.gif").getImage();
        	direction=DIR_NONE;
        	ultimaDireccion=DIR_DOWN;
        	hayProyectil=false;
        	congelado = false;
        	muerto = false;
	}

	 public void colisiono(){
		if(tieneBonus){
			generarBonus();
		}
	 	contenedor.sacarObjeto(this);
	 	muerto = true;
	 	
    }
    
    public int getPuntaje(){
		return puntajeQueOtorga;
	}
    
    public boolean estaMuerto(){
		return muerto ;
	}

	/*public void dispararProyectil(){
		if(hayProyectil==false){
			Proyectil p = new Proyectil((int)this.getLocation().getX()+12,(int)this.getLocation().getY()+12,this.ultimaDireccion);
			this.contenedor.agregarObjeto(p);
			hayProyectil = true;
		}
	}*/

	public void mover(){
		if((!congelado)/*&&(efectoCOMIENZO==0)*/){
		super.mover();
		if(movimientoAleatorio==0){
			random = (int) (Math.random()*4+1);
			switch(random){
				case 1:{
					direction=DIR_UP;
					//img = new javax.swing.ImageIcon("Imagenes/Tank arriba.png").getImage();
					ultimaDireccion=DIR_UP;
					dispararProyectil(this);
					//p = new Proyectil(this);
				}
				break;
				case 2:{
					direction=DIR_DOWN;
					ultimaDireccion=DIR_DOWN;
					//img = new javax.swing.ImageIcon("Imagenes/Tank abajo.png").getImage();
					dispararProyectil(this);
				}
				break;
				case 3:{
					direction=DIR_RIGHT;
					ultimaDireccion=DIR_RIGHT;
					//img = new javax.swing.ImageIcon("Imagenes/Tank derecha.png").getImage();
					dispararProyectil(this);
					}
				break;
				case 4:{
					direction=DIR_LEFT;
					ultimaDireccion=DIR_LEFT;
					//img = new javax.swing.ImageIcon("Imagenes/Tank izquierda.png").getImage();
					dispararProyectil(this);
				}
				break;
			}
			movimientoAleatorio = MUEVECADA;
		} else {
			//resetMovAleatorio();
		}
	}
	}

	public void actualizar(){
		
		if(efectoCOMIENZO>0){
			efectoCOMIENZO--;
		}
		
		if(!congelado){
		mover();
		movimientoAleatorio=movimientoAleatorio-1;
		if(direction==DIR_UP)
			img=imagen.get(0);
		if(direction==DIR_DOWN)
			img=imagen.get(1);
		if(direction==DIR_RIGHT)
			img=imagen.get(2);
		if(direction==DIR_LEFT)
			img=imagen.get(3);
	}
}

	/*
	public void collides(){
		//System.out.println("colision!");
		direction = DIR_NONE;
	}*/

	public void paint(Graphics2D g){
        //g.setColor(java.awt.Color.BLACK);
        //g.fillRoundRect(x+5,y+5,width,height,10,10);
        g.drawImage(img,x,y,width,height,null);
    }

		public void generarBonus(){
		int dx = (int) (Math.random()*500+1);
		int dy = (int) (Math.random()*300+1);
		int bonus = (int) (Math.random()*6+1);
		//int bonus = 2; //R no queda bien el escudo, hace el juego medio lento y no puedo hacer que se salga el escudo cuando termine el tiempoPower
		System.out.println("GenerarBonus: x " + dx + " y " + dy );

		if(bonus == 1)
			contenedor.agregarListaBonus( new Escudo(dx,dy) );
		if(bonus == 2)
			contenedor.agregarListaBonus( new Estrella(dx,dy) );
		if(bonus == 3)
			contenedor.agregarListaBonus( new Reloj(dx,dy) );
		if(bonus == 4)
			contenedor.agregarListaBonus( new TanqueBonus(dx,dy) );
		if(bonus == 5)
			contenedor.agregarListaBonus( new Bomba(dx,dy) );
		if(bonus == 6)
			contenedor.agregarListaBonus( new Pala(dx,dy) );


	}


	public void sortearBonus(){
		tieneBonus = false;
		int num = (int) (Math.random()*4+1);
		if( num == 3 )
			tieneBonus = true;
	}

	public void quitarEnergia(int i){
		energia = energia - i;
		System.out.println("Cantidad de energia restante " + energia );
		if (energia <= 0 )
			colisiono();
	}

	public void setCongelado(boolean b){
		congelado = b;
	}

}
