package multiplicacion;

// resultado
public class JuegoA extends PanelBase {

    public JuegoA(String nombre, int dificultad) {
        super();
        this.jugador = nombre;    
        this.tipo_juego = 1;
        this.letra_juego = "b";
        this.dificultad = dificultad;
        
        this.setTitulo();        
        this.pregunta_iniciar();
        this.etapas_juego();
    }
    
    @Override    
    public final void etapas_juego() {     
        this.cargar_imagen_boton(this.button1, 0, 220, 308, "b");
        this.cargar_imagen_boton(this.button2, 1, 220, 308, "b");
        this.cargar_imagen_boton(this.button3, 2, 220, 308, "b");
        this.cargar_imagen_boton(this.button0, this.pregunta.correcto, 220, 308, "a");        
    }
}