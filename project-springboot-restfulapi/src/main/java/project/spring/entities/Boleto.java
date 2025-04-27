package project.spring.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import project.spring.enums.StatusBoleto;

@Entity
@Table(name = "tb_boleto")
public class Boleto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	
	@NotNull(message = "O código de barras não pode ser nulo")
	@NotEmpty(message = "Não pode ser vazio")
	private String codigoBarras;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
	private StatusBoleto statusBoleto;
	
	@ManyToOne
	@JoinColumn(name="usuario_id")
	@JsonBackReference
	private Usuario usuario;
	
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;
	
	public Boleto() {}

	public Boleto(String codigoBarras, StatusBoleto statusBoleto, LocalDateTime dataCriacao, LocalDateTime dataAtualizacao, Usuario usuario) {
		this.codigoBarras = codigoBarras;
		this.statusBoleto = statusBoleto;
		this.dataCriacao = dataCriacao;
		this.dataAtualizacao = dataAtualizacao;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public StatusBoleto getStatusBoleto() {
		return statusBoleto;
	}

	public void setStatusBoleto(StatusBoleto statusBoleto) {
		this.statusBoleto = statusBoleto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public LocalDateTime getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoBarras, dataAtualizacao, dataCriacao, id, statusBoleto, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Boleto other = (Boleto) obj;
		return Objects.equals(codigoBarras, other.codigoBarras)
				&& Objects.equals(dataAtualizacao, other.dataAtualizacao)
				&& Objects.equals(dataCriacao, other.dataCriacao) && Objects.equals(id, other.id)
				&& statusBoleto == other.statusBoleto && Objects.equals(usuario, other.usuario);
	}
	
	
	
	
	
	
	
	
}
