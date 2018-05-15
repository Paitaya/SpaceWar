/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

/**
 *
 * @author ASUS
 */
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class SpecialObj extends Sprite{
	public static final int Y_TO_FADE = 1200;
	public static final int Y_TO_HELP = 800;
	
	private int step = 5;
	private boolean alive = true;
	
	public SpecialObj(int x, int y) {
		super(x, y, 20, 200);
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_HELP - y)/(Y_TO_HELP - Y_TO_FADE)));
		}
                
                g.setColor(Color.GREEN);
                g.setFont(new Font ("Arial Block", Font.BOLD , 50));
                g.drawString("X2" , x , y);
		
	}

	public void proceed(){
		y += step;
		if(y > Y_TO_HELP){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
        
        public void isDie(){
                alive = false;
        }
}