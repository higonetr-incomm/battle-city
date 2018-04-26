import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class MenuPantalla extends JPanel implements ActionListener{
	private JPanel p;
	private JLabel l;
	private ButtonGroup bg;
	private JCheckBox ck1, ck2;
	private String pantalla;
	private Properties prop = new Properties();
    
    public MenuPantalla(Properties prop) {
    	
    	this.prop = prop;
    	l = new JLabel("Modo Pantalla ");
    	bg = new ButtonGroup();
    	
    	ck1 = new JCheckBox("En Ventana",true);
    	ck1.addActionListener(this);
    	pantalla = "En Ventana";
    	
    	ck2 = new JCheckBox("Pantalla Completa",false);
    	ck2.addActionListener(this);
    	
    	bg.add(ck1);
    	bg.add(ck2);
    	
    	p = new JPanel();
    	p.setLayout(new FlowLayout() );
    	p.add(l);
    	p.add(ck1);
    	p.add(ck2);
    	
    	this.setLayout(new BorderLayout() );
    	this.add(BorderLayout.CENTER,p);
    	
    
    	
    }
    
    public  void actionPerformed(ActionEvent e){
    	if(e.getActionCommand()=="Pantalla Completa"){   	
    		pantalla = "Pantalla Completa";
    	}
    	else if(e.getActionCommand()=="En Ventana"){
    		pantalla = "En Ventana";
    	}
    	
    }
    
    public void updateConfig(){
    	try{
    		FileOutputStream out = new FileOutputStream("Config-Usuario.txt");
    		
    		//modifico tipo pantalla en prop
    		prop.setProperty("Pantalla", pantalla);
    		
    		//guardo prop en archivo
    		prop.store(out,"By: Jugador");
			out.close();
    		
    	}catch(Exception e){
    		System.out.println("Error al guardar pantalla en archivo Config-Usuario.txt");	
    	}
    }
    
        /*public void readConfig(String archivo){

			try{
				FileInputStream in = new FileInputStream(archivo);
				 prop.load(in);
				 
				 if("En Ventana".equals(prop.getProperty("Pantalla") )){
				 	pantalla="En Ventana";
				 	ck1.setSelected(true);
				 }else {
				 	pantalla="Pantalla Completa";
				 	ck2.setSelected(true);
				 }
			 
		in.close();		 	 
		}catch(Exception e){
			System.out.println("Error al cargar pantalla desde archivo Config-Usuario.txt");
		}
    
	}*/
	
	/*public void setConfig(){
		if (pantalla == "Ventana";)
			bg.setSelected(ck1,true);
		else if (pantalla == "Completa")
			bg.setSelected(ck2,true);
	} */
}
