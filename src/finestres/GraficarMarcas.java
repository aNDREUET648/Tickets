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
public class GraficarMarcas extends javax.swing.JFrame {

    String user;
    int IDuser;
    // lo del vector de 11 es por las 11 marcas que tenemos
    int[] vector_marcas_cantidad = new int[11];
    String[] vector_marcas_nombre = new String[11];
    int acer, msi, apple, asus, brother, dell, hp, lenovo, samsung, toshiba, xerox;

    /*
     * Constructor del form GraficarMarcas
     */
    public GraficarMarcas() {
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

        try {
            Connection con = Conexion.conector();
            // busca la cantidad de marcas de cada equipo y calcula cuantos  
            // equipos hay de cada una de ellas agrupándolos por marca
            String sql = "select marca, count(marca) as Marcas from Equipos group by marca";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            // lleno los dos vectores con las marcas que hay actualmente
            // y cuantos equipos hay de cada una de ellas
            if (rs.next()) {
                int posicion = 0; // podrá recorrer los vectores definidos antes
                do {
                    // El nombre de la marca
                    vector_marcas_nombre[posicion] = rs.getString(1);
                    // la "Cantidad" que hay
                    vector_marcas_cantidad[posicion] = rs.getInt(2);

                    if (vector_marcas_nombre[posicion].equalsIgnoreCase("Acer")) {
                        acer = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("MSI")) {
                        msi = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Apple")) {
                        apple = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Asus")) {
                        asus = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Brother")) {
                        brother = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Dell")) {
                        dell = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("HP")) {
                        hp = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Lenovo")) {
                        lenovo = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Samsung")) {
                        samsung = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Toshiba")) {
                        toshiba = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Xerox")) {
                        xerox = vector_marcas_cantidad[posicion];
                    }
                    posicion++;
                } while (rs.next());
            }
        } catch (SQLException e) {
            System.err.println("Error al contar marcas " + e);
            JOptionPane.showMessageDialog(null, "Error al contar marcas, contacte con el Administrador");
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
        jLabel1.setText("Gráfica de Marcas");
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
            java.util.logging.Logger.getLogger(GraficarMarcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GraficarMarcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GraficarMarcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GraficarMarcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraficarMarcas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JLabel jLabel_footer;
    // End of variables declaration//GEN-END:variables

    // Generación de la gráfica de pastel
    // sobreescribiendo el método paint
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //sumamos el total de las marcas, obtendremos el número total de equipos que hay
        int total_marcas = acer + msi + apple + asus + brother + dell + hp + lenovo + samsung + toshiba + xerox;
        //determinar cuandos grados de la gráfica abarcará cada marca
        int grados_acer = acer * 360 / total_marcas;
        int grados_msi = msi * 360 / total_marcas;
        int grados_apple = apple * 360 / total_marcas;
        int grados_asus = asus * 360 / total_marcas;
        int grados_brother = brother * 360 / total_marcas;
        int grados_dell = dell * 360 / total_marcas;
        int grados_hp = hp * 360 / total_marcas;
        int grados_lenovo = lenovo * 360 / total_marcas;
        int grados_samsung = samsung * 360 / total_marcas;
        int grados_toshiba = toshiba * 360 / total_marcas;
        int grados_xerox = xerox * 360 / total_marcas;

        // grafico un arco elíptico para cada marca
        // acer
        g.setColor(new Color(175, 122, 197)); // morado
        g.fillArc(25, 100, 270, 270, 0, grados_acer);
        g.fillRect(310, 120, 20, 20);
        g.drawString(acer + " de Acer", 340, 135);

        //Marca alienware
        g.setColor(new Color(0, 255, 0));// verde
        g.fillArc(25, 100, 270, 270, grados_acer, grados_msi);
        g.fillRect(310, 150, 20, 20);
        g.drawString(msi + " de MSI", 340, 165);

        //Marca apple
        g.setColor(new Color(0, 255, 255));// verde agua
        g.fillArc(25, 100, 270, 270, grados_acer + grados_msi, grados_apple);
        g.fillRect(310, 180, 20, 20);
        g.drawString(apple + " de Apple", 340, 195);

        //Marca asus
        g.setColor(new Color(55, 0, 255));// azul
        g.fillArc(25, 100, 270, 270, grados_acer + grados_msi + grados_apple, grados_asus);
        g.fillRect(310, 210, 20, 20);
        g.drawString(asus + " de Asus", 340, 225);

        //Marca brother
        g.setColor(new Color(255, 255, 255));// blanco
        g.fillArc(25, 100, 270, 270, grados_acer + grados_msi + grados_apple + grados_asus, grados_brother);
        g.fillRect(310, 240, 20, 20);
        g.drawString(brother + " de Brother", 340, 255);

        //Marca dell
        g.setColor(new Color(247, 220, 111));// amarillo
        g.fillArc(25, 100, 270, 270, grados_acer + grados_msi + grados_apple + grados_asus + grados_brother, grados_dell);
        g.fillRect(310, 270, 20, 20);
        g.drawString(dell + " de Dell", 340, 285);

        //Marca HP
        g.setColor(new Color(21, 42, 160));// azul marino
        g.fillArc(25, 100, 270, 270, grados_acer + grados_msi + grados_apple + grados_asus + grados_brother + grados_dell, grados_hp);
        g.fillRect(310, 300, 20, 20);
        g.drawString(hp + " de HP", 340, 315);

        //Marca Lenovo
        g.setColor(new Color(215, 96, 0));// naranja
        g.fillArc(25, 100, 270, 270, grados_acer + grados_msi + grados_apple + grados_asus + grados_brother + grados_dell + grados_hp, grados_lenovo);
        g.fillRect(310, 330, 20, 20);
        g.drawString(lenovo + " de Lenovo", 340, 345);

        //Marca Samsung
        g.setColor(new Color(215, 96, 140));// rosa
        g.fillArc(25, 100, 270, 270, grados_acer + grados_msi + grados_apple + grados_asus + grados_brother + grados_dell + grados_hp + grados_lenovo, grados_samsung);
        g.fillRect(430, 120, 20, 20);
        g.drawString(samsung + " de Samsung", 460, 135);

        //Marca Toshiba
        g.setColor(new Color(215, 196, 25));// amarillo canario
        g.fillArc(25, 100, 270, 270, grados_acer + grados_msi + grados_apple + grados_asus + grados_brother + grados_dell + grados_hp + grados_lenovo + grados_samsung, grados_toshiba);
        g.fillRect(430, 150, 20, 20);
        g.drawString(toshiba + " de Toshiba", 460, 165);

        //Marca Xerox
        g.setColor(new Color(93, 173, 226));// azul celeste
        g.fillArc(25, 100, 270, 270, grados_acer + grados_msi + grados_apple + grados_asus + grados_brother + grados_dell + grados_hp + grados_lenovo + grados_samsung + grados_toshiba, grados_xerox);
        g.fillRect(430, 180, 20, 20);
        g.drawString(xerox + " de Xerox", 460, 195);
    }

}
