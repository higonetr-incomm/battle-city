import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.io.FileInputStream;
import java.awt.List;
import java.io.Reader.*;

public class MenuElegirNivel extends JPanel implements ActionListener, ItemListener{
	
	static List listaNiveles; //para que se pase el nivel nuevo creado
	private List listaNivelesElegidos;
	private JButton agregar;
	private JButton quitar;
	private	JButton confirmar;
	private JButton salir;
	
	public MenuElegirNivel(){
	
		listaNiveles = new List(20); listaNiveles.addItemListener(this);
		listaNivelesElegidos = new List(20); listaNivelesElegidos.addItemListener(this);
		agregar = new JButton(" Agregar Nivel "); agregar.addActionListener(this);
		quitar = new JButton(" Quitar Nivel "); quitar.addActionListener(this);
		confirmar = new JButton(" Confirmar "); confirmar.addActionListener(this);
		salir = new JButton(" Salir "); salir.addActionListener(this);
		
		this.setLayout(new BorderLayout());
		this.add(listaNiveles,BorderLayout.WEST);
		this.add(listaNivelesElegidos,BorderLayout.EAST);
		
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(5,1));
		aux.add(agregar);
		aux.add(quitar);
		aux.add(new JLabel(" "));
		aux.add(confirmar);
		aux.add(salir);
		
		try{
   			FileReader fr = new FileReader("Niveles/Niveles.txt");
   			String linea;
   			BufferedReader br = new BufferedReader(fr);
   			while((linea = br.readLine()) != null){
				listaNiveles.add(linea);
			}
			fr.close();
   		}catch(Exception e){
		
   		}
		
		this.add(aux,BorderLayout.CENTER);
	}
	
	public  void actionPerformed(ActionEvent e){
		if (e.getSource().equals(agregar)){
			if(listaNiveles.getSelectedItem()!=null)
				listaNivelesElegidos.add(listaNiveles.getSelectedItem());
		}
		if (e.getSource().equals(quitar)){
			if(listaNivelesElegidos.getSelectedItem()!=null)
				listaNivelesElegidos.remove(listaNivelesElegidos.getSelectedIndex());
		}
		if (e.getSource().equals(confirmar)){
			if((listaNivelesElegidos.getItemCount())>0){
				try{
					FileWriter fwAux = new FileWriter("Niveles/NivelesElegidos.txt");
					for(int i=0; i < listaNivelesElegidos.getItemCount() ; i++){
						fwAux.write(listaNivelesElegidos.getItem(i));
						fwAux.write(System.getProperty("line.separator"));
					}
					fwAux.close();
					JOptionPane.showMessageDialog(this,"Se guardo correctamente");
				}catch(Exception x){
					JOptionPane.showMessageDialog(this,"Error 404: NOT FOUND");
				}
			}
		}
		if (e.getSource().equals(salir)){
			MenuBattleCity.clayout.show(MenuBattleCity.jpanel,"Menu de Juego");
		}	
	}
	
	public void itemStateChanged(ItemEvent e){
	
	}
	
	public static void main(String strs[]){
		MenuElegirNivel prueba = new MenuElegirNivel();
		JFrame prueba1 = new JFrame();
		prueba1.add(prueba);
		prueba1.pack();
		prueba1.setVisible(true);
	}
	
}
