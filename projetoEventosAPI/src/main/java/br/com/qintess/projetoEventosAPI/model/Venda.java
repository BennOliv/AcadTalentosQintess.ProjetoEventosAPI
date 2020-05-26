package br.com.qintess.projetoEventosAPI.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="venda")
public class Venda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_usuario", nullable = false)
	private Usuario usuario;
	
	
	@Column(name="data_hora_venda", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dataHoraVenda;
	
	@Column(name="valor_total", precision = 10, scale = 2)
	private double valorTotal;
	
	@OneToMany(mappedBy = "venda",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<ItemVenda> itens;

	@Override
	public String toString() {
		return "Venda [id=" + id + ", usuario=" + usuario + ", dataHoraVenda=" + dataHoraVenda + ", valorTotal="
				+ valorTotal + "]";
	}
	
}
