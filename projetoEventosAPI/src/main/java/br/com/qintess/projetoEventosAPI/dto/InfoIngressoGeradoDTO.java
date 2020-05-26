package br.com.qintess.projetoEventosAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoIngressoGeradoDTO {
	private String self;
	private String Venda;
	private String Ingresso;
}
