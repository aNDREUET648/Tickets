package finestres;

import java.sql.*;
import clases.Conexion;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/*
 * @author aNDREUET
 */
public class GraficarEstatus extends javax.swing.JFrame {

    String user;
    int IDuser;
    int Activo, Inactivo, NuevoIngreso, NoReparado, EnRevision, Reparado, Entregado;
    // vectores con una cantidad igual al número de status que tengo arriba (7)
    String[] vector_estatus_nombre = new String[7];
    int[] vector_estatus_cantidad = new int[7];
    // este vector es auxiliar y lo creo porque no está definido en mi tabla equipos
    String[] auxiliar = new String[7];

    /*
     * Constructor del form GraficarEstatus
     */
    public GraficarEstatus() {
        initComponents();

        user = Interface.usuario;
        IDuser = Interface.IDuser;

        setSize(550, 450);
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

        // lleno mi vector auxiliar[]
        auxiliar[0] = "Inactivo"; // si habilitado= 0 es Inactivo
        auxiliar[1] = "Activo";    //habilitado=1 es Activo
        auxiliar[2] = "Nuevo ingreso"; //habilitado =2  es Nuevo Ingreso
        auxiliar[3] = "No reparado";
        auxiliar[4] = "En revisión";
        auxiliar[5] = "Reparado";
        auxiliar[6] = "Entregado";

        try {
            Connection con = Conexion.conector();
            // busca la cantidad de equipos que están asignados a cada uno de 
            // los estatus que tenemos definidos para ello
            String sql = "select habilitado, count(habilitado) as Cantidad from Equipos group by habilitado";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            // lleno los dos vectores con la agrupación de todos los estados
            // del equipo y cuantos hay de cada uno de ellos
            if (rs.next()) {
                int posicion = 0; // podrá recorrer los vectores definidos antes
                do {
                    // El nombre del estatus
                    vector_estatus_nombre[posicion] = auxiliar[rs.getInt(1)];
                    // la "Cantidad" que hay
                    vector_estatus_cantidad[posicion] = rs.getInt(2);

                    if (vector_estatus_nombre[posicion].equalsIgnoreCase("Activo")) {
                        Activo = vector_estatus_cantidad[posicion];
                    } else if (vector_estatus_nombre[posicion].equalsIgnoreCase("Inactivo")) {
                        Inactivo = vector_estatus_cantidad[posicion];
                    } else if (vector_estatus_nombre[posicion].equalsIgnoreCase("En revisión")) {
                        EnRevision = vector_estatus_cantidad[posicion];
                    } else if (vector_estatus_nombre[posicion].equalsIgnoreCase("Entregado")) {
                        Entregado = vector_estatus_cantidad[posicion];
                    } else if (vector_estatus_nombre[posicion].equalsIgnoreCase("No reparado")) {
                        NoReparado = vector_estatus_cantidad[posicion];
                    } else if (vector_estatus_nombre[posicion].equalsIgnoreCase("Nuevo ingreso")) {
                        NuevoIngreso = vector_estatus_cantidad[posicion];
                    } else if (vector_estatus_nombre[posicion].equalsIgnoreCase("Reparado")) {
                        Reparado = vector_estatus_cantidad[posicion];
                    }

                    posicion++;
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.err.println("Error al contar equipos " + e);
            JOptionPane.showMessageDialog(null, "Error al contar equipos, contacte con el Administrador");
        }
        // el método repaint() permite invocar al método paint() para
        // dibujar sobre el jFrame
        repaint();

    }

    //
    //  Colocamos en icono que aparecerá en la barra de tareas
    //  Que no aparezca al famosa tacita de café
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
        jLabel_footer = new javax.swing.JLabel();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(java.awt.Color.white);
        jLabel1.setText("Gráfica de status");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel_footer.setForeground(new java.awt.Color(255, 255, 255));
        jLabel_footer.setText("Andreu Garcia Coll - UIB 2020");
        getContentPane().add(jLabel_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, -1, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 450));

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
            java.util.logging.Logger.getLogger(GraficarEstatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraficarEstatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraficarEstatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraficarEstatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraficarEstatus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_footer;
    // End of variables declaration//GEN-END:variables

    public int EstatusMasRepetido(int Activo, int Inactivo, int NuevoIngreso, int NoReparado, int EnRevision, int Reparado, int Entregado) {
        if (Activo > Inactivo && Activo > NoReparado && Activo > NuevoIngreso && Activo > EnRevision && Activo > Reparado && Activo > Entregado) {
            return Activo;
        } else if (Inactivo > NoReparado && Inactivo > NuevoIngreso && Inactivo > EnRevision && Inactivo > Reparado && Inactivo > Entregado) {
            return Inactivo;
        } else if (NuevoIngreso > NoReparado && NuevoIngreso > EnRevision && NuevoIngreso > Reparado && NuevoIngreso > Entregado) {
            return NuevoIngreso;
        } else if (NoReparado > EnRevision && NoReparado > Reparado && NoReparado > Entregado) {
            return NoReparado;
        } else if (EnRevision > Reparado & EnRevision > Entregado) {
            return EnRevision;
        } else if (Reparado > Entregado) {
            return Reparado;
        } else {
            return Entregado;
        }
    }

    // Generación de las barras de la gráfica
    // sobreescribiendo el método paint
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // guardará el valor de lo que haya devuelto EstatusMasRepetido()
        int Estatus_Mas_Repetido = EstatusMasRepetido(Activo, Inactivo, NuevoIngreso, NoReparado, EnRevision, Reparado, Entregado);

        // largo_???? es el tamaño de la barra
        // el 400 es la cantidad de píxeles a utilizar en el interfaz
        int largo_Activo = Activo * 400 / Estatus_Mas_Repetido;
        int largo_Inactivo = Inactivo * 400 / Estatus_Mas_Repetido;
        int largo_NuevoIngreso = NuevoIngreso * 400 / Estatus_Mas_Repetido;
        int largo_NoReparado = NoReparado * 400 / Estatus_Mas_Repetido;
        int largo_EnRevision = EnRevision * 400 / Estatus_Mas_Repetido;
        int largo_Reparado = Reparado * 400 / Estatus_Mas_Repetido;
        int largo_Entregado = Entregado * 400 / Estatus_Mas_Repetido;

        // Asigno color, relleno, grueso y texto a cada una de las barras 
        // Activo
        g.setColor(new Color(240, 248, 0)); //amarillo
        g.fillRect(100, 100, largo_Activo, 40);
        g.drawString("Activo", 10, 118);
        g.drawString("Cantidad: " + Activo, 10, 133);
        // Inactivo
        g.setColor(new Color(255, 0, 0)); //rojo
        g.fillRect(100, 150, largo_Inactivo, 40);
        g.drawString("Inactivo", 10, 168);
        g.drawString("Cantidad: " + Inactivo, 10, 183);
        // Nuevo Ingreso
        g.setColor(new Color(0, 0, 0)); //negro
        g.fillRect(100, 200, largo_NuevoIngreso, 40);
        g.drawString("Nuevo Ingreso", 10, 218);
        g.drawString("Cantidad: " + NuevoIngreso, 10, 233);
        // No Reparado
        g.setColor(new Color(255, 255, 255)); //blanco
        g.fillRect(100, 250, largo_NoReparado, 40);
        g.drawString("No Reparado", 10, 268);
        g.drawString("Cantidad: " + NoReparado, 10, 283);
        // En Revisión
        g.setColor(new Color(0, 85, 0)); //verde
        g.fillRect(100, 300, largo_EnRevision, 40);
        g.drawString("En Revisión", 10, 318);
        g.drawString("Cantidad: " + EnRevision, 10, 333);
        // Reparado
        g.setColor(Color.ORANGE); //naranja
        g.fillRect(100, 350, largo_Reparado, 40);
        g.drawString("Reparado", 10, 368);
        g.drawString("Cantidad: " + Reparado, 10, 383);
        // Entregado
        g.setColor(Color.MAGENTA); // magenta
        g.fillRect(100, 400, largo_Entregado, 40);
        g.drawString("Entregado", 10, 418);
        g.drawString("Cantidad: " + Entregado, 10, 433);
    }
}
