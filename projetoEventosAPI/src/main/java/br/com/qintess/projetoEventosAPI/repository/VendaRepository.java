package br.com.qintess.projetoEventosAPI.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.qintess.projetoEventosAPI.model.Evento;
import br.com.qintess.projetoEventosAPI.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{
	/**
	 * Busca Eventos no banco de dados em uma casa de show específica.
	 * @param id Id de uma casa de chow específica já existente no banco de dados
	 * @return
	 */
	@Query(value=" select * from venda v where v.id_usuario = :usuario ", nativeQuery = true)
	List<Venda> findByUsuario(@Param("usuario") Long id);
	
	//Talveeeeeez dê pau xDD
	//qlqr coisa só deleta :3 (não necessário)
	//(anotado antes de testar, me deseje sorte :3)
	/**
	 * Encontra vendas por data hora.
	 * @param data use o pattern="yyyy/MM/dd HH:mm:ss" para evitar bugs
	 * @return Lista de vendas na data/hora solicitada.
	 */
	@Query(value=" select * from venda v where v.data_hora_venda = :data ", nativeQuery = true)
	List<Evento> findByData(@Param("data") LocalDateTime data);
}
