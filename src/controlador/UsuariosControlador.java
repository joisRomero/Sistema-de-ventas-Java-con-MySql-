package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.dlgUsuarios;

/**
 *
 * @author joisRomero
 */
public class UsuariosControlador implements ActionListener, MouseListener {

    private Usuario usuario;
    private UsuarioDAO usuarioDAO;
    private dlgUsuarios vista;
    private DefaultTableModel tablaModelo = new DefaultTableModel();

    public UsuariosControlador(Usuario usuario, UsuarioDAO usuarioDAO, dlgUsuarios vista) {
        this.usuario = usuario;
        this.usuarioDAO = usuarioDAO;
        this.vista = vista;
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnNuevo.addActionListener(this);
        this.vista.tablaUsuarios.addMouseListener(this);
        this.vista.rbActivos.addActionListener(this);
        this.vista.rbInactivos.addActionListener(this);
        this.vista.rbTodos.addActionListener(this);
        listarUsuarios();
        vista.rbTodos.setSelected(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnAgregar) {
            agregar();
        }
        if (e.getSource() == vista.btnEditar) {
            editar();
        }
        if (e.getSource() == vista.btnNuevo) {
            limpiarCampos();
            vista.btnAgregar.setEnabled(true);
        }
        if (e.getSource() == vista.rbActivos) {
            listarUsuariosActivos();
        }
        if (e.getSource() == vista.rbInactivos) {
            listarUsuariosInactivos();
        }
        if (e.getSource() == vista.rbTodos) {
            listarUsuarios();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.tablaUsuarios) {
            rellenarCamposSeleccion();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private boolean estanVaciosLosCampos() {
        return (vista.txtNombreUsuario.getText().isEmpty() || vista.txtNombre.getText().isEmpty()
                || vista.txtApellidoPaterno.getText().isEmpty() || vista.txtApellidoMaterno.getText().isEmpty()
                || vista.txtClave.getText().isEmpty() || vista.cmbRol.getSelectedIndex() == 0
                || vista.cmbEstado.getSelectedIndex() == 0);
    }

    private void agregar() {
        if (estanVaciosLosCampos()) {
            JOptionPane.showMessageDialog(vista, "Todos los campos tienen que estar "
                    + "llenos", "", JOptionPane.ERROR_MESSAGE);
        } else {
            String nombreUsuario = vista.txtNombreUsuario.getText();
            String nombre = vista.txtNombre.getText();
            String apellidoPaterno = vista.txtApellidoPaterno.getText();
            String apellidoMaterno = vista.txtApellidoMaterno.getText();
            String clave = vista.txtClave.getText();
            String rol = vista.cmbRol.getSelectedItem().toString();
            String estado = vista.cmbEstado.getSelectedItem().toString();

            usuario = new Usuario(nombreUsuario, clave, nombre, apellidoPaterno, apellidoMaterno, rol, estado);
            usuarioDAO.registrar(usuario);
            JOptionPane.showMessageDialog(vista, "Usuario registrado con éxito",
                    "", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            listarUsuarios();
        }
    }

    private void editar() {
        int fila = vista.tablaUsuarios.getSelectedRow();
        if (estanVaciosLosCampos()) {
            JOptionPane.showMessageDialog(vista, "Todos los campos tienen que estar "
                    + "llenos", "", JOptionPane.ERROR_MESSAGE);
        } else {
            String nombreUsuario = vista.txtNombreUsuario.getText();
            String nombre = vista.txtNombre.getText();
            String apellidoPaterno = vista.txtApellidoPaterno.getText();
            String apellidoMaterno = vista.txtApellidoMaterno.getText();
            String clave = vista.txtClave.getText();
            String rol = vista.cmbRol.getSelectedItem().toString();
            String estado = vista.cmbEstado.getSelectedItem().toString();
            int id = Integer.parseInt(vista.tablaUsuarios.getValueAt(fila, 0).toString());

            usuario = new Usuario(id, nombreUsuario, clave, nombre, apellidoPaterno, apellidoMaterno, rol, estado);
            usuarioDAO.editar(usuario);
            JOptionPane.showMessageDialog(vista, "Usuario editado con éxito",
                    "", JOptionPane.INFORMATION_MESSAGE);
            limpiarCampos();
            listarUsuarios();
            vista.btnAgregar.setEnabled(true);
        }
    }

    private void rellenarCamposSeleccion() {
        int fila = vista.tablaUsuarios.getSelectedRow();
        vista.txtNombre.setText(vista.tablaUsuarios.getValueAt(fila, 1).toString());
        vista.txtApellidoPaterno.setText(vista.tablaUsuarios.getValueAt(fila, 2).toString());
        vista.txtApellidoMaterno.setText(vista.tablaUsuarios.getValueAt(fila, 3).toString());
        vista.txtNombreUsuario.setText(vista.tablaUsuarios.getValueAt(fila, 4).toString());
        vista.txtClave.setText(vista.tablaUsuarios.getValueAt(fila, 5).toString());
        vista.cmbRol.setSelectedItem(vista.tablaUsuarios.getValueAt(fila, 6).toString());
        vista.cmbEstado.setSelectedItem(vista.tablaUsuarios.getValueAt(fila, 7).toString());
        vista.btnAgregar.setEnabled(false);

    }

    private void listarUsuarios() {
        List<Usuario> listaUsuarios = usuarioDAO.listaUsuarios();
        tablaModelo = (DefaultTableModel) vista.tablaUsuarios.getModel();
        tablaModelo.setNumRows(0);
        Object[] obj = new Object[8];
        for (Usuario user : listaUsuarios) {
            obj[0] = user.getId();
            obj[1] = user.getNombre();
            obj[2] = user.getApellidoPaterno();
            obj[3] = user.getApellidoMaterno();
            obj[4] = user.getNombreUsuario();
            obj[5] = user.getClave();
            obj[6] = user.getRol();
            obj[7] = user.getEstado();
            tablaModelo.addRow(obj);
        }
        vista.tablaUsuarios.setModel(tablaModelo);
    }

    private void listarUsuariosActivos() {
        List<Usuario> listaUsuarios = usuarioDAO.listaUsuarios();
        tablaModelo = (DefaultTableModel) vista.tablaUsuarios.getModel();
        tablaModelo.setNumRows(0);
        Object[] obj = new Object[8];
        for (Usuario user : listaUsuarios) {
            if (user.getEstado().equals("Activo")) {
                obj[0] = user.getId();
                obj[1] = user.getNombre();
                obj[2] = user.getApellidoPaterno();
                obj[3] = user.getApellidoMaterno();
                obj[4] = user.getNombreUsuario();
                obj[5] = user.getClave();
                obj[6] = user.getRol();
                obj[7] = user.getEstado();
                tablaModelo.addRow(obj);
            }
        }
        vista.tablaUsuarios.setModel(tablaModelo);
    }

    private void listarUsuariosInactivos() {
        List<Usuario> listaUsuarios = usuarioDAO.listaUsuarios();
        tablaModelo = (DefaultTableModel) vista.tablaUsuarios.getModel();
        tablaModelo.setNumRows(0);
        Object[] obj = new Object[8];
        for (Usuario user : listaUsuarios) {
            if (user.getEstado().equals("Inactivo")) {
                obj[0] = user.getId();
                obj[1] = user.getNombre();
                obj[2] = user.getApellidoPaterno();
                obj[3] = user.getApellidoMaterno();
                obj[4] = user.getNombreUsuario();
                obj[5] = user.getClave();
                obj[6] = user.getRol();
                obj[7] = user.getEstado();
                tablaModelo.addRow(obj);
            }
        }
        vista.tablaUsuarios.setModel(tablaModelo);
    }

    private void limpiarCampos() {
        vista.txtNombreUsuario.setText("");
        vista.txtNombre.setText("");
        vista.txtApellidoPaterno.setText("");
        vista.txtApellidoMaterno.setText("");
        vista.txtClave.setText("");
        vista.cmbRol.setSelectedIndex(0);
        vista.cmbEstado.setSelectedIndex(0);
    }

}
