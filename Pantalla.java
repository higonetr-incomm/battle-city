import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.awt.*;

public class Pantalla extends Canvas
{
    protected BufferStrategy strategy;
    protected Color bgColor;
    protected Graphics2D graph;
    
    
    public Pantalla(int width, int height, Color c){
		bgColor = c;
		setPreferredSize(new Dimension(width,height));
		setIgnoreRepaint(true);
	}
	
	public void init(){
		requestFocus();
		createBufferStrategy(3);
		strategy = getBufferStrategy();
	}
	
	public void initGraphics(){
		graph = (Graphics2D)strategy.getDrawGraphics();
		
	}
	
	public void paintBackground(){
		graph.setColor(bgColor);
		if (BattleCity.fullscreen){
		graph.fillRect(0,0,BattleCity.screenSize.width,BattleCity.screenSize.height);
		graph.scale(BattleCity.ESCALA_X,BattleCity.ESCALA_Y);
		}
		else graph.fillRect(0,0,750,650);
	}
	
	public void paintObject(Objeto o){
		o.paint(graph);
	}
	
	public void mostrar(){
		graph.dispose();
		strategy.show();
	}
	
	

}
