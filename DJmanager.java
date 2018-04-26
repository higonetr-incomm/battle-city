import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class DJmanager {
    private Sonido[] sonidos;
     
    public DJmanager(int num){
		sonidos = new Sonido[num];
    }
    
    // repetir van a ser la cantidad de veces que se repite la ejecución del sonido
    public void cargarSonido (int idSonido, String ruta, int repetir){
		if (idSonido<sonidos.length){
			Sonido sound = new Sonido();
			sound.load(ruta,repetir);			
			sonidos[idSonido] = sound ;
		}
		else
			System.out.println("ERROR: el id de sonido es inválido");
    }
    
    public Sonido getSonido(int idSonido){
		return sonidos[idSonido];
	}
	
	/*public static void main(String str[] ){
		DJmanager dj = new DJmanager(6);
		dj.cargarSonido(0,"Sonido/atari.wav",10);
		dj.getSonido(0).play();
		dj.cargarSonido(1,"Sonido/war2.WAV",1);
		dj.getSonido(1).play();
		dj.cargarSonido(2,"Sonido/HAN01.WAV",2);
		dj.getSonido(2).play();
	}*/
}
