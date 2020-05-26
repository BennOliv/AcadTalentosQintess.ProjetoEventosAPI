package br.com.qintess.projetoEventosAPI.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import br.com.qintess.projetoEventosAPI.dto.EventoDTO;
import br.com.qintess.projetoEventosAPI.dto.InfoEventoDTO;
import br.com.qintess.projetoEventosAPI.dto.InfoIngressoDTO;
import br.com.qintess.projetoEventosAPI.dto.IngressoDTO;
import br.com.qintess.projetoEventosAPI.model.Evento;
import br.com.qintess.projetoEventosAPI.model.Ingresso;
import br.com.qintess.projetoEventosAPI.repository.CasaShowRepository;
import br.com.qintess.projetoEventosAPI.repository.EventoRepository;
import br.com.qintess.projetoEventosAPI.repository.IngressoRepository;
import br.com.qintess.projetoEventosAPI.util.*;

@RestController
@RequestMapping("/evento")
public class EventoController {

	@Autowired
	private CasaShowRepository casaRepo;
	
	@Autowired
	private EventoRepository eveRepo;
	
	@Autowired
	private IngressoRepository ingRepo;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InfoEventoDTO> buscaEventos() throws UnsupportedEncodingException{
		return converteVariosParaIDTO(eveRepo.findAll());
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public InfoEventoDTO buscaIngressoById(@PathVariable Long id) throws UnsupportedEncodingException{
		return converteParaIDTO(eveRepo.findById(id).get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long salvaIngresso(@RequestBody EventoDTO evento) {
		return converteDeDTO(evento).getId();
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alteraEvento(@RequestBody InfoEventoDTO evento) {
		
		try {
			Evento registro = converteDeIDTO(evento);

			eveRepo.save(registro);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletaEvento(@RequestBody InfoEventoDTO evento) {
		
		try {
			Evento registro = converteDeIDTO(evento);
			
			eveRepo.delete(registro);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Evento converteDeDTO(EventoDTO evento) {
		
		Evento ret = new Evento();
		
		ret.setNome(evento.getNome());
		ret.setDescricao(evento.getDescricao());
		ret.setData(evento.getData());
		ret.setHrAbertura(evento.getHrAbertura());
		ret.setHrEncerramento(evento.getHrEncerramento());
		ret.setLocal(casaRepo.getOne(evento.getIdCasaShow()));
		if(evento.getImagem() != null) {
			try {
				ret.setImagemEvento(evento.getImagem().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		eveRepo.save(ret);
		
		ret.setIngressos(converteIngressosFromDTO(evento.getIngressos(), ret));
		
		eveRepo.save(ret);
		ingRepo.saveAll(ret.getIngressos());
		
		return ret;
		
	}
	
	private Evento converteDeIDTO(InfoEventoDTO evento) throws IOException {
		Evento ret = new Evento();
		
		ret.setId(evento.getId());
		ret.setNome(evento.getNome());
		ret.setDescricao(evento.getDescricao());
		ret.setData(evento.getData());
		ret.setHrAbertura(evento.getHrAbertura());
		ret.setHrEncerramento(evento.getHrEncerramento());
		ret.setLocal(casaRepo.getOne(evento.getIdCasaShow()));
		ret.setImagemEvento(evento.getImagem().getBytes());
		
		eveRepo.save(ret);
		
		ret.setIngressos(converteIngressosFromIDTO(evento.getIngressos(), ret));
		
		ingRepo.saveAll(ret.getIngressos());
		
		return ret;
	}
	
	private InfoEventoDTO converteParaIDTO(Evento evento) throws UnsupportedEncodingException {
		InfoEventoDTO ret = new InfoEventoDTO();
		
		ret.setId(evento.getId());
		ret.setNome(evento.getNome());
		ret.setDescricao(evento.getDescricao());
		ret.setData(evento.getData());
		ret.setHrAbertura(evento.getHrAbertura());
		ret.setHrEncerramento(evento.getHrEncerramento());
		ret.setIdCasaShow(evento.getLocal().getId());
		
		if(evento.getImagemEvento() != null)
			ret.setImagem(ConversorImagem.to64(evento.getImagemEvento()));
		
		ret.setIngressos(ConversorDTOs.ingressosParaIDTO(evento.getIngressos()));
		
		return ret;
		
	}
	
	private List<InfoEventoDTO> converteVariosParaIDTO(List<Evento> eventos) throws UnsupportedEncodingException {
		List<InfoEventoDTO> ret = new ArrayList<InfoEventoDTO>();
		
		if(!eventos.isEmpty()) {
			for (Evento evento : eventos) {
				ret.add(converteParaIDTO(evento));
			}
		}
		
		return ret;
	}
	
	private List<Ingresso> converteIngressosFromDTO(List<IngressoDTO> ingressos, Evento ev){
		List<Ingresso> ret = new ArrayList<Ingresso>();
		
		for (IngressoDTO ingressoDTO : ingressos) {
			Ingresso retin = new Ingresso();
			
			retin.setTipo(ingressoDTO.getTipo());
			retin.setQuantidadeDisponivel(ingressoDTO.getQtd());
			retin.setPreco(ingressoDTO.getPreco());
			retin.setEvento(ev);
			
			ret.add(retin);
		}
		
		return ret;
	}
	
	private List<Ingresso> converteIngressosFromIDTO(List<InfoIngressoDTO> ingressos, Evento ev){
		List<Ingresso> ret = new ArrayList<Ingresso>();
		
		for (InfoIngressoDTO ingressoDTO : ingressos) {
			Ingresso retin = new Ingresso();
			
			retin.setTipo(ingressoDTO.getTipo());
			retin.setQuantidadeDisponivel(ingressoDTO.getQtd());
			retin.setPreco(ingressoDTO.getPreco());
			retin.setEvento(ev);
			
			ret.add(retin);
		}
		
		return ret;
	}
}
