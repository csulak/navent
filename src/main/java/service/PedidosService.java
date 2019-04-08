package service;

import cache.BumexMemcached;
import dao.PedidoDAO;
import domain.PedidosDomain;

//@Service
public class PedidosService {

	//@Autowired
	private PedidoDAO pedidosDAO;

	//@Autowired
	BumexMemcached bumexMemcached;
	

	
	public void insertOrUpdate(PedidosDomain pedidosDomain) {
		
		PedidosDomain pedidoEncontrado = this.select(pedidosDomain.getIdPedido());
		
		if(pedidoEncontrado == null) {
			// no existe y se debe hacer un insert(nuevo pedido)
			pedidosDAO.insertOrUpdate(pedidosDomain);
			
			//actualizo la cache
			bumexMemcached.set(pedidosDomain.getIdPedido(), pedidosDomain);
			
		}else {
			//se debe hacer un update
			pedidoEncontrado.setDescuento(pedidosDomain.getDescuento());
			pedidoEncontrado.setMonto(pedidosDomain.getMonto());
			pedidoEncontrado.setNombre(pedidosDomain.getNombre());
			
			// se actualiza el existente
			pedidosDAO.insertOrUpdate(pedidosDomain);
			
			//actualizo la cache
			bumexMemcached.set(pedidosDomain.getIdPedido(), pedidosDomain);

		}	
	}
	
	public PedidosDomain select(String id) {
		
		// valido si esta en cache
		PedidosDomain pedidosDomain = (PedidosDomain) bumexMemcached.get(id);
		
		if(pedidosDomain != null) {
			// esta en cache
			return pedidosDomain;
		}
		
		// valido si esta en la BD
		pedidosDomain =  pedidosDAO.select(Integer.parseInt(id));
		if(pedidosDomain == null) {
			// no esta en la BD
			return null;
		}
		
		// esta en la BD
		return pedidosDomain;
	}

	public void deletePedido(String id) {
		PedidosDomain pedidosDomain = this.select(id);
		pedidosDAO.delete(pedidosDomain);
		bumexMemcached.delete(id);
		
	}
	
	public PedidoDAO getPedidosDAO() {
		return pedidosDAO;
	}

	public void setPedidosDAO(PedidoDAO pedidosDAO) {
		this.pedidosDAO = pedidosDAO;
	}

	public BumexMemcached getBumexMemcached() {
		return bumexMemcached;
	}

	public void setBumexMemcached(BumexMemcached bumexMemcached) {
		this.bumexMemcached = bumexMemcached;
	}
	

}