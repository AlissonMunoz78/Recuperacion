import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ver_Admin extends Conexion {
    public JTabbedPane tabbedPane1;
    public JPanel visualito;
    public JLabel VerDatosProductos;
    public JButton verButton;
    private JButton regresarButton;

    public Ver_Admin() {
        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = conectar()) {
                    String query = "SELECT * FROM Productos"; // Consulta para obtener todos los productos
                    PreparedStatement statement = conn.prepareStatement(query);
                    ResultSet rs = statement.executeQuery();

                    // Construir un String con los resultados
                    StringBuilder resultados = new StringBuilder("<html>");
                    while (rs.next()) {
                        resultados.append("ID: ").append(rs.getInt("id_producto"))
                                .append(",  Nombre: ").append(rs.getString("nombre"))
                                .append(",  Descripción: ").append(rs.getString("descripcion"))
                                .append(",  Precio: ").append(rs.getDouble("precio"))
                                .append(",  Stock: ").append(rs.getInt("stock"))
                                .append("<br>");
                    }
                    resultados.append("</html>");

                    // Mostrar resultados en el JLabel
                    VerDatosProductos.setText(resultados.toString());

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error en la base de datos: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Menu Administrador");
                frame.setContentPane(new menu_admin().menusito);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setPreferredSize(new Dimension(300, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

                JFrame verAdmin = (JFrame) SwingUtilities.getWindowAncestor(visualito);
                verAdmin.dispose();
            }
        });
    }
}
