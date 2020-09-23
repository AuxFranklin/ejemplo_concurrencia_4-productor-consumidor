
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** @author franklinvelasquezfuentes
 */
public class ColaBloqueo <E> {
    
    
    private int max;
    private LinkedList<E> cola ;
    
    // concurrencia
    private ReentrantLock bloqueo = new ReentrantLock(true);
    private Condition noVacio = bloqueo.newCondition();
    private Condition noLleno = bloqueo.newCondition();

    public ColaBloqueo(int max) {
        this.max = max;
        this.cola = new LinkedList<>();
    }
    
    
    public void insertar(E element){
        
        bloqueo.lock();
        
        try {
            
            while(cola.size() == max){
                noLleno.wait(); // -> 4 productores esperando
            }
            // ---> 4 continuan
            
            cola.add(element);
                   
            noVacio.signalAll();  // -> 1 productor insertó
            
            //noVacio.signal();
 
        } catch (Exception e) {
            
        } finally {
            bloqueo.unlock();
        }
    }
    
    public E remover(){
        bloqueo.lock();
        
        try {
            
            while(cola.size() == 0){
                noVacio.wait();  // -> 3 consumidores esprando
            }
            // -> 3 consumidores van a retirar
            
            E element = cola.remove();
            
            noLleno.signalAll();  // -> Libere 2 
            
            return element;
            
            
        } catch (Exception e) {
            
            return null;
        } finally {
            bloqueo.unlock();
        }
    }
    
    public void print(){
        for (int i = 0; i < this.cola.size(); i++) {
            System.out.println( "Se insertó en : [" +i + "] - valor : " +  cola.get(i));
            
        }
    }
    
    

}
