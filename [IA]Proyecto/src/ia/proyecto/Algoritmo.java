/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.proyecto;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author koko
 */
public class Algoritmo {
    
    public LinkedList<Casilla> Sucesores;
    public LinkedList<Casilla> laberinto;
    public LinkedList<Camino> Caminos;
    Casilla inicio;
    int ncaminos;
    int filas;
    int columnas;
    public String Soluciones;
    
    public Algoritmo(LinkedList<Casilla> lab, int f, int c){
        this.Sucesores = new LinkedList<>();
        this.Caminos = new LinkedList<>();
        this.inicio = null;
        this.laberinto = lab;
        this.filas = f;
        this.columnas = c;
        this.ncaminos = 0;
        this.Soluciones = "";
    }
    
    public void EncontrarInicio(){
        for (Casilla item : this.laberinto) {            
            if(item.tipo == 2){                
                this.inicio = item;
                System.out.println("Inicia en la casilla: " + item.fila + " , " + item.columna);
            }            
        }        
    }
    
    public void EncontrarCaminos(){        
        
        if(this.Sucesores.isEmpty()){ 
            System.out.println("Sucesores de Inicio:");
            this.EncontrarSucesores(this.inicio);       //encontramos sucesores de inicio 
            this.EncontrarCaminos();            //encotramos caminos segun los sucesores de inicio
        }else{
            //recorrer lista de sucesores de inicio            
            if(this.Sucesores.size() == 1){ //si la lista de sucesores es igual a 1
                this.ncaminos++; // creamos camino nuevo
                Camino tmp = new Camino(this.ncaminos); 
                Casilla tmpc = this.Sucesores.get(0);
                tmpc.paso = true; //bandera de que ya es parte del camino
                tmp.camino.add(tmpc); //agregamos al camino la casilla
                this.Caminos.add(tmp); // agregamos el camino a la lista de caminos
                this.Sucesores.clear(); // limpiamos la lista de sucesores
            }else{
                //si la lista de sucesores es mayor que 1
                for (Casilla item : this.Sucesores) { //recorremos la lista de sucesores
                    this.ncaminos++; // creamos un nuevo camino
                    Camino tmp = new Camino(this.ncaminos); 
                    item.paso = true; // le ponemos a la casilla que ya es parte del camino
                    tmp.camino.add(item); //agregamos la casilla al camino
                    this.Caminos.add(tmp); // agregamos el camino a la lista de caminos
                }
                this.Sucesores.clear();// limpiamos la lista de sucesores
            }            
        }
        
        if(!this.Caminos.isEmpty()){            //vemos que la lista de caminos no sea vacia
            for(int i = 0; i < this.Caminos.size(); i++){ //recorremos la lista de caminos
                Camino caminos = this.Caminos.get(i); //obtenemos el primer camino
                Casilla ultima = caminos.camino.getLast(); //obtenemos la ultima casilla del camino
                if(this.haySucesores(ultima)){
                    i = i-1;
                }
                this.EncontrarSucesores(ultima); // Encontramos los sucesores del camino                
                //recorrer caminos
                if(this.Sucesores.size() == 1){ //si la lista es igual a 1
                    Casilla tmpc = this.Sucesores.get(0); // agregamos la casilla al mismo camino
                    tmpc.paso = true;
                    if(tmpc.essol){
                        caminos.essolucion = true;
                    }
                    caminos.camino.add(tmpc);
                }else{
                    int cnt = 0; // si no es igual a uno creamos nuevos caminos
                    for (Casilla item : this.Sucesores) { // recorremos a los sucesores
                        if(cnt == 0){ // si es el primer sucesor lo agregamos al camino actual
                            Casilla tmpc = item; //obtenemos la casilla
                            tmpc.paso = true; // bandera ya paso
                            if(tmpc.essol){
                                caminos.essolucion = true;
                            }
                            caminos.camino.add(tmpc); //agregamos al camino la casilla
                            cnt++; //aumentamos el contador
                        }else if(cnt == 1){ // cuando es el segund sucesor
                            this.ncaminos++; // agregamos nuevo camino
                            Camino tmp = new Camino(this.ncaminos);
                            for(Casilla ca: caminos.camino){ //copiamos la lista del camino anterior
                                Casilla nue = ca;
                                tmp.camino.add(nue);
                            }
                            tmp.camino.removeLast(); //removemos el ultimo elemento
                            item.paso = true; //bandera paso
                            if(item.essol){
                                tmp.essolucion = true;
                            }
                            tmp.camino.add(item); // agregamos casilla nueva
                            this.Caminos.add(tmp); // agregamos camino nuevo
                            cnt++; //aumentamos contador
                        }else if(cnt == 2){
                            this.ncaminos++; // agregamos nuevo camino
                            Camino tmp = new Camino(this.ncaminos);
                            for(Casilla ca: caminos.camino){ //copiamos la lista del camino anterior
                                Casilla nue = ca;
                                tmp.camino.add(nue);
                            }
                            tmp.camino.removeLast(); //removemos el ultimo elemento
                            item.paso = true; //bandera paso
                            if(item.essol){
                                tmp.essolucion = true;
                            }
                            tmp.camino.add(item); // agregamos casilla nueva
                            this.Caminos.add(tmp); // agregamos camino nuevo
                            cnt++; //aumentamos contador                               
                        }
                    }                                                
                }
                this.Sucesores.clear();
            }
        }
        
        
        String sol = "";
        for(Camino cam: this.Caminos){            
            String cami = "";
            if(cam.essolucion && cam.yaimprimio == false){
                cam.yaimprimio = true;
                for(int i = 0; i < cam.camino.size(); i++){
                    Casilla tmp = cam.camino.get(i);
                    if( i+1 != cam.camino.size()){
                        cami += tmp.mov + ", ";
                    }else{
                        cami += tmp.mov;
                    }                    
                }
                sol += cami + "\n";
            }             
        } 
        
        String[] parts = sol.split("\n");
        int so = 0;
        for(int i = parts.length - 1; i >= 0; i--){
            so++;
            if(!parts[i].equals("")){
                this.Soluciones += "Solucion: " + so + ": " +  parts[i] + "\n";
            }            
        }
        
    }
    
    public void EncontrarSucesores(Casilla ca){
        
        int Labajo = this.filas + 1;
        int Lder = this.columnas + 1;
        
        //sucesor superior
        if(ca.fila - 1 > -1){
            Casilla tmp = null;
            for (Casilla item : this.laberinto) {
                if(ca.fila - 1 == item.fila && ca.columna == item.columna){
                    tmp = item;
                }
            }
            if(tmp != null && (tmp.tipo == 0 || tmp.tipo == 3 ) && tmp.paso == false){
                if(tmp.tipo == 0){
                    tmp.mov = "U";
                    this.Sucesores.add(tmp);
                }else if(tmp.tipo == 3){
                    tmp.essol = true;
                    tmp.mov = "U";
                    this.Sucesores.add(tmp);
                }            
            }                       
        } 
        
        //sucesor inferior
        if(ca.fila + 1 < Labajo){
            Casilla tmp = null;
            for (Casilla item : this.laberinto) {
                if(ca.fila + 1 == item.fila && ca.columna == item.columna){
                    tmp = item;
                }
            }
            if(tmp != null && (tmp.tipo == 0 || tmp.tipo == 3 ) && tmp.paso == false){
                if(tmp.tipo == 0){
                    tmp.mov = "D";
                    this.Sucesores.add(tmp);
                }else if(tmp.tipo == 3){
                    tmp.essol = true;
                    tmp.mov = "D";
                    this.Sucesores.add(tmp);
                }                            
            }                       
        } 
        
        //sucesor izquierda
        if(ca.columna - 1 > -1){
            Casilla tmp = null;
            for (Casilla item : this.laberinto) {
                if(ca.fila == item.fila && ca.columna - 1 == item.columna){
                    tmp = item;
                }
            }
            if(tmp != null && (tmp.tipo == 0 || tmp.tipo == 3 ) && tmp.paso == false){
                if(tmp.tipo == 0){
                    tmp.mov = "L";
                    this.Sucesores.add(tmp);
                }else if(tmp.tipo == 3){
                    tmp.essol = true;
                    tmp.mov = "L";
                    this.Sucesores.add(tmp);
                }              
            }                       
        } 
        
        //sucesor derecha
        if(ca.columna + 1 < Lder){
            Casilla tmp = null;
            for (Casilla item : this.laberinto) {
                if(ca.fila == item.fila && ca.columna + 1 == item.columna){
                    tmp = item;
                }
            }
            if(tmp != null && (tmp.tipo == 0 || tmp.tipo == 3 ) && tmp.paso == false){
                if(tmp.tipo == 0){
                    tmp.mov = "R";
                    this.Sucesores.add(tmp);
                }else if(tmp.tipo == 3){
                    tmp.essol = true;
                    tmp.mov = "R";
                    this.Sucesores.add(tmp);
                }              
            }                       
        }         
        
        for (Casilla item : this.Sucesores) {
            System.out.println("Sucesor:" + item.fila + "," + item.columna + "\n");        
        }
        
    }
   
    public boolean haySucesores(Casilla ca){
        
        boolean hay = false;
        
        int Labajo = this.filas + 1;
        int Lder = this.columnas + 1;
        
        //sucesor superior
        if(ca.fila - 1 > -1){
            Casilla tmp = null;
            for (Casilla item : this.laberinto) {
                if(ca.fila - 1 == item.fila && ca.columna == item.columna){
                    tmp = item;
                }
            }
            if(tmp != null && tmp.tipo == 0 && tmp.paso == false){
                hay = true;            
            }                       
        } 
        
        //sucesor inferior
        if(ca.fila + 1 < Labajo){
            Casilla tmp = null;
            for (Casilla item : this.laberinto) {
                if(ca.fila + 1 == item.fila && ca.columna == item.columna){
                    tmp = item;
                }
            }
            if(tmp != null && tmp.tipo == 0 && tmp.paso == false){
                hay = true;             
            }                       
        } 
        
        //sucesor izquierda
        if(ca.columna - 1 > -1){
            Casilla tmp = null;
            for (Casilla item : this.laberinto) {
                if(ca.fila == item.fila && ca.columna - 1 == item.columna){
                    tmp = item;
                }
            }
            if(tmp != null && tmp.tipo == 0 && tmp.paso == false){
                hay = true;            
            }                       
        } 
        
        //sucesor derecha
        if(ca.columna + 1 < Lder){
            Casilla tmp = null;
            for (Casilla item : this.laberinto) {
                if(ca.fila == item.fila && ca.columna + 1 == item.columna){
                    tmp = item;
                }
            }
            if(tmp != null && tmp.tipo == 0 && tmp.paso == false){
                hay = true;           
            }                       
        }   
        
        return hay;
    }

}
