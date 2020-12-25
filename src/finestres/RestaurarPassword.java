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

/*
 * @author aNDREUET
 */
public class RestaurarPassword extends javax.swing.JFrame {

    String user = "", user_update = "";

    /**
     * Constructor del form RestaurarPassword
     */
    public RestaurarPassword() {
        initComponents();
        user = Interface.usuario;
        user_update = GestionarUsuarios.user_update;

        setSize(360, 260);
        setResizable(false);
        setTitle("Cambio de Password para " + user_update);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        txt_passwordConfirmacion = new javax.swing.JPasswordField();
        jButton_RestaurarPassword = new javax.swing.JButton();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Cambio de password");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(java.awt.Color.white);
        jLabel7.setText("Nuevo Password:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(java.awt.Color.white);
        jLabel8.setText("Confirmar Password:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txt_password.setBackground(new java.awt.Color(153, 153, 255));
        txt_password.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_password.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_password.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_password, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 210, -1));

        txt_passwordConfirmacion.setBackground(new java.awt.Color(153, 153, 255));
        txt_passwordConfirmacion.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_passwordConfirmacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_passwordConfirmacion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_passwordConfirmacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, -1));

        jButton_RestaurarPassword.setBackground(new java.awt.Color(153, 153, 255));
        jButton_RestaurarPassword.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_RestaurarPassword.setForeground(java.awt.Color.white);
        jButton_RestaurarPassword.setText("Restaurar Password");
        jButton_RestaurarPassword.setBorder(null);
        jButton_RestaurarPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RestaurarPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_RestaurarPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 210, 35));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 260));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_RestaurarPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RestaurarPasswordActionPerformed
        String password, confirmacion_password;

        password = txt_password.getText().trim();
        confirmacion_password = txt_passwordConfirmacion.getText().trim();
        //
        // valido de los campos password
        //
        if (!password.equals("") && !confirmacion_password.equals("")) {
            if (password.equals(confirmacion_password)) {
                //
                // los campos de password no están vacíos
                // y además son iguales
                //
                try {
                    Connection con = Conexion.conector();
                    String sql = "update Usuarios set password=? where  user= '" + user_update + "'";
                    PreparedStatement pst = con.prepareStatement(sql);

                    pst.setString(1, password);
                    pst.executeUpdate();
                    con.close();

                    txt_password.setBackground(Color.green);
                    txt_passwordConfirmacion.setBackground(Color.green);
                    JOptionPane.showMessageDialog(null, "Contraseña cambiada");
                    this.dispose();
                    
                } catch (SQLException e) {
                    System.err.println("Error en el cambio de password" + e);
                }
            } else {
                //
                // los campos de password no coinciden
                //
                txt_passwordConfirmacion.setBackground(Color.red);
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            }
        } else {
            //
            // algún campo está vacío
            //
            txt_password.setBackground(Color.red);
            txt_passwordConfirmacion.setBackground(Color.red);
            JOptionPane.showMessageDialog(null, "No puede haber campos vacíos");
        }
    }//GEN-LAST:event_jButton_RestaurarPasswordActionPerformed

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
            java.util.logging.Logger.getLogger(RestaurarPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RestaurarPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RestaurarPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RestaurarPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RestaurarPassword().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_RestaurarPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JPasswordField txt_passwordConfirmacion;
    // End of variables declaration//GEN-END:variables
}