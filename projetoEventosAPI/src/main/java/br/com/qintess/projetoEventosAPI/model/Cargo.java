package br.com.qintess.projetoEventosAPI.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cargo")
public class Cargo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="nome", length = 30, nullable = false)
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cargo",
				cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
				fetch = FetchType.LAZY)
	private List<Usuario> usuarios;

	@Override
	public String toString() {
		return "Cargo [id=" + id + ", nome=" + nome + "]";
	}	
}