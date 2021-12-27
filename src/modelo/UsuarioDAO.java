package modelo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author joisRomero
 */
public class UsuarioDAO {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public Usuario login(String nombreUsuario, String clave) {
        String sql = "Select * from usuario where nombreUsuario = ?  and clave = ?";
        Usuario usuario = new Usuario();
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario.setId(rs.getInt("idUsuario"));
                usuario.setNombreUsuario(rs.getString("nombreusuario"));
                usuario.setClave(rs.getString("clave"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidoMaterno(rs.getString("apellidoMaterno"));
                usuario.setApellidoPaterno(rs.getString("apellidoPaterno"));
                usuario.setRol(rs.getString("rol"));
                usuario.setEstado(rs.getString("estado"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
        return usuario;
    }

    public void registrar(Usuario usuario) {
        String sql = "Insert into usuario (idUsuario, nombreusuario, clave, nombre, "
                + "apellidoMaterno, apellidoPaterno, rol, estado) "
                + "values (null, ?, ?, ?, ?, ?, ?)";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getClave());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellidoMaterno());
            ps.setString(5, usuario.getApellidoPaterno());
            ps.setString(6, usuario.getRol());
            ps.setString(6, usuario.getEstado());
            ps.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public List listaUsuarios() {
        List<Usuario> listaUsuario = new ArrayList<>();
        String sql = "select * from usuario";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getInt("idUsuario"), rs.getString("nombreusuario"),
                        rs.getString("clave"), rs.getString("nombre"),
                        rs.getString("apellidoPaterno"), rs.getString("apellidoMaterno"),
                        rs.getString("rol"), rs.getString("estado"));
                listaUsuario.add(usuario);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }

        return listaUsuario;
    }

    public void editar(Usuario usuario) {
        String sql = "UPDATE usuario SET nombreUsuario = ?, "
                + "clave = ?, nombre = ?, apellidoMaterno = ?, apellidoPaterno = ?, "
                + "rol = ?, estado = ? WHERE idUsuario = ?";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getClave());
            ps.setString(3, usuario.getNombre());
            ps.setString(4, usuario.getApellidoMaterno());
            ps.setString(5, usuario.getApellidoPaterno());
            ps.setString(6, usuario.getRol());
            ps.setString(7, usuario.getEstado());
            ps.setInt(8, usuario.getId());
            ps.execute();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }
}
