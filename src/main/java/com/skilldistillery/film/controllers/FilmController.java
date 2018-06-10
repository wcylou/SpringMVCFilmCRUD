package com.skilldistillery.film.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @RequestMapping(path = "updatefilm.do", method = RequestMethod.GET)
    public ModelAndView updateFilm(@RequestParam(name = "filmid")  int filmId) {
        ModelAndView mv = new ModelAndView();
        List <Film> films = new ArrayList<>();
        System.out.println();
        films = dao.getFilmById(filmId);
        System.out.println(films);
        Film film = films.get(0);
        mv.addObject("filmupdate", film);
        mv.setViewName("WEB-INF/updatefilm.jsp"); // redirect to new mapping
        return mv;
    }
    
    @RequestMapping(path = "updatefilmdetails.do", method = RequestMethod.POST)
    public ModelAndView updateFilmDetails(Film film, @RequestParam(name = "filmid") int filmId) {
        ModelAndView mv = new ModelAndView();
        System.out.println(filmId);
        List <Film> films = new ArrayList<>();
        films.add(dao.updateFilm(film, filmId));
        mv.addObject("filmsbyid", films);
        mv.setViewName("WEB-INF/filmdetails.jsp");
        return mv;
    }
    
    @RequestMapping(path = "deletefilm.do", method = RequestMethod.GET)
    public String deleteFilm(int filmid) {
        List <Film> films = new ArrayList<>();
        System.out.println();
        films = dao.getFilmById(filmid);
        System.out.println(films);
        Film film = films.get(0);
        dao.deleteFilm(film);
        return "intro.html";
    }
    

    @RequestMapping(path = "filmcreated.do", // mapping to handle redirect
            method = RequestMethod.GET) // "state" is already in model for
    public ModelAndView created() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("WEB-INF/filmdetails.jsp");
        return mv;
    }

    @RequestMapping(path = "searchfilmbyid.do", method = RequestMethod.GET)
    public ModelAndView filmDetailsByID(int filmid) {
        ModelAndView mv = new ModelAndView();
        List<Film> films = new ArrayList<>();
        mv.setViewName("WEB-INF/filmdetails.jsp");
            films = dao.getFilmById(filmid);
            mv.addObject("filmsbyid", films);
            System.out.println(films);
        return mv;
    }
    @RequestMapping(path = "searchactorbyid.do", method = RequestMethod.GET)
    public ModelAndView actorDetailsByID(int actorid) {
    	ModelAndView mv = new ModelAndView();
    	List<Actor> actors = new ArrayList<>();
    	mv.setViewName("WEB-INF/actordetails.jsp");
    	actors.add(dao.getActorById(actorid));
    	mv.addObject("actorsbyid", actors);
    	System.out.println(actors);
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
    public String actorDetails(Actor actor, RedirectAttributes redir) {
            dao.addActor(actor);
            List<Actor> actors = new ArrayList<>();
            actors.add(actor);
            redir.addFlashAttribute("actorsbyid", actors);
            redir.addFlashAttribute("actor", actor);
        return "redirect:actorcreated.do";
    }
    @RequestMapping(path = "actorcreated.do", // mapping to handle redirect
            method = RequestMethod.GET) // "state" is already in model for
    public ModelAndView actorCreated() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("WEB-INF/actordetails.jsp");
        return mv;
    }

    @RequestMapping(path = "updateactor.do", method = RequestMethod.GET)
    public ModelAndView updateActor(@RequestParam(name = "actorid")  int actorId) {
    	ModelAndView mv = new ModelAndView();
    	List <Actor> actors = new ArrayList<>();
    	System.out.println();
    	actors.add(dao.getActorById(actorId));
    	Actor actor= actors.get(0);
    	mv.addObject("actorupdate", actor);
    	mv.setViewName("WEB-INF/updateactor.jsp"); // redirect to new mapping
    	return mv;
    }
    @RequestMapping(path = "updateactordetails.do", method = RequestMethod.POST)
    public ModelAndView updateActorDetails(Actor actor, @RequestParam(name = "actorid") int actorId) {
    	ModelAndView mv = new ModelAndView();
    	System.out.println(actorId);
    	System.out.println(actor);
    	List <Actor> actors = new ArrayList<>();
    	actors.add(dao.updateActor(actor, actorId));
    	mv.addObject("actorsbyid", actors);
    	mv.setViewName("WEB-INF/actordetails.jsp");
    	return mv;
    }
    
    @RequestMapping(path = "deleteactor.do", method = RequestMethod.GET)
    public String deleteActor(int actorid) {
    	List <Actor> actors = new ArrayList<>();
    	System.out.println();
    	actors.add(dao.getActorById(actorid));
    	System.out.println(actors);
    	Actor actor = actors.get(0);
    	
    	return "intro.html";
    }

}