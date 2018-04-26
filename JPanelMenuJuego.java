import java.util.*;
import java.awt.*;
import javax.swing.*;

public class JPanelMenuJuego extends JPanel{

	private Image img;

	public JPanelMenuJuego(JButton iniciar, JButton configurar, JButton opciones, JButton editor, JButton ranking, JButton salir, JTextField textoJugador){
		this.setLayout(null);
		img = new ImageIcon("Imagenes/the tanque.jpg").getImage(); 
		iniciar.setBounds(250,40,250,30);
		iniciar.setFont(new Font("Stencil",Font.PLAIN, 20)); 
		iniciar.setForeground(Color.RED);
		iniciar.setContentAreaFilled(false);
		configurar.setBounds(250,70,250,30);
		configurar.setFont(new Font("Stencil",Font.PLAIN, 20)); 
		configurar.setForeground(Color.RED);
		configurar.setContentAreaFilled(false);
		opciones.setBounds(250,100,250,30);
		opciones.setFont(new Font("Stencil",Font.PLAIN, 20)); 
		opciones.setForeground(Color.RED);
		opciones.setContentAreaFilled(false);
		editor.setBounds(250,130,250,30);
		editor.setFont(new Font("Stencil",Font.PLAIN, 20)); 
		editor.setForeground(Color.RED);
		editor.setContentAreaFilled(false);
		ranking.setBounds(250,160,250,30);
		ranking.setFont(new Font("Stencil",Font.PLAIN, 20)); 
		ranking.setForeground(Color.RED);
		ranking.setContentAreaFilled(false);
		salir.setBounds(250,190,250,30);
		salir.setFont(new Font("Stencil",Font.PLAIN, 20)); 
		salir.setForeground(Color.RED);
		salir.setContentAreaFilled(false);
		textoJugador.setBounds(250,10,250,30);
		this.add(textoJugador);
		this.add(iniciar);
		this.add(configurar);
		this.add(opciones);
		this.add(editor);
		this.add(ranking);
		this.add(salir);
		this.repaint();
	}

	public void paint(Graphics g){
		g.drawImage(img,0,0,getWidth(),getHeight(),this);
		setOpaque(false);
		super.paint(g);
	}
	
	public static void main(String [] strs){
		JFrame frame = new JFrame();
		frame.add(new JPanelMenuJuego(new JButton("INICIAR JUEGO"),new JButton("CONFIGURAR"),new JButton("ELEGIR NIVEL"),new JButton("EDITOR DE NIVELES"),new JButton("RANKING"),new JButton("SALIR DEL JUEGO"),new JTextField(20)));
		//frame.pack();
		frame.setSize(700,650);
		frame.setVisible(true);
	}
}
