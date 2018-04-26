import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class Sonido{

	private Clip audio;
	private int loop;
	
	public Sonido(){}

	public void load(String ruta, int l) {
      try {
         // Utilizar URL (en vez deFile) para accedes tando del disco como del JAR.
         	loop = l ;
         	URL url = this.getClass().getClassLoader().getResource(ruta);
			AudioInputStream ais = AudioSystem.getAudioInputStream(url);

         	audio = AudioSystem.getClip();
			audio.open(ais);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }   
	public void play() {
         if (audio.isRunning())
            audio.stop();
         if(  BattleCity.sonido ){   // Stop the player if it is still running
			audio.setFramePosition(0); // rewind to the beginning
         //audio.start(); // Start playing
			audio.loop(loop); 
		}
         	
         	//prueba para hacer esperar la aplicacion para escuchar sonido antes de que se cierre
         	/*try {
         		Thread.currentThread().sleep(2000);
         	}catch (Exception e ) {e.printStackTrace();}*/
	}
	public void stopAudio(){
		if(audio.isRunning()){
				audio.stop();
		}
	}
	

}
