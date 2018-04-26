import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class MenuBattleCity extends JPanel implements ActionListener{
	
	private JButton bIniciar;
	private JButton bConfigurar;
	private JButton bEditor;
	private JButton bSalir;
	private JButton bRanking;
	private JButton bOpciones;
	private JTextField nombre;
	private Image img;
	
	private JPanelMenuJuego menuJuego;
	static JPanel jpanel;
	static CardLayout clayout;
	private GestorMenu gestorMenu;
	private EditorNiveles editorNiveles;
	private BattleCity battlecity;
	private MenuRanking menuRanking;
	private MenuElegirNivel menuElegirNivel;

	
	public MenuBattleCity(){
		bIniciar = new JButton(" Jugar "); bIniciar.addActionListener(this);
		bIniciar.setBounds(300,400,100,45);
		bConfigurar = new JButton(" Configuraciones "); bConfigurar.addActionListener(this);
		bEditor = new JButton(" Editor de niveles "); bEditor.addActionListener(this);
		bRanking = new JButton (" Ver Ranking "); bRanking.addActionListener(this);
		bOpciones = new JButton(" Elegir Niveles "); bOpciones.addActionListener(this);
		bSalir = new JButton(" Salir "); bSalir.addActionListener(this);
		nombre = new JTextField("Nombre Jugador");
		
		//img = new javax.swing.ImageIcon("Imagenes/battle.jpg").getImage();
		jpanel= new JPanel();
		clayout = new CardLayout();
		jpanel.setLayout(clayout);
		this.setLayout(null);

		menuJuego = new JPanelMenuJuego(bIniciar, bConfigurar, bOpciones, bEditor, bRanking, bSalir, nombre);
		jpanel.add(menuJuego, "Menu de Juego");
		gestorMenu = new GestorMenu();
		jpanel.add(gestorMenu,"Menu de configuraiones");
		menuElegirNivel = new MenuElegirNivel();
		jpanel.add(menuElegirNivel,"Menu ElegirNivel");
		editorNiveles = new EditorNiveles();
		jpanel.add(editorNiveles,"Editor de Niveles");
		menuRanking = new MenuRanking();
		jpanel.add(menuRanking,"Menu Ranking");
		

		
		
		jpanel.setBounds(0,0,700,650);
		this.add(jpanel);		

	}
	


	
	public void actionPerformed(ActionEvent e){
		//aca hacer que se abra lo que corresponde segun el boton
		
		if(e.getSource().equals(bIniciar)){
			System.out.println("adentro");
			if(!(nombre.getText().equals("Nombre Jugador"))){
				try{
					FileInputStream in = new FileInputStream("Config-Usuario.txt");
					FileOutputStream out = new FileOutputStream("Config-Usuario.txt");
					//Properties prop = new Properties();
					gestorMenu.getP().load(in);
					gestorMenu.getP().setProperty("jugador",nombre.getText());
					gestorMenu.getP().store(out,"By: Jugador");
					out.close();
					in.close();
				}catch(Exception ex){
					System.out.println("Error al obtener nombre jugador");}
				BattleCity.nuevoBattleClity();
				
				//clayout.show(jpanel,"Juego");
				SistemaDeSoftwareDeJuego.main_panel.setEnabled(false);
			}
			else JOptionPane.showMessageDialog(this,"Ingrese nombre jugador");
		}
		if(e.getSource().equals(bConfigurar)){
			clayout.show(jpanel,"Menu de configuraiones");
		}
		if(e.getSource().equals(bEditor)){
			clayout.show(jpanel,"Editor de Niveles");
		}
		if (e.getSource().equals(bRanking)){
			clayout.show(jpanel,"Menu Ranking");
		}
		if (e.getSource().equals(bOpciones)){
			clayout.show(jpanel,"Menu ElegirNivel");
		}
		
		
		if(e.getSource().equals(bSalir)){
			SistemaDeSoftwareDeJuego.cl.show(SistemaDeSoftwareDeJuego.main_panel,"Sistema de Software de Juego");
		}		
	}
	
	public String getJugador(){
			return nombre.getText();
		}
	
}

