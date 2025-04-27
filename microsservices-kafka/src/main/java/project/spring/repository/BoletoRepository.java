package project.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.spring.entities.Boleto;



@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long> {

}
