package br.com.qintess.projetoEventosAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.qintess.projetoEventosAPI.model.CasaShow;
import br.com.qintess.projetoEventosAPI.repository.CasaShowRepository;

@RestController
@RequestMapping("/casashow")
public class CasaShowController {

	@Autowired
	private CasaShowRepository repo;
	
	@GetMapping
	public List<CasaShow> buscaCasasShow(){
		return repo.findAll();
	}
	
	@PostMapping
	public long criaCasaShow(@RequestBody CasaShow casaShow){
		repo.save(casaShow);
		
		return casaShow.getId();
	}
	
	@PutMapping
	public void alteraCasaShow(@RequestBody CasaShow casaShow){
		repo.save(casaShow);
	}
	
	@DeleteMapping
	public void deletaCasaShow(@RequestBody CasaShow casaShow) {
		repo.delete(casaShow);
	}
}
