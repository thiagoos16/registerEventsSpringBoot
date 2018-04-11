package com.registerEventsSpringBoot.repository;

import org.springframework.data.repository.CrudRepository;

import com.registerEventsSpringBoot.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, String> {

}
