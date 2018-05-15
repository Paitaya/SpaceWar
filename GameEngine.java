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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
	
        
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        private ArrayList<SpecialObj> special = new ArrayList<SpecialObj>();
	
        private SpaceShip v1;	
	private SpaceShip2 v2;
        
	private Timer timer;
        private int timer_spo = 0;
        
        public boolean status1 = true;
        public boolean status2 = true;
        
	private long score = 0;
        private long score2 = 0;
        
	private double difficulty = 0.1;
        
	public GameEngine(GamePanel gp, SpaceShip v1 , SpaceShip2 v2) {
		this.gp = gp;
		this.v1 = v1;		
		this.v2 = v2;
                
                gp.sprites.add(v1);
                gp.sprites.add(v2);
		
                timer = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				processEnemy();
                                processSpecialObj();
                                timer_spo += 1;
			}
		});
		timer.setRepeats(true);	
	}
	
	public void start(){
                timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*1150), 10);
		gp.sprites.add(e);
		enemies.add(e);
	}

        private void generateObj(){
		SpecialObj s = new SpecialObj((int)(Math.random()*1150), 10);
		gp.sprites.add(s);
		special.add(s);
	}
        
	private void processEnemy(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				if(status1 && status2){
                                   score += 100;
                                   score2 += 100;
                                }
                                else if(status1){
                                    score += 100;
                                }
                                else if(status2){
                                    score2 += 100;
                                }
			}
		}
		
                if(timer_spo % 100 == 0) {
                        difficulty += 0.1;
                }
                
		gp.updateGameUI(this);
                
		Rectangle2D.Double vr1 = v1.getRectangle();
                Rectangle2D.Double vr2 = v2.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr1)){
				status1 = false;
                                die();
                                v1.isDie();
                                return;
			}
                        else if(er.intersects(vr2)){
				status2 = false;
                                die();
                                v2.isDie();
                                return;
			}
		}
	}
        
        private void processSpecialObj(){
                if(score >= 5000 || score2 >= 5000){
                    if(timer_spo == 500){
			generateObj();
                        timer_spo = 0;
                        
                    }
                }
		Iterator<SpecialObj> s_iter = special.iterator();
		while(s_iter.hasNext()){
			SpecialObj s = s_iter.next();
			s.proceed();
			
			if(!s.isAlive()){
				s_iter.remove();
				gp.sprites.remove(s);
			}
		}
		
		gp.updateGameUI(this);
                
                Rectangle2D.Double vr1 = v1.getRectangle();
                Rectangle2D.Double vr2 = v2.getRectangle();
		Rectangle2D.Double sr;
		for(SpecialObj s : special){
			sr = s.getRectangle();
			if(sr.intersects(vr1) && status1){
                                score *= 2;
                                s.isDie();
                                return;
			}
                        else if(sr.intersects(vr2) && status2){
                                score *= 2;
                                s.isDie();
                                return;
                        }
		}
	}
        
	public void die(){
           long scoreE = getScore();
           long scoreE2 = getScore2();
           
           if((!status1 && !status2)){
                timer.stop();
                EndGame eg = new EndGame();
                if( scoreE > scoreE2 ){
                    eg.EndGame1();
                }
                else if( scoreE < scoreE2 ){
                    eg.EndGame2();
                }
                else {
                    eg.EndGame3();
                }
            }
            
	}
	
	void controlVehicle(KeyEvent ke) {
		if(status1 == true){
                    switch (ke.getKeyCode()) {
                    case KeyEvent.VK_A:
                        v1.move_x(-1);
			break;
                    case KeyEvent.VK_D:
                        v1.move_x(1);
			break;  
                    case KeyEvent.VK_W:
                        v1.move_y(-1);
			break; 
                    case KeyEvent.VK_S:
                        v1.move_y(1);
			break; 
                    }
                }
                
                if(status2 == true){
                    switch (ke.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
			v2.move_x(-1);
			break;
                    case KeyEvent.VK_RIGHT:
			v2.move_x(1);
			break;
                    case KeyEvent.VK_UP:
			v2.move_y(-1);
			break;
                    case KeyEvent.VK_DOWN:
			v2.move_y(1);
			break; 
                    }
                } 
           
        }
        
	public long getScore(){
		return score;
	}
       
        public long getScore2(){
		return score2;
	}
       
	@Override
	public void keyPressed(KeyEvent e) {
                controlVehicle(e);
          
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//do nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}