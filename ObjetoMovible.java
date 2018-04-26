import java.awt.*;

public abstract class ObjetoMovible extends Objeto
{
	//how many pixels the object skips in each movement
	protected int step;
	//the angle in which this object is moving
	protected double direction;
	
	protected boolean colisiona=false;
	//estos dos para ver la colision
	//public Rectangle recColision;
	
	protected boolean out;
	
	public static final int REB_HORIZONTAL = 0;
	public static final int REB_VERTICAL = 1;
	public static final double DIR_UP = 270.0;
	public static final double DIR_DOWN = 90.0;
	public static final double DIR_RIGHT = 0.0;
	public static final double DIR_LEFT = 180.0;
	public static final double DIR_NONE = -1;
	
    public ObjetoMovible(int x, int y, int width, int height){
        super(x,y,width,height);
        out = false;
        direction = DIR_NONE;
    }
    public ObjetoMovible(){
		out = false;
		direction = DIR_NONE;
	}
	
	public double getDirection(){return direction;}
	
	public boolean isOut() {return out;}
	public void setOut(boolean b) {
		out = b;
		if (out) contenedor.sacarObjeto(this);
	}
	public int getStep(){return step;}
	
	//metodo polimorfico
	public void actualizar(){
		mover();
	}
	
	//moves this object as far as it does not go out a given dimension
	public void mover(){
		if (nextX()>0 & 
			nextX()+width < contenedor.getWidth() && 
			nextY()>0 && 
			nextY()+height < contenedor.getHeight()) { 
	
			/*recColision = new Rectangle(nextX(),nextY(),this.width,this.height);
			//for tanto if recColision intersecta algo
			for(Objeto obj : this.contenedor.objetos){
				if((obj!=this)&&!(obj instanceof Proyectil)){
					//System.out.println(obj instanceof ParedDeLadrillo);
					colisiona=recColision.intersects(obj);
					if(colisiona) 
						break;
					//recColision.getBounds().intersects(vectorRectangle.get("Obstaculo").get(i).getBounds())
					//System.out.println("ESTOY VIENDO SI CHOCA CON LOS OBJETOS"+colisiona);
				}
			}*/			
			
			if ((direction != DIR_NONE)&&(colisiona==false)){
				x += Math.round(Math.cos(Math.toRadians(direction)) * step);
				y += Math.round(Math.sin(Math.toRadians(direction)) * step);
				//System.out.println("moviendo...");
			}
			
			/*if (x<=0 || 
				x+width >= contenedor.getWidth() ||
				y<=0 ||
				y+height >= contenedor.getHeight()){				
				collides();
				System.out.println("no se q hace...");}*/	
		}
		else collides();
	}
	//invocado cuando este objeto colisiona con los bordes de su contenedor
	public abstract void collides();
	
	//public abstract void colision();
	
  
	protected int nextX(){
		return x+(int)Math.round(Math.cos(Math.toRadians(direction)) * step);
	}
	protected int nextY(){
		return y+(int)Math.round(Math.sin(Math.toRadians(direction)) * step);
	}
 
}
