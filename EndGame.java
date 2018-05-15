/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game2;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;


public class EndGame{

    public static void EndGame1(){ 
        JOptionPane.showMessageDialog(null, "PLAYER 1 WON!!!", "SPACE WAR", JOptionPane.PLAIN_MESSAGE);
    }  
    
    public static void EndGame2(){ 
        JOptionPane.showMessageDialog(null, "PLAYER 2 WON!!!", "SPACE WAR", JOptionPane.PLAIN_MESSAGE);
    } 
    
    public static void EndGame3(){ 
        JOptionPane.showMessageDialog(null, "DRAW!!!", "SPACE WAR", JOptionPane.PLAIN_MESSAGE);
    } 
}