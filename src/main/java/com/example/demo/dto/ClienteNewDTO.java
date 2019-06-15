package com.example.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.example.demo.services.ClienteInsert;
@ClienteInsert
public class ClienteNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message="Tem que ser preenchido")
	@Length(min=3,max=50,message="Digite um tamanho valido")
	private String nome;
	@NotEmpty(message="Tem que ser preenchido")
	@Email
	private String email;
	@NotEmpty(message="Tem que ser preenchido")
	
	private String cpf_cnpj;
	@NotEmpty(message="Tem que ser preenchido")
	private String logradouro;
	@NotEmpty(message="Tem que ser preenchido")
	private String numero;
	@NotEmpty(message="Tem que ser preenchido")
	private String complemento;
	@NotEmpty(message="Tem que ser preenchido")
	private String bairro;
	@NotEmpty(message="Tem que ser preenchido")
	private String cep;
	private Integer cidade;
	private Integer tipo;
	@NotEmpty
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	private String telefone;
	private String telefone2;
	public ClienteNewDTO() {}

	public ClienteNewDTO(Integer id,
			@NotEmpty(message = "Tem que ser preenchido") @Length(min = 3, max = 50, message = "Digite um tamanho valido") String nome,
			@Email String email, String cpf_cnpj, String logradouro, String numero, String complemento, String bairro,
			String cep, Integer cidade,Integer tipo,String password) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf_cnpj = cpf_cnpj;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.tipo= tipo;
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

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getCidade() {
		return cidade;
	}

	public void setCidade(Integer cidade) {
		this.cidade = cidade;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	
	
}
