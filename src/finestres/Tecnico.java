package finestres;

import clases.Conexion;
import java.sql.*;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
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
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;

/*
 * @author aNDREUET
 */
public class Tecnico extends javax.swing.JFrame {

    String user, nombre_usuario, apellido_usuario;
    //
    // sesion_usuario actuará como Flag 
    // la ventana de Tecnico no dejará de ejecutarse si 
    // he arrancado esta ventana desde la sesión de Administrador
    //
    int sesion_usuario; // 

    /**
     * Constructor del form Tecnico
     */
    public Tecnico() {
        initComponents();
        user = Interface.usuario;
        sesion_usuario = Administrador.sesion_usuario;
        setSize(550, 300);
        setResizable(false);
        setTitle("Rol: Técnico - Sesión de " + user);
        setLocationRelativeTo(null);
        //
        // dentro del constructor creo una estructura condicional para que no se
        // cierre este Interface si he iniciado sesión de Administrador y no
        // finalice a menos que se cierre la Interface de Administrador.java
        // 
        // En cambio, si estoy he iniciado sesión como Técnico el Interface
        // Tecnico.java se cerrará y por tanto finalizará el programa
        //
        if (sesion_usuario == 1) {
            // he iniciado sesión como Administrador
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        } else {
            // he iniciado sesión como Técnico
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

        // Fondo de pantalla de mi aplicación
        ImageIcon wallpaper = new ImageIcon("src/imatges/wallpaperPrincipal.jpg");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(),
                jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Wallpaper.setIcon(icono);
        this.repaint();

        try {
            Connection con = Conexion.conector();
            //  recupero nombre y apellidos del user que ha entrado
            String sql = "select nombre, apellidos from Usuarios where user = '" + user + "'";
            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                nombre_usuario = rs.getString("nombre");
                apellido_usuario = rs.getString("apellidos");
                // visualizo en el Interface de Técnico nombre y apellidos user
                jLabel_NombreUsuario.setText("Bienvenido " + nombre_usuario + " " + apellido_usuario);
            }
        } catch (SQLException e) {
            System.err.println("Error en la consulta del nombre del Técnico " + e);
        }
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

        jLabel_NombreUsuario = new javax.swing.JLabel();
        jButton_RegistrarCliente = new javax.swing.JButton();
        jButton_GestionarClientes = new javax.swing.JButton();
        jButton_Imprimir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_NombreUsuario.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        jLabel_NombreUsuario.setForeground(java.awt.Color.white);
        jLabel_NombreUsuario.setText("jLabel1");
        getContentPane().add(jLabel_NombreUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jButton_RegistrarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/add.png"))); // NOI18N
        jButton_RegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_RegistrarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_RegistrarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 120, 100));

        jButton_GestionarClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/informationuser.png"))); // NOI18N
        jButton_GestionarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GestionarClientesActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_GestionarClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 120, 100));

        jButton_Imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imatges/impresora.png"))); // NOI18N
        jButton_Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ImprimirActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 120, 100));

        jLabel3.setForeground(java.awt.Color.white);
        jLabel3.setText("Registrar Cliente");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setText("Gestionar Clientes");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, -1, -1));

        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setText("Imprimir Clientes");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, -1, -1));

        jLabel7.setText("Andreu Garcia Coll - UIB 2020");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, -1, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_RegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_RegistrarClienteActionPerformed
        //  creamos una instancia de clases
        RegistrarClientes registrarClientes = new RegistrarClientes();
        registrarClientes.setVisible(true);
    }//GEN-LAST:event_jButton_RegistrarClienteActionPerformed

    private void jButton_GestionarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GestionarClientesActionPerformed
        //  creamos una instancia de clases

        // clase nombre_objeto = nuevo objeto de la misma clase;
        GestionarClientes gestionarClientes = new GestionarClientes();
        gestionarClientes.setVisible(true); // objeto.metodo
    }//GEN-LAST:event_jButton_GestionarClientesActionPerformed

    private void jButton_ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ImprimirActionPerformed
        Document documento = new Document();
        // toda código para crear archivo en pdf necesita estar
        // dentro de una estructura try..catch
        try {
            // recupero la ruta del sistema operativo
            String ruta = System.getProperty("user.home");
            // lo guardo en el escritorio y le añado nombre y apellidos como filename
            // y la extensión que lógicamente será pdf
            ruta = ruta + "/Desktop/ListadoClientes.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(ruta));

            // inserto la cabecera del documento que será una imagen
            // como la librería Image de itextpdf choca con la java.awt
            // coloco directamente la llamada para eliminar el conflicto
            com.itextpdf.text.Image header = com.itextpdf.text.Image.getInstance("src/imatges/BannerPDF.png");
            // pongo el largo y la escala de visualización del header
            header.scaleToFit(650, 1000);
            // lo alineo al centro
            header.setAlignment(Chunk.ALIGN_CENTER);
            // creo un objeto de clase Paragraph para dar formato al texto
            Paragraph parrafo = new Paragraph();
            // lo alineo al centro
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Listado de clientes \n \n");
            // doy formato al párrafo
            parrafo.setFont(FontFactory.getFont("Tahoma", 10, Font.BOLD, BaseColor.DARK_GRAY));

            // una vez definido todo, abro el documento
            // e inserto el banner y el párafo inicial
            documento.open();
            documento.add(header);
            documento.add(parrafo);

            // creo una tabla con los datos generales que vienen de la bd
            // tablaClientes tendrá 5 columnas
            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("ID");
            tabla.addCell("Nombre");
            tabla.addCell("Apellidos");
            tabla.addCell("em@il");
            tabla.addCell("Teléfono");

            // consultamos a la bd la información que irá en el pdf
            try {
                Connection con = Conexion.conector();
                String sql = "SELECT idUsuario, nombre, apellidos, email, telefono"
                        + " FROM Usuarios U, roles_has_usuarios R where "
                        + "Roles_idRol = 3 and R.habilitado = 1 and Usuarios_idUsuario = idUsuario";
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                //lleno tabla de los clientes con los valores devueltos de la consulta
                if (rs.next()) {
                    do {

                        tabla.addCell(rs.getString(1));
                        tabla.addCell(rs.getString(2));
                        tabla.addCell(rs.getString(3));
                        tabla.addCell(rs.getString(4));
                        tabla.addCell(rs.getString(5));

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
            java.util.logging.Logger.getLogger(Tecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tecnico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tecnico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_GestionarClientes;
    private javax.swing.JButton jButton_Imprimir;
    private javax.swing.JButton jButton_RegistrarCliente;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_NombreUsuario;
    private javax.swing.JLabel jLabel_Wallpaper;
    // End of variables declaration//GEN-END:variables
}
