package br.com.qintess.projetoEventosAPI.controller;

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

import br.com.qintess.projetoEventosAPI.dto.InfoIngressoDTO;
import br.com.qintess.projetoEventosAPI.dto.IngressoDTO;
import br.com.qintess.projetoEventosAPI.model.Ingresso;
import br.com.qintess.projetoEventosAPI.repository.EventoRepository;
import br.com.qintess.projetoEventosAPI.repository.IngressoRepository;
import br.com.qintess.projetoEventosAPI.util.ConversorDTOs;

@RestController
@RequestMapping("/ingresso")
public class IngressoController {
	
	@Autowired
	private IngressoRepository ingRepo;
	
	@Autowired
	private EventoRepository eveRepo;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InfoIngressoDTO> buscaIngresso(){
		return ConversorDTOs.ingressosParaIDTO(ingRepo.findAll());
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public InfoIngressoDTO buscaIngressoById(@PathVariable Long id){
		return ConversorDTOs.ingressoParaIDTO(ingRepo.findById(id).get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long salvaIngresso(@RequestBody IngressoDTO ingresso) {
		
		Ingresso registro = new Ingresso();
		
		registro = converteDeDTO(ingresso);

		ingRepo.save(registro);
		
		return registro.getId();
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alteraIngresso(@RequestBody InfoIngressoDTO ingresso) {
		
		Ingresso registro = converteDeIDTO(ingresso);
		
		ingRepo.save(registro);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletaIngresso(@RequestBody InfoIngressoDTO ingresso) {
		Ingresso registro = converteDeIDTO(ingresso);
		
		ingRepo.delete(registro);
	}
	
	private Ingresso converteDeDTO(IngressoDTO ingresso) {
		
		Ingresso ret = new Ingresso();
		
		ret.setTipo(ingresso.getTipo());
		ret.setQuantidadeDisponivel(ingresso.getQtd());
		ret.setPreco(ingresso.getPreco());
		ret.setEvento(eveRepo.getOne(ingresso.getEvento()));
				
		return ret;
	}
	
	private Ingresso converteDeIDTO(InfoIngressoDTO ingresso) {
		
		Ingresso ret = new Ingresso();
		String[] aux = ingresso.getSelf().split("/");
		Long idInf = Long.valueOf(aux[1]);
		aux = ingresso.getEvento().split("/");
		Long idEvInf = Long.valueOf(aux[1]);;
		
		ret.setId(idInf);
		ret.setEvento(eveRepo.getOne(idEvInf));
		ret.setTipo(ingresso.getTipo());
		ret.setQuantidadeDisponivel(ingresso.getQtd());
		ret.setPreco(ingresso.getPreco());
				
		return ret;
	}
}
