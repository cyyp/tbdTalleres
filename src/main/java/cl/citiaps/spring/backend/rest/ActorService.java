package cl.citiaps.spring.backend.rest;

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
import java.util.List;

import cl.citiaps.spring.backend.entities.Actor;
import cl.citiaps.spring.backend.entities.Film;
import cl.citiaps.spring.backend.repository.ActorRepository;
import cl.citiaps.spring.backend.repository.FilmRepository;

@CrossOrigin
@RestController  
@RequestMapping("/actors")
public class ActorService {
	
	@Autowired
	private ActorRepository actorRepository;
	
	@Autowired
	private FilmRepository filmRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Iterable<Actor> getAllUsers() {
		return actorRepository.findAll();
	}
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public  Actor findOne(@PathVariable("id") Integer id) {
		return actorRepository.findOne(id);
	}
	
	//Metodo get que encuentra todas las peliculas en las que ha participado un actor
	@RequestMapping(value = "/{id}/films", method = RequestMethod.GET)
	@ResponseBody
	public List<Film> getfilmsActor(@PathVariable("id") Integer id){
		return actorRepository.findOne(id).getfilmsActor();
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Actor create(@RequestBody Actor resource) {
	     return actorRepository.save(resource);
	}
	
	
	//Metodo post que vincula una pelicula con un actor
	//Se realiza la vinculación de una pelicula de id idFilm, con un actor de id idActor
	
	@RequestMapping(value = "/{idActor}/films/{idFilm}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Actor addFilmActor(@PathVariable("idActor") Integer idActor, @PathVariable("idFilm") Integer idFilm){
		Actor actorObj = actorRepository.findOne(idActor);
		Film filmObj = filmRepository.findOne(idFilm);
		
		if(!(actorObj.getfilmsActor().contains(filmObj))){
			
		actorObj.getfilmsActor().add(filmObj);
		
		}
		filmRepository.save(filmObj);
		return actorRepository.save(actorObj);	
	}
	
	
}