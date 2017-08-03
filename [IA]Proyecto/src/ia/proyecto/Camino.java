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
public class Camino {
 
    public LinkedList<Casilla> camino;
    int solucion;
    boolean essolucion;
    boolean yaimprimio;
    
    public Camino(int s){
        this.camino = new LinkedList<>();       
        this.solucion = s;
        this.essolucion = false;
        this.yaimprimio = false;
    }
    
}
