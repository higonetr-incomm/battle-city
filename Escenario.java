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
import java.io.File;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileInputStream;

public class Escenario extends Objeto
{

	protected Vector<Objeto> objetos;
	protected Vector<Proyectil> proyectiles;
	protected Vector<Proyectil> proyectilesAUX;
	protected Vector<Objeto> removeList;
	protected Vector <Efectos> efectos;
	private int lugarAparicion;
	private int i;

	//////COSAS PARA EL MANEJO DEL NIVEL
	protected Vector <Objeto> tanquesACARGAR;
	protected Vector <Objeto> obstaculosAUX;
	protected Vector <String> tanquesEnemigos;
	private int cantTanquesACTUALES;
	static int nivelActual;
	static boolean pasarNivel;
	static Vector <String> niveles;
	private int tiempoEsperaCARGARNIVEL;
	protected int cantNiveles;
	/////////////////////////


	protected Vector <PowerUp> bonus;///////////////////////////////////////////
	protected Vector<Objeto> base; //////////////////////
	protected int cantEnemigos;
	protected int tiempoDetenido ;
	protected Vector<Objeto> obstaculos;

	private boolean pause=false;

    public Escenario(int x, int y, int width, int height, Vector<String> niveles)  {
       	super(x,y,width,height);

        	//img = Objeto.loadImage(VideoJuego.nivel().getConfig("imagenfondo")); esto es imagen por nivel
        	img = new javax.swing.ImageIcon("Imagenes/fondo escenario.gif").getImage();
        	//this.agregarObjeto(efectos);
        	objetos = new Vector<Objeto>();
        	efectos = new Vector<Efectos>();
        	proyectiles = new Vector<Proyectil>();
        	proyectilesAUX = new Vector<Proyectil>();
 			removeList = new Vector<Objeto>();
			
			///////////COSAS NIVEL//////////////////////
			tanquesACARGAR = new Vector<Objeto>();
			obstaculosAUX= new Vector<Objeto>();
			tanquesEnemigos= new Vector<String>();
			//cargarNivel(obstaculosAUX,tanquesEnemigos,"NivelUNO");
			cantTanquesACTUALES=0;
			nivelActual=0;
			pasarNivel=true;
			this.niveles = niveles;
			tiempoEsperaCARGARNIVEL=0;
			//cantNiveles=0;
			
			////////////////////////////////////////////
			
			bonus = new Vector<PowerUp>();
			obstaculos = new Vector<Objeto>();
			cantEnemigos = 20 ;
			tiempoDetenido = -1;
			base = new Vector<Objeto>();
			lugarAparicion = 0;
			i = 0;
			cantNiveles = niveles.size();
    }

    public void agregarEfectos(){
    	for(Efectos e : efectos)
    		agregarObjeto((Objeto)e);
    	efectos.removeAllElements();
    }
    
    public void cargarObstaculos(){
		for(Objeto o : obstaculosAUX)
    		agregarObjeto(o);
    	obstaculosAUX.removeAllElements();
	}
	
	public void cargarProximoNivel(){
		tanquesEnemigos.removeAllElements();
		cargarNivel(obstaculosAUX,tanquesEnemigos,niveles.get(nivelActual));
	}
	
	public void administrarNiveles(){
		if(nivelActual==cantNiveles){
			BattleCity.cargarPuntaje();
			VideoJuego.maquinaDeEstado=5;
		}
		
		if((pasarNivel)&&(tiempoEsperaCARGARNIVEL==0)){
			VideoJuego.maquinaDeEstado=2;
			cantEnemigos=20;
			//cargarNivel(obstaculosAUX,tanquesEnemigos,"NivelUNO");
			//limpiarEscenario();
			//cargarNivel(obstaculosAUX,tanquesEnemigos,niveles.get(nivelActual));
			//pasarNivel=false;
		}
		
		if(tiempoEsperaCARGARNIVEL>0){ //es el tiempo que espera cuando mata a todos los tanques, y tiene qeu cargar el proximo nivel
			tiempoEsperaCARGARNIVEL--;
		}
		
		if(pasarNivel)
			BattleCity.getSonido(9).play();	
		
		if((!pasarNivel)&&(VideoJuego.maquinaDeEstado==3)){
			administrarTanquesEnemigos();
		}
	}
	
	public void administrarTanquesEnemigos(){
		if((cantEnemigos==20)&&(cantTanquesACTUALES==0)){
			for(int j = 0 ; j < 4 ; j++ ){
				crearTanqueEnemigo(tanquesEnemigos.get(i));
				i++;
			}
			cantTanquesACTUALES=4;
		}
		//System.out.println(cantTanquesACTUALES);
		if((cantEnemigos==16)&&(cantTanquesACTUALES==0)){
			for(int j = 0 ; j < 5 ; j++ ){
				crearTanqueEnemigo(tanquesEnemigos.get(i));
				i++;
			}
			cantTanquesACTUALES=5;
		}
		if((cantEnemigos==11)&&(cantTanquesACTUALES==0)){
			for(int j = 0 ; j < 5 ; j++ ){
				crearTanqueEnemigo(tanquesEnemigos.get(i));
				i++;
			}
			cantTanquesACTUALES=5;
		}
		if((cantEnemigos==6)&&(cantTanquesACTUALES==0)){
			for(int j = 0 ; j < 6 ; j++ ){
				crearTanqueEnemigo(tanquesEnemigos.get(i));
				i++;
			}
			i = 0 ;
			cantTanquesACTUALES=6;
		}
		if(cantEnemigos<=0){
			pasarNivel=true;
			cantEnemigos--;
			nivelActual++;
			tiempoEsperaCARGARNIVEL=200;
			if ( nivelActual == cantNiveles ){
				//VideoJuego.maquinaDeEstado=5;
			}
		}
	}

    public void cargarNivel(Vector<Objeto> obs,Vector<String> te,String nivel){
		try{
		FileReader fr = new FileReader("Niveles/"+nivel+".txt");
		BufferedReader br = new BufferedReader(fr);
		String linea;
		int k=0;
		while((linea = br.readLine()) != null){
			System.out.println(linea);
			if((linea.substring(0,1)).equals("T")){
				for(int i=1;i<linea.length();i++){
					te.add(linea.substring(i,i+1));
				}
			}else{
				for(int i=0;i<linea.length();i++){
					switch(linea.substring(i,i+1)){
					case "1":{
						ParedDeLadrillo p1 = new ParedDeLadrillo(50*i,50*k);
						obs.add(p1);
					}
					break;
					case "2":{
						ParedDeMetal p2 = new ParedDeMetal(50*i,50*k);
						obs.add(p2);
					}
					break;
					case "3":{
						EstanqueDeAgua e1 = new EstanqueDeAgua(50*i,50*k);
						obs.add(e1);
					}
					break;
					case "4":{
						Arbusto a = new Arbusto(50*i,50*k);
						obs.add(a);
					}
					break;
					}
				}
			k=k+1;
			}
		}
		obs.add(new Base());
		fr.close();
		new Base();
		
		for(Objeto o : objetos){
			if(o instanceof TanqueJugador){
				System.out.println("Hola");
				o.setLocation(4*50,12*50);
			}
		}
		
		}catch(Exception e){
			System.out.println("Error en cargarNivel()");}
	}

		public void incorporarObstaculo(Objeto o){
			obstaculos.add(o);
		}

		public void agregarObstaculos(){
		for (Objeto o : obstaculos)
			agregarObjeto(o);
		obstaculos.removeAllElements();
	}

	public void agregarListaBonus(PowerUp p){
		bonus.add(p);
		p.asignarContenedor(this);
		System.out.println("Agrega a la lista bonus");
	}

    public void agregarBase(){
		for(Objeto b : base)
    		agregarObjeto(b);
    	base.removeAllElements();
	}

	public void reforzarBase(){
		Vector<Rectangle> r = new Vector<Rectangle>();
		r.add ( new Rectangle(5*50,12*50,50,50));
		r.add ( new Rectangle(5*50,11*50,50,50));
		r.add ( new Rectangle(6*50,11*50,50,50));
		r.add ( new Rectangle(7*50,11*50,50,50));
		r.add( new Rectangle(7*50,12*50,50,50));
		
		for( Objeto o : objetos )
			for (Rectangle obj : r )
				if( o.intersects(obj) ){
					if(o instanceof TanqueEnemigo && !(removeList.contains(o))){
						removeList.add(o);
						cantEnemigos--;
						cantTanquesACTUALES--;
						explosionChica(efectos,o);
						explosionGrande(efectos,o);
						BattleCity.getSonido(1).play();
					}
					if(o instanceof ParedDeLadrillo || o instanceof ParedDeMetal ){
						removeList.add(o);
					}
				}
		obstaculos.add ( new ParedDeMetal(5*50,12*50,500) );
		obstaculos.add ( new ParedDeMetal(5*50,11*50,500) );
		obstaculos.add ( new ParedDeMetal(6*50,11*50,500) );
		obstaculos.add ( new ParedDeMetal(7*50,11*50,500) );
		obstaculos.add ( new ParedDeMetal(7*50,12*50,500) );
	}

    public void crearTanqueEnemigo(String tipo){
		int ax ;
		int ay ;
		switch(lugarAparicion){
			case 0: ax = 10; ay = 0; lugarAparicion++;
			break;
			case 1: ax = 70; ay = 0; lugarAparicion++;
			break;
			case 2: ax = 130; ay = 0; lugarAparicion++;
			break;
			case 3: ax = 190; ay = 0; lugarAparicion++;
			break;
			case 4: ax = 330; ay = 0; lugarAparicion++;
			break;
			case 5: ax = 400; ay = 0; lugarAparicion++;
			break;
			case 6: ax = 470; ay = 0; lugarAparicion++;
			break;
			default: ax = 10; ay = 0; lugarAparicion=1;
			break;
			
		}
    	TanqueEnemigo te = new TanqueEnemigo(ax,ay,Integer.parseInt(tipo) );
		agregarObjeto(te);

		Efectos e = new Efectos((int)te.getLocation().getX()+12,(int)te.getLocation().getY()+12,45,45);
		e.setImagen(new File("Imagenes/aparicion enemigo (49x49).gif"));
		e.configurar(5,49,49,50);
		efectos.add(e);
    }

    public void actualizar(){
		if(!pause){
			
			administrarNiveles();
				
			if(BattleCity.getJugador().getVidas()<0){
				System.out.println("FIN DEL JUEGO");
				BattleCity.getSonido(3).play();	
				BattleCity.cargarPuntaje();
				VideoJuego.maquinaDeEstado=4;
			}
		
			for (Objeto obj : objetos)
				obj.actualizar();
			for (Proyectil p : proyectiles)
				p.actualizar();
			for (Efectos e : efectos)
				e.actualizar();
			for (PowerUp p : bonus )
				p.actualizar();
			for (Objeto b : base)
				b.actualizar();
			if(tiempoDetenido > 0){
				tiempoDetenido--;
				if(tiempoDetenido == 0 )
					descongelarEnemigos();
			}
		}
	}

	public void setPause(){
		if(pause)
			pause=false;
		else
			pause=true;
	}

	public void explosionChica(Vector<Efectos> efectos,Objeto p){
		Efectos e = new Efectos((int)p.getLocation().getX(),(int)p.getLocation().getY(),20,20);
		e.setImagen(new File("Imagenes/explosion chica (51x51).gif"));
		e.configurar(3,51,51,25);
		efectos.add(e);
	}

	public void explosionGrande(Vector<Efectos> efectos, Objeto t){
		Efectos e = new Efectos((int)t.getLocation().getX(),(int)t.getLocation().getY(),30,30);
		e.setImagen(new File("Imagenes/explosion grande (107x104).gif"));
		e.configurar(2,107,104,25);
		efectos.add(e);
	}

		public void detectarColisiones(){
		
		for (Proyectil p : proyectiles){
			for(Objeto obj : objetos){
				//si un proyectil toca una pared de ladrillo
				if((obj instanceof ParedDeLadrillo)&&(p.intersects(obj))){
					obj.colisiono();
					explosionChica(efectos,p);				
					sacarProyectil(p);
				}
				//si un proyectil toca una pared de metal
				if((obj instanceof ParedDeMetal)&&(p.intersects(obj))){
					if( p.tanqueAsociado() instanceof TanqueJugador )
						if(  ((TanqueJugador)p.tanqueAsociado()).getEstrellas() >= 3 ){
							obj.colisiono();
							BattleCity.getSonido(8).play();	
						}
					explosionChica(efectos,p);
					sacarProyectil(p);
				}
				//si un proyectil toca un arbusto
				//if((obj instanceof Arbusto)&&(p.intersects(obj))){
				//	obj.colisiono();
					//sacarProyectil(p);
				//}
				//si un proyectil toca un tanque enemigo
				if((obj instanceof TanqueEnemigo)&&(p.disparadoPorJugador())&&(p.intersects(obj))){
						BattleCity.getJugador().sumarPuntos(((TanqueEnemigo)obj).getPuntaje());
						((TanqueEnemigo)obj).quitarEnergia( ((TanqueJugador)p.tanqueAsociado()).getEstrellas() );
						if( ((TanqueEnemigo)obj).estaMuerto() ){
							cantEnemigos = cantEnemigos - 1 ;
							cantTanquesACTUALES--;
						}
						explosionChica(efectos,obj);
						explosionGrande(efectos,obj);
						BattleCity.getSonido(4).play();
						sacarProyectil(p);
						if( cantEnemigos == 0 ){
							System.out.println("Gano el juego!!!!");
						}
				}
				//si un proyectil toca un tanque jugador
				if((obj instanceof TanqueJugador)&&!(p.disparadoPorJugador())&&(p.intersects(obj))){
					explosionChica(efectos,p);
					sacarProyectil(p);
					if( !(((TanqueJugador)obj).getIndestructible())){	
						obj.colisiono();
						restartTanqueJugador();
						explosionGrande(efectos,obj);
						BattleCity.getSonido(4).play();
					}else{
						System.out.println("Soy indestructible");
					}
						
				}
				//si un proyectil toca la base
				if((obj instanceof Base)&&(p.intersects(obj))){
					obj.colisiono();
					explosionChica(efectos,p);
					sacarProyectil(p);
				}
			}
			//si chocan dos proyectiles
			for(Proyectil p2 : proyectiles){
				if((p!=p2)&&(p.intersects(p2))){
					p.colisiono();
					p2.colisiono();
					explosionChica(efectos,p);
				}
			}
			
		}
		
		for(Objeto obj : objetos)
			if( obj instanceof TanqueJugador)
				for(PowerUp p : bonus )
					if((obj.intersects(p))){
						((TanqueJugador)obj).obtenerBonus(p);
						BattleCity.getJugador().sumarPuntos(p.getPuntos());
						p.colisiono();
					}
		
						
				
					
	}

    	public void sacarProyectil(Proyectil p){
    		//proyectiles.remove(p);
    		sacarObjeto(p);
    		p.tanqueAsociado().hayProyectil=false;
    		//p.collides();
    	}

    	public void sacarEfecto(Efectos e){
    		//sacarObjeto(e);
    		removeList.add(e);
    	}

			public void sacarBonus(PowerUp p ){
			removeList.add(p);
		}

    public void agregarObjeto(Objeto o){
		objetos.add(o);
		o.asignarContenedor(this);
	}

	/*public void agregarEfecto(Efectos e){
		efectos.add(e);
		e.asignarContenedor(this);
	}*/

	public void agregarProyectil(Proyectil p){
		//proyectiles.add(p);
		//p.asignarContenedor(this);
		proyectilesAUX.add(p);
		p.asignarContenedor(this);
	}
	
	public void cargarProyectiles(){
		for(Proyectil p : proyectilesAUX){
			proyectiles.add(p);
			//p.asignarContenedor(this);
		}
		proyectilesAUX.removeAllElements();
	}

    public void sacarObjeto(Objeto o){
		//System.out.println("sale objeto de tipo "+o.getClass().getName());
		removeList.add(o);
	}


	public void limpiarObjetosBorrados(){
        	for (Objeto o : removeList){
        		if(o instanceof Proyectil){
        			proyectiles.remove(o);
        		}
        		else{
					if( o instanceof PowerUp){
						bonus.remove(o);
					}else{					
						objetos.remove(o);
					}
        		}
        		
        	}
        removeList.clear();
    }

     public void colisiono(){
    }



    //Determina si un objeto dado, contenido en este objeto vacío, intersecta los bordes internos del mismo
    //devuelve ObjetoMovile.REB_HORIZONTAL si intersecta con un borde horizontal o ObjetoMovible.REB_VERTICAL si intersecta
    //con un borde vertical
    /*public int intersectsInside(Objeto rec){
		int retValue=-1;
		if (rec.getX()+rec.getWidth() >= (this.getWidth()-5) || rec.getX()<=(this.getX()+5))
            retValue = ObjetoMovible.REB_VERTICAL;
        else
            if (rec.getY()+rec.getHeight() >=(this.getWidth()-5) || rec.getY()<=(this.getY()+5))
                retValue = ObjetoMovible.REB_HORIZONTAL;
         return retValue;
	}*/


	public void paint(Graphics2D g){
		g.drawImage(img,x,y,width,height,null);
		for (Objeto obj : objetos){
			obj.paint(g);
		}
		for (Proyectil p : proyectiles)
			p.paint(g);
		//esto me sirvio para los arbustos
		for (Objeto obj : objetos){
			if(obj instanceof Arbusto)
				obj.paint(g);
		}
		for(Efectos e : efectos){
			e.paint(g);
		}

		for (PowerUp p : bonus)
			p.paint(g);
	}



	public Vector<Objeto> objetos(){return objetos;}

	public int getCantidadEnemigos(){
		//System.out.println(cantEnemigos);
		return cantEnemigos;
	}

	public void matarEnemigos(){
		for( Objeto obj : objetos)
			if( obj instanceof TanqueEnemigo){
				removeList.add(obj);
				cantEnemigos--;
				cantTanquesACTUALES--;
				explosionChica(efectos,obj);
				explosionGrande(efectos,obj);
				BattleCity.getSonido(4).play();
			}
	}

	public void congelarEnemigos(){
		for( Objeto obj : objetos)
			if( obj instanceof TanqueEnemigo){
				((TanqueEnemigo)obj).setCongelado(true);
			}
		tiempoDetenido = 300 ;
	}

	public void descongelarEnemigos(){
		for( Objeto obj : objetos){
			if( obj instanceof TanqueEnemigo){
				((TanqueEnemigo)obj).setCongelado(false);
			}
		}
		tiempoDetenido = -1;
	}
	
	public void limpiarEscenario(){
		for( Objeto o : objetos )	
			if ( !(o instanceof TanqueJugador) )
				sacarObjeto(o);
        for( Efectos e : efectos ){
			sacarEfecto(e);
		}
        for( Proyectil p: proyectiles )
                sacarProyectil(p);
        
        /////////////////RODRI////////// PARA QUE CARGUE LOS TANQUES SI PERDIO, SI PASA DE NIVEL ANDUVO, HAY QUE SEGUIR PROBANDO PARA ENCONTRARLE ALGUN ERROR SI HAY
        cantEnemigos = 20; ////////////////////////////////RODRI
        tanquesEnemigos.removeAllElements(); ////////////////////////////////RODRI
        cantTanquesACTUALES = 0; ///////////////////RODRI
        i = 0; ////////////////////RODRI
      	
	}
	public void agregarListaEfectos(Efectos e){
		efectos.add(e);
	}
	
	public void restartTanqueJugador(){
		for( Objeto o : objetos ){
			if( o instanceof TanqueJugador && ((TanqueJugador)o).isMuerto() ){
				((TanqueJugador)o).resetear();
			}
		}
	}

}
