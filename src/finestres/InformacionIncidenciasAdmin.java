package finestres;

import java.sql.*;
import clases.Conexion;
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
public class InformacionIncidenciasAdmin extends javax.swing.JFrame {

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
    public InformacionIncidenciasAdmin() {
        initComponents();
        user = Interface.usuario;
        IDuser = Interface.IDuser;
        IDincidente = GestionarIncidenciasAdmin.IDincidente_update;
        // campos para rellenar los textFields
        String nombre = "", incidente = "", nivel = "", prioridad = "", fecha_incidente = "", tipo = "", descrip_incid = "";
        String fecha_interv = "";
        // establecemos un formato de fecha y hora
        //String strDateFormat = "dd-MMM-aaaa - hh: mm: ss";

        setSize(800, 500);
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

        // ahora miro todas las intervenciones que tiene
        try {
            Connection con = Conexion.conector();

            String sql = "SELECT * FROM usuarios USU, incidentes INC, intervenciones INTER, estados EST, niveles NIV, prioridades PRI, tipos WHERE ";
            sql += "idUsuario = INC.Usuarios_idUsuario AND ";
            sql += "idIncidente = " + IDincidente + " AND ";
            sql += "Incidentes_idIncidente = " + IDincidente + " AND ";
            sql += "idIntervencion = NIV.Intervenciones_idIntervencion AND ";
            sql += "idIntervencion = PRI.Intervenciones_idIntervencion AND ";
            sql += "idIntervencion = EST.Intervenciones_idIntervencion AND ";
            sql += "idTipo = tipos_idTipo ";
            sql += "ORDER BY idIncidente DESC, idIntervencion DESC";

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
            model.addColumn("ID Asignado");

            // y relleno la tabla
            while (rs.next()) {
                /*
                        Al estar ordenado desde la intervención más nueva a la más antigua
                        cojo el el primer resultado de como se encuentra actualmente
                        y lo guardo para rellenar los textFields
                 */
                if (rs.getRow() == 1) {

                    if (rs.getString("estado").toString().equals("Finalizado")) {
                        acabado = 1;
                    }
                    nombre = rs.getString("nombre") + " " + rs.getString("apellidos");
                    incidente = Integer.toString(IDincidente);
                    fecha_incidente = rs.getTimestamp("fecha_crea").toString();
                    nivel = rs.getString("nivel");
                    prioridad = rs.getString("prioridad");
                    tipo = rs.getString("tipo");
                    descrip_incid = rs.getString("INC.descripcion");
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
            // Pongo el ID del incidente y nombre y apellidos del usuario
            txt_nombre.setText(nombre);
            txt_idIncidente.setText(incidente);
            txt_fechaincidente.setText(fecha_incidente);
            txt_tipo.setText(tipo);
            jTextPane_descripcion.setText(descrip_incid);
            cmb_nivel.setSelectedItem(nivel);
            cmb_prioridad.setSelectedItem(prioridad);
            con.close();

        } catch (SQLException e) {
            System.err.println("Error al consultar las Intervenciones Admin " + e);
            JOptionPane.showMessageDialog(null, "Error al consultar las Intervenciones Admin, contacte con el Administrador");
        }

        /*
                Ahora cargaremos en el cmb_asignar todos los técnicos administradores
                que se les puede asignar a la incidencia creada. No se incluirán los que
                no están activos actualmente
         */
        cmb_asignar.removeAllItems();
        if (acabado == 0) {

            try {
                Connection con2 = Conexion.conector();
                String sql2 = "SELECT  idUsuario, nombre, apellidos, rol, U.habilitado ";
                sql2 += "FROM Usuarios U, Roles_has_Usuarios, Roles ";
                sql2 += "WHERE  Usuarios_idUsuario = idUsuario AND ";
                sql2 += "Roles_idRol = idRol AND ";
                sql2 += "U.habilitado = 1 AND ";
                sql2 += "idRol != 3"; // que no sean clientes los de la lista
                PreparedStatement pst2 = con2.prepareStatement(sql2);
                ResultSet rs2 = pst2.executeQuery();

                while (rs2.next()) {
                    cmb_asignar.addItem(Integer.toString(rs2.getInt("idUsuario")) + " " + rs2.getString("rol") + " " + rs2.getString("nombre") + " " + rs2.getString("apellidos"));
                }
                con2.close();
            } catch (SQLException e) {
                System.err.println("Error al consultar Usuarios a asignar Admin " + e);
                JOptionPane.showMessageDialog(null, "Error al consultar Usuarios a asignar Admin, contacte con el Administrador");
            }
        } else {

            cmb_prioridad.enable(false);
            cmb_nivel.enable(false);
            cmb_asignar.enable(false);
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
        jLabel_Titulo1 = new javax.swing.JLabel();
        jLabel_Nombre = new javax.swing.JLabel();
        jLabel_Nombre1 = new javax.swing.JLabel();
        jLabel_Nombre2 = new javax.swing.JLabel();
        jLabel_Nombre3 = new javax.swing.JLabel();
        jLabel_Nombre4 = new javax.swing.JLabel();
        jLabel_Nombre7 = new javax.swing.JLabel();
        jLabel_Nombre8 = new javax.swing.JLabel();
        jLabel_Nombre9 = new javax.swing.JLabel();
        txt_tipo = new javax.swing.JTextField();
        txt_idIncidente = new javax.swing.JTextField();
        txt_nombre = new javax.swing.JTextField();
        txt_fechaincidente = new javax.swing.JTextField();
        cmb_asignar = new javax.swing.JComboBox<>();
        cmb_prioridad = new javax.swing.JComboBox<>();
        cmb_nivel = new javax.swing.JComboBox<>();
        jScrollPane_intervenciones = new javax.swing.JScrollPane();
        jTable_intervenciones = new javax.swing.JTable();
        jScrollPane_descripcion = new javax.swing.JScrollPane();
        jTextPane_descripcion = new javax.swing.JTextPane();
        jButton_Actualizar = new javax.swing.JButton();
        jLabel_footer = new javax.swing.JLabel();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(800, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_Titulo.setForeground(java.awt.Color.white);
        jLabel_Titulo.setText("Gestión del Incidente");
        getContentPane().add(jLabel_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, -1, -1));

        jLabel_Titulo1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel_Titulo1.setForeground(java.awt.Color.white);
        jLabel_Titulo1.setText("Intervenciones");
        getContentPane().add(jLabel_Titulo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        jLabel_Nombre.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre.setForeground(java.awt.Color.white);
        jLabel_Nombre.setText("Nombre:");
        getContentPane().add(jLabel_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel_Nombre1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre1.setForeground(java.awt.Color.white);
        jLabel_Nombre1.setText("ID incidente:");
        getContentPane().add(jLabel_Nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabel_Nombre2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre2.setForeground(java.awt.Color.white);
        jLabel_Nombre2.setText("Tipo:");
        getContentPane().add(jLabel_Nombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jLabel_Nombre3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre3.setForeground(java.awt.Color.white);
        jLabel_Nombre3.setText("Descripción:");
        getContentPane().add(jLabel_Nombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel_Nombre4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre4.setForeground(java.awt.Color.white);
        jLabel_Nombre4.setText("Fecha creación:");
        getContentPane().add(jLabel_Nombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 50, -1, -1));

        jLabel_Nombre7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre7.setForeground(java.awt.Color.white);
        jLabel_Nombre7.setText("Asignar a:");
        getContentPane().add(jLabel_Nombre7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, -1, -1));

        jLabel_Nombre8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre8.setForeground(java.awt.Color.white);
        jLabel_Nombre8.setText("Establer prioridad:");
        getContentPane().add(jLabel_Nombre8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, -1));

        jLabel_Nombre9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel_Nombre9.setForeground(java.awt.Color.white);
        jLabel_Nombre9.setText("Escalar nivel:");
        getContentPane().add(jLabel_Nombre9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, -1, -1));

        txt_tipo.setEditable(false);
        txt_tipo.setBackground(new java.awt.Color(153, 153, 255));
        txt_tipo.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_tipo.setForeground(java.awt.Color.white);
        txt_tipo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_tipo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_tipo.setEnabled(false);
        getContentPane().add(txt_tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 80, 25));

        txt_idIncidente.setEditable(false);
        txt_idIncidente.setBackground(new java.awt.Color(153, 153, 255));
        txt_idIncidente.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_idIncidente.setForeground(java.awt.Color.white);
        txt_idIncidente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_idIncidente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_idIncidente.setEnabled(false);
        getContentPane().add(txt_idIncidente, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 50, 25));

        txt_nombre.setEditable(false);
        txt_nombre.setBackground(new java.awt.Color(153, 153, 255));
        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_nombre.setForeground(java.awt.Color.white);
        txt_nombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_nombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_nombre.setEnabled(false);
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 240, 25));

        txt_fechaincidente.setEditable(false);
        txt_fechaincidente.setBackground(new java.awt.Color(153, 153, 255));
        txt_fechaincidente.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_fechaincidente.setForeground(java.awt.Color.white);
        txt_fechaincidente.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_fechaincidente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_fechaincidente.setEnabled(false);
        getContentPane().add(txt_fechaincidente, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 190, 25));

        cmb_asignar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmb_asignar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item1", "Item2" }));
        getContentPane().add(cmb_asignar, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 300, -1));

        cmb_prioridad.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cmb_prioridad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Baja", "Media", "Alta", "Grave", "Critica" }));
        cmb_prioridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_prioridadActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_prioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 130, 90, -1));

        cmb_nivel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cmb_nivel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Basico", "Tecnico", "Externo" }));
        getContentPane().add(cmb_nivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 170, 130, -1));

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

        getContentPane().add(jScrollPane_intervenciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 760, 150));

        jTextPane_descripcion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextPane_descripcion.setEnabled(false);
        jScrollPane_descripcion.setViewportView(jTextPane_descripcion);

        getContentPane().add(jScrollPane_descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 270, 70));

        jButton_Actualizar.setBackground(new java.awt.Color(153, 153, 255));
        jButton_Actualizar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Actualizar.setForeground(java.awt.Color.white);
        jButton_Actualizar.setText("Asignar");
        jButton_Actualizar.setBorder(null);
        jButton_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 400, 210, 35));

        jLabel_footer.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_footer.setText("Andreu Garcia Coll - UIB 2020");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, -1, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_prioridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_prioridadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_prioridadActionPerformed

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed

        if (acabado == 1) {

            acabado = 0;
            jButton_Actualizar.setText("Actualizar");
            jLabel_Titulo.setText("Gestión del Incidente");

        } else {
            // array para extraer el ID del técnico
            String[] items = cmb_asignar.getSelectedItem().toString().split(" ");
            // de este array me quedaré con el primero que es el ID del técnico
            int ID_tecnico = Integer.parseInt(items[0]);
            // fecha de Asignación
            Timestamp fecha = new Timestamp(new java.util.Date().getTime());
            // creo la intervención asociada a este incidente
            // como de Asignación que es
            try {
                Connection con = Conexion.conector();
                String sql = "INSERT INTO Intervenciones ";
                sql += "(descripcion, fecha_intervencion, Usuarios_idUsuario, Incidentes_idIncidente) ";
                sql += "values (?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, "Asignado a: " + items[2] + " " + items[3]); // descripción de la intervención
                pst.setTimestamp(2, fecha); // fecha creación incidente
                pst.setInt(3, ID_tecnico); // usuario que se asigna el incidente
                pst.setInt(4, IDincidente); // variable global del ID incidente

                pst.executeUpdate();
                con.close();
            } catch (SQLException e) {
                System.err.println("Error al registrar intervención al Asignar el incidente " + e);
                JOptionPane.showMessageDialog(null, "Error al registrar intervención al Asignar el incidente, contacte con el administrador");
            }
            // La intervención evoluciona al estado Asignado  

            try {
                Connection con2 = Conexion.conector();
                String sql2 = "INSERT INTO Estados ";
                sql2 += "(estado, descripcion, fecha_evolucion, Intervenciones_idIntervencion) ";
                sql2 += "values (?,?,?,(select MAX(idIntervencion) FROM Intervenciones))";
                PreparedStatement pst2 = con2.prepareStatement(sql2);

                pst2.setString(1, "Asignado"); // estado "Asignado" del Incidente
                pst2.setString(2, "Asignamos el Incidente"); // descripción del problema
                pst2.setTimestamp(3, fecha); // fecha evolución estado "Asignado"

                pst2.executeUpdate();
                con2.close();
            } catch (SQLException e) {
                System.err.println("Error al registrar estado al Asignar el incidente " + e);
                JOptionPane.showMessageDialog(null, "Error al registrar estado al Asignar el incidente, contacte con el administrador");
            }

            // la intervención se escala al Nivel determinado por el Administrador 
            try {
                Connection con3 = Conexion.conector();
                String sql3 = "INSERT INTO Niveles ";
                sql3 += "(nivel, descripcion, fecha_escalada, Intervenciones_idIntervencion) ";
                sql3 += "values (?,?,?,(select MAX(idIntervencion) from Intervenciones))";
                PreparedStatement pst3 = con3.prepareStatement(sql3);

                pst3.setString(1, cmb_nivel.getSelectedItem().toString()); // el que hay en el combobox
                pst3.setString(2, "Nivel cambiado por " + user); // descripción del cambio de nivel
                pst3.setTimestamp(3, fecha); // fecha escalado de nivel

                pst3.executeUpdate();
                con3.close();
            } catch (SQLException e) {
                System.err.println("Error al registrar nivel al Asignar el incidente " + e);
                JOptionPane.showMessageDialog(null, "Error al registrar nivel al Asignar el incidente, contacte con el administrador");
            }

            // en la intervención se establecen unas prioridades.
            try {
                Connection con4 = Conexion.conector();
                String sql4 = "insert into Prioridades (prioridad, descripcion, fecha_establece, Intervenciones_idIntervencion) "
                        + "values (?,?,?,(select MAX(idIntervencion) from Intervenciones))";
                PreparedStatement pst4 = con4.prepareStatement(sql4);

                pst4.setString(1, cmb_prioridad.getSelectedItem().toString()); // Prioridad del Incidente
                pst4.setString(2, "Prioridad cambiada por " + user); // descripción del problema
                pst4.setTimestamp(3, fecha); // fecha establecimiento prioridad

                pst4.executeUpdate();
                con4.close();
            } catch (SQLException e) {
                System.err.println("Error al crear prioridad Hardware " + e);
                JOptionPane.showMessageDialog(null, "Error al crear prioridad Hardware, contacte con el administrador");
            }

            JOptionPane.showMessageDialog(null, "Actualización correcta de  la Incidencia");

        }
        this.dispose(); // destruímos ventana

    }//GEN-LAST:event_jButton_ActualizarActionPerformed

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
            java.util.logging.Logger.getLogger(InformacionIncidenciasAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformacionIncidenciasAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformacionIncidenciasAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformacionIncidenciasAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformacionIncidenciasAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_asignar;
    private javax.swing.JComboBox<String> cmb_nivel;
    private javax.swing.JComboBox<String> cmb_prioridad;
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JLabel jLabel_Nombre;
    private javax.swing.JLabel jLabel_Nombre1;
    private javax.swing.JLabel jLabel_Nombre2;
    private javax.swing.JLabel jLabel_Nombre3;
    private javax.swing.JLabel jLabel_Nombre4;
    private javax.swing.JLabel jLabel_Nombre7;
    private javax.swing.JLabel jLabel_Nombre8;
    private javax.swing.JLabel jLabel_Nombre9;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_Titulo1;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JScrollPane jScrollPane_descripcion;
    private javax.swing.JScrollPane jScrollPane_intervenciones;
    private javax.swing.JTable jTable_intervenciones;
    private javax.swing.JTextPane jTextPane_descripcion;
    private javax.swing.JTextField txt_fechaincidente;
    private javax.swing.JTextField txt_idIncidente;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_tipo;
    // End of variables declaration//GEN-END:variables
}
