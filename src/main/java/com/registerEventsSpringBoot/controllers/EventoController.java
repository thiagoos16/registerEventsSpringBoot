package com.registerEventsSpringBoot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.registerEventsSpringBoot.models.Evento;
import com.registerEventsSpringBoot.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er;
	
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
	
	@RequestMapping("/{codigo}")
	public ModelAndView detalhesEvento(@PathVariable long codigo) { //@PathVariable pega o que é passado como parâmetro
		ModelAndView mv = new ModelAndView("evento/detalhes");
		Evento evento = er.findByCodigo(codigo);
		mv.addObject("evento", evento);
		return mv;
	} 
}
