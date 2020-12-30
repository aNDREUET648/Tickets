package finestres;

import java.sql.*;
import clases.Conexion;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author aNDREUET
 */
public class GestionarClientes extends javax.swing.JFrame {

    // variable que recoge el nombre del usuario que ha iniciado sesión
    // definido en Interface.java y asignada en la línea 144 (+/-)
    String user;
    //variable para recoger el valor de idUsuario que ha iniciado sesión
    //definido en Interface.java y asignada en la línea 165 (+/-)
    int id_usuario;
    // creo la variable que me permita enviar datos entre interfaces
    // y guardará el cliente que queremos consultar al dar click
    // en cualquiera de los registros visualizados en la tabla
    public static int IDcliente_update = 0;
    // declaro globalmente el objeto de la clase DefaultTableModel
    // para poder utilizarlo en cualquier parte de nuestra clase
    // model es la que nos va a permitir poder general el click en la tabla
    // y establecer la interacción con los datos que se muestren en la tabla
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Constructor del form GestionarClientes
     */
    public GestionarClientes() {
        initComponents();
        user = Interface.usuario;
        id_usuario = Interface.IDuser;

        setSize(630, 350);
        setResizable(false);
        setTitle("Técnico - Sesión de " + user);
        setLocationRelativeTo(null);
        
        // evita que se cierre el programa cuando cerramos esta interfaz
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        // Fondo de pantalla de mi aplicación
        ImageIcon wallpaper = new ImageIcon("src/imatges/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(),
                jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Wallpaper.setIcon(icono);
        this.repaint();

        try {
            Connection con = Conexion.conector();
            String sql = "select idUsuario, nombre, apellidos, user, email, registrado_por from Usuarios , Roles_has_Usuarios "
                    + "where  Usuarios_idUsuario = idUsuario and Roles_idRol = 3";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            //
            // Creo conexión hacia la tabla
            //
            jTable_clientes = new JTable(model);
            // hacemos visible nuestra tabla dentro del jScrollPane 1
            jScrollPane1.setViewportView(jTable_clientes);
            // definimos las columnas que estarán en el jScrollPane
            model.addColumn("ID");
            model.addColumn("Nombre");
            model.addColumn("Apellidos");
            model.addColumn("username");
            model.addColumn("em@il");
            model.addColumn("Modificado por");
            // y relleno la tabla
            while (rs.next()) {
                /*
                estoy probando el casting de abajo. Aquí el idUsuario es int pero 
                cuando lo lee celda[0]=rs.getObject(i+1) lo ve como tipo long
                lo arreglo guardando celda[0] como como rs.getInt("idUsuario")
                Why?
                JOptionPane.showMessageDialog(null, "ID DEL CLIENTE " + rs.getInt("idUsuario"));
                 */
                // creo vector de tipo objetos
                Object[] celda = new Object[6]; // 6 columnas
                celda[0] = rs.getInt("idUsuario");
                for (int i = 1; i < 6; i++) {
                    // bucle para ir llenando cada columna de la fila
                    // la primera fila del rs.next() es 1 y no 0 (i+1)
                    celda[i] = rs.getObject(i + 1);
                }
                // agregar nueva fila
                model.addRow(celda); // añadimos la nueva fila ya rellenada  
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Error al llenar la tabla de Clientes " + e);
            JOptionPane.showMessageDialog(null, "Error al mostrar la tabla Clientes, contacte con el Administrador");
        }

        //
        // evento para escuchar los clicks del ratón que damos en la tabla
        //
        jTable_clientes.addMouseListener(new MouseAdapter() {
            @Override  //sobreescribimos el método mouseClicked
            // me guardará momentáneamente el evento que se está generando
            public void mouseClicked(MouseEvent e) {
                // la variable e guardará el evento de manera temporal
                // la fila seleccionada la recogerá el evento getPoint en e
                // la columna será 0 porque nos interesa guardar la columna ID
                // la cual me permitirá obtener la información del cliente
                int fila_point = jTable_clientes.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    // ahora que tengo una fila seleccionada
                    // 
                    // como columna_point es 0 va directo a su valor
                    // para guardar el valor tengo que hacer un casting del model
                    // guardamos el valor del ID del cliente seleccionado al clickar
                    IDcliente_update = (int) model.getValueAt(fila_point, columna_point);
                    // creo una instancia entre clases
                    Informacion_Cliente informacion_cliente = new Informacion_Cliente();
                    informacion_cliente.setVisible(true);

                }
            }
        });
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_clientes = new javax.swing.JTable();
        jLabel_footer = new javax.swing.JLabel();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(630, 350));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Clientes registrados");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, -1, -1));

        jTable_clientes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable_clientes);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 630, 180));

        jLabel_footer.setText("Andreu Garcia Coll - UIB 2020");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, -1, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(GestionarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionarClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_clientes;
    // End of variables declaration//GEN-END:variables
}
