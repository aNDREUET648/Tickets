package finestres;

import java.sql.*;
import clases.Conexion;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/*
 * @author aNDREUET
 */
public class InformacionUsuario extends javax.swing.JFrame {

    String user = "", user_update = "";
    public static int ID_usuario_update;
    // model interactuará con mi tabla (j_Table_equipos) creada en el form
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Constructor form InformacionUsuario
     */
    public InformacionUsuario() {
        initComponents();
        user = Interface.usuario;
        user_update = GestionarUsuarios.user_update;

        setTitle("Información del usuario - " + user_update + " - Sesión de " + user);
        setSize(1000, 550);
        setResizable(false);
        setLocationRelativeTo(null);
        setLocationRelativeTo(null);

        // evita que se cierre el programa cuando cerramos esta interfaz
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        // Fondo de pantalla de mi aplicación
        ImageIcon wallpaper = new ImageIcon("src/imatges/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(),
                jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Wallpaper.setIcon(icono);
        this.repaint();

        jLabel_Titulo.setText("Información del Usuario - " + user_update);

        try {
            Connection con = Conexion.conector();
            String habilitado = "";
            String sql = "select  * from Usuarios U, Roles_has_Usuarios, Roles where  user= '" + user_update + "'"
                    + " and Usuarios_idUsuario = idUsuario and Roles_idRol = idRol";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                ID_usuario_update = rs.getInt("idUsuario"); // guardo el id del usuario
                txt_nombre.setText(rs.getString("nombre"));
                txt_email.setText(rs.getString("email"));
                txt_apellidos.setText(rs.getString("apellidos"));
                txt_username.setText(rs.getString("user"));
                txt_RegistradoPor.setText(rs.getString("registrado_por"));
                cmb_rol.setSelectedItem(rs.getString("rol"));

                habilitado = rs.getString("U.habilitado");                
                if ("1".equals(habilitado)) {
                    cmb_habilitado.setSelectedIndex(0);
                } else {
                    cmb_habilitado.setSelectedIndex(1);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Error en cargar Usuario. " + e);
            JOptionPane.showMessageDialog(null, "Error al cargar Usuario, contacte con el Administrador");
        }
        
        //
        // rellenamos la tabla con los equipos registrados del cliente
        //
        try {
            Connection con = Conexion.conector();
            String sql = "select * from Equipos where usuarios_idUsuario = '" + ID_usuario_update + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            jTable_equipos = new JTable(model);
            jScrollPane_equipos.setViewportView(jTable_equipos);

            model.addColumn("ID equipo");
            model.addColumn("Tipo de equipo");
            model.addColumn("Marca");
            model.addColumn("Modelo");

            while (rs.next()) {
                //creo vector de tipo objectos
                Object[] celda = new Object[4];
                //paso la celda del idEquipo como int para no tener el
                // error que tuve en GestionarClientes
                celda[0]=rs.getInt("idEquipos");
                for (int i = 1; i < 4; i++) {
                    celda[i] = rs.getObject(i + 1);
                }
                model.addRow(celda);
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Error al cargar los equipos " + e);
            JOptionPane.showMessageDialog(null, "Error al mostrar equipos, contacte con el Administrador");
        }

    }

    //
    //  Colocamos en icono que aparecerá en la barra de tareas
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
        jLabel_Nombre1 = new javax.swing.JLabel();
        jLabel_Nombre2 = new javax.swing.JLabel();
        jLabel_Nombre3 = new javax.swing.JLabel();
        jLabel_Nombre4 = new javax.swing.JLabel();
        jLabel_Nombre5 = new javax.swing.JLabel();
        jLabel_Nombre6 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_apellidos = new javax.swing.JTextField();
        txt_username = new javax.swing.JTextField();
        txt_RegistradoPor = new javax.swing.JTextField();
        cmb_habilitado = new javax.swing.JComboBox<>();
        cmb_rol = new javax.swing.JComboBox<>();
        jButton_Actualizar = new javax.swing.JButton();
        jButton_RestaurarPassword = new javax.swing.JButton();
        jButton_RegistrarEquipo = new javax.swing.JButton();
        jScrollPane_equipos = new javax.swing.JScrollPane();
        jTable_equipos = new javax.swing.JTable();
        jLabel_footer = new javax.swing.JLabel();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 550));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_Titulo.setForeground(java.awt.Color.white);
        jLabel_Titulo.setText("Información del Usuario");
        getContentPane().add(jLabel_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, -1, -1));

        jLabel_Nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_Nombre.setForeground(java.awt.Color.white);
        jLabel_Nombre.setText("Nombre:");
        getContentPane().add(jLabel_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel_Nombre1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_Nombre1.setForeground(java.awt.Color.white);
        jLabel_Nombre1.setText("em@il:");
        getContentPane().add(jLabel_Nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel_Nombre2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_Nombre2.setForeground(java.awt.Color.white);
        jLabel_Nombre2.setText("Apellidos:");
        getContentPane().add(jLabel_Nombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel_Nombre3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_Nombre3.setForeground(java.awt.Color.white);
        jLabel_Nombre3.setText("Rol:");
        getContentPane().add(jLabel_Nombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        jLabel_Nombre4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_Nombre4.setForeground(java.awt.Color.white);
        jLabel_Nombre4.setText("username:");
        getContentPane().add(jLabel_Nombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel_Nombre5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_Nombre5.setForeground(java.awt.Color.white);
        jLabel_Nombre5.setText("Habilitado");
        getContentPane().add(jLabel_Nombre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, -1, -1));

        jLabel_Nombre6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_Nombre6.setForeground(java.awt.Color.white);
        jLabel_Nombre6.setText("Registrado por:");
        getContentPane().add(jLabel_Nombre6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, -1, -1));

        txt_nombre.setBackground(new java.awt.Color(153, 153, 255));
        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_nombre.setForeground(java.awt.Color.white);
        txt_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 210, -1));

        txt_email.setBackground(new java.awt.Color(153, 153, 255));
        txt_email.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_email.setForeground(java.awt.Color.white);
        txt_email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_email.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 210, -1));

        txt_apellidos.setBackground(new java.awt.Color(153, 153, 255));
        txt_apellidos.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_apellidos.setForeground(java.awt.Color.white);
        txt_apellidos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_apellidos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, -1));

        txt_username.setBackground(new java.awt.Color(153, 153, 255));
        txt_username.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_username.setForeground(java.awt.Color.white);
        txt_username.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_username.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_username.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 210, -1));

        txt_RegistradoPor.setBackground(new java.awt.Color(153, 153, 255));
        txt_RegistradoPor.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txt_RegistradoPor.setForeground(java.awt.Color.white);
        txt_RegistradoPor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_RegistradoPor.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_RegistradoPor.setEnabled(false);
        getContentPane().add(txt_RegistradoPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 210, -1));

        cmb_habilitado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmb_habilitado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));
        getContentPane().add(cmb_habilitado, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, -1, -1));

        cmb_rol.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmb_rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Tecnico", "Cliente" }));
        getContentPane().add(cmb_rol, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jButton_Actualizar.setBackground(new java.awt.Color(153, 153, 255));
        jButton_Actualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_Actualizar.setForeground(java.awt.Color.white);
        jButton_Actualizar.setText("Actualizar Usuario");
        jButton_Actualizar.setBorder(null);
        jButton_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, 210, 35));

        jButton_RestaurarPassword.setBackground(new java.awt.Color(153, 153, 255));
        jButton_RestaurarPassword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_RestaurarPassword.setForeground(java.awt.Color.white);
        jButton_RestaurarPassword.setText("Restaurar Password");
        jButton_RestaurarPassword.setBorder(null);
        jButton_RestaurarPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RestaurarPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_RestaurarPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 320, 210, 35));

        jButton_RegistrarEquipo.setBackground(new java.awt.Color(153, 153, 255));
        jButton_RegistrarEquipo.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_RegistrarEquipo.setForeground(java.awt.Color.white);
        jButton_RegistrarEquipo.setText("Registrar equipo");
        jButton_RegistrarEquipo.setBorder(null);
        jButton_RegistrarEquipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RegistrarEquipoActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_RegistrarEquipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 320, 210, 35));

        jTable_equipos.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane_equipos.setViewportView(jTable_equipos);

        getContentPane().add(jScrollPane_equipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, 680, 180));

        jLabel_footer.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_footer.setText("Andreu Garcia Coll - UIB 2020");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 450, -1, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed
        int rol_cmb, habilitado_cmb, validacion = 0;
        String nombre, email, apellidos, username, pass, rol_string = "", habilitado_string = "";

        email = txt_email.getText().trim();
        username = txt_username.getText().trim();
        nombre = txt_nombre.getText().trim();
        apellidos = txt_apellidos.getText().trim();
        rol_cmb = cmb_rol.getSelectedIndex() + 1;
        habilitado_cmb = cmb_habilitado.getSelectedIndex() + 1;

        if (email.equals("")) {
            txt_email.setBackground(Color.RED);
            validacion++;
        }
        if (username.equals("")) {
            txt_username.setBackground(Color.RED);
            validacion++;
        }
        if (nombre.equals("")) {
            txt_nombre.setBackground(Color.RED);
            validacion++;
        }
        if (apellidos.equals("")) {
            txt_apellidos.setBackground(Color.RED);
            validacion++;
        }

        if (validacion == 0) {
            // sigue activo o lo desactivamos
            // si está activo es 1, sino es 0
            if (habilitado_cmb != 1) {
                habilitado_cmb = 0;
            }

            try {
                //
                // consulta a todos los usuarios menos al que estamos tratando de modificar (idUsuario = ID)
                // y vemos que este username está disponible si no lo encontramos
                //
                Connection con = Conexion.conector();
                String sql;
                sql = "select user from Usuarios where user = '" + username + "'"
                        + " and not idUsuario = '" + ID_usuario_update + "'";
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    txt_username.setBackground(Color.RED);
                    JOptionPane.showMessageDialog(null, "Nombre de usuario no disponible");
                    con.close(); // cerramos la conexión
                } else {
                    //
                    //  SÍ. Hay disponibilidad para el usuario que queremos utilizar
                    //  por lo que modificamos los datos de la persona que
                    //  hemos consultado
                    //
                    Connection con2 = Conexion.conector();
                    String sql2 = "update Usuarios set nombre=?, apellidos=?,"
                            + " email=?, user=?, habilitado=? where idUsuario = '" + ID_usuario_update + "'";
                    PreparedStatement pst2 = con2.prepareStatement(sql2);

                    //
                    // modifico los valores del usuario en Usuarios
                    //
                    pst2.setString(1, nombre);       // nombre
                    pst2.setString(2, apellidos);    // apellidos
                    pst2.setString(3, email);        // email
                    pst2.setString(4, username);     // user
                    pst2.setInt(5, habilitado_cmb);  // habilitado

                    int resultado = pst2.executeUpdate();

                    if (resultado > 0) {
                        //JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al agregar registro usuario, contacte con el Administrador");
                    }
                    //
                    // y también tengo que modificar
                    // de la tabla Roles_has_Usuarios
                    // el nuevo rol que tendrá y cuando ha tomado posesión
                    //
                    Timestamp fecha_actual = new Timestamp(new java.util.Date().getTime());
                    String sql3 = "Update Roles_has_Usuarios set Roles_idRol=?, fecha_posesion=? "
                            + "where Usuarios_idUsuario = '" + ID_usuario_update + "'";
                    PreparedStatement pst3 = con2.prepareStatement(sql3);

                    pst3.setInt(1, rol_cmb);       // Roles_idRol
                    pst3.setTimestamp(2, fecha_actual);  // añadimos el timestamp

                    resultado = pst3.executeUpdate();

                    if (resultado > 0) {
                        //
                        // para que quede más bonito
                        // marcamos en verde todos los campos insertados
                        // indicando que todo hay ido perfecto
                        //
                        txt_nombre.setBackground(Color.GREEN);
                        txt_apellidos.setBackground(Color.GREEN);
                        txt_email.setBackground(Color.GREEN);
                        txt_username.setBackground(Color.GREEN);
                        // JOptionPane.showMessageDialog(null, "Modificado el rol");
                        JOptionPane.showMessageDialog(null, "Usuario modificado correctamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al insertar el rol del usuario, contacte con el Administrador");
                    }
                    con2.close();
                    this.dispose(); // destruímos ventana
                }
            } catch (SQLException e) {
                System.err.println("Error al Actualizar el usuario desde Admin " + e);
                JOptionPane.showMessageDialog(null, "Error al Actualizar el usuario desde Admin, contacte con el Administrador");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos");
        }

    }//GEN-LAST:event_jButton_ActualizarActionPerformed

    private void jButton_RestaurarPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RestaurarPasswordActionPerformed
        
        // realizo una instancia de clase para abrir el interfaz
        // creo objeto de la clase RestaurarPassword y lo nombro
        RestaurarPassword restaurarPassword = new RestaurarPassword();
        restaurarPassword.setVisible(true);
    }//GEN-LAST:event_jButton_RestaurarPasswordActionPerformed

    private void jButton_RegistrarEquipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RegistrarEquipoActionPerformed
        // hacemos una instancia de clase
        RegistrarEquipoUsuario registrarEquipoUsuario = new RegistrarEquipoUsuario();
        registrarEquipoUsuario.setVisible(true);
    }//GEN-LAST:event_jButton_RegistrarEquipoActionPerformed

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
            java.util.logging.Logger.getLogger(InformacionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InformacionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InformacionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InformacionUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformacionUsuario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_habilitado;
    private javax.swing.JComboBox<String> cmb_rol;
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_RegistrarEquipo;
    private javax.swing.JButton jButton_RestaurarPassword;
    private javax.swing.JLabel jLabel_Nombre;
    private javax.swing.JLabel jLabel_Nombre1;
    private javax.swing.JLabel jLabel_Nombre2;
    private javax.swing.JLabel jLabel_Nombre3;
    private javax.swing.JLabel jLabel_Nombre4;
    private javax.swing.JLabel jLabel_Nombre5;
    private javax.swing.JLabel jLabel_Nombre6;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JScrollPane jScrollPane_equipos;
    private javax.swing.JTable jTable_equipos;
    private javax.swing.JTextField txt_RegistradoPor;
    private javax.swing.JTextField txt_apellidos;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables
}
