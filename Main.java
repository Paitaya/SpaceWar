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
import com.sun.prism.paint.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main{
	public static void main(String[] args){  
               JFrame frame = new JFrame("Space War");
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setSize(1200, 800);
               frame.getContentPane().setLayout(new BorderLayout());
               SpaceShip v1 = new SpaceShip(300, 700, 30, 30);
               SpaceShip2 v2 = new SpaceShip2(900, 700, 30, 30);
               GamePanel gp = new GamePanel();
               GameEngine engine = new GameEngine(gp, v1 , v2);
               frame.addKeyListener(engine);
               frame.getContentPane().add(gp, BorderLayout.CENTER);
               engine.start();
               frame.setVisible(true);
        }
               
}
