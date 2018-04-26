import java.util.Vector;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Properties;
import java.io.*;

public class Control implements KeyListener {
	
	private String[] controles;
	Properties p = new Properties();
	
	public static final int ARRIBA = 0;
	public static final int ABAJO = 1;
	public static final int IZQUIERDA = 2;
	public static final int DERECHA = 3;
	public static final int TIRO = 4;
	public static final int PAUSA = 5;
	
	private Vector<Controlable> controlables; 
	/*en este caso va a ser uno solo, el tanque del jugador*/
	
	///////////////////cargo control desde archivo////////////////////
	public Control(){
		
		controlables = new Vector<Controlable>();
		controles = new String[7];
	
		
		try{
			 FileInputStream in=null;
			 in = new FileInputStream("Config-Usuario.txt");
			 p.load(in);
			 controles[ARRIBA] = p.getProperty("ARRIBA");
			 controles[ABAJO] = p.getProperty("ABAJO");
			 controles[IZQUIERDA] = p.getProperty("IZQUIERDA");
			 controles[DERECHA] = p.getProperty("DERECHA");
		   	 controles[TIRO] = p.getProperty("TIRO");
		   	 controles[PAUSA] = p.getProperty("PAUSA");
		   	 in.close();
		}catch(Exception e){
			System.out.println("FALLo control()");
			controles[ARRIBA] = "Arriba";
			controles[ABAJO] = "Abajo";
			controles[IZQUIERDA] = "Izquierda";
			controles[DERECHA] = "Derecha";
			controles[TIRO] = "Espacio";
			controles[PAUSA] = p.getProperty("P");
			
		}		
	}
	
/*	public Control(int arriba, int abajo, int derecha, int izquierda, int accion){
		
		controlables = new Vector<Controlable>();
		controles = new int[7];
		
		controles[ARRIBA] = arriba;
		controles[ABAJO] = abajo;
		controles[IZQUIERDA] = izquierda;
		controles[DERECHA] = derecha;
		controles[ACCION_A] = accion;
	}*/
	
	public String getControl(int control){
		return controles[control];
	}
	
	public void addControlable(Controlable c){
		controlables.add(c);
	}

    public void keyPressed(KeyEvent e){ 
    	/*por eso tiene la responsabilidad de los eventos de la pantalla*/
    	
		for (Controlable cont : controlables)
			cont.procesarControl(e.getKeyText(e.getExtendedKeyCode()),this);
			
    }
    
    public void keyReleased(KeyEvent e){
    	
		for (Controlable cont : controlables)
			cont.controlTerminado();
		
		/*if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
    		{	
        		System.exit(0);
    		}*/	
    } 
    
    public void keyTyped(KeyEvent e) {}

}
