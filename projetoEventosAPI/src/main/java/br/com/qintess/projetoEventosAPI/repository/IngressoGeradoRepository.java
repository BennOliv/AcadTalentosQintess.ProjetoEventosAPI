package br.com.qintess.projetoEventosAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.qintess.projetoEventosAPI.model.IngressoGerado;

public interface IngressoGeradoRepository extends JpaRepository<IngressoGerado, Long>{
	
	@Query(value="select * from ingresso_gerado ig where ig.venda_id = :venda",nativeQuery = true)
	List<IngressoGerado> findByVenda(@Param("venda") Long id);
	
	@Query(value="select * from ingresso_gerado ig where ig.ingresso_id = :ingresso", nativeQuery = true)
	List<IngressoGerado> findByIngresso(@Param("ingresso") Long id);
}
