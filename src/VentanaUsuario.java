import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VentanaUsuario extends Conexion {
    public JTabbedPane tabbedPane1;
    public JPanel panleusuario;
    public JButton verStockButton;
    public JButton regresarButton2;
    public JLabel verstocks;
    private JButton productosButton;
    private JButton regresarButton;


    // Logica para el stock menor o igual a 20
    public VentanaUsuario() {
        verStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try (Connection conn = conectar()) {
                    String query = "SELECT * FROM Productos WHERE stock <= 20";
                    PreparedStatement statement = conn.prepareStatement(query);
                    ResultSet rs = statement.executeQuery();

                    StringBuilder resultados = new StringBuilder("<html>");
                    while (rs.next()) {
                        resultados.append("ID: ").append(rs.getInt("id_producto"))
                                .append(", Nombre: ").append(rs.getString("nombre"))
                                .append(", Descripción: ").append(rs.getString("descripcion"))
                                .append(", Precio: ").append(rs.getDouble("precio"))
                                .append(", Stock: ").append(rs.getInt("stock"))
                                .append("<br>");
                    }
                    resultados.append("</html>");

                    verstocks.setText(resultados.toString());

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error en la base de datos: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });


        regresarButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame frame = new JFrame("LOGIN");
                frame.setContentPane(new Login().loginsito);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setPreferredSize(new Dimension(300, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

                JFrame loguinFrame = (JFrame) SwingUtilities.getWindowAncestor(panleusuario);
                loguinFrame.dispose();

            }
        });
        productosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = conectar()) {
                    String query = "SELECT * FROM Productos";
                    PreparedStatement statement = conn.prepareStatement(query);
                    ResultSet rs = statement.executeQuery();

                    StringBuilder resultados = new StringBuilder("<html>");
                    while (rs.next()) {
                        resultados.append("ID: ").append(rs.getInt("id_producto"))
                                .append(", Nombre: ").append(rs.getString("nombre"))
                                .append(", Descripción: ").append(rs.getString("descripcion"))
                                .append(", Precio: ").append(rs.getDouble("precio"))
                                .append(", Stock: ").append(rs.getInt("stock"))
                                .append("<br>");
                    }
                    resultados.append("</html>");

                    verstocks.setText(resultados.toString());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error en la base de datos: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("MENU PRINCIPAL");
                frame.setContentPane(new MenuPrincipal().menuPanel);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 300);
                frame.setPreferredSize(new Dimension(300, 300));
                frame.setLocationRelativeTo(null);
                frame.pack();
                frame.setVisible(true);

                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(Jpanel);
                currentFrame.dispose();
            }
        });
    }
}