package br.com.qintess.projetoEventosAPI.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="item_venda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVenda {

	@EmbeddedId
	private ItemVendaId id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("idVenda")
	private Venda venda;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("idIngresso")
	private Ingresso ingresso;
	
	@Column(name="quantidade", nullable = false)
	private int quantidade;
	
	@Column(name="valor", nullable = false)
	private double valorSubTotal;
	
	@OneToMany(mappedBy = "vendaItem",fetch = FetchType.EAGER)
	private List<IngressoGerado> ingressosGerados;

	@Override
	public String toString() {
		return "ItemVenda [id=" + id + ", venda=" + venda + ", ingresso=" + ingresso + ", quantidade=" + quantidade
				+ ", valorSubTotal=" + valorSubTotal + ", ingressosGerados=" + ingressosGerados + "]";
	}
	
	
}
