package br.com.qintess.projetoEventosAPI.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
	private String nome;
	private String email;
	private String senha;
	private int cpf;
	private String logradouro;
	private String numero;
	private String cidade;
	private String estado;
	private LocalDate dataNascimento;
	private Long idCargo;
}
