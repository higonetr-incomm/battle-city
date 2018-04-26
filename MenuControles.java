import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.io.FileInputStream;


public class MenuControles extends JPanel implements ActionListener, KeyListener {
	private boolean flag = true; 
	private JPanel p_centro;
	private JButton defecto,a;
	private JLabel etiqueta;
	private JButton[] botones;
	private String[] acciones = {"ARRIBA", "ABAJO", "IZQUIERDA", "DERECHA", "TIRO", "PAUSA"};
//	String controles[] = {"Arriba", "Abajo", "Izquierda", "Derecha", "Q", "P"};
	private Properties prop;
	


    public MenuControles(Properties prop) {
    	botones = new JButton[6];
    	this.prop = prop;
    	
    	/////////boton reestablecer/////////////
    	defecto = new JButton("Reestablecer valores por defecto");
    	defecto.addActionListener(this);
    	
    	////////////panel central///////////////
    	JPanel p_ajuste = new JPanel(new BorderLayout());
    	p_centro = new JPanel();
    	p_centro.setLayout(new GridLayout(6,2));
    	
    	for(int i=0 ;i<6 ;i++){
    		etiqueta = new JLabel(acciones[i] );
    		p_centro.add(etiqueta);
    		botones[i] = new JButton(" ");
    		botones[i].getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none"); // para leer el espacio
    		p_centro.add(botones[i]);
    		botones[i].addActionListener(this);
    		botones[i].addKeyListener(this);
    		
    	}
    	
    	//this.readConfig("Config-Usuario.txt");
    	
    	//cargar configuracion en interfaz
    	for (int i=0;i<botones.length;i++)
    			botones[i].setLabel(prop.getProperty(acciones[i]));
    	
    	/////////////this panel//////////////
    	p_ajuste.add(p_centro,BorderLayout.CENTER);
    	this.setLayout(new BorderLayout());
    	this.add(BorderLayout.CENTER,p_ajuste);
    	this.add(BorderLayout.SOUTH,defecto);
    }
    
    
    public  void actionPerformed(ActionEvent e){
    	if(flag){
   	   		a=(JButton)e.getSource();
   	   		if(a!=defecto)
   	   		{
   	   		a.setLabel("Presione una tecla");
   			flag=false;
   	   		}
   	   	    else
   	   	    {
   	   	      //volver a cargar los botones por defecto
   	   	      this.setDefault();
   	   	      }
   	   	    }
    }
    
    public void keyReleased(KeyEvent e){
    	Boolean ver=true;
   		if(!flag){
   			for(int i=0;i<botones.length && ver;i++){
   			   if(botones[i].getLabel().equals(e.getKeyText(e.getExtendedKeyCode())))
   		    		ver=false;
   			}
   	  	   if(ver){
   				a.setLabel(e.getKeyText(e.getExtendedKeyCode()));
   				flag=true;
   	   		 }
   		}
    }
    
    public void keyPressed(KeyEvent e){
    }
    
    public void keyTyped(KeyEvent e){
    }
   
    
    
    public void updateConfig(){
    	
    	try{
    		FileOutputStream out = new FileOutputStream("Config-Usuario.txt");
    		  		
    		//modifico controles en prop
    		for (int i=0;i<botones.length;i++)
    			prop.setProperty(acciones[i],botones[i].getLabel());
    			
    		//prop.list(System.out);
    		//guardo prop en archivo
    		prop.store(out,"By: Jugador");
    		out.close();

			
    	}catch(Exception e){
    		System.out.println("Error al guardar controles en archivo Config-Usuario.txt");
    	}
    }
    
     public void setDefault(){
		FileInputStream in=null;

		try{
			 in = new FileInputStream("Config-Default.txt");
			 prop.load(in);
			 for(int i=0;i<botones.length;i++)
			 		botones[i].setLabel(prop.getProperty(acciones[i]));
		}catch(Exception e){
			System.out.println("FALLÓ MCONFIG.SETDEFAULT");
		}
	
	}
    
}
