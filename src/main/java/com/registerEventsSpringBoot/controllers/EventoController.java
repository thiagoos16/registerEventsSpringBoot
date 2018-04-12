package com.registerEventsSpringBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.registerEventsSpringBoot.models.Convidado;
import com.registerEventsSpringBoot.models.Evento;
import com.registerEventsSpringBoot.repository.ConvidadoRepository;
import com.registerEventsSpringBoot.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er;
	
	@Autowired
	private ConvidadoRepository cr;
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/form";
	}
	
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String form(Evento evento) {
		er.save(evento);
		
		return "redirect:/cadastrarEvento";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index"); //vai renderizar o index.html
		Iterable<Evento> eventos = er.findAll(); //Pois será carregado uma lista de Eventos vindo do banco
		mv.addObject("eventos", eventos); //enviando a lista de eventos para a view
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) { //@PathVariable pega o que é passado como parâmetro
		ModelAndView mv = new ModelAndView("evento/detalhes");
		Evento evento = er.findByCodigo(codigo);
		mv.addObject("evento", evento);
		Iterable<Convidado> convidados = cr.findByEvento(evento); 
		mv.addObject("convidados", convidados);
		return mv;
	}
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, Convidado convidado) {
		Evento evento = er.findByCodigo(codigo);
		convidado.setEvento(evento);
		cr.save(convidado);
		return "redirect:/{codigo}";
	}
}
