/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.proyecto;

import java.util.LinkedList;

/**
 *
 * @author koko
 */
public class Laberinto {
    
    public LinkedList<Casilla> lab;
    public int filas;
    public int columnas;
    
    public Laberinto(){
        this.lab = new LinkedList<>();
        this.filas = 0;
        this.columnas = 0;
    }
}
