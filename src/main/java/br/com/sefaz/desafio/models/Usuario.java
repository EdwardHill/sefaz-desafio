package br.com.sefaz.desafio.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)  
	private Long id;

	@NotNull (message = "O campo não deve estar vazio!")
	@Size(min=8, max=100 , message="Tem de ter pelo menos 8 letras")
	private String nome;
	
	@NotNull (message = "O campo não deve estar vazio!")
	@Pattern(regexp = "^[\\w\\-]+(\\.[\\w\\-]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$", message="E-mail com formato incorreto.")
	private String email;
	
	@NotNull (message = "O campo não deve estar vazio!")
	@Size(min=8, max=15, message="A senha deve ter pelo menos 8 Digitos!")
	private String senha;
		
	@OneToMany(mappedBy = "usuario", targetEntity = Telefone.class ,fetch = FetchType.LAZY, cascade ={ CascadeType.REMOVE, CascadeType.PERSIST})
	private List<Telefone> telefones;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}


	

}