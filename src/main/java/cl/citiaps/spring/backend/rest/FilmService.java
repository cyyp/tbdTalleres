package cl.citiaps.spring.backend.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import cl.citiaps.spring.backend.entities.Film;
import cl.citiaps.spring.backend.entities.Actor;
import cl.citiaps.spring.backend.repository.FilmRepository;
import cl.citiaps.spring.backend.repository.ActorRepository;;

@CrossOrigin
@RestController
@RequestMapping("/films")
public class FilmService {

	@Autowired
	private FilmRepository filmRepository;
	
	@Autowired
	private ActorRepository actorRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Film> getAllFilms() {
		return filmRepository.findAll();
	}
	
	//Metodo get que obtiene todos los actores que han participado en una pelicula
	@RequestMapping(value = "/{id}/actors", method = RequestMethod.GET)
	@ResponseBody
	public List<Actor> getactorsFilm(@PathVariable("id") Integer id){
		return filmRepository.findOne(id).getactorsFilm();
	}
	
	
	//Metodo post que vincula un actor con una pelicula.
	//Se realiza la vinculación de un actor con id idActor, con una pelicula de id idFilm.
	
	@RequestMapping(value = "/{idFilm}/actors/{idActor}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Film addFilmActor(@PathVariable("idFilm") Integer idFilm, @PathVariable("idActor") Integer idActor){
		Actor actorObj = actorRepository.findOne(idActor);
		Film filmObj = filmRepository.findOne(idFilm);
		
		
		if(!(filmObj.getactorsFilm().contains(actorObj))){
			
		//filmObj.getactorsFilm().add(actorObj);
		actorObj.getfilmsActor().add(filmObj);
		}
		actorRepository.save(actorObj);
		return filmRepository.save(filmObj);	
	}
	
	
	
}
