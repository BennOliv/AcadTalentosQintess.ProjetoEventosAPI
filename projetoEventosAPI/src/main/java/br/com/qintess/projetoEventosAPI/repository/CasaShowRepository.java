package br.com.qintess.projetoEventosAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.qintess.projetoEventosAPI.model.CasaShow;

public interface CasaShowRepository extends JpaRepository<CasaShow, Long>{

	@Query(value=" select * from casa_de_show cs where cs.nome like '%:nome%' ", nativeQuery = true)
	List<CasaShow> findByNome(@Param("nome") String nome);
	
	@Query(value=" select * from casa_de_show cs where cs.cidade like '%:cidade%' ", nativeQuery = true)
	List<CasaShow> findByCidade(@Param("cidade") String cidade);
	
	boolean existsByNome(String nome);
	
}
