package finestres;

import java.sql.*;
import clases.Conexion;
//
//   agrego las clases que voy a utilizar de la librería itext5
//   al no pertenecer al JDK, NetBeans no los incluirá automáticamente
//
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import java.awt.Color;
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
public class Informacion_Cliente extends javax.swing.JFrame {

    //
    //  definición de las variables globales
    //
    // model interactuará con mi tabla (j_Table_equipos) creada en el form
    DefaultTableModel model = new DefaultTableModel();
    // IDcliente_update recupera valor que viene de otro interfaz
    int IDcliente_update = 0;
    // IDequipo enviará su valor a otras interfaces
    public static int IDequipo = 0;
    String user = "";
    int IDuser = 0;

    /**
     * Constructor del form Informacion_Cliente
     */
    public Informacion_Cliente() {
        initComponents();
        user = Interface.usuario;
        IDuser = Interface.IDuser;
        IDcliente_update = GestionarClientes.IDcliente_update;

        setSize(630, 470);
        setResizable(false);
        setLocationRelativeTo(null);

        // evita que se cierre el programa cuando cerramos esta interfaz
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Fondo de pantalla de mi aplicación
        ImageIcon wallpaper = new ImageIcon("src/imatges/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(),
                jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Wallpaper.setIcon(icono);
        this.repaint();
        //
        // rellenamos los campos del cliente en el formulario y los títulos del formulario
        //
        try {
            Connection con = Conexion.conector();
            String sql = "select * from Usuarios where idUsuario = '" + IDcliente_update + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                setTitle("Información del cliente " + rs.getString("nombre") + " " + rs.getString("apellidos") + " - Sesión de " + user);
                jLabel_Titulo.setText("Información del cliente " + rs.getString("nombre") + " " + rs.getString("apellidos"));

                txt_nombre.setText(rs.getString("nombre"));
                txt_email.setText(rs.getString("email"));
                txt_apellidos.setText(rs.getString("apellidos"));
                txt_telefono.setText(rs.getString("telefono"));
                txt_ultimaModificacion.setText(Integer.toString(rs.getInt("registrado_por")));
            }
            con.close();
        } catch (SQLException e) {
            System.err.println("Error al cargar el Cliente " + e);
            JOptionPane.showMessageDialog(null, "Error al mostrar el Cliente, contacte con el Administrador");
        }
        //
        // rellenamos la tabla con los equipos registrados del cliente
        //
        try {
            Connection con = Conexion.conector();
            String sql = "select * from Equipos where usuarios_idUsuario = '" + IDcliente_update + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            jTable_equipos = new JTable(model);
            jScrollPane_equipos.setViewportView(jTable_equipos);

            model.addColumn("ID equipo");
            model.addColumn("Tipo de equipo");
            model.addColumn("Marca");
            model.addColumn("Status");

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
                    IDequipo = (int) model.getValueAt(fila_point, columna_point);
                    // creo una instancia entre clases
                    InformacionEquipo informacionEquipo = new InformacionEquipo();
                    informacionEquipo.setVisible(true);
                }
            }
        });
    }  // cierre del constructor

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

        jScrollPane_equipos = new javax.swing.JScrollPane();
        jTable_equipos = new javax.swing.JTable();
        jLabel_Titulo = new javax.swing.JLabel();
        jLabel_Nombre = new javax.swing.JLabel();
        jLabel_email = new javax.swing.JLabel();
        jLabel_apellidos = new javax.swing.JLabel();
        jLabel_telefono = new javax.swing.JLabel();
        jLabel_UltimaModificacion = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_apellidos = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_ultimaModificacion = new javax.swing.JTextField();
        jButton_Registrar = new javax.swing.JButton();
        jButton_Actualizar = new javax.swing.JButton();
        jButton_ImprimirReporte = new javax.swing.JButton();
        jLabel_footer = new javax.swing.JLabel();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(jScrollPane_equipos, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 380, 180));

        jLabel_Titulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel_Titulo.setForeground(java.awt.Color.white);
        jLabel_Titulo.setText("Información del Cliente");
        getContentPane().add(jLabel_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel_Nombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_Nombre.setForeground(java.awt.Color.white);
        jLabel_Nombre.setText("Nombre:");
        getContentPane().add(jLabel_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel_email.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_email.setForeground(java.awt.Color.white);
        jLabel_email.setText("em@il:");
        getContentPane().add(jLabel_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel_apellidos.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_apellidos.setForeground(java.awt.Color.white);
        jLabel_apellidos.setText("Apellidos:");
        getContentPane().add(jLabel_apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel_telefono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_telefono.setForeground(java.awt.Color.white);
        jLabel_telefono.setText("telefono");
        getContentPane().add(jLabel_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jLabel_UltimaModificacion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel_UltimaModificacion.setForeground(java.awt.Color.white);
        jLabel_UltimaModificacion.setText("Última modificación por:");
        getContentPane().add(jLabel_UltimaModificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        txt_nombre.setBackground(new java.awt.Color(153, 153, 255));
        txt_nombre.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_nombre.setForeground(java.awt.Color.white);
        txt_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 210, -1));

        txt_email.setBackground(new java.awt.Color(153, 153, 255));
        txt_email.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_email.setForeground(java.awt.Color.white);
        txt_email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_email.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 210, -1));

        txt_apellidos.setBackground(new java.awt.Color(153, 153, 255));
        txt_apellidos.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_apellidos.setForeground(java.awt.Color.white);
        txt_apellidos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_apellidos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 210, -1));

        txt_telefono.setBackground(new java.awt.Color(153, 153, 255));
        txt_telefono.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_telefono.setForeground(java.awt.Color.white);
        txt_telefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_telefono.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 210, -1));

        txt_ultimaModificacion.setBackground(new java.awt.Color(153, 153, 255));
        txt_ultimaModificacion.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        txt_ultimaModificacion.setForeground(java.awt.Color.white);
        txt_ultimaModificacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_ultimaModificacion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txt_ultimaModificacion.setEnabled(false);
        getContentPane().add(txt_ultimaModificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 210, -1));

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
        getContentPane().add(jButton_Registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 210, 35));

        jButton_Actualizar.setBackground(new java.awt.Color(153, 153, 255));
        jButton_Actualizar.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jButton_Actualizar.setForeground(java.awt.Color.white);
        jButton_Actualizar.setText("Actualizar cliente");
        jButton_Actualizar.setBorder(null);
        jButton_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 210, 35));

        jButton_ImprimirReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/impresora.png"))); // NOI18N
        jButton_ImprimirReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ImprimirReporteActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_ImprimirReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 120, 100));

        jLabel_footer.setText("Andreu Garcia Coll - UIB 2020");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, -1, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RegistrarActionPerformed
        // hacemos una instancia de clase
        RegistrarEquipo registrar_equipo = new RegistrarEquipo();
        registrar_equipo.setVisible(true);

    }//GEN-LAST:event_jButton_RegistrarActionPerformed

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed

        int validacion = 0; // flag por si quedaron campos de texto vacíos
        String nombre, apellidos, email, telefono;

        nombre = txt_nombre.getText().trim();
        apellidos = txt_apellidos.getText().trim();
        email = txt_email.getText().trim();
        telefono = txt_telefono.getText().trim();

        if (nombre.equals("")) {
            txt_nombre.setBackground(Color.RED);
            validacion++;
        }
        if (apellidos.equals("")) {
            txt_apellidos.setBackground(Color.RED);
            validacion++;
        }
        if (email.equals("")) {
            txt_email.setBackground(Color.RED);
            validacion++;
        }
        if (telefono.equals("")) {
            txt_telefono.setBackground(Color.RED);
            validacion++;
        }

        if (validacion == 0) {
            try {
                Connection con = Conexion.conector();
                String sql = "update Usuarios set nombre=?, apellidos=?, email=?, telefono=? ,registrado_por=? "
                        + "where idUsuario = '" + IDcliente_update + "'";
                PreparedStatement pst = con.prepareStatement(sql);

                pst.setString(1, nombre);
                pst.setString(2, apellidos);
                pst.setString(3, email);
                pst.setString(4, telefono);
                pst.setInt(5, IDuser);

                pst.executeUpdate();
                con.close();

                txt_nombre.setBackground(Color.green);
                txt_apellidos.setBackground(Color.green);
                txt_email.setBackground(Color.green);
                txt_telefono.setBackground(Color.green);
                txt_ultimaModificacion.setText(user);
                JOptionPane.showMessageDialog(null, "Actualizado correctamente");
                this.dispose(); // se cerrará la ventana y liberará recursos
                Limpiar();  // limpiamos los campos del form

            } catch (SQLException e) {
                System.err.println("Error en actualizar cliente " + e);
                JOptionPane.showMessageDialog(null, "Error al actualizar el cliente, contacte con el Administrador");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debes rellenar todos los campos");
        }

    }//GEN-LAST:event_jButton_ActualizarActionPerformed

    private void jButton_ImprimirReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ImprimirReporteActionPerformed

        // creamos un objeto de la clase Document (de iTextpdf)    
        Document documento = new Document();
        // la documentos creados con itextpdf van dentro de un try catch
        try {
            // recupero la ruta del sistema operativo
            String ruta = System.getProperty("user.home");
            // lo guardo en el escritorio y le añado nombre y apellidos como filename
            // y la extensión que lógicamente será pdf
            ruta = ruta + "/Desktop/" + txt_nombre.getText().trim() + " " + txt_apellidos.getText().trim() + ".pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            // inserto la cabecera del documento que será una imagen
            // como la librería Image de itextpdf choca con la java.awt
            // coloco directamente la llamada para eliminar el conflicto
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/imatges/BannerPDF.png");
            // pongo el largo y la escala de visualización del header
            header.scaleToFit(650, 100);
            // lo alineo al centro
            header.setAlignment(Chunk.ALIGN_CENTER);
            // creo un objeto de clase Paragraph para dar formato al texto
            Paragraph parrafo = new Paragraph();
            // lo alineo al centro
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Información del cliente \n \n");
            // doy formato al párrafo
            parrafo.setFont(FontFactory.getFont("Tahoma", 10, Font.BOLD, BaseColor.DARK_GRAY));

            // una vez definido todo, abro el documento
            // e inserto el banner y el párafo inicial
            documento.open();
            documento.add(header);
            documento.add(parrafo);

            // creo una tabla con los datos generales que vienen de la bd
            // tablaClientes tendrá 5 columnas
            PdfPTable tablaCliente = new PdfPTable(5);
            tablaCliente.addCell("ID");
            tablaCliente.addCell("Nombre");
            tablaCliente.addCell("Apellidos");
            tablaCliente.addCell("em@il");
            tablaCliente.addCell("Teléfono");

            // consultamos a la bd la información que irá en el pdf
            try {
                Connection con = Conexion.conector();
                String sql = "select  idUsuario, nombre, apellidos, email, telefono"
                        + " from Usuarios where  idUsuario = '" + IDcliente_update + "'";
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                //lleno tablaCliente con los valores devueltos de la consulta
                if (rs.next()) {
                    do {

                        tablaCliente.addCell(rs.getString(1));
                        tablaCliente.addCell(rs.getString(2));
                        tablaCliente.addCell(rs.getString(3));
                        tablaCliente.addCell(rs.getString(4));
                        tablaCliente.addCell(rs.getString(5));

                    } while (rs.next());
                    //envío la tablaCliente al documento
                    documento.add(tablaCliente);
                }
                
                //con.close();
                
                Paragraph parrafo2 = new Paragraph();
                // lo alineo al centro
                parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo2.add("\n \n Equipos registrados \n \n");
                // doy formato al párrafo
                parrafo2.setFont(FontFactory.getFont("Tahoma", 10, Font.BOLD, BaseColor.DARK_GRAY));

                documento.add(parrafo2);

                PdfPTable tablaEquipos = new PdfPTable(4);
                tablaEquipos.addCell("ID");
                tablaEquipos.addCell("Tipo");
                tablaEquipos.addCell("Marca");
                tablaEquipos.addCell("Status");

                try {
                    Connection con2 = Conexion.conector();
                    String sql2 = "select  idEquipos, tipo, marca, habilitado "
                            + "from Equipos where  usuarios_idUsuario = '" + IDcliente_update + "'";
                    PreparedStatement pst2 = con2.prepareStatement(sql2);
                    ResultSet rs2 = pst2.executeQuery();
                    //lleno tablaEquipos con los valores devueltos de la consulta
                    if (rs2.next()) {
                        do {

                            tablaEquipos.addCell(rs2.getString(1));
                            tablaEquipos.addCell(rs2.getString(2));
                            tablaEquipos.addCell(rs2.getString(3));
                            if (rs2.getInt(4) == 1) {
                                tablaEquipos.addCell("Activo");
                            } else {
                                tablaEquipos.addCell("Inactivo");
                            }

                        } while (rs2.next());
                        //envío la tablaEquipos al documento
                        documento.add(tablaEquipos);
                    }
                    
                    //con2.close();
                    
                } catch (SQLException e) {
                    System.err.println("Error al generar listado de equipos del cliente " + e);
                    JOptionPane.showMessageDialog(null, "Error al generar listado de equipos del cliente, contacte con el Administrador");
                }

            } catch (SQLException e) {
                System.err.println("Error al obtener los datos del cliente " + e);
                JOptionPane.showMessageDialog(null, "Error al obtener los datos del cliente, contacte con el Administrador");
            }
            // cierro finalmente el documento
            documento.close();
            JOptionPane.showMessageDialog(null, "Informe correctamente creado");
            // Catch de la generación del documento pdf
            // DocumentException, gestión de los errores del documento
            // IOException, gestión de los errores de entrada/salida de datos
        } catch (DocumentException | IOException e) {
            System.err.println("Error en pdf o en ruta de la imagen " + e);
            JOptionPane.showMessageDialog(null, "Error al generar el PDF, contacte con el Administrador");
        }
    }//GEN-LAST:event_jButton_ImprimirReporteActionPerformed

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
            java.util.logging.Logger.getLogger(Informacion_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Informacion_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Informacion_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Informacion_Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Informacion_Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_ImprimirReporte;
    private javax.swing.JButton jButton_Registrar;
    private javax.swing.JLabel jLabel_Nombre;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_UltimaModificacion;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_apellidos;
    private javax.swing.JLabel jLabel_email;
    private javax.swing.JLabel jLabel_footer;
    private javax.swing.JLabel jLabel_telefono;
    private javax.swing.JScrollPane jScrollPane_equipos;
    private javax.swing.JTable jTable_equipos;
    private javax.swing.JTextField txt_apellidos;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_ultimaModificacion;
    // End of variables declaration//GEN-END:variables

    public void Limpiar() {
        txt_nombre.setText("");
        txt_apellidos.setText("");
        txt_email.setText("");
        txt_telefono.setText("");
    }
}
