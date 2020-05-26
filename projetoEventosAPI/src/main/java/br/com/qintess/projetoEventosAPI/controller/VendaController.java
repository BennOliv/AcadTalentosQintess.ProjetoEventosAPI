package br.com.qintess.projetoEventosAPI.controller;

import java.time.LocalDateTime;
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

import br.com.qintess.projetoEventosAPI.dto.InfoIngressoGeradoDTO;
import br.com.qintess.projetoEventosAPI.dto.InfoItemVendaDTO;
import br.com.qintess.projetoEventosAPI.dto.InfoVendaDTO;
import br.com.qintess.projetoEventosAPI.dto.ItemVendaDTO;
import br.com.qintess.projetoEventosAPI.dto.VendaDTO;
import br.com.qintess.projetoEventosAPI.model.Ingresso;
import br.com.qintess.projetoEventosAPI.model.IngressoGerado;
import br.com.qintess.projetoEventosAPI.model.ItemVenda;
import br.com.qintess.projetoEventosAPI.model.Venda;
import br.com.qintess.projetoEventosAPI.repository.IngressoGeradoRepository;
import br.com.qintess.projetoEventosAPI.repository.IngressoRepository;
import br.com.qintess.projetoEventosAPI.repository.ItemVendaRepository;
import br.com.qintess.projetoEventosAPI.repository.UsuarioRepository;
import br.com.qintess.projetoEventosAPI.repository.VendaRepository;
import br.com.qintess.projetoEventosAPI.util.ConversorDTOs;

@RestController
@RequestMapping("/venda")
public class VendaController {

	@Autowired
	private VendaRepository vendaRepo;
	
	@Autowired
	private ItemVendaRepository itemVendaRepo;
	
	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private IngressoRepository ingressoRepo;
	
	@Autowired
	private IngressoGeradoRepository ingressoGeradoRepo;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<InfoVendaDTO> buscaVendas(){
		return ConversorDTOs.vendasParaIDTO(vendaRepo.findAll());
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public InfoVendaDTO buscaVendaPorId(@PathVariable Long id) {
		return ConversorDTOs.vendaParaIDTO(vendaRepo.findById(id).get());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Long salvaIngresso(@RequestBody VendaDTO venda) {
		return vendaConverteDeDTO(venda).getId();
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void alteraVenda(@RequestBody InfoVendaDTO venda) {
		vendaRepo.save(vendaConverteDeIDTO(venda));
	}
	
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletaVenda(@RequestBody InfoVendaDTO venda) {
		vendaRepo.delete(vendaConverteDeIDTO(venda));
	}
	
	private	Venda vendaConverteDeDTO (VendaDTO venda) {
		Venda ret = new Venda();
		double valor;
		
		ret.setUsuario(usuarioRepo.findById(venda.getIdUsuario()).get());
		ret.setDataHoraVenda(LocalDateTime.now());
		
		vendaRepo.save(ret);
		
		ret.setItens(converteItensDeDTO(venda.getItensVenda(), ret));
		
		vendaRepo.save(ret);
		
		return ret;
	}
	private Venda vendaConverteDeIDTO(InfoVendaDTO venda) {
		Venda ret = new Venda();
		String[] aux = venda.getSelf().split("/");
		ret.setId(Long.valueOf(aux[1]));
		aux = venda.getUsuario().split("/");
		ret.setUsuario(usuarioRepo.findById(Long.valueOf(aux[1])).get());
		ret.setDataHoraVenda(venda.getDataHoraVenda());
		ret.setValorTotal(venda.getValorTotal());
		
		ret.setItens(converteItensDeIDTO(venda.getItensVenda(), ret));
		
		return ret;
	}
	
	private List<ItemVenda> converteItensDeDTO(List<ItemVendaDTO> itens, Venda venda){
		List<ItemVenda> ret = new ArrayList<>();
		
			for (ItemVendaDTO itemVendaDTO : itens) {
				ItemVenda retin = new ItemVenda();
				Ingresso registroIng = ingressoRepo.findById(itemVendaDTO.getIdIngresso()).get();
				
				retin.setVenda(venda);
				retin.setIngresso(registroIng);
				retin.setQuantidade(itemVendaDTO.getQuantidade());
				retin.setValorSubTotal(itemVendaDTO.getQuantidade() * registroIng.getPreco());
				
				itemVendaRepo.save(retin);
				
				List<IngressoGerado> ingressos = new ArrayList<IngressoGerado>();
				
				for (int i = 0; i < retin.getQuantidade(); i++) {
					IngressoGerado ent = new IngressoGerado();
					
					ent.setVendaItem(retin);
					
					ingressos.add(ent);
				}
				
				ingressoGeradoRepo.saveAll(ingressos);
				
				itemVendaRepo.save(retin);
				
				ret.add(retin);
				
			}
		return ret;
	}
	
	private List<ItemVenda> converteItensDeIDTO(List<InfoItemVendaDTO> itens, Venda venda){
		List<ItemVenda> ret = new ArrayList<>();
		
		for (InfoItemVendaDTO itemVenda : itens) {
			ItemVenda retin = new ItemVenda();
			
			String[] aux = itemVenda.getSelf().split("/");
			retin.setId(itemVendaRepo.findById(Long.valueOf(aux[1])).get().getId());
			aux = itemVenda.getIngresso().split("/");
			retin.setIngresso(ingressoRepo.findById(Long.valueOf(aux[1])).get());
			aux = itemVenda.getVenda().split("/");
			retin.setVenda(vendaRepo.findById(Long.valueOf(aux[1])).get());
			retin.setQuantidade(itemVenda.getQuantidade());
			retin.setValorSubTotal(itemVenda.getSubTotal());
			
			
			List<IngressoGerado> raton = new ArrayList<>();
			
			for (InfoIngressoGeradoDTO ingressoGerado : itemVenda.getIngressosGerados()) {
				IngressoGerado ent = new IngressoGerado();
				aux = ingressoGerado.getSelf().split("/");
				ent.setId(Long.valueOf(aux[1]));
				ent.setVendaItem(retin);
				
				raton.add(ent);
			}
			ret.add(retin);
		}
		
		return ret;
	}
}