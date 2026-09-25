package multiplicacion;


import java.awt.Color;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author mario
 */
public class Inicial extends javax.swing.JFrame {

    private int comenzar;
    
    public Inicial() {
        initComponents();
        this.setTitle("Multiplicación");

        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.setBounds( 70, 100, 960, 515);
        this.setVisible(true);    
        
        this.comenzar = 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btInicio = new javax.swing.JButton();
        btCreditos = new javax.swing.JButton();
        PanelBase = new javax.swing.JPanel();
        PInicio = new javax.swing.JPanel();
        txtnombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbayuda = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cbjuego = new javax.swing.JComboBox();
        btjugar = new javax.swing.JButton();
        javax.swing.ImageIcon imagen2 = new javax.swing.ImageIcon(getClass().getResource("/images/5x2a.gif"));
        javax.swing.Icon icono2 = new javax.swing.ImageIcon(
            imagen2.getImage().getScaledInstance(
                120,
                168,
                Image.SCALE_DEFAULT)
        );
        carta2 = new javax.swing.JButton();
        carta2.setIcon(icono2);
        javax.swing.ImageIcon imagen1 = new javax.swing.ImageIcon(getClass().getResource("/images/2x3b.gif"));
        javax.swing.Icon icono1 = new javax.swing.ImageIcon(
            imagen1.getImage().getScaledInstance(
                120,
                168,
                Image.SCALE_DEFAULT)
        );
        carta1 = new javax.swing.JButton();
        carta1.setIcon(icono1);
        javax.swing.ImageIcon imagen3 = new javax.swing.ImageIcon(getClass().getResource("/images/2x7a.gif"));
        javax.swing.Icon icono3 = new javax.swing.ImageIcon(
            imagen3.getImage().getScaledInstance(
                120,
                168,
                Image.SCALE_DEFAULT)
        );
        carta3 = new javax.swing.JButton();
        carta3.setIcon(icono3);
        javax.swing.ImageIcon imagen4 = new javax.swing.ImageIcon(getClass().getResource("/images/3x2b.gif"));
        javax.swing.Icon icono4 = new javax.swing.ImageIcon(
            imagen4.getImage().getScaledInstance(
                120,
                168,
                Image.SCALE_DEFAULT)
        );
        carta4 = new javax.swing.JButton();
        carta4.setIcon(icono4);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(160, 160, 246));
        setMaximumSize(new java.awt.Dimension(960, 515));
        setMinimumSize(new java.awt.Dimension(960, 515));
        setPreferredSize(new java.awt.Dimension(960, 515));
        setResizable(false);
        setSize(new java.awt.Dimension(960, 515));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel1.setBackground(new java.awt.Color(160, 160, 246));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(111, 111, 234));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        jPanel2.setMaximumSize(new java.awt.Dimension(940, 31));
        jPanel2.setMinimumSize(new java.awt.Dimension(940, 31));
        jPanel2.setPreferredSize(new java.awt.Dimension(940, 31));
        jPanel2.setLayout(null);

        btInicio.setFont(new java.awt.Font("DejaVu Sans", 1, 15)); // NOI18N
        btInicio.setForeground(java.awt.Color.black);
        btInicio.setText("Inicio");
        btInicio.setMaximumSize(new java.awt.Dimension(67, 26));
        btInicio.setMinimumSize(new java.awt.Dimension(67, 26));
        btInicio.setPreferredSize(new java.awt.Dimension(67, 26));
        btInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInicioActionPerformed(evt);
            }
        });
        jPanel2.add(btInicio);
        btInicio.setBounds(10, 3, 80, 26);

        btCreditos.setFont(new java.awt.Font("DejaVu Sans", 1, 15)); // NOI18N
        btCreditos.setForeground(java.awt.Color.black);
        btCreditos.setText("Créditos");
        btCreditos.setToolTipText("");
        btCreditos.setMaximumSize(new java.awt.Dimension(67, 26));
        btCreditos.setMinimumSize(new java.awt.Dimension(67, 26));
        btCreditos.setPreferredSize(new java.awt.Dimension(67, 26));
        btCreditos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreditosActionPerformed(evt);
            }
        });
        jPanel2.add(btCreditos);
        btCreditos.setBounds(820, 3, 110, 26);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 940, -1));

        PanelBase.setBackground(new java.awt.Color(160, 160, 246));
        PanelBase.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        PanelBase.setMaximumSize(new java.awt.Dimension(960, 470));
        PanelBase.setMinimumSize(new java.awt.Dimension(960, 470));
        PanelBase.setName(""); // NOI18N
        PanelBase.setPreferredSize(new java.awt.Dimension(960, 470));
        PanelBase.setLayout(null);

        PInicio.setBackground(new java.awt.Color(160, 160, 246));
        PInicio.setBorder(null);
        PInicio.setMaximumSize(new java.awt.Dimension(690, 360));
        PInicio.setMinimumSize(new java.awt.Dimension(690, 360));
        PInicio.setPreferredSize(new java.awt.Dimension(690, 360));
        PInicio.setLayout(null);

        txtnombre.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        txtnombre.setForeground(java.awt.Color.blue);
        PInicio.add(txtnombre);
        txtnombre.setBounds(350, 70, 240, 32);

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel2.setForeground(java.awt.Color.blue);
        jLabel2.setText("Elige el tipo de juego");
        PInicio.add(jLabel2);
        jLabel2.setBounds(120, 140, 220, 25);

        jLabel4.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel4.setForeground(java.awt.Color.blue);
        jLabel4.setText("Elije el nivel de dificultad");
        PInicio.add(jLabel4);
        jLabel4.setBounds(80, 210, 260, 25);

        cbayuda.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        cbayuda.setForeground(java.awt.Color.blue);
        cbayuda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fácil", "Intermedio", "Avanzado" }));
        PInicio.add(cbayuda);
        cbayuda.setBounds(350, 210, 240, 32);

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        jLabel5.setForeground(java.awt.Color.blue);
        jLabel5.setText("Ingresa tu nombre");
        PInicio.add(jLabel5);
        jLabel5.setBounds(140, 70, 200, 25);

        cbjuego.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        cbjuego.setForeground(java.awt.Color.blue);
        cbjuego.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Buscar Resultado", "Buscar Multiplicación", "Combinado" }));
        PInicio.add(cbjuego);
        cbjuego.setBounds(350, 140, 240, 32);

        PanelBase.add(PInicio);
        PInicio.setBounds(130, 2, 680, 360);

        btjugar.setFont(new java.awt.Font("DejaVu Sans", 1, 18)); // NOI18N
        btjugar.setForeground(java.awt.Color.black);
        btjugar.setText("Jugar");
        btjugar.setToolTipText("");
        btjugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btjugarActionPerformed(evt);
            }
        });
        PanelBase.add(btjugar);
        btjugar.setBounds(410, 380, 150, 34);

        carta2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                carta2MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                carta2MouseEntered(evt);
            }
        });
        PanelBase.add(carta2);
        carta2.setBounds(10, 280, 120, 168);

        carta1.setToolTipText("");
        carta1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                carta1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                carta1MouseEntered(evt);
            }
        });
        PanelBase.add(carta1);
        carta1.setBounds(10, 10, 120, 168);

        carta3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                carta3MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                carta3MouseEntered(evt);
            }
        });
        PanelBase.add(carta3);
        carta3.setBounds(810, 10, 120, 168);

        carta4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                carta4MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                carta4MouseEntered(evt);
            }
        });
        PanelBase.add(carta4);
        carta4.setBounds(810, 280, 120, 168);

        jPanel1.add(PanelBase, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 41, 940, 460));
        PanelBase.getAccessibleContext().setAccessibleName("");

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInicioActionPerformed
        this.comenzar = 0;
        
        this.PanelBase.removeAll();
        this.PanelBase.add(this.PInicio);
        this.PInicio.setBounds(130, 2, 680, 360);
        
        this.btjugar.setText("Jugar");
        this.PanelBase.add(this.btjugar);        
        this.agregarCartas();        
        this.PanelBase.repaint();
    }//GEN-LAST:event_btInicioActionPerformed

    private void btCreditosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreditosActionPerformed
        Creditos creditos = new Creditos();
        this.PanelBase.removeAll();
        this.PanelBase.add(creditos);
        creditos.setBounds(130, 2, 680, 360);
        
        this.agregarCartas();          
        this.PanelBase.repaint();        
    }//GEN-LAST:event_btCreditosActionPerformed

    private void btjugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btjugarActionPerformed
        String nombre = this.txtnombre.getText(); 
        int tipo_juego = this.cbjuego.getSelectedIndex();        
        int dificultad = this.cbayuda.getSelectedIndex();

        if( !nombre.isEmpty() ) { 
            this.PanelBase.removeAll();
            
            if( this.comenzar == 1 ) {
                PanelBase Juego;
                if( tipo_juego == 0 ) {
                    Juego = new JuegoA(nombre, dificultad);   // resultado
                    Juego.setBounds(0, 0, 960, 470);
                    this.PanelBase.add(Juego);
                    
                } else if( tipo_juego == 1 ) {
                    Juego = new JuegoB(nombre, dificultad);   //multiplicacion  
                    Juego.setBounds(0, 0, 960, 470);
                    this.PanelBase.add(Juego); 
                    
                } else if ( tipo_juego == 2 ) {
                    Juego = new JuegoC(nombre, dificultad);   //combinado 
                    Juego.setBounds(0, 0, 960, 470);
                    this.PanelBase.add(Juego);                
                }
                
            } else {   
                Ayuda PanelAyuda = new Ayuda(nombre, ""+this.cbjuego.getSelectedItem(), ""+this.cbayuda.getSelectedItem());    
                this.PanelBase.add(PanelAyuda);
                PanelAyuda.setBounds(130, 2, 680, 360);

                this.btjugar.setText("Comenzar");
                this.comenzar = 1;        

                this.PanelBase.add(this.btjugar);
                this.agregarCartas();
            }
            this.PanelBase.repaint();
            
        } else {
            this.txtnombre.setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.red));
        }    
    }//GEN-LAST:event_btjugarActionPerformed

    private void carta1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carta1MouseEntered
        this.cargar_imagen_boton(this.carta1, 120, 168, "2x3a");
    }//GEN-LAST:event_carta1MouseEntered

    private void carta1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carta1MouseExited
        this.cargar_imagen_boton(this.carta1, 120, 168, "2x3b");
    }//GEN-LAST:event_carta1MouseExited

    private void carta2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carta2MouseEntered
        this.cargar_imagen_boton(this.carta2, 120, 168, "5x2b");
    }//GEN-LAST:event_carta2MouseEntered

    private void carta2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carta2MouseExited
        this.cargar_imagen_boton(this.carta2, 120, 168, "5x2a");
    }//GEN-LAST:event_carta2MouseExited

    private void carta3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carta3MouseEntered
        this.cargar_imagen_boton(this.carta3, 120, 168, "2x7b");
    }//GEN-LAST:event_carta3MouseEntered

    private void carta3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carta3MouseExited
        this.cargar_imagen_boton(this.carta3, 120, 168, "2x7a");
    }//GEN-LAST:event_carta3MouseExited

    private void carta4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carta4MouseEntered
        this.cargar_imagen_boton(this.carta4, 120, 168, "3x2a");
    }//GEN-LAST:event_carta4MouseEntered

    private void carta4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carta4MouseExited
        this.cargar_imagen_boton(this.carta4, 120, 168, "3x2b");
    }//GEN-LAST:event_carta4MouseExited

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
    
    private void agregarCartas() {
        this.PanelBase.add(this.carta1);
        this.PanelBase.add(this.carta2);
        this.PanelBase.add(this.carta3);
        this.PanelBase.add(this.carta4);
    }
      
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PInicio;
    private javax.swing.JPanel PanelBase;
    private javax.swing.JButton btCreditos;
    private javax.swing.JButton btInicio;
    private javax.swing.JButton btjugar;
    private javax.swing.JButton carta1;
    private javax.swing.JButton carta2;
    private javax.swing.JButton carta3;
    private javax.swing.JButton carta4;
    private javax.swing.JComboBox cbayuda;
    private javax.swing.JComboBox cbjuego;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}