package cl.citiaps.spring.backend.entities;


import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="film")
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@ManyToMany(mappedBy = "filmsActor")
	private List<Actor> actorsFilm;
	
	
	@Id
	@Column(name="film_id", unique=true, nullable=false)
	private int filmId;
	
	@Column(name="title", nullable=false, length=45)
	private String filmTitle;
	
	@Column(name = "description", nullable = false, length=100)
	private String filmDescription;
	
	@Column(name = "release_year", unique = false, nullable = false)
	private int filmReleaseYear;
	
	public Film(){	
	}

	public List<Actor> getactorsFilm(){
		return this.actorsFilm;
	}
	
	public int getFilmId() {
		return this.filmId;
	}
	
	public String getFilmTitle(){
		return this.filmTitle;
	}
	
	public String getfilmDescription(){
		return this.filmDescription;
	}
	
	public int getReleaseYear(){
		return this.filmReleaseYear;
	}
}

