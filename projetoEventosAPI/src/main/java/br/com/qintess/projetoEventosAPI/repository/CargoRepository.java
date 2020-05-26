package br.com.qintess.projetoEventosAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.qintess.projetoEventosAPI.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{

	@Query(value=" select * from cargo c where c.nome like '%:nome%' ", nativeQuery = true)
	Cargo findByNome(@Param("nome") String nome);
}
