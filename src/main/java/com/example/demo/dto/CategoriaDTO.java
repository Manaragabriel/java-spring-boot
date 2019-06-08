package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.domain.Categoria;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
public class CategoriaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Integer id;
	@NotEmpty(message="Nome obrigatorio")
	@Length(min=5,max=80,message="Tamanho errado")
	public String nome;
	public CategoriaDTO() {}
	
	public CategoriaDTO(Categoria obj) {
		id= obj.getId();
		nome= obj.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
