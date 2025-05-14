package project.spring.repository;






import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import project.spring.entities.Pagamento;


@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, String> {
	
	public List<Pagamento> findByUsuarioUsuarioId(String usuarioId);
	
	public Optional<Pagamento> findByIdApiPrincipal(Long identificadorApiPrincipal);	
}
