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


/*
 * @author aNDREUET
 */
public class GestionarIncidenciasCliente extends javax.swing.JFrame {

    // variable que recoge el nombre del usuario que ha iniciado sesión
    // definido en Interface.java y asignada en la línea 144 (+/-)
    String user;
    //variable para recoger el valor de idUsuario que ha iniciado sesión
    //definido en Interface.java y asignada en la línea 165 (+/-)
    int id_usuario;
    // creo la variable que me permita enviar datos entre interfaces
    // y guardará el equipo que queremos consultar al dar click
    // en cualquiera de los registros visualizados en la tabla
    public static int IDincidente_update = 0;
    // declaro globalmente el objeto de la clase DefaultTableModel
    // para poder utilizarlo en cualquier parte de nuestra clase
    // model es la que nos va a permitir poder general el click en la tabla
    // y establecer la interacción con los datos que se muestren en la tabla
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Cronstructor del form HistoricoClientes
     */
    public GestionarIncidenciasCliente() {
        initComponents();
        user = Interface.usuario;
        id_usuario = Interface.IDuser;

        setSize(1000, 420);
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

        // ahora busco todos los incidentes de un cliente
        try {
            Connection con = Conexion.conector();
            String sql = "SELECT idIncidente, tipo, fecha_crea, INCID.descripcion, estado, INTER.descripcion ";
            sql += "FROM incidentes INCID, intervenciones INTER, estados EST, tipos ";
            sql += "WHERE idIncidente = Incidentes_idIncidente AND ";
            sql += "idIntervencion = Intervenciones_idIntervencion AND ";
            sql += "idTipo = tipos_idTipo AND ";
            sql += "INCID.Usuarios_idUsuario = '" + id_usuario + "' ";
            sql += "ORDER BY idIncidente DESC, idIntervencion DESC";

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            //
            // Creo la tabla de incidentes
            //
            jTable_incidentes = new JTable(model);
            // creo el contenedor y hago visible la tabla dentro de él
            jScrollPane_incidentes.setViewportView(jTable_incidentes);
            // definimos las columnas que estarán en el jScrollPane
            model.addColumn("ID");
            model.addColumn("Tipo");
            model.addColumn("Fecha creación");
            model.addColumn("Descripción");
            model.addColumn("Estado");
            model.addColumn("Intervención");

            // y relleno la tabla
            while (rs.next()) {

                Object[] celda = new Object[6]; // 6 columnas
                celda[0] = rs.getInt("idIncidente");
                celda[1] = rs.getString("tipo");
                celda[2] = rs.getTimestamp("fecha_crea");
                celda[3] = rs.getString("INCID.descripcion");
                celda[4] = rs.getString("estado");
                celda[5] = rs.getString("INTER.descripcion");
                // agregar nueva fila
                model.addRow(celda); // añadimos la nueva fila ya rellenada  
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Error al llenar Histórico de Incidentes Cliente " + e);
            JOptionPane.showMessageDialog(null, "Error al llenar Histórico de Incidentes Cliente, contacte con el Administrador");
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

        jLabel_Titulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane_incidentes = new javax.swing.JScrollPane();
        jTable_incidentes = new javax.swing.JTable();
        cmb_estatus = new javax.swing.JComboBox<>();
        jButton_Mostrar = new javax.swing.JButton();
        jButton_Imprimir = new javax.swing.JButton();
        jLabel_footer = new javax.swing.JLabel();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(1000, 420));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_Titulo.setForeground(java.awt.Color.white);
        jLabel_Titulo.setText("Gestión de Incidentes");
        getContentPane().add(jLabel_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("Estado:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, -1, 30));

        jTable_incidentes.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane_incidentes.setViewportView(jTable_incidentes);

        getContentPane().add(jScrollPane_incidentes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 960, 180));

        cmb_estatus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmb_estatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Inicio", "Asignado", "En Proceso", "Finalizado" }));
        cmb_estatus.setToolTipText("Filtraje de los incidentes para su visualización y exportación");
        cmb_estatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_estatusActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_estatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 50, 130, 30));

        jButton_Mostrar.setBackground(new java.awt.Color(10, 47, 63));
        jButton_Mostrar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Mostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/buscar.png"))); // NOI18N
        jButton_Mostrar.setToolTipText("Pulsa para filtrar");
        jButton_Mostrar.setBorder(null);
        jButton_Mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_MostrarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 30, 30));

        jButton_Imprimir.setBackground(new java.awt.Color(10, 47, 63));
        jButton_Imprimir.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Imprimir.setForeground(java.awt.Color.white);
        jButton_Imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/impresora.png"))); // NOI18N
        jButton_Imprimir.setToolTipText("Listado de incidencias (PDF)");
        jButton_Imprimir.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton_Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ImprimirActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 290, 80, 80));

        jLabel_footer.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_footer.setText("Andreu Garcia Coll - UIB 2020");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 350, -1, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 420));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_estatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_estatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_estatusActionPerformed

    private void jButton_MostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_MostrarActionPerformed

        // filtro según el estado
        String seleccion = cmb_estatus.getSelectedItem().toString();
        // borro el contenido de la tabla 
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

                sql = "SELECT idIncidente, tipo, fecha_crea, INCID.descripcion, estado, INTER.descripcion ";
                sql += "FROM incidentes INCID, intervenciones INTER, estados EST, tipos ";
                sql += "WHERE idIncidente = Incidentes_idIncidente AND ";
                sql += "idIntervencion = Intervenciones_idIntervencion AND ";
                sql += "idTipo = tipos_idTipo AND ";
                sql += "INCID.Usuarios_idUsuario = '" + id_usuario + "' ";
                sql += "ORDER BY idIncidente DESC, idIntervencion DESC";

            } else {    // pues sino, lo que se haya seleccionado en el comboBox

                sql = "SELECT idIncidente, tipo, fecha_crea, INCID.descripcion, estado, INTER.descripcion ";
                sql += "FROM incidentes INCID, intervenciones INTER, estados EST, tipos ";
                sql += "WHERE idIncidente = Incidentes_idIncidente AND ";
                sql += "idIntervencion = Intervenciones_idIntervencion AND ";
                sql += "idTipo = tipos_idTipo AND ";
                sql += "estado = '" + seleccion + "' AND ";
                sql += "INCID.Usuarios_idUsuario = '" + id_usuario + "' ";
                sql += "ORDER BY idIncidente DESC, idIntervencion DESC";

            }
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            //
            // Creo nuevamente la tabla de incidentes
            // ya que la había borrado previamente con los métodos setxxxCount(0)
            //
            jTable_incidentes = new JTable(model);
            // creo el contenedor y hago visible la tabla dentro de él
            jScrollPane_incidentes.setViewportView(jTable_incidentes);
            // definimos las columnas que estarán en el jScrollPane
            model.addColumn("ID");
            model.addColumn("Tipo");
            model.addColumn("Fecha creación");
            model.addColumn("Descripción");
            model.addColumn("Estado");
            model.addColumn("Intervención");

            // y relleno la tabla
            while (rs.next()) {

                Object[] celda = new Object[6]; // 6 columnas
                celda[0] = rs.getInt("idIncidente");
                celda[1] = rs.getString("tipo");
                celda[2] = rs.getTimestamp("fecha_crea");
                celda[3] = rs.getString("INCID.descripcion");
                celda[4] = rs.getString("estado");
                celda[5] = rs.getString("INTER.descripcion");
                // agregar nueva fila
                model.addRow(celda); // añadimos la nueva fila ya rellenada  
            }
            con.close();

        } catch (SQLException e) {
            System.err.println("Error al recuperar los registros de los Incidentes Cliente " + e);
            JOptionPane.showMessageDialog(null, "Error al recuperar los registros de los Incidentes Cliente, contacte con el Administrador");
        }
        //
        // Vuelvo otra vez a utilizar el método ObtenerDatosTabla
        // para ver los datos filtrados
        //
        ObtenerDatosTabla();

    }//GEN-LAST:event_jButton_MostrarActionPerformed

    private void jButton_ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ImprimirActionPerformed

        // filtramos según el estado
        String seleccion = cmb_estatus.getSelectedItem().toString();
        Document documento = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
        // toda código para crear archivo en pdf necesita estar
        // dentro de una estructura try..catch
        try {
            // recupero la ruta del sistema operativo
            String ruta = System.getProperty("user.home");
            // lo guardo en el escritorio y le añado nombre y apellidos como filename
            // y la extensión que lógicamente será pdf
            ruta = ruta + "/Desktop/Histórico Incidencias - user " + user + " Filtro " + seleccion + ".pdf";
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
            parrafo.add("Histórico Incidentes - Filtro: " + seleccion + "\n \n");
            // doy formato al párrafo
            parrafo.setFont(FontFactory.getFont("Tahoma", 6, Font.NORMAL, BaseColor.DARK_GRAY));

            // una vez definido todo, abro el documento
            // e inserto el banner y el párafo inicial
            documento.open();
            documento.add(header);
            documento.add(parrafo);

            // consultamos a la bd la información que irá en el pdf
            try {
                Connection con = Conexion.conector();
                
                
                String sql = "SELECT * ";
                sql += "FROM usuarios USU, incidentes INCID, intervenciones INTER, estados EST, tipos, roles ROL, roles_has_usuarios RHU ";
                sql += "WHERE idIncidente = INTER.Incidentes_idIncidente AND ";
                sql += "INCID.Usuarios_idUsuario = idUsuario AND ";
                sql += "RHU.Usuarios_idUsuario = idUsuario AND ";
                sql += "ROL.idRol = RHU.Roles_idRol AND ";
                sql += "idIntervencion = EST.Intervenciones_idIntervencion AND ";
                sql += "idTipo = INCID.tipos_idTipo AND ";

                // si tenemnos el filtro de activo, inactivo o incidencia abierta
                //
                // Query selectivo dependiendo del filtro status del jcomboBox (cmb_estatus)
                //
                switch (seleccion) {
                    case "Inicio":
                    case "Asignado":
                    case "En Proceso":
                    case "Finalizado":
                        sql += "estado= '" + seleccion + "' AND ";
                        break;
                    default: // Todos
                        break;
                }

                sql += "idUsuario = '" + id_usuario + "' ";
                sql += "ORDER BY idIncidente DESC, idIntervencion DESC";

                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                //lleno tabla de los clientes con los valores devueltos de la consulta
                
                if (rs.next()) {
                    
                    if (rs.getRow() == 1) {
                        //
                        // Cabecera y datos generales de los incidentes
                        //
                        Paragraph parrafo2 = new Paragraph();
                        parrafo2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
                        parrafo2.setFont(FontFactory.getFont("Tahoma", 16, Font.NORMAL, BaseColor.DARK_GRAY));
                        parrafo2.add("                         Nombre y apellidos: " + rs.getString("nombre") + " " + rs.getString("apellidos") + "                              "
                                + "Rol: " + rs.getString("rol") + "\n");
                        parrafo2.add("                         em@il: " + rs.getString("email") + "\n");
                        parrafo2.add("                         Teléfono: " + rs.getString("telefono") + "\n\n\n\n");
                        documento.add(parrafo2);

                    }
 
                    // creo una tabla con los datos generales que vienen de la bd
                    // tablaClientes tendrá 5 columnas
                    PdfPTable tabla = new PdfPTable(6);
                    float[] columnWidths = new float[]{10f, 25f, 20f, 50f, 25f, 60f};
                    tabla.setWidthPercentage(95);
                    tabla.setWidths(columnWidths);

                    tabla.addCell("ID");
                    tabla.addCell("Fecha");
                    tabla.addCell("Tipo");
                    tabla.addCell("Descripción");
                    tabla.addCell("Estado");
                    tabla.addCell("Descripción intervención");
                    
                    do {

                        tabla.addCell(Integer.toString(rs.getInt("idIncidente")));
                        tabla.addCell(rs.getTimestamp("fecha_intervencion").toString());
                        tabla.addCell(rs.getString("Tipo"));
                        tabla.addCell(rs.getString("INCID.descripcion"));
                        tabla.addCell(rs.getString("estado"));
                        tabla.addCell(rs.getString("INTER.descripcion"));

                    } while (rs.next());
                    //envío la tablaCliente al documento
                    documento.add(tabla);
                }
            } catch (SQLException e) {
                System.err.println("Error al generar el listado de incidentes " + e);
                JOptionPane.showMessageDialog(null, "Error al generar el listado de incidentes, contacte con el Administrador");
            }

            documento.close();
            JOptionPane.showMessageDialog(null, "Listado de incidentes creado correctamente");

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
            java.util.logging.Logger.getLogger(GestionarIncidenciasCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarIncidenciasCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarIncidenciasCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarIncidenciasCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GestionarIncidenciasCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmb_estatus;
    private javax.swing.JButton jButton_Imprimir;
    private javax.swing.JButton jButton_Mostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JScrollPane jScrollPane_incidentes;
    private javax.swing.JTable jTable_incidentes;
    // End of variables declaration//GEN-END:variables

    public void ObtenerDatosTabla() {
        //
        // evento para escuchar los clicks del ratón que damos en la tabla
        //
        jTable_incidentes.addMouseListener(new MouseAdapter() {
            @Override  //sobreescribimos el método mouseClicked
            // me guardará momentáneamente el evento que se está generando
            public void mouseClicked(MouseEvent e) {
                // la variable e guardará el evento de manera temporal
                // la fila seleccionada la recogerá el evento getPoint en e
                // la columna será 0 porque nos interesa guardar la columna ID
                // la cual me permitirá obtener la información del cliente
                int fila_point = jTable_incidentes.rowAtPoint(e.getPoint());
                int columna_point = 0;

                if (fila_point > -1) {
                    // ahora que tengo una fila seleccionada
                    // 
                    // como columna_point es 0 va directo a su valor
                    // para guardar el valor tengo que hacer un casting del model
                    // guardamos el valor del ID del cliente seleccionado al clickar
                    IDincidente_update = (int) model.getValueAt(fila_point, columna_point);
                    //JOptionPane.showMessageDialog(null, "Ticket número "+ IDincidente_update);
                    // creo una instancia entre clases
                    InformacionIncidenciasCliente informacionIncidenciasCliente = new InformacionIncidenciasCliente();
                    informacionIncidenciasCliente.setVisible(true);
                }
            }
        });
    }
}
