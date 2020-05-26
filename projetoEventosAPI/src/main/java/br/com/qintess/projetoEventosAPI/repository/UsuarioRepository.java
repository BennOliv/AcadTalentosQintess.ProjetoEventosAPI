package br.com.qintess.projetoEventosAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.qintess.projetoEventosAPI.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query(value=" select * from usuario u where u.nome like '%:nome%' ", nativeQuery = true)
	List<Usuario> findByNome(@Param("nome") String nome);
	
	@Query(value=" select * from usuario u where u.cidade like '%:cidade%' ", nativeQuery = true)
	List<Usuario> findByCidade(@Param("cidade") String cidade);
	
	boolean existsByNome(String nome);
	
}
