
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** @author franklinvelasquezfuentes
 */
public class Hilo implements Runnable{
    
    ColaBloqueo<Integer> cola;
    int numero;

    public Hilo(ColaBloqueo<Integer> cola, int numero) {
        this.cola = cola;
        this.numero = numero;
    }

    @Override
    public void run() {
        Random r = new Random();
        int random_number = r.nextInt(2);
        
        if(random_number == 0){
            cola.insertar(numero);
        } else {
            cola.remover();
        }
    }
    
    
    

}
