import java.awt.*;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Frame;
import java.awt.Color;
import java.awt.event.*;
import java.io.RandomAccessFile;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.io.FileInputStream;

public class BattleCity extends VideoJuego{
	Toolkit t = Toolkit.getDefaultToolkit();
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int ANCHO_JUEGO = 750;
	public static int ALTO_JUEGO = 650;
	public static int ANCHO_FULLSCREEN = screenSize.width;
	public static int ALTO_FULLSCREEN = screenSize.height;
	
	public static int X_ESCENARIO = 0;
	public static int Y_ESCENARIO = 0;
	public static double ESCALA_X = ((double)ANCHO_FULLSCREEN)/((double)ANCHO_JUEGO);
	
	//public static double ESCALA_X = ANCHO_FULLSCREEN;
	public static double ESCALA_Y = ((double)ALTO_FULLSCREEN)/((double)ALTO_JUEGO);
	//public static double ESCALA_Y = 1.65;
	//public static double ESCALA_X = 2.56;
	//static boolean fullscreen = false;
	
	static CardLayout cl = new CardLayout();
	static JFrame f = new JFrame ("Battle City");
	static JPanel panel = new JPanel();
	protected static Ranking ranking;
	
	/////configuraciones
	private String colorTanque;
	private Vector <String> niveles;
	static boolean sonido = true;
	private String pista;
	static boolean fullscreen = false;

	public BattleCity(){
		System.out.println(ESCALA_X);
		System.out.println(ESCALA_Y);
		jugador = new JugadordeBattleCity("");
		cargarConfig();
		if (fullscreen)
			pantallaCompleta();
		else pantalla = new Pantalla(ANCHO_JUEGO,ALTO_JUEGO,Color.BLACK);
		escenario = new Escenario(X_ESCENARIO,Y_ESCENARIO,ANCHO_JUEGO-100,ALTO_JUEGO, niveles);
		estadoJuego = new MenuEstado(X_ESCENARIO+ANCHO_JUEGO-100,Y_ESCENARIO,100,ALTO_JUEGO);
		
		dj = new DJmanager(13);
		iniciarSonido();
		
		menu= new MenuInicio(X_ESCENARIO,Y_ESCENARIO,ANCHO_JUEGO,ALTO_JUEGO);
		//menuTanque = new MenuSeleccionarTanque(X_ESCENARIO,Y_ESCENARIO,ANCHO_JUEGO,ALTO_JUEGO);
		pantallaPasarNivel = new PantallaPasarNivel(X_ESCENARIO,Y_ESCENARIO,ANCHO_JUEGO,ALTO_JUEGO);
		pantallaGameOver = new PantallaGameOver(X_ESCENARIO,Y_ESCENARIO,ANCHO_JUEGO,ALTO_JUEGO);
		pantallaGanoElJuego = new PantallaGanoElJuego(X_ESCENARIO,Y_ESCENARIO,ANCHO_JUEGO,ALTO_JUEGO);
		ranking = new Ranking("Ranking Battle City");

		pantalla.addKeyListener(menu);

		Vector<Objeto> obstaculos = new Vector<Objeto>(); 		
		
		Control control = new Control();
		TanqueJugador n = new TanqueJugador(colorTanque);
		control.addControlable(n);
	
		/*control.addControlable(menu) y en menu hacer lo mismo que tanquejugador con procesador controles
		 * tener un metodo estatico que me devuelva el estado en el que estoy, y menu solo procesa controles si esta en
		 * el estado en el que tiene que estar
		 * */
	
		//TanqueEnemigo te = new TanqueEnemigo(100,0);
		//TanqueEnemigo te2 = new TanqueEnemigo(200,0);
		//TanqueEnemigo te3 = new TanqueEnemigo(300,0);
		//TanqueEnemigo te4 = new TanqueEnemigo(400,0);
		
		pantalla.addKeyListener(control); //control recibe los eventos de teclado que ocurran en la pantalla
		//le puedo agregar los keyListener que quiera a pantalla
		
		//escenario.crearTanqueEnemigo();
		//escenario.crearTanqueEnemigo();
		//escenario.crearTanqueEnemigo();
		//escenario.crearTanqueEnemigo();
		
		escenario.agregarObjeto(n);
		//cargarNivel(escenario,obstaculos,"NivelUNO");
		//nivelUNO(escenario,obstaculos);
		
		//for(Objeto x : obstaculos)
		//	escenario.agregarObjeto(x);
		
		//escenario.agregarObjeto(n);
		//escenario.agregarObjeto(te);
		//escenario.agregarObjeto(te2);
		//escenario.agregarObjeto(te3);
		//escenario.agregarObjeto(te4);
		
	}
	

	public static void main(String [] strs){
		BattleCity.nuevoBattleClity();
	}

	public static void nuevoBattleClity(){
		BattleCity prueba = new BattleCity();
		//JFrame f = new JFrame ("Battle City");
		f.setVisible(true);
		f.setResizable(false);
		f.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

		///////////////////////////////////////////
		////////////////MENU///////////////////////
		//CardLayout cl = new CardLayout();
		//f.setLayout(cl);
		//MenuInicio mi = new MenuInicio();
		//f.add(mi,BorderLayout.CENTER);
		f.add(prueba.getPantalla(),BorderLayout.CENTER);
		//JPanel panel = new JPanel();
		//panel.setLayout(cl);
		//panel.add(prueba.getPantalla(),"Juego");
		//panel.add(mi,"Menu");
		//mi.addKeyListener(mi);
		//f.add(panel);

		//cl.show(panel,"Menu");
		//cl.show(panel,"Juego");

		//cl.show(f,"Juego");
		//f.add(prueba.getPantalla(),"BattleCity");
		//f.add(mi,"Menu");

		//f.setLocationRelativeTo(null);
		
		prueba.play();
		f.pack();
		//f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public static JugadordeBattleCity getJugador(){
		return (JugadordeBattleCity)jugador ;
	}

	public static Escenario getEscenario(){
		return escenario;
	}

	public void iniciarSonido(){

	dj.cargarSonido (0,"Sonidos/sonidos/atrapasbonus.wav",0);
	dj.cargarSonido (1,"Sonidos/sonidos/bomba.wav",0);
	dj.cargarSonido (2,"Sonidos/sonidos/bonuspala.wav",0);
	dj.cargarSonido (3,"Sonidos/sonidos/derrota.wav",0);
	dj.cargarSonido (4,"Sonidos/sonidos/destruyetanque.wav",0);
	dj.cargarSonido (5,"Sonidos/sonidos/disparo.wav",0);
	dj.cargarSonido (6,"Sonidos/sonidos/disparoJUGADOR.wav",0);
	dj.cargarSonido (7,"Sonidos/sonidos/invensible.wav",0);
	dj.cargarSonido (8,"Sonidos/sonidos/pareddemetal.wav",0);
	dj.cargarSonido (9,"Sonidos/sonidos/pasasnivel.wav",0);
	dj.cargarSonido (10,"Sonidos/sonidos/pausa.wav",0);
	dj.cargarSonido (11,"Sonidos/sonidos/sonidosMENU.wav",0);
	dj.cargarSonido (12,"Sonidos/"+pista+".wav",10);
	

	}
	public static Sonido getSonido(int i){
		return (dj.getSonido(i));
	}

	public void pantallaCompleta(){
			f.setSize(ANCHO_FULLSCREEN,ALTO_FULLSCREEN);
			f.setUndecorated(true);
			System.out.println("Fullscreen"+" "+ANCHO_FULLSCREEN+"X"+ALTO_FULLSCREEN);
			
			//X_ESCENARIO = (ANCHO_FULLSCREEN-ANCHO_JUEGO)/2;
			//Y_ESCENARIO = (ALTO_FULLSCREEN-ALTO_JUEGO)/2;
		
			//ANCHO_JUEGO=ANCHO_FULLSCREEN;
			//ALTO_JUEGO=ALTO_FULLSCREEN;
			pantalla = new Pantalla(ANCHO_FULLSCREEN,ALTO_FULLSCREEN,Color.BLACK);
			}
	
	public static void cargarPuntaje(){
		ranking.agregarPuntaje(((JugadordeBattleCity)jugador).getPuntos(),jugador.getNombre());
	}
			
	public void cargarConfig(){
		FileInputStream in=null;
		try{
			/////config grales
			in = new FileInputStream("Config-Usuario.txt");
			Properties prop = new Properties();
			prop.load(in);
			jugador.setNombre(prop.getProperty("jugador"));
			colorTanque = prop.getProperty("TANQUE");
			if (prop.getProperty("Sonido").equals("true"))
				sonido = true;
			else sonido = false;
			pista = prop.getProperty("Pista");
			
			System.out.println(colorTanque+" "+sonido+" "+pista);
			///niveles
			niveles = new Vector<String> ();
			if (prop.getProperty("Pantalla").equals("Pantalla Completa"))
				fullscreen = true;
			FileReader fr = new FileReader("Niveles/NivelesElegidos.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea;
			while((linea = br.readLine())!= null){
				niveles.add(linea);
				System.out.println(linea);}
			fr.close();
			
				
		}catch(Exception e){
			System.out.println("Error en cargarConfig()");}
	
		
		}

}

