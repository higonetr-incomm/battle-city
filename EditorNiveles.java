import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;

public class EditorNiveles extends JPanel implements ActionListener {
	
	private JPanel panel_superior;
	private JPanel panel_escenario;
	private JPanel panel_terrenos;
	
	private JTextArea nivel;
	private JButton guardar;
	private JButton salir;
	private int terreno_pulsado=-1;
	
	private Vector<ImageIcon> imagenes;	
	private JButton [] bot_escenario;
	private JButton [] bot_terreno;

    public EditorNiveles() {
    	try{
    	imagenes=new Vector<ImageIcon>();
		imagenes.add(new ImageIcon("Imagenes/terreno/agua.gif") );
		imagenes.add(new ImageIcon("Imagenes/terreno/paredLadrillo.gif") );
		imagenes.add(new ImageIcon("Imagenes/terreno/paredMetal.gif"));
		imagenes.add(new ImageIcon("Imagenes/terreno/arbusto.gif") );
		imagenes.add(new ImageIcon("Imagenes/otros/imageIconBLACK.jpg"));
		
		Image image;
		ImageIcon aguila = new ImageIcon(("Imagenes/otros/aguila (51x52).gif"));
        image = aguila.getImage();
        image = createImage(new FilteredImageSource(image.getSource(),
            new CropImageFilter(0, 0, 51, 52)));
		imagenes.add(new ImageIcon(image));
		imagenes.add(new ImageIcon("Imagenes/bonus/bonusTanque.gif"));
		
    	}catch(Exception e){
    		System.out.println("error al cargar imagenes en editor niveles");
    	}
    	
    	///////////panel superior//////////
    	panel_superior = new JPanel();
    	panel_superior.setLayout(new FlowLayout());
    	nivel=new JTextArea("Ingrese el nombre del nivel",1,20);
    	//nivel.AccessibleJTextArea;
    	guardar = new JButton("Guardar");
    	guardar.addActionListener(this);
    	salir = new JButton("Salir");
    	salir.addActionListener(this);
    	panel_superior.add(nivel);
    	panel_superior.add(guardar);
    	panel_superior.add(salir);
    		
    	///////////panel escenario//////////////
    	bot_escenario = new JButton[169];
    	panel_escenario = new JPanel();
    	panel_escenario.setLayout(new GridLayout(13,13));
    	
    	for(int i=0;i<bot_escenario.length;i++){
			//////////////////////base////////////////////
			if ((i>=148 && i<=150) || (i>=161 && i<= 163) ){
					if (i==162)
						bot_escenario[i]=new JButton(imagenes.get(5));
					else 
						bot_escenario[i]=new JButton(imagenes.get(1));
			}
					
			/////////////botones intocables (aparicion tanques)//////////////
			else if (i<=12 || i==160){
				bot_escenario[i]=new JButton(imagenes.get(6));
				bot_escenario[i].setEnabled(false);
			}
							
			////////botones modificables///////
			else {
				bot_escenario[i]=new JButton(imagenes.get(4));
				bot_escenario[i].addActionListener(this);
			}
			
				bot_escenario[i].setBackground(Color.BLACK);
				panel_escenario.add(bot_escenario[i]);				
    	}

    	/////////panel terrenos//////////////
    	bot_terreno = new JButton[5];
    	panel_terrenos = new JPanel();
    	panel_terrenos.setLayout(new GridLayout(5,1));
    	
    	for (int i=0;i<bot_terreno.length;i++){
			if (i==4)bot_terreno[i] = new JButton("Borrar");
			else bot_terreno[i] = new JButton(imagenes.get(i));
    		bot_terreno[i].setBackground(Color.WHITE);
    		panel_terrenos.add(bot_terreno[i]);
    		bot_terreno[i].addActionListener(this);
    		
    	}    	
    	
    	///////////this panel///////////
    	this.setLayout(new BorderLayout());
    	//this.setTitle("Editor de niveles Battle City");
    	
    	this.add(BorderLayout.NORTH,panel_superior);
    	this.add(BorderLayout.CENTER,panel_escenario);
    	this.add(BorderLayout.EAST,panel_terrenos);
    	//this.setVisible(true);
    	//this.setSize(700,700);
    	//this.pack();
    	
    }
    
    public void actionPerformed(ActionEvent e){
    	///////cambio terreno_pulsado////////
    	for (int i=0;i<bot_terreno.length;i++){
			if (e.getSource().equals(bot_terreno[i]))
			terreno_pulsado=i;
			}
    	////////////guardo nivel///////////
		if (e.getSource().equals(guardar)){
			if ( !nivel.getText().equals("Ingrese el nombre del nivel")){
				saveNivel();
				SistemaDeSoftwareDeJuego.cl.show(SistemaDeSoftwareDeJuego.main_panel,"Battle City");
			}else JOptionPane.showMessageDialog(this,"Ingrese el nombre del nivel");
    	}
    	/////////vuelvo al menu BattleCity//////
    	else if (e.getSource().equals(salir)){
			MenuBattleCity.clayout.show(MenuBattleCity.jpanel,"Menu de Juego");}
    	///////seteo obstaculos en terreno////
    	else if (terreno_pulsado!=-1){
    		for(int i=0;i<bot_escenario.length;i++){
    			if (i!=bot_escenario.length  && e.getSource().equals(bot_escenario[i]))
    				bot_escenario[i].setIcon(imagenes.get(terreno_pulsado));
    		}
    	}
    	
    }
   
   
   public void saveNivel(){
   		int obstaculo=0;
   		try{
   			FileWriter fw = new FileWriter("Niveles/"+nivel.getText()+".txt");
   			for(int i=0;i<bot_escenario.length;i++){
    			if (bot_escenario[i].getIcon().equals(imagenes.get(0)))
    				obstaculo=3;
    			else if (bot_escenario[i].getIcon().equals(imagenes.get(1)))
    				obstaculo=1;
    			else if (bot_escenario[i].getIcon().equals(imagenes.get(2)))
    				obstaculo=2;
    			else if (bot_escenario[i].getIcon().equals(imagenes.get(3)))
    				obstaculo=4;
    			else if (bot_escenario[i].getIcon().equals(imagenes.get(5)))
					obstaculo=5;
    			else 
    				obstaculo=0;
    				
    			fw.write(Integer.toString(obstaculo));
    			    			
    			if ((i+1)%13==0)
    				fw.write(System.getProperty("line.separator"));
    		}
    		
    		FileWriter fwAux = new FileWriter("Niveles/Niveles.txt",true);
    		fwAux.write(System.getProperty("line.separator"));
    		fwAux.write(nivel.getText());
    		//fwAux.write("TU VIEJA");
    		fwAux.close();
    		
    		MenuElegirNivel.listaNiveles.add(nivel.getText());
    		
    		fw.write("T11221123321111111111");
    		fw.close();
    		//Escenario.niveles.add(nivel.getText());
    		JOptionPane.showMessageDialog(this,"El nivel se guardo correctamente");
   		}catch(Exception e){
   			System.out.println("Error en EditorNiveles.saveNivel()");
   			System.out.println("Error E/S: "+e);
   			JOptionPane.showMessageDialog(this,"El nivel no se pudo guardar");
   		}
   		
   	
   	}
   	
    public static void main(String args[]){
    	EditorNiveles en = new EditorNiveles();
    }
    
}
