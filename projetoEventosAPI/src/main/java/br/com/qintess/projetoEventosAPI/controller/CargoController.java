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

import br.com.qintess.projetoEventosAPI.model.Cargo;
import br.com.qintess.projetoEventosAPI.repository.CargoRepository;

@RestController
@RequestMapping("/cargo")
public class CargoController {

	@Autowired
	private CargoRepository repo;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Cargo> buscaCargos(){
		return repo.findAll();
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cargo buscaCargoById(@PathVariable Long id) {
		return repo.getOne(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public long criaCargo(@RequestBody Cargo cargo){
		repo.save(cargo);
		
		return cargo.getId();
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alteraCargo(@RequestBody Cargo cargo){
		repo.save(cargo);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletaCargo(@RequestBody Cargo cargo) {
		repo.delete(cargo);
	}
}
