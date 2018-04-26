
public abstract class Jugador{
	
	protected String nombre;
    protected  int score;
    protected  int nivel;
    protected double tiempo;

    public Jugador() {
    }

   public void setNombre(String nom){
	   nombre = nom;
	}
	public String getNombre(){
		return nombre ;
	}

    public int getScore(){
    	return score;

    }
    
    public void setNivel(int level){
    	nivel=level;
    }
    
    public int getNivel(){
   	return nivel;
   }
   
   public double getTiempo(){
   	return tiempo;
   }
   
   public void setTiempo(double t){
   	tiempo+=t;
   }
 }
