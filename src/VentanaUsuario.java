import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Mateo Cardenas
public class VentanaUsuario extends JFrame{

    private JTabbedPane tabbedPane1;
    private JPanel panelusuario;
    private JTextArea textver1;
    private JButton VERPRODUCTOSButton;
    private JTextArea textmostrar2;
    private JButton VERSTOCKButton;

    public VentanaUsuario() {
        this.setTitle("USUARIO");
        this.setSize(600, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        tabbedPane1 = new JTabbedPane();

        JPanel panelVerProductos = new JPanel();
        textver1 = new JTextArea(10, 40);
        VERPRODUCTOSButton = new JButton("Ver Productos");

        panelVerProductos.add(new JScrollPane(textver1));
        panelVerProductos.add(VERPRODUCTOSButton);

        JPanel panelVerStock = new JPanel();
        textmostrar2 = new JTextArea(10, 40);
        VERSTOCKButton = new JButton("Ver Productos ");

        panelVerStock.add(new JScrollPane(textmostrar2)); // Agregar el área de texto con scroll
        panelVerStock.add(VERSTOCKButton);

        tabbedPane1.addTab("Ver Productos", panelVerProductos);
        tabbedPane1.addTab("Productos", panelVerStock);

        panelusuario = new JPanel();
        panelusuario.add(tabbedPane1);

        this.add(panelusuario);

        this.setVisible(true);

        VERPRODUCTOSButton.addActionListener(e -> verProductos());
        VERSTOCKButton.addActionListener(e -> verStock());
    }

    private void verProductos() {
        try (Connection conn = Conexion.getConexion()) {
            if (conn != null) {
                String sql = "SELECT nombre, descripcion, precio FROM productos";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                textver1.setText("");
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    double precio = rs.getDouble("precio");

                    textver1.append("Producto: " + nombre + "\n");
                    textver1.append("Descripción: " + descripcion + "\n");
                    textver1.append("Precio: " + precio + "\n");
                    textver1.append("-------------------------\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void verStock() {
        try (Connection conn = Conexion.getConexion()) {
            if (conn != null) {
                String sql = "SELECT nombre, descripcion, precio, stock FROM productos WHERE stock <= 20";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                textmostrar2.setText("");

                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String descripcion = rs.getString("descripcion");
                    double precio = rs.getDouble("precio");
                    int stock = rs.getInt("stock");


                    textmostrar2.append("Producto: " + nombre + "\n");
                    textmostrar2.append("Descripción: " + descripcion + "\n");
                    textmostrar2.append("Precio: " + precio + "\n");
                    textmostrar2.append("Stock: " + stock + "\n");
                    textmostrar2.append("-------------------------\n");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}





