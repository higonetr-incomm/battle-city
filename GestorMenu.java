import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

//////////falta agregar cambio apariencia tanque/////////////////////

public class GestorMenu extends JPanel implements ActionListener {
	//barra de menus
	private JMenuBar menuBar;
	//items de la barra de menus
	private JMenuItem iControles, iPantalla, iSonido, iTanque;
	private Properties prop;
	

	private JPanel panel;
	private CardLayout cl;
	////////////////////////////////
	private JButton guardar,salir;
	private MenuControles mControles;
	private MenuPantalla mPantalla;
	private MenuSonido mSonido;	
	private MenuTanque mTanque;
	
	
    public GestorMenu() {
    	prop = new Properties();
    	//setSize(500,300);
    	//setTitle("Settings");
    	
    	///////////////MenuBar////////////////
    	menuBar = new JMenuBar();
    	//setJMenuBar(menuBar);
    	
    	/////////////boton guardar y salir ////////////////
    	guardar = new JButton("Guardar Cambios");
    	guardar.addActionListener(this);
    	salir = new JButton("Salir");
    	salir.addActionListener(this);
    	JPanel saveExit= new JPanel(new FlowLayout());
    	saveExit.add(guardar);
    	saveExit.add(salir);
    	
    	///////////// MenuItems///////////////
    	iControles = new JMenuItem("Controles");
    	menuBar.add(iControles);
    	iControles.addActionListener(this);
    	
    	
    	iPantalla = new JMenuItem("Pantalla");
    	menuBar.add(iPantalla);
    	iPantalla.addActionListener(this);
    	
    	
    	iSonido = new JMenuItem("Sonido");
    	menuBar.add(iSonido);
    	iSonido.addActionListener(this);
    	
    	iTanque = new JMenuItem("Tanque");
    	menuBar.add(iTanque);
    	iTanque.addActionListener(this);
    	
    	////////////leo prop desde archivo////////////
    	this.readConfig("Config-Usuario.txt");
    	
    	/////////////////// menus////////////////////
    	mControles = new MenuControles(prop);
    	mPantalla = new MenuPantalla(prop);
    	mSonido = new MenuSonido(prop);
    	mTanque = new MenuTanque(prop);
    	
    	////////////////////////////////////
    	panel = new JPanel();
    	cl = new CardLayout();
    	panel.setLayout(cl);
    	
    	panel.add(mControles,"Controles");	
    	panel.add(mPantalla,"Pantalla");
    	panel.add(mSonido,"Sonido");
    	panel.add(mTanque,"Tanque");
    	  	
    	
    	
    	//////////////////this contenedor////////////////
    	this.setLayout(new BorderLayout());
    	
    	this.add(BorderLayout.NORTH,menuBar);
    	this.add(BorderLayout.CENTER,panel);
    	this.add(BorderLayout.SOUTH,saveExit);
    
    	//this.pack(); //ajuste de pantalla automatico
    	
    	//this.setVisible(true);
    	//this.setResizable(false);
    	
    	
    }
    
    public Properties getP(){
		return prop;}
    
    public void readConfig(String archivo){
    	FileInputStream in = null;
    	JFrame f = new JFrame();
    	
    	try{
    		in = new FileInputStream(archivo);
    		prop.load(in);
    		
    		in.close();
    	}catch (Exception e){
    		JOptionPane.showMessageDialog(f,"No se pudo cargar la configuracion desde el archivo");	
  
    	}
    }
    
    public void actionPerformed(ActionEvent e){
    	cl.show(panel,e.getActionCommand() );
    	
    	  	if (e.getSource().equals(guardar)){
				try{  
    			//
    			mPantalla.updateConfig();
    			mSonido.updateConfig();
    			mControles.updateConfig();
    			mTanque.updateConfig();
    			JOptionPane.showMessageDialog(this,"La configuracion se guardo correctamente");
    			
    		}catch(Exception exc){
    		JOptionPane.showMessageDialog(this,"No se pudo guardar la nueva configuracion");
			}
		}
		
		else if(e.getSource().equals(salir)){
			MenuBattleCity.clayout.show(MenuBattleCity.jpanel, "Menu de Juego");
			}
    } 
    
    public static void nuevoGestorMenu(){
    	JFrame f = new JFrame(); 
    	try{
    		new GestorMenu();
    	}catch(Exception e){
    		JOptionPane.showMessageDialog(f,e.getStackTrace());
    	}
    	
    }
    
    
}
