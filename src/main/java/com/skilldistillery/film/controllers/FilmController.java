package com.skilldistillery.film.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skilldistillery.film.database.DatabaseAccessor;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;

@Controller
public class FilmController {
	@Autowired
	private DatabaseAccessor dao;

	public DatabaseAccessor getSdao() {
		return dao;
	}

//	@RequestMapping(path = "addfilm.do", method = RequestMethod.POST)
//	public ModelAndView addFilm(Film film) {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("WEB-INF/addfilm.jsp");
//
//		List<Film> films = new ArrayList<>();
//		films.add(film);
//		mv.addObject(films);
//		return mv;
//	}

	@RequestMapping(path = "addfilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/addfilm.jsp"); // redirect to new mapping
		return mv;
	}

	@RequestMapping(path = "filmdetails.do", method = RequestMethod.POST)
	public String filmDetails(Film film, RedirectAttributes redir) {
			dao.addFilm(film);
			List<Film> films = new ArrayList<>();
			films.add(film);
			redir.addFlashAttribute("filmsbyid", films); // add "state" to model for next request
			redir.addFlashAttribute("film", film);
		return "redirect:filmcreated.do";
	}
	@RequestMapping(path = "updatefilm.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(Film film) {
		ModelAndView mv = new ModelAndView();
		mv.addObject(film);
		mv.setViewName("WEB-INF/updatefilm.jsp"); // redirect to new mapping
		return mv;
	}
	@RequestMapping(path = "updatefilmdetails.do", method = RequestMethod.POST)
	public String updateFilmDetails(Film film, RedirectAttributes redir) {
		dao.updateFilm(film);
		List<Film> films = new ArrayList<>();
		films.add(film);
		redir.addFlashAttribute("filmsbyid", films); // add "state" to model for next request
		return "redirect:filmcreated.do";
	}

	@RequestMapping(path = "filmcreated.do", // mapping to handle redirect
			method = RequestMethod.GET) // "state" is already in model for
	public ModelAndView created() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/filmdetails.jsp");
		return mv;
	}

	@RequestMapping(path = "searchfilmbyid.do", method = RequestMethod.GET)
	public ModelAndView filmDetailsByID(String filmid) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = new ArrayList<>();
		mv.setViewName("WEB-INF/filmdetails.jsp");
			films = dao.getFilmById(Integer.parseInt(filmid));
			mv.addObject("filmsbyid", films);
			System.out.println(films);
		return mv;
	}

	@RequestMapping(path = "searchfilmbykeyword.do", method = RequestMethod.GET)
	public ModelAndView filmDetailsByKeyword(String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> films = new ArrayList<>();
		mv.setViewName("WEB-INF/filmdetails.jsp");
			films = dao.getFilmBySearchTerm(keyword);
			mv.addObject("filmsbyid", films);
			// TODO Auto-generated catch block
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
			dao.addActor(actor);
		return "WEB-INF/actordetails.jsp";
	}

}
