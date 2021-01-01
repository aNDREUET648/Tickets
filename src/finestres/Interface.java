package finestres;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.sql.*;
import clases.Conexion; // importo mi conexión a la bd

/*
 * @author aNDREUET
 */
public class Interface extends javax.swing.JFrame {

    //static String user;
    String pass = "";  //variable temporal del password de usuario
    // usuario enviará su valor a muchas interfaces
    public static String usuario = "";
    // IDuser enviará su valor a otras interfaces
    public static int IDuser = 0;


    /*
     * Constructor del form Interface
     */
    public Interface() {
        initComponents();
        this.setLocationRelativeTo(null); //coloca a la ventana en el centro de la pantalla
        this.setTitle("Acceso al Sistema");
        this.setSize(300, 460);        // Tamaño de nuestra pantalla principal de login
        this.setResizable(false);         // Impedir que se modifiquen las dimensiones del interfaz 
        jTextUser.requestFocus();   // sitúa el cursor sobre el campo de usuario

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

        jButtonExit = new javax.swing.JButton();
        jLabelIconoUser = new javax.swing.JLabel();
        jLabelUser = new javax.swing.JLabel();
        jLabelPass = new javax.swing.JLabel();
        jTextUser = new javax.swing.JTextField();
        jPassword = new javax.swing.JPasswordField();
        jButtonInicioSesion = new javax.swing.JButton();
        jLabelFondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonExit.setBackground(new java.awt.Color(0, 50, 150));
        jButtonExit.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonExit.setForeground(new java.awt.Color(255, 255, 255));
        jButtonExit.setText("Salir");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        jLabelIconoUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/user.png"))); // NOI18N
        jLabelIconoUser.setText("jLabel1");
        jLabelIconoUser.setMaximumSize(new java.awt.Dimension(300, 470));
        getContentPane().add(jLabelIconoUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 130, 170));

        jLabelUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelUser.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUser.setText("Usuario:");
        getContentPane().add(jLabelUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, -1, -1));

        jLabelPass.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelPass.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPass.setText("Contraseña:");
        getContentPane().add(jLabelPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        jTextUser.setBackground(new java.awt.Color(22, 122, 218));
        jTextUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextUser.setForeground(new java.awt.Color(222, 222, 222));
        jTextUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextUserActionPerformed(evt);
            }
        });
        getContentPane().add(jTextUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 270, 145, 30));

        jPassword.setBackground(new java.awt.Color(22, 122, 218));
        jPassword.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPassword.setForeground(new java.awt.Color(222, 222, 222));
        jPassword.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPassword.setCaretColor(new java.awt.Color(255, 255, 255));
        jPassword.setName(""); // NOI18N
        jPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(jPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 310, 145, 30));

        jButtonInicioSesion.setBackground(new java.awt.Color(0, 50, 150));
        jButtonInicioSesion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButtonInicioSesion.setForeground(new java.awt.Color(255, 255, 255));
        jButtonInicioSesion.setText("Iniciar Sesión");
        jButtonInicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInicioSesionActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonInicioSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, -1, -1));

        jLabelFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/wallpaperPrincipal.jpg"))); // NOI18N
        getContentPane().add(jLabelFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 300, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Salir si pulso el botón

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButtonExitActionPerformed
    private void jTextUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextUserActionPerformed

    }//GEN-LAST:event_jTextUserActionPerformed

//  Botón de Inicio de Sesión
    @SuppressWarnings("StringEquality")
    private void jButtonInicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInicioSesionActionPerformed
        usuario = jTextUser.getText().trim();
        pass = String.valueOf(jPassword.getPassword());

        if (!usuario.equals("") || !pass.equals("")) {
            try {
                // invoco al método conexionMySQL de mi clase Conexion

                Connection con = Conexion.conector();
                String sql = "select U.habilitado, nombre, password, rol, idUsuario from Usuarios U, Roles_has_Usuarios, Roles where user ='" + usuario
                        + "'  and password = BINARY '" + pass + "' and Usuarios_idUsuario = idUsuario and Roles_idRol = idRol and U.habilitado = 1";
                PreparedStatement pst = con.prepareStatement(sql);

                ResultSet rs = pst.executeQuery(sql);
                if (rs.next()) {

                    String rol = rs.getString("rol");
                    String habilitado = rs.getString("U.habilitado");
                    String contra = rs.getString("password");
                    //
                    // me guardo el idUsuario para emplearlo en otros formularios
                    //  
                    IDuser = rs.getInt("idUsuario");
                    
                    if ("1".equals(habilitado)) {
                        if (rol.equalsIgnoreCase("Administrador")) {
                            dispose(); // destruirá la ventana de Interface(login) y sus componentes
                            //JOptionPane.showMessageDialog(null, "Bienvenido a la Aplicación Tickets - Administrador " + usuario + " " + rol + " " + habilitado);
                            new Administrador().setVisible(true);
                        } else if (rol.equalsIgnoreCase("Tecnico")) {
                            dispose(); // destruirá la ventana de Interface(login) y sus componentes
                            //JOptionPane.showMessageDialog(null, "Bienvenido a la Aplicación Tickets - Técnico " + usuario + " " + rol + " " + habilitado);
                            new Tecnico().setVisible(true);
                        } else if (rol.equalsIgnoreCase("Cliente")) {
                            dispose(); // destruirá la ventana de Interface(login) y sus componentes
                            //JOptionPane.showMessageDialog(null, "Bienvenido a la Aplicación Tickets - Cliente " + usuario + " " + rol + " " + habilitado);
                            new Cliente().setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Tickets NO PIULA " + usuario + " " + rol + " " + habilitado);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario no habilitado " + usuario + " " + rol + " " + habilitado);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Datos de Acceso incorrectos");
                    jTextUser.setText("");
                    jPassword.setText("");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error inicio sesión, contacte con el administrador");
                System.err.println("Error al acceder" + e);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No puede haber campos vacíos");
        }
    }//GEN-LAST:event_jButtonInicioSesionActionPerformed

    private void jPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordActionPerformed

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String args[]) throws SQLException {

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
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonInicioSesion;
    private javax.swing.JLabel jLabelFondo;
    private javax.swing.JLabel jLabelIconoUser;
    private javax.swing.JLabel jLabelPass;
    private javax.swing.JLabel jLabelUser;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JTextField jTextUser;
    // End of variables declaration//GEN-END:variables
}
