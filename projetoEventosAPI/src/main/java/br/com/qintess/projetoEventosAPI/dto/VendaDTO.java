package br.com.qintess.projetoEventosAPI.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO {
	private Long idUsuario;
	private List<ItemVendaDTO> itensVenda;
}