package br.com.qintess.projetoEventosAPI.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoVendaDTO {
	private String self;
	private String usuario;
	private LocalDateTime dataHoraVenda;
	private double valorTotal;
	private List<InfoItemVendaDTO> itensVenda;
}
