package br.com.qintess.projetoEventosAPI.model;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ingresso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingresso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_evento",nullable = false)
	private Evento evento;
	
	@Column(length = 20, nullable = false)
	private String tipo;
	
	@Column(precision = 10, scale = 2, nullable = false)
	private double preco;
	
	@Column(nullable = false)
	private int quantidadeDisponivel;
	
	@OneToMany(mappedBy = "ingresso",fetch = FetchType.LAZY)
	private List<ItemVenda> vendas;
	
	@Override
	public String toString() {
		return "Ingresso [id=" + id + ", evento=" + evento + ", tipo=" + tipo + ", preco=" + preco
				+ ", quantidadeDisponivel=" + quantidadeDisponivel + "]";
	}
}
