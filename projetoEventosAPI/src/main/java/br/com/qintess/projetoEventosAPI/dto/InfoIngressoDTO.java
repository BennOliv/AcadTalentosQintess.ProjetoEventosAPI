package br.com.qintess.projetoEventosAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoIngressoDTO {
	private String self;
	private String evento;
	private String tipo;
	private Double preco;
	private Integer qtd;
}
