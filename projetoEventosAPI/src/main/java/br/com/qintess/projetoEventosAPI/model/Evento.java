package br.com.qintess.projetoEventosAPI.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="evento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="nome", length = 20, nullable = false)
	private String nome;
	
	@Column(name="descricao", nullable = false)
	private String descricao;
	
	@Column(name="data",nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	
	@Column(name="hr_abertura", nullable = true)
	private LocalTime hrAbertura;
	
	@Column(name="hr_encerramento", nullable = true)
	private LocalTime hrEncerramento;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "id_casa_show")
	private CasaShow local;
	
	@Lob
	@Column(columnDefinition = "mediumblob")
	private byte[] imagemEvento;
		
	@OneToMany(mappedBy = "evento",orphanRemoval = true, fetch = FetchType.EAGER)
	private List<Ingresso> ingressos;

	@Override
	public String toString() {
		return "Evento [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", data=" + data + ", hrAbertura="
				+ hrAbertura + ", hrEncerramento=" + hrEncerramento + ", local=" + local + "]";
	}
	
	
}
