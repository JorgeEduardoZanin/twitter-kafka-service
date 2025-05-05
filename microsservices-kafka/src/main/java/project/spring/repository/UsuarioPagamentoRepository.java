package project.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.spring.entities.UsuarioPagamento;

@Repository
public interface UsuarioPagamentoRepository extends JpaRepository<UsuarioPagamento, String>{

	
	public Optional<UsuarioPagamento> findByCustomer(String customerId);
	
}
