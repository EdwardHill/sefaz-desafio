package br.com.sefaz.desafio.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.GenericGenerator;


@Entity
public class Telefone implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)    *mudar para  utilizar o banco de dados hsqldb  local*
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;
	
	@NotNull (message = "O campo não deve estar vazio!")
	@Size(min=4 , message="O DDD precisa ter pelo menos 2 Digitos!")    
	private String ddd;       //foi necessário a mudança da tipagem dessa atributo de int para String por causa de melhor manipulação com a mascara do input.
	
	
	
	@NotNull (message = "O campo não deve estar vazio!")
	@Size(min=12, message="O Número precisa ter pelo menos 9 Digitos!")
	private String numero;
	
	@NotNull (message = "O campo não deve estar vazio!")
	private Tipo tipo;
	
	
	@ManyToOne(optional=true, fetch=FetchType.EAGER) //*Fecth type é EAGER para ManyToOne e OneToOne por default, do contrário será LAZY para ManyToMany e OneToMany. 
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	

}