package multiplicacion;

import java.awt.Color;
import java.awt.Image;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author mario
 */
public abstract class PanelBase extends javax.swing.JPanel {

    Thread Tiempo;
    
    protected Pregunta pregunta;    
    protected LinkedList<Pregunta> preguntas;
    
    protected Resultados resultado;
    
    protected int contador = 1;
    protected int etapas = 1;
    protected int indice = -1; //trabaja con el indice actual
    
    protected String jugador = "";
    protected int tipo_juego = 0;
    protected String letra_juego = "";
    protected int dificultad = 0;
    
    private int esperar = 0;
    
    public PanelBase() {
        initComponents();
        this.setSize(960, 470);
        this.setBackground(new java.awt.Color(160, 160, 246));

        this.pregunta = new Pregunta();    
        this.preguntas = new LinkedList<Pregunta>();
        this.btresultado.setVisible(true);
        this.resultado = new Resultados(); 
        
        this.btleft.setVisible(false);
        this.btright.setVisible(false);  
        
        this.lbrespuesta2.setVisible(false);
        this.lbrespuesta1.setVisible(false);        
        this.lbrespuesta3.setVisible(false);        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        button0 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        button1 = new javax.swing.JButton();
        lbrespuesta1 = new javax.swing.JLabel();
        button2 = new javax.swing.JButton();
        lbrespuesta2 = new javax.swing.JLabel();
        button3 = new javax.swing.JButton();
        lbrespuesta3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btright = new javax.swing.JButton();
        btleft = new javax.swing.JButton();
        btresultado = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        texto = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();

        setBorder(null);
        setFocusTraversalPolicyProvider(true);
        setMaximumSize(new java.awt.Dimension(960, 470));
        setMinimumSize(new java.awt.Dimension(960, 470));
        setPreferredSize(new java.awt.Dimension(960, 470));
        setLayout(null);

        jPanel2.setBackground(java.awt.Color.white);
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel2.setToolTipText("");
        jPanel2.setMaximumSize(new java.awt.Dimension(250, 340));
        jPanel2.setPreferredSize(new java.awt.Dimension(240, 340));
        jPanel2.setLayout(null);

        button0.setBackground(java.awt.Color.white);
        button0.setBorder(null);
        button0.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        button0.setFocusPainted(false);
        button0.setFocusable(false);
        button0.setMaximumSize(new java.awt.Dimension(154, 215));
        button0.setMinimumSize(new java.awt.Dimension(154, 215));
        button0.setPreferredSize(new java.awt.Dimension(154, 215));
        button0.setRequestFocusEnabled(false);
        button0.setRolloverEnabled(false);
        button0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button0MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button0MouseExited(evt);
            }
        });
        jPanel2.add(button0);
        button0.setBounds(10, 10, 225, 315);

        add(jPanel2);
        jPanel2.setBounds(0, 82, 245, 335);

        jPanel1.setBackground(java.awt.Color.white);
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel1.setToolTipText("");
        jPanel1.setLayout(null);

        button1.setBackground(java.awt.Color.white);
        button1.setAlignmentX(0.1F);
        button1.setAlignmentY(0.1F);
        button1.setBorder(null);
        button1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button1.setMaximumSize(new java.awt.Dimension(154, 215));
        button1.setMinimumSize(new java.awt.Dimension(154, 215));
        button1.setPreferredSize(new java.awt.Dimension(154, 215));
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel1.add(button1);
        button1.setBounds(235, 10, 225, 315);

        lbrespuesta1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbrespuesta1.setToolTipText("");
        lbrespuesta1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(lbrespuesta1);
        lbrespuesta1.setBounds(320, 30, 50, 50);
        button1.add(lbrespuesta1);

        button2.setBackground(java.awt.Color.white);
        button2.setAlignmentX(0.1F);
        button2.setAlignmentY(0.1F);
        button2.setBorder(null);
        button2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        button2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button2.setMaximumSize(new java.awt.Dimension(154, 215));
        button2.setMinimumSize(new java.awt.Dimension(154, 215));
        button2.setPreferredSize(new java.awt.Dimension(154, 215));
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel1.add(button2);
        button2.setBounds(10, 10, 225, 315);

        lbrespuesta2.setToolTipText("");
        lbrespuesta2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(lbrespuesta2);
        lbrespuesta2.setBounds(100, 30, 50, 50);
        button2.add(lbrespuesta2);

        button3.setBackground(java.awt.Color.white);
        button3.setAlignmentX(0.1F);
        button3.setAlignmentY(0.1F);
        button3.setBorder(null);
        button3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button3.setMaximumSize(new java.awt.Dimension(154, 215));
        button3.setMinimumSize(new java.awt.Dimension(154, 215));
        button3.setPreferredSize(new java.awt.Dimension(154, 215));
        button3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button3ActionPerformed(evt);
            }
        });
        jPanel1.add(button3);
        button3.setBounds(460, 10, 225, 315);

        lbrespuesta3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbrespuesta3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel1.add(lbrespuesta3);
        lbrespuesta3.setBounds(550, 30, 50, 50);
        button3.add(lbrespuesta3);

        add(jPanel1);
        jPanel1.setBounds(245, 82, 695, 335);

        jPanel3.setBackground(new java.awt.Color(111, 111, 234));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel3.setToolTipText("");
        jPanel3.setLayout(null);

        btright.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/right_32.png"))); // NOI18N
        btright.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btrightActionPerformed(evt);
            }
        });
        jPanel3.add(btright);
        btright.setBounds(890, 0, 50, 40);

        btleft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/left_32.png"))); // NOI18N
        btleft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btleftActionPerformed(evt);
            }
        });
        jPanel3.add(btleft);
        btleft.setBounds(0, 0, 50, 40);

        btresultado.setFont(new java.awt.Font("DejaVu Sans", 1, 15)); // NOI18N
        btresultado.setForeground(java.awt.Color.black);
        btresultado.setText("Resultado");
        btresultado.setMaximumSize(new java.awt.Dimension(100, 29));
        btresultado.setMinimumSize(new java.awt.Dimension(100, 29));
        btresultado.setPreferredSize(new java.awt.Dimension(100, 29));
        btresultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btresultadoActionPerformed(evt);
            }
        });
        jPanel3.add(btresultado);
        btresultado.setBounds(410, 5, 120, 29);

        add(jPanel3);
        jPanel3.setBounds(0, 417, 940, 42);

        jPanel5.setBackground(new java.awt.Color(160, 160, 246));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel5.setToolTipText("");
        jPanel5.setLayout(null);

        jProgressBar1.setForeground(java.awt.Color.blue);
        jProgressBar1.setMaximum(23);
        jProgressBar1.setStringPainted(true);
        jPanel5.add(jProgressBar1);
        jProgressBar1.setBounds(260, 10, 670, 22);

        texto.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        texto.setForeground(java.awt.Color.blue);
        texto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        texto.setToolTipText("");
        jPanel5.add(texto);
        texto.setBounds(10, 5, 250, 30);

        add(jPanel5);
        jPanel5.setBounds(0, 41, 940, 42);

        jPanel4.setBackground(new java.awt.Color(160, 160, 246));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel4.setLayout(null);

        titulo.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        titulo.setForeground(java.awt.Color.blue);
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        titulo.setText("Juego");
        titulo.setToolTipText("");
        jPanel4.add(titulo);
        titulo.setBounds(10, 5, 280, 30);

        add(jPanel4);
        jPanel4.setBounds(0, 0, 940, 42);
    }// </editor-fold>//GEN-END:initComponents
    
    protected void cargar_imagen_boton(JButton boton, int posicion, int width, int height, String type ) {
        javax.swing.ImageIcon fot3 = new javax.swing.ImageIcon(getClass().getResource("/images/"+this.pregunta.valor2[posicion]+"x"+this.pregunta.valor1[posicion]+type+".gif"));
        javax.swing.Icon icono3 = new javax.swing.ImageIcon(
                fot3.getImage().getScaledInstance(
                    width, 
                    height, 
                    Image.SCALE_DEFAULT)
                );
        boton.setIcon(icono3);    
    }
    
    protected int integer_random(int start, int end){
        Random random = new Random();
        if ( start > end ) {
            throw new IllegalArgumentException("Start cannot exceed End.");
        }
        long range = (long)end - (long)start + 1;
        long fraction = (long)(range * random.nextDouble());
        int number =  (int)(fraction + start); 
        return number;
    }
    
    protected int string_int(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch(NumberFormatException e){
        }
        return 0;
    }
    
    protected void pregunta_valores(int posicion) {
        if( this.etapas == 1 ) {
            this.pregunta.valor1[posicion] = 1;
            this.pregunta.valor2[posicion] = integer_random(1, 9);
            
        } else if( this.etapas == 2 ) {
            this.pregunta.valor1[posicion] = integer_random(2, 3);
            this.pregunta.valor2[posicion] = integer_random(1, 9);
            
        } else if( this.etapas == 3 ) {        
            this.pregunta.valor1[posicion] = integer_random(4, 5);
            this.pregunta.valor2[posicion] = integer_random(1, 9);
            
        } else if( this.etapas == 4 ) {        
            this.pregunta.valor1[posicion] = 6;
            this.pregunta.valor2[posicion] = integer_random(1, 9);
            
        } else if( this.etapas == 5 ) {
            this.pregunta.valor1[posicion] = 7;
            this.pregunta.valor2[posicion] = integer_random(1, 9);
            
        } else if( this.etapas == 6 ) {
            this.pregunta.valor1[posicion] = 8;
            this.pregunta.valor2[posicion] = integer_random(1, 9);
            
        } else if( this.etapas == 7 ) {
            this.pregunta.valor1[posicion] = 9;
            this.pregunta.valor2[posicion] = integer_random(1, 9);
            
        } else if( this.etapas == 8 ) {     
            this.pregunta.valor1[posicion] = integer_random(1, 9);
            this.pregunta.valor2[posicion] = integer_random(1, 9);            
        }        
        
        this.pregunta.resultado[posicion] = this.pregunta.valor1[posicion]*this.pregunta.valor2[posicion];        
    }
    
    protected void pregunta_iniciar(){
        this.pregunta = new Pregunta();
            
        this.indice++;          
        this.pregunta_crear();        
        this.pregunta_guardar();
    }
    
    protected void pregunta_guardar(){     
        this.preguntas.add(indice, pregunta);  
    }    
    
    protected void pregunta_aux_recuperar(){
        Pregunta aux_pregunta;     
        Iterator<Pregunta> iter=preguntas.iterator();
        int i = 0;
        while (iter.hasNext()){
            aux_pregunta = iter.next();
            ++i;             
        }     
    }
        
    protected void pregunta_recuperar(){        
        this.pregunta = this.preguntas.get(this.indice);
        
        this.texto.setText("");
        this.button3.setBackground(Color.white);
        this.button1.setBackground(Color.white);
        this.button2.setBackground(Color.white);  
        if( this.pregunta.respuesta == 1 ) {
            this.respuesta1();
        } else if ( this.pregunta.respuesta == 2 ) {
            this.respuesta2();            
        } else if ( this.pregunta.respuesta == 3 ) {
            this.respuesta3();            
        }
    }    
        
    protected void pregunta_crear() {
        this.btleft.setEnabled(false);
        
        this.pregunta.correcto = integer_random(0, 2);
       
        this.pregunta_valores(0);
        this.pregunta_valores(1);
        int continuar = 1;
        while( continuar == 1) {
            if( this.pregunta.valor1[1] == this.pregunta.valor1[0] && this.pregunta.valor2[1] == this.pregunta.valor2[0]  ) {
                this.pregunta_valores(1);
            } else  {
                continuar=0;
            }
        }
                
        this.pregunta_valores(2);
        continuar = 1;
        while( continuar == 1) {
            if( (this.pregunta.valor1[2] == this.pregunta.valor1[0] && this.pregunta.valor2[2] == this.pregunta.valor2[0]) ||
                (this.pregunta.valor1[2] == this.pregunta.valor1[1] && this.pregunta.valor2[2] == this.pregunta.valor2[1]) ) {
                this.pregunta_valores(2);
            } else  {
                continuar=0;
            }
        }
        
    }
        
    private void respuestas(int respuesta_numero) {
        if( respuesta_numero == 0) { 
            this.respuesta1();
        } else if( respuesta_numero == 1) { 
            this.respuesta2();
        } else if( respuesta_numero == 2) { 
            this.respuesta3();
        }
        //Si la respuesta es correcta se actulizan el frame, se espera 2 segundos 
        // y se avanza a la siguiente pregunta
        if(this.pregunta.correcto == respuesta_numero ) { 
            this.update(this.getGraphics());
            try {              
                this.esperar = 1;
                Thread.sleep(2000);        
                this.esperar = 0;
                
            } catch (InterruptedException ex) {
                Logger.getLogger(PanelBase.class.getName()).log(Level.SEVERE, null, ex);
            }   
            this.jProgressBar1.setValue(this.indice+1);             
            this.pregunta_avanzar();        
        }
    }
    
    private void respuesta1() {        
        this.button1.setBackground(Color.blue);
        
        if(this.button2.isEnabled()) {
            this.button2.setBackground(Color.white);
        }
        if(this.button3.isEnabled()) {
            this.button3.setBackground(Color.white);
        }
                    
        this.correccion_respuesta((this.pregunta.correcto == 0 )?1:0, 1, this.button1, this.lbrespuesta1);
    }

    private void respuesta2() {
        if(this.button1.isEnabled()) {
            this.button1.setBackground(Color.white);
        }
        this.button2.setBackground(Color.blue);
        if(this.button3.isEnabled()) {
            this.button3.setBackground(Color.white);
        }
        
        this.correccion_respuesta((this.pregunta.correcto == 1 )?1:0, 2, this.button2, this.lbrespuesta2);
    }    
    
    private void respuesta3() {
        if(this.button1.isEnabled()) {
            this.button1.setBackground(Color.white);
        }
        if(this.button2.isEnabled()) {
            this.button2.setBackground(Color.white);
        }       
        this.button3.setBackground(Color.blue);
        
        this.correccion_respuesta((this.pregunta.correcto == 2 )?1:0, 3, this.button3, this.lbrespuesta3);
    }   
    
    private void correccion_respuesta(int correcta, int indice_boton, JButton boton, JLabel lbrespuesta) {
        Pregunta aux_pregunta;
        aux_pregunta = this.preguntas.get(this.indice);
        
        if(correcta == 1 ) {
            this.texto.setText("Correcto   "+aux_pregunta.valor1[aux_pregunta.correcto]+" x "+aux_pregunta.valor2[aux_pregunta.correcto]+" = "+aux_pregunta.resultado[aux_pregunta.correcto]+" ");
            
            this.button1.setEnabled( (( indice_boton == 1 )?true:false) );
            this.button2.setEnabled( (( indice_boton == 2 )?true:false) );        
            this.button3.setEnabled( (( indice_boton == 3 )?true:false) );
                                    
            lbrespuesta.setVisible(true);        
            lbrespuesta.setBounds(90, 20, 50, 50);
            lbrespuesta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/correcto.png"))); // NOI18N

            try {         
                Player player = new Player(getClass().getResourceAsStream("/audios/correcto.mp3"));
                player.play();
                player.close();
            } catch (JavaLayerException e) {
            }
            
        } else {

            lbrespuesta.setVisible(true);        
            lbrespuesta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/incorrecto.png"))); // NOI18N

            boton.setBackground(Color.white);                
            boton.setEnabled(false);      
            
            try {         
                Player player = new Player(getClass().getResourceAsStream("/audios/incorrecto.mp3"));
                player.play();
                player.close();
            } catch (JavaLayerException e) {
            }
            
            this.texto.setText("Incorrecto");
        }    
    }
        
    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        this.pregunta.respuesta = 1;        
        this.respuestas(0);        
        this.resultado.set_preguntas(this.preguntas);
    }//GEN-LAST:event_button1ActionPerformed
    
    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        this.pregunta.respuesta = 2;
        this.respuestas(1);        
        this.resultado.set_preguntas(this.preguntas);
    }//GEN-LAST:event_button2ActionPerformed

    private void button3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button3ActionPerformed
        this.pregunta.respuesta = 3;
        this.respuestas(2);                
        this.resultado.set_preguntas(this.preguntas);  
    }//GEN-LAST:event_button3ActionPerformed

    protected void setTitulo() {
        this.titulo.setText("Pregunta "+(this.indice + 2)+" ");
    }
    
    private void pregunta_avanzar() {
        this.texto.setText("");
        this.button3.setBackground(Color.white);
        this.button1.setBackground(Color.white);
        this.button2.setBackground(Color.white);         
        
        this.lbrespuesta2.setVisible(false);
        this.lbrespuesta1.setVisible(false);        
        this.lbrespuesta3.setVisible(false);         
        
        if( this.etapas < 9 ) {
            if( this.contador == 3 ) {
                this.etapas++;
                this.contador = 1;
            } else {
                this.contador++;
            }
        }
        this.setTitulo();
        if( this.etapas == 8 && this.contador == 3) {
            this.button0.setEnabled(false);            
            this.button1.setEnabled(false);
            this.button2.setEnabled(false);            
            this.button3.setEnabled(false);
            
//            this.btright.setVisible(true);
//            this.btleft.setVisible(true);            
                        
            this.removeAll();
            Felicitacion felicitacion = new Felicitacion(this.jugador, this.preguntas);
            felicitacion.setBounds(130, 2, 680, 360);
            this.add(felicitacion);
            this.repaint();                        
            this.indice++;

        } else {     

            this.button0.setEnabled(true);            
            this.button1.setEnabled(true);
            this.button2.setEnabled(true);            
            this.button3.setEnabled(true);
            
            if (this.preguntas.size() == (this.indice+1)) {  //se crea una pregunta nueva
                this.pregunta_iniciar();
            } else {                                         //se recupera una pregunta
                this.indice++;
                this.pregunta_recuperar();
            }
            this.etapas_juego();
        }    
    }
    
    private void pregunta_retroceder() {
        this.texto.setText("");
        this.button3.setBackground(Color.white);
        this.button1.setBackground(Color.white);
        this.button2.setBackground(Color.white);        
        
        if( this.etapas > 0 ) {
            if( this.contador == 1 ) {
                this.etapas--;
                this.contador = 3;
            } else {
                this.contador--;
            }
        }
        
        this.setTitulo(); 
        this.indice--; 
        this.pregunta_recuperar();
        this.etapas_juego();
                
        if( this.indice == 0) { //si se retorna a la primera pregunta, no se puede retroceder mas
            this.btleft.setEnabled(false);
        } else {
            this.btleft.setEnabled(true);
        }
        
        this.btright.setEnabled(true);    
    }    
    
    private void btleftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btleftActionPerformed
        this.pregunta_retroceder();
    }//GEN-LAST:event_btleftActionPerformed
        
    private void btresultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btresultadoActionPerformed
        this.resultado.setVisible(true);
        this.resultado.set_preguntas(this.preguntas);
    }//GEN-LAST:event_btresultadoActionPerformed

    private void btrightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btrightActionPerformed
        this.pregunta_avanzar();
    }//GEN-LAST:event_btrightActionPerformed

    private void button0MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button0MouseEntered
        if( this.dificultad == 1  ) {
            this.cargar_imagen_boton(this.button0, this.pregunta.correcto, 220, 308, this.letra_juego);
        }
    }//GEN-LAST:event_button0MouseEntered

    private void button0MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button0MouseExited
        if( this.dificultad == 1  ) {
            this.cargar_imagen_boton(this.button0, this.pregunta.correcto, 220, 308, (  (this.letra_juego.equals("a"))?"b":"a") );
        }
    }//GEN-LAST:event_button0MouseExited

    public abstract void etapas_juego();
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton btleft;
    private javax.swing.JButton btresultado;
    protected javax.swing.JButton btright;
    protected javax.swing.JButton button0;
    protected javax.swing.JButton button1;
    protected javax.swing.JButton button2;
    protected javax.swing.JButton button3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JLabel lbrespuesta1;
    private javax.swing.JLabel lbrespuesta2;
    private javax.swing.JLabel lbrespuesta3;
    protected javax.swing.JLabel texto;
    protected javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
