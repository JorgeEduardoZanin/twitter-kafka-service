package project.spring.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.spring.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
	
	List<Usuario> findAllByOrderByNomeAsc();
	
	@Query(value = "SELECT u FROM Usuario u WHERE u.nome LIKE %?1% ORDER BY u.nome")
	public List<Usuario> findAllWhereNomeOrderByNome(String nome);
	
	@Query(value = "SELECT u FROM Usuario u WHERE u.email = ?1")
	public Optional<Usuario> findByEmail(String email);
	
}
