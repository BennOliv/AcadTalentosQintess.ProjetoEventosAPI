package br.com.qintess.projetoEventosAPI.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemVendaId implements Serializable{

	private long idVenda;
	
	private long idIngresso;

	@Override
	public int hashCode() {
		return Objects.hash(idVenda, idIngresso);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemVendaId other = (ItemVendaId) obj;
		return Objects.equals(idVenda, other.idVenda) &&
				Objects.equals(idIngresso, other.idIngresso);
	}

	@Override
	public String toString() {
		return "ItemVendaId [vendaId=" + idVenda + ", ingressoId=" + idIngresso + "]";
	}
	
}
