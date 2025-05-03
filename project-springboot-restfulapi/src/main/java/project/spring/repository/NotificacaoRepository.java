package project.spring.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.spring.entities.Notificacao;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

	List<Notificacao> findAllByUsuarioId(UUID usuarioId);

	
	Optional<Notificacao> findByBoletoId(Long boletoId);

	
}
