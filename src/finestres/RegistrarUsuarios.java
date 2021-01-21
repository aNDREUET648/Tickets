package finestres;

import clases.Conexion;
import java.sql.*;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/*
 * @author aNDREUET
 */
public class RegistrarUsuarios extends javax.swing.JFrame {

    String user;
    int IDuser;

    /*
     * Constructor del form RegistrarUsuarios
     */
    public RegistrarUsuarios() {
        initComponents();
        user = Interface.usuario;
        IDuser = Interface.IDuser;

        setTitle("Agregar un nuevo usuario - Sesión de " + user);
        setSize(630, 370);
        setResizable(false);
        setLocationRelativeTo(null);
        
        // contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, textField_2, textField_1}));
        
        
        //impedimos que el programa finalice cuando la interface se cierre
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        // Fondo de pantalla de mi aplicación
        ImageIcon wallpaper = new ImageIcon("src/imatges/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(),
                jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Wallpaper.setIcon(icono);
        this.repaint();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_apellidos = new javax.swing.JTextField();
        txt_username = new javax.swing.JTextField();
        txt_password = new javax.swing.JPasswordField();
        txt_telefono = new javax.swing.JTextField();
        cmb_rol = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jLabel_footer = new javax.swing.JLabel();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(630, 370));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Registro de usuarios");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Nombre:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("em@il:");
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Apellidos:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("Rol de:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setText("Username:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("Password:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("Teléfono:");
        jLabel8.setToolTipText("");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, -1, -1));

        txt_nombre.setBackground(new java.awt.Color(141, 159, 166));
        txt_nombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_nombre.setForeground(java.awt.Color.white);
        txt_nombre.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_nombre.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nombreActionPerformed(evt);
            }
        });
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 210, 25));

        txt_email.setBackground(new java.awt.Color(141, 159, 166));
        txt_email.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_email.setForeground(java.awt.Color.white);
        txt_email.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_email.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_emailActionPerformed(evt);
            }
        });
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 210, 25));

        txt_apellidos.setBackground(new java.awt.Color(141, 159, 166));
        txt_apellidos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_apellidos.setForeground(java.awt.Color.white);
        txt_apellidos.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_apellidos.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_apellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_apellidosActionPerformed(evt);
            }
        });
        getContentPane().add(txt_apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, 25));

        txt_username.setBackground(new java.awt.Color(141, 159, 166));
        txt_username.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_username.setForeground(java.awt.Color.white);
        txt_username.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_username.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_usernameActionPerformed(evt);
            }
        });
        getContentPane().add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 210, 25));

        txt_password.setBackground(new java.awt.Color(141, 159, 166));
        txt_password.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_password.setForeground(java.awt.Color.white);
        txt_password.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_password.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 210, 25));

        txt_telefono.setBackground(new java.awt.Color(141, 159, 166));
        txt_telefono.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_telefono.setForeground(java.awt.Color.white);
        txt_telefono.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_telefono.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txt_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_telefonoActionPerformed(evt);
            }
        });
        getContentPane().add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 210, 25));

        cmb_rol.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmb_rol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrador", "Tecnico", "Cliente" }));
        getContentPane().add(cmb_rol, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jButton1.setBackground(new java.awt.Color(16, 72, 75));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setForeground(java.awt.Color.white);
        jButton1.setText("Registrar Usuario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 240, 130, 35));

        jLabel_footer.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_footer.setText("Andreu Garcia Coll - UIB 2020");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 300, -1, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 370));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nombreActionPerformed

    private void txt_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_emailActionPerformed

    private void txt_apellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_apellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_apellidosActionPerformed

    private void txt_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_usernameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int rol_cmb, validacion = 0;
        String nombre, apellidos, email, username, password, rol_string, telefono;

        telefono = txt_telefono.getText().trim();
        email = txt_email.getText().trim();
        username = txt_username.getText().trim();
        password = txt_password.getText().trim();
        nombre = txt_nombre.getText().trim();
        apellidos = txt_apellidos.getText().trim();
        rol_cmb = cmb_rol.getSelectedIndex() + 1;  // que rol tiene mi usuario

        if (telefono.equals("")) {
            txt_telefono.setBackground(new Color(255,180,40));
            validacion++;
        }
        if (email.equals("")) {
            txt_email.setBackground(new Color(255,180,40));
            validacion++;
        }

        if (username.equals("")) {
            txt_username.setBackground(new Color(255,180,40));
            validacion++;
        }

        if (password.equals("")) {
            txt_password.setBackground(new Color(255,180,40));
            validacion++;
        }

        if (nombre.equals("")) {
            txt_nombre.setBackground(new Color(255,180,40));
            validacion++;
        }

        if (apellidos.equals("")) {
            txt_apellidos.setBackground(new Color(255,180,40));
            validacion++;
        }

        // verificamos que no haya dos username iguales
        try {
            Connection con = Conexion.conector();
            String sql = "select user from Usuarios where user = '" + username + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                txt_username.setBackground(new Color(255,180,40));
                JOptionPane.showMessageDialog(null, "El usuario ya existe");
                con.close(); // cerramos la conexión
            } else {
                //
                //          insertamos registros
                //
                con.close(); // cerramos la conexión con y abriremos otra con2
                // se han rellenado bien los campos y se pueden insertar el usuario
                if (validacion == 0) {
                    try {
                        //
                        //  insertamos los datos en la tabla de Usuarios
                        //
                        Connection con2 = Conexion.conector();
                        String sql2 = "INSERT INTO Usuarios (nombre, apellidos, user, password, email, habilitado, registrado_por, telefono) ";
                        sql2 += "values (?, ?, ?, ?, ?, ?, ?, ?)";
                        PreparedStatement pst2 = con2.prepareStatement(sql2);

                        // pst2.setInt(1, 0); // el IdUsuario es autoincremental no escribo nada
                        pst2.setString(1, nombre);       // nombre
                        pst2.setString(2, apellidos);    // apellidos
                        pst2.setString(3, username);     // user
                        pst2.setString(4, password);     // password
                        pst2.setString(5, email);        // email
                        pst2.setString(6, "1");          // habilitado
                        pst2.setInt(7, IDuser);     // el que dió de alta el usuario
                        pst2.setString(8, telefono);        // telefono

                        int resultado = pst2.executeUpdate();

                        if (resultado > 0) {
                            // JOptionPane.showMessageDialog(null, "Usuario creado");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al agregar registro usuario, contacte con el Administrador");
                        }

                        Timestamp fecha_actual = new Timestamp(new java.util.Date().getTime());
                        String sql3 = "insert into Roles_has_Usuarios(Usuarios_idUsuario,Roles_idRol,fecha_posesion) "
                                + "VALUES ((select MAX(idUsuario) from Usuarios),?,?)";
                        PreparedStatement pst3 = con2.prepareStatement(sql3);

                        pst3.setInt(1, rol_cmb);       // Roles_idRol
                        pst3.setTimestamp(2, fecha_actual);  // añadimos el timestamp

                        resultado = pst3.executeUpdate();

                        if (resultado > 0) {
                            //  marcamos en verde todos los campos insertados
                            txt_nombre.setBackground(new Color(40,190,255));
                            txt_apellidos.setBackground(new Color(40,190,255));
                            txt_email.setBackground(new Color(40,190,255));
                            txt_telefono.setBackground(new Color(40,190,255));
                            txt_username.setBackground(new Color(40,190,255));
                            txt_password.setBackground(new Color(40,190,255));
                            JOptionPane.showMessageDialog(null, "Usuario creado");
                            // JOptionPane.showMessageDialog(null, "Añadido su rol");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al insertar el rol del usuario, contacte con el Administrador");
                        }

                        con2.close();
                        Limpiar();
                        this.dispose(); // destruímos ventana

                    } catch (SQLException e) {
                        System.err.println("Error al crear el usuario " + e);
                        JOptionPane.showMessageDialog(null, "Error al insertar nuevo usuario, contacte con el Administrador");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error validando el usuario (username) " + e);
            JOptionPane.showMessageDialog(null, "Error al comparar el username, contacte con el Administrador");
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_telefonoActionPerformed

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
            java.util.logging.Logger.getLogger(RegistrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_rol;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JTextField txt_apellidos;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_username;
    // End of variables declaration//GEN-END:variables

    public void Limpiar() {
        txt_nombre.setText("");
        txt_apellidos.setText("");
        txt_email.setText("");
        txt_username.setText("");
        txt_password.setText("");
        cmb_rol.setSelectedIndex(0); // inicializamos el combo box 
    }

}
