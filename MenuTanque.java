import javax.swing.*;
import java.io.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;

public class MenuTanque extends JPanel implements ListSelectionListener{
	private JLabel imgTanque;
	private JList colorTanque;
	private String [] colores = {"amarillo","azul","rojo","verde"};
	private String colorSelected;
	private Properties prop;
	
	public MenuTanque(Properties prop) {
		this.prop = prop;
		this.setLayout(null);
		colorTanque = new JList(colores);
		colorTanque.setSelectedIndex(0);
		colorSelected = colores[0];
		colorTanque.addListSelectionListener(this);
		colorTanque.setBounds(175,100,175,200);
		imgTanque = new JLabel();
		imgTanque.setIcon(new ImageIcon("Imagenes/Tanques Jugador/"+colores[0]+"ARRIBA.gif"));
		imgTanque.setBounds(400,125,100,100);
		this.add(colorTanque);
		this.add(imgTanque);
		
	}
	
		
	 public void valueChanged(ListSelectionEvent e){
        
        for(int i=0;i<colores.length;i++){
         if(colorTanque.getSelectedIndex()==i){
            colorSelected = colores[i];
         	imgTanque.setIcon(new ImageIcon("Imagenes/Tanques Jugador/"+colores[i]+"ARRIBA.gif"));
          }        
        } 
	}
	
	public void updateConfig(){
		try{
    		FileOutputStream out = new FileOutputStream("Config-Usuario.txt");
    		  		
    		//el color en prop
    			prop.setProperty("TANQUE",colorSelected);
    			
    		prop.store(out,"By: Jugador");
    		out.close();
    	}catch(Exception e){
    		System.out.println("Error al guardar controles en archivo Config-Usuarioooooooo.txt");
    	}
		}


}

