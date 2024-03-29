package com.example.demo.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@JsonFormat(pattern="dd/MM/yyyy HH:mm" )
	@Temporal(TemporalType.TIMESTAMP)
	private Date instante;
	
	@OneToOne(cascade= CascadeType.ALL, mappedBy="pedido")
	private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name="endereco_id")
	private Endereco endereco;
	
	@OneToMany(mappedBy="id.pedido", cascade= CascadeType.ALL)
	private Set<ItemPedido> itens= new HashSet<>();
	
	public Pedido() {}

	public Pedido(Integer id, Date instante, Cliente cliente, Endereco endereco) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.endereco = endereco;
	}

	public Double getTotal() {
		double soma=0.0;
		for(ItemPedido i: itens) {
			soma+= i.getSubTotal();
		}
		return soma;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	

	@Override
	public String toString() {
		SimpleDateFormat smp= new SimpleDateFormat("dd/MM/yyyy hh:mm");
		NumberFormat nb= NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		StringBuilder builder= new StringBuilder();
		builder.append("ID do pedido: ");
		builder.append(getId());
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		builder.append(", Data: ");
		builder.append(smp.format(getInstante()));
		builder.append(", Estado pagamento: ");
		builder.append(getPagamento().getEstado());
		builder.append("\nDetalhes: \n");
		for(ItemPedido pd: getItens()) {
			builder.append(pd.toString());
		}
		builder.append("Valor total:");
		builder.append(nb.format(getTotal()));
		return builder.toString();
		
	}
	
	
}
