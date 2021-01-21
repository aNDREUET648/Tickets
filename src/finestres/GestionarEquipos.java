package finestres;

import java.sql.*;
import clases.Conexion;
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

/**
 *
 * @author aNDREUET
 */
public class GestionarEquipos extends javax.swing.JFrame {

    // variable que recoge el nombre del usuario que ha iniciado sesión
    // definido en Interface.java y asignada en la línea 144 (+/-)
    String user;
    //variable para recoger el valor de idUsuario que ha iniciado sesión
    //definido en Interface.java y asignada en la línea 165 (+/-)
    int id_usuario;
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
    public GestionarEquipos() {
        initComponents();
        user = Interface.usuario;
        id_usuario = Interface.IDuser;
        usuario_sesion = Interface.usuario_sesion;

        setSize(800, 450);
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

            String sql = "SELECT * FROM equipos EQU, Usuarios USU, roles_has_usuarios RHU ";
            sql += "WHERE USU.idUsuario = EQU.Usuarios_idUsuario AND ";
            sql += "USU.idUsuario = RHU.Usuarios_idUsuario AND ";
            //
            // Query selectivo dependiendo del rol del usuario
            //
            switch (usuario_sesion) {
                case 0: // el que hace la consulta es un Cliente
                    sql += "USU.idUsuario = " + id_usuario + " ";
                    break;
                case 1: // el que hace la consulta es un Administrador
                    // para no tener errores de sintaxis
                    // añado esta sentencia sql que es una TAUTOLOGÍA
                    // para que se ejecute siempre, porque no necesitava ninguna
                    // pero el AND de arriba me obliga a poner algo
                    sql += "EQU.habilitado = EQU.habilitado "; // todos los equipos
                    break;
                case 2: // el que hace la consulta es un Técnico
                    sql += "RHU.Roles_idRol = 3 "; // equipos de Clientes
                    break;
                default:
                    break;
            }
            sql += "ORDER BY apellidos, nombre ";

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            //
            // Creo la tabla de equipos
            //
            jTable_equipos = new JTable(model);
            // creo el contenedor y hago visible la tabla dentro de él
            jScrollPane_equipos.setViewportView(jTable_equipos);
            // definimos las columnas que estarán en el jScrollPane
            if (usuario_sesion == 0) {
                // si es un cliente que muestre sólo el ID del equipo
                model.addColumn("ID equipo");
            } else {
                // si es un Administrador o un Técnico que muestre de quien son los equipos
                model.addColumn("Apellidos y nombre");
            }

            model.addColumn("Tipo");
            model.addColumn("Marca");
            model.addColumn("Modelo");

            if (usuario_sesion == 0) {
                // si es un cliente que muestre sólo el ID del equipo
                model.addColumn("Número de Serie");
            } else {
                // si es un Administrador o un Técnico que muestre de quien son los equipos
                model.addColumn("ID equipo");
            }

            model.addColumn("Status");
            // y relleno la tabla
            while (rs.next()) {
                // creo vector de tipo objetos
                Object[] celda = new Object[6]; // 6 columnas
                if (usuario_sesion == 0) {
                    // si es un cliente que muestre sólo el ID del equipo
                    celda[0] = rs.getInt("idEquipos");
                    celda[4] = rs.getString("num_serie");
                } else {
                    // si es un Administrador o un Técnico que muestre de quien son los equipos
                    celda[0] = rs.getString("apellidos") + ", " + rs.getString("nombre");
                    celda[4] = rs.getInt("idEquipos");
                }
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
            System.err.println("Error al llenar la tabla de Equipos " + e);
            JOptionPane.showMessageDialog(null, "Error al mostrar la tabla Equipos, contacte con el Administrador");
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
        jLabel2 = new javax.swing.JLabel();
        jScrollPane_equipos = new javax.swing.JScrollPane();
        jTable_equipos = new javax.swing.JTable();
        jLabel_footer = new javax.swing.JLabel();
        cmb_estatus = new javax.swing.JComboBox<>();
        jButton_Mostrar = new javax.swing.JButton();
        jButton_Imprimir = new javax.swing.JButton();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(800, 450));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Equipos registrados");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(java.awt.Color.white);
        jLabel2.setText("Estado:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, -1));

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

        getContentPane().add(jScrollPane_equipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 760, 230));

        jLabel_footer.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_footer.setText("Andreu Garcia Coll - UIB 2020");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, -1, -1));

        cmb_estatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmb_estatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Activo", "Inactivo", "Incidencia abierta" }));
        getContentPane().add(cmb_estatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 130, 30));

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
        getContentPane().add(jButton_Mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 40, 30, 30));

        jButton_Imprimir.setBackground(new java.awt.Color(10, 47, 63));
        jButton_Imprimir.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Imprimir.setForeground(java.awt.Color.white);
        jButton_Imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/impresora.png"))); // NOI18N
        jButton_Imprimir.setToolTipText("Listado de Equipos registrados (PDF)");
        jButton_Imprimir.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ImprimirActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 320, 80, 80));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MostrarActionPerformed
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
            String sql = "SELECT * FROM equipos EQU, Usuarios USU, roles_has_usuarios RHU ";
            sql += "WHERE USU.idUsuario = EQU.Usuarios_idUsuario AND ";
            sql += "USU.idUsuario = RHU.Usuarios_idUsuario AND ";
            //
            // Query selectivo dependiendo del filtro status del jcomboBox (cmb_estatus)
            //
            switch (seleccion) {
                case 1:
                    sql += "EQU.habilitado = 1 AND ";
                    break;
                case 2:
                    sql += "EQU.habilitado = 0 AND ";
                    break;
                case 3:
                    sql += "EQU.habilitado = 2 AND ";
                    break;
                default:
                    break;
            }
            //
            // Query selectivo dependiendo del rol del usuario
            //
            switch (usuario_sesion) {
                case 0: // el que hace la consulta es un Cliente
                    sql += "USU.idUsuario = " + id_usuario + " ";
                    break;
                case 1: // el que hace la consulta es un Administrador
                    // para no tener errores de sintaxis
                    // añado esta sentencia sql que es una TAUTOLOGÍA
                    // para que se ejecute siempre, porque no necesitava ninguna
                    // pero el AND de arriba me obliga a poner algo
                    sql += "EQU.habilitado = EQU.habilitado "; // todos los equipos
                    break;
                case 2: // el que hace la consulta es un Técnico
                    sql += "RHU.Roles_idRol = 3 "; // equipos de Clientes
                    break;
                default:
                    break;
            }
            sql += "ORDER BY apellidos, nombre ";

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

            if (usuario_sesion == 0) {
                // si es un cliente que muestre sólo el ID del equipo
                model.addColumn("ID equipo");
            } else {
                // si es un Administrador o un Técnico que muestre de quien son los equipos
                model.addColumn("Nombre y apellidos");
            }

            model.addColumn("Tipo");
            model.addColumn("Marca");
            model.addColumn("Modelo");

            if (usuario_sesion == 0) {
                // si es un cliente que muestre sólo el ID del equipo
                model.addColumn("Número de Serie");
            } else {
                // si es un Administrador o un Técnico que muestre de quien son los equipos
                model.addColumn("ID equipo");
            }

            model.addColumn("Status");
            // y relleno la tabla

            while (rs.next()) {
                // creo vector de tipo objetos
                Object[] celda = new Object[6]; // 6 columnas
                if (usuario_sesion == 0) {
                    // si es un cliente que muestre sólo el ID del equipo
                    celda[0] = rs.getInt("idEquipos");
                    celda[4] = rs.getString("num_serie");
                } else {
                    // si es un Administrador o un Técnico que muestre de quien son los equipos
                    celda[0] = rs.getString("apellidos") + ", " + rs.getString("nombre");
                    celda[4] = rs.getInt("idEquipos");
                }
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
            System.err.println("Error al recuperar los registros de la tabla Equipos" + e);
            JOptionPane.showMessageDialog(null, "Error al recuperar los registros de la tabla Equipos, contacte con el Administrador");
        }
        //
        // reutilizo el método ObtenerDatosTabla
        // para la realización del filtraje
        //
        ObtenerDatosTabla();


    }//GEN-LAST:event_jButton_MostrarActionPerformed

    private void jButton_ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ImprimirActionPerformed

        // filtramos según el estado
        int seleccion = cmb_estatus.getSelectedIndex();
        String filtro = cmb_estatus.getSelectedItem().toString();
        Document documento = new Document(PageSize.A4.rotate(), 0, 0, 0, 10);
        // toda código para crear archivo en pdf necesita estar
        // dentro de una estructura try..catch
        try {
            // recupero la ruta del sistema operativo
            String ruta = System.getProperty("user.home");
            // lo guardo en el escritorio y le añado nombre y apellidos como filename
            // y la extensión que lógicamente será pdf
            ruta = ruta + "/Desktop/Listado de Equipos -  Filtro " + filtro + ".pdf";
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
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Listado de Usuarios - Filtro: " + filtro + "\n \n");
            // doy formato al párrafo
            parrafo.setFont(FontFactory.getFont("Tahoma", 6, Font.NORMAL, BaseColor.DARK_GRAY));

            // una vez definido todo, abro el documento
            // e inserto el banner y el párafo inicial
            documento.open();
            documento.add(header);
            documento.add(parrafo);

            // creo una tabla con los datos generales que vienen de la bd
            // tablaClientes tendrá 5 columnas
            PdfPTable tabla = new PdfPTable(7);
            float[] columnWidths = new float[]{15f, 45f, 22f, 22f, 45f, 40f, 20f};
            tabla.setWidthPercentage(95);
            tabla.setWidths(columnWidths);

            tabla.addCell("ID equipo");
            tabla.addCell("Apellidos y nombre");
            tabla.addCell("Tipo");
            tabla.addCell("Marca");
            tabla.addCell("Modelo");
            tabla.addCell("Número de serie");
            tabla.addCell("Estado actual");

            // consultamos a la bd la información que irá en el pdf
            try {
                Connection con = Conexion.conector();

                String sql = "SELECT * FROM equipos EQU, Usuarios USU, roles_has_usuarios RHU ";
                sql += "WHERE USU.idUsuario = EQU.Usuarios_idUsuario AND ";
                sql += "USU.idUsuario = RHU.Usuarios_idUsuario AND ";
                //
                // Query selectivo dependiendo del filtro status del jcomboBox (cmb_estatus)
                //
                switch (seleccion) {
                    case 1: // estado = "Activo"
                        sql += "EQU.habilitado = 1 AND ";
                        break;
                    case 2: // estado = "Inactivo"
                        sql += "EQU.habilitado = 0 AND ";
                        break;
                    case 3: // estado = "Incidencia abierto"
                        sql += "EQU.habilitado = 2 AND ";
                        break;
                    default: // estado = "Todos"
                        break;
                }
                //
                // Query selectivo dependiendo del rol del usuario
                //
                switch (usuario_sesion) {
                    case 0: // el que hace la consulta es un Cliente
                        sql += "USU.idUsuario = " + id_usuario + " ";
                        break;
                    case 1: // el que hace la consulta es un Administrador
                        // para no tener errores de sintaxis
                        // añado esta sentencia sql que es una TAUTOLOGÍA
                        // para que se ejecute siempre, porque no necesitava ninguna
                        // pero el AND de arriba me obliga a poner algo
                        sql += "EQU.habilitado = EQU.habilitado "; // todos los equipos
                        break;
                    case 2: // el que hace la consulta es un Técnico
                        sql += "RHU.Roles_idRol = 3 "; // equipos de Clientes
                        break;
                    default:
                        break;
                }
                sql += "ORDER BY apellidos, nombre ";

                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                //lleno tabla de los clientes con los valores devueltos de la consulta
                if (rs.next()) {
                    do {

                        tabla.addCell(Integer.toString(rs.getInt("idEquipos")));
                        tabla.addCell(rs.getString("apellidos") + ", " + rs.getString("nombre"));
                        tabla.addCell(rs.getString("tipo"));
                        tabla.addCell(rs.getString("marca"));
                        tabla.addCell(rs.getString("modelo"));
                        tabla.addCell(rs.getString("num_serie"));
                        switch (rs.getString("EQU.habilitado")) {
                            case "1":
                                tabla.addCell("Activo");
                                break;
                            case "0":
                                tabla.addCell("Inactivo");
                                break;
                            case "2":
                                tabla.addCell("Incidencia");
                                break;
                            default:  // no tocaría que aparecesiese este mensaje
                                tabla.addCell("XD");
                                break;
                        }

                    } while (rs.next());
                    //envío la tablaCliente al documento
                    documento.add(tabla);
                }
            } catch (SQLException e) {
                System.err.println("Error al generar el listado de equipos " + e);
                JOptionPane.showMessageDialog(null, "Error al generar el listado de equipos, contacte con el Administrador");
            }

            documento.close();
            JOptionPane.showMessageDialog(null, "Listado de equipos creado correctamente");

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
            java.util.logging.Logger.getLogger(GestionarEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarEquipos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionarEquipos().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_estatus;
    private javax.swing.JButton jButton_Imprimir;
    private javax.swing.JButton jButton_Mostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
                if (usuario_sesion == 0) {
                    // si es un cliente coge el id del equipo de la primera columna
                    columna_point = 0;
                } else {
                    // si es técnico o administrador la tiene en la 5ª columna
                    columna_point = 4;
                }

                if (fila_point > -1) {
                    // ahora que tengo una fila seleccionada
                    // 
                    // como columna_point es 0 va directo a su valor
                    // para guardar el valor tengo que hacer un casting del model
                    // guardamos el valor del ID del cliente seleccionado al clickar
                    IDequipo_update = (int) model.getValueAt(fila_point, columna_point);
                    // creo una instancia entre clases
                    InformacionEquipo informacionEquipo = new InformacionEquipo();
                    informacionEquipo.setVisible(true);
                }
            }
        });
    }
}
