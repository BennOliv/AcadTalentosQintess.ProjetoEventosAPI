package br.com.qintess.projetoEventosAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.qintess.projetoEventosAPI.model.ItemVenda;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, Long>{
	
	@Query(value="select * from ingresso_gerado ig where ig.venda_item_venda_id = :venda", nativeQuery = true)
	List<ItemVenda> findByVenda(@Param("venda") long id);
	
	@Query(value="select * from ingresso_gerado ig where ig.venda_item_ingresso_id = :ingresso", nativeQuery = true)
	List<ItemVenda> findByIngresso(@Param("ingresso") long id);
}