import java.awt.*;
import javax.swing.*;

public abstract class Obstaculo extends Objeto{
	protected boolean destruible=true;
	public Obstaculo(int x, int y, int width, int height){
		super(x,y,width,height);
		//super(BattleCity.X_ESCENARIO + x,BattleCity.Y_ESCENARIO + y,width,height);
	}
	public boolean getDestruible(){
		return destruible;
	}
}
