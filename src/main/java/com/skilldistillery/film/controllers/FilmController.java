package com.skilldistillery.film.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
		
		List<Film> films = new ArrayList<>();
		films.add(film);
		mv.addObject(films);
		return mv;
	}
	@RequestMapping(path="filmdetails.do", method = RequestMethod.POST)
	public String filmDetails(Film film) {
		try {
			dao.addFilm(film);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "WEB-INF/filmdetails.jsp";
	}
	
}
