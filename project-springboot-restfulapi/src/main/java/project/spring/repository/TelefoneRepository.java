package project.spring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.spring.entities.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long>{
	
	public List<Telefone> findByUsuarioIdOrderByNumeroAsc(UUID usuarioId);
	
	public Telefone findByIdAndUsuarioId(Long id, UUID usuarioId);
	
	@Query(value = "SELECT t FROM Telefone t WHERE t.numero LIKE %?1% AND t.usuario.id = ?2 ORDER BY t.numero")
	public List<Telefone> findAllByNumero(String numero, UUID usuarioId);

}
