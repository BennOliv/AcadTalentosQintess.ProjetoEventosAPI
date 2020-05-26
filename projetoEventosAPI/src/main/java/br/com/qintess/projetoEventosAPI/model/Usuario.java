package br.com.qintess.projetoEventosAPI.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", logradouro=" + logradouro + ", numero=" + numero + ", cidade=" + cidade + ", estado=" + estado
				+ ", dataNascimento=" + dataNascimento + ", cargo=" + cargo + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String senha;
	
	@Column(nullable = false)
	private int cpf;
	
	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = false, length = 10)
	private String numero;
	
	@Column(nullable = false, length = 50)
	private String cidade;
	
	@Column(nullable = false, length = 19)
	private String estado;
	
	@Column(name="data_nascimento", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
				fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Cargo cargo;
	
	@OneToMany(mappedBy = "usuario",
				cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Venda> compras;
	
}
