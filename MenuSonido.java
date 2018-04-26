import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;


public class MenuSonido extends JPanel implements ActionListener {
	private JPanel acti_sonido,sel_pista;
	private JLabel sound,pista;
	private JCheckBox on;
	private JButton defecto,a;
	private JList<String> lista;
	private String [] pistas = {"Pista Original", "Mario Bros", "Spectre"};
	private String seleclista = pistas[0];
	private Boolean sonido = true;
	private Properties prop = new Properties();

    public MenuSonido(Properties prop) {
    	//////////////Panel activar sonido//////////// 
    	this.prop = prop;
    	sound = new JLabel("Sonido: ");
    	on = new JCheckBox("Activado",true);
    	on.addActionListener(this);
    	
    	
    	acti_sonido = new JPanel();
    	acti_sonido.setLayout(new FlowLayout() );
    	
    	acti_sonido.add(sound);
    	acti_sonido.add(on);
    	
    	///////////Panel seleccion tema////////////////
    	pista = new JLabel("Pista musical ");
    	lista = new JList<String>(pistas);
    		
    	    	
    	sel_pista = new JPanel();
    	sel_pista.setLayout(new FlowLayout() );
    	
    	sel_pista.add(pista);
    	sel_pista.add(lista);
    	
    	///////boton reestablecer///
    	defecto = new JButton("Reestablecer valores");
    	defecto.addActionListener(this);
    	
    	
    	/////////////////this JPANEL//////////////////
    	this.setLayout(new BorderLayout() );
 		this.add(BorderLayout.NORTH,acti_sonido);
 		this.add(BorderLayout.CENTER,sel_pista);
 		this.add(BorderLayout.SOUTH,defecto);
 		
 		//cargo valores por defecto
 		//this.readConfig("Config-Usuario.txt");
 		setConfig();
    }
    
    
    public  void actionPerformed(ActionEvent e){
    		if(e.getSource()==defecto)
		{
			this.setDefault();
			setConfig();
		}
		else{
	       if(sonido){
	       	  lista.setEnabled(false);
	       	  sonido=false;     	 
	       }
	       	else
	       	{
	        	lista.setEnabled(true);
	        	sonido=true;
	       	}
		}
    		
    }
    
    public  void updateConfig(){
    	try{
    		FileOutputStream out = new FileOutputStream("Config-Usuario.txt");
    		
    		//modifico sonido en prop
    		prop.setProperty("Sonido", sonido.toString() );
    		prop.setProperty("Pista", lista.getSelectedValue().toString() );
    		
    		
    		//guardo prop en archivo
    		prop.store(out,"By: Jugador");
			out.close();
    		
    	}catch(Exception e){
    		System.out.println("Error al guardar sonido en archivo Config-Usuario.txt");	
    	}
    }
    
    public void setDefault(){
    	FileInputStream in=null;

			try{
				 in = new FileInputStream("Config-Default.txt");
				 prop.load(in);
				 
				 if("true".equals(prop.getProperty("Sonido")))
					sonido=true;
			 	 else
			 		sonido=false;
			 	
			 	seleclista=prop.getProperty("Pista");
			 	in.close();	 
		}catch(Exception e){
			System.out.println("Error al cargar sonido desde archivo Config-Usuario.txt");
		}
    
	}
	
	public void setConfig(){
		if("true".equals(prop.getProperty("Sonido")))
					sonido=true;
			 	 else
			 		sonido=false;
			 	
			 	seleclista=prop.getProperty("Pista");
			 	
		if(sonido){
			on.setSelected(true);
			lista.setSelectedValue(seleclista,true);
			lista.setEnabled(true);
		}
	    else{
	    	on.setSelected(false);
	    	lista.setSelectedValue(seleclista,true);
	    	lista.setEnabled(false);
	    }
		
	}
	
	
}
