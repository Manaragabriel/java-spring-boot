package com.example.demo.enums;

public enum EstadoPagamento {

	QUITADO(1,"Quitado"),
	PENDENTE(2,"Pendente"),
	CANCELADO(3,"Cancelado");
	
	private int cod;
	private String descricao;
	private EstadoPagamento(int cod,String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(EstadoPagamento tc: EstadoPagamento.values()) {
			if(cod.equals(tc.getCod())) {
				return tc;
			}
		}
		throw new IllegalArgumentException("id invalido");
	}
		
	
}
