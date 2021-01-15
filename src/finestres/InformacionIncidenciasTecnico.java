package finestres;

import java.sql.*;
import clases.Conexion;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/*
 * @author aNDREUET
 */
public class InformacionIncidenciasTecnico extends javax.swing.JFrame {

    int IDincidente = 0, IDuser = 0;
    // flag para comprobar si el incidente ya está finalizado y actuar en consecuencia
    int acabado = 0;
    String user = "";
    // declaro globalmente el objeto de la clase DefaultTableModel
    // para poder utilizarlo en cualquier parte de nuestra clase
    // model es la que nos va a permitir poder general el click en la tabla
    // y establecer la interacción con los datos que se muestren en la tabla
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Constructor del form InformacionIncidenciasAdmin
     */
    public InformacionIncidenciasTecnico() {
        initComponents();
        user = Interface.usuario;
        IDuser = Interface.IDuser;
        IDincidente = GestionarIncidenciasTecnico.IDincidente_update;

        DefaultTableModel model = new DefaultTableModel();

        setSize(1000, 650);
        setResizable(false);  // no se modificar el tamaño del interfaz
        setLocationRelativeTo(null); // centrar la interfaz al ejecutar

        // evita que se cierre el programa cuando cerramos esta interfaz
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Fondo de pantalla de mi aplicación
        ImageIcon wallpaper = new ImageIcon("src/imatges/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(),
                jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Wallpaper.setIcon(icono);
        this.repaint();

        // Busco la Incidencia que tengo que modificar como técnico
        // ya sea reparar, o finalizar
        try {
            Connection con = Conexion.conector();

            String sql = "SELECT idIncidente, nombre, apellidos, fecha_crea, INC.descripcion, tipo, estado, ";
            sql += "prioridad, nivel, idIntervencion, INTER.descripcion, INTER.fecha_intervencion, INTER.Usuarios_idUsuario ";
            sql += "FROM incidentes INC ";
            sql += "INNER JOIN Usuarios USU ON USU.idUsuario = INC.Usuarios_idUsuario ";
            sql += "INNER JOIN intervenciones INTER ON INTER.Incidentes_idIncidente = INC.idIncidente ";
            sql += "INNER JOIN estados EST ON EST.Intervenciones_idIntervencion = INTER.idIntervencion ";
            sql += "INNER JOIN niveles NIV ON NIV.Intervenciones_idIntervencion = INTER.idIntervencion ";
            sql += "INNER JOIN prioridades PRI ON PRI.Intervenciones_idIntervencion = INTER.idIntervencion ";
            sql += "INNER JOIN tipos TIP ON TIP.idTipo = INC.tipos_idTipo ";
            sql += "WHERE idIncidente = " + IDincidente + " ";
            sql += "ORDER BY idIntervencion DESC";

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            //
            // Creo la tabla de intervenciones
            //
            jTable_intervenciones = new JTable(model);
            // creo el contenedor y hago visible la tabla dentro de él
            jScrollPane_intervenciones.setViewportView(jTable_intervenciones);
            // definimos las columnas que estarán en el jScrollPane
            model.addColumn("Estado");
            model.addColumn("Fecha");
            model.addColumn("Descripción");
            model.addColumn("Nivel");
            model.addColumn("Prioridad");
            model.addColumn("ID Técnico");

            while (rs.next()) {
                /*
                        Al estar ordenado desde la intervención más nueva a la más antigua
                        relleno campos del formulario y miro si es una incidencia finalizada
                 */
                if (rs.getRow() == 1) {

                    if (rs.getString("estado").toString().equals("Finalizado")) {
                        acabado = 1;
                    }
                    txt_nombre.setText(rs.getString("nombre") + " " + rs.getString("apellidos"));
                    txt_idIncidente.setText(Integer.toString(IDincidente));
                    txt_fechaincidente.setText(rs.getTimestamp("fecha_crea").toString());
                    txt_nivel.setText(rs.getString("nivel"));
                    txt_prioridad.setText(rs.getString("prioridad"));
                    txt_tipo.setText(rs.getString("tipo"));
                    jTextPane_descripcion.setText(rs.getString("INC.descripcion"));
                }
                // ahora sí guardo el los resultados ponerlo en la jTable_intervenciones
                Object[] celda = new Object[6]; // 6 columnas
                celda[0] = rs.getString("estado");
                celda[1] = rs.getTimestamp("fecha_intervencion").toString();
                celda[2] = rs.getString("INTER.descripcion");
                celda[3] = rs.getString("nivel");
                celda[4] = rs.getString("prioridad");
                celda[5] = rs.getString("INTER.Usuarios_idUsuario");

                // agregar nueva fila
                model.addRow(celda); // añadimos la nueva fila ya rellenada 
            }

            con.close();

        } catch (SQLException e) {
            System.err.println("Error al consultar la Incidencia del Técnico " + e);
            JOptionPane.showMessageDialog(null, "Error al consultar la Incidencia del Técnico, contacte con el Administrador");
        }
        
        if (acabado == 1) {
            
            jTextPane_intervencion.enable(false);
            cmb_estado.setSelectedIndex(1);
            cmb_estado.enable(false);
            jButton_Actualizar.setText("Salir");
            jLabel_Titulo.setText("Consulta del Incidente");
            
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
        jLabel_Nombre9 = new javax.swing.JLabel();
        jLabel_Nombre1 = new javax.swing.JLabel();
        jLabel_Nombre2 = new javax.swing.JLabel();
        jLabel_Nombre3 = new javax.swing.JLabel();
        jLabel_Nombre4 = new javax.swing.JLabel();
        jLabel_Nombre5 = new javax.swing.JLabel();
        jLabel_Nombre6 = new javax.swing.JLabel();
        jLabel_Nombre7 = new javax.swing.JLabel();
        txt_tipo = new javax.swing.JTextField();
        txt_idIncidente = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_fechaincidente = new javax.swing.JTextField();
        txt_prioridad = new javax.swing.JTextField();
        txt_nivel = new javax.swing.JTextField();
        cmb_estado = new javax.swing.JComboBox<>();
        jButton_Actualizar = new javax.swing.JButton();
        jScrollPane_descripcion = new javax.swing.JScrollPane();
        jTextPane_descripcion = new javax.swing.JTextPane();
        jScrollPane_intervencion = new javax.swing.JScrollPane();
        jTextPane_intervencion = new javax.swing.JTextPane();
        jLabel_footer = new javax.swing.JLabel();
        jLabel_Titulo1 = new javax.swing.JLabel();
        jScrollPane_intervenciones = new javax.swing.JScrollPane();
        jTable_intervenciones = new javax.swing.JTable();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(800, 650));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_Titulo.setForeground(java.awt.Color.white);
        jLabel_Titulo.setText("Gestión del Incidente");
        getContentPane().add(jLabel_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        jLabel_Nombre.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre.setForeground(java.awt.Color.white);
        jLabel_Nombre.setText("Nombre:");
        getContentPane().add(jLabel_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel_Nombre9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre9.setForeground(java.awt.Color.white);
        jLabel_Nombre9.setText("Establecer estado:");
        getContentPane().add(jLabel_Nombre9, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, -1, -1));

        jLabel_Nombre1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre1.setForeground(java.awt.Color.white);
        jLabel_Nombre1.setText("ID incidente:");
        getContentPane().add(jLabel_Nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel_Nombre2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre2.setForeground(java.awt.Color.white);
        jLabel_Nombre2.setText("Tipo:");
        getContentPane().add(jLabel_Nombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, -1, -1));

        jLabel_Nombre3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre3.setForeground(java.awt.Color.white);
        jLabel_Nombre3.setText("Descripción:");
        getContentPane().add(jLabel_Nombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel_Nombre4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre4.setForeground(java.awt.Color.white);
        jLabel_Nombre4.setText("Fecha creación:");
        getContentPane().add(jLabel_Nombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, -1, -1));

        jLabel_Nombre5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre5.setForeground(java.awt.Color.white);
        jLabel_Nombre5.setText("Actuación realizada:");
        getContentPane().add(jLabel_Nombre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, -1, -1));

        jLabel_Nombre6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre6.setForeground(java.awt.Color.white);
        jLabel_Nombre6.setText("Prioridad:");
        getContentPane().add(jLabel_Nombre6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 100, -1, -1));

        jLabel_Nombre7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre7.setForeground(java.awt.Color.white);
        jLabel_Nombre7.setText("Nivel:");
        getContentPane().add(jLabel_Nombre7, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 140, -1, -1));

        txt_tipo.setEditable(false);
        txt_tipo.setBackground(new java.awt.Color(16, 72, 75));
        txt_tipo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_tipo.setForeground(java.awt.Color.white);
        txt_tipo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_tipo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 100, 25));

        txt_idIncidente.setEditable(false);
        txt_idIncidente.setBackground(new java.awt.Color(16, 72, 75));
        txt_idIncidente.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_idIncidente.setForeground(java.awt.Color.white);
        txt_idIncidente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idIncidente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_idIncidente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 50, 25));

        txt_nombre.setEditable(false);
        txt_nombre.setBackground(new java.awt.Color(16, 72, 75));
        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_nombre.setForeground(java.awt.Color.white);
        txt_nombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_nombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 270, 25));

        txt_fechaincidente.setEditable(false);
        txt_fechaincidente.setBackground(new java.awt.Color(16, 72, 75));
        txt_fechaincidente.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_fechaincidente.setForeground(java.awt.Color.white);
        txt_fechaincidente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_fechaincidente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_fechaincidente, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 60, 200, 25));

        txt_prioridad.setEditable(false);
        txt_prioridad.setBackground(new java.awt.Color(16, 72, 75));
        txt_prioridad.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_prioridad.setForeground(java.awt.Color.white);
        txt_prioridad.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_prioridad.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_prioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, 80, 25));

        txt_nivel.setEditable(false);
        txt_nivel.setBackground(new java.awt.Color(16, 72, 75));
        txt_nivel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_nivel.setForeground(java.awt.Color.white);
        txt_nivel.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_nivel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_nivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 140, 80, 25));

        cmb_estado.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cmb_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En Proceso", "Finalizado" }));
        cmb_estado.setAutoscrolls(true);
        cmb_estado.setBorder(null);
        cmb_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_estadoActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 180, 130, -1));

        jButton_Actualizar.setBackground(new java.awt.Color(10, 47, 63));
        jButton_Actualizar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Actualizar.setForeground(java.awt.Color.white);
        jButton_Actualizar.setText("Actualizar");
        jButton_Actualizar.setBorder(null);
        jButton_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 440, 210, 35));

        jTextPane_descripcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextPane_descripcion.setEnabled(false);
        jScrollPane_descripcion.setViewportView(jTextPane_descripcion);

        getContentPane().add(jScrollPane_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 270, 70));

        jTextPane_intervencion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane_intervencion.setViewportView(jTextPane_intervencion);

        getContentPane().add(jScrollPane_intervencion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 400, 70));

        jLabel_footer.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_footer.setText("Andreu Garcia Coll - UIB 2020");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 550, -1, -1));

        jLabel_Titulo1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_Titulo1.setForeground(java.awt.Color.white);
        jLabel_Titulo1.setText("Intervenciones");
        getContentPane().add(jLabel_Titulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jTable_intervenciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane_intervenciones.setViewportView(jTable_intervenciones);

        getContentPane().add(jScrollPane_intervenciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 910, 170));

        jLabel_Wallpaper.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Wallpaper.setForeground(java.awt.Color.white);
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 650));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed

        // fecha de Actualización de la Incidencia (con esta intervención)
        Timestamp fecha = new Timestamp(new java.util.Date().getTime());
        String txt_estado = cmb_estado.getSelectedItem().toString();

        if (acabado == 1) {
            
            acabado = 0;
            jButton_Actualizar.setText("Actualizar");
            jLabel_Titulo.setText("Gestión del Incidente");
            this.dispose();
            
        } else {

            if (jTextPane_intervencion.getText().equals("")) {
                // Si no ha escrito nada en lo que ha estado haciendo
                // le aviso o no sale de ahí
                JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos");
                jTextPane_intervencion.requestFocus();

            } else {

                // Anoto como intervención lo que le ha hecho el técnico
                try {
                    Connection con = Conexion.conector();
                    String sql = "INSERT INTO Intervenciones ";
                    sql += "(descripcion, fecha_intervencion, Usuarios_idUsuario, Incidentes_idIncidente) ";
                    sql += "values (?,?,?,?)";
                    PreparedStatement pst = con.prepareStatement(sql);

                    pst.setString(1, jTextPane_intervencion.getText()); // descripción de la intervención
                    pst.setTimestamp(2, fecha); // fecha creación incidente
                    pst.setInt(3, IDuser); // usuario que se asigna el incidente
                    pst.setInt(4, IDincidente); // variable global del ID incidente

                    pst.executeUpdate();
                    con.close();
                } catch (SQLException e) {
                    System.err.println("Error al registrar intervención por el técnico " + e);
                    JOptionPane.showMessageDialog(null, "Error al registrar intervención por el técnico, contacte con el administrador");
                }
                // La intervención evoluciona al estado 'En Proceso' o 'Finalizado'  

                try {
                    Connection con2 = Conexion.conector();
                    String sql2 = "INSERT INTO Estados ";
                    sql2 += "(estado, descripcion, fecha_evolucion, Intervenciones_idIntervencion) ";
                    sql2 += "values (?,?,?,(select MAX(idIntervencion) FROM Intervenciones))";
                    PreparedStatement pst2 = con2.prepareStatement(sql2);

                    pst2.setString(1, txt_estado); // estado 'En Proceso' o 'Finalizado'
                    pst2.setString(2, txt_estado + " por: " + user); // descripción del problema
                    pst2.setTimestamp(3, fecha); // fecha evolución estado "Asignado"

                    pst2.executeUpdate();
                    con2.close();
                } catch (SQLException e) {
                    System.err.println("Error al registrar estado por el técnico " + e);
                    JOptionPane.showMessageDialog(null, "Error al registrar estado por el técnico, contacte con el administrador");
                }

                // El nivel de la intervención se queda igual que estaba el Nivel lo determina el Administrador 
                try {
                    Connection con3 = Conexion.conector();
                    String sql3 = "INSERT INTO Niveles ";
                    sql3 += "(nivel, descripcion, fecha_escalada, Intervenciones_idIntervencion) ";
                    sql3 += "values (?,?,?,(select MAX(idIntervencion) from Intervenciones))";
                    PreparedStatement pst3 = con3.prepareStatement(sql3);

                    pst3.setString(1, txt_nivel.getText().toString()); // el que hay en el Formulario
                    pst3.setString(2, "Se mantiene el nivel"); // el nivel no se cambia
                    pst3.setTimestamp(3, fecha); // fecha escalado de nivel

                    pst3.executeUpdate();
                    con3.close();
                } catch (SQLException e) {
                    System.err.println("Error al registrar nivel por el técnico " + e);
                    JOptionPane.showMessageDialog(null, "Error al registrar nivel por el técnico, contacte con el administrador");
                }

                // Al igual que en el nivel, las prioridades las establece el Administrador.
                try {
                    Connection con4 = Conexion.conector();
                    String sql4 = "insert into Prioridades (prioridad, descripcion, fecha_establece, Intervenciones_idIntervencion) "
                            + "values (?,?,?,(select MAX(idIntervencion) from Intervenciones))";
                    PreparedStatement pst4 = con4.prepareStatement(sql4);

                    pst4.setString(1, txt_prioridad.getText().toString()); // la que hay en el Formulario
                    pst4.setString(2, "Se mantiene la prioridad"); // la prioridad no se cambia
                    pst4.setTimestamp(3, fecha); // fecha establecimiento prioridad

                    pst4.executeUpdate();
                    con4.close();
                } catch (SQLException e) {
                    System.err.println("Error al registrar prioridad por el técnico " + e);
                    JOptionPane.showMessageDialog(null, "Error al registrar prioridad por el técnico, contacte con el administrador");
                }

                JOptionPane.showMessageDialog(null, "Actualización correcta de  la Incidencia");
                this.dispose(); // destruímos ventana
            }
        }
    }//GEN-LAST:event_jButton_ActualizarActionPerformed

    private void cmb_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_estadoActionPerformed

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
            java.util.logging.Logger.getLogger(InformacionIncidenciasTecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformacionIncidenciasTecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformacionIncidenciasTecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformacionIncidenciasTecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformacionIncidenciasTecnico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_estado;
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JLabel jLabel_Nombre;
    private javax.swing.JLabel jLabel_Nombre1;
    private javax.swing.JLabel jLabel_Nombre2;
    private javax.swing.JLabel jLabel_Nombre3;
    private javax.swing.JLabel jLabel_Nombre4;
    private javax.swing.JLabel jLabel_Nombre5;
    private javax.swing.JLabel jLabel_Nombre6;
    private javax.swing.JLabel jLabel_Nombre7;
    private javax.swing.JLabel jLabel_Nombre9;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_Titulo1;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JScrollPane jScrollPane_descripcion;
    private javax.swing.JScrollPane jScrollPane_intervencion;
    private javax.swing.JScrollPane jScrollPane_intervenciones;
    private javax.swing.JTable jTable_intervenciones;
    private javax.swing.JTextPane jTextPane_descripcion;
    private javax.swing.JTextPane jTextPane_intervencion;
    private javax.swing.JTextField txt_fechaincidente;
    private javax.swing.JTextField txt_idIncidente;
    private javax.swing.JTextField txt_nivel;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_prioridad;
    private javax.swing.JTextField txt_tipo;
    // End of variables declaration//GEN-END:variables
}
