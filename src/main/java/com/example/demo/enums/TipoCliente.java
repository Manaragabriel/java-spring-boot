package com.example.demo.enums;

public enum TipoCliente {

	PESSOAFISICA(1,"Pessoa fisica"),
	PESSOAJURIDICA(2,"Pessoa juridica");
	
	private int cod;
	private String descricao;
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
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		for(TipoCliente tc: TipoCliente.values()) {
			if(cod.equals(tc.getCod())) {
				return tc;
			}
		}
		throw new IllegalArgumentException("id invalido");
	}
	
}
