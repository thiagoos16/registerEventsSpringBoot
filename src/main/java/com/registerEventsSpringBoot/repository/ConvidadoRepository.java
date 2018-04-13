package com.registerEventsSpringBoot.repository;

import org.springframework.data.repository.CrudRepository;

import com.registerEventsSpringBoot.models.Convidado;
import com.registerEventsSpringBoot.models.Evento;

public interface ConvidadoRepository extends CrudRepository<Convidado, String> {
	Iterable<Convidado> findByEvento(Evento evento);
	Convidado findByRg(String rg);
}
