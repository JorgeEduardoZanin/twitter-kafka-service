package project.spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.spring.entities.Pagamento;


@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, String> {
	
}
