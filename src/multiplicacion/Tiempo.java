package multiplicacion;

/**
 *
 * @author mario
 */
public class Tiempo extends Thread implements Runnable { // implementa un Hilo
  
    public int seg = 0; 

    public javax.swing.JTextField jtext;
    
    private boolean ejecutar; // para indicar si se siguen ejecutando los códigos en el lapso de tiempo

    public Tiempo(javax.swing.JTextField jtext) {
        this.jtext = jtext;
    }
    
    public Tiempo() {
       
    }    
    
    
    public void run () {        
        seg = 0; 
//        while(true){  //Bucle sin fin para que no termine el hilo
            try {
//                Thread.sleep(1000); // Dormimos el hilo un segundo
                Thread.currentThread().sleep(1000);
                /*
                seg++; //Cuando el hilo se despierta cada segundo añade un segundo a la variable local
                if(seg == 60){  //Si el segundo es igual a 60
                    seg = 0; //Los segundo vuelven a 0
                }
                
                    System.out.println(seg+"");

                this.jtext.setText(""+seg+"");
    */            
            } catch (InterruptedException e) {
                e.printStackTrace ();
            }
//        }
    }
    
    public int get_segundos() {
        return this.seg;
    }
}