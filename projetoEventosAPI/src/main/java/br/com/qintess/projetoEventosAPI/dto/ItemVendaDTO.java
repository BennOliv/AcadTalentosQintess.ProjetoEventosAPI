package br.com.qintess.projetoEventosAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemVendaDTO {
	private Long idIngresso;
	private int quantidade;
}
