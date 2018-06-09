package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.database.DatabaseAccessor;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	// TODO : Autowire a StateDAO and create getters and setters

	@Autowired
	private DatabaseAccessor dao;

	public DatabaseAccessor getSdao() {
		return dao;
	}

	@RequestMapping(path = "addfilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/addfilm.jsp");

		List<Film> films = new ArrayList<>();
		films.add(film);
		mv.addObject(films);
		return mv;
	}

	@RequestMapping(path = "filmdetails.do", method = RequestMethod.POST)
	public String filmDetails(Film film) {
		try {
			dao.addFilm(film);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "WEB-INF/filmdetails.jsp";
	}

	@RequestMapping(path = "searchfilmbyid.do", method = RequestMethod.GET)
	public ModelAndView filmDetailsByID(String filmid) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = new ArrayList<>();
		mv.setViewName("WEB-INF/filmdetails.jsp");
		try {
			films = dao.getFilmById(Integer.parseInt(filmid));
			mv.addObject("filmsbyid", films);
			System.out.println(films);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(path = "searchfilmbykeyword.do", method = RequestMethod.GET)
	public ModelAndView filmDetailsByKeyword(String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = new ArrayList<>();
		mv.setViewName("WEB-INF/filmdetails.jsp");
		try {
			films = dao.getFilmBySearchTerm(keyword);
			mv.addObject("filmsbyid", films);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping(path = "addactor.do", method = RequestMethod.POST)
	public ModelAndView addActor(Actor actor) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/addactor.jsp");

		List<Actor> actors = new ArrayList<>();
		actors.add(actor);
		mv.addObject(actors);
		System.out.println(actors);
		return mv;
	}
	
	@RequestMapping(path = "actordetails.do", method = RequestMethod.POST)
	public String actorDetails(Actor actor) {
		try {
			dao.addActor(actor);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "WEB-INF/actordetails.jsp";
	}


}
