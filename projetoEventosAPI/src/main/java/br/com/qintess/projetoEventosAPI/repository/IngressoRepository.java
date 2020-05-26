package br.com.qintess.projetoEventosAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.qintess.projetoEventosAPI.model.Ingresso;

public interface IngressoRepository extends JpaRepository<Ingresso, Long>{
	
	@Query(value = "select * from ingresso i where i.id_evento = :evento", nativeQuery = true)
	List<Ingresso> findByEvento(@Param("evento") Long id);
	
	
	
}
