package com.example.demo.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Categoria;
import com.example.demo.domain.Cidade;
import com.example.demo.domain.Cliente;
import com.example.demo.domain.Endereco;
import com.example.demo.domain.Estado;
import com.example.demo.domain.ItemPedido;
import com.example.demo.domain.Pagamento;
import com.example.demo.domain.PagamentoComBoleto;
import com.example.demo.domain.PagamentoComCartao;
import com.example.demo.domain.Pedido;
import com.example.demo.domain.Produto;
import com.example.demo.enums.EstadoPagamento;
import com.example.demo.enums.TipoCliente;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.CidadeRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.EnderecoRepository;
import com.example.demo.repository.EstadoRepository;
import com.example.demo.repository.ItemPedidoRepository;
import com.example.demo.repository.PagamentoRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProdutoRepository;


@Service
public class DBService {

	@Autowired
	private EstadoRepository estado_repo;
	
	@Autowired
	private CidadeRepository cidade_repo;
	@Autowired
	private CategoriaRepository categ_repo;
	
	@Autowired
	private ProdutoRepository prod_repo;
	
	@Autowired
	private ClienteRepository cliente_repo;
	@Autowired
	private EnderecoRepository endereco_repo;
	
	@Autowired
	private PedidoRepository pedido_repo;
	@Autowired
	private PagamentoRepository pagamento_repo;
	@Autowired
	private ItemPedidoRepository item_pedido_repo;
	
	public void instantiateTestDatabase() throws ParseException {
		Categoria cat= new Categoria(null,"informatica");
		Categoria cat2= new Categoria(null,"celulares");
		Produto p1= new Produto(null,"rx 5000",1000.00);
		Produto p2= new Produto(null,"zenphone",600.00);
		Produto p3= new Produto(null,"rtx 2000",1000.00);
		Produto p4= new Produto(null,"phone",600.00);
		Produto p5= new Produto(null,"gtx ",1000.00);
		Produto p6= new Produto(null,"iphone",600.00);
		cat.getProdutos().addAll(Arrays.asList(p1,p3,p5));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		p1.getCategorias().addAll(Arrays.asList(cat));		
		p2.getCategorias().addAll(Arrays.asList(cat2));
		p3.getCategorias().addAll(Arrays.asList(cat));		
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat));		
		p6.getCategorias().addAll(Arrays.asList(cat2));
		Estado e1= new Estado(null,"Sao Paulo");
		Estado e2= new Estado(null,"Rio de Janeiro");
		Cidade c1= new Cidade(null,"Campinas",e1);
		Cidade c2= new Cidade(null,"Rio de Janeiro",e2);
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2));
		estado_repo.saveAll(Arrays.asList(e1,e2));
		cidade_repo.saveAll(Arrays.asList(c1,c2));
		Cliente cliente= new Cliente(null,"Gabriel","gabrielmanara2010@hotmail.com","431.578.448-65",TipoCliente.PESSOAFISICA);
		cliente.getTelefones().addAll(Arrays.asList("3333","3444"));
		Endereco end= new Endereco(null,"rua","200","comṕlemento","bairro","222",cliente,c1);
		Endereco end2= new Endereco(null,"rua2","2002","comṕlementop","bairroo","2222",cliente,c2);
		cliente.getEnderecos().addAll(Arrays.asList(end,end2));
		
		cliente_repo.saveAll(Arrays.asList(cliente));
		endereco_repo.saveAll(Arrays.asList(end,end2));
		categ_repo.saveAll(Arrays.asList(cat,cat2));
		prod_repo.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
		SimpleDateFormat fm= new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped= new Pedido(null,fm.parse("30/05/2019 12:00"),cliente,end);
		Pedido ped2= new Pedido(null,fm.parse("20/05/2019 12:00"),cliente,end2);	
		Pagamento pay= new PagamentoComCartao(null,EstadoPagamento.QUITADO,ped,6);
		Pagamento pay2= new PagamentoComBoleto(null,EstadoPagamento.PENDENTE,ped2,fm.parse("30/05/2019 12:00"),null);
		cliente.getPedidos().addAll(Arrays.asList(ped,ped2));
		ped.setPagamento(pay);
		ped2.setPagamento(pay2);
		
		pedido_repo.saveAll(Arrays.asList(ped,ped2));
		pagamento_repo.saveAll(Arrays.asList(pay,pay2));
		ItemPedido i1= new ItemPedido(ped,p1,0.00,1,p1.getPreco());
		ItemPedido i2= new ItemPedido(ped2,p2,3.00,1,p2.getPreco());
		ped.getItens().addAll(Arrays.asList(i1));
		ped2.getItens().addAll(Arrays.asList(i2));
		p1.getItens().addAll(Arrays.asList(i1));
		p2.getItens().addAll(Arrays.asList(i2));
		item_pedido_repo.saveAll(Arrays.asList(i1,i2));
	}
}
