package project.spring.entities;

import java.io.Serial;
import java.util.List;
import java.util.UUID;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;




@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Serial
    private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "user_id")
	private UUID id;
	
	private String nome;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Boleto> boletos;
	
	@Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
	private boolean assinante = false;

	
	@Column(unique = true)
	private String email;
	private Integer idade;
	private String password;
	
	public Usuario() {
    }
	
	public Usuario(String nome, String email, Integer idade, String password) {
		this.nome = nome;
		this.password = password;
		this.email = email;
		this.idade = idade;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public List<Boleto> getBoletos() {
		return boletos;
	}

	public void setBoletos(List<Boleto> boletos) {
		this.boletos = boletos;
	}

	public boolean isAssinante() {
		return assinante;
	}

	public void setAssinante(boolean assinante) {
		this.assinante = assinante;
	}
	
}
