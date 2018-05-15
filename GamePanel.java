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
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
        
        private int status;
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();
        
        
	public GamePanel() {
                bi = new BufferedImage(1200, 800, BufferedImage.TYPE_INT_ARGB);
                big = (Graphics2D)bi.getGraphics();
                big.setBackground(Color.BLACK);
                
        }
	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 1200, 800);
		big.setColor(Color.WHITE);
                big.setFont(new Font ("Arial Block", Font.BOLD , 10));
                big.drawString(String.format("Player 1 's Score : %08d", reporter.getScore()),20, 20);
                big.drawString(String.format("Player 2 's Score : %08d", reporter.getScore2()),900, 20);
                //big.drawLine(600, 0, 600, 800);
		
                for(Sprite s : sprites){
			s.draw(big);
		}
		repaint();
        }
        
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi , null , 0 ,0);
	}

}