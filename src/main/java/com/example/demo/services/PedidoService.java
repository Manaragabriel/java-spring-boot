package com.example.demo.services;


import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.ItemPedido;
import com.example.demo.domain.Pagamento;
import com.example.demo.domain.PagamentoComBoleto;
import com.example.demo.domain.Pedido;
import com.example.demo.domain.Produto;
import com.example.demo.enums.EstadoPagamento;
import com.example.demo.exceptions.ObjectNotFoundException;
import com.example.demo.repository.ItemPedidoRepository;
import com.example.demo.repository.PagamentoRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProdutoRepository;
import java.util.Arrays;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedido_repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pag_repo;
	
	@Autowired
	private ProdutoService prod_serv;
	
	@Autowired 
	private ItemPedidoRepository item_repo;
	
	public Pedido find(Integer id) {
		Optional<Pedido> ped= pedido_repo.findById(id);
		return ped.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado"));
	}
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto= (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencher(pagto, obj.getInstante());
		}
		obj= pedido_repo.save(obj);
		pag_repo.save(obj.getPagamento());
		for(ItemPedido pd: obj.getItens()) {
			pd.setDesconto(0.0);
			pd.setPreco(prod_serv.find(pd.getProduto().getId()).getPreco());
			pd.setPedido(obj);
			
		}

		item_repo.saveAll(obj.getItens());
		return obj;
	}
}
