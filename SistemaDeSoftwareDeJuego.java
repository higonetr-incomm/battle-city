import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SistemaDeSoftwareDeJuego extends JFrame implements ActionListener, ItemListener{

	/*protected VideoJuego los_videoJuegos[];*/
	static JPanel main_panel;
	private JPanel panel_juegos;
	MenuBattleCity mbc;
	static CardLayout cl;	
	
	private Button bStart;
	private Button bAgregar;
	private Button bQuitar;
	private List listGames;	
	private MyCanvas myCanvas; //clase auxiliar

	private String arrImagenes[]={"Imagenes/wow.jpeg","Imagenes/battle.jpg","Imagenes/witcher.jpg"};
	/*private cantJuegos entonces*/
	private Image imagen[] = new Image[3];
	/*haces Image[cantJuegos]*/

	public SistemaDeSoftwareDeJuego(){
		super("Sistema de Software de Juegos");

		Panel panelImg = new Panel();
		Panel panelBot = new Panel();

		MediaTracker mt = new MediaTracker(this);
		//MediaTracker is a utility class to track the status of a number of media objects.
		//cargo las imagenes
		for(int i=0; i<arrImagenes.length ; i++){
			imagen[i] = Toolkit.getDefaultToolkit().getImage(arrImagenes[i]);
			//no se que es la clase Toolkit, pero toma la imagen del arreglo
			mt.addImage(imagen[i],i);
		}
		try{
			mt.waitForAll();
		} catch (InterruptedException ie) {}

		myCanvas = new MyCanvas(imagen[0]);
		bStart = new Button(" INICIAR ");
		bStart.addActionListener(this);
		bAgregar = new Button(" AGREGAR ");
		bAgregar.addActionListener(this);
		bQuitar = new Button(" ELIMINAR ");
		bQuitar.addActionListener(this);
		listGames = new List(20);
		listGames.addItemListener(this);

		panelImg.add(myCanvas,BorderLayout.CENTER);
		
		panelBot.setLayout(new GridLayout(3,1));
		panelBot.add(bStart);
		panelBot.add(bAgregar);
		panelBot.add(bQuitar);

		listGames.add("World Of Warcraft");
		listGames.add("Battle City");
		listGames.add("The Witcher 3");

		main_panel = new JPanel();
		cl = new CardLayout();
		main_panel.setLayout(cl);
		panel_juegos = new JPanel();
		mbc = new MenuBattleCity();
		
		panel_juegos.setLayout(new BorderLayout());
		panel_juegos.add(panelImg,BorderLayout.CENTER);
		panel_juegos.add(listGames,BorderLayout.WEST);
		panel_juegos.add(panelBot,BorderLayout.EAST);
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		main_panel.add(panel_juegos,"Sistema de Software de Juego");
		main_panel.add(mbc,"Battle City");
		//cl.show(main_panel,"Battle City");
		this.add(main_panel,BorderLayout.CENTER);
		this.setSize(705,680);
		this.setSize(630,400);
		//this.pack();
		this.setVisible(true);
		this.setResizable(false);
		//this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	void inciarVideoJuego(){
	}

	void finalizarVideoJuego(){
	}


	void quitarVideoJuego(){
	}

	public static void main (String [] strs){
		SistemaDeSoftwareDeJuego x = new SistemaDeSoftwareDeJuego();
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource().equals(bStart) && listGames.getSelectedItem().equals("Battle City")){
				this.setSize(705,680);
    			cl.show(main_panel,"Battle City");
    		}
	}

	public void itemStateChanged(ItemEvent e){
		myCanvas.cambiarImagen(imagen[listGames.getSelectedIndex()]);
		System.out.println(listGames.getSelectedItem());
	}

}

class MyCanvas extends Canvas{
	private Image imagen;

	public MyCanvas(Image i){
		imagen = i;
		this.setPreferredSize(new Dimension(400,400));
	}

	public void cambiarImagen(Image i){
		imagen = i;
		repaint();
	}

	public void paint(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0,0,getWidth(),getHeight());
		//rellena un rectangulo
		//g.setColor(Color.red); PARA QUE LA LINEA ROJA NO APAREZCA
		g.drawRect(0,0,imagen.getWidth(this)+1 ,imagen.getHeight(this)+1);
		//dibuja el rectangulo por afuera de la imagen
		//si las imagenes ocupan todo el espacio las lineas anteriores no son necesarias
		g.drawImage(imagen,0,0,this);
	}

}
