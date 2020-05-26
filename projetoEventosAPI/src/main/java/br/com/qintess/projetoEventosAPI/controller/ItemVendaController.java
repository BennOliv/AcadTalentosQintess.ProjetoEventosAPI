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

import br.com.qintess.projetoEventosAPI.model.ItemVenda;
import br.com.qintess.projetoEventosAPI.model.ItemVendaId;
import br.com.qintess.projetoEventosAPI.repository.ItemVendaRepository;

@RestController
@RequestMapping("/itemvenda")
public class ItemVendaController {

	@Autowired
	private ItemVendaRepository repo;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ItemVenda> buscaItemVendas(){
		return repo.findAll();
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public ItemVenda buscaItemVendaById(@PathVariable Long id) {
		return repo.getOne(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ItemVendaId criaItemVenda(@RequestBody ItemVenda itemVenda){
		repo.save(itemVenda);
		
		return itemVenda.getId();
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alteraItemVenda(@RequestBody ItemVenda itemVenda){
		repo.save(itemVenda);
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletaItemVenda(@RequestBody ItemVenda itemVenda) {
		repo.delete(itemVenda);
	}
}

