
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author franklinvelasquezfuentes
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ExecutorService  ejecutor = Executors.newCachedThreadPool();
        
        ColaBloqueo<Integer> cola = new ColaBloqueo<>(10);
        
        for (int i = 0; i < 1000; i++) {
            ejecutor.execute( new Hilo(cola,i));
        }
        
        
        ejecutor.shutdown();
        
        try {
            ejecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
            cola.print();
        } catch (Exception e) {
        }
        
    }
    
}
