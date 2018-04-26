import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;


public class MenuRanking extends JPanel implements ActionListener{
	private JPanel panel;
	private JButton salir;
	private Properties prop = new Properties();
	private String [] posiciones={"1","2","3","4","5","6","7","8","9","10"};
	private String [] usuarios={"a","b","c","d","e","f","g","h","i","j"};
	
	MenuRanking() {
		panel = new JPanel(new GridLayout(13,3));
		panel.setBounds(200,50,400,500);
		salir = new JButton("Salir");
		salir.setBounds(310,550,100,30);
		salir.addActionListener(this);
		
		try{
				FileInputStream in = new FileInputStream("Ranking Battle City.txt");
				prop.load(in);
				panel.add(new JLabel("RANKING"));
				panel.add(new JLabel("BATTLE CITY"));
				panel.add(new JLabel(""));
				panel.add(new JLabel("POSICION"));
				panel.add(new JLabel("JUGADOR"));
				panel.add(new JLabel ("PUNTAJE"));
					for (int i=1;i<11;i++){
					panel.add(new JLabel(Integer.toString(i)));
					panel.add(new JLabel(prop.getProperty(usuarios[i-1])));
					panel.add(new JLabel(prop.getProperty(posiciones[i-1])));
				}
					
			}catch(Exception e){System.out.println("Error al mostrar ranking");}
			this.setLayout(null);
			this.add(panel);
			this.add(salir);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource().equals(salir)){
			MenuBattleCity.clayout.show(MenuBattleCity.jpanel,"Menu de Juego");
		}
	}
	
}

