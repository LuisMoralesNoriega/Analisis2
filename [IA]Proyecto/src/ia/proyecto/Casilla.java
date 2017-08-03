/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.proyecto;

/**
 *
 * @author koko
 */
public class Casilla {
    
    int tipo;
    int fila;
    int columna;
    boolean paso;
    boolean essol;
    String mov;
    
    public Casilla(int t, int f, int c){
        this.tipo = t;
        this.fila = f;
        this.columna = c;
        this.paso = false;
        this.essol = false;
        this.mov = "";
    }
    
}
