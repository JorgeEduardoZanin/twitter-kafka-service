package project.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.spring.entities.NotificacaoPagamento;

@Repository
public interface NotificacaoPagamentoRepository extends JpaRepository<NotificacaoPagamento, Long>{

	
}
