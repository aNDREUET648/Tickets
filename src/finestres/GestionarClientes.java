package finestres;

import java.sql.*;
import clases.Conexion;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
//
//   agrego las clases que voy a utilizar de la librería itext5
//   al no pertenecer al JDK, NetBeans no los incluirá automáticamente
//
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

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

        setSize(1000, 500);
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
            String sql = "SELECT idUsuario, apellidos, nombre, user, email, registrado_por FROM ";
            sql += "Usuarios , Roles_has_Usuarios WHERE ";
            sql += "Usuarios_idUsuario = idUsuario AND ";
            sql += "Roles_idRol = 3 ";
            sql += "ORDER BY apellidos, nombre";
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
            model.addColumn("Apellidos");
            model.addColumn("Nombre");
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
        // Utilizo el método ObtenerDatosTabla
        // que nuevamente emplearé al darle al botón mostrar
        //
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
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_clientes = new javax.swing.JTable();
        jButton_RegistrarClientes = new javax.swing.JButton();
        jButton_Mostrar = new javax.swing.JButton();
        cmb_estatus = new javax.swing.JComboBox<>();
        jLabel_footer = new javax.swing.JLabel();
        jButton_Imprimir = new javax.swing.JButton();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(800, 400));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Clientes registrados");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Estado:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, -1, -1));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 940, 180));

        jButton_RegistrarClientes.setBackground(new java.awt.Color(10, 47, 63));
        jButton_RegistrarClientes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_RegistrarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/addUser.png"))); // NOI18N
        jButton_RegistrarClientes.setToolTipText("Agregar un nuevo cliente");
        jButton_RegistrarClientes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_RegistrarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RegistrarClientesActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_RegistrarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 300, 80, 80));

        jButton_Mostrar.setBackground(new java.awt.Color(10, 47, 63));
        jButton_Mostrar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Mostrar.setForeground(java.awt.Color.white);
        jButton_Mostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/buscar.png"))); // NOI18N
        jButton_Mostrar.setToolTipText("Pulsa para filtrar");
        jButton_Mostrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_MostrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 30, 30));

        cmb_estatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmb_estatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Activo", "Inactivo" }));
        cmb_estatus.setToolTipText("Filtraje de los clientes para su visualización y exportación ");
        getContentPane().add(cmb_estatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, 130, 30));

        jLabel_footer.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_footer.setText("Andreu Garcia Coll - UIB 2020");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, -1, -1));

        jButton_Imprimir.setBackground(new java.awt.Color(10, 47, 63));
        jButton_Imprimir.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Imprimir.setForeground(java.awt.Color.white);
        jButton_Imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/impresora.png"))); // NOI18N
        jButton_Imprimir.setToolTipText("Listado de clientes registrados (PDF)");
        jButton_Imprimir.setBorder(null);
        jButton_Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ImprimirActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 300, 80, 80));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_RegistrarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RegistrarClientesActionPerformed
        //  creamos una instancia de clases
        RegistrarClientes registrarClientes = new RegistrarClientes();
        registrarClientes.setVisible(true);
    }//GEN-LAST:event_jButton_RegistrarClientesActionPerformed

    private void jButton_MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MostrarActionPerformed

        // filtramos según el estado
        String seleccion = cmb_estatus.getSelectedItem().toString();

        // cliente activo o inactivo
        int activo = -1;
        switch (seleccion) {
            case "Activo":
                activo = 1;
                break;
            case "Inactivo":
                activo = 0;
                break;
            default:
                break;
        }
        // borramos el contenido de la tabla 
        // limpia las filas
        model.setRowCount(0);
        // limpia las columnas
        model.setColumnCount(0);

        try {
            String sql = "";
            Connection con = Conexion.conector();
            // ahora vendría la instrucción hacia la bd pero ahora voy a utilizar dos instrucciones dinámicas a la bd.
            // una instrucción mostrará todos los incidentes y la otra filtrará según el estado
            // 
            if (seleccion.equalsIgnoreCase("Todos")) {
                sql = "SELECT idUsuario, apellidos, nombre, user, email, registrado_por FROM ";
                sql += "Usuarios , Roles_has_Usuarios WHERE ";
                sql += "Usuarios_idUsuario = idUsuario AND ";
                sql += "Roles_idRol = 3 ";
                sql += "ORDER BY apellidos, nombre";
            } else {

                sql = "SELECT idUsuario, apellidos, nombre, user, email, registrado_por FROM ";
                sql += "Usuarios USU, Roles_has_Usuarios ROL WHERE ";
                sql += "Usuarios_idUsuario = idUsuario AND ";
                sql += "Roles_idRol = 3 AND ";
                sql += "USU.habilitado = " + activo + " ";
                sql += "ORDER BY apellidos, nombre";
            }
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
            model.addColumn("Apellidos");
            model.addColumn("Nombre");
            model.addColumn("username");
            model.addColumn("em@il");
            model.addColumn("Modificado por");
            // y relleno la tabla
            while (rs.next()) {

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
            System.err.println("Error al recuperar los registros de los Clientes Técnico " + e);
            JOptionPane.showMessageDialog(null, "Error al recuperar los registros de los Clientes Técnico, contacte con el Administrador");
        }

        //
        // Utilizo el método ObtenerDatosTabla
        // que nuevamente emplearé al darle al botón mostrar
        //
        ObtenerDatosTabla();

    }//GEN-LAST:event_jButton_MostrarActionPerformed

    private void jButton_ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ImprimirActionPerformed

        String sql, seleccion = cmb_estatus.getSelectedItem().toString();
        // cliente activo o inactivo
        int activo = -1;
        switch (seleccion) {
            case "Activo":
                activo = 1;
                break;
            case "Inactivo":
                activo = 0;
                break;
            default:
                break;
        }
        Document documento = new Document(PageSize.A4.rotate(), 10, 10, 0, 0);
        // toda código para crear archivo en pdf necesita estar
        // dentro de una estructura try..catch
        try {
            // recupero la ruta del sistema operativo
            String ruta = System.getProperty("user.home");
            // lo guardo en el escritorio y le añado nombre y apellidos como filename
            // y la extensión que lógicamente será pdf
            ruta = ruta + "/Desktop/Listado Clientes - Filtro " + seleccion + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            // inserto la cabecera del documento que será una imagen
            // como la librería Image de itextpdf choca con la java.awt
            // coloco directamente la llamada para eliminar el conflicto
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/imatges/BannerPDF.png");
            // pongo el largo y la escala de visualización del header
            header.scaleToFit(860, 100);
            // lo alineo al centro
            header.setAlignment(Chunk.ALIGN_CENTER);
            // creo un objeto de clase Paragraph para dar formato al texto
            Paragraph parrafo = new Paragraph();
            // lo alineo al centro
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Listado de clientes - Filtro: " + seleccion + "\n \n");
            // doy formato al párrafo
            parrafo.setFont(FontFactory.getFont("Tahoma", 6, Font.NORMAL, BaseColor.DARK_GRAY));

            // una vez definido todo, abro el documento
            // e inserto el banner y el párafo inicial
            documento.open();
            documento.add(header);
            documento.add(parrafo);

            // creo una tabla con los datos generales que vienen de la bd
            // tablaClientes tendrá 5 columnas
            PdfPTable tabla = new PdfPTable(6);
            float[] columnWidths = new float[]{15f, 65f, 65f, 30f, 25f, 30f};
            tabla.setWidthPercentage(95);
            tabla.setWidths(columnWidths);

            tabla.addCell("ID");
            tabla.addCell("Apellidos y nombre");
            tabla.addCell("em@il");
            tabla.addCell("Teléfono");
            tabla.addCell("user");
            tabla.addCell("Estado");

            // consultamos a la bd la información que irá en el pdf
            try {
                Connection con = Conexion.conector();

                if (seleccion.equalsIgnoreCase("Todos")) {
                    sql = "SELECT idUsuario, apellidos, nombre, email, telefono, user, USU.habilitado FROM ";
                    sql += "Usuarios USU, Roles_has_Usuarios WHERE ";
                    sql += "Usuarios_idUsuario = idUsuario AND ";
                    sql += "Roles_idRol = 3 ";
                    sql += "ORDER BY apellidos, nombre";
                } else {

                    sql = "SELECT idUsuario, apellidos, nombre, email, telefono, user, USU.habilitado FROM ";
                    sql += "Usuarios USU, Roles_has_Usuarios ROL WHERE ";
                    sql += "Usuarios_idUsuario = idUsuario AND ";
                    sql += "Roles_idRol = 3 AND ";
                    sql += "USU.habilitado = " + activo + " ";
                    sql += "ORDER BY apellidos, nombre";
                }

                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                //lleno tabla de los clientes con los valores devueltos de la consulta
                if (rs.next()) {
                    do {

                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2) + ", " + rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));
                        tabla.addCell(rs.getString(6));
                        if (rs.getString("USU.habilitado").equals("1")) {
                            tabla.addCell("Activo");
                        } else {
                            tabla.addCell("Inactivo");
                        }

                    } while (rs.next());
                    //envío la tablaCliente al documento
                    documento.add(tabla);
                }
            } catch (SQLException e) {
                System.err.println("Error al generar el listado de los clientes " + e);
                JOptionPane.showMessageDialog(null, "Error al generar el listado de los clientes, contacte con el Administrador");
            }

            documento.close();
            JOptionPane.showMessageDialog(null, "Listado de clientes creado correctamente");

            // Catch de la generación del documento pdf
            // DocumentException, gestión de los errores del documento
            // IOException, gestión de los errores de entrada/salida de datos
        } catch (DocumentException | IOException e) {
            System.err.println("Error al generar pdf o en ruta de la imagen " + e);
            JOptionPane.showMessageDialog(null, "Error al generar el PDF, contacte con el Administrador");
        }

    }//GEN-LAST:event_jButton_ImprimirActionPerformed

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
    private javax.swing.JComboBox<String> cmb_estatus;
    private javax.swing.JButton jButton_Imprimir;
    private javax.swing.JButton jButton_Mostrar;
    private javax.swing.JButton jButton_RegistrarClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_clientes;
    // End of variables declaration//GEN-END:variables

    public void ObtenerDatosTabla() {
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
}
