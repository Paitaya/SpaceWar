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

import java.awt.Color;
import java.awt.Graphics2D;

public class SpaceShip2 extends Sprite{

	int step = 10;
	private boolean s2_alive = true;
        
	public SpaceShip2(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(s2_alive){
                    g.setColor(Color.BLUE);
                    g.fillRect(x, y, width, height);
                }   
	}
        
        public void move_x(int direction){
		x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 1200 - width)
			x = 1180 - width;
        

	}
        public void move_y(int direction){
                y += (step * direction);
		if(y < 0)
			y = 0;
		if(y > 750 - height)
			y = 750 - height;
	}
        
        public boolean isAlive(){
		return s2_alive;
	}
        
        public void isDie(){
                s2_alive = false;
                
        }
}