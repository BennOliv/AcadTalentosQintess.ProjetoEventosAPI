package br.com.qintess.projetoEventosAPI.model;

import java.util.List;

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

@Entity
@Table(name="casa_de_show")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CasaShow {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="nome", nullable = false, length = 50)
	private String nome;
	
	@Column(name="logradouro", nullable = false)
	private String logradouro;
	
	@Column(name="numero", nullable = false, length = 10)
	private String numero;
	
	@Column(name="cidade", nullable = false, length = 50)
	private String cidade;
	
	@Column(name="estado", nullable = false, length = 19)
	private String estado;
	
	@Column(name="capacidade_comum", nullable = false)
	private int capacidadeComum;
	
	@Column(name="capacidade_vip", nullable = false)
	private int capacidadeVip;

	@JsonIgnore
	@OneToMany(mappedBy = "local", fetch = FetchType.LAZY)
	private List<Evento> eventos;

	@Override
	public String toString() {
		return "CasaShow [id=" + id + ", nome=" + nome + ", logradouro=" + logradouro + ", numero=" + numero
				+ ", cidade=" + cidade + ", estado=" + estado + ", capacidadeComum=" + capacidadeComum
				+ ", capacidadeVip=" + capacidadeVip + "]";
	}
}
