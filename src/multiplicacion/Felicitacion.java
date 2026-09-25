package multiplicacion;

import java.awt.Image;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author mario
 */
public final class Felicitacion extends javax.swing.JPanel {

    protected String jugador = "";
    protected int indice = 0;
    protected LinkedList<Pregunta> preguntas;
    
    public Felicitacion(String nombre, LinkedList<Pregunta> preguntas) {
        initComponents();
        this.setSize(960, 470);
        this.setBackground(new java.awt.Color(160, 160, 246));
        
        this.preguntas = preguntas;
        this.jugador = nombre;    
        
        this.labelAyuda1.setText("¡¡ Felicitaciones  "+nombre+" !!");  
        
        mostrar_cartas();
    }

    public void mostrar_cartas() {
        Pregunta pregunta;
        if( this.indice < this.preguntas.size() ) {
            pregunta = this.preguntas.get(this.indice);
            this.cargar_imagen_boton(this.carta1, 120, 168, pregunta.valor2[pregunta.correcto]+"x"+pregunta.valor1[pregunta.correcto]+"a");
            this.cargar_imagen_boton(this.carta3, 120, 168, pregunta.valor2[pregunta.correcto]+"x"+pregunta.valor1[pregunta.correcto]+"b");            
            this.indice++;
        } else {
            this.indice = 0;
        }    
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.ImageIcon fot5 = new javax.swing.ImageIcon(getClass().getResource("/images/personaje.png"));
        javax.swing.Icon icono5 = new javax.swing.ImageIcon(
            fot5.getImage().getScaledInstance(
                200,
                223,
                Image.SCALE_DEFAULT)
        );
        jlabel3 = new javax.swing.JLabel(icono5);
        labelAyuda = new javax.swing.JLabel();
        labelAyuda1 = new javax.swing.JLabel();
        javax.swing.ImageIcon imagen1 = new javax.swing.ImageIcon(getClass().getResource("/images/2x3b.gif"));
        javax.swing.Icon icono1 = new javax.swing.ImageIcon(
            imagen1.getImage().getScaledInstance(
                120,
                168,
                Image.SCALE_DEFAULT)
        );
        carta1 = new javax.swing.JButton();
        carta1.setIcon(icono1);
        javax.swing.ImageIcon imagen2 = new javax.swing.ImageIcon(getClass().getResource("/images/5x2a.gif"));
        javax.swing.Icon icono2 = new javax.swing.ImageIcon(
            imagen2.getImage().getScaledInstance(
                120,
                168,
                Image.SCALE_DEFAULT)
        );
        carta3 = new javax.swing.JButton();
        carta3.setIcon(icono2);

        setBackground(new java.awt.Color(160, 160, 246));
        setBorder(null);
        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(680, 360));
        setMinimumSize(new java.awt.Dimension(680, 360));
        setPreferredSize(new java.awt.Dimension(680, 360));
        setLayout(null);

        jlabel3.setToolTipText("");
        jlabel3.setAutoscrolls(true);
        jlabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        add(jlabel3);
        jlabel3.setBounds(30, 130, 200, 223);

        labelAyuda.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        labelAyuda.setForeground(java.awt.Color.blue);
        labelAyuda.setText("¡¡ Lo hiciste fabuloso !!");
        labelAyuda.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(labelAyuda);
        labelAyuda.setBounds(230, 140, 280, 40);

        labelAyuda1.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        labelAyuda1.setForeground(java.awt.Color.blue);
        labelAyuda1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAyuda1.setText("¡¡ Felicitaciones  !!");
        labelAyuda1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(labelAyuda1);
        labelAyuda1.setBounds(0, 40, 680, 60);

        carta1.setToolTipText("");
        carta1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                carta1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                carta1MouseEntered(evt);
            }
        });
        add(carta1);
        carta1.setBounds(360, 180, 120, 168);

        carta3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                carta3MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                carta3MouseEntered(evt);
            }
        });
        add(carta3);
        carta3.setBounds(550, 180, 120, 168);
    }// </editor-fold>//GEN-END:initComponents

    private void carta1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carta1MouseEntered
        mostrar_cartas();
    }//GEN-LAST:event_carta1MouseEntered

    private void carta3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carta3MouseExited
        mostrar_cartas();
    }//GEN-LAST:event_carta3MouseExited

    private void carta3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carta3MouseEntered
        mostrar_cartas();
    }//GEN-LAST:event_carta3MouseEntered

    private void carta1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carta1MouseExited
        mostrar_cartas();
    }//GEN-LAST:event_carta1MouseExited

    
    protected void cargar_imagen_boton(JButton boton, int width, int height, String carta ) {
        javax.swing.ImageIcon imagen = new javax.swing.ImageIcon(getClass().getResource("/images/"+carta+".gif"));
        javax.swing.Icon icono3 = new javax.swing.ImageIcon(
                imagen.getImage().getScaledInstance(
                    width, 
                    height, 
                    Image.SCALE_DEFAULT)
                );
        boton.setIcon(icono3);    
    }     
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton carta1;
    private javax.swing.JButton carta2;
    private javax.swing.JButton carta3;
    private javax.swing.JLabel jlabel3;
    private javax.swing.JLabel labelAyuda;
    private javax.swing.JLabel labelAyuda1;
    // End of variables declaration//GEN-END:variables

}