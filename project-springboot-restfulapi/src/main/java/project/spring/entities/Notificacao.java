package project.spring.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_notificacao")
public class Notificacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	public String status;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	@JsonBackReference
	private Usuario usuario;
	
	@OneToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(  
        unique = true,               
        nullable = false
    )
	private Boleto boleto;

	public Notificacao() {
	}

	
	public Notificacao(String status, Usuario usuario, Boleto boleto) {
		this.status = status;
		this.usuario = usuario;
		this.boleto = boleto;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Boleto getBoleto() {
		return boleto;
	}


	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notificacao other = (Notificacao) obj;
		return Objects.equals(id, other.id) && Objects.equals(status, other.status);
	}
	
	
}
