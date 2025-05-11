package project.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import project.spring.entities.UsuarioPagamento;

@Repository
public interface UsuarioPagamentoRepository extends JpaRepository<UsuarioPagamento, String>{

	
	public Optional<UsuarioPagamento> findByUsuarioId(String u);
	
	public Optional<UsuarioPagamento> findByCustomer(String customerId);
	
	@Query("SELECT p FROM UsuarioPagamento p WHERE p.expiracaoAssinatura > CURRENT_TIMESTAMP")
	public Optional<UsuarioPagamento> findPagamentoNaoExpirado(String usuarioId);
	
}
