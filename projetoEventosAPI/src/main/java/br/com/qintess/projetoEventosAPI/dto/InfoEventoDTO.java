package br.com.qintess.projetoEventosAPI.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoEventoDTO {
	private Long id;
	private String nome;
	private String descricao;
	private LocalDate data;
	private LocalTime hrAbertura;
	private LocalTime hrEncerramento;
	private Long idCasaShow;
	private String imagem;
	private List<InfoIngressoDTO> ingressos;
}
