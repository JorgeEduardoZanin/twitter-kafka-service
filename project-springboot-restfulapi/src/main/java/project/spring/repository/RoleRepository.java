package project.spring.repository;
 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.spring.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	 public Role findFirstByName(String name);
}
