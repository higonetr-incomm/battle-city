import java.io.*;
import java.util.*;
 
public class Ranking{
	private String [] posiciones={"1","2","3","4","5","6","7","8","9","10"};
	private String [] usuarios={"a","b","c","d","e","f","g","h","i","j"};
	
	private Properties prop = new Properties();
	private String nombre;
	
	Ranking(String name){
		this.nombre = name;
		
	}
	
	public void agregarPuntaje(int puntaje, String jugador){
		try{
			FileInputStream in = new FileInputStream(nombre+".txt");
			prop.load(in);
			FileOutputStream out = new FileOutputStream(nombre+".txt");
			int ex_puntaje;
			String ex_jugador;
			for (int i=(posiciones.length)-1;i>=0;i--){
				if (puntaje>Integer.parseInt(prop.getProperty(posiciones[i])) ){
					if (i!=(posiciones.length)-1){
						ex_puntaje = Integer.parseInt(prop.getProperty(posiciones[i]));
						ex_jugador = prop.getProperty(usuarios[i]);
						prop.setProperty(posiciones[i],Integer.toString(puntaje));
						prop.setProperty(usuarios[i],jugador);	
						prop.setProperty(posiciones[i+1],Integer.toString(ex_puntaje));
						prop.setProperty(usuarios[i+1],ex_jugador);	
					}
					else{
						prop.setProperty(posiciones[i],Integer.toString(puntaje));
						prop.setProperty(usuarios[i],jugador);	
					}			
				}
				
			}
			prop.store(out,"By: Jugador");
    		out.close();
    		in.close();
			
			
			}catch(Exception e){
				System.out.println("Error al agregar en el ranking");
				}		
		}
		
		public void  mostrarRanking(){
			try{
				FileInputStream in = new FileInputStream(nombre+".txt");
				prop.load(in);
				System.out.println("Top 10");
				for (int i=1;i<posiciones.length;i++){
					System.out.println(i +" "+ prop.getProperty(usuarios[i-1]) +" "+ prop.getProperty(posiciones[i-1]) );
				}
					
			}catch(Exception e){System.out.println("Error al mostrar ranking");}
			
			}
			
		
		public int ultimoPuntaje(){
				int ultimo=-1;
				try{
				FileInputStream in = new FileInputStream(nombre+".txt");
				prop.load(in);
				ultimo = Integer.parseInt(prop.getProperty("10"));
				}catch(Exception e){
					System.out.println("Error al obtener ultimo_puntaje");}
				
				return(ultimo);
			}

}
