import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.image.BufferStrategy;
import java.util.Vector;


public abstract class VideoJuego implements Runnable{

	protected Thread t;
	protected boolean running;
	protected static Pantalla pantalla;
	protected static Escenario escenario;
	protected boolean estaEnElMenu=true;
	protected MenuEstado estadoJuego ;
	protected static Jugador jugador;
	protected static DJmanager dj ;
	public Ranking ranking;
	
	///////COSAS NIVEL
	protected static MenuInicio menu;
	//protected static MenuSeleccionarTanque menuTanque;
	static int maquinaDeEstado=1;
	int tiempoPasarNivel=300;
	protected static PantallaPasarNivel pantallaPasarNivel;
	protected static PantallaGameOver pantallaGameOver;
	protected static PantallaGanoElJuego pantallaGanoElJuego;
	//////////////////////

    public VideoJuego(){}

     public void run(){
		/*loop*/
		dj.getSonido(12).play();
		while (running) {
            pantalla.initGraphics(); //Crea el objeto graphics2d para graph
            pantalla.paintBackground(); //Pinta el graph
            
            ////MENU////
            pantallaPasarNivel.actualizar(); //lo hago aca porque tengo que ver si cargo la barra o no
            pantallaGameOver.actualizar();
            pantallaGanoElJuego.actualizar();
            
            if(maquinaDeEstado==1){ //esta en el menu
				pantalla.paintObject(menu);
             	menu.actualizar();
             	//System.out.println(menu.gestorMovimientoMenu);
             	//System.out.println("MENU");
            }
            //if(maquinaDeEstado==12){
				//System.out.println("ESTOY ACA");
				//pantalla.addKeyListener(menuTanque);
				//pantalla.paintObject(menuTanque);
             	//menuTanque.actualizar();
			//}
			if(maquinaDeEstado==2){
				if(tiempoPasarNivel>0){
					pantalla.paintObject(pantallaPasarNivel);
					//pantallaPasarNivel.actualizar();
					tiempoPasarNivel--;
					if(tiempoPasarNivel==0){
						maquinaDeEstado=3;
					}
				}
				if(Escenario.pasarNivel){
					tiempoPasarNivel=300;
					System.out.println("ESTOY ACA CARAJO");
					escenario.limpiarEscenario();
					escenario.cargarProximoNivel();
					Escenario.pasarNivel=false;
				}
				
			}
			if(maquinaDeEstado==4){
				pantalla.paintObject(pantallaGameOver);
				if(pantallaGameOver.Cargo()){
					escenario.nivelActual=0;
					BattleCity.getJugador().vidaJugador=3;
					//escenario.cantEnemigos=0;
					//FALTA ALGO PARA Q LOS TANQUES VUELVAN A APARECER
					escenario.limpiarEscenario();
					escenario.cargarProximoNivel();
					maquinaDeEstado=1;
				}	
			}
			if(maquinaDeEstado==5){
				pantalla.paintObject(pantallaGanoElJuego);
				if(pantallaGanoElJuego.Cargo()){
					escenario.nivelActual=0;
					//escenario.cantEnemigos=0;
					//FALTA ALGO PARA Q LOS TANQUES VUELVAN A APARECER
					escenario.limpiarEscenario();
					escenario.cargarProximoNivel();
					maquinaDeEstado=1;
				}
			}

            if(maquinaDeEstado==3){
            /*
            if(maquinaDeEstado==2) // q haga el resto
            */
            try{
				pantalla.paintObject(escenario);
			}
			catch (Exception e) {  }
			 //pinta el escenario con el graph con el metodo escenario.paint que escenario es objeto
			pantalla.paintObject(estadoJuego); //pinta el escenario con el graph con el metodo escenario.paint que escenario es objeto
			/*actualiza el escenario y todos los objetos que contiene*/
			escenario.actualizar();
			/*limpiar los objetos que han salido de la pantalla*
			/*detectar colisiones*/
			//detectarColision();
			/*mostrar pantalla*/
			escenario.cargarObstaculos();
			escenario.cargarProyectiles();
			escenario.agregarEfectos();
			//escenario.agregarBase();
			escenario.agregarObstaculos();
			
			escenario.detectarColisiones();
			escenario.limpiarObjetosBorrados();
			//escenario.cargarNivel();
			}
			
			pantalla.mostrar(); //esto hace graph.dispose(); strategy.show();

			try{
				t.sleep(6);
			}
			catch (Exception e) { e.printStackTrace(); }
		}
    }

	public void play(){
		pantalla.init(); 			//inicia la pantalla, le pide focus y inicia el buffer
		t = new Thread(this);	 //hace nuevo treahd de videojuego
		running = true;
		t.start();
	}

    public void stop(){
        running =false;
    }

    public Pantalla getPantalla(){
    	return pantalla;
    }

	//public abstract void detectarColision();

}
