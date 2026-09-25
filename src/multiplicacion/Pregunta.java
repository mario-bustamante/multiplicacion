package multiplicacion;

/**
 *
 * @author mario
 */
public class Pregunta {
    
    public int[] valor1;  // primer valor de la multiplicacion
    public int[] valor2;  // segundo valor de la multiplicacion
    public int[] resultado; // resultado de la multiplicacion
    public int correcto; //indice del valor correcto
    public int respuesta; //indice del valor correcto
    
    public Pregunta(){
        this.valor1 = new int[]{ 0, 0, 0};  // primer valor de la multiplicacion
        this.valor2 = new int[]{ 0, 0, 0};  // segundo valor de la multiplicacion
        this.resultado = new int[]{ 0, 0, 0}; // resultado de la multiplicacion
        this.correcto = 0; //indice del valor correcto
        this.respuesta = 0; //indice del valor correcto        
    }    
}