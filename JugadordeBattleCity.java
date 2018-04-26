public class JugadordeBattleCity extends Jugador{

	protected int vidaJugador;
	protected int puntos;

	public JugadordeBattleCity(String x){ //constructor con nombre
		vidaJugador=3;
		nombre = x;
		puntos = 0;
	}
	
	public int getPuntos(){
		return puntos;
	}
	
	public void sumarPuntos(int n){
		puntos = puntos + n ;
	}
	
	public int getVidas(){
		return vidaJugador;
	}

	public void quitarVida(){
		vidaJugador=vidaJugador-1;
	}
	
	public void sumarVida(){
		if( vidaJugador < 6 )
			vidaJugador = vidaJugador + 1 ;
	}
	
}
