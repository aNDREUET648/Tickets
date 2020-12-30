package finestres;

import java.sql.*;
import clases.Conexion;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Calendar; // fecha y hora
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/*
 * @author aNDREUET
 */
public class RegistrarEquipo extends javax.swing.JFrame {

    int IDcliente_update = 0;
    String user = "", nom_cliente = "";

    /**
     * Constructor form RegistrarEquipo
     */
    public RegistrarEquipo() {
        initComponents();
        user = Interface.usuario;
        IDcliente_update = GestionarClientes.IDcliente_update;

        try {
            Connection con = Conexion.conector();
            String sql = "select  nombre, apellidos from Usuarios where "
                    + " idUsuario = '" + IDcliente_update + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                nom_cliente = rs.getString("nombre") + " " + rs.getString("apellidos");

            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Error al consultar el nombre del cliente " + e);
            JOptionPane.showMessageDialog(null, "Error al al consultar el nombre del cliente, contacte con el Administrador");
        }

        // ahora si que puedo ponerlo porque ya tengo el valor de nombre_cliente
        setTitle("Añadir nuevo equipo para  " + nom_cliente);
        setSize(630, 445);
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

        txt_nombreCliente.setText(nom_cliente);

    }  // fin del Constructor

    //
    //  Colocamos en icono que aparecerá en la barra de tareas
    //  Que nos nos aparezca la famosa tacita de Café
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane_observaciones = new javax.swing.JTextPane();
        jLabel_Titulo = new javax.swing.JLabel();
        jLabel_Nombre = new javax.swing.JLabel();
        jLabel_Nombre1 = new javax.swing.JLabel();
        jLabel_Nombre2 = new javax.swing.JLabel();
        jLabel_Nombre3 = new javax.swing.JLabel();
        jLabel_Nombre4 = new javax.swing.JLabel();
        jLabel_Nombre5 = new javax.swing.JLabel();
        txt_nombreCliente = new javax.swing.JTextField();
        txt_modelo = new javax.swing.JTextField();
        txt_num_serie = new javax.swing.JTextField();
        cmb_tipoequipo = new javax.swing.JComboBox<>();
        cmb_marcas = new javax.swing.JComboBox<>();
        jButton_Registrar = new javax.swing.JButton();
        jLabel_footer = new javax.swing.JLabel();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(jTextPane_observaciones);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 330, 230));

        jLabel_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_Titulo.setForeground(java.awt.Color.white);
        jLabel_Titulo.setText("Registro de equipo");
        getContentPane().add(jLabel_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        jLabel_Nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Nombre.setForeground(java.awt.Color.white);
        jLabel_Nombre.setText("Nombre del Cliente:");
        getContentPane().add(jLabel_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        jLabel_Nombre1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Nombre1.setForeground(java.awt.Color.white);
        jLabel_Nombre1.setText("Tipo de equipo:");
        getContentPane().add(jLabel_Nombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        jLabel_Nombre2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Nombre2.setForeground(java.awt.Color.white);
        jLabel_Nombre2.setText("Marca:");
        getContentPane().add(jLabel_Nombre2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jLabel_Nombre3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Nombre3.setForeground(java.awt.Color.white);
        jLabel_Nombre3.setText("Modelo:");
        getContentPane().add(jLabel_Nombre3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, -1, -1));

        jLabel_Nombre4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Nombre4.setForeground(java.awt.Color.white);
        jLabel_Nombre4.setText("Número de serie:");
        getContentPane().add(jLabel_Nombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, -1, -1));

        jLabel_Nombre5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Nombre5.setForeground(java.awt.Color.white);
        jLabel_Nombre5.setText("Observaciones:");
        getContentPane().add(jLabel_Nombre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, -1, -1));

        txt_nombreCliente.setBackground(new java.awt.Color(153, 153, 255));
        txt_nombreCliente.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_nombreCliente.setForeground(java.awt.Color.white);
        txt_nombreCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombreCliente.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_nombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 210, -1));

        txt_modelo.setBackground(new java.awt.Color(153, 153, 255));
        txt_modelo.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_modelo.setForeground(java.awt.Color.white);
        txt_modelo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_modelo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 210, -1));

        txt_num_serie.setBackground(new java.awt.Color(153, 153, 255));
        txt_num_serie.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_num_serie.setForeground(java.awt.Color.white);
        txt_num_serie.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_num_serie.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_num_serie, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 210, -1));

        cmb_tipoequipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laptop", "Desktop", "Impresora", "Multifunción" }));
        getContentPane().add(cmb_tipoequipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        cmb_marcas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acer", "Apple", "Asus", "Brother", "Canon", "Dell", "HP", "Epson", "Lenovo", "OKI", "Samsung", "Toshiba", "Xerox" }));
        getContentPane().add(cmb_marcas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        jButton_Registrar.setBackground(new java.awt.Color(153, 153, 255));
        jButton_Registrar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Registrar.setForeground(java.awt.Color.white);
        jButton_Registrar.setText("Registrar equipo");
        jButton_Registrar.setBorder(null);
        jButton_Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RegistrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 310, 210, 35));

        jLabel_footer.setText("Andreu Garcia Coll - UIB 2020");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, -1, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 445));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RegistrarActionPerformed

        int validacion = 0; // flag por si quedaron campos de texto vacíos
        int habilitado = 1; // por defecto está activo. Si estatus es Activo
        String tipo_equipo, marca, modelo, num_serie, fecha_ingreso,
                estatus, observaciones;
        // Calendar es una clase abstracta y no podermos hacer un new
        // tenemos que llamarla con getinstance
        Calendar calendar = Calendar.getInstance();
        Timestamp fecha = new Timestamp(new java.util.Date().getTime());

        tipo_equipo = cmb_tipoequipo.getSelectedItem().toString();
        marca = cmb_marcas.getSelectedItem().toString();
        modelo = txt_modelo.getText().trim();
        num_serie = txt_num_serie.getText().trim();
        observaciones = jTextPane_observaciones.getText();
        habilitado = 1;
        
        fecha_ingreso = Integer.toString(calendar.get(Calendar.DATE));

        if (modelo.equals("")) {
            txt_modelo.setBackground(Color.red);
            validacion++;
        }
        if (num_serie.equals("")) {
            txt_num_serie.setBackground(Color.red);
            validacion++;
        }
        if (observaciones.equals("")) {
            jTextPane_observaciones.setText("Sin observaciones");
            // validacion++;
        }

        if (validacion == 0) {

            try {
                Connection con = Conexion.conector();
                String sql = "insert into equipos values (?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con.prepareStatement(sql);

                // El idEquipo pero como es AutoIncremental es igual lo que ponga
                // porque el pondrá el id que quiera, por eso pongo 0
                pst.setInt(1, 0);
                pst.setInt(1, 0);
                pst.setString(2, tipo_equipo); // tipo
                pst.setString(3, marca); // marca
                pst.setString(4, modelo); // modelo
                pst.setString(5, num_serie); // número de serie
                pst.setTimestamp(6, fecha); // fecha
                pst.setString(7, observaciones); // observaciones
                pst.setInt(8, habilitado); // habilitado (status: Activo, Inactivo)
                pst.setInt(9, IDcliente_update); // foreign key del id del usuario

                pst.executeUpdate();
                con.close();
                
                txt_nombreCliente.setBackground(Color.GREEN);
                txt_modelo.setBackground(Color.GREEN);
                txt_num_serie.setBackground(Color.GREEN);
                
                JOptionPane.showMessageDialog(null, "Nuevo equipo registrado");
                this.dispose();

            } catch (SQLException e) {
                System.err.println("Error al registrar equipo " + e);
                JOptionPane.showMessageDialog(null, "Error al registrar equipo, contacte con el administrador");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos");
        }


    }//GEN-LAST:event_jButton_RegistrarActionPerformed

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
            java.util.logging.Logger.getLogger(RegistrarEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarEquipo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_marcas;
    private javax.swing.JComboBox<String> cmb_tipoequipo;
    private javax.swing.JButton jButton_Registrar;
    private javax.swing.JLabel jLabel_Nombre;
    private javax.swing.JLabel jLabel_Nombre1;
    private javax.swing.JLabel jLabel_Nombre2;
    private javax.swing.JLabel jLabel_Nombre3;
    private javax.swing.JLabel jLabel_Nombre4;
    private javax.swing.JLabel jLabel_Nombre5;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane_observaciones;
    private javax.swing.JTextField txt_modelo;
    private javax.swing.JTextField txt_nombreCliente;
    private javax.swing.JTextField txt_num_serie;
    // End of variables declaration//GEN-END:variables
}
