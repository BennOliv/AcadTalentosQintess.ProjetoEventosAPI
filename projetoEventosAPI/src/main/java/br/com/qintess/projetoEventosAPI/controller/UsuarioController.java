package br.com.qintess.projetoEventosAPI.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.qintess.projetoEventosAPI.dto.InfoUsuarioDTO;
import br.com.qintess.projetoEventosAPI.dto.UsuarioDTO;
import br.com.qintess.projetoEventosAPI.model.Usuario;
import br.com.qintess.projetoEventosAPI.repository.CargoRepository;
import br.com.qintess.projetoEventosAPI.repository.UsuarioRepository;
import br.com.qintess.projetoEventosAPI.util.ConversorDTOs;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private CargoRepository cargoRepo;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InfoUsuarioDTO> buscaUsuarios(){
		
		List<Usuario> usuarios = usuarioRepo.findAll();
		
		return converteParaIDTOs(usuarios);
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public InfoUsuarioDTO buscaUsuarioById(@PathVariable Long id) {
		return converteParaIDTO(usuarioRepo.findById(id).get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long salvaUsuario(@RequestBody UsuarioDTO usuarioDto) {
		
		Usuario usuario = new Usuario();
		
		usuario = converteDeDTO(usuarioDto);
		
		usuarioRepo.save(usuario);
		
		return usuario.getId();
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizaUsuario(@RequestBody InfoUsuarioDTO infoUsuario) {
		Usuario registro = converteDeIDTO(infoUsuario);
		
		usuarioRepo.save(registro);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletaUsuario(@RequestBody InfoUsuarioDTO infoUsuario) {
		Usuario registro = converteDeIDTO(infoUsuario);
		
		usuarioRepo.delete(registro);
		
	}
	
	private Usuario converteDeDTO(UsuarioDTO usuario) {
		Usuario ret = new Usuario();
		
		ret.setNome(usuario.getNome());
		ret.setEmail(usuario.getEmail());
		ret.setSenha(usuario.getSenha());
		ret.setCpf(usuario.getCpf());
		ret.setLogradouro(usuario.getLogradouro());
		ret.setNumero(usuario.getNumero());
		ret.setCidade(usuario.getCidade());
		ret.setEstado(usuario.getEstado());
		ret.setDataNascimento(usuario.getDataNascimento());
		ret.setCargo(cargoRepo.findById(usuario.getIdCargo()).get());
		
		return ret;
	}
	
	private Usuario converteDeIDTO(InfoUsuarioDTO usuario) {
		
		Usuario ret = new Usuario();
		String aux [] = usuario.getSelf().split("/");
		Long idUsuario = Long.valueOf(aux[1]);
		aux = usuario.getCargo().split("/");
		Long idCargo = Long.valueOf(aux[1]);
		Usuario registroAntigo = usuarioRepo.findById(idUsuario).get();//anti-exploit
		
		ret.setId(idUsuario);
		ret.setNome(usuario.getNome());
		ret.setEmail(usuario.getEmail());
		ret.setSenha(registroAntigo.getSenha());
		ret.setLogradouro(usuario.getLogradouro());
		ret.setNumero(usuario.getNumero());
		ret.setCidade(usuario.getCidade());
		ret.setEstado(usuario.getEstado());
		ret.setDataNascimento(usuario.getDataNascimento());
		ret.setCargo(cargoRepo.findById(idCargo).get());
		ret.setCompras(registroAntigo.getCompras());
		ret.setCompras(registroAntigo.getCompras());
		
		return ret;
	}
	
	private InfoUsuarioDTO converteParaIDTO(Usuario usuario) {
		InfoUsuarioDTO ret = new InfoUsuarioDTO();
		
		ret.setSelf("/usuario/"+usuario.getId());
		ret.setNome(usuario.getNome());
		ret.setEmail(usuario.getEmail());
		ret.setLogradouro(usuario.getLogradouro());
		ret.setNumero(usuario.getNumero());
		ret.setCidade(usuario.getCidade());
		ret.setEstado(usuario.getEstado());
		ret.setDataNascimento(usuario.getDataNascimento());
		ret.setCargo("/cargo/" + usuario.getCargo().getId());
		
		ret.setCompras(ConversorDTOs.vendasParaIDTO(usuario.getCompras()));
		
		return ret;
	}
	
	private List<InfoUsuarioDTO> converteParaIDTOs(List<Usuario> usuarios){
		
		List<InfoUsuarioDTO> ret = new ArrayList<>();
		
		if(!usuarios.isEmpty()) {
			for (Usuario usuario : usuarios) {
				ret.add(converteParaIDTO(usuario));
			}
		}
		
		return ret;		
	}
}
