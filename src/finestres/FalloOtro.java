package finestres;

import java.sql.*;
import clases.Conexion;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/*
 * @author aNDREUET
 */
public class FalloOtro extends javax.swing.JFrame {

    String user = "";
    int IDuser = 0, IDequipo = 0;
    int IDequipo_update = 0;
    // model interactuará con mi tabla (j_Table_equipos) creada en el form
    DefaultTableModel model = new DefaultTableModel();

    /*
     * Constructor del form FalloHardware
     */
    public FalloOtro() {
        initComponents();
        user = Interface.usuario;
        IDuser = Interface.IDuser;

        setSize(700, 370);
        setResizable(false);
        setTitle("Rol: Cliente - Sesión de " + user);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Fondo de pantalla de mi aplicación
        ImageIcon wallpaper = new ImageIcon("src/imatges/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(),
                jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Wallpaper.setIcon(icono);
        this.repaint();

        //
        // relleno los campos de nombre y los títulos del formulario
        //
        try {
            Connection con = Conexion.conector();
            String sql = "select * from Usuarios where idUsuario = '" + IDuser + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                setTitle("Información del usuario " + rs.getString("nombre") + " " + rs.getString("apellidos") + " - Sesión de " + user);

                txt_nombre.setText(rs.getString("nombre") + " " + rs.getString("apellidos"));
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Error al cargar el Cliente " + e);
            JOptionPane.showMessageDialog(null, "Error al mostrar el Cliente, contacte con el Administrador");
        }

    }

    //
    //  Coloco el icono que aparecerá en la barra de tareas
    //
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("imatges/logohpp.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_Titulo = new javax.swing.JLabel();
        jLabel_Nombre = new javax.swing.JLabel();
        jLabel_Nombre8 = new javax.swing.JLabel();
        jLabel_Nombre9 = new javax.swing.JLabel();
        jLabel_Nombre10 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane_Descripcion = new javax.swing.JTextPane();
        jButton_CrearIncidencia = new javax.swing.JButton();
        cmb_prioridad = new javax.swing.JComboBox<>();
        cmb_estado = new javax.swing.JComboBox<>();
        jLabel_footer = new javax.swing.JLabel();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(700, 370));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_Titulo.setForeground(java.awt.Color.white);
        jLabel_Titulo.setText("Reportar otro tipo de problema");
        getContentPane().add(jLabel_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        jLabel_Nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Nombre.setForeground(java.awt.Color.white);
        jLabel_Nombre.setText("Nombre:");
        getContentPane().add(jLabel_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, -1, -1));

        jLabel_Nombre8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Nombre8.setForeground(java.awt.Color.white);
        jLabel_Nombre8.setText("Descripción problema:");
        getContentPane().add(jLabel_Nombre8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        jLabel_Nombre9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Nombre9.setForeground(java.awt.Color.white);
        jLabel_Nombre9.setText("Prioridad:");
        getContentPane().add(jLabel_Nombre9, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, -1, -1));

        jLabel_Nombre10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Nombre10.setForeground(java.awt.Color.white);
        jLabel_Nombre10.setText("Estado:");
        getContentPane().add(jLabel_Nombre10, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, -1, -1));

        txt_nombre.setBackground(new java.awt.Color(153, 153, 255));
        txt_nombre.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_nombre.setForeground(java.awt.Color.white);
        txt_nombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_nombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_nombre.setEnabled(false);
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 250, -1));

        jScrollPane1.setViewportView(jTextPane_Descripcion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 500, 80));

        jButton_CrearIncidencia.setBackground(new java.awt.Color(153, 153, 255));
        jButton_CrearIncidencia.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_CrearIncidencia.setForeground(java.awt.Color.white);
        jButton_CrearIncidencia.setText("Crear Incidencia");
        jButton_CrearIncidencia.setBorder(null);
        jButton_CrearIncidencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CrearIncidenciaActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_CrearIncidencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 240, 210, 35));

        cmb_prioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baja", "Media", "Alta", "Grave", "Critica" }));
        cmb_prioridad.setEnabled(false);
        getContentPane().add(cmb_prioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 80, -1));

        cmb_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Inicio", "Asignado", "En proceso", "Finalizado" }));
        cmb_estado.setEnabled(false);
        getContentPane().add(cmb_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, 100, -1));

        jLabel_footer.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_footer.setText("Andreu Garcia Coll - UIB 2020");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, -1, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_CrearIncidenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CrearIncidenciaActionPerformed

        String descripcion;
        Timestamp fecha = new Timestamp(new java.util.Date().getTime());

        // compruebo que no ha dejado el campo vacío
        descripcion = jTextPane_Descripcion.getText().trim();

        if (!descripcion.equals("")) {

            descripcion = "Otro. \n" + jTextPane_Descripcion.getText();
            // creo un nuevo incidente Tabla Incidentes
            try {
                Connection con = Conexion.conector();
                String sql = "insert into Incidentes (fecha_crea, descripcion, Usuarios_idUsuario, tipos_idTipo) values (?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setTimestamp(1, fecha); // fecha creación incidente
                pst.setString(2, descripcion); // descripción del problema
                pst.setInt(3, IDuser); // usuario que crea el incidente
                pst.setInt(4, 3); // Incidente de tipo Otros Problemas

                pst.executeUpdate();
                con.close();
            } catch (SQLException e) {
                System.err.println("Error al crear incidente Otros " + e);
                JOptionPane.showMessageDialog(null, "Error al crear incidente Otros, contacte con el administrador");
            }

            // creo la intervención asociada a este incidente
            // como primera intervención es la de creación
            try {
                Connection con2 = Conexion.conector();
                String sql2 = "insert into Intervenciones (descripcion, fecha_intervencion, Usuarios_idUsuario, Incidentes_idIncidente) "
                        + "values (?,?,?,(select MAX(idIncidente) from Incidentes))";
                PreparedStatement pst2 = con2.prepareStatement(sql2);

                pst2.setString(1, "Inicio. Reporte Otros Problemas"); // descripción del problema
                pst2.setTimestamp(2, fecha); // fecha creación incidente
                // técnico al que se le asigna el incidente
                // en la creación del incidente al no determinarse el problema
                // el incidente se asigna al super usuario (idUsuario = 1)
                pst2.setInt(3, 1); // usuario que se asigna el incidente

                pst2.executeUpdate();
                con2.close();
            } catch (SQLException e) {
                System.err.println("Error al crear intervención Otros " + e);
                JOptionPane.showMessageDialog(null, "Error al crear intervención Otros, contacte con el administrador");
            }

            // inicialmente la intervención evoluciona al estado Inicio mientras 
            // no se asigne (por un administrador) a otro estado
            // por lo que aquí creo el primer estado del incidente
            try {
                Connection con3 = Conexion.conector();
                String sql3 = "insert into Estados (estado, descripcion, fecha_evolucion, Intervenciones_idIntervencion) "
                        + "values (?,?,?,(select MAX(idIntervencion) from Intervenciones))";
                PreparedStatement pst3 = con3.prepareStatement(sql3);

                pst3.setString(1, "Inicio"); // estado "Inicio" del Incidente
                pst3.setString(2, "Estado Inicial de la Incidencia de Otros Problemas"); // descripción del problema
                pst3.setTimestamp(3, fecha); // fecha evolución estado "Inici"

                pst3.executeUpdate();
                con3.close();
            } catch (SQLException e) {
                System.err.println("Error al crear estado Otros " + e);
                JOptionPane.showMessageDialog(null, "Error al crear estado Otros, contacte con el administrador");
            }

            // inicialmente la intervención se escala a un Nivel Básico 
            // por lo que aquí defino el primer nivel del incidente
            try {
                Connection con4 = Conexion.conector();
                String sql4 = "insert into Niveles (nivel, descripcion, fecha_escalada, Intervenciones_idIntervencion) "
                        + "values (?,?,?,(select MAX(idIntervencion) from Intervenciones))";
                PreparedStatement pst4 = con4.prepareStatement(sql4);

                pst4.setString(1, "Basico"); // nivel "Básicoc" del Incidente
                pst4.setString(2, "Nivel Básico de la Incidencia de Otros Problemas"); // descripción del problema
                pst4.setTimestamp(3, fecha); // fecha evolución estado "Inici"

                pst4.executeUpdate();
                con4.close();
            } catch (SQLException e) {
                System.err.println("Error al crear nivel Otros " + e);
                JOptionPane.showMessageDialog(null, "Error al crear nivel Otros, contacte con el administrador");
            }

            // por último tenemos las prioridades.
            // inicialmente la intervención se establece a una prioridad Baja
            // por lo que aquí defino la prioridad inicial del incidente
            try {
                Connection con5 = Conexion.conector();
                String sql5 = "insert into Prioridades (prioridad, descripcion, fecha_establece, Intervenciones_idIntervencion) "
                        + "values (?,?,?,(select MAX(idIntervencion) from Intervenciones))";
                PreparedStatement pst5 = con5.prepareStatement(sql5);

                pst5.setString(1, "Baja"); // Prioridad "Baja" del Incidente
                pst5.setString(2, "Prioridad Baja de la Incidencia de Otros Problemas"); // descripción del problema
                pst5.setTimestamp(3, fecha); // fecha evolución estado "Inici"

                pst5.executeUpdate();
                con5.close();
            } catch (SQLException e) {
                System.err.println("Error al crear prioridad Otros " + e);
                JOptionPane.showMessageDialog(null, "Error al crear prioridad Otros, contacte con el administrador");
            }

            JOptionPane.showMessageDialog(null, "Incidencia creada correctamente");
            this.dispose(); // destruímos ventana
        } else {
            jScrollPane1.setBackground(Color.red);
            //jTextPane_Descripcion.setBackground(Color.red);
            JOptionPane.showMessageDialog(null, "Debes describir el problema");
        }


    }//GEN-LAST:event_jButton_CrearIncidenciaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FalloOtro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FalloOtro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FalloOtro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FalloOtro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FalloOtro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_estado;
    private javax.swing.JComboBox<String> cmb_prioridad;
    private javax.swing.JButton jButton_CrearIncidencia;
    private javax.swing.JLabel jLabel_Nombre;
    private javax.swing.JLabel jLabel_Nombre10;
    private javax.swing.JLabel jLabel_Nombre8;
    private javax.swing.JLabel jLabel_Nombre9;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane_Descripcion;
    private javax.swing.JTextField txt_nombre;
    // End of variables declaration//GEN-END:variables
}
