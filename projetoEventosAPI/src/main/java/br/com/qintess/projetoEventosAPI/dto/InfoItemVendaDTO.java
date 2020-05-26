package br.com.qintess.projetoEventosAPI.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoItemVendaDTO {
	private String self;
	private String ingresso;
	private String venda;
	private int quantidade;
	private double subTotal;
	private List<InfoIngressoGeradoDTO> ingressosGerados;
}
