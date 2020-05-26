package br.com.qintess.projetoEventosAPI.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDTO {
	private String nome;
	private String descricao;
	private LocalDate data;
	private LocalTime hrAbertura;
	private LocalTime hrEncerramento;
	private Long idCasaShow;
	private MultipartFile imagem;
	private List<IngressoDTO> ingressos;
}