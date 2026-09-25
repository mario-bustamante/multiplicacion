package multiplicacion;

// combinado
public class JuegoC extends PanelBase {

    public JuegoC(String nombre, int dificultad) {
        super();
        this.jugador = nombre;    
        this.tipo_juego = 3;
        this.letra_juego = "b";
        this.dificultad = dificultad;
        
        this.setTitulo();        
        this.pregunta_iniciar();
        this.etapas_juego();   
    }
    
    @Override    
    public final void etapas_juego() {    
        this.letra_juego = ((this.letra_juego.equals("a"))?"b":"a");
        System.out.println(this.letra_juego);
        
        this.cargar_imagen_boton(this.button1, 0, 220, 308, this.letra_juego ); //230, 322
        this.cargar_imagen_boton(this.button2, 1, 220, 308, this.letra_juego ); //225, 315
        this.cargar_imagen_boton(this.button3, 2, 220, 308, this.letra_juego ); //220, 308
        this.cargar_imagen_boton(this.button0, this.pregunta.correcto, 220, 308, ((this.letra_juego.equals("a"))?"b":"a") );        
    }
}