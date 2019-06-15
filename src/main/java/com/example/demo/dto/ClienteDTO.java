package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.example.demo.services.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO implements Serializable{


	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message="Tem que ser preenchido")
	@Length(min=3,max=50,message="Digite um tamanho valido")
	private String nome;
	@Email
	private String email;
	private String password;
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ClienteDTO() {}

	public ClienteDTO(Integer id, String nome, String email,String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.password= password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
