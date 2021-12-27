package modelo;

/**
 *
 * @author joisRomero
 */
public class Usuario {
    private int id;
    private String nombreUsuario;
    private String clave;
    private String nombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String rol;
    private String estado;

    public Usuario() {
    }

    public Usuario(int id, String nombreUsuario, String clave, String nombre, String apellidoPaterno, String apellidoMaterno, String rol, String estado) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.nombre = nombre;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.rol = rol;
        this.estado = estado;
    }

    public Usuario(String nombreUsuario, String clave, String nombre, String apellidoMaterno, String apellidoPaterno, String rol, String estado) {
        this.nombreUsuario = nombreUsuario;
        this.clave = clave;
        this.nombre = nombre;
        this.apellidoMaterno = apellidoMaterno;
        this.apellidoPaterno = apellidoPaterno;
        this.rol = rol;
        this.estado = estado;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }    
}
