package entidades;

public class ClienteDTO {
	private int idCliente;
    private String nombreCliente;
    private int montoFacturacion;

    public ClienteDTO(int idCliente, String nombreCliente, int montoFacturacion) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.montoFacturacion = montoFacturacion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double getMontoTotalFacturacion() {
        return montoFacturacion;
    }

    public void setMontoTotalFacturacion(int montoFacturacion) {
        this.montoFacturacion = montoFacturacion;
    }

    @Override
    public String toString() {
        return "ClienteDTO{" +
                "idCliente=" + idCliente +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", montoTotalFacturacion=" + montoFacturacion +
                '}';
    }
}
