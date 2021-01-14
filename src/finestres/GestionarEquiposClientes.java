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
public class GestionarEquiposClientes extends javax.swing.JFrame {

    // variable que recoge el nombre del usuario que ha iniciado sesión
    // definido en Interface.java y asignada en la línea 144 (+/-)
    String user;
    //variable para recoger el valor de idUsuario que ha iniciado sesión
    //definido en Interface.java y asignada en la línea 165 (+/-)
    int IDuser;
    // creo la variable que me permita enviar datos entre interfaces
    // y guardará el equipo que queremos consultar al dar click
    // en cualquiera de los registros visualizados en la tabla
    public static int IDequipo_update = 0;
    // declaro globalmente el objeto de la clase DefaultTableModel
    // para poder utilizarlo en cualquier parte de nuestra clase
    // model es la que nos va a permitir poder general el click en la tabla
    // y establecer la interacción con los datos que se muestren en la tabla
    DefaultTableModel model = new DefaultTableModel();
    // con que usuario he iniciado sesión y determinar que tipo de rol estoy trabajando
    int usuario_sesion;

    /**
     * Constructor del form GestionarClientes
     */
    public GestionarEquiposClientes() {
        initComponents();
        user = Interface.usuario;
        IDuser = Interface.IDuser;
        usuario_sesion = Interface.usuario_sesion;

        setSize(800, 400);
        setResizable(false);
        setTitle("Cliente - Sesión de " + user);
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

            String sql = "SELECT * FROM equipos EQU, Usuarios USU, roles_has_usuarios RHU ";
            sql += "WHERE USU.idUsuario = EQU.Usuarios_idUsuario AND ";
            sql += "USU.idUsuario = RHU.Usuarios_idUsuario AND ";
            sql += "USU.idUsuario = " + IDuser;

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            //
            // Creo la tabla de equipos
            //
            jTable_equipos = new JTable(model);
            // creo el contenedor y hago visible la tabla dentro de él
            jScrollPane_equipos.setViewportView(jTable_equipos);

            model.addColumn("ID");
            model.addColumn("Tipo");
            model.addColumn("Marca");
            model.addColumn("Modelo");
            model.addColumn("Número de Serie");
            model.addColumn("Status");

            // y relleno la tabla
            while (rs.next()) {
                // creo vector de tipo objetos
                Object[] celda = new Object[6]; // 6 columnas

                celda[0] = rs.getInt("idEquipos");
                celda[4] = rs.getString("num_serie");
                celda[1] = rs.getString("tipo");
                celda[2] = rs.getString("marca");
                celda[3] = rs.getString("modelo");

                switch (rs.getInt("EQU.habilitado")) {
                    case 0:
                        celda[5] = "Inactivo";
                        break;
                    case 1:
                        celda[5] = "Activo";
                        break;
                    case 2:
                        celda[5] = "Incidencia";
                        break;
                }
                // agregar nueva fila
                model.addRow(celda); // añadimos la nueva fila ya rellenada  
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Error al llenar la tabla de Equipos del Cliente" + e);
            JOptionPane.showMessageDialog(null, "Error al mostrar la tabla Equipos del Cliente, contacte con el Administrador");
        }

        ObtenerDatosTabla();

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
        jScrollPane_equipos = new javax.swing.JScrollPane();
        jTable_equipos = new javax.swing.JTable();
        jLabel_footer = new javax.swing.JLabel();
        cmb_estatus = new javax.swing.JComboBox<>();
        jButton_Mostrar = new javax.swing.JButton();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(800, 400));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Equipos registrados");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

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

        getContentPane().add(jScrollPane_equipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 760, 180));

        jLabel_footer.setForeground(java.awt.Color.white);
        jLabel_footer.setText("Andreu Garcia Coll - UIB 2020");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, -1, -1));

        cmb_estatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmb_estatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Activo", "Inactivo", "Incidencia abierta" }));
        getContentPane().add(cmb_estatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 30, 130, 30));

        jButton_Mostrar.setBackground(new java.awt.Color(10, 47, 63));
        jButton_Mostrar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Mostrar.setForeground(java.awt.Color.white);
        jButton_Mostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/buscar.png"))); // NOI18N
        jButton_Mostrar.setBorder(null);
        jButton_Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_MostrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 30, 30, 30));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MostrarActionPerformed

        String sql = "";
        // seleccion actuará como flag, dependiendo del tipo de rol
        // realizará un Query distinto en el string sql en concreto del
        // filtro de estado (Todos, Activo, Inactivo, Incidencia abierta)
        int seleccion = cmb_estatus.getSelectedIndex();
        // borramos el contenido de la tabla 
        // limpia las filas
        model.setRowCount(0);
        // limpia las columnas
        model.setColumnCount(0);

        try {
            Connection con = Conexion.conector();

            sql = "SELECT * FROM equipos EQU, Usuarios USU, roles_has_usuarios RHU ";
            sql += "WHERE USU.idUsuario = EQU.Usuarios_idUsuario AND ";
            sql += "USU.idUsuario = RHU.Usuarios_idUsuario AND ";
            sql += "USU.idUsuario = " + IDuser;

            // si tenemnos el filtro de activo, inactivo o incidencia abierta
            //
            // Query selectivo dependiendo del filtro status del jcomboBox (cmb_estatus)
            //
            switch (seleccion) {
                case 1:
                    sql += " AND EQU.habilitado = 1"; // Activo
                    break;
                case 2:
                    sql += " AND EQU.habilitado = 0"; // Inactivo
                    break;
                case 3:
                    sql += " AND EQU.habilitado = 2"; // Incidencia abierta
                    break;
                default:
                    break;
            }

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            //
            // Creo nuevamente la tabla de equipos
            // ya que la había borrado previamente con los métodos setxxxCount(0)
            //
            jTable_equipos = new JTable(model);
            // creo el contenedor y hago visible la tabla dentro de él
            jScrollPane_equipos.setViewportView(jTable_equipos);

            // definimos las columnas que estarán en el jScrollPane
            model.addColumn("ID");
            model.addColumn("Tipo");
            model.addColumn("Marca");
            model.addColumn("Modelo");
            model.addColumn("Número de Serie");
            model.addColumn("Status");

            // y relleno la tabla
            while (rs.next()) {
                // creo vector de tipo objetos
                Object[] celda = new Object[6]; // 6 columnas

                celda[0] = rs.getInt("idEquipos");
                celda[4] = rs.getString("num_serie");
                celda[1] = rs.getString("tipo");
                celda[2] = rs.getString("marca");
                celda[3] = rs.getString("modelo");

                switch (rs.getInt("EQU.habilitado")) {
                    case 0:
                        celda[5] = "Inactivo";
                        break;
                    case 1:
                        celda[5] = "Activo";
                        break;
                    case 2:
                        celda[5] = "Incidencia";
                        break;
                }
                // agregar nueva fila
                model.addRow(celda); // añadimos la nueva fila ya rellenada   
            }
            con.close();

        } catch (SQLException e) {
            System.err.println("Error al recuperar los registros de la tabla Equipos del Cliente" + e);
            JOptionPane.showMessageDialog(null, "Error al recuperar los registros de la tabla Equipos del Cliente, contacte con el Administrador");
        }
        //
        // reutilizo el método ObtenerDatosTabla
        // para la realización del filtraje
        //
        ObtenerDatosTabla();


    }//GEN-LAST:event_jButton_MostrarActionPerformed

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
            java.util.logging.Logger.getLogger(GestionarEquiposClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarEquiposClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarEquiposClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarEquiposClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionarEquiposClientes().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_estatus;
    private javax.swing.JButton jButton_Mostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JScrollPane jScrollPane_equipos;
    private javax.swing.JTable jTable_equipos;
    // End of variables declaration//GEN-END:variables

    public void ObtenerDatosTabla() {
        //
        // evento para escuchar los clicks del ratón que damos en la tabla
        //
        jTable_equipos.addMouseListener(new MouseAdapter() {
            @Override  //sobreescribimos el método mouseClicked
            // me guardará momentáneamente el evento que se está generando
            public void mouseClicked(MouseEvent e) {
                // la variable e guardará el evento de manera temporal
                // la fila seleccionada la recogerá el evento getPoint en e
                // la columna será 0 porque nos interesa guardar la columna ID
                // la cual me permitirá obtener la información del cliente
                int fila_point = jTable_equipos.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    // ahora que tengo una fila seleccionada
                    // 
                    // como columna_point es 0 va directo a su valor
                    // para guardar el valor tengo que hacer un casting del model
                    // guardamos el valor del ID del cliente seleccionado al clickar
                    IDequipo_update = (int) model.getValueAt(fila_point, columna_point);
                    // creo una instancia entre clases
                    InformacionEquipoCliente informacionEquipoCliente = new InformacionEquipoCliente();
                    informacionEquipoCliente.setVisible(true);
                }
            }
        });
    }
}
