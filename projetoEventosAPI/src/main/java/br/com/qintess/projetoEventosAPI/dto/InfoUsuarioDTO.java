package br.com.qintess.projetoEventosAPI.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfoUsuarioDTO {
	private String self;
	private String nome;
	private String email;
	private String logradouro;
	private String numero;
	private String cidade;
	private String estado;
	private LocalDate dataNascimento;
	private String cargo;
	private List<InfoVendaDTO> compras;
}
