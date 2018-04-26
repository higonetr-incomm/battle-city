import java.awt.*;
import javax.swing.*;
import java.awt.image.*;

public abstract class Objeto extends Rectangle
{
    protected Escenario contenedor;
    protected Image img;
    protected BufferedImage imgB;
    protected Rectangle recColision;
    protected boolean colisiono;
    
    public Objeto(int x, int y, int width, int height){
        super(x,y,width,height);
        colisiono=false;
    }
    
    public abstract void colisiono();
    
    public Rectangle recColision(){
    	return recColision;
    }
    
    public Objeto(){}
    
    public Escenario getEscenario(){
    		return contenedor;
    }
    
    public void asignarContenedor(Escenario ov){
		contenedor = ov;
	}

    public void paint(Graphics2D g){
    		g.drawImage(img,x,y,width,height,null);
    }
    
    public abstract void actualizar();
    
    public static Image loadImage(String path){
		return (new ImageIcon(path).getImage());
	}

}
