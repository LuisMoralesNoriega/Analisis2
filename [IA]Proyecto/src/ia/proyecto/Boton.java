/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.proyecto;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author koko
 */
public class Boton extends JButton implements ActionListener{
    
    public Boton(int posx, int posy, int an, int alto){        
        setBounds(posx,posy,an,alto);
        addActionListener(this);
    }

    public void CambiarNombre(int f, int c){
        setText((f+1) + " , " + (c+1));       
    }
    
    public void CambiarColor(int c){
        if(c == 1){
            setBackground(Color.BLUE);
        }else if(c == 2){
            setBackground(Color.GREEN);
        }else if(c == 3){
            setBackground(Color.RED);
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        //setBackground(Color.BLUE);
    }
    
    
}
