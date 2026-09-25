package multiplicacion;

import java.awt.Image;

/**
 *
 * @author mario
 */
public class Ayuda extends javax.swing.JPanel {

    protected String jugador = "";
    protected int tipo_juego = 0;
    protected String letra_juego = "";
    protected String opcion = "";    
    protected String ayuda = "";
    
    public Ayuda(String nombre, String opcion, String ayuda) {
        initComponents();
        this.setSize(960, 470);
        this.setBackground(new java.awt.Color(160, 160, 246));
        
        this.jugador = nombre;    
        this.tipo_juego = 1;
        this.ayuda = ayuda;   
        
        nombre = (""+nombre.charAt(0)).toUpperCase()+nombre.substring(1, nombre.length());

        this.labelAyuda1.setText("<html>"+nombre+", el tipo de juego que seleccionaste es "+opcion+", con un nivel de dificultad "+ayuda+"</html>");

        this.labelAyuda.setText("<html>"+nombre+", observa la primera carta, y luego haz clic en la carta que corresponde a su parte de atrás</html>");
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
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

        setBackground(new java.awt.Color(160, 160, 246));
        setBorder(null);
        setToolTipText("");
        setMaximumSize(new java.awt.Dimension(680, 360));
        setMinimumSize(new java.awt.Dimension(680, 360));
        setPreferredSize(new java.awt.Dimension(680, 360));
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel1.setForeground(java.awt.Color.black);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Instrucciones");
        jLabel1.setToolTipText("");
        add(jLabel1);
        jLabel1.setBounds(0, 10, 690, 30);

        jlabel3.setToolTipText("");
        jlabel3.setAutoscrolls(true);
        jlabel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        add(jlabel3);
        jlabel3.setBounds(30, 130, 200, 223);

        labelAyuda.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        labelAyuda.setForeground(java.awt.Color.blue);
        labelAyuda.setText("<html>Observa la primera carta, y luego haz clic en la carta que corresponde a su parte de atrás</html>");
        labelAyuda.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(labelAyuda);
        labelAyuda.setBounds(235, 190, 370, 110);

        labelAyuda1.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        labelAyuda1.setForeground(java.awt.Color.blue);
        labelAyuda1.setText("<html>Observa la primera carta, y luego haz clic en la carta que corresponde a su parte de atrás</html>");
        labelAyuda1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(labelAyuda1);
        labelAyuda1.setBounds(80, 60, 540, 60);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jlabel3;
    private javax.swing.JLabel labelAyuda;
    private javax.swing.JLabel labelAyuda1;
    // End of variables declaration//GEN-END:variables

}