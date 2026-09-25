package multiplicacion;

import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JFrame;

/**
 *
 * @author mario
 */
public class Resultados extends javax.swing.JFrame {

    public Resultados() {
        initComponents();
        this.setTitle("Resultados");
        this.setBounds(1031, 100, 200, 519);
                
        this.setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        this.setVisible(false); 
        this.jtextarea.setEditable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jtextarea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(190, 250));
        setMinimumSize(new java.awt.Dimension(190, 250));
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jtextarea.setBackground(new java.awt.Color(160, 160, 246));
        jtextarea.setColumns(20);
        jtextarea.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jtextarea.setForeground(java.awt.Color.blue);
        jtextarea.setRows(5);
        jScrollPane2.setViewportView(jtextarea);

        getContentPane().add(jScrollPane2);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jtextarea;
    // End of variables declaration//GEN-END:variables

    public void set_preguntas(LinkedList<Pregunta> preguntas) { 
        this.repaint();
        
        this.jtextarea.setText("");
        Pregunta aux_pregunta;     
        
        Iterator<Pregunta> iter=preguntas.iterator();
        int i = 1;
        
        String pregunta="";
        String resultado="";
        String texto; 
        
        while (iter.hasNext()){
            aux_pregunta = iter.next();

            if( aux_pregunta.respuesta == 0) {
                texto = "Sin respuesta";
            } else if ( (aux_pregunta.correcto+1) == aux_pregunta.respuesta ) {
                texto = "Correcta";
            } else {
                texto = "Incorrecta";
            }
            
            if(aux_pregunta.correcto == 0) {
                pregunta = (aux_pregunta.valor1[0])+"x"+(aux_pregunta.valor2[0]);
                resultado = aux_pregunta.resultado[0]+"";

            } else if(aux_pregunta.correcto == 1) {
                pregunta = (aux_pregunta.valor1[1])+"x"+(aux_pregunta.valor2[1]);
                resultado = aux_pregunta.resultado[1]+"";
                
            } else if(aux_pregunta.correcto == 2) {
                pregunta = (aux_pregunta.valor1[2])+"x"+(aux_pregunta.valor2[2]);
                resultado = aux_pregunta.resultado[2]+"";
                
            }
            
            this.jtextarea.append("Pregunta "+(i)+"\n"+pregunta+"="+((texto.equals("Correcta"))?resultado:"??")+"  "+texto+"\n");                  
            ++i;
        }
    }
}