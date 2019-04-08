package domain;


//@Entity
public class PedidosDomain {

	//@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	private String idPedido;
	private String nombre;
	private int monto;
	private int descuento;
	
	public PedidosDomain() {
		
	}
	
	public PedidosDomain(String idPedido, String nombre, int monto, int descuento) {
		super();
		this.idPedido = idPedido;
		this.nombre = nombre;
		this.monto = monto;
		this.descuento = descuento;
	}


	public String getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

}
