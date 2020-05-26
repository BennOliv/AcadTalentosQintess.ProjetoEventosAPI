package br.com.qintess.projetoEventosAPI.util;

import java.util.ArrayList;
import java.util.List;

import br.com.qintess.projetoEventosAPI.dto.InfoIngressoDTO;
import br.com.qintess.projetoEventosAPI.dto.InfoIngressoGeradoDTO;
import br.com.qintess.projetoEventosAPI.dto.InfoItemVendaDTO;
import br.com.qintess.projetoEventosAPI.dto.InfoVendaDTO;
import br.com.qintess.projetoEventosAPI.model.Ingresso;
import br.com.qintess.projetoEventosAPI.model.IngressoGerado;
import br.com.qintess.projetoEventosAPI.model.ItemVenda;
import br.com.qintess.projetoEventosAPI.model.Venda;

public class ConversorDTOs {
	
	public static List<InfoIngressoDTO> ingressosParaIDTO(List<Ingresso> ingressos){
		
		List<InfoIngressoDTO> ret = new ArrayList<InfoIngressoDTO>();
		
		if(!ingressos.isEmpty()) {
			for (Ingresso ingresso : ingressos) {
				ret.add(ingressoParaIDTO(ingresso));
			}
		}
		
		return ret;
	}
	
	public static InfoIngressoDTO ingressoParaIDTO(Ingresso ingresso) {
		
		InfoIngressoDTO ret = new InfoIngressoDTO();
		
		ret.setSelf("/ingresso/" + ingresso.getId());
		ret.setTipo(ingresso.getTipo());
		ret.setQtd(ingresso.getQuantidadeDisponivel());
		ret.setPreco(ingresso.getPreco());
		ret.setEvento("/evento/" + ingresso.getEvento().getId());	
		
		return ret;
	}
	
	
	public static List<InfoVendaDTO> vendasParaIDTO(List<Venda> vendas){
		
		List<InfoVendaDTO> ret = new ArrayList<>();
		
		if(!vendas.isEmpty()) {
			for (Venda venda : vendas) {
				ret.add(vendaParaIDTO(venda));
			}
		}
		
		return ret;
	}
	
	
	public static InfoVendaDTO vendaParaIDTO(Venda venda) {
		
		InfoVendaDTO ret = new InfoVendaDTO();
		double cont;
		
		ret.setSelf("/venda/" + venda.getId());
		ret.setUsuario("/usuario/"+venda.getUsuario().getId());
		ret.setDataHoraVenda(venda.getDataHoraVenda());
		ret.setValorTotal(venda.getValorTotal());
		ret.setItensVenda(itensVendaParaIDTO(venda.getItens()));
		
		return ret;
	}
	
	public static InfoItemVendaDTO itemParaIDTO(ItemVenda item) {
		
		InfoItemVendaDTO ret = new InfoItemVendaDTO();
		
		ret.setSelf("/itemvenda/" + item.getId());
		ret.setIngresso("/ingresso/"+item.getIngresso().getId());
		ret.setQuantidade(item.getQuantidade());
		ret.setSubTotal(item.getValorSubTotal());
		ret.setVenda("/venda/"+ item.getVenda().getId());
		
		ret.setIngressosGerados(ingressosGeradosParaIDTO(item.getIngressosGerados()));
		
		return ret;
	}
	
	public static List<InfoItemVendaDTO> itensVendaParaIDTO(List<ItemVenda> itensVenda){
		List<InfoItemVendaDTO> ret = new ArrayList<>();
		if(!itensVenda.isEmpty()) {
			for (ItemVenda itemVenda : itensVenda) {
				ret.add(itemParaIDTO(itemVenda));
			}
		}
		
		return ret;
	}
	
	public static InfoIngressoGeradoDTO ingressoGeradoParaIDTO(IngressoGerado ingressoGerado) {
		
		InfoIngressoGeradoDTO ret = new InfoIngressoGeradoDTO();
		
		ret.setSelf("/ingressogerado/"+ingressoGerado.getId());
		ret.setVenda("/venda/"+ingressoGerado.getVendaItem().getVenda().getId());
		ret.setIngresso("/ingresso/"+ingressoGerado.getVendaItem().getIngresso().getId());
		
		return ret;
	}
	
	public static List<InfoIngressoGeradoDTO> ingressosGeradosParaIDTO(List<IngressoGerado> ingressosGerados){
		List<InfoIngressoGeradoDTO> ret = new ArrayList<>();
		
		if(!ingressosGerados.isEmpty()) {
			for (IngressoGerado ingressoGerado : ingressosGerados) {
				ret.add(ingressoGeradoParaIDTO(ingressoGerado));
			}
		}
		
		return ret;
	}
	
}
