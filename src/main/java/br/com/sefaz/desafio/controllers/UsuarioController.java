 package br.com.sefaz.desafio.controllers;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.sefaz.desafio.models.Telefone;
import br.com.sefaz.desafio.models.Usuario;
import br.com.sefaz.desafio.respository.TelefoneRepository;
import br.com.sefaz.desafio.respository.UsuarioRepository;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usr ;
	
	@Autowired
	private TelefoneRepository tfr;
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.GET)
	public ModelAndView novoUs(Usuario usuario,Telefone telefone){ 
		ModelAndView mv= new ModelAndView ("usuario/formUsuario");
		
			return mv;
	}
	
	
	@RequestMapping(value="/cadastrarUsuario", method=RequestMethod.POST)
	public ModelAndView salvarUsuario(@Valid Usuario usuario,BindingResult resU,@Valid Telefone telefone,BindingResult resT,RedirectAttributes atts){
		if(resU.hasErrors()||resT.hasErrors()) {
			
			return novoUs(usuario, telefone);
		
		}
		
		ModelAndView mv = new ModelAndView("redirect:/cadastrarUsuario");
		telefone.setUsuario(usuario);	
		usr.save(usuario);
		tfr.save(telefone);
		atts.addFlashAttribute("mensagem", "Usuario Adicionado com Sucesso!");
		
		return mv ;
	}
	
	
	
	@RequestMapping("/listarUsuarios")
	public ModelAndView  listaUsuarios(@RequestParam(defaultValue="0")int page, RedirectAttributes att) {		
		ModelAndView mv = new ModelAndView("usuario/listarUsuarios");			                          
		mv.addObject("usuarios", usr.findAll(PageRequest.of(page, 5)));
		mv.addObject("pgat",page);
		return mv;
	}
	
	
	

	@RequestMapping(value="/editarUsuario", method=RequestMethod.GET)
	public ModelAndView editUsuario(long id) {
		Usuario usuario = usr.findById(id);
		
		ModelAndView mv = new ModelAndView("usuario/editarUsuario");
		
		mv.addObject("usuario", usuario);	
		return mv;
	}
	
	
	
	@RequestMapping(value="/editarUsuario", method=RequestMethod.POST)
	public ModelAndView updateUsuario(@Valid Usuario usuario,BindingResult res,RedirectAttributes atts) {
		ModelAndView mv = new ModelAndView();		
		if(res.hasErrors()){
			
			mv.addObject("usuario", usuario);
			atts.addFlashAttribute("mensagem", "Verifique os campos!");
			
			mv = new ModelAndView("redirect:/editarUsuario");
			return editUsuario(usuario.getId());

			
			
		}
		usr.save(usuario);
		mv = new ModelAndView("redirect:/editarUsuario");			
		mv.addObject("id", usuario.getId());
		atts.addFlashAttribute("mensagem", "Usuario Editado com Sucesso!");
		return mv;
	}
	
	@RequestMapping(value="/editarTelefone", method=RequestMethod.GET)
	public ModelAndView editarTelefone(long id) {
		Telefone telefone = tfr.findById(id);
		Usuario usuario = telefone.getUsuario();
		ModelAndView mv = new ModelAndView("usuario/editarTelefone");
		
		mv.addObject("telefone",telefone);
		mv.addObject("usuario",usuario);
		
		System.out.println("Nome do Usuario: "+usuario.getNome()+"-- ID: "+usuario.getId()+"\ntelefone ID: "+telefone.getId());
		return mv;
	}
	
	
	
	@RequestMapping(value="/editarTelefone", method=RequestMethod.POST)
	public ModelAndView updateTelefone(@RequestParam("usuario")Usuario usuario, @Valid Telefone telefone,BindingResult res,RedirectAttributes atts) {
		ModelAndView mv = new ModelAndView();	
		if(res.hasErrors()){
			mv.addObject("id",telefone.getId());
			//mv = new ModelAndView("redirect:/editarTelefone");
			
			atts.addFlashAttribute("mensagem", "Verifique os campos!");
			return editarTelefone(telefone.getId());
					
		}
		
		
		telefone.setUsuario(usuario);
		tfr.save(telefone);
		mv.addObject("telefone", telefone);
		long id = usuario.getId();
		
		
		atts.addFlashAttribute("mensagem", "Telefone Editado com Sucesso!");
		mv = new ModelAndView("redirect:/"+id);
		return mv;
	}
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView detalhesUsuario(@PathVariable("id") long id,@RequestParam(defaultValue="0")int page) {
		Usuario usuario = usr.findById(id);
		ModelAndView mv = new ModelAndView("usuario/detalhesUsuario");
		mv.addObject("usuario", usuario);
		mv.addObject("telefones",tfr.findByUsuario(usuario, PageRequest.of(page, 3)));
		mv.addObject("paginaAtual",page); //pagina Atual para paginação

		
		return mv;
	}
	
	@RequestMapping("/deletarUsuario")
	public String deletarUsuario(long id) {
		Usuario usuario =usr.findById(id);
		usr.delete(usuario);
		return "redirect:/listarUsuarios";
	}
	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String detalhesUsuarioPost(@PathVariable("id") long id, @Valid Telefone telefone,BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{id}";
		
		}
		Usuario usuario = usr.findById(id);
		telefone.setUsuario(usuario);
		tfr.save(telefone);
		attributes.addFlashAttribute("mensagem", "Telefone Adicionado com Sucesso!");
		return "redirect:/{id}";
	
	}
	
	

	@RequestMapping("/deletarTelefone")
	public String deletarTelefone(long id) {
		Telefone telefone = tfr.findById(id);
		tfr.delete(telefone);
		
		Usuario usuario = telefone.getUsuario();
		long idLong = usuario.getId();
		String ids = ""+idLong;
		return "redirect:/"+ids;
	}
	
	

}
