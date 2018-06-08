package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.film.database.DatabaseAccessor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	// TODO : Autowire a StateDAO and create getters and setters
	
	@Autowired
	private DatabaseAccessor dao;

	public DatabaseAccessor getSdao() {
		return dao;
	}

	@RequestMapping(path="addfilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/addfilm.jsp");
		mv.addObject(film);
		return mv;
	}
	
}
